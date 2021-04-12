package seedu.address.logic.commands.doctor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DR_LEONARD;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DR_SHELDON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DR_SHELDON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TALL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.logic.commands.doctor.EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR;
import static seedu.address.logic.commands.doctor.EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.doctor.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.EditDoctorDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditDoctorCommandTest {

    private Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
            getTypicalAppointmentSchedule(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Doctor editedDoctor = new DoctorBuilder().build();
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(editedDoctor).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_IN_LIST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );

        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDoctor = Index.fromOneBased(model.getFilteredDoctorList().size());
        Doctor lastDoctor = model.getFilteredDoctorList().get(indexLastDoctor.getZeroBased());

        DoctorBuilder doctorInList = new DoctorBuilder(lastDoctor);
        Doctor editedDoctor = doctorInList.withName(VALID_NAME_DR_SHELDON).withTags(VALID_TAG_TALL).build();

        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_DR_SHELDON)
                .withTags(VALID_TAG_TALL).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(indexLastDoctor, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );

        expectedModel.setDoctor(lastDoctor, editedDoctor);

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_IN_LIST,
                new EditDoctorDescriptor());
        Doctor editedDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_IN_LIST);

        Doctor doctorInFilteredList = model.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(doctorInFilteredList).withName(VALID_NAME_DR_SHELDON).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_IN_LIST,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_DR_SHELDON).build());

        String expectedMessage = String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );

        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDoctorUnfilteredList_failure() {
        Doctor firstPerson = model.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(firstPerson).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_SECOND_IN_LIST, descriptor);

        assertCommandFailure(editDoctorCommand, model, MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_duplicateDotorFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_IN_LIST);

        // edit person in filtered list into a duplicate in address book
        Doctor doctorInList = model.getDoctorRecords().getPersonList().get(INDEX_SECOND_IN_LIST.getZeroBased());
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_IN_LIST,
                new EditDoctorDescriptorBuilder(doctorInList).build());

        assertCommandFailure(editDoctorCommand, model, MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_DR_SHELDON).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editDoctorCommand, model, MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_IN_LIST);
        Index outOfBoundIndex = INDEX_SECOND_IN_LIST;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDoctorRecords().getPersonList().size());

        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(outOfBoundIndex,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_DR_SHELDON).build());

        assertCommandFailure(editDoctorCommand, model, MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDoctorCommand standardCommand = new EditDoctorCommand(INDEX_FIRST_IN_LIST, DESC_DR_LEONARD);

        // same values -> returns true
        EditDoctorDescriptor copyDescriptor = new EditDoctorDescriptor(DESC_DR_LEONARD);
        EditDoctorCommand commandWithSameValues = new EditDoctorCommand(INDEX_FIRST_IN_LIST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearDoctorCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_SECOND_IN_LIST, DESC_DR_LEONARD)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_FIRST_IN_LIST, DESC_DR_SHELDON)));
    }

}
