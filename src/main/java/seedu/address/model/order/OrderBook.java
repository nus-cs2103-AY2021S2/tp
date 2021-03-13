package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.UniqueItemList;

public class OrderBook implements ReadOnlyOrderBook {
    private final UniqueItemList<Order> orders;
    {
        orders = new UniqueItemList<Order>();
    }

    public OrderBook() {}

    public OrderBook(ReadOnlyOrderBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setOrders(List<Order> orders) {
        this.orders.setItems(orders);
    }

    public void resetData(ReadOnlyOrderBook newData) {
        requireNonNull(newData);

        setOrders(newData.getOrderList());
    }

    public boolean hasOrder(Order order) {
        requireNonNull(order);

        return orders.contains(order);
    }

    public void addOrder(Order o) {
        orders.add(o);
    }

    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);
        orders.setItem(target, editedOrder);
    }

    public void removeOrder(Order key) {
        orders.remove(key);
    }

    @Override
    public String toString() {
        return orders.asUnmodifiableObservableList().size() + " orders";
        // TODO: refine later
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderBook // instanceof handles nulls
                && orders.equals(((OrderBook) other).orders));
    }

}
