package seedu.plan.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plan.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plan.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plan.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.plan.testutil.TypicalModules.COMPUTER_ORGANIZATION_MODULE;
import static seedu.plan.testutil.TypicalModules.SOFTWARE_ENGINEERING_MODULE;
import static seedu.plan.testutil.TypicalPlans.getTypicalModulePlanner;

import org.junit.jupiter.api.Test;

import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.model.Model;
import seedu.plan.model.ModelManager;
import seedu.plan.model.UserPrefs;
import seedu.plan.model.plan.Module;
import seedu.plan.model.plan.Semester;

public class DeleteModuleCommandTest {

    private Model model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
    private final String invalidModuleCode = "abcd";

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module module = SOFTWARE_ENGINEERING_MODULE;
        Semester semesterToDelete = new Semester(1);
        model.addSemester(INDEX_FIRST_PLAN.getZeroBased(), semesterToDelete);
        model.addModule(INDEX_FIRST_PLAN.getZeroBased(), 1, module);
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PLAN,
                INDEX_FIRST_PLAN, module.getModuleCode());
        String successMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                INDEX_FIRST_PLAN.getOneBased(), INDEX_FIRST_PLAN.getOneBased());
        ModelManager exceptedModel = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        assertCommandSuccess(deleteModuleCommand, model, successMessage, exceptedModel);
    }

    @Test
    public void execute_invalidModule_throwsCommandException() {
        Semester semesterToDelete = new Semester(1);
        model.addSemester(INDEX_FIRST_PLAN.getZeroBased(), semesterToDelete);
        //invalid module code
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PLAN,
                INDEX_FIRST_PLAN, invalidModuleCode);
        assertCommandFailure(deleteModuleCommand, model,
                Messages.MESSAGE_INVALID_MODULE_CODE_IN_SEMESTER);

        //invalid plan number
        Index outOfBoundPlanIndex = Index.fromOneBased(model.getFilteredPlanList().size() + 1);
        deleteModuleCommand = new DeleteModuleCommand(outOfBoundPlanIndex, INDEX_FIRST_PLAN,
                SOFTWARE_ENGINEERING_MODULE.getModuleCode());
        assertCommandFailure(deleteModuleCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);

        //invalid sem number
        Index outOfBoundSemIndex = Index.fromOneBased(99);
        deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PLAN, outOfBoundSemIndex,
                SOFTWARE_ENGINEERING_MODULE.getModuleCode());
        assertCommandFailure(deleteModuleCommand, model, Messages.MESSAGE_INVALID_SEM_NUMBER);
    }

    @Test
    public void equals() {
        DeleteModuleCommand command1 = new DeleteModuleCommand(INDEX_FIRST_PLAN, INDEX_FIRST_PLAN,
                SOFTWARE_ENGINEERING_MODULE.getModuleCode());
        DeleteModuleCommand command2 = new DeleteModuleCommand(INDEX_FIRST_PLAN, INDEX_FIRST_PLAN,
                COMPUTER_ORGANIZATION_MODULE.getModuleCode());
        assertTrue(command1.equals(command1));

        DeleteModuleCommand copy1 = new DeleteModuleCommand(INDEX_FIRST_PLAN, INDEX_FIRST_PLAN,
                SOFTWARE_ENGINEERING_MODULE.getModuleCode());
        assertTrue(command1.equals(copy1));

        assertFalse(command1.equals(command2));

        assertFalse(command1.equals(null));

        assertFalse(command1.equals("String"));
    }
}
