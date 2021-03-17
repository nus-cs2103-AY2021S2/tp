package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderDescription;


public class JsonAdaptedOrderDescription {
    private final String orderDescription;

    /**
     * Constructs a {@code JsonAdaptedOrderDescription} with the given {@code orderDescription}.
     */
    @JsonCreator
    public JsonAdaptedOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    /**
     * Converts a given {@code orderDescription} into this class for Jackson use.
     */
    public JsonAdaptedOrderDescription(OrderDescription source) {
        orderDescription = source.value;
    }

    @JsonValue
    public String getOrderDescription() {
        return orderDescription;
    }

    /**
     * Converts this Jackson-friendly adapted OrderDescription object into the model's {@code OrderDescription} object.
     *
     */
    public OrderDescription toModelType() throws IllegalValueException {
        if (!OrderDescription.isValidOrderDescription(orderDescription)) {
            throw new IllegalValueException(OrderDescription.MESSAGE_CONSTRAINTS);
        }
        return new OrderDescription(orderDescription);
    }
}
