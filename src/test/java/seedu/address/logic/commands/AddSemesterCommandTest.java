package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
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
import seedu.address.testutil.TypicalPlans;

public class AddSemesterCommandTest {

    @Test
    public void constructor_nullSemester_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSemesterCommand(null, null));
    }

    @Test
    public void execute_semesterAcceptedByPlan_addSuccessful() throws Exception {
        ModelStubPlanAcceptingSemesterAdded modelStub = new ModelStubPlanAcceptingSemesterAdded();
        Semester validSemester = new Semester(1);

        CommandResult commandResult = new AddSemesterCommand(Index.fromOneBased(1), validSemester)
                .execute(modelStub);

        assertEquals(String.format(AddSemesterCommand.MESSAGE_SUCCESS,
                Index.fromOneBased(1).toString(), validSemester),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validSemester), modelStub.semestersAdded);
    }

    @Test
    public void execute_duplicateSemesterInPlan_throwsCommandException() {
        Semester semester = new Semester(1);
        Plan plan = new PlanBuilder().build();

        ModelStub modelStub = new ModelStubWithPlanAndSemester(plan, semester);
        AddSemesterCommand addsemesterCommand = new AddSemesterCommand(
                Index.fromOneBased(1), semester);

        assertThrows(CommandException.class, AddSemesterCommand.MESSAGE_DUPLICATE_SEMESTER, () ->
                addsemesterCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Semester semester1 = new Semester(1);
        Semester semester2 = new Semester(2);
        AddSemesterCommand addSemester1Command = new AddSemesterCommand(Index.fromOneBased(1), semester1);
        AddSemesterCommand addSemester2Command = new AddSemesterCommand(Index.fromOneBased(1), semester2);

        // same object -> returns true
        assertTrue(semester1.equals(semester1));

        // same values -> returns true
        AddSemesterCommand addSemester1CommandCopy = new AddSemesterCommand(Index.fromOneBased(1), semester1);
        assertTrue(addSemester1Command.equals(addSemester1CommandCopy));

        // different types -> returns false
        assertFalse(addSemester1Command.equals(1));

        // null -> returns false
        assertFalse(addSemester1Command.equals(null));

        // different person -> returns false
        assertFalse(addSemester1Command.equals(addSemester2Command));
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
    private class ModelStubWithPlanAndSemester extends ModelStub {
        private final AddressBook addressBook = new AddressBook();
        private final Semester semester;

        ModelStubWithPlanAndSemester(Plan plan, Semester semester) {
            requireNonNull(plan);
            addressBook.addPlan(plan.addSemester(semester));
            this.semester = semester;
        }

        @Override
        public ObservableList<Plan> getFilteredPlanList() {
            return new FilteredList<>(addressBook.getPersonList());
        }

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return addressBook.hasPlan(plan);
        }

        @Override
        public boolean hasSemester(int planNumber, Semester semester) {
            return this.semester.equals(semester);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubPlanAcceptingSemesterAdded extends ModelStub {
        final ArrayList<Semester> semestersAdded = new ArrayList<>();
        final AddressBook addressBook = TypicalPlans.getTypicalAddressBook();

        @Override
        public boolean hasSemester(int planNumber, Semester semester) {
            requireAllNonNull(planNumber, semester);
            Plan plan = addressBook.getPersonList().get(planNumber);
            return plan.getSemesters().stream().anyMatch((currentSemester) ->
                    currentSemester.getSemNumber() == semester.getSemNumber()
            );
        }

        @Override
        public void addSemester(int planNumber, Semester semester) {
            requireAllNonNull(planNumber, semester);
            Plan plan = addressBook.getPersonList().get(planNumber);
            addressBook.setPlan(plan, plan.addSemester(semester));
            semestersAdded.add(semester);
        }

        @Override
        public ObservableList<Plan> getFilteredPlanList() {
            return new FilteredList<>(addressBook.getPersonList());
        }

        @Override
        public ReadOnlyAddressBook getPlans() {
            return new AddressBook();
        }
    }
}
