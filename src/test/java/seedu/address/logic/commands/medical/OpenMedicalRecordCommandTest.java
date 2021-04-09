package seedu.address.logic.commands.medical;

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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.*;

public class OpenMedicalRecordCommandTest {
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
        OpenMedicalRecordCommand openMedicalRecordCommand = new OpenMedicalRecordCommand(outOfBoundIndex);

        assertCommandFailure(openMedicalRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToCreateRecord = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        OpenMedicalRecordCommand openMedicalRecordCommand = new OpenMedicalRecordCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(OpenMedicalRecordCommand.MESSAGE_SUCCESS,
                                                patientToCreateRecord.getName().fullName);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.selectPatient(patientToCreateRecord);
        assertCommandSuccess(openMedicalRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        OpenMedicalRecordCommand openMedicalRecordFirstCommand = new OpenMedicalRecordCommand(INDEX_FIRST_PERSON);
        OpenMedicalRecordCommand openMedicalRecordSecondCommand = new OpenMedicalRecordCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(openMedicalRecordFirstCommand.equals(openMedicalRecordFirstCommand));

        // same values -> returns true
        OpenMedicalRecordCommand openMedicalRecordFirstCommandCopy = new OpenMedicalRecordCommand(INDEX_FIRST_PERSON);
        assertTrue(openMedicalRecordFirstCommand.equals(openMedicalRecordFirstCommandCopy));

        // different types -> returns false
        assertFalse(openMedicalRecordFirstCommand.equals(1));

        // null -> returns false
        assertFalse(openMedicalRecordFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(openMedicalRecordFirstCommand.equals(openMedicalRecordSecondCommand));
    }
}
