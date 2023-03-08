package e2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
    private final int size;
    private final int numberOfMines;
    private boolean didYouLose;
    private boolean areYouWinner;
    private Map<Pair<Integer, Integer>, Integer> localNumberOfMines = new HashMap<>();
    private List<Pair<Integer, Integer>> mines = new ArrayList<>();
    private Set<Pair<Integer, Integer>> autoclickedPositions = new HashSet<>();
    private Set<Pair<Integer, Integer>> flagList = new HashSet<>();

    public LogicsImpl(int size, int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.didYouLose = false;
        this.areYouWinner = false;
        createMines();
    }

    private void createMines(){
        final Supplier<Integer> random = ()->new Random().nextInt(size);   
		do {
			mines.add(new Pair<>(random.get(), random.get()));
		} while (mines.stream().distinct().count() < this.numberOfMines);
    }


    @Override
    public List<Pair<Integer, Integer>> getMines() {
        return this.mines;
    }


    @Override
    public boolean didYouLose() {
        return this.didYouLose;
    }


    @Override
    public boolean isThereMine(Pair<Integer, Integer> position) {
        return this.getMines().contains(position);
    }


    @Override
    public boolean areYouAWinner() {
        return this.areYouWinner;
    }

    private List<Pair<Integer, Integer>> getNearbyPositions(Pair<Integer, Integer> position){
        List<Pair<Integer, Integer>> nearbyPositions = List.of(new Pair<Integer,Integer>(1,1),
        new Pair<Integer,Integer>(0,1), new Pair<Integer,Integer>(-1,1), new Pair<Integer,Integer>(1,0),
        new Pair<Integer,Integer>(-1,-1), new Pair<Integer,Integer>(-1,0), new Pair<Integer,Integer>(1,-1),
        new Pair<Integer,Integer>(0,-1));
        return nearbyPositions.stream()
        .map(newPosition -> new Pair<>(newPosition.getX() + position.getX(), newPosition.getY() + position.getY()))
        .filter(newPosition -> newPosition.getX() >= 0 && newPosition.getY() >= 0)
        .filter(newPosition -> newPosition.getX() < this.size && newPosition.getY() < this.size)
        .collect(Collectors.toList());
        
    }



    private boolean AreThereNearbyMines(){
        return !getLocalNumberOfMines().isEmpty();
    }


    @Override
    public void setLocalNumberOfMines(Pair<Integer, Integer> position) {
        int localNumberOfMines = (int) getNearbyPositions(position).stream()
        .filter(pos -> this.getMines().contains(pos)).count();
        if (localNumberOfMines > 0){
            this.localNumberOfMines.put(position, localNumberOfMines);
        } 
        
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getLocalNumberOfMines() {
        return this.localNumberOfMines;

    }

    private void computeAutoClickedPositions(Pair<Integer, Integer> position){
        if(!this.getMines().contains(position) && !AreThereNearbyMines()){
            this.autoclickedPositions.add(position);
            System.out.println(getNearbyPositions(position));
            /*for (Pair<Integer, Integer> pos : getNearbyPositions(position)){
                computeAutoClickedPositions(pos);
            }*/
            //getNearbyPositions(position).forEach(pos -> computeAutoClickedPositions(pos));
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getAutoClickedPositions(Pair<Integer, Integer> position) {
        computeAutoClickedPositions(position);
        return this.autoclickedPositions;
    }

    @Override
    public void changeFlagList(Pair<Integer, Integer> position) {
        if (this.flagList.contains(position)){
            this.flagList.remove(position);
        } else {
            this.flagList.add(position);
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getFlagList() {
        return this.flagList;
    }

}
