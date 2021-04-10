package seedu.timeforwheels.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.UniqueCustomerList;

/**
 * Wraps all data at the delivery-list level
 * Duplicates are not allowed (by .isSameCustomer comparison)
 */
public class DeliveryList implements ReadOnlyDeliveryList {

    private final UniqueCustomerList customers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueCustomerList();
    }

    public DeliveryList() {}

    /**
     * Creates an DeliveryList using the Customers in the {@code toBeCopied}
     */
    public DeliveryList(ReadOnlyDeliveryList toBeCopied) {
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
     * Resets the existing data of this {@code DeliveryList} with {@code newData}.
     */
    public void resetData(ReadOnlyDeliveryList newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the delivery list.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Adds a customer to the delivery list.
     * The customer must not already exist in the delivery list.
     */
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the delivery list.
     * The customer identity of {@code editedCustomer} must not be the same as
     * another existing customer in the delivery list.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        customers.setCustomer(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code DeliveryList}.
     * {@code key} must exist in the delivery list.
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryList // instanceof handles nulls
                && customers.equals(((DeliveryList) other).customers));
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }
}
