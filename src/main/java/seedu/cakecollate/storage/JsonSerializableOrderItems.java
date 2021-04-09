package seedu.cakecollate.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.ReadOnlyOrderItems;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * An immutable OrderItem that is serializable to JSON format.
 */
@JsonRootName(value = "OrderItems")
class JsonSerializableOrderItems {

    public static final String MESSAGE_DUPLICATE_ORDER_ITEMS = "OrderItems list contains duplicate orderItems(s).";

    private final List<JsonAdaptedOrderItems> orderItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrderItems} with the given orders.
     */
    @JsonCreator
    public JsonSerializableOrderItems(@JsonProperty("orderItems") List<JsonAdaptedOrderItems> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    /**
     * Converts a given {@code ReadOnlyOrderItems} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderItems}.
     */
    public JsonSerializableOrderItems(ReadOnlyOrderItems source) {
        orderItems.addAll(source.getOrderItemList().stream()
                .map(JsonAdaptedOrderItems::new).collect(Collectors.toList()));
    }

    /**
     * Converts this orderitems into the model's {@code OrderItems} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public OrderItems toModelType() throws IllegalValueException {
        OrderItems orderitems = new OrderItems();
        for (JsonAdaptedOrderItems jsonAdaptedOrderItems : orderItems) {
            OrderItem orderItem = jsonAdaptedOrderItems.toModelType();
            if (orderitems.hasOrderItem(orderItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER_ITEMS);
            }
            orderitems.addOrderItem(orderItem);
        }
        return orderitems;
    }
}
