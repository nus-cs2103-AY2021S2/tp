package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.UniqueCustomerList;
import seedu.address.model.order.Order;
import seedu.address.model.order.UniqueOrderList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameCustomer comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueCustomerList customers;
    private final UniqueOrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueCustomerList();
        orders = new UniqueOrderList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers.setCustomers(customers);
    }

    /**
     * Replaces the contents of the orders list with {@code orders}.
     * {@code orders} must not contain duplicate customers.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setOrders(newData.getOrderList());
    }

    //// order-level operations

    /**
     * Returns true if a orders with the same identity as {@code orders} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds a order to the address book.
     * The order must not already exist in the address book.
     */
    public void addOrder(Order o) {
        orders.add(o);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing customer
     * in the address book.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOrder(Order key) {
        orders.remove(key);
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Adds a customer to the address book.
     * The customer must not already exist in the address book.
     */
    public void addCustomer(Customer p) {
        customers.add(p);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer
     * in the address book.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        customers.setCustomer(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCustomer(Customer key) {
        customers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return customers.asUnmodifiableObservableList().size() + " customers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return customers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && customers.equals(((AddressBook) other).customers));
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }
}
