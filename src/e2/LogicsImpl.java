package e2;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogicsImpl implements Logics {
    private MinesStrategy minesStrategy;
    private FlagStrategy flagStrategy;
    private GameStateStrategy gameStateStrategy;
    private AutoclickStrategy autoclickStrategy;

    public LogicsImpl(int size, int numberOfMines) {
        this.minesStrategy = new MinesStrategyImpl(size, numberOfMines);
        this.flagStrategy = new FlagStrategyImpl();
        this.gameStateStrategy = new GameStateStrategyImpl(this.minesStrategy.getMines());
        this.autoclickStrategy = new AutoclickStrategyImpl(size, numberOfMines, minesStrategy);
    }


    @Override
    public List<Cell> getMines() {
        return this.minesStrategy.getMines();
    }


    @Override
    public boolean didYouLose() {
        return this.gameStateStrategy.didYouLose();
    }


    @Override
    public boolean isThereMine(Cell cell) {
        return this.minesStrategy.isThereMine(cell);
    }


    @Override
    public boolean areYouAWinner(Set<Cell> clickedCells, Set<Cell> allCells) {
        return this.gameStateStrategy.areYouAWinner(clickedCells, allCells);
    }

    @Override
    public void setLocalNumberOfMines(Cell cell) {
        this.minesStrategy.setLocalNumberOfMines(cell);        
    }

    @Override
    public Map<Cell, Integer> getLocalNumberOfMines() {
        return this.minesStrategy.getLocalNumberOfMines();

    }

    @Override
    public Set<Cell> getAutoClickedCells(Cell cell) {
        return this.autoclickStrategy.getAutoClickedCells(cell);
    }

    @Override
    public void changeFlagList(Cell cell) {
        this.flagStrategy.changeFlagList(cell);
    }

    @Override
    public Set<Cell> getFlagList() {
        return this.flagStrategy.getFlagList();
    }

}
