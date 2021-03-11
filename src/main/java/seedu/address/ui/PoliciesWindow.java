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
     * Formats and sets up policies and their URLs for display in window.
     *
     * @param policiesToDisplay joined {@code String} of all policies associated with the chosen contact.
     */
    public void setPoliciesToDisplay(String policiesToDisplay) {
        outerBox.getChildren().clear();
        String[] split = policiesToDisplay.split("\n");
        if (hasPoliciesToDisplay(split)) {
            for (int i = 0; i < split.length; i++) {
                HBox row = new HBox(10);

                String[] policyNumAndUrl = split[i].split(": ", 2);
                final String policyNum = policyNumAndUrl[0];
                final String possibleUrl = policyNumAndUrl[1];

                if (isUrl(possibleUrl)) {
                    Button rowButton = new Button("Copy URL!");
                    rowButton.setOnAction(e -> copyUrl(possibleUrl));
                    row.setAlignment(Pos.CENTER);
                    // Hyperlink link = new Hyperlink(possibleUrl);
                    // link.setText(split[i]);
                    row.getChildren().addAll(new Label(split[i]), rowButton);
                } else {
                    row.getChildren().addAll(new Label(split[i]));
                }

                outerBox.getChildren().add(row);
            }
            outerBox.setSpacing(5);
        } else {
            HBox row = new HBox();
            row.getChildren().add(new Label(split[0]));
            outerBox.getChildren().add(row);
        }
        outerBox.setPadding(new Insets(25, 50, 25, 50));
    }

    private boolean hasPoliciesToDisplay(String[] split) {
        return !split[0].equals(NO_POLICY_FEEDBACK);
    }

    private boolean isUrl(String possibleUrl) {
        return !possibleUrl.equals(NOT_URL);
    }
}
