package seedu.dictionote.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.dictionote.model.dictionary.DisplayableContent;

/**
 * An UI component that displays information of a {@code DisplayableContent}.
 */
public class DictionaryContentPanel extends UiPart<Region> implements DictionaryContentConfig {

    private static final String FXML = "DictionaryContentPanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */


    private DictionaryListPanelConfig dictionaryListPanelConfig;

    private ObservableList<DisplayableContent> contentList;

    @FXML
    private ListView<DisplayableContent> dictionaryContentListView;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DictionaryContentPanel(DictionaryListPanel dictionaryListPanelConfig) {
        super(FXML);
        this.dictionaryListPanelConfig = dictionaryListPanelConfig;
        this.contentList = FXCollections.observableArrayList();

        dictionaryContentListView.setItems(contentList);
        dictionaryContentListView.setCellFactory(listView -> new DictionaryContentListViewCell());
    }

    @Override
    public void setDisplayContent(DisplayableContent displayableContent) {
        contentList.clear();
        contentList.add(displayableContent);
    }

    @Override
    public boolean isContentVisible() {
        return dictionaryListPanelConfig.isContentVisible();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code DisplayableContent} using a
     * {@code DictionaryContentCard}.
     */
    class DictionaryContentListViewCell extends ListCell<DisplayableContent> {
        @Override
        protected void updateItem(DisplayableContent displayableContent, boolean empty) {
            super.updateItem(displayableContent, empty);

            if (empty || displayableContent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DictionaryContentCard(displayableContent).getRoot());
            }
        }
    }
}
