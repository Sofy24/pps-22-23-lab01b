package e2;

import java.util.List;
import java.util.Set;

public class GameStateStrategyImpl implements GameStateStrategy {
    private boolean didYouLose;
    private List<Cell> mines;

    public GameStateStrategyImpl(List<Cell> mines) {
        this.didYouLose = false;
        this.mines = mines;
    }

    

    @Override
    public boolean didYouLose() {
        return this.didYouLose;
    }

    @Override
    public boolean areYouAWinner(Set<Cell> clickedCells, Set<Cell> allCells) {
        clickedCells.addAll(this.mines);
        return clickedCells.size() == allCells.size();
    }
    
}
