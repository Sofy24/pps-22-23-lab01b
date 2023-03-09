package e2;

import java.util.Set;

public interface AutoclickStrategy{

    /**
     * @param position
     * @return a set with the autoclicked positions
     */
    Set<Pair<Integer, Integer>> getAutoClickedPositions(Pair<Integer, Integer> position);
    
}
