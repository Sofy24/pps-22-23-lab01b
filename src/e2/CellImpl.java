package e2;

import java.util.Set;
import java.util.stream.Collectors;

public class CellImpl implements Cell {
    private final int x;
    private final int y;
    private boolean isRevealed = false;
    private String text = "";
    private int localNumberOfMines = 0;

    public CellImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLocalNumberOfMines() {
        return this.localNumberOfMines;
    }

    public void setLocalNumberOfMines(int localNumberOfMines) {
        this.localNumberOfMines = localNumberOfMines;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isRevealed() {
        return this.isRevealed;
    }

    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    @Override
    public Set<Cell> getAdjacentCells(Cell cell, int size) {
        Set<Cell> nearbyCells = Set.of(new CellImpl(1,1),
        new CellImpl(0,1), new CellImpl(-1,1), new CellImpl(1,0),
        new CellImpl(-1,-1), new CellImpl(-1,0), new CellImpl(1,-1),
        new CellImpl(0,-1));
        return nearbyCells.stream()
        .filter(newCell -> newCell.getX() >= 0 && newCell.getY() >= 0)
        .filter(newCell -> newCell.getX() < size && newCell.getY() < size)
        .map(newCell -> GridSingleton.getCell(newCell.getX() + cell.getX(), newCell.getY() + cell.getY()))
        .collect(Collectors.toSet());
    } 

    @Override
    public Set<Cell> getNotRevealedAdjacentCells(Cell cell, int size) {
        Set<Cell> nearbyCells = Set.of(new CellImpl(1,1),
        new CellImpl(0,1), new CellImpl(-1,1), new CellImpl(1,0),
        new CellImpl(-1,-1), new CellImpl(-1,0), new CellImpl(1,-1),
        new CellImpl(0,-1));
        return nearbyCells.stream()
        .filter(newCell -> newCell.getX() + cell.getX() >= 0 && newCell.getY() + cell.getY() >= 0)
        .filter(newCell -> newCell.getX() + cell.getX() < size && newCell.getY() + cell.getY() < size)
        .map(newCell -> GridSingleton.getCell(newCell.getX() + cell.getX(), newCell.getY() + cell.getY()))
        .filter(newCell -> !newCell.isRevealed())
        .collect(Collectors.toSet());
    }  


    
	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
        return x == other.getX() && y == other.getY();
	}

}
