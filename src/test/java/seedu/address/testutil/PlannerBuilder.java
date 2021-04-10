package seedu.address.testutil;

import seedu.address.model.Planner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Planner objects.
 * Example usage: <br>
 *     {@code Planner ab = new PlannerBuilder().withTask("John", "Doe").build();}
 */
public class PlannerBuilder {

    private Planner planner;

    public PlannerBuilder() {
        planner = new Planner();
    }

    public PlannerBuilder(Planner planner) {
        this.planner = planner;
    }

    /**
     * Adds a new {@code Task} to the {@code Planner} that we are building.
     */
    public PlannerBuilder withTask(Task task) {
        planner.addTask(task);
        return this;
    }

    /**
     * Adds a new {@code Tag} to the {@code Planner} that we are building.
     */
    public PlannerBuilder withTag(Tag tag) {
        planner.addTag(tag);
        return this;
    }

    public Planner build() {
        return planner;
    }
}
