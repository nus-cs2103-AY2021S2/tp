package seedu.plan.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plan.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.plan.testutil.Assert.assertThrows;
import static seedu.plan.testutil.TypicalModules.COMPUTER_ORGANIZATION_MODULE;
import static seedu.plan.testutil.TypicalModules.SOFTWARE_ENGINEERING_MODULE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.plan.commons.core.GuiSettings;
import seedu.plan.commons.core.index.Index;
import seedu.plan.model.Model;
import seedu.plan.model.ModulePlanner;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.ReadOnlyUserPrefs;
import seedu.plan.model.plan.Module;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.plan.Semester;
import seedu.plan.model.util.History;
import seedu.plan.testutil.TypicalPlans;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null, null, "", ""));
    }

    @Test
    public void execute_moduleAcceptedByPlan_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStud = new ModelStubAcceptingModuleAdded();
        Module validModule = SOFTWARE_ENGINEERING_MODULE;

        CommandResult commandResult = new AddModuleCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                SOFTWARE_ENGINEERING_MODULE.getModuleCode()).execute(modelStud);

        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS,
                Index.fromOneBased(1).toString(), Index.fromOneBased(1).toString(),
                SOFTWARE_ENGINEERING_MODULE.getModuleCode()),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validModule), modelStud.moduleAdded);
    }

    @Test
    public void equals() {
        Module module1 = SOFTWARE_ENGINEERING_MODULE;
        Module module2 = COMPUTER_ORGANIZATION_MODULE;
        AddModuleCommand addModuleCommand1 = new AddModuleCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), SOFTWARE_ENGINEERING_MODULE.getModuleCode());
        AddModuleCommand addModuleCommand2 = new AddModuleCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), COMPUTER_ORGANIZATION_MODULE.getModuleCode());
        assertTrue(SOFTWARE_ENGINEERING_MODULE.equals(SOFTWARE_ENGINEERING_MODULE));
        assertFalse(SOFTWARE_ENGINEERING_MODULE.equals(COMPUTER_ORGANIZATION_MODULE));

        AddModuleCommand copy1 = new AddModuleCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), SOFTWARE_ENGINEERING_MODULE.getModuleCode());
        assertTrue(addModuleCommand1.equals(copy1));
        assertFalse(addModuleCommand2.equals(copy1));
        assertFalse(addModuleCommand2.equals(null));
        assertFalse(addModuleCommand1.equals(addModuleCommand2));
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
    private class ModelStubWithModule extends ModelStub {
        private final ModulePlanner modulePlanner = new ModulePlanner();
        private final Module module;

        ModelStubWithModule(Plan plan, Semester semester, Module module) {
            requireAllNonNull(plan, semester);
            semester.addModule(module);
            plan.addSemester(semester);
            modulePlanner.addPlan(plan);
            this.module = module;
        }

        @Override
        public boolean hasModule(int planNumber, int semNumber, Module module) {
            return this.module.equals(module);
        }
    }

    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> moduleAdded = new ArrayList<>();
        final ModulePlanner modulePlanner = TypicalPlans.getTypicalAddressBookWithPlanSemester();

        @Override
        public ReadOnlyModulePlanner getPlans() {
            return new ModulePlanner();
        }

        @Override
        public boolean hasModule(int planNumber, int semNumber, Module module) {
            requireAllNonNull(planNumber, semNumber, module);
            Plan plan = modulePlanner.getPersonList().get(planNumber);
            Semester semester = plan.getSemesterBySemNumber(semNumber);
            return semester.getModules().stream().anyMatch((currentModule) ->
                    currentModule.getModuleCode() == module.getModuleCode()
            );
        }

        @Override
        public void addModule(int planNumber, int semNumber, Module module) {
            requireAllNonNull(planNumber, semNumber, module);
            Plan plan = modulePlanner.getPersonList().get(planNumber);
            Semester semester = plan.getSemesterBySemNumber(semNumber);
            semester.addModule(module);
            modulePlanner.setPlan(plan, plan.addSemester(semester));
            moduleAdded.add(module);
        }
    }
}
