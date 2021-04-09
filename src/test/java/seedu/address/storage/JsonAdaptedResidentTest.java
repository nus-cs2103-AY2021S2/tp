package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedResident.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.resident.TypicalResidents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Room;
import seedu.address.model.resident.Year;

public class JsonAdaptedResidentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_YEAR = "6";
    private static final String INVALID_ROOM = "03145";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_YEAR = BENSON.getYear().toString();
    private static final String VALID_ROOM = BENSON.getRoom().toString();

    @Test
    public void toModelType_validResidentDetails_returnsResident() throws Exception {
        JsonAdaptedResident resident = new JsonAdaptedResident(BENSON);
        assertEquals(BENSON, resident.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR, VALID_ROOM);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedResident resident = new JsonAdaptedResident(null, VALID_PHONE,
                VALID_EMAIL, VALID_YEAR, VALID_ROOM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_YEAR, VALID_ROOM);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedResident resident = new JsonAdaptedResident(VALID_NAME, null,
                VALID_EMAIL, VALID_YEAR, VALID_ROOM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_YEAR, VALID_ROOM);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedResident resident = new JsonAdaptedResident(VALID_NAME, VALID_PHONE,
                null, VALID_YEAR, VALID_ROOM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_YEAR, VALID_ROOM);
        String expectedMessage = Year.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullYear_throwsIllegalValueException() {
        JsonAdaptedResident resident = new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_ROOM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidRoom_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR, INVALID_ROOM);
        String expectedMessage = Room.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }
}
