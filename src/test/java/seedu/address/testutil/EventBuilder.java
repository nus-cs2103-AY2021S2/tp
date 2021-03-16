package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Event;
import seedu.address.model.event.Time;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class EventBuilder {
    public static final String DEFAULT_NAME = "CS2103 Lecture";
    public static final String DEFAULT_START_DATE = "2021-01-01";
    public static final String DEFAULT_START_TIME = "00:00";
    public static final String DEFAULT_END_DATE = "2021-01-10";
    public static final String DEFAULT_END_TIME = "23:59";

    private Name name;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private Set<Tag> tags;
    private Set<Category> categories;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        startDate = new Date(DEFAULT_START_DATE);
        startTime = new Time(DEFAULT_START_TIME);
        endDate = new Date(DEFAULT_END_DATE);
        endTime = new Time(DEFAULT_END_TIME);
        tags = new HashSet<>();
        categories = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        startDate = eventToCopy.getStartDate();
        startTime = eventToCopy.getStartTime();
        endDate = eventToCopy.getEndDate();
        endTime = eventToCopy.getEndTime();
        tags = eventToCopy.getTags();
        categories = eventToCopy.getCategories();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code Event} that we are building.
     */
    public EventBuilder withStartDate(String date) {
        this.startDate = new Date(date);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartTime(String time) {
        this.startTime = new Time(time);
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code Event} that we are building.
     */
    public EventBuilder withEndDate(String date) {
        this.endDate = new Date(date);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndTime(String time) {
        this.endTime = new Time(time);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and set it to the {@code Task} that we are building.
     */
    public EventBuilder withCategories(String ... tags) {
        this.categories = SampleDataUtil.getCategorySet(tags);
        return this;
    }

    public Event build() {
        return new Event(name, startDate, startTime, endDate, endTime, categories, tags);
    }
}
