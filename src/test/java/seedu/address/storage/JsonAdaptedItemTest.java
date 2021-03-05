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
<<<<<<< HEAD
import seedu.address.model.item.Name;
import seedu.address.model.item.Quantity;
=======
import seedu.address.model.item.Phone;
>>>>>>> mid-1.2-base-refactor

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_QUANTITY = "+651234";
    private static final String INVALID_EXPIRYDATE = "example.com";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_TAG = "#friend";

<<<<<<< HEAD
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_QUANTITY = BENSON.getQuantity().toString();
=======
    private static final String VALID_NAME = BENSON.getItemName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
>>>>>>> mid-1.2-base-refactor
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
<<<<<<< HEAD
            new JsonAdaptedItem(INVALID_NAME, VALID_QUANTITY, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
=======
            new JsonAdaptedItem(INVALID_NAME, VALID_PHONE, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
>>>>>>> mid-1.2-base-refactor
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
<<<<<<< HEAD
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_QUANTITY, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
=======
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_PHONE, VALID_EXPIRYDATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
>>>>>>> mid-1.2-base-refactor
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
