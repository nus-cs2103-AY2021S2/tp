package seedu.dictionote.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dictionote.model.dictionary.DisplayableContent;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DictionaryContentDisplay extends UiPart<Region> implements DictionaryContentConfig {

    private static final String FXML = "DictionaryContentDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final DictionaryListPanelConfig dictionaryListPanelConfig;

    private DisplayableContent displayableContent;

    @FXML
    private HBox displayPane;
    @FXML
    private Label header;
    @FXML
    private Label mainContent;
    @FXML
    private Label week;
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DictionaryContentDisplay(DictionaryListPanelConfig dictionaryListPanelConfig) {
        super(FXML);
        this.dictionaryListPanelConfig = dictionaryListPanelConfig;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DictionaryContentDisplay)) {
            return false;
        }

        // state check
        DictionaryContentDisplay display = (DictionaryContentDisplay) other;
        return dictionaryListPanelConfig.equals(display.dictionaryListPanelConfig);
    }

    @Override
    public void setDisplayContent(DisplayableContent displayableContent) {
        this.displayableContent = displayableContent;
        header.setText(displayableContent.getDictionaryHeader());
        mainContent.setText(displayableContent.getDictionaryContent());
        if (displayableContent.getDictionaryWeek() == null) {
            week.setVisible(false);
        } else {
            week.setText(displayableContent.getDictionaryWeek());
        }

    }

    @Override
    public boolean isContentVisible() {
        return dictionaryListPanelConfig.isContentVisible();
    }
}
