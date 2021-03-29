package seedu.plan.testutil;

import seedu.plan.model.ModulePlanner;
import seedu.plan.model.plan.Plan;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ModulePlannerBuilder {

    private ModulePlanner modulePlanner;

    public ModulePlannerBuilder() {
        modulePlanner = new ModulePlanner();
    }

    public ModulePlannerBuilder(ModulePlanner modulePlanner) {
        this.modulePlanner = modulePlanner;
    }

    /**
     * Adds a new {@code Plan} to the {@code AddressBook} that we are building.
     */
    public ModulePlannerBuilder withPerson(Plan plan) {
        modulePlanner.addPlan(plan);
        return this;
    }

    public ModulePlanner build() {
        return modulePlanner;
    }
}
