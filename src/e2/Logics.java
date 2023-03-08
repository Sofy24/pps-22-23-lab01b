package e2;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Logics {

    /**
     * @return the mines
     */
    List<Pair<Integer, Integer>> getMines();

    /**
     * @return whether you've lost the game
     */
    boolean didYouLose();

    /**
     * @return whether you've won the game
     */
    boolean areYouAWinner();

    /**
     * @param position
     * @return whether in the specific position there's a mine
     */
    boolean isThereMine(Pair<Integer, Integer> position);

    /**
     * @param position
     */
    void setLocalNumberOfMines(Pair<Integer, Integer> position);

    /**
     * @return nearby locations with number of mines
     */
    Map<Pair<Integer, Integer>, Integer> getLocalNumberOfMines();

    /**
     * @param position
     * @return a set with the autoclicked positions
     */
    Set<Pair<Integer, Integer>> getAutoClickedPositions(Pair<Integer, Integer> position);

    /**
     * @param position
     */
    void changeFlagList(Pair<Integer, Integer> position);

    /**
     * @return a list with positions that have the flag
     */
    Set<Pair<Integer, Integer>> getFlagList();
}
