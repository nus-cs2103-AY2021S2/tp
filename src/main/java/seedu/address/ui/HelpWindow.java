package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.CliSyntax;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103-t16-3.github.io/tp/UserGuide.html";

    public static final String ADD_PROMPT = AddCommand.getCommandWord()
                                          + " - Adds a residence into ResidenceTracker. If a parameter is specified "
                                          + "multiple times, only the last occurrence will be taken."
                                          + "\nEnter \"" + AddCommand.getCommandWord()
                                          + " " + CliSyntax.PREFIX_NAME + "NAME_OF_RESIDENCE "
                                          + CliSyntax.PREFIX_RESIDENCE_ADDRESS + "ADDRESS "
                                          + "[" + CliSyntax.PREFIX_CLEAN_STATUS_TAG + "VALID_CLEAN_STATUS]"
                                          + " [" + CliSyntax.PREFIX_TAG + "TAG]...\""
                                          + "\nExample: " + AddCommand.getCommandWord() + " "
                                          + CliSyntax.PREFIX_NAME + "Clementi HDB "
                                          + CliSyntax.PREFIX_RESIDENCE_ADDRESS
                                          + "459A Clementi Ave 3, #04-257, S121459 "
                                          + CliSyntax.PREFIX_CLEAN_STATUS_TAG + "n "
                                          + CliSyntax.PREFIX_TAG + "Reserved\n\n";

    public static final String ADDB_PROMPT = AddBookingCommand.getCommandWord()
                                           + " - Adds a booking to a residence. If a parameter is specified "
                                           + "multiple times, only the last occurrence will be taken."
                                           + "\nEnter \""
                                           + AddBookingCommand.getCommandWord() + " RESIDENCE_INDEX "
                                           + CliSyntax.PREFIX_NAME + "NAME_OF_BOOKER "
                                           + CliSyntax.PREFIX_PHONE + "PHONE_OF_BOOKER "
                                           + CliSyntax.PREFIX_BOOKING_START_DATE + "START_TIME "
                                           + CliSyntax.PREFIX_BOOKING_END_DATE + "END_TIME\""
                                           + "\nExample: " + AddBookingCommand.getCommandWord()
                                           + " 1 " + CliSyntax.PREFIX_NAME + "John "
                                           + CliSyntax.PREFIX_PHONE + "91234567 "
                                           + CliSyntax.PREFIX_BOOKING_START_DATE + "01-01-2021 "
                                           + CliSyntax.PREFIX_BOOKING_END_DATE + "02-01-2021\n\n";

    public static final String CLEAR_PROMPT = ClearCommand.getCommandWord()
                                            + " - Clears all entries from the residence tracker."
                                            + "\nEnter \"" + ClearCommand.getCommandWord() + "\"\n\n";

    public static final String DELETE_PROMPT = DeleteCommand.getCommandWord()
                                             + " - Deletes the specified residence from the list of residences "
                                             + "based on index."
                                             + "\nEnter \"" + DeleteCommand.getCommandWord() + " INDEX\""
                                             + "\nExample: " + DeleteCommand.getCommandWord() + " 3\n\n";

    public static final String DELETEB_PROMPT = DeleteBookingCommand.getCommandWord()
                                              + " - Deletes the specified booking from the specified residence "
                                              + "based on index."
                                              + "\nEnter \"" + DeleteBookingCommand.getCommandWord()
                                              + " " + CliSyntax.PREFIX_RESIDENCE + "RESIDENCE_INDEX "
                                              + CliSyntax.PREFIX_BOOKING + "BOOKING_INDEX\""
                                              + "\nExample: " + DeleteBookingCommand.getCommandWord()
                                              + " " + CliSyntax.PREFIX_RESIDENCE + "3 "
                                              + CliSyntax.PREFIX_BOOKING + "2\n\n";

    public static final String EDIT_PROMPT = EditCommand.getCommandWord()
                                           + " - Edits fields of an existing residence (other than bookings)."
                                           + "\nEnter \"" + EditCommand.getCommandWord() + " INDEX "
                                           + CliSyntax.PREFIX_CLEAN_STATUS_TAG + "n\""
                                           + "\nExample: " + EditCommand.getCommandWord() + " 2 "
                                           + CliSyntax.PREFIX_CLEAN_STATUS_TAG + "n\n\n";

    public static final String EDITB_PROMPT = EditBookingCommand.getCommandWord()
                                            + " - Edits the details of the booking identified by "
                                            + "booking index of the residence identified "
                                            + "based on the residence's index provided. "
                                            + "\nExisting values will be overwritten by the input values."
                                            + "\nEnter \"" + EditBookingCommand.getCommandWord()
                                            + " " + CliSyntax.PREFIX_RESIDENCE + " RESIDENCE_INDEX "
                                            + CliSyntax.PREFIX_BOOKING + " BOOKING_INDEX "
                                            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
                                            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
                                            + "[" + CliSyntax.PREFIX_BOOKING_START_DATE + "START_DATE] "
                                            + "[" + CliSyntax.PREFIX_BOOKING_END_DATE + "END_DATE]\""
                                            + "\nExample: " + EditBookingCommand.getCommandWord()
                                            + " " + CliSyntax.PREFIX_RESIDENCE + "1 "
                                            + CliSyntax.PREFIX_BOOKING + "1 "
                                            + CliSyntax.PREFIX_BOOKING_START_DATE + "01-01-2020\n\n";



    public static final String STATUS_PROMPT = StatusCommand.getCommandWord()
                                             + " - Updates multiple residences' clean status "
                                             + "by the index number used in the displayed residence list."
                                             + "\nEnter \"" + StatusCommand.getCommandWord()
                                             + "clean INDEX1 INDEX2 ...\""
                                             + "\n Example: " + StatusCommand.getCommandWord() + " clean 1 2 4\n\n";


    public static final String FIND_PROMPT = FindCommand.getCommandWord()
                                           + " - Finds residences whose name contains the given keyword."
                                           + "\nEnter \"" + FindCommand.getCommandWord() + " KEYWORDS\""
                                           + "\nExample: " + FindCommand.getCommandWord() + " Heights Condo\n\n";

    public static final String LIST_PROMPT = ListCommand.getCommandWord()
                                           + " - Shows a list of all residences in ResidenceTracker."
                                           + "\nEnter \"" + ListCommand.getCommandWord() + "\"\n\n";

    public static final String REMIND_PROMPT = RemindCommand.getCommandWord()
                                            + " - Shows a list of residences with bookings starting in the next 7 days."
                                            + "\nEnter \"" + RemindCommand.getCommandWord() + "\"\n\n";

    public static final String EXIT_PROMPT = ExitCommand.getCommandWord() + " - Exits the program."
                                           + "\nEnter \"" + ExitCommand.getCommandWord() + "\"\n\n";


    public static final String HELP_MESSAGE = "List of commands:\n"
                                            + ADD_PROMPT
                                            + ADDB_PROMPT
                                            + CLEAR_PROMPT
                                            + DELETE_PROMPT
                                            + DELETEB_PROMPT
                                            + EDIT_PROMPT
                                            + EDITB_PROMPT
                                            + STATUS_PROMPT
                                            + FIND_PROMPT
                                            + LIST_PROMPT
                                            + REMIND_PROMPT
                                            + EXIT_PROMPT;

    public static final String URL_MESSAGE = "For more info, refer to user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label urlMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        urlMessage.setText(URL_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
