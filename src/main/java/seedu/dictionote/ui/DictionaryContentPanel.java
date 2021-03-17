package seedu.dictionote.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Creates a {@code DictionaryContentPanel} for {@code DisplayableContent}.
     */
    public DictionaryContentPanel(DictionaryListPanel dictionaryListPanelConfig) {
        super(FXML);
        this.dictionaryListPanelConfig = dictionaryListPanelConfig;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @Override
    public void setDisplayContent(DisplayableContent displayableContent) {
        dialogContainer.getChildren().clear();
        dialogContainer.getChildren().add(new DictionaryContentCard(displayableContent).getRoot());
    }

    @Override
    public boolean isContentVisible() {
        return dictionaryListPanelConfig.isContentVisible();
    }
}
