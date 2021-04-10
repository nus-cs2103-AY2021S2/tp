package chim.ui.panels;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import chim.commons.core.LogsCenter;
import chim.model.customer.Customer;
import chim.model.customer.CustomerId;
import chim.model.order.Order;
import chim.ui.UiPart;
import chim.ui.cards.OrderCard;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel in UI containing a list of orders.
 */
public class OrderListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @FXML
    private ListView<Order> listView;

    private final ObservableList<Customer> customerList;

    /**
     * Creates a {@code OrderListPanel} with the given {@code orderList}.
     * {@code customerList} is input into this class so that for each order, we can look for its
     * customer's details.
     */
    public OrderListPanel(ObservableList<Order> orderList, ObservableList<Customer> customerList) {
        super(FXML);
        listView.setItems(orderList);
        listView.setCellFactory(listView -> new OrderListView());
        this.customerList = customerList;
    }

    /**
     * Custom {@code ListCell} that displays the details of an {@code Order} using a {@code OrderCard}.
     */
    class OrderListView extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                Customer customer = getCustomerById(order.getCustomerId());
                requireNonNull(customer);
                setGraphic(new OrderCard(order, customer, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Obtains the {@code Customer} with the given {@code customerId}.
     * The customer with {@code customerId} must exist.
     */
    private Customer getCustomerById(CustomerId customerId) {
        for (Customer customer : customerList) {
            if (customerId.equals(customer.getId())) {
                return customer;
            }
        }
        return null;
    }
}
