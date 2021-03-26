package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.plan.Plan;

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
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Plan plan : getTypicalPersons()) {
            ab.addPlan(plan);
        }
        return ab;
    }

    public static List<Plan> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(SOFTWARE_ENGINEERING, COMPUTER_NETWORKING));
    }
}
