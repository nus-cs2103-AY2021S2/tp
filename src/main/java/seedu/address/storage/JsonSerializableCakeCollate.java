package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CakeCollate;
import seedu.address.model.ReadOnlyCakeCollate;
import seedu.address.model.order.Order;

/**
 * An Immutable CakeCollate that is serializable to JSON format.
 */
@JsonRootName(value = "cakecollate")
class JsonSerializableCakeCollate {

    public static final String MESSAGE_DUPLICATE_ORDER = "Orders list contains duplicate order(s).";

    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCakeCollate} with the given orders.
     */
    @JsonCreator
    public JsonSerializableCakeCollate(@JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyCakeCollate} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCakeCollate}.
     */
    public JsonSerializableCakeCollate(ReadOnlyCakeCollate source) {
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this cakecollate into the model's {@code CakeCollate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CakeCollate toModelType() throws IllegalValueException {
        CakeCollate cakeCollate = new CakeCollate();
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (cakeCollate.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            cakeCollate.addOrder(order);
        }
        return cakeCollate;
    }

}
