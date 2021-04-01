package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.session.Session;

public class SessionListPanel extends UiPart<Region> {
    private static final String FXML = "SessionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @javafx.fxml.FXML
    private ListView<Session> sessionListView;

    /**
     * Creates a {@code sessionListPanel} with the given {@code ObservableList}.
     */
    public SessionListPanel(ObservableList<Session> sessionList) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListPanel.SessionListViewCell());
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
