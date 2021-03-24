package fooddiary.ui;

import java.util.logging.Logger;

import fooddiary.commons.core.GuiSettings;
import fooddiary.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String EXTERNAL_DETAILS = "For more details, refer to our user guide: \n" + USERGUIDE_URL;
    public static final String HELP_MESSAGE =
            "add: Adds a food review to the Food Diary.\n" +
            "   add  n/Al Amaan Restaurant ra/4 re/best for Butter Chicken a/12 Clementi Rd, " +
            "Singapore 129742 c/Indian Muslim\n\n" +
            "delete: Deletes a food review from the Food Diary.\n" +
            "   delete n/Al Amaan Restaurant\n" +
            "   delete i/1\n\n" +
            "list: Lists all the restaurants with food reviews.\n" +
            "   list\n\n" +
            "find: Finds for food reviews whose names, ratings, address and categories " +
            "match any of the provided keywords.\n" +
            "   find Amaan Restaurant\n\n" +
            "findall: Finds for food reviews whose names, ratings, address and categories match ALL " +
            "of the provided keywords.\n" +
            "   findall Amaan Restuarant 5/5\n\n" +
            "view: Opens up a window, showing the details of a specified food review " +
            "in a full expanded view.\n" +
            "   view 1\n\n" +
            EXTERNAL_DETAILS;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

//    private Logic logic;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);

//        setWindowDefaultSize(logic.getGuiSettings());
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

//    /**
//     * Sets the default size based on {@code guiSettings}.
//     */
//    private void setWindowDefaultSize(GuiSettings guiSettings) {
//        primaryStage.setHeight(guiSettings.getWindowHeight());
//        primaryStage.setWidth(guiSettings.getWindowWidth());
//        if (guiSettings.getWindowCoordinates() != null) {
//            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
//            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
//        }
//    }

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
