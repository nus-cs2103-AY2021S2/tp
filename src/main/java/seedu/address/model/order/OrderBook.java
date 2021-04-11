package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.UniqueItemList;

public class OrderBook implements ReadOnlyBook<Order> {
    private final UniqueItemList<Order> orders;
    {
        orders = new UniqueItemList<Order>();
    }

    public OrderBook() {}

    /**
     * Constructor to copy order book
     * @param toBeCopied
     */
    public OrderBook(ReadOnlyBook<Order> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Set orders from list
     * @param orders
     */
    public void setOrders(List<Order> orders) {
        this.orders.setItems(orders);
    }

    /**
     * Reset data to specified data
     * @param newData
     */
    public void resetData(ReadOnlyBook<Order> newData) {
        requireNonNull(newData);

        setOrders(newData.getItemList());
    }

    /**
     * Check if order exists
     * @param order
     * @return result
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);

        return orders.contains(order);
    }

    /**
     * Add order to list
     * @param order order to be added
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Set order to new order details
     * @param target target order to be edited
     * @param editedOrder edited version of order
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);
        orders.setItem(target, editedOrder);
    }

    /**
     * Remove specific order
     * @param key order to be removed
     */
    public void removeOrder(Order key) {
        orders.remove(key);
    }

    /**
     * Turns the state of an order to complete
     * @param key order to be completed
     */
    public void completeOrder(Order key) {
        key.setState(Order.State.COMPLETED);
    }

    /**
     * Cancel an order
     * @param key order to be cancelled
     */
    public void cancelOrder(Order key) {
        key.setState(Order.State.CANCELLED);
    }

    /**
     * Sorts item with a comparator that compares datetime
     * @param comp comparator to use for sorting
     */
    public void sortItemsByDateTime(Comparator<Order> comp) {
        orders.sort(comp);
    }

    @Override
    public String toString() {
        return orders.asUnmodifiableObservableList().size() + " orders";
        // TODO: refine later
    }

    @Override
    public ObservableList<Order> getItemList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderBook // instanceof handles nulls
                && orders.equals(((OrderBook) other).orders));
    }
}
