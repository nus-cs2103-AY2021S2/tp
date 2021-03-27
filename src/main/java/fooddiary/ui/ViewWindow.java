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
    private Label price;
    @FXML
    private Label rating;
    @FXML
    private Label address;
    @FXML
    private Label reviews;
    @FXML
    private FlowPane tagCategory;
    @FXML
    private FlowPane tagSchool;

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
        tagCategory.getChildren().clear();
        tagSchool.getChildren().clear();
        name.setText(entryDetails.get("name") + "\n\n");
        price.setText(String.format("Price: $%s", entryDetails.get("price")));
        rating.setText(String.format("Rating: %s / 5", entryDetails.get("rating")));
        address.setText(String.format("Address: %s\n", entryDetails.get("address")));
        reviews.setText(String.format("Reviews:\n%s\n\n", entryDetails.get("reviews")));
        Arrays.stream(entryDetails.get("categories").split(";"))
                .forEach(tag -> tagCategory.getChildren().add(new Label(tag)));
        Arrays.stream(entryDetails.get("schools").split(";"))
                .forEach(tag -> tagSchool.getChildren().add(new Label(tag)));
    }
}
