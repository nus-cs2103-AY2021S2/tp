package seedu.cakecollate.ui;

import java.util.logging.Logger;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Panel containing the list of orders.
 */
public class OrderItemListTable extends UiPart<Region> {
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);
    private static final String FXML = "OrderItemListPanel.fxml";

    @javafx.fxml.FXML
    private TableView<OrderItem> tableView;
    @FXML
    private TableColumn<OrderItem, Number> index;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderItemListTable(ObservableList<OrderItem> orderItemList) {
        super(FXML);
        tableView.setItems(orderItemList);
        index.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tableView.getItems()
                .indexOf(column.getValue()) + 1));
    }
}

