package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.dish.Dish;

/**
 * Panel containing the list of persons.
 */
public class MenuListPanel extends UiPart<Region> {
    private static final String FXML = "MenuListPanel.fxml";

    @FXML
    private ListView<Dish> menuListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public MenuListPanel(ObservableList<Dish> menuList) {
        super(FXML);
        menuListView.setItems(menuList);
        menuListView.setCellFactory(listView -> new MenuListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class MenuListViewCell extends ListCell<Dish> {
        @Override
        protected void updateItem(Dish dish, boolean empty) {
            super.updateItem(dish, empty);

            if (empty || dish == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MenuCard(dish, getIndex() + 1).getRoot());
            }
        }
    }

}
