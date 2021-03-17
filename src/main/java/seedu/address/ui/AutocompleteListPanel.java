package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import com.sun.javafx.collections.ImmutableObservableList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

public class AutocompleteListPanel extends UiPart<Region> {

    private static final String FXML = "AutocompleteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AutocompleteListPanel.class);

    @FXML
    private ListView<String> autocompleteListView;

    /**
     * Creates a {@code AutocompleteListPanel} with an empty {@code ImmutableObservableList}.
     */
    public AutocompleteListPanel() {
        super(FXML);
        autocompleteListView.setItems(new ImmutableObservableList<>());
        autocompleteListView.setCellFactory(listView -> new AutocompleteListViewCell());
    }

    public void updateList(ObservableList<String> list) {
        autocompleteListView.setItems(FXCollections.observableList(list));
    }

    /**
     * Selects the previous item in the ListView and returns the result.
     *
     * @param callback accept value for demo purposes.
     */
    public void doTab(Consumer<String> callback) {
        int selectedIndex = autocompleteListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            selectedIndex = 0;
        } else {
            selectedIndex = (selectedIndex + 1) % autocompleteListView.getItems().size();
        }
        autocompleteListView.getSelectionModel().select(selectedIndex);
        autocompleteListView.scrollTo(selectedIndex);
        String value = autocompleteListView.getSelectionModel().getSelectedItem();
        callback.accept(value);
    }

    /**
     * Custom {@code ListCell} that displays the command text using a {@code AutocompleteListCell}.
     */
    class AutocompleteListViewCell extends ListCell<String> {

        @Override
        protected void updateItem(String value, boolean empty) {
            super.updateItem(value, empty);

            if (empty || value.isEmpty()) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AutocompleteListCell(value).getRoot());
            }
        }
    }
}
