package seedu.address.storage.order;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderBook;

/**
 * An immutable OrderBook that is serializable to JSON format.
 */
@JsonRootName(value = "orderbook")
public class JsonSerializableOrderBook {
    public static final String MESSAGE_DUPLICATE_DISH = "Order list contains duplicate orders.";

    private final List<Order> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrderBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableOrderBook(@JsonProperty("orders") List<Order> orders) {
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyBook<Order>} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderBook}.
     */
    public JsonSerializableOrderBook(ReadOnlyBook<Order> source) {
        orders.addAll(source.getItemList());
    }

    /**
     * Converts this order book into the model's {@code OrderBook} object.
     * @return OrderBook model type
     * @throws IllegalValueException
     */
    public OrderBook toModelType() throws IllegalValueException {
        OrderBook orderBook = new OrderBook();
        orderBook.setOrders(orders);
        return orderBook;
    }
}
