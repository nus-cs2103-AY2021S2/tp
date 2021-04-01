package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-w13-3.github.io/tp/UserGuide.html";
    public static final String HELP_LINK = "Refer to the full user guide here: ";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpTitle;

    @FXML
    private Label helpMessage;

    @FXML
    private Label helpLink;

    @FXML
    private Scene scene;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpLink.setText(HELP_LINK);
        // helpMessage.setText(HELP_MESSAGE);
        // logger.info("help message is: " + helpMsg);
        // helpMessage.setText(helpMsg);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow(String theme) {
        this(new Stage());
        updateHelpWindowTheme(theme);
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
     * Updates theme for helpWindow.
     */
    public void updateHelpWindowTheme(String theme) {
        scene.getStylesheets().clear();
        if (theme.equals("dark")) {
            scene.getStylesheets().add("view/DarkThemeHelpWindow.css");
        } else {
            scene.getStylesheets().add("view/HeliBookThemeHelpWindow.css");
        }
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
     * opens the URL to the user guide in the default user browser.
     */
    @FXML
    private void openUrl() throws URISyntaxException, IOException {
        URI userGuideUri = new URI(USERGUIDE_URL);
        Desktop.getDesktop().browse(userGuideUri);
    }


    public void setHelpText(String helpTitle, String helpMsg) {
        // logger.info("helpMsg: " + helpMsg);
        this.helpTitle.setText(helpTitle);
        this.helpMessage.setText(helpMsg);
    }
}
