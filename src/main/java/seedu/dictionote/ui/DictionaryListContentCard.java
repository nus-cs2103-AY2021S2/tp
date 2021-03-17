package seedu.dictionote.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dictionote.model.dictionary.Content;

/**
 * An UI component that displays information of a {@code Content}.
 */
public class DictionaryListContentCard extends UiPart<Region> {

    private static final String FXML = "DictionaryListContentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Content content;
    @FXML
    private Label id;
    @FXML
    private HBox cardPane;
    @FXML
    private Label header;
    @FXML
    private Label mainContent;
    @FXML
    private Label week;

    /**
     * Creates a {@code DictionaryListContentCard} with the given {@code content} and index to display.
     */
    public DictionaryListContentCard(Content content, int displayedIndex) {
        super(FXML);
        this.content = content;
        id.setText(displayedIndex + ". ");
        header.setText(content.getHeader());
        mainContent.setText(content.getMainContent());
        week.setText(content.getWeek());
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
        return id.getText().equals(contents.id.getText())
                && content.equals(contents.content);
    }
}
