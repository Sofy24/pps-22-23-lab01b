package e2;

import java.util.Set;

public interface FlagStrategy{
    /**
     * @param position
     */
    void changeFlagList(Pair<Integer, Integer> position);

    /**
     * @return a list with positions that have the flag
     */
    Set<Pair<Integer, Integer>> getFlagList();
    
}
