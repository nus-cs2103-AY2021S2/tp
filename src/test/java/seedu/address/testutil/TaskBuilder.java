package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleName;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Task;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_REMARK = "";

    private ModuleName moduleName;
    private Phone phone;
    private Email email;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        moduleName = taskToCopy.getModuleName();
        phone = taskToCopy.getPhone();
        email = taskToCopy.getEmail();
        remark = taskToCopy.getRemark();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code ModuleName} of the {@code Person} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public TaskBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public TaskBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public TaskBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Task build() {
        return new Task(moduleName, phone, email, remark, tags);
    }

}
