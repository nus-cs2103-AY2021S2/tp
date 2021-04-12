package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.MeetingClashException;
import seedu.address.model.meeting.exceptions.NoMeetingException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of meetings (via {@code Person}s) that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueMeetingList implements Iterable<Person> {

    private final TreeMap<Meeting, Person> meetingMap = new TreeMap<>();
    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the added Person's meeting has any clashes, and returns the clashed meeting.
     */
    public Optional<Person> clash(Person toCheck) {
        requireNonNull(toCheck);
        return toCheck.getMeeting()
                .map(meetingMap::get)
                .filter(person -> !person.equals(toCheck));
    }

    /**
     * Adds a person's meeting to the list
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (clash(toAdd).isPresent()) {
            throw new MeetingClashException();
        } else if (internalList.stream().anyMatch(person -> person.equals(toAdd))) {
            throw new DuplicatePersonException();
        }
        boolean sucessSet = toAdd.getMeeting()
                .map(meeting -> {
                    meetingMap.put(meeting, toAdd);
                    return meetingMap.get(meeting);
                })
                .map(person -> person.equals(toAdd))
                .orElse(true);
        if (sucessSet) {
            internalList.setAll(meetingMap.values());
        }
    }

    /**
     * Replaces a person in the event of a person edit or meeting edit.
     */
    public void setPerson(Person original, Person updated) {
        requireAllNonNull(original, updated);
        if (original.isSamePerson(updated)) {
            if (!original.getMeeting().equals(updated.getMeeting()) && clash(updated).isPresent()) {
                throw new MeetingClashException();
            }
            boolean sucessSet = original.getMeeting()
                    .map(meetingMap::remove)
                    .map(person -> person.equals(original))
                    .orElse(true);
            if (!sucessSet) {
                reconstructMap();
                return;
            }
        } else {
            assert original.getMeeting().equals(updated.getMeeting());
        }
        boolean sucessSet = updated.getMeeting()
                .map(meeting -> {
                    meetingMap.put(meeting, updated);
                    return meetingMap.get(meeting);
                })
                .map(person -> person.equals(updated))
                .orElse(true);
        if (sucessSet) {
            internalList.setAll(meetingMap.values());
        } else {
            reconstructMap();
        }
    }

    /**
     * Removes the equivalent person from the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        boolean sucessSet = toRemove.getMeeting()
                .map(meetingMap::remove)
                .map(person -> person.equals(toRemove))
                .orElse(true);
        if (sucessSet) {
            internalList.setAll(meetingMap.values());
        } else {
            reconstructMap();
        }
    }

    public void setPersons(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        meetingMap.clear();
        meetingMap.putAll(replacement.meetingMap);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        meetingMap.clear();
        internalList.setAll(meetingMap.values());
        persons.forEach(this::add);
    }

    public String getNotifications() {
        StringBuilder sb = new StringBuilder();
        String template = "You have a meeting with %s at %s\n";
        for (Person person : internalList) {
            person.getMeeting()
                    .map(meeting -> meeting.dateTime)
                    .filter(datetime -> datetime.toLocalDate().equals(LocalDate.now()))
                    .filter(datetime -> datetime.toLocalTime().compareTo(LocalTime.now()) > 0)
                    .map(datetime ->
                            sb.append(String.format(template, person.getName().fullName,
                                    datetime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")))));
        }
        return sb.toString();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Should never be used but just in case of error
     */
    public void reconstructMap() {
        meetingMap.clear();
        for (Person person : internalList) {
            meetingMap.put(person.getMeeting().get(), person);
        }
        throw new NoMeetingException();
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMeetingList // instanceof handles nulls
                && internalList.equals(((UniqueMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
