package fooddiary.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import fooddiary.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Controller for a View page
 */
public class ViewWindow extends UiPart<Stage> {

    private static HashMap<String, String> entryDetails;

    private static final Logger logger = LogsCenter.getLogger(ViewWindow.class);
    private static final String FXML = "ViewWindow.fxml";

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label rating;
    @FXML
    private Label address;
    @FXML
    private Label review;
    @FXML
    private FlowPane tags;

    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the ViewWindow.
     */
    public ViewWindow(Stage root) {
        super(FXML, root);
        if (entryDetails != null) {
            setEntryContent(entryDetails);
        }
    }

    /**
     * Creates a new ViewWindow.
     */
    public ViewWindow() {
        this(new Stage());
    }

    /**
     * Shows the View window.
     *
     * @throws IllegalStateException <ul>
     *                                   <li>
     *                                       if this method is called on a thread other
     *                                       than the JavaFX Application Thread.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called during animation or layout processing.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called on the primary stage.
     *                                   </li>
     *                                   <li>
     *                                       if {@code dialogStage} is already showing.
     *                                   </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing view window of the specified entry.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the view window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the view window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the view window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Sets the content entry to be ready for view.
     *
     * @param entryDetails Entry details
     */
    public void setEntryContent(HashMap<String, String> entryDetails) {
        tags.getChildren().clear();
        name.setText(entryDetails.get("name") + "\n\n");
        rating.setText(String.format("Rating: %s / 5", entryDetails.get("rating")));
        address.setText(entryDetails.get("address") + "\n\n");
        review.setText(entryDetails.get("review") + "\n\n");
        Arrays.stream(entryDetails.get("tags").split(";"))
                .forEach(tag -> tags.getChildren().add(new Label(tag)));
    }
}
