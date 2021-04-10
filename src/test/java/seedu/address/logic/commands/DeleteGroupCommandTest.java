package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

public class DeleteGroupCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupCommand(null));
    }

    @Test
    public void execute_validGroupName_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Group group = new GroupBuilder().withName("Close Friends").build();
        model.addGroup(group);

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(group.getName());
        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, group.getName());
        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistingGroupName_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Group group = new GroupBuilder().withName("Close Friends").build();

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(group.getName());

        assertCommandFailure(deleteGroupCommand, model, Messages.MESSAGE_UNKNOWN_GROUP);
    }

    @Test
    public void equals() {
        Group group = new GroupBuilder().withName("Close Friends").build();
        Group group2 = new GroupBuilder().withName("Others").build();
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(group.getName());
        DeleteGroupCommand deleteGroup2Command = new DeleteGroupCommand(group2.getName());

        // same object -> returns true
        assertTrue(deleteGroupCommand.equals(deleteGroupCommand));

        // same values -> returns true
        DeleteGroupCommand deleteGroupCommandCopy = new DeleteGroupCommand(group.getName());
        assertTrue(deleteGroupCommand.equals(deleteGroupCommandCopy));

        // different types -> returns false
        assertFalse(deleteGroupCommand.equals(1));

        // null -> returns false
        assertFalse(deleteGroupCommand.equals(null));

        // different group -> returns false
        assertFalse(deleteGroupCommand.equals(deleteGroup2Command));
    }
}
