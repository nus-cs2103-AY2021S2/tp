package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_MEET_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(MEET_ALEX));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(MEET_ALEX);
        assertTrue(uniqueAppointmentList.contains(MEET_ALEX));
    }

    @Test
    public void contains_appointmentWithSameAttributeFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(MEET_ALEX);
        Appointment editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withRemark(VALID_REMARK_MEET_BOB).build();
        assertTrue(uniqueAppointmentList.contains(editedMeetAlex));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicatePersonException() {
        uniqueAppointmentList.add(MEET_ALEX);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(MEET_ALEX));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, MEET_ALEX));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(MEET_ALEX, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () ->
                uniqueAppointmentList.setAppointment(MEET_ALEX, MEET_ALEX));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(MEET_ALEX);
        uniqueAppointmentList.setAppointment(MEET_ALEX, MEET_ALEX);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEET_ALEX);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameAttributes_success() {
        uniqueAppointmentList.add(MEET_ALEX);
        Appointment editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withRemark(VALID_REMARK_MEET_BOB).build();
        uniqueAppointmentList.setAppointment(MEET_ALEX, editedMeetAlex);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedMeetAlex);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentAttributes_success() {
        uniqueAppointmentList.add(MEET_ALEX);
        uniqueAppointmentList.setAppointment(MEET_ALEX, MEET_BOB);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEET_BOB);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueAttributes_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(MEET_ALEX);
        uniqueAppointmentList.add(MEET_BOB);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointment(MEET_ALEX, MEET_BOB));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(MEET_ALEX));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(MEET_ALEX);
        uniqueAppointmentList.remove(MEET_ALEX);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(MEET_ALEX);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEET_BOB);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(MEET_ALEX);
        List<Appointment> appointmentList = Collections.singletonList(MEET_BOB);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEET_BOB);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(MEET_ALEX, MEET_ALEX);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }
}
