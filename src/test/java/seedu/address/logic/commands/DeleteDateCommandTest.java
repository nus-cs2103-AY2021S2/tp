package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDates.getTypicalDatesBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IMPORTANT_DATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IMPORTANT_DATE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalLessonBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.date.ImportantDate;

public class DeleteDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalDatesBook(),
        getTypicalLessonBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ImportantDate importantDateToDelete = model.getFilteredImportantDatesList()
            .get(INDEX_FIRST_IMPORTANT_DATE.getZeroBased());
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_IMPORTANT_DATE);

        String expectedMessage = String.format(DeleteDateCommand.MESSAGE_DELETE_DATE_SUCCESS, importantDateToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getDatesBook(),
            model.getLessonBook());
        expectedModel.deleteImportantDate(importantDateToDelete);

        assertCommandSuccess(deleteDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredImportantDatesList().size() + 1);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(outOfBoundIndex);

        assertCommandFailure(deleteDateCommand, model, Messages.MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteDateCommand deleteDateFirstCommand = new DeleteDateCommand(INDEX_FIRST_IMPORTANT_DATE);
        DeleteDateCommand deleteDateSecondCommand = new DeleteDateCommand(INDEX_SECOND_IMPORTANT_DATE);

        // same object -> returns true
        assertTrue(deleteDateFirstCommand.equals(deleteDateFirstCommand));

        // same values -> returns true
        DeleteDateCommand deleteDateFirstCommandCopy = new DeleteDateCommand(INDEX_FIRST_IMPORTANT_DATE);
        assertTrue(deleteDateFirstCommand.equals(deleteDateFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteDateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteDateFirstCommand.equals(null));

        // different important dates -> returns false
        assertFalse(deleteDateFirstCommand.equals(deleteDateSecondCommand));
    }
}
