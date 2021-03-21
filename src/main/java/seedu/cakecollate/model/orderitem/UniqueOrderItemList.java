package seedu.cakecollate.model.orderitem;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cakecollate.model.orderitem.exceptions.DuplicateOrderItemException;
import seedu.cakecollate.model.orderitem.exceptions.OrderItemNotFoundException;

/**
 * A list of orders that enforces uniqueness between its elements and does not allow nulls.
 * A order is considered unique by comparing using {@code Order#isSameOrder(Order)}. As such, adding and updating of
 * orders uses Order#isSameOrder(Order) for equality so as to ensure that the order being added or updated is
 * unique in terms of identity in the UniqueOrderList. However, the removal of an order uses Order#equals(Object) so
 * as to ensure that the order with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see OrderItem#isSameOrderItem(OrderItem)
 */
public class UniqueOrderItemList implements Iterable<OrderItem> {

    private final ObservableList<OrderItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<OrderItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent order item as the given argument.
     */
    public boolean contains(OrderItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrderItem);
    }

    /**
     * Adds an order item to the list.
     * The order item must not already exist in the list.
     */
    public void add(OrderItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOrderItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the list.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the list.
     */
    public void setOrder(OrderItem target, OrderItem editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderItemNotFoundException();
        }

        if (!target.isSameOrderItem(editedOrder) && contains(editedOrder)) {
            throw new DuplicateOrderItemException();
        }

        internalList.set(index, editedOrder);
    }

    /**
     * Removes the equivalent order from the list.
     * The order must exist in the list.
     */
    public void remove(OrderItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderItemNotFoundException();
        }
    }

    public void setOrders(UniqueOrderItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<OrderItem> orders) {
        requireAllNonNull(orders);
        if (!orderItemsAreUnique(orders)) {
            throw new DuplicateOrderItemException();
        }

        internalList.setAll(orders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<OrderItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<OrderItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOrderItemList // instanceof handles nulls
                && internalList.equals(((UniqueOrderItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code orders} contains only unique orders.
     */
    private boolean orderItemsAreUnique(List<OrderItem> orders) {
        for (int i = 0; i < orders.size() - 1; i++) {
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(i).isSameOrderItem(orders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
