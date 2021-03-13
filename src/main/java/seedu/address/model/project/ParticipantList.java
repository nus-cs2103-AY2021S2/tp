package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.person.Person;

public class ParticipantList {

    private final List<Person> participants = new ArrayList<>();

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

    public List<Person> getParticipants() {
        return participants;
    }

    /**
     * Returns a sequential stream with this {@code ParticipantList} as its source.
     * @return a sequential Stream over the persons in this {@code ParticipantList}.
     */
    public Stream<Person> stream() {
        return participants.stream();
    }

    /**
     * Adds a person to this {@code ParticipantList} and return that new {@code ParticipantList}.
     *
     * @param person {@code Person} to add.
     */
    public ParticipantList addParticipant(Person person) {
        List<Person> participants = this.participants;
        participants.add(person);
        return new ParticipantList(participants);
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
