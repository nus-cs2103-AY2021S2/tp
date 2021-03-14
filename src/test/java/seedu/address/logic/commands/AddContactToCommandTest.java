package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.AddContactToCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

public class AddContactToCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(MESSAGE_SUCCESS, personToAdd.getName(), projectToAddTo.getProjectName());

        ModelManager expectedModel = new ModelManager(
                getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs()
        );
        expectedModel.setProject(
                projectToAddTo,
                projectToAddTo.addParticipant(personToAdd)
        );

        assertCommandSuccess(addContactToCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_THIRD_PERSON, INDEX_FIRST_PERSON);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> addContactToCommand.execute(model)
        );
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_FIRST_PERSON, Index.fromOneBased(8));

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> addContactToCommand.execute(model)
        );
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddContactToCommand addContactToCommand = new AddContactToCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);

        model.setProject(
                projectToAddTo,
                projectToAddTo.addParticipant(personToAdd)
        );

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
        AddContactToCommand addOneToOneCommand = new AddContactToCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        AddContactToCommand addOneToTwoCommand = new AddContactToCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        // same object -> returns true
        assertEquals(addOneToOneCommand, addOneToOneCommand);

        // same values -> returns true
        AddContactToCommand addOneToOneCommandCopy = new AddContactToCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        assertEquals(addOneToOneCommandCopy, addOneToOneCommand);

        // different types -> returns false
        assertNotEquals(addOneToOneCommand, 1);

        // null -> returns false
        assertNotEquals(addOneToOneCommand, null);

        // different person -> returns false
        assertNotEquals(addOneToTwoCommand, addOneToOneCommand);
    }

}
