package e2;

import java.util.List;
import java.util.Map;

public interface MinesStrategy{
    
    /**
     * @return the mines
     */
    List<Pair<Integer, Integer>> getMines();

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


}
