package seedu.timeforwheels.model;

import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.timeforwheels.commons.core.GuiSettings;
import seedu.timeforwheels.commons.core.LogsCenter;
import seedu.timeforwheels.model.customer.Customer;

/**
 * Represents the in-memory model of the delivery list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final DeliveryList deliveryList;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;

    /**
     * Initializes a ModelManager with the given deliveryList and userPrefs.
     */
    public ModelManager(ReadOnlyDeliveryList deliveryList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(deliveryList, userPrefs);

        logger.fine("Initializing with delivery list: " + deliveryList + " and user prefs " + userPrefs);

        this.deliveryList = new DeliveryList(deliveryList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.deliveryList.getCustomerList());
    }

    public ModelManager() {
        this(new DeliveryList(), new UserPrefs());
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
    public Path getDeliveryListFilePath() {
        return userPrefs.getDeliveryListFilePath();
    }

    @Override
    public void setDeliveryListFilePath(Path deliveryListFilePath) {
        requireNonNull(deliveryListFilePath);
        userPrefs.setDeliveryListFilePath(deliveryListFilePath);
    }

    //=========== DeliveryList ================================================================================

    @Override
    public void setDeliveryList(ReadOnlyDeliveryList deliveryList) {
        this.deliveryList.resetData(deliveryList);
    }

    @Override
    public ReadOnlyDeliveryList getDeliveryList() {
        return deliveryList;
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return deliveryList.hasCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer target) {
        deliveryList.removeCustomer(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        deliveryList.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        deliveryList.setCustomer(target, editedCustomer);
    }

    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedDeliveryList}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
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
        return deliveryList.equals(other.deliveryList)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers);
    }

}
