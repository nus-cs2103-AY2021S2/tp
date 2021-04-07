package fooddiary.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;
import fooddiary.testutil.Assert;
import fooddiary.testutil.TypicalEntries;

public class JsonAdaptedEntryTest {
    private static final String INVALID_NAME = "Rest@ur@nt B";
    private static final String INVALID_RATING = "234";
    private static final String INVALID_PRICE = "1234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_REVIEW = " ";
    private static final String INVALID_TAG_CATEGORY = "#Western";
    private static final String INVALID_TAG_SCHOOL = "the hive";

    private static final String VALID_NAME = TypicalEntries.ENTRY_B.getName().toString();
    private static final String VALID_RATING = TypicalEntries.ENTRY_B.getRating().toString();
    private static final String VALID_PRICE = TypicalEntries.ENTRY_B.getPrice().toString();
    private static final String VALID_ADDRESS = TypicalEntries.ENTRY_B.getAddress().toString();
    private static final List<JsonAdaptedReview> VALID_REVIEWS = TypicalEntries.ENTRY_B.getReviews().stream()
            .map(JsonAdaptedReview::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTagCategory> VALID_TAG_CATEGORY = TypicalEntries.ENTRY_B.getTagCategories()
            .stream()
            .map(JsonAdaptedTagCategory::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTagSchool> VALID_TAG_SCHOOL = TypicalEntries.ENTRY_B.getTagSchools()
            .stream()
            .map(JsonAdaptedTagSchool::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEntryDetails_returnsEntry() throws Exception {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(TypicalEntries.ENTRY_B);
        Assertions.assertEquals(TypicalEntries.ENTRY_B, entry.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(INVALID_NAME, VALID_RATING, VALID_PRICE, VALID_REVIEWS,
                        VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(null, VALID_RATING, VALID_PRICE,
                VALID_REVIEWS, VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = String.format(JsonAdaptedEntry
                .MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_NAME, INVALID_RATING, VALID_PRICE, VALID_REVIEWS,
                        VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(VALID_NAME, null, VALID_PRICE, VALID_REVIEWS,
                VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = String.format(JsonAdaptedEntry
                .MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_NAME, VALID_RATING, INVALID_PRICE, VALID_REVIEWS,
                        VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(VALID_NAME, VALID_RATING, null, VALID_REVIEWS,
                VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = String.format(JsonAdaptedEntry
                .MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidReview_throwsIllegalValueException() {
        List<JsonAdaptedReview> invalidReviews = new ArrayList<>(VALID_REVIEWS);
        invalidReviews.add(new JsonAdaptedReview(INVALID_REVIEW));
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_PRICE, invalidReviews,
                        VALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = Review.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }


    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_PRICE, VALID_REVIEWS,
                        INVALID_ADDRESS, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_PRICE, VALID_REVIEWS,
                null, VALID_TAG_CATEGORY, VALID_TAG_SCHOOL);
        String expectedMessage = String.format(JsonAdaptedEntry
                .MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelTyp_invalidTagCategory_throwsIllegalValueException() {
        List<JsonAdaptedTagCategory> invalidTagCategoryList = new ArrayList<>(VALID_TAG_CATEGORY);
        invalidTagCategoryList.add(new JsonAdaptedTagCategory(INVALID_TAG_CATEGORY));
        JsonAdaptedEntry entry = new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_PRICE, VALID_REVIEWS,
                VALID_ADDRESS, invalidTagCategoryList, VALID_TAG_SCHOOL);
        String expectedMessage = TagCategory.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelTyp_invalidTagSchool_throwsIllegalValueException() {
        List<JsonAdaptedTagSchool> invalidTagSchoolList = new ArrayList<>(VALID_TAG_SCHOOL);
        invalidTagSchoolList.add(new JsonAdaptedTagSchool(INVALID_TAG_SCHOOL));
        JsonAdaptedEntry entry = new JsonAdaptedEntry(VALID_NAME, VALID_RATING, VALID_PRICE, VALID_REVIEWS,
                VALID_ADDRESS, VALID_TAG_CATEGORY, invalidTagSchoolList);
        String expectedMessage = TagSchool.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

}
