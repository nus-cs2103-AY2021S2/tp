package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for the Important Dates page
 */
public class ImportantDatesWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ImportantDatesWindow.class);
    private static final String FXML = "ImportantDatesWindow.fxml";
    private Logic logic;
    private DateListPanel dateListPanel;


    @FXML
    private StackPane dateListPanelPlaceholder;

    /**
     * Creates a new ImportantDatesWindow.
     *
     * @param root Stage to use as the root of the ImportantDatesWindow.
     */
    public ImportantDatesWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
    }

    /**
     * Creates a new ImportantDatesWindow.
     */
    public ImportantDatesWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the important dates window.
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
        logger.fine("Showing important dates window.");
        dateListPanel = new DateListPanel(logic.getTransformedImportantDatesList());
        dateListPanelPlaceholder.getChildren().add(dateListPanel.getRoot());
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the important dates window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the important dates window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the important dates window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
