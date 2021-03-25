package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;

/**
 * Controller for a help page
 */
public class EditorWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://github.com/AY2021S2-CS2103T-W12-1/"
            + "tp/blob/master/docs/UserGuide.md";
    public static final String HELP_MESSAGE = "Refer to the full user guide here: " + USERGUIDE_URL + "\n";
    public static final String COMMANDGUIDE_MESSAGE = AddCommand.MESSAGE_USAGE + "\n\n" + DeleteCommand.MESSAGE_USAGE
            + "\n\n" + ListCommand.MESSAGE_USAGE + "\n\n"
            + HelpCommand.MESSAGE_USAGE + "\n\n";

    private static final Logger logger = LogsCenter.getLogger(EditorWindow.class);
    private static final String FXML = "EditorWindow.fxml";
    private final String context;

    @FXML
    private Button returnButton;

    @FXML
    private TextArea textField;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public EditorWindow(Stage root, String context) {
        super(FXML, root);
        this.context = context;
    }

    /**
     * Creates a new HelpWindow.
     */
    public EditorWindow(String context) {
        this(new Stage(), context);
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

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        this.hide();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleReturn() {
        this.hide();
    }
}
