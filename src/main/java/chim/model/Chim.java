package chim.model;

import static chim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chim.commons.core.Messages;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseId;
import chim.model.cheese.CheeseType;
import chim.model.cheese.UniqueCheeseList;
import chim.model.customer.Customer;
import chim.model.customer.CustomerId;
import chim.model.customer.Phone;
import chim.model.customer.UniqueCustomerList;
import chim.model.order.CompletedDate;
import chim.model.order.Order;
import chim.model.order.OrderDate;
import chim.model.order.OrderId;
import chim.model.order.Quantity;
import chim.model.order.UniqueOrderList;
import javafx.collections.ObservableList;

/**
 * Wraps all data in CHIM.
 * Duplicate customers, orders, or cheeses are not allowed.
 */
public class Chim implements ReadOnlyChim {

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

    public Chim() {
    }

    /**
     * Creates Chim using the Customers, Orders and Cheeses in the {@code toBeCopied}
     */
    public Chim(ReadOnlyChim toBeCopied) {
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
     * Resets the existing data of this {@code Chim} with {@code newData}.
     */
    public void resetData(ReadOnlyChim newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setOrders(newData.getOrderList());
        setCheeses(newData.getCheeseList());
    }

    //// order-level operations

    /**
     * Returns true if orders with the same identity as {@code orders} exists in CHIM.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds an order to CHIM.
     * The order must not already exist in CHIM.
     */
    public void addOrder(Order o) {
        orders.add(o);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in CHIM.
     * The order identity of {@code editedOrder} must not be the same as another existing order
     * in CHIM.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);
    }

    /**
     * Removes {@code key} from this {@code Chim}.
     * {@code key} must exist in CHIM.
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

    /**
     * Returns an order with {@code orderId} if exists in CHIM.
     */
    public Order getOrderWithId(OrderId orderId) {
        requireNonNull(orderId);
        return orders.getOrderWithId(orderId);
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in CHIM.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Returns true if a customer with {@code phone} exists in CHIM.
     */
    public boolean hasCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return customers.hasPhone(phone);
    }

