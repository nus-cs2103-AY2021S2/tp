package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;

public class ParticipantList {

    private final ObservableList<Contact> participants = FXCollections.observableArrayList();

    /**
     * Constructs an empty {@code ParticipantList}.
     */
    public ParticipantList() {}

    /**
     * Constructs a {@code ParticipantList}.
     *
     * @param participants A list of {@code Person}.
     */
    public ParticipantList(List<Contact> participants) {
        requireNonNull(participants);

        this.participants.addAll(participants);
    }

    /**
     * Returns the participants as a {@code ObservableList<Person>}.
     *
     * @return the participants as a {@code ObservableList<Person>}.
     */
    public ObservableList<Contact> getParticipants() {
        return participants;
    }

    /**
     *  Returns the number of participants in the {@code ParticipantList}.
     *
     * @return the number of participants in the {@code ParticipantList}.
     */
    public int size() {
        return participants.size();
    }

    /**
     *  Returns the {@code Person} at the specified position in this {@code ParticipantList}.
     *
     * @return the {@code Person} at the specified position in this {@code ParticipantList}.
     */
    public Contact get(int index) {
        return participants.get(index);
    }

    /**
     * Deletes a {@code Person} from this {@code ParticipantList}.
     *
     * @param i Index of {@code Person} to be deleted.
     */
    public void delete(Integer i) {
        requireNonNull(i);
        participants.remove(i.intValue());
    }

    /**
     * Returns a copy of this {@code ParticipantList}
     *
     * @return A copy of this {@code ParticipantList}
     */
    public ParticipantList getCopy() {
        return new ParticipantList(getParticipants());
    }

    /**
     * Returns a sequential stream with this {@code ParticipantList} as its source.
     *
     * @return a sequential Stream over the persons in this {@code ParticipantList}.
     */
    public Stream<Contact> stream() {
        return participants.stream();
    }

    /**
     * Adds a person to this {@code ParticipantList}.
     *
     * @param contact {@code Person} to add.
     */
    public void addParticipant(Contact contact) {
        this.participants.add(contact);
    }

    /**
     * Returns true if this {@code ParticipantList} has the provided {@code Person}.
     *
     * @param contact the person to test.
     * @return whether the {@code Person} is in this {@code ParticipantList}.
     */
    public boolean contains(Contact contact) {
        return stream().anyMatch(contact::isSamePerson);
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
