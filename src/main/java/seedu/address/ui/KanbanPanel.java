package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;

import java.util.logging.Logger;

/**
 * Panel containing the list of bugs.
 */
public class KanbanPanel extends UiPart<Region> {
    private static final String FXML = "KanbanPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPaneKanbanView.class);
    private final Logic logic;

    @FXML
    private VBox backlogColumn;

    @FXML
    private VBox todoColumn;

    @FXML
    private VBox inProgressColumn;

    @FXML
    private VBox doneColumn;


    /**
     * Creates a {@code BugListPanel} with the given {@code ObservableList}.
     */
    public KanbanPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillKanban();
    }

    private void fillKanban() {
        fillColumn(backlogColumn, EventStatus.BACKLOG, EventStatus.STRING_BACKLOG);

        fillColumn(todoColumn, EventStatus.TODO, EventStatus.STRING_TODO);

        fillColumn(inProgressColumn, EventStatus.IN_PROGRESS, EventStatus.STRING_IN_PROGRESS);

        fillColumn(doneColumn, EventStatus.DONE, EventStatus.STRING_DONE);
    }

    private void fillColumn(VBox column, EventStatus status, String title) {
        EventListPaneKanbanView eventListPanel = new EventListPaneKanbanView(logic.getFilteredListByStatus(status));
        VBox.setVgrow(eventListPanel.getRoot(), Priority.ALWAYS);

        Label label = new Label(title + String.format(" (%d)", eventListPanel.getNumberOfTasks()));
        label.getStyleClass().add("status");

        column.getChildren().addAll(label, eventListPanel.getRoot());
    }

}

