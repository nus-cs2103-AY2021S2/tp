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
public class PlanBuilder {
    public static final String DEFAULT_DESCRIPTION = "Networking";

    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PlanBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code planToCopy}.
     */
    public PlanBuilder(Plan planToCopy) {
        description = planToCopy.getDescription();
        tags = new HashSet<>(planToCopy.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Plan} that we are building.
     */
    public PlanBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Plan} that we are building.
     */
    public PlanBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Plan build() {
        return new Plan(description, tags);
    }

}
