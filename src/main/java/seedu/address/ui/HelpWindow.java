package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://github.com/AY2021S2-CS2103-T16-3/"
                                             + "tp/blob/master/docs/UserGuide.md";

    public static final String ADD_PROMPT = "add - Adds a residence into ResidenceTracker."
                                          + "\nEnter \"add n/NAME_OF_RESIDENCE a/ADDRESS [b/BOOKING_DETAILS] "
                                          + "[clean/[y or n]] [t/TAG]...\""
                                          + "\nExample: add n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 "
                                          + "b/4 adults clean/n t/Reserved\n\n";

    public static final String CLEAR_PROMPT = "clear - Clears all entries from the residence tracker."
                                            + "\nEnter \"clear\"\n\n";

    public static final String DELETE_PROMPT = "delete - Deletes the specified residence from the list of residences."
                                             + "based on index."
                                             + "\nEnter \"delete INDEX\""
                                             + "\nExample: delete 3\n\n";

    public static final String EDIT_PROMPT = "edit - Edits fields of an existing residence (other than bookings)."
                                           + "\nEnter \"edit INDEX c/n \""
                                           + "\nExample: edit 2 c/n\n\n";

    public static final String FIND_PROMPT = "find - Finds residences whose name contains the given keyword."
                                           + "\nEnter \"find KEYWORDS\""
                                           + "\nExample: find Heights Condo\n\n";

    public static final String LIST_PROMPT = "list - Shows a list of all residences in ResidenceTracker."
                                           + "\n Enter \"list\"\n\n";

    public static final String EXIT_PROMPT = "exit - Exits the program."
                                           + "\n Enter \"exit\"\n\n";

    public static final String HELP_MESSAGE = "List of commands:\n"
                                            + ADD_PROMPT
                                            + CLEAR_PROMPT
                                            + DELETE_PROMPT
                                            + EDIT_PROMPT
                                            + FIND_PROMPT
                                            + LIST_PROMPT
                                            + EXIT_PROMPT;

    public static final String URL_MESSAGE = "For more info, refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label urlMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        urlMessage.setText(URL_MESSAGE);
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
