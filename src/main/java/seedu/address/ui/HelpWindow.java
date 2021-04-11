package seedu.address.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-t12-3.github.io/tp/UserGuide.html";
    public static final String USERGUIDE_MSG = "Refer to the user guide: ";
    public static final String READ_FAIL_MSG = "Error getting local help, please view the user guide in the URL below.";
    private static String commandSummary;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label helpMessage;

    @FXML
    private Button copyButton;

    @FXML
    private Label urlMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        initializeCommandSummary();
        helpMessage.setText(commandSummary);
        urlMessage.setText(USERGUIDE_MSG);
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

    /**
     * Reads the command summary from UserGuide and populate the help message with the read content
     */
    private void initializeCommandSummary() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass()
                    .getResourceAsStream("/UserGuide.md")));

            String currentLine = reader.readLine();

            // Keeps reading the file until Command Summary is reached
            while (currentLine != null && !currentLine.equals("## Command summary")) {
                currentLine = reader.readLine();
            }

            // keeps reading and parsing until the next section is reached
            StringBuilder builder = new StringBuilder();

            while (currentLine != null && !currentLine.contains("\\##")) {
                builder.append(parseCommandSummary(currentLine));
                currentLine = reader.readLine();
            }

            commandSummary = builder.toString();

        } catch (IOException e) {
            commandSummary = READ_FAIL_MSG;
            logger.info("Error reading file: " + e);
        } catch (NullPointerException e) {
            commandSummary = READ_FAIL_MSG;
            logger.info("Help file does not exist: " + e);
        }

    }

    private String parseCommandSummary(String input) {
        // ignore input that is not bolded, and therefore not a command (command is bolded)
        if (!input.contains("**")) {
            return "";
        }

        input = input.substring(1, input.length() - 1); // remove the left and right table borders

        StringBuilder builder = new StringBuilder();
        String[] tokenized = input.split("\\|", 2); // split into command and its description

        // exclude html bold formatting (**), and code formatting (`)
        String name = tokenized[0].replace("**", "")
                .replace("`", "");

        // exclude examples (which comes after <br>), and code formatting (`)
        String desc = tokenized[1].split("<br>")[0]
                .replace("`", "").trim();

        builder.append(name).append(" | ").append(desc).append("\n");
        return builder.toString();
    }
}
