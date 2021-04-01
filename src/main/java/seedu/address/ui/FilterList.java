package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * A ui for a FilterList that contains a list of FilterCards.
 */
public class FilterList extends UiPart<Region> {

    private static final String FXML = "FilterList.fxml";

    @FXML
    private ListView<String> filterListView;

    /**
     * Creates a {@code FilterList} with the given {@code filterStringList}.
     */
    public FilterList(ObservableList<String> filterStringList) {
        super(FXML);
        filterListView.setItems(filterStringList);
        filterListView.setCellFactory(listView -> new FilterListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a filter using a {@code FilterCard}.
     */
    class FilterListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String filterString, boolean empty) {
            super.updateItem(filterString, empty);

            if (empty || filterString == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FilterCard(filterString).getRoot());
            }
        }
    }
}
