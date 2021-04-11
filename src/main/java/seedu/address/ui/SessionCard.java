package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.session.Session;

public class SessionCard extends UiPart<Region> {
    private static final String FXML = "SessionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Session session;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label classId;
    @FXML
    private Label day;
    @FXML
    private Label timeslot;
    @FXML
    private Label subject;
    @FXML
    private Label tutor;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code SessionCode} with the given {@code Session} and index to display.
     */
    public SessionCard(Session session, int displayedIndex) {
        super(FXML);
        this.session = session;
        id.setText(displayedIndex + ". ");
        classId.setText(session.getClassId().toString());
        day.setText(session.getDay().toString());
        timeslot.setText(session.getTimeslot().toString());
        subject.setText(session.getSubject().toString());
        tutor.setText(session.getTutor().toString());
        session.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label currTag = new Label(tag.tagName);
                    currTag.setWrapText(true);
                    currTag.setMaxWidth(300);
                    tags.getChildren().add(currTag);
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SessionCard)) {
            return false;
        }

        // state check
        SessionCard card = (SessionCard) other;
        return id.getText().equals(card.id.getText())
                && session.equals(card.session);
    }
}
