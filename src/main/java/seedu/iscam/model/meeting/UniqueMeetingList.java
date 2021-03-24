package seedu.iscam.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.iscam.model.meeting.exceptions.MeetingConflictException;
import seedu.iscam.model.meeting.exceptions.MeetingNotFoundException;

/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 * A meeting is considered unique by comparing using {@code Meeting#isInConflict(Meeting)}. As such, adding and
 * updating of meetings uses Meeting#isInConflict(Meeting) for equality so as to ensure that the meeting being added or
 * updated is unique in terms of identity in the UniqueMeetingList. However, the removal of a meeting uses
 * Meeting#equals(Object) so as to ensure that the meeting with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
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
        return internalList.stream().anyMatch(toCheck::isInConflict);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new MeetingNotFoundException();
        }
        internalList.add(toAdd);
        sortMeetings();
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

        if (!target.isInConflict(editedMeeting) && contains(editedMeeting)) {
            throw new MeetingConflictException();
        }

        internalList.set(index, editedMeeting);
        sortMeetings();
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
        sortMeetings();
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        sortMeetings();
    }

    // TODO: Complete this method and add headers
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);

    }

    private void sortMeetings() {
        FXCollections.sort(internalList, new MeetingComparator());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    // This is done
    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueMeetingList
                && internalList.equals(((UniqueMeetingList) other).internalList));
    }

    private class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.getDateTime().get().compareTo(m2.getDateTime().get());
        }
    }
}
