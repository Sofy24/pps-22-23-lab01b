package e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoclickStrategyImpl implements AutoclickStrategy{
    private final int size;
    private final int numberOfMines;
    private MinesStrategy minesStrategy;
    private Set<Cell> revealedCells;
    private List<Cell> cellToBeComputed;

    public AutoclickStrategyImpl(int size, int numberOfMines, MinesStrategy minesStrategy) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.minesStrategy = minesStrategy;
        this.revealedCells = new HashSet<>();
        this.cellToBeComputed = new ArrayList<>();
    }

    private void autoclickCells(Cell cell){
        this.cellToBeComputed.addAll(cell.getNotRevealedAdjacentCells(cell, size));
        cell.setRevealed(true);
        this.cellToBeComputed.remove(cell);
        this.cellToBeComputed.removeAll(this.cellToBeComputed.stream().filter(c -> minesStrategy.getMines().contains(c)).toList());
        this.cellToBeComputed.removeAll(this.minesStrategy.getCellsNearMines());
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

/*package e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoclickStrategyImpl implements AutoclickStrategy{
    private final int size;
    private final int numberOfMines;
    private MinesStrategy minesStrategy;
    private Set<Cell> autoclickedCells = new HashSet<>();
    List<Cell> areGoingToCheckCells = new ArrayList<>();

    public AutoclickStrategyImpl(int size, int numberOfMines, MinesStrategy minesStrategy) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.minesStrategy = minesStrategy;
    }
    

    private boolean areThereNearbyMines(){
        return !this.minesStrategy.getLocalNumberOfMines().isEmpty();
    }
    
    public void reveal(Cell cell) {
        if (cell.getX() < 0 || cell.getX() >= size || cell.getY() < 0 || cell.getY() >= size) {
            return;
        }
       
        if (cell.isRevealed()) { //reveal(adjacentCell);
            return;
        }
    
        cell.setRevealed(true);
        this.autoclickedCells.add(cell);
    
        if (this.minesStrategy.getMines().contains(cell)) {
            // Game over
            return;
        }
    
        if (this.minesStrategy.getLocalNumberOfMines().get(cell) == 0) {
            // Recursively reveal all adjacent cells
            cell.getAdjacentCells(cell, size).stream().forEach(adjacentCell -> reveal(adjacentCell));
        }
    }
    



    public void autoclick(Cell cell) {
        this.minesStrategy.setLocalNumberOfMines(cell);
        int adjacentMines = this.minesStrategy.getLocalNumberOfMines().get(cell);
        int adjacentUnrevealed = cell.getNotRevealedAdjacentCells(cell, size).size();
    
        if (adjacentMines == 0 || adjacentUnrevealed == 0) {
            return;
        }
    
        Set<Cell> adjacentCells = cell.getAdjacentCells(cell, size);
        Set<Cell> unrevealedAdjacentCells = new HashSet<>();
    
        for (Cell adjacentCell : adjacentCells) {
            if (!adjacentCell.isRevealed()) { //&& !adjacentCell.isFlagged()
                unrevealedAdjacentCells.add(adjacentCell);
            }
        }
    
        if (unrevealedAdjacentCells.size() == adjacentMines) {
            for (Cell adjacentCell : adjacentCells) { //&& !adjacentCell.isFlagged()
                if (!adjacentCell.isRevealed()) {
                    adjacentCell.setRevealed(true);
                    this.autoclickedCells.add(adjacentCell);
                }
            }
    
            for (Cell adjacentCell : adjacentCells) {
                reveal(adjacentCell);
                // if (adjacentCell.isFlagged()) {
                //     // Recursively reveal all adjacent cells
                //     reveal(adjacentCell);
                // }
            }
        }
    }
    

    // /private void computeAutoClickedCells(Set<Cell> cells){
    //     for (Cell cell : cells){
    //         this.minesStrategy.setLocalNumberOfMines(cell);
    //         if(!this.minesStrategy.getMines().contains(cell) && !areThereNearbyMines()){
    //             this.autoclickedCells.add(cell);
    //             cell.setRevealed(true);
    //             areGoingToCheckCells.addAll(cell.getNotRevealedAdjacentCells(cell, size));
    //         }
    //     }
    //     System.out.println("going"+ areGoingToCheckCells);
    // }/

    private void computeAutoClickedCells(Cell cell){
        this.areGoingToCheckCells.remove(cell);
        this.minesStrategy.setLocalNumberOfMines(cell);
        if(!this.minesStrategy.getMines().contains(cell) && !areThereNearbyMines()){
            Set<Cell> cells = cell.getNotRevealedAdjacentCells(cell, size);
            for (Cell c : cells){
                if(!this.areGoingToCheckCells.contains(c) && !c.isRevealed()){
                    this.areGoingToCheckCells.add(c);
                }
            } 
        }
        this.autoclickedCells.add(cell);
        cell.setRevealed(true);
        System.out.println("goi"+this.areGoingToCheckCells);
        
    }


    @Override
    public Set<Cell> getAutoClickedCells(Cell cell) {
        // this.areGoingToCheckCells.add(cell);
        // while(!this.areGoingToCheckCells.isEmpty()) {
        //     computeAutoClickedCells(this.areGoingToCheckCells.get(0));
        // }
        return this.autoclickedCells;
    }
    
}*/
