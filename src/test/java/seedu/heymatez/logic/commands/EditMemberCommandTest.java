package seedu.heymatez.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showPersonAtName;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.EditMemberDescriptorBuilder;
import seedu.heymatez.testutil.PersonBuilder;
import seedu.heymatez.testutil.TaskBuilder;
import seedu.heymatez.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMemberCommand.
 */
public class EditMemberCommandTest {

    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();

        Person editedPerson = new PersonBuilder().build();
        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(editedPerson).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(parsedNameAlice, descriptor);

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HeyMatez(model.getHeyMatez()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        List<Person> personList = model.getFilteredPersonList();
        Person lastPerson = personList.get(personList.size() - 1);
        Name nameLastPerson = lastPerson.getName();

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(nameLastPerson, descriptor);

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HeyMatez(model.getHeyMatez()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();

        EditMemberCommand editMemberCommand = new EditMemberCommand(parsedNameAlice,
                new EditMemberCommand.EditMemberDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HeyMatez(model.getHeyMatez()), new UserPrefs());

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = TypicalPersons.ALICE;
        Name secondPerson = TypicalPersons.BENSON.getName();

        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(firstPerson).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(secondPerson, descriptor);

        assertCommandFailure(editMemberCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        Name parsedNameBenson = TypicalPersons.BENSON.getName();
        showPersonAtName(model, parsedNameAlice);

        Person personInList = null;

        // edit person in filtered list into a duplicate in address book
        for (Person person : model.getHeyMatez().getPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameBenson)) {
                personInList = person;
                break;
            }
        }

        EditMemberCommand editMemberCommand = new EditMemberCommand(parsedNameAlice,
                new EditMemberDescriptorBuilder(personInList).build());

        assertCommandFailure(editMemberCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        Name invalidName = new Name("John");
        EditMemberCommand.EditMemberDescriptor descriptor =
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(invalidName, descriptor);

        assertCommandFailure(editMemberCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    /**
     * Edit filtered list where the input name is invalid.
     */
    @Test
    public void execute_invalidPersonNameFilteredList_failure() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        Name invalidName = TypicalPersons.BENSON.getName();
        showPersonAtName(model, parsedNameAlice);

        EditMemberCommand editMemberCommand = new EditMemberCommand(invalidName,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editMemberCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void execute_editAssigneeName_filteredListSuccess() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        showPersonAtName(model, parsedNameAlice);

        Person personInFilteredList = null;

        Task dummyTaskWithAssigned = new TaskBuilder().withAssignees(parsedNameAlice.fullName).build();

        for (Person person : model.getFilteredPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameAlice)) {
                personInFilteredList = person;
                break;
            }
        }

        model.addTask(dummyTaskWithAssigned);

        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();

        EditMemberCommand editMemberCommand = new EditMemberCommand(parsedNameAlice,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HeyMatez(model.getHeyMatez()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.editAssignee(personInFilteredList, editedPerson);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        Name parsedNameBenson = TypicalPersons.BENSON.getName();

        final EditMemberCommand standardCommand = new EditMemberCommand(parsedNameAlice, DESC_AMY);

        // same values -> returns true
        EditMemberCommand.EditMemberDescriptor copyDescriptor = new EditMemberCommand.EditMemberDescriptor(DESC_AMY);
        EditMemberCommand commandWithSameValues = new EditMemberCommand(parsedNameAlice, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(parsedNameBenson, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(parsedNameAlice, DESC_BOB)));
    }

}
