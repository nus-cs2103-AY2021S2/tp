package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.MeetingClashException;
import seedu.address.model.meeting.exceptions.NoMeetingForPersonException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueMeetingList implements Iterable<Person> {

    private final TreeMap<Meeting, Person> meetingMap = new TreeMap<>();
    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean clash(Person toCheck) {
        requireNonNull(toCheck);
        return toCheck.getMeeting().map(meetingMap::containsKey).orElse(false);
    }

    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (clash(toAdd)) {
            throw new MeetingClashException();
        } else if (meetingMap.containsValue(toAdd)) {
            throw new DuplicatePersonException();
        }
        if (toAdd.getMeeting().isPresent()) {
            meetingMap.put(toAdd.getMeeting().get(), toAdd);
            internalList.setAll(meetingMap.values());
        }
    }

    public void setPerson(Person original, Person updated) {
        requireAllNonNull(original, updated);
        if (original.isSamePerson(updated)) {
            if (!original.getMeeting().equals(updated.getMeeting()) && clash(updated)) {
                throw new MeetingClashException();
            }
            if (original.getMeeting().isPresent() && updated.getMeeting().isPresent()) {
                meetingMap.remove(original.getMeeting().get());
                meetingMap.put(updated.getMeeting().get(), updated);
                internalList.setAll(meetingMap.values());
            }
        } else {
            assert original.getMeeting().equals(updated.getMeeting());
            if (updated.getMeeting().isPresent()) {
                meetingMap.remove(original.getMeeting().get());
                meetingMap.put(updated.getMeeting().get(), updated);
                internalList.setAll(meetingMap.values());
            } else {
                throw new NoMeetingForPersonException();
            }
        }
    }

    /**
     * Removes the equivalent person from the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (toRemove.getMeeting().isPresent()) {
            meetingMap.put(toRemove.getMeeting().get(), toRemove);
            internalList.setAll(meetingMap.values());
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
        persons.stream().forEach(this::add);
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
