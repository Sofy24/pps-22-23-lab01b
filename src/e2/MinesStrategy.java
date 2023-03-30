package e2;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MinesStrategy{
    
    /**
     * @return the mines
     */
    List<Cell> getMines();

    /**
     * @param cell
     * @return whether in the specific cell there's a mine
     */
    boolean isThereMine(Cell cell);

    /**
     * @param cell
     */
    void setLocalNumberOfMines(Cell cell);

    /**
     * @return a set of cells adjacent to at least one mine
     */
    Set<Cell> getCellsNearMines();
}
