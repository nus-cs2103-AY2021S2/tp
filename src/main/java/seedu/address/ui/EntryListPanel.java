package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entry.Entry;

/**
 * Panel containing the list of entries.
 */
public class EntryListPanel extends UiPart<Region> {
    private static final String FXML = "EntryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EntryListPanel.class);

    @FXML
    private ListView<Entry> entryListView;

    /**
     * Creates a {@code EntryListPanel} with the given {@code ObservableList}.
     */
    public EntryListPanel(ObservableList<Entry> entryList) {
        super(FXML);
        entryListView.setItems(entryList);
        entryListView.setCellFactory(listView -> new EntryListPanel.EntryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Entry} using a {@code EntryCard}.
     */
    class EntryListViewCell extends ListCell<Entry> {
        @Override
        protected void updateItem(Entry entry, boolean empty) {
            super.updateItem(entry, empty);

            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EntryCard(entry, getIndex() + 1).getRoot());
            }
        }
    }
}
