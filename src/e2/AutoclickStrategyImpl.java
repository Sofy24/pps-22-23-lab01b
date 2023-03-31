package e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoclickStrategyImpl implements AutoclickStrategy{
    private final int size;
    private MinesStrategy minesStrategy;
    private Set<Cell> revealedCells;
    private List<Cell> cellToBeComputed;

    public AutoclickStrategyImpl(int size, MinesStrategy minesStrategy) {
        this.size = size;
        this.minesStrategy = minesStrategy;
        this.revealedCells = new HashSet<>();
        this.cellToBeComputed = new ArrayList<>();
    }

    private void autoclickCells(Cell cell){
        if (cell.getLocalNumberOfMines() == 0){
            this.cellToBeComputed.addAll(cell.getNotRevealedAdjacentCells(cell, size));
        }
        cell.setRevealed(true);
        this.cellToBeComputed.remove(cell);
        this.cellToBeComputed.removeAll(this.cellToBeComputed.stream().filter(c -> minesStrategy.getMines().contains(c)).toList());

        this.revealedCells.add(cell);
        if (this.cellToBeComputed.size() > 0){
            autoclickCells(this.cellToBeComputed.get(0));
        }
    }
    
    @Override
    public Set<Cell> getAutoClickedCells(Cell cell) {
        autoclickCells(cell);
        return this.revealedCells;
    }



    
}
