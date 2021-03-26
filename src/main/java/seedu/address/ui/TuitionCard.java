package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * An UI component that displays information of a {@code Tuition}.
 */
public class TuitionCard extends UiPart<Region> {

    private static final String FXML = "TuitionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox tuitionCardPane;
    @FXML
    private Label name;
    @FXML
    private ListView<Session> sessionListView;

    /**
     * Creates a {@code TuitionCard} with the given {@code Tuition} and index to display.
     */
    public TuitionCard(Student student) {
        super(FXML);
        this.student = student;
        name.setText(student.getName().fullName);

        ObservableList<Session> sessionList = FXCollections.observableList(student.getListOfSessions());

        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionCard)) {
            return false;
        }

        // state check
        TuitionCard card = (TuitionCard) other;
        return name.getText().equals(card.name.getText())
                && student.equals(card.student);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SessionCard}.
     */
    class SessionListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SessionCard(session, getIndex() + 1).getRoot());
            }
        }
    }
}
