package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.FIONA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.testutil.PersonBuilder;

class UnarchiveCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        Patient newAlice = new PersonBuilder(ALICE).build();
        Patient newBenson = new PersonBuilder(BENSON).build();
        Patient newFiona = new PersonBuilder(FIONA).build();
        model.addPerson(newAlice);
        model.addPerson(newBenson);
        model.addPerson(newFiona);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        patientInFilteredList.setArchived(true);
        Patient copy = new PersonBuilder(patientInFilteredList).build();
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_PERSON_SUCCESS,
                                                patientInFilteredList.getName().fullName);

        expectedModel.unarchivePerson(copy);
        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToUnarchive = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        patientToUnarchive.setArchived(true);
        Patient copy = new PersonBuilder(patientToUnarchive).build();

        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_PERSON_SUCCESS,
                                                    patientToUnarchive.getName().fullName);

        expectedModel.unarchivePerson(copy);

        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Patient newAlice = new PersonBuilder(ALICE).build();
        model.archivePerson(newAlice);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnarchiveCommand archiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_unarchiveUnarchivedPerson_throwsCommandException() {
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);
        assertCommandFailure(unarchiveCommand, model, UnarchiveCommand.MESSAGE_PERSON_NOT_ARCHIVED);
    }


    @Test
    public void equals() {
        UnarchiveCommand unarchiveFirstCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);
        UnarchiveCommand unarchiveSecondCommand = new UnarchiveCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommand));

        // same values -> returns true
        UnarchiveCommand unarchiveFirstCommandCopy = new UnarchiveCommand(INDEX_FIRST_PERSON);
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(unarchiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unarchiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unarchiveFirstCommand.equals(unarchiveSecondCommand));
    }

}
