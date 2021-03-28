package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.ui.timetablepanel.TimeTablePanel;

/**
 * Controller for timetable viewing
 */
public class TimeTableWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(TimeTableWindow.class);
    private static final String FXML = "TimetableWindow.fxml";
    private TimeTablePanel timeTablePanel;

    @FXML
    private StackPane tabPanelPlaceholder;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public TimeTableWindow(Stage root, ObservableList<Event> events) {
        super(FXML, root);
        timeTablePanel = new TimeTablePanel(events);
        tabPanelPlaceholder.getChildren().add(timeTablePanel.getRoot());
    }

    /**
     * Creates a new HelpWindow.
     */
    public TimeTableWindow(ObservableList<Event> events) {
        this(new Stage(), events);
    }

    /**
     * Shows the help window.
     */
    public void show(ObservableList<Event> events) {
        logger.fine("Showing help page about the application.");
        timeTablePanel.reconstruct(events);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
