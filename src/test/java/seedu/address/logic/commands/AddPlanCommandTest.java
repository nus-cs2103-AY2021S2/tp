package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;
import seedu.address.model.util.History;
import seedu.address.testutil.PlanBuilder;

public class AddPlanCommandTest {

    @Test
    public void constructor_nullPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPlanCommand(null));
    }

    @Test
    public void execute_planAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPlanAdded modelStub = new ModelStubAcceptingPlanAdded();
        Plan validPlan = new PlanBuilder().build();

        CommandResult commandResult = new AddPlanCommand(validPlan).execute(modelStub);

        assertEquals(String.format(AddPlanCommand.MESSAGE_SUCCESS,
                modelStub.plansAdded.size() + validPlan.toString()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPlan), modelStub.plansAdded);
    }

    @Test
    public void execute_duplicatePlan_throwsCommandException() {
        Plan validPlan = new PlanBuilder().build();
        AddPlanCommand addPlanCommand = new AddPlanCommand(validPlan);
        ModelStub modelStub = new ModelStubWithPlan(validPlan);

        assertThrows(CommandException.class, AddPlanCommand.MESSAGE_DUPLICATE_PLAN, () ->
                addPlanCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Plan softwareEngineeringRoute = new PlanBuilder().withDescription("software engineering route").build();
        Plan computerNetworkingRoute = new PlanBuilder().withDescription("computer networking route").build();
        AddPlanCommand addSoftwareEngineeringPlanCommand = new AddPlanCommand(softwareEngineeringRoute);
        AddPlanCommand addComputerNetworkingPlanCommand = new AddPlanCommand(computerNetworkingRoute);

        // same object -> returns true
        assertTrue(addSoftwareEngineeringPlanCommand.equals(addSoftwareEngineeringPlanCommand));

        // same values -> returns true
        AddPlanCommand addSoftwareEngineeringPlanCommandCopy = new AddPlanCommand(softwareEngineeringRoute);
        assertTrue(addSoftwareEngineeringPlanCommand.equals(addSoftwareEngineeringPlanCommandCopy));

        // different types -> returns false
        assertFalse(addSoftwareEngineeringPlanCommand.equals(1));

        // null -> returns false
        assertFalse(addSoftwareEngineeringPlanCommand.equals(null));

        // different person -> returns false
        assertFalse(addSoftwareEngineeringPlanCommand.equals(addComputerNetworkingPlanCommand));
    }

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
        public void validate(Plan masterPlan, Semester currentSemester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSemester(int planNumber, Semester semester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSemester(Plan plan, Semester target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSemester(int planNumber, Semester semester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(int planNumber, int semNumber, Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(int planNumber, int semNumber, Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public History getHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Plan getMasterPlan() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Semester getCurrentSemester() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentSemester(Integer currentSemesterNumber) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMasterPlan(Plan plan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StringProperty getCurrentCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentCommand(String command) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Plan plan, Semester semester, Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isValidGrade(String grade) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPlan extends ModelStub {
        private final Plan plan;

        ModelStubWithPlan(Plan plan) {
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
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPlanAdded extends ModelStub {
        final ArrayList<Plan> plansAdded = new ArrayList<>();
        final StringProperty currentCommand = new SimpleStringProperty("");
        final AddressBook addressBook = new AddressBook();

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return plansAdded.stream().anyMatch(plan::equals);
        }

        @Override
        public void addPlan(Plan plan) {
            requireNonNull(plan);
            plansAdded.add(plan);
        }

        @Override
        public void setCurrentCommand(String command) {
            currentCommand.set(command);
        }

        @Override
        public ObservableList<Plan> getFilteredPlanList() {
            addressBook.setPersons(plansAdded);
            return new FilteredList<>(addressBook.getPersonList());
        }

        @Override
        public ReadOnlyAddressBook getPlans() {
            return new AddressBook();
        }
    }
}
