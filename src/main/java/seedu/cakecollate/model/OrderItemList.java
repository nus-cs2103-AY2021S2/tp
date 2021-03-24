package seedu.cakecollate.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.UniqueOrderItemList;

public class OrderItemList implements ReadOnlyOrderItemList {

    private final UniqueOrderItemList orderItems;

    /**
     * Non-static initialization block to avoid duplication
     */
    {
        orderItems = new UniqueOrderItemList();
    }

    public OrderItemList() {
    }

    /*
     * Creates an OrderItemList using the Order Items in the {@code toBeCopied}
     */
    public OrderItemList(ReadOnlyOrderItemList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the order items list with {@code orderItems}.
     * {@code orderItems} must not contain duplicate orders.
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems.setOrderItems(orderItems);
    }

    /**
     * Resets the existing data of this {@code OrderItemList} with {@code newData}.
     */
    public void resetData(ReadOnlyOrderItemList newData) {
        requireNonNull(newData);
        setOrderItems(newData.getOrderItemList());
    }

    /**
     * Returns true if a order item with the same type as {@code orderItem} exists in the order item list.
     */
    public boolean hasOrderItem(OrderItem orderItem) {
        requireNonNull(orderItem);
        return orderItems.contains(orderItem);
    }

    /**
     * Adds a order item to the Order Item List.
     * The order item must not already exist in the order item list.
     */
    public void addOrderItem(OrderItem p) {
        orderItems.add(p);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrderItem}.
     * {@code target} must exist in the OrderItemList.
     */
    public void setOrderItem(OrderItem target, OrderItem editedOrderItem) {
        requireNonNull(editedOrderItem);

        orderItems.setOrderItem(target, editedOrderItem);
    }

    /**
     * Removes {@code key} from this {@code OrderItemList}.
     * {@code key} must exist in OrderItemList.
     */
    public void removeOrderItem(OrderItem key) {
        orderItems.remove(key);
    }

    @Override
    public String toString() {
        return orderItems.asUnmodifiableObservableList().size() + " order items";
    }

    @Override
    public ObservableList<OrderItem> getOrderItemList() {
        return orderItems.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderItemList // instanceof handles nulls
                && orderItems.equals(((OrderItemList) other).orderItems));
    }

    @Override
    public int hashCode() {
        return orderItems.hashCode();
    }

}
