package seedu.storemando.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.model.item.Item;

/**
 * Panel containing the list of items.
 */
public class LocationListPanel extends UiPart<Region> {
    private static final String FXML = "LocationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LocationListPanel.class);

    @FXML
    private ListView<Item> locationListView;

    /**
     * Creates a {@code LocationListPanel} with the given {@code ObservableList}.
     */
    public LocationListPanel(ObservableList<Item> itemList) {
        super(FXML);
        locationListView.setItems(itemList);
        locationListView.setCellFactory(listView -> new LocationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code LocationCard}.
     */
    class LocationListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LocationCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
