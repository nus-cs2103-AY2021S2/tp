package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.plan.*;
import seedu.address.model.plan.Plan;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Plan objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Phone phone;
    private Email email;
    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        description = new Description(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code planToCopy}.
     */
    public PersonBuilder(Plan planToCopy) {
        phone = planToCopy.getPhone();
        email = planToCopy.getEmail();
        description = planToCopy.getDescription();
        tags = new HashSet<>(planToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Plan} that we are building.
     */
    public PersonBuilder withName(String name) {
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Plan} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Plan} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.description = new Description(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Plan} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Plan} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Plan build() {
        return new Plan(phone, email, description, tags);
    }

}
