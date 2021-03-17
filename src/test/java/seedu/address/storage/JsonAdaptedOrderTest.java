package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Address;
import seedu.address.model.order.DeliveryDate;
import seedu.address.model.order.Email;
import seedu.address.model.order.Name;
import seedu.address.model.order.OrderDescription;
import seedu.address.model.order.Phone;

public class JsonAdaptedOrderTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ORDER_DESCRIPTION = " ";
    private static final String INVALID_DELIVERY_DATE = "31/31/2100";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedOrderDescription> VALID_ORDER_DESC = BENSON.getOrderDescriptions().stream()
            .map(JsonAdaptedOrderDescription::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_DELIVERY_DATE = BENSON.getDeliveryDate().toString();

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(BENSON);
        assertEquals(BENSON, order.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORDER_DESC,
                        VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_ORDER_DESC, VALID_TAGS, VALID_DELIVERY_DATE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORDER_DESC,
                        invalidTags, VALID_DELIVERY_DATE);
        assertThrows(IllegalValueException.class, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderDescription_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_TAGS, VALID_DELIVERY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderDescription_throwsIllegalValueException() {
        List<JsonAdaptedOrderDescription> invalidOrderDescs = new ArrayList<>(VALID_ORDER_DESC);
        invalidOrderDescs.add(new JsonAdaptedOrderDescription(INVALID_ORDER_DESCRIPTION));
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                invalidOrderDescs, VALID_TAGS, VALID_DELIVERY_DATE);
        assertThrows(IllegalValueException.class, order::toModelType);
    }

    @Test
    public void toModelType_invalidDeliveryDate_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORDER_DESC, VALID_TAGS,
                        INVALID_DELIVERY_DATE);
        String expectedMessage = DeliveryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullDeliveryDate_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORDER_DESC, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DeliveryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

}
