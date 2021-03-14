package seedu.address.ui.panels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.customer.Customer;
import seedu.address.ui.UiPart;
import seedu.address.ui.cards.CustomerCard;

/**
 * Panel in UI containing a list of customers.
 */
public class CustomerListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomerListPanel.class);

    @FXML
    private ListView<Customer> listView;

    /**
     * Creates a {@code CustomerListPanel} with the given {@code customerList}.
     */
    public CustomerListPanel(ObservableList<Customer> customerList) {
        super(FXML);
        listView.setItems(customerList);
        listView.setCellFactory(listView -> new CustomerListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the details of a {@code Customer} using a {@code CustomerCard}.
     */
    class CustomerListViewCell extends ListCell<Customer> {
        @Override
        protected void updateItem(Customer customer, boolean empty) {
            super.updateItem(customer, empty);

            if (empty || customer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CustomerCard(customer, getIndex() + 1).getRoot());
            }
        }
    }
}
