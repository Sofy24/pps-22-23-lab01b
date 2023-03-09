package e2;

import java.util.List;
import java.util.Set;

public class GameStateStrategyImpl implements GameStateStrategy {
    private boolean didYouLose;
    private List<Pair<Integer, Integer>> mines;

    public GameStateStrategyImpl(List<Pair<Integer, Integer>> mines) {
        this.didYouLose = false;
        this.mines = mines;
    }

    

    @Override
    public boolean didYouLose() {
        return this.didYouLose;
    }

    @Override
    public boolean areYouAWinner(Set<Pair<Integer, Integer>> clickedCells, Set<Pair<Integer, Integer>> allPositions) {
        clickedCells.addAll(this.mines);
        return clickedCells.equals(allPositions);
    }
    
}
