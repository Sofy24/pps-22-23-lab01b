package e2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        this.autoclickStrategy = new AutoclickStrategyImpl(size, numberOfMines);
    }


    @Override
    public List<Pair<Integer, Integer>> getMines() {
        return this.minesStrategy.getMines();
    }


    @Override
    public boolean didYouLose() {
        return this.gameStateStrategy.didYouLose();
    }


    @Override
    public boolean isThereMine(Pair<Integer, Integer> position) {
        return this.minesStrategy.isThereMine(position);
    }


    @Override
    public boolean areYouAWinner(Set<Pair<Integer, Integer>> clickedCells, Set<Pair<Integer, Integer>> allPositions) {
        return this.gameStateStrategy.areYouAWinner(clickedCells, allPositions);
    }

    @Override
    public void setLocalNumberOfMines(Pair<Integer, Integer> position) {
        this.minesStrategy.setLocalNumberOfMines(position);        
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getLocalNumberOfMines() {
        return this.minesStrategy.getLocalNumberOfMines();

    }

    @Override
    public Set<Pair<Integer, Integer>> getAutoClickedPositions(Pair<Integer, Integer> position) {
        return this.autoclickStrategy.getAutoClickedPositions(position);
    }

    @Override
    public void changeFlagList(Pair<Integer, Integer> position) {
        this.flagStrategy.changeFlagList(position);
    }

    @Override
    public Set<Pair<Integer, Integer>> getFlagList() {
        return this.flagStrategy.getFlagList();
    }

}
