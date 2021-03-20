package seedu.us.among.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.us.among.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-t12-4.github.io/tp/";
    public static final String APIINTRO_URL = "https://www.youtube.com/watch?v=-MTSQjw5DrM";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;
    public static final String HELP_API_MESSAGE = "See a quick introduction to RESTful APIs: " + APIINTRO_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label helpApiMessage;

    @FXML
    private TableView tableView;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        helpApiMessage.setText(HELP_API_MESSAGE);
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
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> {
                getRoot().show();
                getRoot().centerOnScreen();
            });
        } else {
            getRoot().show();
            getRoot().centerOnScreen();
        }
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
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> getRoot().hide());
        } else {
            getRoot().hide();
        }
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> getRoot().requestFocus());
        } else {
            getRoot().requestFocus();
        }
    }

    /**
     * Copies the selected URL to the clipboard.
     */
    private void copyUrl(String defaultUrl) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(defaultUrl);
        clipboard.setContent(url);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUserGuideUrl() {
        copyUrl(USERGUIDE_URL);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyApiGuideUrl() {
        copyUrl(APIINTRO_URL);
    }
}
