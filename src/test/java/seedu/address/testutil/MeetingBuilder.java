package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_NAME = "CS2103 Lectures";
    public static final String DEFAULT_START = "2021-03-12 14:00";
    public static final String DEFAULT_TERMINATE = "2021-03-12 16:00";
    public static final String DEFAULT_PRIORITY = "5";
    public static final String DEFAULT_DESCRIPTION = "Taught by Mr.Damith.";

    private MeetingName meetingName;
    private DateTime start;
    private DateTime terminate;
    private Priority priority;
    private Description description;
    private Set<Group> groups;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        meetingName = new MeetingName(DEFAULT_NAME);
        start = new DateTime(DEFAULT_START);
        terminate = new DateTime(DEFAULT_TERMINATE);
        priority = new Priority(DEFAULT_PRIORITY);
        description = new Description(DEFAULT_DESCRIPTION);
        groups = new HashSet<>();
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code MeetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        meetingName = meetingToCopy.getName();
        start = meetingToCopy.getStart();
        terminate = meetingToCopy.getTerminate();
        priority = meetingToCopy.getPriority();
        description = meetingToCopy.getDescription();
        groups = new HashSet<>(meetingToCopy.getGroups());
    }

    /**
     * Sets the {@code PersonName} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withName(String name) {
        this.meetingName = new MeetingName(name);
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withGroups(String ... groups) {
        this.groups = SampleDataUtil.getGroupSet(groups);
        return this;
    }

    /**
     * Sets the start date time of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withStart(String start) {
        this.start = new DateTime(start);
        return this;
    }

    /**
     * Sets the terminate date time of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTerminate(String terminate) {
        this.terminate = new DateTime(terminate);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Meeting build() {
        return new Meeting(meetingName, start, terminate, priority, description, groups);
    }

}
