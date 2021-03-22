package seedu.dictionote.ui;

import static seedu.dictionote.commons.core.Messages.STANDARD_DATE_TIME_DISPLAY_FORMAT;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dictionote.model.note.Note;

/**
 * An UI component that displays information of a {@code DisplayableContent}.
 */
public class NoteContentPanel extends UiPart<Region> implements NoteContentConfig {

    private static final String FXML = "NoteContentPanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */


    private Note note;

    @FXML
    private HBox contentPane;
    @FXML
    private TextArea notecontent;
    @FXML
    private Label create;
    @FXML
    private Label update;
    @FXML
    private Label done;
    @FXML
    private FlowPane tags;


    private DateTimeFormatter displayFormat;

    private boolean onEditMode;

    /**
     * Creates a {@code DictionaryContentPanel} for {@code DisplayableContent}.
     */
    public NoteContentPanel() {
        super(FXML);
        displayFormat = DateTimeFormatter.ofPattern(STANDARD_DATE_TIME_DISPLAY_FORMAT);
        onEditMode = false;
    }



    @Override
    public void setNote(Note note) {
        this.note = note;
        notecontent.setText(note.getNote());
        create.setText("Created: " + displayFormat.format(note.getCreateTime()));
        update.setText(" Updated: " + displayFormat.format(note.getLastEditTime()));
        tags.getChildren().clear();
        note.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (note.isDone()) {
            done.setText("✓");
        }
    }

    @Override
    public boolean haveNote() {
        return !(note == null);
    }

    @Override
    public void resetNote() {
        if(haveNote()) {
            setNote(note);
        }
    }

    @Override
    public String getEditedContent() {
        return haveNote() ? notecontent.getText() : "";
    }

    @Override
    public Note getNote() {
        return note;
    }

    @Override
    public boolean onEditMode() {
        return onEditMode;
    }
}
