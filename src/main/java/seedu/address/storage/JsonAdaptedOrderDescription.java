package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.model.person.OrderDescription;


// todo remove all references to Tag in orderDesc related editted files
public class JsonAdaptedOrderDescription {
    private final String orderDescription;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedOrderDescription(String tagName) {
        this.orderDescription = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedOrderDescription(OrderDescription source) {
        orderDescription = source.value;
    }

    @JsonValue
    public String getOrderDescription() {
        return orderDescription;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     */
    public OrderDescription toModelType() {
        // no validation checks for order description so far
        return new OrderDescription(orderDescription);
    }
}
