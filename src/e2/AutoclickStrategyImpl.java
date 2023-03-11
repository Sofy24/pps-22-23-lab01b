package e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoclickStrategyImpl implements AutoclickStrategy{
    private final int size;
    private final int numberOfMines;
    private Set<Cell> autoclickedCells = new HashSet<>();
    private MinesStrategy minesStrategy;
    List<Cell> areGoingToCheckCells = new ArrayList<>();

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

    /*private void computeAutoClickedCells(Set<Cell> cells){
        for (Cell cell : cells){
            this.minesStrategy.setLocalNumberOfMines(cell);
            if(!this.minesStrategy.getMines().contains(cell) && !areThereNearbyMines()){
                this.autoclickedCells.add(cell);
                cell.setRevealed(true);
                areGoingToCheckCells.addAll(cell.getNotRevealedAdjacentCells(cell, size));
            }
        }
        System.out.println("going"+ areGoingToCheckCells);
    }*/

    private void computeAutoClickedCells(Cell cell){
        this.areGoingToCheckCells.remove(cell);
        if(cell.getText().equals("") || cell.getText().equals("F")){
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
        this.areGoingToCheckCells.add(cell);
        while(!this.areGoingToCheckCells.isEmpty()) {
            computeAutoClickedCells(this.areGoingToCheckCells.get(0));
        }
        return this.autoclickedCells;
    }
    
}
