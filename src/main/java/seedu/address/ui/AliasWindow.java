package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyUniqueAliasMap;

/**
 * Controller for an alias page
 */
public class AliasWindow extends UiPart<Stage> {

    private static final String HEADER = "Your aliases:";

    private static final Logger logger = LogsCenter.getLogger(AliasWindow.class);
    private static final String FXML = "AliasWindow.fxml";

    @FXML
    private Label aliasText;
    @FXML
    private Label header;

    private final ReadOnlyUniqueAliasMap aliases;

    /**
     * Creates a new AliasWindow.
     *
     * @param root Stage to use as the root of the AliasWindow.
     */
    public AliasWindow(Stage root, ReadOnlyUniqueAliasMap aliases) {
        super(FXML, root);
        this.aliases = aliases;
        header.setText(HEADER);
        updateAliases();
    }

    /**
     * Creates a new AliasWindow.
     */
    public AliasWindow(ReadOnlyUniqueAliasMap aliases) {
        this(new Stage(), aliases);
    }

    /**
     * Shows the alias window.
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
        logger.fine("Showing alias page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the alias window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the alias window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the alias window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Updates aliases in alias window.
     */
    public void updateAliases() {
        aliasText.setText(aliases.toString());
        getRoot().sizeToScene();
    }

}
