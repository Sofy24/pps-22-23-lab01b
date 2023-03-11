package e2;

import java.util.HashSet;
import java.util.Set;

public class FlagStrategyImpl implements FlagStrategy{
    private Set<Cell> flagList = new HashSet<>();

    public FlagStrategyImpl() {
    }

    @Override
    public void changeFlagList(Cell cell) {
        if (this.flagList.contains(cell)){
            this.flagList.remove(cell);
        } else {
            this.flagList.add(cell);
        }
    }

    @Override
    public Set<Cell> getFlagList() {
        return this.flagList;
    }
    
}
