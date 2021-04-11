package seedu.student.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.student.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String DIVIDER = "\n -------------------------------------------------------------------------"
            + "----------------------------------------------------";

    public static final String COMMAND_SUMMARY = " Here is a list of commonly used commands in Vax@NUS" + DIVIDER
            + "\n Tips: Words in capital are compulsory while the words in square bracket are optional."
            + "\n\n Add a student: add n/NAME i/MATRICULATION_NUMBER f/FACULTY p/PHONE_NUMBER e/EMAIL"
            + "\n                        a/ADDRESS s/VACCINATION_STATUS m/MEDICAL_DETAILS [r/SCHOOL_RESIDENCE] "
            + "\n\n Edit a student: edit INDEX [n/NAME] [f/FACULTY] [p/PHONE] [e/EMAIL] "
            + "\n                       [a/ADDRESS] [s/VACCINATION_STATUS] [m/MEDICAL_DETAILS] [r/SCHOOL_RESIDENCE]"
            + "\n\n Delete a student: delete MATRICULATION_NUMBER"
            + "\n\n Add an appointment: addAppt MATRICULATION_NUMBER d/DATE ts/START_TIME"
            + "\n\n Edit an appointment: editAppt MATRICULATION_NUMBER d/DATE ts/START_TIME"
            + "\n\n Delete an appointment: deleteAppt MATRICULATION_NUMBER"
            + "\n\n Find a student and appointment: find MATRICULATION_NUMBER"
            + "\n\n List all students and appointments : list"
            + "\n\n Exit Program: exit";

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-w10-4.github.io/tp/UserGuide.html";

    public static final String HELP_MESSAGE = DIVIDER + "\n" + "Do refer to our user guide: " + USERGUIDE_URL
                                                + " for more information.";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(COMMAND_SUMMARY + HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing help page about the application.");
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

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
