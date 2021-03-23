package seedu.cakecollate.model;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.model.order.Order;

/**
 * Represents the in-memory model of the cakecollate data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CakeCollate cakeCollate;
    private final UserPrefs userPrefs;
    private final FilteredList<Order> filteredOrders;

    /**
     * Initializes a ModelManager with the given cakeCollate and userPrefs.
     */
    public ModelManager(ReadOnlyCakeCollate cakeCollate, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(cakeCollate, userPrefs);

        logger.fine("Initializing with cakecollate: " + cakeCollate + " and user prefs " + userPrefs);

        this.cakeCollate = new CakeCollate(cakeCollate);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredOrders = new FilteredList<>(this.cakeCollate.getOrderList());
        sortFilteredOrderList();
    }

    public ModelManager() {
        this(new CakeCollate(), new UserPrefs());
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
    public Path getCakeCollateFilePath() {
        return userPrefs.getCakeCollateFilePath();
    }

    @Override
    public void setCakeCollateFilePath(Path cakeCollateFilePath) {
        requireNonNull(cakeCollateFilePath);
        userPrefs.setCakeCollateFilePath(cakeCollateFilePath);
    }

    //=========== CakeCollate ================================================================================

    @Override
    public void setCakeCollate(ReadOnlyCakeCollate cakeCollate) {
        this.cakeCollate.resetData(cakeCollate);
    }

    @Override
    public ReadOnlyCakeCollate getCakeCollate() {
        return cakeCollate;
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return cakeCollate.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        cakeCollate.removeOrder(target);
    }

    @Override
    public void addOrder(Order order) {
        cakeCollate.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        sortFilteredOrderList();
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        cakeCollate.setOrder(target, editedOrder);
        sortFilteredOrderList();
    }

    //=========== Filtered Order List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code versionedCakeCollate}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void sortFilteredOrderList() {
        // calls the cakeCollate model to sort the list because only
        // the cakeCollate model class has access to the modifiable list
        cakeCollate.sortOrderList();
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
        // sortFilteredOrderList();
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
        return cakeCollate.equals(other.cakeCollate)
                && userPrefs.equals(other.userPrefs)
                && filteredOrders.equals(other.filteredOrders);
    }

}
