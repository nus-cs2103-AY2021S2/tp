package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPassenger.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassengers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;

public class JsonAdaptedPassengerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TRIPDAY = "funday";
    private static final String INVALID_DRIVER = "Huyser Wang; Phone: ph0ne";
    private static final String INVALID_TRIPTIME = "21032103";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_TRIPDAY = BENSON.getTripDay().toString();
    private static final String VALID_TRIPTIME = BENSON.getTripTime().toString();
    private static final String VALID_DRIVER = BENSON.getDriverStr();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPassengerDetails_returnsPassenger() throws Exception {
        JsonAdaptedPassenger passenger = new JsonAdaptedPassenger(BENSON);
        assertEquals(BENSON, passenger.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger =
                new JsonAdaptedPassenger(INVALID_NAME, VALID_PHONE, VALID_ADDRESS, VALID_TRIPDAY,
                        VALID_TRIPTIME, VALID_DRIVER, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger = new JsonAdaptedPassenger(null, VALID_PHONE, VALID_ADDRESS, VALID_TRIPDAY,
                VALID_TRIPTIME, VALID_DRIVER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger =
                new JsonAdaptedPassenger(VALID_NAME, INVALID_PHONE, VALID_ADDRESS, VALID_TRIPDAY,
                        VALID_TRIPTIME, VALID_DRIVER, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger = new JsonAdaptedPassenger(VALID_NAME, null, VALID_ADDRESS, VALID_TRIPDAY,
                VALID_TRIPTIME, VALID_DRIVER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger =
                new JsonAdaptedPassenger(VALID_NAME, VALID_PHONE, INVALID_ADDRESS, VALID_TRIPDAY,
                        VALID_TRIPTIME, VALID_DRIVER, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger = new JsonAdaptedPassenger(VALID_NAME, VALID_PHONE, null, VALID_TRIPDAY,
                VALID_TRIPTIME, VALID_DRIVER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }
    @Test
    public void toModelType_invalidDriver_throwsIllegalValueException() {
        JsonAdaptedPassenger passenger =
                new JsonAdaptedPassenger(VALID_NAME, VALID_PHONE, VALID_ADDRESS, VALID_TRIPDAY,
                        VALID_TRIPTIME, INVALID_DRIVER, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, passenger::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPassenger passenger =
                new JsonAdaptedPassenger(VALID_NAME, VALID_PHONE, VALID_ADDRESS, VALID_TRIPDAY,
                        VALID_TRIPTIME, VALID_DRIVER, invalidTags);
        assertThrows(IllegalValueException.class, passenger::toModelType);
    }

}
