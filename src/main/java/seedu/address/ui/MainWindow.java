package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

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
    private AutocompleteListPanel autocompleteListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CommandBox commandBox;

    private String lastFlag = "";
    private List<String> currentList = new ArrayList<>();
    private boolean toggleable = true;
    private boolean showAlias = false;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane autocompleteListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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

        getRoot().addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            switch (event.getCode()) {
            case ENTER:
                currentList.clear();
                break;
            case TAB:
                if (toggleable) {
                    processKeyTabPress(logic, event);
                }
                break;
            case UP:
                processKeyUpPress();
                break;
            case DOWN:
                processKeyDownPress();
                break;
            default:
                break;
            }

        });
    }

    private void processKeyDownPress() {
        personListPanel.selectNext((value) -> {
            commandBox.setAndAppendIndex(value);
        });
    }

    private void processKeyUpPress() {
        personListPanel.selectPrev((value) -> {
            commandBox.setAndAppendIndex(value);
        });
    }

    private void processKeyTabPress(Logic logic, KeyEvent event) {
        String currentlyInBox = commandBox.getTextFieldText();

        if (currentlyInBox == null) {
            return;
        }

        List<String> availFlags = logic.getAvailableFlags(currentlyInBox);

        if (availFlags != null) {
            lastFlag = currentlyInBox.split("-")[currentlyInBox.split("-").length - 1];

            // Check if lastFlag has content
            if ((lastFlag.split(" ").length > 1
                    || lastFlag.startsWith(AddCommand.COMMAND_WORD + " "))
                    && !availFlags.isEmpty()) {

                commandBox.setAndAppendFlag(availFlags.get(0) + " ");

                // Removes flag content
                lastFlag = lastFlag.split(" ")[0];

                if (currentList.isEmpty()) {
                    return;
                }
                currentList = availFlags;
                currentList.remove(availFlags.get(0));
            } else {
                // Cycling through Flags

                if (!logic.getAutocompleteFlags(AddCommand.COMMAND_WORD)
                        .contains(("-" + lastFlag).trim())) {
                    return;
                }

                // Populate currentList
                if (currentList.isEmpty()) {
                    currentList = availFlags;
                }
                String addBack = "-" + lastFlag;

                // String without current flag
                String rollBackString = currentlyInBox.split(addBack)[0];

                // Updated text if flags available
                if (!availFlags.isEmpty()) {
                    commandBox.setTextValue(rollBackString + currentList.get(0) + " ");
                }

                currentList.remove(0);

                if (!currentList.contains(addBack + " ")) {
                    currentList.add(addBack.trim());
                }
            }
        } else {
            autocompleteListPanel.processTabKey((value) -> {
                if (value == null) {
                    commandBox.setTextValue(commandBox.getTextFieldText());
                } else {
                    commandBox.setTextValue(value);
                }
            });
            event.consume();
        }
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(),
                logic.getDisplayFilter(), logic.getSelectedPersonPredicate());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        autocompleteListPanel = new AutocompleteListPanel();
        autocompleteListPanelPlaceholder.getChildren().add(autocompleteListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand);
        // Pre-populates list
        autocompleteListPanel.updateList(logic.getAutocompleteCommands(""));
        commandBox.setKeyUpCallback((value) -> {
            // Update autocomplete list on keyup
            autocompleteListPanel.updateList(logic.getAutocompleteCommands(value));

            if (showAlias) {
                toggleable = false;
                showAlias = false;
            } else {
                toggleable = true;
            }
        });
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
        if (helpWindow.isShowing()) {
            helpWindow.focus();
        } else {
            helpWindow.show();
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
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowAlias()) {
                showAlias = commandResult.isShowAlias();
            }

            personListPanel.updateDisplayFilter(logic.getDisplayFilter());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
