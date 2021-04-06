package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class PoliciesWindow extends UiPart<Stage> {

    private static final String NO_POLICY_FEEDBACK = "This contact has no policies now!";
    private static final String NOT_URL = "No URL!";
    private static final Logger logger = LogsCenter.getLogger(PoliciesWindow.class);
    private static final String FXML = "PoliciesWindow.fxml";
    private static final String COPY_URL_TEXT = "Copy URL!";

    @FXML
    private VBox outerBox;

    /**
     * Creates a new PoliciesWindow.
     *
     * @param root Stage to use as the root of the PoliciesWindow.
     */
    public PoliciesWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new PoliciesWindow.
     */
    public PoliciesWindow() {
        this(new Stage());
    }

    /**
     * Shows the policies window.
     *
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
        logger.fine("Showing policies, if any, associated with chosen contact.");
        getRoot().show();
    }

    /**
     * Returns true if the policies window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the policies window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the policies window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the policy to the clipboard.
     */
    @FXML
    private void copyUrl(String urlToCopy) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(urlToCopy);
        clipboard.setContent(url);
    }

    /**
     * Formats a policy window for a contact with no policies.
     *
     * @param noPolicyFeedback {@code String} to display indicating that contact has no policies.
     */
    public void noPolicyToDisplay(String noPolicyFeedback) {
        clearWindow();

        String[] nameAndFeedback = noPolicyFeedback.split(" has ");
        String name = nameAndFeedback[0];
        setWindowTitle(name + "'s Policies");

        HBox row = new HBox();
        setupHBoxRowNoButton(row, noPolicyFeedback);

        outerBox.getChildren().add(row);
        formatOuterBox();
    }

    /**
     * Formats and sets up policies and their URLs for display in window.
     *
     * @param policiesToDisplay joined {@code String} of all policies associated with the chosen contact.
     */
    public void setPoliciesToDisplay(String windowTitle, String policiesToDisplay) {
        clearWindow();
        setWindowTitle(windowTitle);

        String[] split = policiesToDisplay.split("\n");

        for (int i = 0; i < split.length; i++) {
            HBox row = new HBox(10);

            String[] policyNumAndUrl = split[i].split(": ", 2);
            final String possibleUrl = policyNumAndUrl[1];

            if (isUrl(possibleUrl)) {
                setupHBoxRowWithButton(row, split[i], possibleUrl);
            } else {
                setupHBoxRowNoButton(row, split[i]);
            }
            outerBox.getChildren().add(row);
        }
        formatOuterBox();
    }

    private void clearWindow() {
        outerBox.getChildren().clear();
    }

    private void setWindowTitle(String windowTitle) {
        getRoot().setTitle(windowTitle);
    }

    private void setupHBoxRowWithButton(HBox row, String rowText, String url) {
        Button rowButton = new Button(COPY_URL_TEXT);
        rowButton.setOnAction(e -> copyUrl(url));
        row.setAlignment(Pos.CENTER);
        row.getChildren().addAll(new Label(rowText), rowButton);
    }

    private void setupHBoxRowNoButton(HBox row, String rowText) {
        row.getChildren().add(new Label(rowText));
    }

    private void formatOuterBox() {
        outerBox.setSpacing(10);
        outerBox.setPadding(new Insets(25, 50, 25, 50));
    }

    private boolean isUrl(String possibleUrl) {
        return !possibleUrl.equals(NOT_URL);
    }
}
