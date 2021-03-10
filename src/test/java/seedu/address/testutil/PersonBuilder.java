package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleName;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Task;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private ModuleName moduleName;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
<<<<<<< HEAD
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
=======
    public PersonBuilder(Task taskToCopy) {
        moduleName = taskToCopy.getModuleName();
        phone = taskToCopy.getPhone();
        email = taskToCopy.getEmail();
        address = taskToCopy.getAddress();
        tags = new HashSet<>(taskToCopy.getTags());
>>>>>>> 0b8c8feb9aad11ae1aba8284be389d81151a3bc4
    }

    /**
     * Sets the {@code ModuleName} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

<<<<<<< HEAD
    public Person build() {
        return new Person(name, phone, email, tags);
=======
    public Task build() {
        return new Task(moduleName, phone, email, address, tags);
>>>>>>> 0b8c8feb9aad11ae1aba8284be389d81151a3bc4
    }

}
