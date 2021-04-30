package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.pool.Pool;

/**
 * Panel containing the list of passengers.
 */
public class PoolListPanel extends UiPart<Region> {
    private static final String FXML = "PoolListPanel.fxml";

    @FXML
    private ListView<Pool> poolListView;

    /**
     * Creates a {@code PassengerListPanel} with the given {@code ObservableList}.
     */
    public PoolListPanel(ObservableList<Pool> poolList) {
        super(FXML);
        poolListView.setItems(poolList);
        poolListView.setCellFactory(listView -> new PoolListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Passenger} using a {@code PassengerCard}.
     */
    static class PoolListViewCell extends ListCell<Pool> {
        @Override
        protected void updateItem(Pool pool, boolean empty) {
            super.updateItem(pool, empty);

            if (empty || pool == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PoolCard(pool, getIndex() + 1).getRoot());
            }
        }
    }

}
