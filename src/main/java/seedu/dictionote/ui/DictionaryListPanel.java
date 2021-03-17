package seedu.dictionote.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;

/**
 * Panel containing the list of persons.
 */
public class DictionaryListPanel extends UiPart<Region> implements DictionaryListPanelConfig {
    private static final String FXML = "DictionaryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DictionaryListPanel.class);


    @FXML
    private VBox vBox;

    @FXML
    private ListView<Content> dictionaryContentListView;

    @FXML
    private ListView<Definition> dictionaryDefinitionListView;
    /**
     * Creates a {@code DictionaryListPanel} with the given {@code ObservableList}.
     */
    public DictionaryListPanel(ObservableList<Content> contentList, ObservableList<Definition> definitionList) {
        super(FXML);

        dictionaryDefinitionListView.managedProperty().bind(dictionaryDefinitionListView.visibleProperty());
        dictionaryContentListView.managedProperty().bind(dictionaryContentListView.visibleProperty());

        dictionaryDefinitionListView.setItems(definitionList);
        dictionaryDefinitionListView.setCellFactory(listView -> new DefinitionListViewCell());

        dictionaryContentListView.setItems(contentList);
        dictionaryContentListView.setCellFactory(listView -> new ContentListViewCell());
    }

    /**
     * Display content list view
     */
    public void openContentDisplay() {
        if (!dictionaryContentListView.isVisible()) {
            dictionaryContentListView.setVisible(true);
        }
        if (dictionaryDefinitionListView.isVisible()) {
            dictionaryDefinitionListView.setVisible(false);
        }
    }

    /**
     * Display definition display
     */
    public void openDefinitionDisplay() {
        if (!dictionaryDefinitionListView.isVisible()) {
            dictionaryDefinitionListView.setVisible(true);
        }
        if (dictionaryContentListView.isVisible()) {
            dictionaryContentListView.setVisible(false);
        }
    }

    @Override
    public boolean isContentVisible() {
        return dictionaryContentListView.isVisible();
    }

    /**r
     * Custom {@code ListCell} that displays the graphics of a {@code Content}
     * using a {@code DictionaryContentCard}.
     */
    class ContentListViewCell extends ListCell<Content> {
        @Override
        protected void updateItem(Content content, boolean empty) {
            super.updateItem(content, empty);

            if (empty || content == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DictionaryListContentCard(content, getIndex() + 1).getRoot());
            }
            openContentDisplay();
        }
    }
    /**r
     * Custom {@code ListCell} that displays the graphics of a {@code Definition}
     * using a {@code DictionaryDefinitionCard}.
     */
    class DefinitionListViewCell extends ListCell<Definition> {
        @Override
        protected void updateItem(Definition definition, boolean empty) {
            super.updateItem(definition, empty);

            if (empty || definition == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DictionaryListDefinitionCard(definition, getIndex() + 1).getRoot());
            }
            openDefinitionDisplay();
        }
    }

}
