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
    private Map<Cell, Integer> localNumberOfMines = new HashMap<>();
    private List<Cell> mines = new ArrayList<>();

    public MinesStrategyImpl(int size, int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        createMines();
    }

    private void createMines(){
        final Supplier<Integer> random = ()->new Random().nextInt(size);   
        while (mines.stream().distinct().count() < this.numberOfMines){
            Cell cell = new CellImpl(random.get(), random.get());
            if(!this.mines.contains(cell)){
                mines.add(cell);
            }
        }
    }

    private List<Cell> getNearbyCells(Cell cell){
        List<Cell> nearbyCells = List.of(new CellImpl(1,1),
        new CellImpl(0,1), new CellImpl(-1,1), new CellImpl(1,0),
        new CellImpl(-1,-1), new CellImpl(-1,0), new CellImpl(1,-1),
        new CellImpl(0,-1));
        return nearbyCells.stream()
        .map(newCell -> new CellImpl(newCell.getX() + cell.getX(), newCell.getY() + cell.getY()))
        .filter(newCell -> newCell.getX() >= 0 && newCell.getY() >= 0)
        .filter(newCell -> newCell.getX() < this.size && newCell.getY() < this.size)
        .collect(Collectors.toList());
        
    }


    @Override
    public boolean isThereMine(Cell cell) {
        return this.getMines().contains(cell);
    }

    @Override
    public void setLocalNumberOfMines(Cell cell) {
        int localNumberOfMines = (int) getNearbyCells(cell).stream()
        .filter(currentCell -> this.getMines().contains(currentCell)).count();
        if (localNumberOfMines > 0){
            this.localNumberOfMines.put(cell, localNumberOfMines);
        } 
        
    }

    @Override
    public Map<Cell, Integer> getLocalNumberOfMines() {
        return this.localNumberOfMines;

    }

    @Override
    public List<Cell> getMines() {
        return this.mines;
    }
    
}