    /**
     * Returns a customer with {@code phone} if exists in CHIM.
     */
    public Customer getCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return customers.getCustomerWithPhone(phone);
    }

    /**
     * Returns a customer with {@code id} if exists in CHIM.
     */
    public Customer getCustomerWithId(CustomerId id) {
        requireNonNull(id);
        return customers.getCustomerWithId(id);
    }


    /**
     * Adds a customer to CHIM.
     * The customer must not already exist in CHIM.
     */
    public void addCustomer(Customer p) {
        customers.add(p);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in CHIM.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer
     * in CHIM.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        customers.setCustomer(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code Chim}.
     * {@code key} must exist in CHIM.
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
     * Returns true if a cheese with the same identity as {@code cheese} exists in CHIM.
     */
    public boolean hasCheese(Cheese cheese) {
        requireNonNull(cheese);
        return cheeses.contains(cheese);
    }

    /**
     * Adds a cheese to CHIM.
     * The cheese must not already exist in CHIM.
     */
    public void addCheese(Cheese c) {
        cheeses.add(c);
    }

    /**
     * Replaces the given cheese {@code target} in the list with {@code editedCheese}.
     * {@code target} must exist in CHIM.
     * The cheese identity of {@code editedCheese} must not be the same as another existing cheese
     * in CHIM.
     */
    public void setCheese(Cheese target, Cheese editedCheese) {
        requireNonNull(editedCheese);

        cheeses.setCheese(target, editedCheese);
    }

    /**
     * Removes {@code key} from this {@code Chim}.
     * {@code key} must exist in CHIM.
     */
    public void deleteCheese(Cheese key) {
        cheeses.delete(key);
    }

    /**
     * Returns a cheese with {@code cheeseId} if exists in CHIM.
     */
    public Cheese getCheeseWithId(CheeseId cheeseId) {
        requireNonNull(cheeseId);
        return cheeses.getCheeseWithId(cheeseId);
    }

    public Set<CheeseId> getUnassignedCheeses(CheeseType cheeseType, Quantity quantity) {
        return cheeses.getUnassignedCheeses(cheeseType, quantity);
    }

    public void updateCheesesStatus(Set<CheeseId> cheesesAssigned) {
        cheeses.updateCheesesStatus(cheesesAssigned);
    }

    /**
     * Checks whether CHIM's current state is valid.
     * Includes checking dependencies between models.
     */
    public void checkChim() {
        Set<CustomerId> customerIdSet = customers.asUnmodifiableObservableList().stream()
            .map(x -> x.getId()).collect(Collectors.toSet());
        Map<CheeseId, Cheese> cheeseIdMap = cheeses.asUnmodifiableObservableList().stream()
            .collect(Collectors.toMap(x -> x.getCheeseId(), x -> x));

        HashSet<CheeseId> orderCheeseIdSet = new HashSet<>();
        // Check that each order is valid on the system level
        for (Order order : orders.asUnmodifiableObservableList()) {
            CustomerId customerId = order.getCustomerId();
            CheeseType expectedCheeseType = order.getCheeseType();
            Set<CheeseId> cheeseIds = order.getCheeses();
            OrderId orderId = order.getOrderId();
            OrderDate orderDate = order.getOrderDate();
            Optional<CompletedDate> completedDate = order.getCompletedDate();

            checkArgument(customerIdSet.contains(customerId),
                String.format(Messages.MESSAGE_INVALID_ORDER_CUSTOMER_ID, orderId.value));

            if (cheeseIds.size() > 0) {
                for (CheeseId cheeseId : cheeseIds) {
                    checkArgument(cheeseIdMap.containsKey(cheeseId),
                        String.format(Messages.MESSAGE_INVALID_ORDER_CHEESE_ID, orderId.value));

                    // Each assigned cheese should have a one-to-one relation with orders
                    checkArgument(!orderCheeseIdSet.contains(cheeseId),
                        String.format(Messages.MESSAGE_INVALID_CHEESE_MULTIPLE_ORDER, orderId.value, cheeseId.value));
                    orderCheeseIdSet.add(cheeseId);

                    Cheese currentCheese = cheeseIdMap.get(cheeseId);

                    // Cheese should have been marked assigned
                    checkArgument(currentCheese.isCheeseAssigned(),
                        String.format(Messages.MESSAGE_INVALID_CHEESE_NOT_ASSIGNED, orderId.value, cheeseId.value));

                    // Cheese should match the order by type
                    checkArgument(currentCheese.getCheeseType().equals(expectedCheeseType),
                        String.format(Messages.MESSAGE_INVALID_ORDER_CHEESE_CHEESE_TYPE,
                            orderId.value, cheeseId.value));

                    if (completedDate.isPresent()) {
                        // Cheese's manufacture date must be after the order's completed date
                        checkArgument(completedDate.get().isAfterOrEquals(currentCheese.getManufactureDate()),
                            String.format(Messages.MESSAGE_INVALID_MANUFACTURE_DATE_COMPLETED_DATE,
                                orderId.value, cheeseId.value));

                        // Cheese's expiry date must be after the order's completed date
                        if (currentCheese.getExpiryDate().isPresent()) {
                            checkArgument(currentCheese.getExpiryDate().get().isAfterOrEquals(completedDate.get()),
                                String.format(Messages.MESSAGE_INVALID_EXPIRY_DATE_COMPLETED_DATE,
                                    orderId.value, cheeseId.value));
                        }
                    }

                }
            }
        }

        for (Cheese cheese: cheeseIdMap.values()) {
            if (cheese.isCheeseAssigned()) {
                CheeseId cheeseId = cheese.getCheeseId();
                // Cheeses that are assigned must have an order attached to it
                checkArgument(orderCheeseIdSet.contains(cheese.getCheeseId()),
                    String.format(Messages.MESSAGE_INVALID_ASSIGNED_CHEESE, cheeseId.value));
            }
        }
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
                || (other instanceof Chim // instanceof handles nulls
                && customers.equals(((Chim) other).customers))
                && cheeses.equals(((Chim) other).cheeses)
                && orders.equals(((Chim) other).orders);
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }

}
