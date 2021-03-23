package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.MeetingClashException;
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
     * Checks if the added Person's meeting has any clashes.
     */
    public boolean clash(Person toCheck) {
        requireNonNull(toCheck);
        return toCheck.getMeeting().map(meetingMap::containsKey).orElse(false);
    }

    /**
     * Adds a person's meeting to the list
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (clash(toAdd)) {
            throw new MeetingClashException();
        } else if (internalList.stream().anyMatch(person -> person.equals(toAdd))) {
            throw new DuplicatePersonException();
        }
        toAdd.getMeeting().map(meeting -> meetingMap.put(meeting, toAdd)).isEmpty();
        internalList.setAll(meetingMap.values());
    }

    /**
     * Replaces a person in the event of a person edit or meeting edit.
     */
    public void setPerson(Person original, Person updated) {
        requireAllNonNull(original, updated);
        if (original.isSamePerson(updated)) {
            if (!original.getMeeting().equals(updated.getMeeting()) && clash(updated)) {
                throw new MeetingClashException();
            }
            original.getMeeting().map(meeting -> meetingMap.remove(meeting)).isEmpty();
        } else {
            assert original.getMeeting().equals(updated.getMeeting());
        }
        updated.getMeeting().map(meeting -> meetingMap.put(meeting, updated)).isEmpty();
        internalList.setAll(meetingMap.values());
    }

    /**
     * Removes the equivalent person from the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (toRemove.getMeeting().isPresent()) {
            meetingMap.remove(toRemove.getMeeting().get(), toRemove);
        }
        internalList.setAll(meetingMap.values());
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
        persons.forEach(this::add);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
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
