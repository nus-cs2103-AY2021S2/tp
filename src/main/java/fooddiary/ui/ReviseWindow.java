package fooddiary.ui;

import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fooddiary.commons.core.LogsCenter;
import fooddiary.commons.core.index.Index;
import fooddiary.model.entry.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for a Revise page
 */
public class ReviseWindow extends UiPart<Stage> {
    private static Entry entry;
    private static Index index;

    private static final Logger logger = LogsCenter.getLogger(ReviseWindow.class);
    private static final String FXML = "ReviseWindow.fxml";

    @javafx.fxml.FXML
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
    private TextField tagCategoryText;
    @FXML
    private TextField tagSchoolText;

    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the ViewWindow.
     */
    public ReviseWindow(Stage root) {
        super(FXML, root);
        if (entry != null && index != null) {
            setEntryContent(entry, index);
        }
    }

    /**
     * Creates a new ReviseWindow.
     */
    public ReviseWindow() {
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
        logger.fine("Showing revise window of the specified entry.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the revise window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the revise window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the revise window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Sets the content entry to be ready for revision.
     *
     * @param entry entry
     * @param index index of entry
     */
    public void setEntryContent(Entry entry, Index index) {
        nameText.setText(entry.getName().fullName);
        priceText.setText(String.format("%s", entry.getPrice().value));
        ratingText.setText(String.format("%s", entry.getRating().value));
        addressText.setText(entry.getAddress().value);
        String reviewsStr = entry.getReviews().stream()
                .map(review -> review.value + "\n\n")
                .collect(Collectors.joining());
        reviewsText.setText(reviewsStr);
        String tagCategories = entry.getTagCategories().stream()
                .sorted(Comparator.comparing(tag -> tag.getTag()))
                .map(tag -> tag.getTag() + " ")
                .collect(Collectors.joining());
        String tagSchools = entry.getTagSchools().stream()
                .sorted(Comparator.comparing(tag -> tag.getTag()))
                .map(tag -> tag.getTag() + " ")
                .collect(Collectors.joining());
        tagCategoryText.setText(tagCategories);
        tagSchoolText.setText(tagSchools);
        this.index = index;
    }


}
