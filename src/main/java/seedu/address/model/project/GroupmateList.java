package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.groupmate.Groupmate;

public class GroupmateList {

    private final ObservableList<Groupmate> groupmates = FXCollections.observableArrayList();

    /**
     * Constructs an empty {@code ParticipantList}.
     */
    public GroupmateList() {}

    /**
     * Constructs a {@code ParticipantList}.
     *
     * @param groupmates A list of {@code Person}.
     */
    public GroupmateList(List<Groupmate> groupmates) {
        requireNonNull(groupmates);

        this.groupmates.addAll(groupmates);
    }

    /**
     * Returns the participants as a {@code ObservableList<Person>}.
     *
     * @return the participants as a {@code ObservableList<Person>}.
     */
    public ObservableList<Groupmate> getGroupmates() {
        return groupmates;
    }

    /**
     *  Returns the number of participants in the {@code ParticipantList}.
     *
     * @return the number of participants in the {@code ParticipantList}.
     */
    public int size() {
        return groupmates.size();
    }

    /**
     *  Returns the {@code Person} at the specified position in this {@code ParticipantList}.
     *
     * @return the {@code Person} at the specified position in this {@code ParticipantList}.
     */
    public Groupmate get(int index) {
        return groupmates.get(index);
    }

    /**
     * Deletes a {@code Person} from this {@code ParticipantList}.
     *
     * @param i Index of {@code Person} to be deleted.
     */
    public void delete(Integer i) {
        requireNonNull(i);
        groupmates.remove(i.intValue());
    }

    /**
     * Returns a copy of this {@code ParticipantList}
     *
     * @return A copy of this {@code ParticipantList}
     */
    public GroupmateList getCopy() {
        return new GroupmateList(getGroupmates());
    }

    /**
     * Returns a sequential stream with this {@code ParticipantList} as its source.
     *
     * @return a sequential Stream over the persons in this {@code ParticipantList}.
     */
    public Stream<Groupmate> stream() {
        return groupmates.stream();
    }

    /**
     * Adds a person to this {@code ParticipantList}.
     *
     * @param groupmate {@code Person} to add.
     */
    public void addGroupmate(Groupmate groupmate) {
        this.groupmates.add(groupmate);
    }

    /**
     * Returns true if this {@code ParticipantList} has the provided {@code Person}.
     *
     * @param groupmate the person to test.
     * @return whether the {@code Person} is in this {@code ParticipantList}.
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
