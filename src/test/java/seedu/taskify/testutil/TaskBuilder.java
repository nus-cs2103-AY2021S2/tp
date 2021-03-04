package seedu.taskify.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Address;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Description;
import seedu.taskify.model.task.Email;
import seedu.taskify.model.task.Name;
import seedu.taskify.model.task.Task;
import seedu.taskify.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DESCRIPTION = "2103t tutorial";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE = "2020-04-13 09:30";

    private Name name;
    private Description description;
    private Email email;
    private Address address;
    private Date date;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        date = new Date(DEFAULT_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        email = taskToCopy.getEmail();
        address = taskToCopy.getAddress();
        date = taskToCopy.getDate();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Task} that we are building.
     */
    public TaskBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Task} that we are building.
     */
    public TaskBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Task build() {
        return new Task(name, description, email, address, date, tags);
    }

}
