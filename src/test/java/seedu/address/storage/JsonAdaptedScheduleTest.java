package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;

public class JsonAdaptedScheduleTest {
    private static final String INVALID_TITLE = "H@mework";
    private static final String INVALID_TIME_FROM = "2/5/2021 11:00 AM";
    private static final String INVALID_TIME_TO = "2021-5-5 20:00 PM";
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_TITLE = MATHS_HOMEWORK_SCHEDULE.getTitle().toString();
    private static final String VALID_TIME_FROM = MATHS_HOMEWORK_SCHEDULE.getTimeFrom().toString();
    private static final String VALID_TIME_TO = MATHS_HOMEWORK_SCHEDULE.getTimeTo().toString();
    private static final String VALID_DESCRIPTION = MATHS_HOMEWORK_SCHEDULE.getDescription().toString();

    @Test
    public void toModelType_validScheduleDetails_returnsSchedule() throws Exception {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(MATHS_HOMEWORK_SCHEDULE);
        assertEquals(MATHS_HOMEWORK_SCHEDULE, schedule.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(INVALID_TITLE, VALID_DESCRIPTION, VALID_TIME_FROM, VALID_TIME_TO);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(null, VALID_DESCRIPTION, VALID_TIME_FROM, VALID_TIME_TO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidTimeFrom_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_TITLE, INVALID_TIME_FROM, VALID_TIME_TO, VALID_DESCRIPTION);
        String expectedMessage = AppointmentDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullTimeFrom_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_TITLE, VALID_DESCRIPTION, null, VALID_TIME_TO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidTimeTo_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_TITLE, VALID_DESCRIPTION, VALID_TIME_FROM, INVALID_TIME_TO);
        String expectedMessage = AppointmentDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullTimeTo_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_TITLE, VALID_DESCRIPTION, VALID_TIME_FROM, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_TITLE, INVALID_DESCRIPTION, VALID_TIME_FROM, VALID_TIME_TO);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_TITLE, null, VALID_TIME_FROM, VALID_TIME_TO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }
}
