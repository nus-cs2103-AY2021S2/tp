package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class ShortcutsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ShortcutsWindow.class);
    private static final String FXML = "ShortcutsWindow.fxml";

    @FXML
    private VBox outerBox;

    /**
     * Creates a new ShortcutsWindow.
     *
     * @param root Stage to use as the root of the ShortcutsWindow.
     */
    public ShortcutsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new ShortcutsWindow.
     */
    public ShortcutsWindow() {
        this(new Stage());
    }

    /**
     * Shows the shortcuts window.
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
        logger.fine("Showing shortcuts, if any.");
        getRoot().show();
    }

    /**
     * Returns true if the shortcuts window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the shortcuts window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the shortcuts window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Formats a shortcut window when there are no shortcuts.
     *
     * @param noShortcutFeedback {@code String} to display indicating there are no shortcuts.
     */
    public void noShortcutToDisplay(String noShortcutFeedback) {
        clearWindow();

        setWindowTitle("Shortcuts");

        HBox row = new HBox();
        setupHBoxRowNoButton(row, noShortcutFeedback);

        outerBox.getChildren().add(row);
        formatOuterBox();
    }

    /**
     * Formats and sets up shortcuts for display in window.
     *
     * @param shortcutsToDisplay joined {@code String} of all shortcuts.
     */
    public void setShortcutsToDisplay(String windowTitle, String shortcutsToDisplay) {
        clearWindow();
        setWindowTitle(windowTitle);

        String[] split = shortcutsToDisplay.split("\n");

        for (int i = 0; i < split.length; i++) {
            HBox row = new HBox(10);
            setupHBoxRowNoButton(row, split[i]);
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

    private void setupHBoxRowNoButton(HBox row, String rowText) {
        row.getChildren().add(new Label(rowText));
    }

    private void formatOuterBox() {
        outerBox.setSpacing(10);
        outerBox.setPadding(new Insets(25, 50, 25, 50));
        outerBox.setMinWidth(200);
        outerBox.autosize();
    }

}
