package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;

/**
 * An UI component that displays information of a {@Student} and a list the {@code Session} of that {@code Student}.
 */
public class TuitionCard extends UiPart<Region> {
    // TODO: Some Ui to differentiate recurring sessions from others.

    private static final String FXML = "TuitionCard.fxml";
    private static final int ROW_HEIGHT = 98;

    public final Student student;

    @FXML
    private VBox tuitionCardPane;
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

        populateSessionListView(sessionList);

        // hard code height to prevent list from being scrollable
        sessionListView.setPrefHeight(sessionList.size() * ROW_HEIGHT);
    }

    /**
     * Populates the sessionListView with all {@code Session} of the given {@Student}.
     */
    private void populateSessionListView(ObservableList<Session> sessionList) {
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
