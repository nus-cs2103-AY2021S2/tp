package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class CalendarWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(CalendarWindow.class);
    private static final String FXML = "CalendarWindow.fxml";

    /**
     * Creates a new CalendarWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CalendarWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new CalendarWindow.
     */
    public CalendarWindow() {
        this(new Stage());
    }

    /**
     * Shows the calendar window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing calendar page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the calendar window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the calendar window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the calendar window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}
