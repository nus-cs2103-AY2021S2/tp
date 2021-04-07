package seedu.cakecollate.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;

/**
 * Jackson-friendly version of {@link OrderItem}.
 */
public class JsonAdaptedOrderItems {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order item's %s field is missing!";

    private final String type;

    /**
     * Constructs a {@code JsonAdaptedOrderItem} with the given orderItem details
     */
    @JsonCreator
    public JsonAdaptedOrderItems(@JsonProperty("type") String type) {
        this.type = type;
    }

    /**
     * Constructs a given {@code OrderItem} with the given orderItem details
     */
    public JsonAdaptedOrderItems(OrderItem source) {
        type = source.getType().value;
    }

    /**
     * Converts this Jackson-friendly adapted orderItem object into the model's {@code OrderItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public OrderItem toModelType() throws IllegalValueException {
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        if (!Type.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type typeName = new Type(type);
        return new OrderItem(typeName);
    }
}

