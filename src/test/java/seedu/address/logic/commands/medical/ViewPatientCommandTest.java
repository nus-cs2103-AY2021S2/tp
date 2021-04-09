package seedu.address.logic.commands.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

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


public class ViewPatientCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        Patient newAlice = new PersonBuilder(ALICE).build();
        Patient newBenson = new PersonBuilder(BENSON).build();
        Patient newCarl = new PersonBuilder(CARL).build();
        Patient newDaniel = new PersonBuilder(DANIEL).build();
        Patient newElle = new PersonBuilder(ELLE).build();
        Patient newFiona = new PersonBuilder(FIONA).build();
        Patient newGeorge = new PersonBuilder(GEORGE).build();
        Patient newHoon = new PersonBuilder(HOON).build();
        Patient newIda = new PersonBuilder(IDA).build();
        model.addPerson(newAlice);
        model.addPerson(newBenson);
        model.addPerson(newCarl);
        model.addPerson(newDaniel);
        model.addPerson(newElle);
        model.addPerson(newFiona);
        model.addPerson(newGeorge);
        model.addPerson(newHoon);
        model.addPerson(newIda);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(outOfBoundIndex);

        assertCommandFailure(viewPatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewPatientCommand viewPatientCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ViewPatientCommand.MESSAGE_SUCCESS, patientToView.getName().fullName);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.selectPatient(patientToView);
        assertCommandSuccess(viewPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ViewPatientCommand viewPatientFirstCommand = new ViewPatientCommand(INDEX_FIRST_PERSON);
        ViewPatientCommand viewPatientSecondCommand = new ViewPatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewPatientFirstCommand.equals(viewPatientFirstCommand));

        // same values -> returns true
        ViewPatientCommand viewPatientFirstCommandCopy = new ViewPatientCommand(INDEX_FIRST_PERSON);
        assertTrue(viewPatientFirstCommand.equals(viewPatientFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewPatientFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewPatientFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewPatientFirstCommand.equals(viewPatientSecondCommand));
    }
}
