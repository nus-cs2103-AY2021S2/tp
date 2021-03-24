package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.UniqueCheeseList;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.customer.UniqueCustomerList;
import seedu.address.model.order.Order;
import seedu.address.model.order.Quantity;
import seedu.address.model.order.UniqueOrderList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameCustomer comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueCustomerList customers;
    private final UniqueOrderList orders;
    private final UniqueCheeseList cheeses;

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
        cheeses = new UniqueCheeseList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Customers, Orders and Cheeses in the {@code toBeCopied}
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
     * Replaces the contents of the cheese list with {@code cheeses}.
     * {@code cheeses} must not contain duplicate cheeses.
     */
    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses.setCheeses(cheeses);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setOrders(newData.getOrderList());
        setCheeses(newData.getCheeseList());
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
     * The order identity of {@code editedOrder} must not be the same as another existing order
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
    public void deleteOrder(Order key) {
        orders.delete(key);

        // Cascade-delete the cheeses assigned to the deleted order
        List<Cheese> cheesesToDelete = new ArrayList<>();

        for (CheeseId cheeseId : key.getCheeses()) {
            for (Cheese cheese : cheeses) {
                if (cheese.getCheeseId().toString().equals(cheeseId.toString())) {
                    cheesesToDelete.add(cheese);
                    break;
                }
            }
        }

        for (Cheese cheese : cheesesToDelete) {
            deleteCheese(cheese);
        }
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
     * Returns true if a customer with {@code phone} exists in the address book.
     */
    public boolean hasCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return customers.hasPhone(phone);
    }

    /**
     * Returns a customer with {@code phone} if exists in the address book.
     */
    public Customer getCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return customers.getCustomerWithPhone(phone);
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
    public void deleteCustomer(Customer key) {
        customers.delete(key);

        // Cascade-delete orders belonging to the deleted customer
        List<Order> ordersToDelete = new ArrayList<>();

        for (Order order : orders) {
            if (order.getCustomerId().toString().equals(key.getId().toString())) {
                ordersToDelete.add(order);
            }
        }

        for (Order order : ordersToDelete) {
            deleteOrder(order);
        }
    }

    //// cheese-level operations

    /**
     * Returns true if a cheese with the same identity as {@code cheese} exists in the address book.
     */
    public boolean hasCheese(Cheese cheese) {
        requireNonNull(cheese);
        return cheeses.contains(cheese);
    }

    /**
     * Adds a cheese to the address book.
     * The cheese must not already exist in the address book.
     */
    public void addCheese(Cheese c) {
        cheeses.add(c);
    }

    /**
     * Replaces the given cheese {@code target} in the list with {@code editedCheese}.
     * {@code target} must exist in the address book.
     * The cheese identity of {@code editedCheese} must not be the same as another existing cheese
     * in the address book.
     */
    public void setCheese(Cheese target, Cheese editedCheese) {
        requireNonNull(editedCheese);

        cheeses.setCheese(target, editedCheese);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void deleteCheese(Cheese key) {
        cheeses.delete(key);
    }

    public Set<CheeseId> getUnassignedCheeses(CheeseType cheeseType, Quantity quantity) {
        return cheeses.getUnassignedCheeses(cheeseType, quantity);
    }

    public void updateCheesesStatus(Set<CheeseId> cheesesAssigned) {
        cheeses.updateCheesesStatus(cheesesAssigned);
    }

    //// util methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(customers.asUnmodifiableObservableList().size())
                .append(" customers, ")
                .append(cheeses.asUnmodifiableObservableList().size())
                .append(" cheeses, ")
                .append(orders.asUnmodifiableObservableList().size())
                .append(" orders");

        return sb.toString();
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
    public ObservableList<Cheese> getCheeseList() {
        return cheeses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && customers.equals(((AddressBook) other).customers))
                && cheeses.equals(((AddressBook) other).cheeses)
                && orders.equals(((AddressBook) other).orders);
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }

}
