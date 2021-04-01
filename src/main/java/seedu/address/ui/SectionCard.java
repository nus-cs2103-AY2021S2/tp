package seedu.address.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.model.medical.Section;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SectionCard extends UiPart<Region> {

    private static final String FXML = "SectionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Section section;

    @FXML
    private TextField sectionTitle;
    @FXML
    private TextArea sectionBody;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SectionCard(Section newSection, boolean editable) {
        super(FXML);
        this.section = newSection;
        sectionTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                section.setTitle(newValue);
            }
        });
        sectionBody.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                int amtContent = newValue.length() / 80;
                int numLines = newValue.length()
                        - newValue.replace("\n", "").length();
                sectionBody.setPrefRowCount(numLines + amtContent + 1);
                section.setBody(newValue);
            }
        });
        sectionTitle.setText(newSection.getTitle());
        sectionBody.setText(newSection.getBody());
        if (!editable) {
            sectionTitle.setEditable(false);
            sectionBody.setEditable(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SectionCard)) {
            return false;
        }

        // state check
        SectionCard card = (SectionCard) other;
        return sectionTitle.getText().equals(card.sectionTitle.getText())
                && sectionBody.getText().equals(card.sectionBody.getText());
    }
}
