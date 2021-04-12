package seedu.booking.ui;

import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_PREVIOUS;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_VENUES;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.booking.commons.core.GuiSettings;
import seedu.booking.commons.core.LogsCenter;
import seedu.booking.logic.Logic;
import seedu.booking.logic.commands.CommandResult;
import seedu.booking.logic.commands.CommandShowType;
import seedu.booking.logic.commands.ListBookingCommand;
import seedu.booking.logic.commands.ListPersonCommand;
import seedu.booking.logic.commands.ListVenueCommand;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private BookingListPanel upcomingBookingListPanel;
    private VenueListPanel venueListPanel;
    private BookingListPanel bookingListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane upcomingBookingListPanelPlaceholder;

    @FXML
    private Label currentListName;

    @FXML
    private Label currentListSize;

    @FXML
    private StackPane resultListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private VBox cardDisplayPlaceholder;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        venueListPanel = new VenueListPanel(logic.getFilteredVenueList());
        bookingListPanel = new BookingListPanel(logic.getFilteredBookingList());
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());

        ChangeListener<Person> personListener = new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                cardDisplayPlaceholder.getChildren().clear();
                cardDisplayPlaceholder.getChildren().add(new PersonCardBig(newValue).getRoot());
            }
        };

        ChangeListener<Venue> venueListener = new ChangeListener<Venue>() {
            @Override
            public void changed(ObservableValue<? extends Venue> observable, Venue oldValue, Venue newValue) {
                cardDisplayPlaceholder.getChildren().clear();
                cardDisplayPlaceholder.getChildren().add(new VenueCardBig(newValue).getRoot());
            }
        };

        ChangeListener<Booking> bookingListener = new ChangeListener<Booking>() {
            @Override
            public void changed(ObservableValue<? extends Booking> observable, Booking oldValue, Booking newValue) {
                cardDisplayPlaceholder.getChildren().clear();
                cardDisplayPlaceholder.getChildren().add(new BookingCardBig(newValue).getRoot());
            }
        };

        venueListPanel.addListener(venueListener);
        personListPanel.addListener(personListener);
        bookingListPanel.addListener(bookingListener);

        displayList(new CommandResult("", false, COMMAND_SHOW_VENUES, false));

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getBookingSystemFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Based on the command result, decide on which list to show.
     */
    private void displayList(CommandResult commandResult) {
        CommandShowType commandType = commandResult.getShowType();
        if (commandType == COMMAND_SHOW_PREVIOUS) {
            return;
        }
        resultListPanelPlaceholder.getChildren().clear();
        int listSize = 0;
        switch(commandType) {
        case COMMAND_SHOW_BOOKINGS:
            resultListPanelPlaceholder.getChildren().add(bookingListPanel.getRoot());
            listSize = logic.getFilteredBookingList().size();
            break;
        case COMMAND_SHOW_VENUES:
            resultListPanelPlaceholder.getChildren().add(venueListPanel.getRoot());
            listSize = logic.getFilteredVenueList().size();
            break;
        case COMMAND_SHOW_PERSONS:
            resultListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
            listSize = logic.getFilteredPersonList().size();
            break;
        case COMMAND_SHOW_NONE:
            break;
        default:
            assert false;
        }
        currentListName.setText(commandType.toString());
        currentListSize.setText(String.valueOf(listSize) +
                ((listSize == 1) ? " Listing" : " Listings"));
    }

    private void handleList(String command) {
        try {
            displayList(executeCommand(command));
        } catch (CommandException | ParseException e) {
            logger.info("Something went wrong while executing menu command: " + command);
        }
    }

    /**
     * Lists all persons when menu item is clicked
     */
    @FXML
    private void handleListPersons() {
        handleList(ListPersonCommand.COMMAND_WORD);
    }

    /**
     * Lists all venues when menu item is clicked
     */
    @FXML
    private void handleListVenues() {
        handleList(ListVenueCommand.COMMAND_WORD);
    }

    /**
     * Lists all bookings when menu item is clicked
     */
    @FXML
    private void handleListBookings() {
        handleList(ListBookingCommand.COMMAND_WORD);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.booking.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            displayList(commandResult);

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
