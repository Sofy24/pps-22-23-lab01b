package e2;

import java.util.Set;

public interface Cell {

    /**
     * @param cell
     * @param size
     * @return a set of cells adjacent to the given cell
     */
    Set<Cell> getAdjacentCells(Cell cell, int size);

    int getX();

    int getY();

    /**
     * @param cell
     * @param size
     * @return a set of not revealed cells adjacent to the given cell
     */
    Set<Cell> getNotRevealedAdjacentCells(Cell cell, int size);

    void setRevealed(boolean b);

    boolean isRevealed();

    void setLocalNumberOfMines(int number);

    int getLocalNumberOfMines();

    void setText(String string);

    String getText();

}
