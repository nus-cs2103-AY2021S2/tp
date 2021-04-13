package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plan.testutil.TypicalPlans.getTypicalModulePlanner;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.plan.commons.core.GuiSettings;
import seedu.plan.model.Model;
import seedu.plan.model.ModelManager;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.ReadOnlyUserPrefs;
import seedu.plan.model.UserPrefs;
import seedu.plan.model.plan.Module;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.plan.Semester;
import seedu.plan.model.util.History;
import seedu.plan.testutil.PlanBuilder;


public class ValidateCommandTest {
    private Model model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());

    @Test
    public void execute_specifiedGetMasterPlan_failure() {
        requireNonNull(model);
        Plan validPlan = new PlanBuilder().build();
        ModelStubWithPlan modelStub = new ModelStubWithPlan(validPlan);

        assertFalse(modelStub.getIsMasterPlan());
    }

    @Test
    public void execute_specifiedGetMasterPlan_success() {
        requireNonNull(model);
        Plan validPlan = new PlanBuilder().build();
        validPlan.setMasterPlan(true);
        ModelStubWithPlan modelStub = new ModelStubWithPlan(validPlan);

        assertTrue(modelStub.getIsMasterPlan());
    }

    @Test
    public void execute_specifiedGetCurrentSemester_failure() {
        requireNonNull(model);
        Plan validPlan = new PlanBuilder().build();
        ModelStubWithPlanSemesters modelStub = new ModelStubWithPlanSemesters(validPlan);

        assertFalse(modelStub.hasCurrentSemester());
    }

    @Test
    public void execute_specifiedGetCurrentSemester_success() {
        requireNonNull(model);
        Plan validPlan = new PlanBuilder().build();
        ModelStubWithPlanSemesters modelStub = new ModelStubWithPlanSemesters(validPlan);
        modelStub.setCurrentSemester(1);

        assertTrue(modelStub.hasCurrentSemester());
    }

    @Test
    public void equals() {
        final ValidateCommand standardCommand = new ValidateCommand();

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> return false
        assertFalse(standardCommand.equals(new ClearCommand()));
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
        public void setPlans(ReadOnlyModulePlanner newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModulePlanner getPlans() {
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
    private class ModelStubWithPlan extends ValidateCommandTest.ModelStub {
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

        public boolean getIsMasterPlan() {
            return this.plan.getIsMasterPlan();
        }
    }

    private class ModelStubWithPlanSemesters extends ModelStub {
        private final Plan plan;
        private Integer currentSemesterNumber;

        ModelStubWithPlanSemesters(Plan plan) {
            requireNonNull(plan);
            this.plan = plan;
        }

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return this.plan.equals(plan);
        }

        @Override
        public boolean hasSemester(int planNumber, Semester semester) {
            plan.addSemester(semester);
            return this.plan.equals(plan);
        }

        @Override
        public void setCurrentSemester(Integer currentSemesterNumber) {
            this.currentSemesterNumber = currentSemesterNumber;
        }

        private boolean hasCurrentSemester() {
            return currentSemesterNumber != null;
        }
    }
}
