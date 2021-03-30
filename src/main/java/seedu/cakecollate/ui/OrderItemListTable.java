package seedu.cakecollate.ui;

import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Panel containing the list of orders.
 */
public class OrderItemListTable extends UiPart<Region> {
    private static final String FXML = "OrderItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @javafx.fxml.FXML
    private TableView<OrderItem> table;
    @FXML
    private TableColumn<OrderItem, Number> index;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderItemListTable(ObservableList<OrderItem> orderItemList) {
        super(FXML);
        table.setItems(orderItemList);
        index.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems()
                .indexOf(column.getValue()) + 1));
    }
}

