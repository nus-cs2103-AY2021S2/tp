package seedu.address.model.meeting;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.scheduler.Schedulable;

/**
 * Represents a meeting in MeetBuddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting implements Schedulable {

    public static final String MESSAGE_CONSTRAINTS =
            "The start date time of a meeting should be strictly earlier than the terminate date time."
                    + "The meeting must start and end on the same date";


    // Identity fields
    private final MeetingName meetingName;
    private final DateTime start;
    private final DateTime terminate;

    // Data fields
    private final Priority priority;
    private final Description description;
    private final Set<Group> groups = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingName meetingName, DateTime start, DateTime terminate, Priority priority,
                   Description description, Set<Group> groups) {
        requireAllNonNull(meetingName, start, terminate, priority, description, groups);
        checkArgument(isValidStartTerminate(start, terminate), MESSAGE_CONSTRAINTS);
        this.meetingName = meetingName;
        this.start = start;
        this.terminate = terminate;
        this.priority = priority;
        this.description = description;
        this.groups.addAll(groups);
    }

    public MeetingName getName() {
        return meetingName;
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getTerminate() {
        return terminate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    /**
     * Returns true if both meetings have the same meetingName, start and terminate time. (Use identify fields only)
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getName().equals(getName())
                && otherMeeting.getStart().equals(getStart())
                && otherMeeting.getTerminate().equals(getTerminate());
    }



    /**
     * Returns true if a given date time for the meeting is valid.
     */
    public static boolean isValidStartTerminate(DateTime start, DateTime terminate) {
        boolean isSameDate = start.toLocalDate().equals(terminate.toLocalDate());
        return start.compareTo(terminate) < 0 && isSameDate;
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getName().equals(getName())
                && otherMeeting.getStart().equals(getStart())
                && otherMeeting.getTerminate().equals(getTerminate())
                && otherMeeting.getPriority().equals(getPriority())
                && otherMeeting.getDescription().equals(getDescription())
                && otherMeeting.getGroups().equals(getGroups());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(meetingName, start, terminate, priority, description, groups);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Start: ")
                .append(getStart())
                .append("; Terminate: ")
                .append(getTerminate())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Description: ")
                .append(getDescription());

        Set<Group> groups = getGroups();
        if (!groups.isEmpty()) {
            builder.append("; Groups: ");
            groups.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Checks if the meeting is happening at this instant of time.
     * @param localDateTime
     * @return
     */

    public boolean containsTime(LocalDateTime localDateTime) {
        LocalDateTime startLocalDateTime = start.toLocalDateTime();
        LocalDateTime endLocalDateTime = terminate.toLocalDateTime();
        return startLocalDateTime.compareTo(localDateTime) <= 0
                && endLocalDateTime.compareTo(localDateTime) > 0;
    }

    //==================interface methods =================================================

    public LocalDateTime getStartLocalDateTime() {
        return start.toLocalDateTime();
    }

    public LocalDateTime getTerminateLocalDateTime() {
        return terminate.toLocalDateTime();
    }

    @Override
    public boolean isConflict(Schedulable schedulable) {
        return !(this.getTerminateLocalDateTime().compareTo(schedulable.getStartLocalDateTime()) <= 0
                || this.getStartLocalDateTime().compareTo(schedulable.getTerminateLocalDateTime()) >= 0);
    }

    @Override
    public String getNameString() {
        return meetingName.fullName;
    }

}
