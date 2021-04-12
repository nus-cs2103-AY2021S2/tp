package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedResidence.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalResidences.RESIDENCE_A;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;

public class JsonAdaptedResidenceTest {
    private static final String INVALID_NAME = "Rchel!";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_CLEAN_STATUS_TAG = "NO MEANING";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = RESIDENCE_A.getResidenceName().toString();
    private static final String VALID_ADDRESS = RESIDENCE_A.getResidenceAddress().toString();
    private static final String VALID_CLEAN_STATUS_TAG = RESIDENCE_A.getCleanStatusTag().toString();
    private static final List<JsonAdaptedBooking> VALID_BOOKINGS = new ArrayList<>();
    private static final List<JsonAdaptedTag> VALID_TAGS = RESIDENCE_A.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedResidence residence = new JsonAdaptedResidence(RESIDENCE_A);
        assertEquals(RESIDENCE_A, residence.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedResidence residence =
                new JsonAdaptedResidence(INVALID_NAME, VALID_ADDRESS, VALID_BOOKINGS,
                        VALID_CLEAN_STATUS_TAG, VALID_TAGS);
        String expectedMessage = ResidenceName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, residence::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedResidence residence = new JsonAdaptedResidence(null, VALID_ADDRESS, VALID_BOOKINGS,
                VALID_CLEAN_STATUS_TAG, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ResidenceName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, residence::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedResidence residence =
                new JsonAdaptedResidence(VALID_NAME, INVALID_ADDRESS, VALID_BOOKINGS,
                        VALID_CLEAN_STATUS_TAG, VALID_TAGS);
        String expectedMessage = ResidenceAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, residence::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedResidence residence = new JsonAdaptedResidence(VALID_NAME, null, VALID_BOOKINGS,
                VALID_CLEAN_STATUS_TAG, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ResidenceAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, residence::toModelType);
    }

    @Test
    public void toModelType_invalidCleanStatus_throwsIllegalValueException() {
        JsonAdaptedResidence residence =
                new JsonAdaptedResidence(VALID_NAME, VALID_ADDRESS, VALID_BOOKINGS,
                        INVALID_CLEAN_STATUS_TAG, VALID_TAGS);
        String expectedMessage = CleanStatusTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, residence::toModelType);
    }

    @Test
    public void toModelType_nullCleanStatus_throwsIllegalValueException() {
        JsonAdaptedResidence residence =
                new JsonAdaptedResidence(VALID_NAME, VALID_ADDRESS, VALID_BOOKINGS,
                        null, VALID_TAGS);
        String expectedMessage = null;
        assertThrows(NullPointerException.class, expectedMessage, residence::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedResidence residence =
                new JsonAdaptedResidence(VALID_NAME, VALID_ADDRESS, VALID_BOOKINGS,
                        VALID_CLEAN_STATUS_TAG, invalidTags);
        assertThrows(IllegalValueException.class, residence::toModelType);
    }

}
