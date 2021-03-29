package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.groupmate.Groupmate;

public class GroupmateList {

    private final ObservableList<Groupmate> groupmates = FXCollections.observableArrayList();

    /**
     * Constructs an empty {@code GroupmateList}.
     */
    public GroupmateList() {}

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
     * Returns the groupmates as a {@code ObservableList<Groupmate>}.
     *
     * @return the groupmates as a {@code ObservableList<Groupmate>}.
     */
    public ObservableList<Groupmate> getGroupmates() {
        return groupmates;
    }

    /**
     *  Returns the number of groupmates in the {@code GroupmateList}.
     *
     * @return the number of groupmates in the {@code GroupmateList}.
     */
    public int size() {
        return groupmates.size();
    }

    /**
     *  Returns the {@code Groupmate} at the specified position in this {@code GroupmateList}.
     *
     * @return the {@code Groupmate} at the specified position in this {@code GroupmateList}.
     */
    public Groupmate get(int index) {
        return groupmates.get(index);
    }

    /**
     * Deletes a {@code Groupmate} from this {@code GroupmateList}.
     *
     * @param i Index of {@code Groupmate} to be deleted.
     */
    public void delete(Integer i) {
        requireNonNull(i);
        groupmates.remove(i.intValue());
    }

    /**
     * Returns a copy of this {@code GroupmateList}
     *
     * @return A copy of this {@code GroupmateList}
     */
    public GroupmateList getCopy() {
        return new GroupmateList(getGroupmates());
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
     * Set the {@code Groupmate} specified by index with a new {@code Groupmate}.
     *
     * @param i index number specifies the target {@code Groupmate}.
     * @param groupmate new {@code Groupmate} for this index.
     */
    public void setGroupmate(Integer i, Groupmate groupmate) {
        requireAllNonNull(groupmate, i);

        this.groupmates.set(i, groupmate);
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
