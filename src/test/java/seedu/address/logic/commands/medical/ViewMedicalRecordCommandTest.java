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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.medical.Section;
import seedu.address.model.person.Patient;
import seedu.address.testutil.PersonBuilder;

class ViewMedicalRecordCommandTest {

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
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_invalidMedicalRecordIndexUnfilteredList_failure() {
        Patient patientToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 18, 00);
        Section section = new Section("test");
        List<Section> sections = new ArrayList<>();
        sections.add(section);
        patientToView.addMedicalRecord(new MedicalRecord(dateTime, sections));

        model.selectPatient(patientToView);
        Index outOfBoundIndex = Index.fromOneBased(patientToView.getRecords().size() + 1);
        ViewMedicalRecordCommand viewMedicalRecordCommand = new ViewMedicalRecordCommand(outOfBoundIndex);

        assertCommandFailure(viewMedicalRecordCommand, model, Messages.MESSAGE_INVALID_MEDICAL_RECORD_INDEX);
    }
    @Test
    public void execute_validMedicalRecordIndexUnFilteredList_success() {
        Patient patientToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 18, 00);
        Section section = new Section("test");
        List<Section> sections = new ArrayList<>();
        sections.add(section);
        patientToView.addMedicalRecord(new MedicalRecord(dateTime, sections));

        model.selectPatient(patientToView);
        ViewMedicalRecordCommand viewMedicalRecordCommand = new ViewMedicalRecordCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ViewMedicalRecordCommand.MESSAGE_SUCCESS,
                                    INDEX_FIRST_PERSON.getOneBased(), patientToView.getName(),
                                    patientToView.getRecords().get(INDEX_FIRST_PERSON.getZeroBased()).getDateDisplay());
        assertCommandSuccess(viewMedicalRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewingMrecWithoutSelectingPatient_throwsCommandException() {
        ViewMedicalRecordCommand viewMedicalRecordCommand = new ViewMedicalRecordCommand(INDEX_FIRST_PERSON);
        assertCommandFailure(viewMedicalRecordCommand, model, Messages.MESSAGE_NOT_VIEWING_PATIENT);
    }

    @Test
    public void execute_viewingPatientWithNoRecords_throwsCommandException() {
        ViewMedicalRecordCommand viewMedicalRecordCommand = new ViewMedicalRecordCommand(INDEX_FIRST_PERSON);
        Patient patientToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.selectPatient(patientToView);
        assertCommandFailure(viewMedicalRecordCommand, model,
                                String.format(Messages.MESSAGE_NO_MEDICAL_RECORD_FOUND,
                                    patientToView.getName().fullName, patientToView.getName().fullName));
    }

    @Test
    public void equals() {
        ViewMedicalRecordCommand viewMrecFirstCommand = new ViewMedicalRecordCommand(INDEX_FIRST_PERSON);
        ViewMedicalRecordCommand viewMrecSecondCommand = new ViewMedicalRecordCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewMrecFirstCommand.equals(viewMrecFirstCommand));

        // same values -> returns true
        ViewMedicalRecordCommand viewMrecFirstCommandCopy = new ViewMedicalRecordCommand(INDEX_FIRST_PERSON);
        assertTrue(viewMrecFirstCommand.equals(viewMrecFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewMrecFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewMrecFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewMrecFirstCommand.equals(viewMrecSecondCommand));
    }
}
