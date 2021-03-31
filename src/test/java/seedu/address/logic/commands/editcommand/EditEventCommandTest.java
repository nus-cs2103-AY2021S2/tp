package seedu.address.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;
import static seedu.address.testutil.typicalmodules.ModuleBuilder.DEFAULT_TITLE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.clearcommand.ClearModulesCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RemindMe;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.testutil.GeneralEventBuilder;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class EditEventCommandTest {
    private Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());

    @Test
    public void execute_descriptionFieldsSpecifiedUnfilteredList_success() {
        GeneralEvent editedEvent = new GeneralEventBuilder().build();

        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(), edit);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_SUCCESS, edit);

        Model expectedModel = new ModelManager(
                new RemindMe(model.getRemindMe()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedMod);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Title testTitle = new Title("CS Test");
        Module testModule = new Module(testTitle);
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        modelCopy.addModule(testModule);

        Module moduleInList = modelCopy.getFilteredModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE.getOneBased(),
                moduleInList.getTitle());

        assertCommandFailure(editModuleCommand, modelCopy, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        Title testTitle = new Title("CS Test");
        Module testModule = new Module(testTitle);
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        modelCopy.addModule(testModule);

        showModuleAtIndex(modelCopy, INDEX_FIRST_MODULE);

        Module moduleInList = modelCopy.getRemindMe().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE.getOneBased(),
                moduleInList.getTitle());

        assertCommandFailure(editModuleCommand, modelCopy, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex.getOneBased(),
                new Title("Title"));

        assertCommandFailure(editModuleCommand, model, editModuleCommand.MESSAGE_NO_MODULE);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_THIRD_MODULE;

        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex.getOneBased(),
                new Title("Title"));

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_NO_MODULE);
    }

    @Test
    public void equals() {
        Title edit = new Title("CS2101");
        final EditModuleCommand standardCommand = new EditModuleCommand(INDEX_FIRST_MODULE.getOneBased(), edit);

        // same values -> returns true
        Title editCopy = new Title("CS2101");
        EditModuleCommand commandWithSameValues = new EditModuleCommand(INDEX_FIRST_MODULE.getOneBased(), editCopy);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearModulesCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_MODULE.getOneBased(), edit)));

        // different descriptor -> returns false
        Title diffEdit = new Title("CS2102");
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_FIRST_MODULE.getOneBased(), diffEdit)));
    }
}
