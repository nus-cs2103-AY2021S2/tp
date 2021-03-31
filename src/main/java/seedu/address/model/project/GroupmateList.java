package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.groupmate.Groupmate;

/**
 * Represents a list of Groupmates.
 * Groupmate list ensures that there are no duplicates.
 * Also maintains an internal list of sorted groupmates.
 */
public class GroupmateList {

    private final ObservableList<Groupmate> groupmates = FXCollections.observableArrayList();
    private final SortedList<Groupmate> sortedGroupmates = new SortedList<>(groupmates,
            Comparator.comparing(Groupmate::toString, String::compareToIgnoreCase));

    /**
     * Constructs an empty {@code GroupmateList}.
     */
    public GroupmateList() {
    }

    /**
     * Constructs a {@code GroupmateList}.
     *
     * @param groupmates A list of {@code Groupmate}.
     */
    public GroupmateList(List<Groupmate> groupmates) {
        requireNonNull(groupmates);

        this.groupmates.addAll(groupmates);
    }

    /**
     * Returns groupmates as a {@code SortedList<Groupmate>}.
     *
     * @return groupmates as a {@code SortedList<Groupmate>}.
     */
    public SortedList<Groupmate> getSortedGroupmates() {
        return sortedGroupmates;
    }

    /**
     * Returns the number of groupmates in the {@code GroupmateList}.
     *
     * @return the number of groupmates in the {@code GroupmateList}.
     */
    public int size() {
        return groupmates.size();
    }

    /**
     * Returns the {@code Groupmate} at the specified position in the sorted {@code GroupmateList}.
     *
     * @return the {@code Groupmate} at the specified position in the sorted {@code GroupmateList}.
     */
    public Groupmate get(int index) {
        int groupmatesIndex = sortedGroupmates.getSourceIndex(index);

        return groupmates.get(groupmatesIndex);
    }

    /**
     * Deletes a {@code Groupmate} from this {@code GroupmateList}.
     *
     * @param i Index of {@code Groupmate} in the sorted list to be deleted.
     */
    public void delete(Integer i) {
        requireNonNull(i);

        int groupmatesIndex = sortedGroupmates.getSourceIndex(i);
        groupmates.remove(groupmatesIndex);
    }

    /**
     * Returns a copy of this {@code GroupmateList}
     *
     * @return A copy of this {@code GroupmateList}
     */
    public GroupmateList getCopy() {
        return new GroupmateList(getSortedGroupmates());
    }

    /**
     * Returns a sequential stream with this {@code GroupmateList} as its source.
     *
     * @return a sequential Stream over the groupmates in this {@code GroupmateList}.
     */
    public Stream<Groupmate> stream() {
        return groupmates.stream();
    }

    /**
     * Adds a groupmate to this {@code GroupmateList}.
     *
     * @param groupmate {@code Groupmate} to add.
     */
    public void addGroupmate(Groupmate groupmate) {
        this.groupmates.add(groupmate);
    }

    /**
     * Set the {@code Groupmate} specified by index in the sorted list with a new {@code Groupmate}.
     *
     * @param i index number specifies the target {@code Groupmate} in the sorted list.
     * @param groupmate new {@code Groupmate} for this index.
     */
    public void setGroupmate(Integer i, Groupmate groupmate) {
        requireAllNonNull(groupmate, i);

        int groupmatesIndex = sortedGroupmates.getSourceIndex(i);
        this.groupmates.set(groupmatesIndex, groupmate);
    }

    /**
     * Returns true if this {@code GroupmateList} has the provided {@code Groupmate}.
     *
     * @param groupmate the groupmate to test.
     * @return whether the {@code Groupmate} is in this {@code GroupmateList}.
     */
    public boolean contains(Groupmate groupmate) {
        return stream().anyMatch(groupmate::isSameGroupmate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupmateList // instanceof handles nulls
                && groupmates.equals(((GroupmateList) other).groupmates)); // state check
    }

    @Override
    public int hashCode() {
        return groupmates.hashCode();
    }

}
