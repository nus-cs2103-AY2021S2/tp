package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditPatientCommandTest {

    private Model model = new ModelManager(getTypicalAppointmentSchedule(), getTypicalPatientRecords(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Patient editedPatient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_IN_LIST, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(),
                new AddressBook<>(model.getPatientRecords()), new UserPrefs());

        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPatient = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPatient.getZeroBased());

        PatientBuilder patientInList = new PatientBuilder(lastPatient);
        Patient editedPatient = patientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(indexLastPatient, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(),
                new AddressBook<>(model.getPatientRecords()), new UserPrefs());

        expectedModel.setPatient(lastPatient, editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_IN_LIST,
                new EditPatientDescriptor());
        Patient editedPatient = model.getFilteredPatientList().get(INDEX_FIRST_IN_LIST.getZeroBased());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(),
                new AddressBook<>(model.getPatientRecords()), new UserPrefs());

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_IN_LIST);

        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withName(VALID_NAME_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_IN_LIST,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(),
                new AddressBook<>(model.getPatientRecords()), new UserPrefs());

        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePatientUnfilteredList_failure() {
        Patient firstPerson = model.getFilteredPatientList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(firstPerson).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_SECOND_IN_LIST, descriptor);

        assertCommandFailure(editPatientCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_IN_LIST);

        // edit person in filtered list into a duplicate in address book
        Patient patientInList = model.getPatientRecords().getPersonList().get(INDEX_SECOND_IN_LIST.getZeroBased());
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_IN_LIST,
                new EditPatientDescriptorBuilder(patientInList).build());

        assertCommandFailure(editPatientCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_IN_LIST);
        Index outOfBoundIndex = INDEX_SECOND_IN_LIST;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPatientRecords().getPersonList().size());

        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBoundIndex,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPatientCommand standardCommand = new EditPatientCommand(INDEX_FIRST_IN_LIST, DESC_AMY);

        // same values -> returns true
        EditPatientDescriptor copyDescriptor = new EditPatientDescriptor(DESC_AMY);
        EditPatientCommand commandWithSameValues = new EditPatientCommand(INDEX_FIRST_IN_LIST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPatientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_SECOND_IN_LIST, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_FIRST_IN_LIST, DESC_BOB)));
    }

}
