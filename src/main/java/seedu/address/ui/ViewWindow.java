package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Controller for a View page
 */
public class ViewWindow extends UiPart<Stage> {

    private static Person person;

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
        if (person != null) {
            setEntryContent(person);
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

    public void setEntryContent(Person person) {
        tags.getChildren().clear();
        name.setText(person.getName().fullName);
        rating.setText(String.format("Rating: %s / 5", person.getRating().value));
        address.setText(person.getAddress().value + "\n\n");
        review.setText(person.getReview().value + "\n\n");
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagCategory))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagCategory.titleCase())));

    }
}
