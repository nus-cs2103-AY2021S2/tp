package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;

/**
 * Controller for a help page
 */
public class HelpPanel extends UiPart<Region> {
    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-t11-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;
    public static final String URL_COPIED = "URL has been copied.";

    private static final String FXML = "HelpPanel.fxml";

    private MainWindow mainWindow;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpPanel.
     */
    public HelpPanel(MainWindow mainWindow) {
        super(FXML);
        helpMessage.setText(HELP_MESSAGE);
        this.mainWindow = mainWindow;
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
        mainWindow.setFeedbackToUser(URL_COPIED);
    }
}
