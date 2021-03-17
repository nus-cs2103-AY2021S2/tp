package seedu.dictionote.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dictionote.model.dictionary.Definition;

/**
 * An UI component that displays information of a {@code Definition}.
 */
public class DictionaryListDefinitionCard extends UiPart<Region> {

    private static final String FXML = "DictionaryListDefinitionCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Definition definition;
    @FXML
    private Label id;
    @FXML
    private HBox cardPane;
    @FXML
    private Label header;
    @FXML
    private Label content;

    /**
     * Creates a {@code NoteCard} with the given {@code Note} and index to display.
     */
    public DictionaryListDefinitionCard(Definition definition, int displayedIndex) {
        super(FXML);
        this.definition = definition;
        id.setText(displayedIndex + ". ");
        header.setText(definition.getTerm());
        content.setText(definition.getDefs());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DictionaryListDefinitionCard)) {
            return false;
        }

        // state check
        DictionaryListDefinitionCard contents = (DictionaryListDefinitionCard) other;
        return id.getText().equals(contents.id.getText())
                && definition.equals(contents.definition);
    }
}
