package e2;

import java.util.HashSet;
import java.util.Set;

public class FlagStrategyImpl implements FlagStrategy{
    private Set<Pair<Integer, Integer>> flagList = new HashSet<>();

    public FlagStrategyImpl() {
    }

    @Override
    public void changeFlagList(Pair<Integer, Integer> position) {
        if (this.flagList.contains(position)){
            this.flagList.remove(position);
        } else {
            this.flagList.add(position);
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getFlagList() {
        return this.flagList;
    }
    
}
