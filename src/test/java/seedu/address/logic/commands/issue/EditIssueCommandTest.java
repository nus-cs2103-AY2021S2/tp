package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.EditIssueCommand.EditIssueDescriptor;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_CATEGORY_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_DESCRIPTION_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_ROOM_NUMBER_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_STATUS_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_TIMESTAMP_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.showIssueAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.issue.Issue;
import seedu.address.testutil.issue.EditIssueDescriptorBuilder;
import seedu.address.testutil.issue.IssueBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditIssueCommand.
 */
public class EditIssueCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredIssueList_success() {
        Issue issueToEdit = model.getFilteredIssueList().get(0);

        Issue editedIssue = new IssueBuilder(issueToEdit)
                .withRoomNumber("09-100") // not using 11-110 as it will result in duplicate
                .withDescription(VALID_ISSUE_DESCRIPTION_11_110)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_11_110)
                .withStatus(VALID_ISSUE_STATUS_11_110)
                .withCategory(VALID_ISSUE_CATEGORY_11_110)
                .build();

        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withRoomNumber("09-100")
                .withDescription(VALID_ISSUE_DESCRIPTION_11_110)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_11_110)
                .withStatus(VALID_ISSUE_STATUS_11_110)
                .withCategory(VALID_ISSUE_CATEGORY_11_110)
                .build();
        EditIssueCommand editIssueCommand = new EditIssueCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditIssueCommand.MESSAGE_EDIT_ISSUE_SUCCESS, editedIssue);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()),
                new UserPrefs());
        expectedModel.setIssue(issueToEdit, editedIssue);

        assertCommandSuccess(editIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredIssueList_success() {
        Index indexLastIssue = Index.fromOneBased(model.getFilteredIssueList().size());
        Issue lastIssue = model.getFilteredIssueList().get(indexLastIssue.getZeroBased());

        IssueBuilder issueInList = new IssueBuilder(lastIssue);
        Issue editedIssue = issueInList
                .withDescription(VALID_ISSUE_DESCRIPTION_11_110)
                .withCategory(VALID_ISSUE_CATEGORY_11_110)
                .build();

        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder(editedIssue)
                .withDescription(VALID_ISSUE_DESCRIPTION_11_110)
                .withCategory(VALID_ISSUE_CATEGORY_11_110)
                .build();

        EditIssueCommand editIssueCommand = new EditIssueCommand(indexLastIssue, descriptor);

        String expectedMessage = String.format(EditIssueCommand.MESSAGE_EDIT_ISSUE_SUCCESS, editedIssue);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setIssue(lastIssue, editedIssue);

        assertCommandSuccess(editIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredIssueList_success() {
        EditIssueCommand editIssueCommand = new EditIssueCommand(INDEX_FIRST, new EditIssueDescriptor());
        Issue editedIssue = model.getFilteredIssueList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditIssueCommand.MESSAGE_EDIT_ISSUE_SUCCESS, editedIssue);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showIssueAtIndex(model, INDEX_FIRST);

        Issue issueInFilteredList = model.getFilteredIssueList()
                .get(INDEX_FIRST.getZeroBased());
        Issue editedIssue = new IssueBuilder(issueInFilteredList)
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_11_110)
                .build();
        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_11_110)
                .build();
        EditIssueCommand editIssueCommand = new EditIssueCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditIssueCommand.MESSAGE_EDIT_ISSUE_SUCCESS, editedIssue);

        Model expectedModel = new ModelManagerWithExistingRoom(new AddressBook(model.getAddressBook()),
                new UserPrefs());
        expectedModel.setIssue(model.getFilteredIssueList().get(0), editedIssue);

        assertCommandSuccess(editIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateIssueFilteredList_failure() {
        showIssueAtIndex(model, INDEX_FIRST);

        // edit issue in filtered list into a duplicate in address book
        Issue issueInList = model.getAddressBook().getIssueList().get(INDEX_SECOND.getZeroBased());
        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder(issueInList).build();
        EditIssueCommand editIssueCommand = new EditIssueCommand(INDEX_FIRST, descriptor);

        assertCommandFailure(editIssueCommand, model, EditIssueCommand.MESSAGE_DUPLICATE_ISSUE);
    }

    @Test
    public void execute_invalidIssueIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIssueList().size() + 1);
        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_11_110)
                .build();

        EditIssueCommand editIssueCommand = new EditIssueCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editIssueCommand, model, String.format(
                Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, model.getFilteredIssueList().size()));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidIssueIndexFilteredList_failure() {
        showIssueAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getIssueList().size());

        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_11_110)
                .build();
        EditIssueCommand editIssueCommand = new EditIssueCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editIssueCommand, model, String.format(
                Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, model.getFilteredIssueList().size()));
    }

    @Test
    public void equals() {
        final EditIssueCommand standardCommand = new EditIssueCommand(INDEX_FIRST, DESC_11_110);

        // same values -> returns true
        EditIssueDescriptor copyDescriptor = new EditIssueDescriptor(DESC_11_110);
        EditIssueCommand commandWithSameValues = new EditIssueCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> return false
        assertFalse(standardCommand.equals(null));

        // different types -> return false;
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> return false
        assertFalse(standardCommand.equals(new EditIssueCommand(INDEX_SECOND, DESC_11_110)));

        // different index -> return false
        assertFalse(standardCommand.equals(new EditIssueCommand(INDEX_FIRST, DESC_10_100)));
    }

    /**
     * A Model stub that definitely has the room the issue is being edited to
     */
    private class ModelManagerWithExistingRoom extends ModelManager {

        public ModelManagerWithExistingRoom(AddressBook addressBook, UserPrefs userPrefs) {
            super(addressBook, userPrefs);
        }

        @Override
        public boolean hasRoom(seedu.address.model.room.RoomNumber roomNumber) {
            return true;
        }

    }

    /**
     * A Model stub that definitely DOES NOT has the room the issue is being edited to
     */
    private class ModelManagerWithNoExistingRoom extends ModelManager {

        public ModelManagerWithNoExistingRoom(AddressBook addressBook, UserPrefs userPrefs) {
            super(addressBook, userPrefs);
        }

        @Override
        public boolean hasRoom(seedu.address.model.room.RoomNumber roomNumber) {
            return false;
        }

    }

}
