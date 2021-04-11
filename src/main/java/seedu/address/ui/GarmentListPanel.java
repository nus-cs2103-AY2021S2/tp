package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.garment.Garment;

/**
 * Panel containing the list of garments.
 */
public class GarmentListPanel extends UiPart<Region> {
    private static final String FXML = "GarmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GarmentListPanel.class);

    @FXML
    private ListView<Garment> garmentListView;

    /**
     * Creates a {@code GarmentListPanel} with the given {@code ObservableList}.
     */
    public GarmentListPanel(ObservableList<Garment> garmentList) {
        super(FXML);
        garmentListView.setItems(garmentList);
        garmentListView.setCellFactory(listView -> new GarmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Garment} using a {@code GarmentCard}.
     */
    class GarmentListViewCell extends ListCell<Garment> {
        @Override
        protected void updateItem(Garment garment, boolean empty) {
            super.updateItem(garment, empty);

            if (empty || garment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GarmentCard(garment, getIndex() + 1).getRoot());
            }
        }
    }

}
