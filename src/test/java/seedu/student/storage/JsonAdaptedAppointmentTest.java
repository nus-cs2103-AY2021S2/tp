package seedu.student.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.student.storage.JsonAdaptedAppointment.INVALID_DATETIME_FORMAT_MESSAGE;
import static seedu.student.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.student.testutil.Assert.assertThrows;
import static seedu.student.testutil.TypicalAppointments.ALICE_APPOINTMENT;

import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.student.commons.exceptions.IllegalValueException;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;

public class JsonAdaptedAppointmentTest {

    private static final String VALID_DATE = ALICE_APPOINTMENT.getDate().toString();
    private static final String VALID_MATRICULATION_NUMBER = ALICE_APPOINTMENT.getMatriculationNumber().toString();
    private static final String VALID_START_TIME = ALICE_APPOINTMENT.getStartTime().toString();

    private static final String INVALID_DATE = "2021-03-32";
    private static final String INVALID_MATRICULATION_NUMBER = "A00000T";
    private static final String INVALID_START_TIME = "14:03";
    private static final String INVALID_TIME_FORMAT = "219:30";

    @Test
    public void toModelType_validAppointment_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(ALICE_APPOINTMENT);
        assertEquals(ALICE_APPOINTMENT, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidMatriculationNumber_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_MATRICULATION_NUMBER,
                VALID_DATE, VALID_START_TIME);
        String expectedMessage = MatriculationNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullMatriculationNumber_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null,
                VALID_DATE, VALID_START_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MatriculationNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_MATRICULATION_NUMBER,
                null, VALID_START_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_MATRICULATION_NUMBER,
                INVALID_DATE, VALID_START_TIME);
        String expectedMessage = INVALID_DATETIME_FORMAT_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDateAndTimeFormat_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_MATRICULATION_NUMBER,
                INVALID_DATE, INVALID_TIME_FORMAT);
        String expectedMessage = INVALID_DATETIME_FORMAT_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_MATRICULATION_NUMBER, VALID_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "startTime");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidTimeFormat_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_MATRICULATION_NUMBER,
                VALID_DATE, INVALID_TIME_FORMAT);
        String expectedMessage = INVALID_DATETIME_FORMAT_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_MATRICULATION_NUMBER,
                VALID_DATE, INVALID_START_TIME);
        String expectedMessage = Appointment.MESSAGE_TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
