package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
     * Returns {@code participants} as a {@code ObservableList<Person>}
     * @return An {@code ObservableList<Person>}
     */
    public ObservableList<Person> getAsObservableList() {
        return FXCollections.observableList(participants);
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
