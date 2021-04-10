package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_MEET_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentBookTest {

    private final AppointmentBook appointmentBook = new AppointmentBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), appointmentBook.getAppointmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAppointmentBook_replacesData() {
        AppointmentBook newData = getTypicalAppointmentBook();
        appointmentBook.resetData(newData);
        assertEquals(newData, appointmentBook);
    }

    @Test
    public void resetData_withDuplicateAppointments_throwsDuplicateAppointmentException() {
        // Two appointments with the same attribute fields
        Appointment editedAlex = new AppointmentBuilder(MEET_ALEX).withRemark(VALID_REMARK_MEET_BOB).build();
        List<Appointment> newAppointments = Arrays.asList(MEET_ALEX, editedAlex);
        AppointmentBookStub newData = new AppointmentBookStub(newAppointments);

        assertThrows(DuplicateAppointmentException.class, () -> appointmentBook.resetData(newData));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentBook.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInAppointmentBook_returnsFalse() {
        assertFalse(appointmentBook.hasAppointment(MEET_ALEX));
    }

    @Test
    public void hasAppointment_appointmentInAppointmentBook_returnsTrue() {
        appointmentBook.addAppointment(MEET_ALEX);
        assertTrue(appointmentBook.hasAppointment(MEET_ALEX));
    }

    @Test
    public void hasAppointment_appointmentWithSameAttributeFieldsInAppointmentBook_returnsTrue() {
        appointmentBook.addAppointment(MEET_ALEX);
        Appointment editedAlex = new AppointmentBuilder(MEET_ALEX).withRemark(VALID_REMARK_MEET_BOB).build();
        assertTrue(appointmentBook.hasAppointment(editedAlex));
    }

    @Test
    public void getAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> appointmentBook.getAppointmentList().remove(0));
    }

    /**
     * A stub ReadOnlyAppointmentBook whose appointment list can violate interface constraints.
     */
    private static class AppointmentBookStub implements ReadOnlyAppointmentBook {
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        AppointmentBookStub(Collection<Appointment> appointments) {
            this.appointments.setAll(appointments);
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }
    }

}
