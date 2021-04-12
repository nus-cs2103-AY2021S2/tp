package seedu.storemando.ui;

import static java.time.temporal.ChronoUnit.DAYS;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.model.item.Item;

/**
 * Table displaying all items expiring in a week.
 */
public class ExpiringTablePanel extends UiPart<Region> implements Initializable {
    private static final String FXML = "TablePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private ObservableList<Item> itemList;

    @FXML
    private Label labelId;

    @FXML
    private TableView<ExpiringItem> tableView;

    @FXML
    private TableColumn<ExpiringItem, String> numberOfDays;

    @FXML
    private TableColumn<ExpiringItem, String> numberOfItems;

    /**
     * Creates a {@code ExpiringTablePanel} with the given {@code ObservableList}.
     */
    public ExpiringTablePanel(ObservableList<Item> itemList) {
        super(FXML);
        this.itemList = itemList;
        tableView.setItems(getExpiringItems(itemList));
    }

    /**
     * Passing the data into the table.
     *
     * @param itemList The list of data required by the table.
     * @return A list of items expiring in a week.
     */
    private ObservableList<ExpiringItem> getExpiringItems(ObservableList<Item> itemList) {
        ObservableList<ExpiringItem> tableRows = FXCollections.observableArrayList();
        for (int i = 0; i <= 7; i++) {
            tableRows.add(new ExpiringItem(i, itemList));
        }
        return tableRows;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelId.setText("Expiring Items");
        numberOfDays.setCellValueFactory(new PropertyValueFactory<ExpiringItem, String>("daysBeforeExpiry"));
        numberOfItems.setCellValueFactory(new PropertyValueFactory<ExpiringItem, String>("numberOfItems"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        try {
            tableView.setSelectionModel(null);
        } catch (NullPointerException ex) {
            logger.info("User tried to click on the expiring item's table");
        }
    }

    /**
     * Item that are expiring in a week.
     */
    public class ExpiringItem {
        private String daysBeforeExpiry;
        private String numberOfItems;

        /**
         * Expiring Item constructor.
         *
         * @param daysBeforeExpiry   The number of days before the item expire.
         * @param itemList   The list of items in StoreMando.
         */
        public ExpiringItem(int daysBeforeExpiry, ObservableList<Item> itemList) {
            this.daysBeforeExpiry = convertDaysInIntToString(daysBeforeExpiry);
            long numberOfItems = getExpiringItemWithinDays(daysBeforeExpiry, itemList);
            this.numberOfItems = convertNumOfItemInIntToString(numberOfItems);

        }

        /**
         * Convert days that is a integer type to a string.
         *
         * @param daysBeforeExpiry The days in integer that will be converted to String.
         * @return The string representation of days.
         */
        private String convertDaysInIntToString(int daysBeforeExpiry) {
            if (daysBeforeExpiry <= 1) {
                return String.valueOf(daysBeforeExpiry) + " day";
            } else {
                return String.valueOf(daysBeforeExpiry) + " days";
            }
        }

        /**
         * Convert number of items that is a integer to a string.
         *
         * @param numberOfItems The number of items in integer that will be converted to string.
         * @return The string representation of number of items.
         */
        private String convertNumOfItemInIntToString(long numberOfItems) {
            if (numberOfItems <= 1) {
                return Long.toString(numberOfItems) + " item";
            } else {
                return Long.toString(numberOfItems) + " items";
            }
        }

        /**
         * Get the number of items that is expiring in the specified number of days.
         *
         * @param day The specified number of days.
         * @param itemList The list of items in StoreMando.
         * @return The number of items expiring in the specified number of days.
         */
        private long getExpiringItemWithinDays(int day, ObservableList<Item> itemList) {
            LocalDate today = LocalDate.now();
            return itemList.stream().filter(item -> {
                if (item.getExpiryDate().expiryDate == null) {
                    return false;
                }
                LocalDate itemExpiryDate = item.getExpiryDate().expiryDate;
                Long daysDifference = DAYS.between(today, itemExpiryDate);
                return daysDifference == day;
            }).count();
        }

        public String getDaysBeforeExpiry() {
            return daysBeforeExpiry;
        }

        public String getNumberOfItems() {
            return numberOfItems;
        }
    }
}
