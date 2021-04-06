package seedu.cakecollate.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cakecollate.storage.JsonAdaptedOrderItems.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE_MUD;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.model.orderitem.Type;

public class JsonAdaptedOrderItemsTest {

    private static final String INVALID_TYPE = "";

    @Test
    public void toModelType_validOrderItem_returnsOrderItem() throws Exception {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(CHOCOLATE_MUD);
        assertEquals(CHOCOLATE_MUD, orderItem.toModelType());
    }


    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(INVALID_TYPE);
        String expectedMsg = Type.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMsg, orderItem::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems((String) null);
        String expectedMsg = String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMsg, orderItem::toModelType);
    }
}

