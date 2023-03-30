package e2;

import java.util.List;
import java.util.Set;

public interface Logics {

    /**
     * @return the mines
     */
    List<Cell> getMines();

    /**
     * @return whether you've lost the game
     */
    boolean didYouLose();

    /**
     * @return whether you've won the game
     */
    boolean areYouAWinner(Set<Cell> clickedCells, Set<Cell> allCells);

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
     * @param cell
     * @return a set with the autoclicked cells
     */
    Set<Cell> getAutoClickedCells(Cell cell);

    /**
     * @param cell
     */
    void changeFlagList(Cell cell);

    /**
     * @return a list with cells that have the flag
     */
    Set<Cell> getFlagList();
}
