package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.ExpiryDate;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Location;
import seedu.address.model.item.Phone;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EXPIRYDATE = "example.com";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getItemName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EXPIRYDATE = BENSON.getExpiryDate().toString();
    private static final String VALID_LOCATION = BENSON.getLocation().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validItemDetails_returnsItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(BENSON);
        assertEquals(BENSON, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(INVALID_NAME, VALID_PHONE, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_PHONE, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, INVALID_PHONE, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, null, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test

    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, VALID_PHONE, INVALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, VALID_PHONE, null, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, VALID_PHONE, VALID_EXPIRYDATE, INVALID_LOCATION, VALID_TAGS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, VALID_PHONE, VALID_EXPIRYDATE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, VALID_PHONE, VALID_EXPIRYDATE, VALID_LOCATION, invalidTags);
        assertThrows(IllegalValueException.class, item::toModelType);
    }

}
