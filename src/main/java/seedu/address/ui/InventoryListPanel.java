package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.ingredient.Ingredient;

/**
 * Panel containing the list of persons.
 */
public class InventoryListPanel extends UiPart<Region> {
    private static final String FXML = "InventoryListPanel.fxml";

    @FXML
    private ListView<Ingredient> inventoryListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public InventoryListPanel(ObservableList<Ingredient> inventoryList) {
        super(FXML);
        inventoryListView.setItems(inventoryList);
        inventoryListView.setCellFactory(listView -> new InventoryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class InventoryListViewCell extends ListCell<Ingredient> {
        @Override
        protected void updateItem(Ingredient ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if (empty || ingredient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InventoryCard(ingredient, getIndex() + 1).getRoot());
            }
        }
    }

}
