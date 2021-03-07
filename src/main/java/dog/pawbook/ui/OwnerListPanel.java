package dog.pawbook.ui;

import java.util.logging.Logger;

import dog.pawbook.commons.core.LogsCenter;
import dog.pawbook.model.owner.Owner;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of owners.
 */
public class OwnerListPanel extends UiPart<Region> {
    private static final String FXML = "OwnerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OwnerListPanel.class);

    @FXML
    private ListView<Owner> ownerListView;

    /**
     * Creates a {@code OwnerListPanel} with the given {@code ObservableList}.
     */
    public OwnerListPanel(ObservableList<Owner> ownerList) {
        super(FXML);
        ownerListView.setItems(ownerList);
        ownerListView.setCellFactory(listView -> new OwnerListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Owner} using a {@code OwnerCard}.
     */
    class OwnerListViewCell extends ListCell<Owner> {
        @Override
        protected void updateItem(Owner owner, boolean empty) {
            super.updateItem(owner, empty);

            if (empty || owner == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OwnerCard(owner, getIndex() + 1).getRoot());
            }
        }
    }

}
