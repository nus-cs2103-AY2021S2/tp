package seedu.address.storage.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderBook;
import seedu.address.model.order.ReadOnlyOrderBook;

import java.util.ArrayList;
import java.util.List;

/**
 * An immutable OrderBook that is serializable to JSON format.
 */
@JsonRootName(value = "orderbook")
public class JsonSerializableOrderBook {
    public static final String MESSAGE_DUPLICATE_DISH = "Order list contains duplicate orders.";

    private final List<Order> orders = new ArrayList<>();

    @JsonCreator
    public JsonSerializableOrderBook(@JsonProperty("orders") List<Order> orders) {
        this.orders.addAll(orders);
    }

    public JsonSerializableOrderBook(ReadOnlyOrderBook source) {
        orders.addAll(source.getOrderList());
    }

    public OrderBook toModelType() throws IllegalValueException {
        OrderBook orderBook = new OrderBook();
        orderBook.setOrders(orders);
        return orderBook;
    }
}
