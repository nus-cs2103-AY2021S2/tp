package seedu.address.ui.timetablepanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.ui.UiPart;

/**
 * Controller for timetable viewing
 */
public class TimeTableWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(TimeTableWindow.class);
    private static final String FXML = "timetablepanel/TimeTableWindow.fxml";
    private TimeTablePanel timeTablePanel;

    @FXML
    private StackPane timetablePanelPlaceholder;

    /**
     * Creates a new TimeTableWindow.
     *
     * @param root Stage to use as the root of the TimeTableWindow.
     */
    public TimeTableWindow(Stage root) {
        super(FXML, root);
        timeTablePanel = new TimeTablePanel();
        timetablePanelPlaceholder.getChildren().add(timeTablePanel.getRoot());
    }

    /**
     * Creates a new TimeTableWindow.
     */
    public TimeTableWindow() {
        this(new Stage());
    }

    /**
     * Shows the timetable window.
     */
    public void show(ObservableList<Event> events) {
        logger.fine("Showing timetable to user.");
        timeTablePanel.reconstruct(events);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the TimeTableWindow is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the TimeTableWindow.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the TimeTableWindow.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
