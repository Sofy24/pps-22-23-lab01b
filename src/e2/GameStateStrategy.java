package e2;

import java.util.List;
import java.util.Set;

public interface GameStateStrategy{

    /**
     * @return whether you've lost the game
     */
    boolean didYouLose();

    /**
     * @return whether you've won the game
     */
    boolean areYouAWinner(Set<Pair<Integer, Integer>> clickedCells, Set<Pair<Integer, Integer>> allPositions);
}
