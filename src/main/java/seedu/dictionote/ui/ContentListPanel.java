package seedu.dictionote.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.model.dictionary.Content;

/**
 * Panel containing the list of persons.
 */
public class ContentListPanel extends UiPart<Region> {
    private static final String FXML = "ContentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ListView<Content> contentListView;

    /**
     * Creates a {@code ContentListPanel} with the given {@code ObservableList}.
     */
    public ContentListPanel(ObservableList<Content> contentList) {
        super(FXML);
        contentListView.setItems(contentList);
        contentListView.setCellFactory(listView -> new ContentListViewCell());
    }

    /**r
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ContentListViewCell extends ListCell<Content> {
        @Override
        protected void updateItem(Content content, boolean empty) {
            super.updateItem(content, empty);

            if (empty || content == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContentCard(content, getIndex() + 1).getRoot());
            }
        }
    }

}
