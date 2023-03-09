package e2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MinesStrategyImpl implements MinesStrategy{
    private final int size;
    private final int numberOfMines;
    private Map<Pair<Integer, Integer>, Integer> localNumberOfMines = new HashMap<>();
    private List<Pair<Integer, Integer>> mines = new ArrayList<>();

    public MinesStrategyImpl(int size, int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        createMines();
    }

    private void createMines(){
        final Supplier<Integer> random = ()->new Random().nextInt(size);   
		do {
			mines.add(new Pair<>(random.get(), random.get()));
		} while (mines.stream().distinct().count() < this.numberOfMines);
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


    @Override
    public boolean isThereMine(Pair<Integer, Integer> position) {
        return this.getMines().contains(position);
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

    @Override
    public List<Pair<Integer, Integer>> getMines() {
        return this.mines;
    }
    
}
