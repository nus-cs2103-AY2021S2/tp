package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;
import seedu.address.testutil.PersonBuilder;

public class AddPlanCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPlanCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Plan validPlan = new PersonBuilder().build();

        CommandResult commandResult = new AddPlanCommand(validPlan).execute(modelStub);

        assertEquals(String.format(AddPlanCommand.MESSAGE_SUCCESS, validPlan), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPlan), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Plan validPlan = new PersonBuilder().build();
        AddPlanCommand addPlanCommand = new AddPlanCommand(validPlan);
        ModelStub modelStub = new ModelStubWithPerson(validPlan);

        assertThrows(CommandException.class, AddPlanCommand.MESSAGE_DUPLICATE_PLAN, () -> addPlanCommand.execute(modelStub));
    }

    /*
    @Test
    public void equals() {
        Plan alice = new PersonBuilder().withName("Alice").build();
        Plan bob = new PersonBuilder().withName("Bob").build();
        AddPlanCommand addAliceCommand = new AddPlanCommand(alice);
        AddPlanCommand addBobCommand = new AddPlanCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPlanCommand addAliceCommandCopy = new AddPlanCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different plan -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
     */

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPlansFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlansFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPlan(Plan plan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlans(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getPlans() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPlan(Plan plan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePlan(Plan target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlan(Plan target, Plan editedPlan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Plan> getFilteredPlanList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPlanList(Predicate<Plan> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSemester(int planNumber, Semester semester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSemester(int planNumber, Semester semester) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single plan.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Plan plan;

        ModelStubWithPerson(Plan plan) {
            requireNonNull(plan);
            this.plan = plan;
        }

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return this.plan.equals(plan);
        }
    }

    /**
     * A Model stub that always accept the plan being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Plan> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return personsAdded.stream().anyMatch(plan::equals);
        }

        @Override
        public void addPlan(Plan plan) {
            requireNonNull(plan);
            personsAdded.add(plan);
        }

        @Override
        public ReadOnlyAddressBook getPlans() {
            return new AddressBook();
        }
    }

}
