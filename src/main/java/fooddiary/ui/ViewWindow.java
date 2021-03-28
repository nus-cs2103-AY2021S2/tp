package fooddiary.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fooddiary.commons.core.LogsCenter;
import fooddiary.logic.commands.CommandResult;
import fooddiary.model.entry.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Controller for a View page
 */
public class ViewWindow extends UiPart<Stage> {

    private static Entry entry;

    private static final Logger logger = LogsCenter.getLogger(ViewWindow.class);
    private static final String FXML = "ViewWindow.fxml";

    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label rating;
    @FXML
    private Label address;
    @FXML
    private Label reviews;
    @FXML
    private Label tagCategoryLabel;
    @FXML
    private Label tagCategorySchool;
    @FXML
    private TextField nameText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField ratingText;
    @FXML
    private TextField addressText;
    @FXML
    private TextArea reviewsText;
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
        if (entry != null) {
            setEntryContent(entry);
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
     * @param entry Entry details
     */
    public void setEntryContent(Entry entry) {
        tagCategory.getChildren().clear();
        tagSchool.getChildren().clear();
        nameText.setText(entry.getName().fullName);
        priceText.setText(String.format("$%s", entry.getPrice().value));
        ratingText.setText(String.format("%s / 5", entry.getRating().value));
        addressText.setText(entry.getAddress().value);
        String reviewsStr = entry.getReviews().stream()
                .map(review -> review.value + "\n\n")
                .collect(Collectors.joining());
        reviewsText.setText(reviewsStr);
        entry.getTagCategories().stream()
                .sorted(Comparator.comparing(tag -> tag.getTag()))
                .forEach(tag -> tagCategory.getChildren().add(new Label(tag.getTag())));
        entry.getTagSchools().stream()
                .sorted(Comparator.comparing(tag -> tag.getTag()))
                .forEach(tag -> tagSchool.getChildren().add(new Label(tag.getTag())));
    }
}
