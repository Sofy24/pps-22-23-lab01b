package e2;

import java.util.Set;

public interface FlagStrategy{
    /**
     * @param cell
     */
    void changeFlagList(Cell cell);

    /**
     * @return a list with cells that have the flag
     */
    Set<Cell> getFlagList();
    
}
