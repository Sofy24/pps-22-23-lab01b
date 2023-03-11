package e2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoclickStrategyImpl implements AutoclickStrategy{
    private final int size;
    private final int numberOfMines;
    private Set<Cell> autoclickedCells = new HashSet<>();
    private MinesStrategy minesStrategy;

    public AutoclickStrategyImpl(int size, int numberOfMines, MinesStrategy minesStrategy) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.minesStrategy = minesStrategy;
    }

    /*private List<Cell> getNearbyCells(Cell cell){
        List<Cell> nearbyCells = List.of(new CellImpl(1,1),
        new CellImpl(0,1), new CellImpl(-1,1), new CellImpl(1,0),
        new CellImpl(-1,-1), new CellImpl(-1,0), new CellImpl(1,-1),
        new CellImpl(0,-1));
        return nearbyCells.stream()
        .map(newCell -> new CellImpl(newCell.getX() + cell.getX(), newCell.getY() + cell.getY()))
        .filter(newCell -> newCell.getX() >= 0 && newCell.getY() >= 0)
        .filter(newCell -> newCell.getX() < this.size && newCell.getY() < this.size)
        .collect(Collectors.toList());
        
    }*/

    

    private boolean areThereNearbyMines(){
        return !this.minesStrategy.getLocalNumberOfMines().isEmpty();
    }

    private void computeAutoClickedCells(Cell cell){
        if(!this.minesStrategy.getMines().contains(cell) && !areThereNearbyMines()){
            this.autoclickedCells.add(cell);
            cell.setRevealed(true);
            //System.out.println(getNearbyCells(cell));
            /*for (Cell pos : getNearbyCells(cell)){
                computeAutoClickedCells(cell);
            }*/
            //cell.getNotRevealedAdjacentCells(cell, size).forEach(currentCell -> computeAutoClickedCells(currentCell));
        }
    }

    @Override
    public Set<Cell> getAutoClickedCells(Cell cell) {
        computeAutoClickedCells(cell);
        return this.autoclickedCells;
    }
    
}
