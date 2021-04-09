package seedu.cakecollate.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Panel containing the list of orders.
 */
public class OrderItemPanel extends Panel {
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @FXML
    private ListView<OrderItem> listView;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderItemPanel(ObservableList<OrderItem> orderList) {
        super();
        listView.setItems(orderList);
        listView.setCellFactory(listView -> new OrderItemViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code OrderCard}.
     */
    class OrderItemViewCell extends ListCell<OrderItem> {
        @Override
        protected void updateItem(OrderItem orderItem, boolean empty) {
            super.updateItem(orderItem, empty);

            if (empty || orderItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderItemCard(orderItem, Integer.toString(getIndex() + 1)).getRoot());
            }
        }
    }
}

