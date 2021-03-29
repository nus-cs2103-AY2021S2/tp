package seedu.plan.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plan.model.ModulePlanner;
import seedu.plan.model.plan.Plan;

/**
 * A utility class containing a list of {@code Plan} objects to be used in tests.
 */
public class TypicalPlans {

    public static final Plan SOFTWARE_ENGINEERING = new PlanBuilder()
            .withDescription("software engineering route")
            .withTags("fun").build();

    public static final Plan COMPUTER_NETWORKING = new PlanBuilder()
            .withDescription("computer networking route")
            .withTags("interesting").build();

    private TypicalPlans() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ModulePlanner getTypicalModulePlanner() {
        ModulePlanner ab = new ModulePlanner();
        for (Plan plan : getTypicalPlans()) {
            ab.addPlan(plan);
        }
        return ab;
    }

    public static List<Plan> getTypicalPlans() {
        return new ArrayList<>(Arrays.asList(SOFTWARE_ENGINEERING, COMPUTER_NETWORKING));
    }
}
