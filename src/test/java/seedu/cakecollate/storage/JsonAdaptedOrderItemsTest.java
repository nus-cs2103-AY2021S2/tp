package seedu.cakecollate.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cakecollate.storage.JsonAdaptedOrderItems.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE_MUD;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.model.orderitem.Cost;
import seedu.cakecollate.model.orderitem.Type;

public class JsonAdaptedOrderItemsTest {
    private static final String INVALID_COST = "130 Banana Cakes";
    private static final String INVALID_TYPE = "";

    private static final String VALID_COST = CHOCOLATE_MUD.getCost().toString();
    private static final String VALID_TYPE = CHOCOLATE_MUD.getType().toString();

    @Test
    public void toModelType_validOrderItem_returnsOrderItem() throws Exception {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(CHOCOLATE_MUD);
        assertEquals(CHOCOLATE_MUD, orderItem.toModelType());
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(INVALID_COST, VALID_TYPE);
        String expectedMsg = Cost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMsg, orderItem::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(null, VALID_TYPE);
        String expectedMsg = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMsg, orderItem::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(VALID_COST, INVALID_TYPE);
        String expectedMsg = Type.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMsg, orderItem::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedOrderItems orderItem = new JsonAdaptedOrderItems(VALID_COST, null);
        String expectedMsg = String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMsg, orderItem::toModelType);
    }
}
