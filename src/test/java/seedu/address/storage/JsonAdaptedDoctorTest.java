package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDoctor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.DR_WHO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;

public class JsonAdaptedDoctorTest {
    private static final String INVALID_UUID = "invalidUUID";
    private static final String INVALID_NAME = "Dr Wh@o";
    private static final String INVALID_TAG = "#fever";

    private static final String VALID_UUID = DR_WHO.getUuid().toString();
    private static final String VALID_NAME = DR_WHO.getName().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = DR_WHO.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(DR_WHO);
        assertEquals(DR_WHO, doctor.toModelType());
    }

    @Test
    public void toModelType_invalidUuid_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                INVALID_UUID, VALID_NAME, VALID_TAGS);
        String expectedMessage = JsonAdaptedDoctor.UUID_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullUuid_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
            null, VALID_NAME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_UUID, INVALID_NAME, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_UUID, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_UUID, VALID_NAME, invalidTags);
        assertThrows(IllegalValueException.class, doctor::toModelType);
    }

}
