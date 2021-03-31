package fooddiary.ui;

import java.util.logging.Logger;

import fooddiary.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103-t14-2.github.io/tp/UserGuide.html#quick-start";
    public static final String EXTERNAL_DETAILS = "For more details, refer to our user guide: \n" + USERGUIDE_URL;
    public static final String CATEGORIES = "2. Food Categories:\nFASTFOOD, WESTERN, INDIAN, CHINESE, FUSION, "
                    + "JAPANESE, KOREAN, MALAY, HALAL, VEGETARIAN, VEGAN, FRUITS, DESSERT, OTHERS, INVALID\n\n\n";
    public static final String SCHOOL_LOC = "3. School Locations:\nSOC, FASS, BIZ, SCIENCE, FOE, UTOWN, NUSS, "
                    + "PGP, USC, CLB, UHALL, SDE, MED, DENT, VENTUS, INVALID\n\n\n";
    public static final String HELP_MESSAGE = "1. List of Commands:\n\n"
                    + "add: Adds an entry to the Food Diary.\n"
                    + "     add n/Al Amaan ra/5 p/6 re/I like this food a lot! a/3155 Commonwealth Ave W, "
                    + "Singapore 129588 c/Indian c/Halal s/Ventus\n\n"
                    + "addon: Adds on details (i.e review, price) of the entry "
                    + "identified by the index number used in the displayed entry.\n"
                    + "     addon 1 re/I like this food a lot! "
                    + "Singapore 129742 c/Indian Muslim\n\n"
                    + "edit: Edits the details of the entry identified by the index number used in the "
                    + "displayed entry list. Existing values will be overwritten by the input values.\n"
                    + "     edit 1 ra/5 re/I like this food a lot!\n\n"
                    + "delete: Deletes a food review from the Food Diary.\n"
                    + "     delete 1\n\n"
                    + "list: Lists all the restaurants with food reviews.\n"
                    + "     list\n\n"
                    + "find: Finds for food reviews whose names, ratings, address and categories "
                    + "match any of the provided keywords.\n"
                    + "     find Amaan Restaurant\n\n"
                    + "findall: Finds for food reviews whose names, ratings, address and categories match ALL "
                    + "of the provided keywords.\n"
                    + "     findall Amaan Restuarant 5/5\n\n"
                    + "view: Opens up a window, showing the details of a specified food review."
                    + "in a full expanded view.\n"
                    + "     view 1\n\n\n" + CATEGORIES + SCHOOL_LOC + EXTERNAL_DETAILS;


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private TextArea helpMessageField;

    /**
     * Creates a new HelpWindow with a TextArea for the help guide.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        final KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);
        setEscShortCut(esc);
        helpMessageField.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                                   <li>
     *                                       if this method is called on a thread other than the
     *                                       JavaFX Application Thread.
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
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
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
