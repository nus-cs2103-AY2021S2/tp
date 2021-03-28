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
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;
import seedu.address.testutil.PersonBuilder;

public class AddContactToCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        Project validProject = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Contact validContact = new PersonBuilder().withName("Tom").build();

        CommandResult commandResult = new AddContactToCommand(INDEX_FIRST, validContact).execute(model);

        assertEquals(String.format(AddContactToCommand.MESSAGE_SUCCESS, validContact.getName(),
                validProject.getProjectName()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Contact contactToAdd = new PersonBuilder().build();
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_THIRD, contactToAdd);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> addContactToCommand.execute(model)
        );
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Contact contactToAdd = new PersonBuilder().build();
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_FIRST, contactToAdd);

        projectToAddTo.addParticipant(contactToAdd);

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
        Contact contactToAdd = new PersonBuilder().build();
        AddContactToCommand addOneToOneCommand = new AddContactToCommand(INDEX_FIRST, contactToAdd);
        AddContactToCommand addOneToTwoCommand = new AddContactToCommand(INDEX_SECOND, contactToAdd);

        // same object -> returns true
        assertEquals(addOneToOneCommand, addOneToOneCommand);

        // same values -> returns true
        AddContactToCommand addOneToOneCommandCopy = new AddContactToCommand(INDEX_FIRST, contactToAdd);
        assertEquals(addOneToOneCommandCopy, addOneToOneCommand);

        // different types -> returns false
        assertNotEquals(addOneToOneCommand, 1);

        // null -> returns false
        assertNotEquals(addOneToOneCommand, null);

        // different person -> returns false
        assertNotEquals(addOneToTwoCommand, addOneToOneCommand);
    }
}
