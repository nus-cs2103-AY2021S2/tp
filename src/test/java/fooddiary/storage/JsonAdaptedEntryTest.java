package fooddiary.storage;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.testutil.Assert;
import fooddiary.testutil.TypicalPersons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedEntryTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_RATING = "234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_REVIEW = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalPersons.BENSON.getName().toString();
    private static final String VALID_REVIEW = TypicalPersons.BENSON.getReview().toString();
    private static final String VALID_RATING = TypicalPersons.BENSON.getRating().toString();
    private static final String VALID_ADDRESS = TypicalPersons.BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalPersons.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedEntry person = new JsonAdaptedEntry(TypicalPersons.BENSON);
        Assertions.assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEntry person =
                new JsonAdaptedEntry(INVALID_NAME, VALID_RATING, VALID_REVIEW, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEntry person = new JsonAdaptedEntry(null, VALID_RATING, VALID_REVIEW, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedEntry person =
                new JsonAdaptedEntry(VALID_NAME, INVALID_RATING, VALID_REVIEW, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedEntry person = new JsonAdaptedEntry(VALID_NAME, null, VALID_REVIEW, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidReview_throwsIllegalValueException() {
        JsonAdaptedEntry person =
                new JsonAdaptedEntry(VALID_NAME, VALID_RATING, INVALID_REVIEW, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Review.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test

    public void toModelType_nullReview_throwsIllegalValueException() {
        JsonAdaptedEntry person = new JsonAdaptedEntry(VALID_NAME, VALID_RATING, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT, Review.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEntry person =
                new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_REVIEW, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEntry person = new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_REVIEW, null, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEntry person =
                new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_REVIEW, VALID_ADDRESS, invalidTags);
        Assert.assertThrows(IllegalValueException.class, person::toModelType);
    }

}
