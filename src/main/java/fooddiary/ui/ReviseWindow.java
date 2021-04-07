package fooddiary.ui;

import java.awt.AWTException;
import java.awt.Robot;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
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
    private Label categoriesLabel;
    @FXML
    private Label schoolsLabel;
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
    private TextField categoriesText;
    @FXML
    private TextField schoolsText;
    @FXML
    private Button reviseButton;

    /**
     * Creates a new ReviseWindow.
     *
     * @param root Stage to use as the root of the ReviseWindow.
     */
    public ReviseWindow(Stage root) {
        super(FXML, root);
        final KeyCombination activateRevise = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
        setReviseShortCut(activateRevise, reviseButton);
        final KeyCombination tabOverReviews = new KeyCodeCombination(KeyCode.TAB);
        setTabOverReviewsShortCut(tabOverReviews);
        final KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);
        setEscShortCut(esc);
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
     * Shows the Revise window.
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
        nameText.requestFocus();
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
     * Sets up 'Ctrl + S' (Windows) or 'Command + S' (MAC) to complete revision
     *
     * @param keyCombination 'Ctrl + S' (Windows) or 'Command + S' (MAC)
     * @param reviseButton Revise button to be activated
     */
    private void setReviseShortCut(KeyCombination keyCombination, Button reviseButton) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                reviseButton.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Sets up 'TAB' key to tab over Reviews TextArea
     *
     * @param tabOverReviews 'TAB' on keyboard
     */
    private void setTabOverReviewsShortCut(KeyCombination tabOverReviews) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && tabOverReviews.match(event)) {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyCode.CONTROL.getCode());
                    robot.keyPress(KeyCode.TAB.getCode());
                    robot.keyRelease(KeyCode.TAB.getCode());
                    robot.keyRelease(KeyCode.CONTROL.getCode());
                } catch (AWTException e) {
                    return;
                }
                event.consume();
            }
        });
    }

    /**
     * Sets up 'ESC' key to hide window
     *
     * @param esc 'ESC' on keyboard
     */
    private void setEscShortCut(KeyCombination esc) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (esc.match(event)) {
                hide();
                event.consume();
            }
        });
    }

    /**
     * Sets the content entry to be ready for revision.
     *
     * @param entry entry
     * @param index index of entry
     * @param mainWindow main ui window
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

        String categoriesStr = entry.getTagCategories().stream()
                .sorted(Comparator.comparing(tag -> tag.getTag()))
                .map(tag -> tag.getTag() + " ")
                .collect(Collectors.joining());

        String schoolsStr = entry.getTagSchools().stream()
                .sorted(Comparator.comparing(tag -> tag.getTag()))
                .map(tag -> tag.getTag() + " ")
                .collect(Collectors.joining());

        categoriesText.setText(categoriesStr);
        schoolsText.setText(schoolsStr);
    }

    /**
     * Submits revision to edit command for update.
     *
     * @throws CommandException if invalid command given
     * @throws ParseException if parsing formatting error occurs
     */
    @FXML
    private void revise() throws CommandException, ParseException {
        String name = String.format("%s%s", CliSyntax.PREFIX_NAME.toString(), nameText.getText());
        String rating = String.format("%s%s", CliSyntax.PREFIX_RATING.toString(), ratingText.getText());
        String price = String.format("%s%s", CliSyntax.PREFIX_PRICE.toString(), priceText.getText());
        String address = String.format("%s%s", CliSyntax.PREFIX_ADDRESS.toString(), addressText.getText());

        String[] reviewsArr = reviewsText.getText().split("\\r?\\n|\\r");
        String reviewsStr = reviewsArr.length == 1
                && reviewsArr[0].equals("") ? String.format("%s", CliSyntax.PREFIX_REVIEW) : "";
        for (String review : reviewsArr) {
            if (!review.isEmpty()) {
                reviewsStr += String.format(" %s%s", CliSyntax.PREFIX_REVIEW, review);
            }
        }

        String[] categoriesArr = categoriesText.getText().split(" ");
        String categoriesStr = categoriesArr.length == 1
                && categoriesArr[0].equals("") ? String.format("%s", CliSyntax.PREFIX_TAG_CATEGORY) : "";
        for (String category : categoriesArr) {
            if (!category.isEmpty()) {
                categoriesStr += String.format(" %s%s", CliSyntax.PREFIX_TAG_CATEGORY, category);
            }
        }

        String[] schoolsArr = schoolsText.getText().split(" ");
        String schoolsStr = categoriesArr.length == 1
                && schoolsArr[0].equals("") ? String.format("%s", CliSyntax.PREFIX_TAG_SCHOOL) : "";
        for (String school : schoolsArr) {
            if (!school.isEmpty()) {
                schoolsStr += String.format(" %s%s", CliSyntax.PREFIX_TAG_SCHOOL, school);
            }
        }

        String commandToSend = String.format("%s %d %s %s %s %s %s %s %s", EditCommand.COMMAND_WORD,
                index.getOneBased(), name, rating, price, address, reviewsStr, categoriesStr, schoolsStr);

        try {
            mainWindow.executeCommand(commandToSend);
        } catch (CommandException | ParseException e) {
            hide();
        }
    }

}
