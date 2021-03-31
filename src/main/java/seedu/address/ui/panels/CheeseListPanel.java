package seedu.address.ui.panels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cheese.Cheese;
import seedu.address.ui.UiPart;
import seedu.address.ui.cards.CheeseCard;

/**
 * Panel in UI containing a list of cheeses.
 */
public class CheeseListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomerListPanel.class);

    @FXML
    private ListView<Cheese> listView;

    /**
     * Creates a {@code CheeseListPanel} with the given {@code cheeseList}.
     */
    public CheeseListPanel(ObservableList<Cheese> cheeseList) {
        super(FXML);
        listView.setItems(cheeseList);
        listView.setCellFactory(listView -> new CheeseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the details of a {@code Cheese} using a {@code CustomerCard}.
     */
    class CheeseListViewCell extends ListCell<Cheese> {
        @Override
        protected void updateItem(Cheese cheese, boolean empty) {
            super.updateItem(cheese, empty);

            if (empty || cheese == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CheeseCard(cheese, getIndex() + 1).getRoot());
            }
        }
    }
}
