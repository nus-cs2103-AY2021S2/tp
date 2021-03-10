package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

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
