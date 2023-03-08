package e2;

import java.util.List;

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
}
