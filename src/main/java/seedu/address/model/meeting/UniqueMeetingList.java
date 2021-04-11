package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.meeting.exceptions.MeetingTimeClashException;

/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 * A meeting is considered unique by comparing using {@code Meeting#isSameMeeting(Meeting)}.
 * As such, adding and updating of meetings uses Meeting#isSameMeeting(Meeting) for equality
 * so as to ensure that the meeting being added or updated is
 * unique in terms of identity in the UniqueMeetingList. However, the removal of a meeting uses Meeting#equals(Object)
 * so as to ensure that the meeting with exactly the same fields will be removed.
 * Also, it enforces that the meetings cannot overlap with each other ( i.e there is no clashing meetings.)
 *
 * Supports a minimal set of list operations.
 * In addition supports getting meetings happening at a certain point in time. For example, any time t,
 * it gets the meeting whose interval [start, end) such that it contains the time t.
 * Furthermore it gets a list of meetings that conflict with a certain meeting.
 *
 * @see Meeting#isSameMeeting(Meeting)
 */
public class UniqueMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent meeting as the given argument.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMeeting);
    }

    /**
     * Checks if there is a clash in Meeting Times
     */
    public boolean clashes(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck :: isConflict);
    }

    public boolean clashesExceptOne(Meeting target, Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream()
                .filter(x -> !x.equals(target))
                .anyMatch(toCheck :: isConflict);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not already exist in the list.
     * There must be no clashes with current meeting.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        if (clashes(toAdd)) {
            throw new MeetingTimeClashException();
        }
        internalList.add(toAdd);
    }

    /**
     * Obtains the list of meetings that clashes,
     * if there is a clash in Meeting Times.
     */
    public List<Meeting> getClashes(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().filter(toCheck :: isConflict).collect(Collectors.toList());
    }

    /**
     * Gets the meeting happening at a particular point in time. Note that at the instance of time queried must lie
     * between start (inclusive) and end ( exclusive) times.
     */
    public Optional<Meeting> getMeetingAtInstant(LocalDateTime localDateTime) {
        requireNonNull(localDateTime);
        return internalList.stream()
                .filter(meeting -> meeting.containsTime(localDateTime))
                .findFirst();
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateMeetingException();
        }
        if (!target.isSameMeeting(editedMeeting) && clashes(editedMeeting)) {
            throw new MeetingTimeClashException();
        }

        internalList.set(index, editedMeeting);
    }

    public void updateMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateMeetingException();
        }
        if (!target.isSameMeeting(editedMeeting) && clashesExceptOne(target, editedMeeting)) {
            throw new MeetingTimeClashException();
        }

        internalList.set(index, editedMeeting);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.setAll(meetings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
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

    /**
     * Returns true if {@code meetings} contains only unique meetings.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).isSameMeeting(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns true if there are no meetings, false if otherwise .
     * @return
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }
}
