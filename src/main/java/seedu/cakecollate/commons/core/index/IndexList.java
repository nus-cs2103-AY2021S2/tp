package seedu.cakecollate.commons.core.index;

import java.util.Collections;
import java.util.List;

public class IndexList {
    private List<Index> indexList;

    public IndexList(List<Index> indexList) {
        this.indexList = indexList;
    }

    /**
     * Sorts {@code IndexList} in descending order.
     */
    public void sortList() {
        Collections.sort(this.indexList);
        Collections.reverse(this.indexList);
    }

    public void add(Index index) {
        this.indexList.add(index);
    }

    public List<Index> getIndexList() {
        return this.indexList;
    }

    /** Checks whether two @code IndexList}s are equal.
     * @param other {@code IndexList} to compare this list to.
     * @return true if the two {@code IndexList}s are equal and false otherwise.
     */
    public boolean checkIfEqual(IndexList other) {
        boolean equal = true;
        if (this.indexList.size() != other.getIndexList().size()) {
            return false;
        } else {
            for (int i = 0; i < other.getIndexList().size(); i++) {
                if (!this.indexList.get(i).equals(other.indexList.get(i))) {
                    equal = false;
                }
            }
        }
        return equal;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IndexList // instanceof handles nulls
                && checkIfEqual((IndexList) other)); // state check
    }

}
