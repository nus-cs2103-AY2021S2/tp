package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;

public class JsonAdaptedTutorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_NOTE = "benson note";
    private static final String VALID_IS_FAVOURITE = "false";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTutorSubject> VALID_TUTOR_SUBJECTS = BENSON.getSubjectList()
            .asUnmodifiableObservableList()
            .stream()
            .map(JsonAdaptedTutorSubject::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTutorDetails_returnsTutor() throws Exception {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(BENSON);
        assertEquals(BENSON, tutor.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(INVALID_NAME, VALID_GENDER, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(null, VALID_GENDER, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, null,
                        VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, VALID_PHONE,
                        null, VALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, VALID_PHONE,
                        VALID_EMAIL, INVALID_ADDRESS, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, VALID_PHONE,
                        VALID_EMAIL, null, VALID_NOTE, VALID_TAGS, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_GENDER, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, invalidTags, VALID_TUTOR_SUBJECTS, VALID_IS_FAVOURITE);
        assertThrows(IllegalValueException.class, tutor::toModelType);
    }

}
