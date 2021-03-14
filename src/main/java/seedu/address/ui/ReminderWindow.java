package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;

/**
 * The Reminder Window. Provides the user schedule within a week.
 */
public class ReminderWindow extends UiPart<Stage> {

    private static final String FXML = "ReminderWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(ReminderWindow.class);


    /**
     * Creates a new ReminderWindow.
     */
    public ReminderWindow() {
        super(FXML);
        setWindowDefaultSize(new GuiSettings());
    }

    void show() {
        getRoot().show();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        this.getRoot().setHeight(guiSettings.getWindowHeight());
        this.getRoot().setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            this.getRoot().setX(guiSettings.getWindowCoordinates().getX());
            this.getRoot().setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * This method closes the reminder window.
     */
    @FXML
    private void close() {
        getRoot().hide();
    }

}
