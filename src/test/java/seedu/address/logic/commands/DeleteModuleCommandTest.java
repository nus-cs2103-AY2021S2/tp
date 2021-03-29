package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.COMPUTER_ORGANIZATION_MODULE;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.address.testutil.TypicalModules.SOFTWARE_ENGINEERING_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.Semester;

public class DeleteModuleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private String INVALID_MODULE_CODE = "abcd";

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module module = SOFTWARE_ENGINEERING_MODULE;
        Semester semesterToDelete = new Semester(1);
        model.addSemester(INDEX_FIRST_PLAN.getZeroBased(), semesterToDelete);
        model.addModule(INDEX_FIRST_PLAN.getZeroBased(),
                INDEX_FIRST_PLAN.getZeroBased(), module);
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PLAN,
                INDEX_FIRST_PLAN, module.getModuleCode());
        String successMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                INDEX_FIRST_PLAN.getOneBased(), INDEX_FIRST_PLAN.getOneBased());
        ModelManager exceptedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(deleteModuleCommand, model, successMessage, exceptedModel);
    }

    @Test
    public void execute_invalidModule_throwsCommandException() {
        Semester semesterToDelete = new Semester(1);
        model.addSemester(INDEX_FIRST_PLAN.getZeroBased(), semesterToDelete);
        //invalid module code
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PLAN,
                INDEX_FIRST_PLAN, INVALID_MODULE_CODE);
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
