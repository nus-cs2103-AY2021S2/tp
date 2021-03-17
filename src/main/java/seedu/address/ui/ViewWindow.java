package seedu.address.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a View page
 */
public class ViewWindow extends UiPart<Stage> {

    private static HashMap<String, String> personDetails;

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
        if (personDetails != null) {
            setEntryContent(personDetails);
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
     * @param personDetails Entry details
     */
    public void setEntryContent(HashMap<String, String> personDetails) {
        tags.getChildren().clear();
        name.setText(personDetails.get("name") + "\n\n");
        rating.setText(String.format("Rating: %s / 5", personDetails.get("rating")));
        address.setText(personDetails.get("address") + "\n\n");
        review.setText(personDetails.get("review") + "\n\n");
        Arrays.stream(personDetails.get("tags").split(";"))
                .forEach(tag -> tags.getChildren().add(new Label(tag)));
    }
}
