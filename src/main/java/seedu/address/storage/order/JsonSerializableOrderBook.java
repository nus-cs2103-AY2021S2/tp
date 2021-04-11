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

    @JsonCreator
    public JsonSerializableOrderBook(@JsonProperty("orders") List<Order> orders) {
        this.orders.addAll(orders);
    }

    public JsonSerializableOrderBook(ReadOnlyBook<Order> source) {
        orders.addAll(source.getItemList());
    }

    /**
     * To model type
     * @return OrderBook model type
     * @throws IllegalValueException
     */
    public OrderBook toModelType() throws IllegalValueException {
        OrderBook orderBook = new OrderBook();
        orderBook.setOrders(orders);
        return orderBook;
    }
}
