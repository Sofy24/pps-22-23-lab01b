package e2;

import java.util.List;
import java.util.Map;

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
     * @return nearby locations with number of mines
     */
    Map<Cell, Integer> getLocalNumberOfMines();


}
