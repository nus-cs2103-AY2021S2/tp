package seedu.storemando.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.storemando.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.BREAD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.model.expirydate.ExpiryDate;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.Location;
import seedu.storemando.model.item.Quantity;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_QUANTITY = "+651234";
    private static final String INVALID_EXPIRYDATE = "example.com";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String VALID_NAME = BREAD.getItemName().toString();
    private static final String VALID_QUANTITY = BREAD.getQuantity().toString();
    private static final String VALID_EXPIRYDATE = BREAD.getExpiryDate().toString();
    private static final String VALID_LOCATION = BREAD.getLocation().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BREAD.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validItemDetails_returnsItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(BREAD);
        assertEquals(BREAD, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(INVALID_NAME, VALID_QUANTITY, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_QUANTITY, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, INVALID_QUANTITY, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, null, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test

    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, VALID_QUANTITY, INVALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, VALID_QUANTITY, null, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, VALID_QUANTITY, VALID_EXPIRYDATE, INVALID_LOCATION, VALID_TAGS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, VALID_QUANTITY, VALID_EXPIRYDATE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME, VALID_QUANTITY, VALID_EXPIRYDATE, VALID_LOCATION, invalidTags);
        assertThrows(IllegalValueException.class, item::toModelType);
    }

}
