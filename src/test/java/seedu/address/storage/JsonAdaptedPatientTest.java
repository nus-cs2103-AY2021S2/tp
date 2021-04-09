package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    public static final String INVALID_DOB = "30022020";
    public static final String INVALID_GENDER = "X";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    public static final String INVALID_BLOODTYPE = "O";
    private static final String INVALID_HEIGHT = "169mm";
    private static final String INVALID_WEIGHT = "200g";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    public static final String VALID_DOB = BENSON.getDateOfBirth().value;
    public static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    public static final String VALID_BLOODTYPE = BENSON.getBloodType().toString();
    private static final String VALID_HEIGHT = BENSON.getHeight().toString();
    private static final String VALID_WEIGHT = BENSON.getWeight().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_DOB, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, INVALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, INVALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT,
                VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, null,
                VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                null, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, INVALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, null,
                VALID_EMAIL, VALID_ADDRESS, null, VALID_HEIGHT, VALID_WEIGHT, false,
                VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE,
                INVALID_HEIGHT, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_BLOODTYPE, null, VALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, INVALID_WEIGHT, false, VALID_TAGS, null, null);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, null, false, VALID_TAGS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_DOB, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BLOODTYPE, VALID_HEIGHT, VALID_WEIGHT, false, invalidTags, null, null);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
