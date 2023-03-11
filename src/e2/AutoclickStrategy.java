package e2;

import java.util.Set;

public interface AutoclickStrategy{

    /**
     * @param cell
     * @return a set with the autoclicked cells
     */
    Set<Cell> getAutoClickedCells(Cell cell);
    
}
