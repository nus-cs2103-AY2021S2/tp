package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.CustomerId;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Quantity;
import seedu.address.model.util.FilteredSortedList;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Represents the in-memory model of CHIM's data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Chim chim;
    private final UserPrefs userPrefs;
    private final FilteredSortedList<Customer> filteredAndSortedCustomers;
    private final FilteredSortedList<Order> filteredAndSortedOrders;
    private final FilteredSortedList<Cheese> filteredAndSortedCheeses;

    /**
     * Initializes a ModelManager with the given chim and userPrefs.
     */
    public ModelManager(ReadOnlyChim chim, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(chim, userPrefs);

        logger.fine("Initializing with CHIM: " + chim + " and user prefs " + userPrefs);

        this.chim = new Chim(chim);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAndSortedCustomers = new FilteredSortedList<>(this.chim.getCustomerList());
        filteredAndSortedOrders = new FilteredSortedList<>(this.chim.getOrderList());
        filteredAndSortedCheeses = new FilteredSortedList<>(this.chim.getCheeseList());
    }

    public ModelManager() {
        this(new Chim(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getChimFilePath() {
        return userPrefs.getChimFilePath();
    }

    @Override
    public void setChimFilePath(Path chimFilePath) {
        requireNonNull(chimFilePath);
        userPrefs.setChimFilePath(chimFilePath);
    }

    //=========== Chim ================================================================================

    @Override
    public void setChim(ReadOnlyChim chim) {
        this.chim.resetData(chim);
    }

    @Override
    public ReadOnlyChim getChim() {
        return chim;
    }

    //=========== Customer Operations ==========================================================================

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return chim.hasCustomer(customer);
    }

    @Override
    public boolean hasCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return chim.hasCustomerWithPhone(phone);
    }

    @Override
    public Customer getCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return chim.getCustomerWithPhone(phone);
    }

    @Override
    public Customer getCustomerWithId(CustomerId id) {
        requireNonNull(id);
        return chim.getCustomerWithId(id);
    }

    @Override
    public void deleteCustomer(Customer target) {
        chim.deleteCustomer(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        chim.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        chim.setCustomer(target, editedCustomer);
    }

    @Override
    public ObservableList<Customer> getCompleteCustomerList() {
        return chim.getCustomerList();
    }

    //=========== Order Operations ==========================================================================

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return chim.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        chim.deleteOrder(target);
    }

    @Override
    public void addOrder(Order order) {
        chim.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        chim.setOrder(target, editedOrder);
    }

    @Override
    public Order getOrderWithId(OrderId orderId) {
        requireNonNull(orderId);
        return chim.getOrderWithId(orderId);
    }

    //=========== Cheese Operations ==========================================================================

    @Override
    public boolean hasCheese(Cheese cheese) {
        requireNonNull(cheese);
        return chim.hasCheese(cheese);
    }

    @Override
    public void deleteCheese(Cheese target) {
        chim.deleteCheese(target);
    }

    @Override
    public void addCheese(Cheese cheese) {
        chim.addCheese(cheese);
        updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);
    }

    @Override
    public void setCheese(Cheese target, Cheese editedCheese) {
        requireAllNonNull(target, editedCheese);

        chim.setCheese(target, editedCheese);
    }

    @Override
    public Cheese getCheeseWithId(CheeseId cheeseId) {
        requireNonNull(cheeseId);

        return chim.getCheeseWithId(cheeseId);
    }

    @Override
    public Set<CheeseId> getUnassignedCheeses(CheeseType cheeseType, Quantity quantity) {
        return chim.getUnassignedCheeses(cheeseType, quantity);
    }

    @Override
    public void updateCheesesStatus(Set<CheeseId> cheesesAssigned) {
        chim.updateCheesesStatus(cheesesAssigned);
    }


    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedChim}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredAndSortedCustomers.getObservableList();
    }

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code versionedChim}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredAndSortedOrders.getObservableList();
    }

    @Override
    public int getFilteredOrderListIncompleteCount() {
        int c = 0;
        for (Order o : getFilteredOrderList()) {
            if (!o.isComplete()) {
                c++;
            }
        }
        return c;
    }

    @Override
    public int getFilteredCheeseListUnassignedCount() {
        int c = 0;
        for (Cheese cheese : getFilteredCheeseList()) {
            if (!cheese.isCheeseAssigned()) {
                c++;
            }
        }
        return c;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Cheese} backed by the internal list of
     * {@code versionedChim}
     */
    @Override
    public ObservableList<Cheese> getFilteredCheeseList() {
        return filteredAndSortedCheeses.getObservableList();
    }

    @Override
    public void updateFilteredCustomerList(FieldPredicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredAndSortedCustomers.setModelPredicate(predicate);
    }

    @Override
    public void updateFilteredOrderList(FieldPredicate<Order> predicate) {
        requireNonNull(predicate);
        filteredAndSortedOrders.setModelPredicate(predicate);
    }

    @Override
    public void updateFilteredCheeseList(FieldPredicate<Cheese> predicate) {
        requireNonNull(predicate);
        filteredAndSortedCheeses.setModelPredicate(predicate);
    }

    //=========== For Toggling UI Panel ==================================================================

    @Override
    public void setPanelToCustomerList() {
        getGuiSettings().setPanelToCustomerList();
    }

    @Override
    public void setPanelToCheeseList() {
        getGuiSettings().setPanelToCheeseList();
    }

    @Override
    public void setPanelToOrderList() {
        getGuiSettings().setPanelToOrderList();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return chim.equals(other.chim)
                && userPrefs.equals(other.userPrefs)
                && filteredAndSortedCustomers.equals(other.filteredAndSortedCustomers)
                && filteredAndSortedOrders.equals(other.filteredAndSortedOrders)
                && filteredAndSortedCheeses.equals(other.filteredAndSortedCheeses);
    }

}
