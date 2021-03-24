package seedu.iscam.testutil;

import seedu.iscam.model.client.Location;
import seedu.iscam.model.client.Name;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.tag.Tag;
import seedu.iscam.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

public class MeetingBuilder {

    public static final String DEFAULT_CLIENT_NAME = "Amy Bee";
    public static final String DEFAULT_DATETIME = "10-12-2021 13:00";
    public static final String DEFAULT_LOCATION = "Starbucks, Jurong West";
    public static final String DEFAULT_DESCRIPTION = "Discussion on lifetime policy.";

    private Name name;
    private DateTime dateTime;
    private Location location;
    private Description description;
    private Set<Tag> tags = new HashSet<>();
    private boolean isDone = false;

    public MeetingBuilder() {
        name = new Name(DEFAULT_CLIENT_NAME);
        dateTime = new DateTime(DEFAULT_DATETIME);
        location = new Location(DEFAULT_LOCATION);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        isDone = false;
    }

    public MeetingBuilder(Meeting toCopy) {
        name = toCopy.getClientName();
        dateTime = toCopy.getDateTime();
        location = toCopy.getLocation();
        description = toCopy.getDescription();
        tags = toCopy.getTags();
        isDone = toCopy.getIsDone();
    }

    public MeetingBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public MeetingBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    public MeetingBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public MeetingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public MeetingBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public MeetingBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Meeting build() {
        return new Meeting(name, dateTime, location, description, tags, isDone);
    }
}
