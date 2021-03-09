package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.plan.Description;
import seedu.address.model.plan.Plan;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Plan objects.
 */
public class PersonBuilder {
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        description = new Description(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code planToCopy}.
     */
    public PersonBuilder(Plan planToCopy) {
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

    public Plan build() {
        return new Plan(description, tags);
    }

}
