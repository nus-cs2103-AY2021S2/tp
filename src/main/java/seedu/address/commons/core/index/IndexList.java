package seedu.address.commons.core.index;

import java.util.Collections;
import java.util.List;

public class IndexList {
    public List<Index> indexList;

    public IndexList(List<Index> indexList) {
        this.indexList = indexList;
    }

    public void sortList() {
        Collections.sort(this.indexList);
        Collections.reverse(this.indexList);
    }

    public void add(Index index) {
        this.indexList.add(index);
    }
}
