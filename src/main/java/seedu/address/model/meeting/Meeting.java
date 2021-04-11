package seedu.address.model.meeting;

import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.Schedulable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


/**
 * Represents a meeting in MeetBuddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting implements Schedulable {

    public static final String MESSAGE_CONSTRAINTS =
            "The start date time of a meeting should be strictly earlier than the terminate date time.\nA meeting "
                    + "should be at least 15 minutes long.\nA meeting should be at most one week long.\nFor example:"
                    + "\nIf the meeting starts on 15 August 7:00 am, it should be not end later than 22 Aug 6:59am";


    // Identity fields
    private final MeetingName meetingName;
    private final DateTime start;
    private final DateTime terminate;

    // Data fields
    private final Priority priority;
    private final Description description;
    private final Set<Group> groups = new HashSet<>();
    private PersonMeetingConnection connection = null;

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

    public PersonMeetingConnection getConnection() {
        return this.connection;
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
     * Returns true if a given date time for the meeting is valid. Note the meeting must be at
     * least 15 mins long and at most 7 days long.
     */
    public static boolean isValidStartTerminate(DateTime start, DateTime terminate) {
        long minutesBetween = Duration.between(start.toLocalDateTime(), terminate.toLocalDateTime()).toMinutes();
        long daysBetween = Duration.between(start.toLocalDateTime(), terminate.toLocalDateTime()).toDays();
        return start.compareTo(terminate) < 0 && (minutesBetween >= 15) && (daysBetween < 7);
    }
    /**
     * Adds new groups from a set. Merge if new group appears.
     */
    public void addGroups(Set<Group> newGroup) {
        this.groups.addAll(newGroup);
    }
    /**
     * Deletes groups from a set.
     */
    public void deleteGroups(Set<Group> groupsToDelete) {
        this.groups.removeAll(groupsToDelete);
    }
    /**
     * Sets the person meeting connection so that the meeting can have access to the Person Meeting Connection object.
     * This method will only be invoked in @code{AddMeetingCommand}.
     * Note that Meeting Object has no permission to modify connection, this method is used for later read connection.
     */
    public void setPersonMeetingConnection(PersonMeetingConnection connection) {
        this.connection = connection;
    }
    /**
     * Returns an immutable person set.
     */
    public Set<Person> getConnectionToPerson() {
        // If the user didn't try to add connection, then returns an empty set.
        if (connection == null) {
            return new HashSet<Person>();
        } else {
            UniquePersonList persons = connection.getPersonsByMeeting(this);
            HashSet<Person> personsSet = new HashSet<>();
            for (Person person : persons) {
                personsSet.add(person);
            }
            return personsSet;
        }
    }

    //=============== Some useful predicates ==========

    public boolean containsName(MeetingName name) {
        return meetingName.toString().contains(name.toString());
    }

    public boolean containsDescription(Description desc) {
        return description.toString().contains(desc.toString());
    }

    public boolean containsPerson(Person person) {
        Set<Person> allPersons = getConnectionToPerson();
        return allPersons.contains(person);
    }

    public boolean containsGroup(Group group) {
        return groups.contains(group);
    }

    public boolean containsTime(DateTime time) {
        boolean afterOrAtStart = time.compareTo(start) >= 0;
        boolean beforeOrAtEnd = time.compareTo(terminate) <= 0;
        return afterOrAtStart && beforeOrAtEnd;
    }

    public boolean hasPriority(Priority prio) {
        return priority.equals(prio);
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

        Set<Person> personSet = getConnectionToPerson();
        if (!personSet.isEmpty()) {
            builder.append("; Person Related: ");
            for (Person person : personSet) {
                builder.append("[" + person.getName() + "]");
            }
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

    /**
     * Checks if a meeting is a schedulable object is in conflict with this meeting.
     * @param schedulable
     * @return
     */

    public boolean isConflict(Schedulable schedulable) {
        return !(this.getTerminateLocalDateTime().compareTo(schedulable.getStartLocalDateTime()) <= 0
                || this.getStartLocalDateTime().compareTo(schedulable.getTerminateLocalDateTime()) >= 0);
    }

    //==================interface methods =================================================

    public LocalDateTime getStartLocalDateTime() {
        return start.toLocalDateTime();
    }

    public LocalDateTime getTerminateLocalDateTime() {
        return terminate.toLocalDateTime();
    }


    @Override
    public String getNameString() {
        return meetingName.fullName;
    }

}
