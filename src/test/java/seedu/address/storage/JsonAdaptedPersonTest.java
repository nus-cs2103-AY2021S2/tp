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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SCHOOL = "R@chel School";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GUARDIAN_NAME = "&lice";
    private static final String INVALID_GUARDIAN_PHONE = "+432156";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_LESSON = "Monday";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_SCHOOL = BENSON.getSchool().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_GUARDIAN_NAME = BENSON.getGuardianName().toString();
    private static final String VALID_GUARDIAN_PHONE = BENSON.getGuardianPhone().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLesson> VALID_LESSON = BENSON.getLessons().stream()
            .map(JsonAdaptedLesson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSchool_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_SCHOOL, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = School.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSchool_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, School.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, null, VALID_ADDRESS,
                VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL,
                null, VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGuardianName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGuardianName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGuardianPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, INVALID_GUARDIAN_PHONE, VALID_TAGS, VALID_LESSON);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGuardianPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_GUARDIAN_NAME, null, VALID_TAGS, VALID_LESSON);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, invalidTags, VALID_LESSON);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidLesson_throwsIllegalValueException() {
        List<JsonAdaptedLesson> invalidLesson = new ArrayList<>(VALID_LESSON);
        invalidLesson.add(new JsonAdaptedLesson(INVALID_LESSON));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_SCHOOL, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GUARDIAN_NAME, VALID_GUARDIAN_PHONE, VALID_TAGS, invalidLesson);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
