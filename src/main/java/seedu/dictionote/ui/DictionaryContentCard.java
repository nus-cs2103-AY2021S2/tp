package seedu.dictionote.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.dictionote.model.dictionary.DisplayableContent;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DictionaryContentCard extends UiPart<Region> {

    private static final String FXML = "DictionaryContentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private DisplayableContent displayableContent;

    @FXML
    private Label header;
    @FXML
    private Label content;
    @FXML
    private Label week;

    /**
     * Creates a {@code DictionaryListContentCard} with the given {@code DisplayableContent} and index to display.
     */
    public DictionaryContentCard(DisplayableContent displayableContent) {
        super(FXML);
        this.displayableContent = displayableContent;
        header.setText(displayableContent.getDictionaryHeader());
        content.setText(displayableContent.getDictionaryContent());
        if (displayableContent.getDictionaryWeek() == null) {
            week.setVisible(false);
        } else {
            week.setText(displayableContent.getDictionaryWeek());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DictionaryListContentCard)) {
            return false;
        }

        // state check
        DictionaryListContentCard contents = (DictionaryListContentCard) other;
        return content.equals(contents.content);
    }
}
