package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.project.Project;
import seedu.address.testutil.GroupmateBuilder;

public class AddContactToCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        Project validProject = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Groupmate validGroupmate = new GroupmateBuilder().withName("Tom").build();

        CommandResult commandResult = new AddContactToCommand(INDEX_FIRST, validGroupmate).execute(model);

        assertEquals(String.format(AddContactToCommand.MESSAGE_SUCCESS, validGroupmate.getName(),
                validProject.getProjectName()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Groupmate groupmateToAdd = new GroupmateBuilder().build();
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_THIRD, groupmateToAdd);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> addContactToCommand.execute(model)
        );
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Groupmate groupmateToAdd = new GroupmateBuilder().build();
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_FIRST, groupmateToAdd);

        projectToAddTo.addGroupmate(groupmateToAdd);

        assertThrows(
                CommandException.class,
                String.format(
                        AddContactToCommand.MESSAGE_DUPLICATE_CONTACT,
                        projectToAddTo.getProjectName()
                ), () -> addContactToCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Groupmate groupmateToAdd = new GroupmateBuilder().build();
        AddContactToCommand addOneToOneCommand = new AddContactToCommand(INDEX_FIRST, groupmateToAdd);
        AddContactToCommand addOneToTwoCommand = new AddContactToCommand(INDEX_SECOND, groupmateToAdd);

        // same object -> returns true
        assertEquals(addOneToOneCommand, addOneToOneCommand);

        // same values -> returns true
        AddContactToCommand addOneToOneCommandCopy = new AddContactToCommand(INDEX_FIRST, groupmateToAdd);
        assertEquals(addOneToOneCommandCopy, addOneToOneCommand);

        // different types -> returns false
        assertNotEquals(addOneToOneCommand, 1);

        // null -> returns false
        assertNotEquals(addOneToOneCommand, null);

        // different contact -> returns false
        assertNotEquals(addOneToTwoCommand, addOneToOneCommand);
    }
}
