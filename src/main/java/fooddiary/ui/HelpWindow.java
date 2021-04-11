package fooddiary.ui;

import java.util.logging.Logger;

import fooddiary.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103-t14-2.github.io/tp/UserGuide.html#quick-start";
    public static final String EXTERNAL_DETAILS = "For more details, refer to our user guide: \n" + USERGUIDE_URL;
    public static final String CATEGORIES = "2. Food Categories:\nFASTFOOD, WESTERN, INDIAN, CHINESE, FUSION, "
                    + "JAPANESE, KOREAN, MALAY, HALAL, VEGETARIAN, VEGAN, FRUITS, DESSERT, OTHERS\n\n\n";
    public static final String SCHOOL_LOC = "3. School Locations:\nSOC, FASS, BIZ, SCIENCE, FOE, UTOWN, NUSS, YIH, "
                    + "PGP, USC, CLB, UHALL, SDE, MED, DENT, VENTUS\n\n\n";
    public static final String HELP_MESSAGE = "1. List of Commands:\n\n"
                    + "add: Adds an entry to the Food Diary.\n\n"
                    + "     add n/Al Amaan ra/5 p/6 re/I like their food a lot! a/3155 Commonwealth Ave W, "
                    + "Singapore 129588 c/Indian c/Halal s/Ventus\n\n\n"
                    + "addon: Adds on details (i.e review, price, category) od the entry "
                    + "identified by the index number used in the list of displayed entries.\n\n"
                    + "     addon 1 p/7 re/I like this food a lot! "
                    + "Singapore 129742 c/Indian\n"
                    + "     addon 2 p/18\n\n\n"
                    + "edit: Edits the details of the entry identified by the index number used in the "
                    + "displayed entry list. Existing values will be overwritten by the input values.\n\n"
                    + "     edit 1 ra/5 re/I like this food a lot!\n\n\n"
                    + "delete: Deletes an entry from the Food Diary.\n\n"
                    + "     delete 1\n\n\n"
                    + "list: Lists all the entries currently in The Food Diary.\n\n"
                    + "     list\n\n\n"
                    + "find: Finds entries whose names, ratings, reviews, address and categories "
                    + "match any of the provided keywords.\n\n"
                    + "     find Amaan\n"
                    + "     find fastfood indian $6\n\n\n"
                    + "findall: Finds entries whose names, ratings, address and categories match ALL "
                    + "of the provided keywords.\n\n"
                    + "     findall Amaan 5/5 $5-15 indian\n\n\n"
                    + "view: Opens up a window, showing the details of a"
                    + "specified entry in a full expanded view."
                    + " Allows the user to read through reviews that are too "
                    + "lengthy to be shown in the main UI window."
                    + " Use ‘ESC’ key to quickly exit the view window.\n\n"
                    + "     view 1\n\n\n"
                    + "revise: Opens up a window, showing the existing details of an entry and allowing for "
                    + "quick corrections and updates without requiring the use of prefixes and command syntax in the"
                    + " UI. Use ‘TAB’ key to iterate through fields, ‘Ctrl + S’ (Windows),"
                    + " ‘Command + S’ (Mac) to save,"
                    + " ‘ESC’ key to quickly exit the revise window.\n\n"
                    + "     revise 1\n\n\n"
                    + "Clear: Deletes all entries from the food diary\n\n"
                    + "     clear\n\n\n"
                    + "Exit: Exits the food diary\n\n"
                    + "     exit\n\n\n\n"
                    + CATEGORIES + SCHOOL_LOC + EXTERNAL_DETAILS;


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
