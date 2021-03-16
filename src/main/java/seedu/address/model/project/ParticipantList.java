package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

public class ParticipantList {

    private final ObservableList<Person> participants = FXCollections.observableArrayList();

    /**
     * Constructs an empty {@code ParticipantList}.
     */
    public ParticipantList() {}

    /**
     * Constructs a {@code ParticipantList}.
     *
     * @param participants A list of {@code Person}.
     */
    public ParticipantList(List<Person> participants) {
        requireNonNull(participants);

        this.participants.addAll(participants);
    }

    public ObservableList<Person> getParticipants() {
        return participants;
    }

    /**
     * Returns a copy of this {@code ParticipantList}
     * @return A copy of this {@code ParticipantList}
     */
    public ParticipantList getCopy() {
        return new ParticipantList(getParticipants());
    }

    /**
     * Returns a sequential stream with this {@code ParticipantList} as its source.
     * @return a sequential Stream over the persons in this {@code ParticipantList}.
     */
    public Stream<Person> stream() {
        return participants.stream();
    }

    /**
     * Adds a person to this {@code ParticipantList}.
     *
     * @param person {@code Person} to add.
     */
    public void addParticipant(Person person) {
        this.participants.add(person);
    }

    /**
     * Returns true if this {@code ParticipantList} has the provided {@code Person}.
     *
     * @param person the person to test.
     * @return whether the {@code Person} is in this {@code ParticipantList}.
     */
    public boolean contains(Person person) {
        return stream().anyMatch(person::isSamePerson);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipantList // instanceof handles nulls
                && participants.equals(((ParticipantList) other).participants)); // state check
    }

    @Override
    public int hashCode() {
        return participants.hashCode();
    }

}
