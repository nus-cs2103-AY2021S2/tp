package fooddiary.ui;

import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fooddiary.commons.core.LogsCenter;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.logic.parser.CliSyntax;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private static MainWindow mainWindow;

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
    @FXML
    private Button reviseButton;

    /**
     * Creates a new ViewWindow.
     *
     * @param root Stage to use as the root of the ViewWindow.
     */
    public ReviseWindow(Stage root) {
        super(FXML, root);
        if (entry != null && index != null) {
            setEntryContent(entry, index, mainWindow);
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
    public void setEntryContent(Entry entry, Index index, MainWindow mainWindow) {
        this.index = index;
        this.mainWindow = mainWindow;
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
    }

    /**
     * Submits revision to edit command for update.
     *
     * @throws CommandException Invalid command given
     * @throws ParseException Parsing formatting error occurs
     */
    @FXML
    private void revise() throws CommandException, ParseException {
        String name = String.format("%s%s", CliSyntax.PREFIX_NAME.toString(), nameText.getText());
        String rating = String.format("%s%s", CliSyntax.PREFIX_RATING.toString(), ratingText.getText());
        String price = String.format("%s%s", CliSyntax.PREFIX_PRICE.toString(), priceText.getText());

        String[] reviewsArr = reviewsText.getText().split("\\r?\\n\n|\\r\r");
        String reviewsStr = "";
        for (String review : reviewsArr) {
            reviewsStr += String.format(" %s%s", CliSyntax.PREFIX_REVIEW, review);
        }

        String[] categoriesArr = tagCategoryText.getText().split(" ");
        String categoriesStr = "";
        for (String category : categoriesArr) {
            categoriesStr += String.format(" %s%s", CliSyntax.PREFIX_TAG_CATEGORY, category);
        }

        String[] schoolsArr = tagSchoolText.getText().split(" ");
        String schoolsStr = "";
        for (String school : schoolsArr) {
            schoolsStr += String.format(" %s%s", CliSyntax.PREFIX_TAG_SCHOOL, school);
        }

        String commandToSend = String.format("%s %d %s %s %s %s%s%s", EditCommand.COMMAND_WORD, index.getOneBased(),
                name, rating, price, reviewsStr, categoriesStr, schoolsStr);
        try {
            mainWindow.executeCommand(commandToSend);
        } catch (CommandException | ParseException e) {
            this.hide();
        }
    }

}
