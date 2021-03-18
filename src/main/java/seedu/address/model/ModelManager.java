package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.model.order.Quantity;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<Cheese> filteredCheeses;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());
        filteredOrders = new FilteredList<>(this.addressBook.getOrderList());
        filteredCheeses = new FilteredList<>(this.addressBook.getCheeseList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== Customer Operations ==========================================================================

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasCustomer(customer);
    }

    @Override
    public boolean hasCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return addressBook.hasCustomerWithPhone(phone);
    }

    @Override
    public Customer getCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return addressBook.getCustomerWithPhone(phone);
    }

    @Override
    public void deleteCustomer(Customer target) {
        addressBook.deleteCustomer(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setCustomer(target, editedCustomer);
    }

    //=========== Order Operations ==========================================================================

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return addressBook.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        addressBook.deleteOrder(target);
    }

    @Override
    public void addOrder(Order order) {
        addressBook.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        addressBook.setOrder(target, editedOrder);
    }

    //=========== Cheese Operations ==========================================================================

    @Override
    public boolean hasCheese(Cheese cheese) {
        requireNonNull(cheese);
        return addressBook.hasCheese(cheese);
    }

    @Override
    public void deleteCheese(Cheese target) {
        addressBook.deleteCheese(target);
    }

    @Override
    public void addCheese(Cheese cheese) {
        addressBook.addCheese(cheese);
        updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);
    }

    @Override
    public void setCheese(Cheese target, Cheese editedCheese) {
        requireAllNonNull(target, editedCheese);

        addressBook.setCheese(target, editedCheese);
    }

    @Override
    public Set<CheeseId> getUnassignedCheeses(CheeseType cheeseType, Quantity quantity) {
        return addressBook.getUnassignedCheeses(cheeseType, quantity);
    }

    @Override
    public void updateCheesesStatus(Set<CheeseId> cheesesAssigned) {
        addressBook.updateCheesesStatus(cheesesAssigned);
    }


    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Cheese} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Cheese> getFilteredCheeseList() {
        return filteredCheeses;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    @Override
    public void updateFilteredCheeseList(Predicate<Cheese> predicate) {
        requireNonNull(predicate);
        filteredCheeses.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers)
                && filteredOrders.equals(other.filteredOrders)
                && filteredCheeses.equals(other.filteredCheeses);
    }

}
