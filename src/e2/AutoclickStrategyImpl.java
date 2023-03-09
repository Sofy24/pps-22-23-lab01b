package e2;

import java.util.HashSet;
import java.util.Set;

public class AutoclickStrategyImpl implements AutoclickStrategy{
    private final int size;
    private final int numberOfMines;
    private Set<Pair<Integer, Integer>> autoclickedPositions = new HashSet<>();

    public AutoclickStrategyImpl(int size, int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
    }

    /*private void computeAutoClickedPositions(Pair<Integer, Integer> position){
        if(!this.getMines().contains(position) && !AreThereNearbyMines()){
            this.autoclickedPositions.add(position);
            System.out.println(getNearbyPositions(position));
            /*for (Pair<Integer, Integer> pos : getNearbyPositions(position)){
                computeAutoClickedPositions(pos);
            }
            //getNearbyPositions(position).forEach(pos -> computeAutoClickedPositions(pos));
        }
    }*/

    @Override
    public Set<Pair<Integer, Integer>> getAutoClickedPositions(Pair<Integer, Integer> position) {
        //computeAutoClickedPositions(position);
        return this.autoclickedPositions;
    }
    
}
