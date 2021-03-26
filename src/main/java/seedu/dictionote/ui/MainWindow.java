package seedu.dictionote.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.logic.Logic;
import seedu.dictionote.logic.commands.CommandResult;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private static final float DEFAULT_WINDOW_WIDTH_RATIO = 0.5f;
    private static final float DEFAULT_WINDOW_HEIGHT_RATIO = 0.66f;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ContactListPanel contactListPanel;
    private NoteListPanel noteListPanel;
    private NoteContentPanel noteContentPanel;
    private DictionaryListPanel dictionaryListPanel;
    private DictionaryContentPanel dictionaryContentPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CommandBox commandBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane contactListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane dictionaryListPlaceholder;

    @FXML
    private StackPane dictionaryContentPlaceholder;

    @FXML
    private StackPane noteListPlaceholder;

    @FXML
    private StackPane noteContentPlaceholder;


    // For show/hide function
    @FXML
    private AnchorPane contactDisplay;

    @FXML
    private AnchorPane dictionaryListDisplay;

    @FXML
    private AnchorPane dictionaryContentDisplay;

    @FXML
    private AnchorPane noteListDisplay;

    @FXML
    private AnchorPane noteContentDisplay;

    @FXML
    private SplitPane contactSplitPanel;

    @FXML
    private SplitPane dictionarySplitPanel;

    @FXML
    private SplitPane noteSplitPanel;

    //Split Panel between dictionary and Note
    @FXML
    private SplitPane mainSplitPanel;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings(), primaryStage);

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    /**
     * Setup event listener for divider.
     */
    private void addSplitPaneListener() {
        mainSplitPanel.getDividers().get(0).positionProperty().addListener((observeValue, oldValue, newValue) -> {
            logic.getGuiSettings().setMainSplitRatio(newValue.doubleValue());
        });

        contactSplitPanel.getDividers().get(0).positionProperty().addListener((observeValue, oldValue, newValue) -> {
            logic.getGuiSettings().setContactSplitRatio(newValue.doubleValue());
        });

        dictionarySplitPanel.getDividers().get(0).positionProperty().addListener((observeValue, oldValue, newValue) -> {
            logic.getGuiSettings().setDictionarySplitRatio(newValue.doubleValue());
        });

        noteSplitPanel.getDividers().get(0).positionProperty().addListener((observeValue, oldValue, newValue) -> {
            logic.getGuiSettings().setNoteSplitRatio(newValue.doubleValue());
        });
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
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
            handleKey(event);
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        contactListPanel = new ContactListPanel(logic.getFilteredContactList());
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());

        noteListPanel = new NoteListPanel(logic.getFilteredNoteList());
        noteListPlaceholder.getChildren().add(noteListPanel.getRoot());

        noteContentPanel = new NoteContentPanel();
        noteContentPlaceholder.getChildren().add(noteContentPanel.getRoot());
        logic.setNoteContentConfig(noteContentPanel);


        dictionaryListPanel = new DictionaryListPanel(logic.getFilteredContentList(),
            logic.getFilteredDefinitionList());
        dictionaryListPlaceholder.getChildren().add(dictionaryListPanel.getRoot());

        dictionaryContentPanel = new DictionaryContentPanel(dictionaryListPanel);
        dictionaryContentPlaceholder.getChildren().add(dictionaryContentPanel.getRoot());
        logic.setDictionaryContentConfig(dictionaryContentPanel);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getContactsListFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings, Stage primaryStage) {
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setHeight(guiSettings.getWindowHeight());
            primaryStage.setWidth(guiSettings.getWindowWidth());
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        } else {
            primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight() * DEFAULT_WINDOW_HEIGHT_RATIO);
            primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth() * DEFAULT_WINDOW_WIDTH_RATIO);
        }
    }

    /**
     * Dynamically detect and change the split ratio depending on the content
     */
    void configSplit() {
        configContactSplit();
        configNoteSplit();
        configDictionarySplit();
        configMainSplit();
    }

    /**
     * Dynamically detect and change the split ratio depending on contact visible visibility
     */
    private void configContactSplit() {
        if (contactDisplay.isVisible()) {
            setDividerPosition(contactSplitPanel, logic.getGuiSettings().getContactSplitRatio());
        } else {
            setDividerPosition(contactSplitPanel, 0);
        }
    }

    /**
     * Dynamically detect and change the split ratio depending on the dictionary list and content visibility
     */
    private void configDictionarySplit() {
        if (!dictionaryListDisplay.isVisible()) {
            setDividerPosition(dictionarySplitPanel, 0);
        } else if (!dictionaryContentDisplay.isVisible()) {
            setDividerPosition(dictionarySplitPanel, 1);
        } else {
            setDividerPosition(dictionarySplitPanel, logic.getGuiSettings().getDictionarySplitRatio());
        }
    }

    /**
     * Dynamically detect and change the split ratio depending on the note list and note visibility
     */
    private void configNoteSplit() {
        if (!noteListDisplay.isVisible()) {
            setDividerPosition(noteSplitPanel, 0);
        } else if (!noteContentDisplay.isVisible()) {
            setDividerPosition(noteSplitPanel, 1);
        } else {
            setDividerPosition(noteSplitPanel, logic.getGuiSettings().getNoteSplitRatio());
        }
    }

    /**
     * Dynamically detect and change the split ratio depending on the dictionary and note visibility
     */
    private void configMainSplit() {
        if (!noteListDisplay.isVisible() && !noteContentDisplay.isVisible()) {
            setDividerPosition(mainSplitPanel, 1);
        } else if (!dictionaryListDisplay.isVisible() && !dictionaryContentDisplay.isVisible()) {
            setDividerPosition(mainSplitPanel, 0);
        } else {
            setDividerPosition(mainSplitPanel, logic.getGuiSettings().getMainSplitRatio());
        }
    }

    /**
     * set divider position for splitpane
     */
    private void setDividerPosition(SplitPane splitPane, double position) {
        if (splitPane.getDividerPositions()[0] != position) {
            splitPane.setDividerPositions(position);
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

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY(), logic.getGuiSettings().getContactSplitRatio(),
            logic.getGuiSettings().getDictionarySplitRatio(), logic.getGuiSettings().getNoteSplitRatio(),
            logic.getGuiSettings().getMainSplitRatio(), contactDisplay.isVisible(),
            dictionaryContentDisplay.isVisible(), dictionaryListDisplay.isVisible(),
            noteContentDisplay.isVisible(), noteListDisplay.isVisible());

        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }


    void handleKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            commandBox.requestFocus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Set the visiable of the panel.
     *
     * @param pane to hide.
     */
    private void setPanelVisibility(Pane pane, boolean visible) {
        pane.setVisible(visible);
    }

    /**
     * Setup display panel.
     */
    private void setupDisplayPanel() {
        setPanelVisibility(contactDisplay, logic.getGuiSettings().isContactPanelVisible());
        setPanelVisibility(dictionaryListDisplay, logic.getGuiSettings().isDictionaryListPanelVisible());
        setPanelVisibility(dictionaryContentDisplay, logic.getGuiSettings().isDictionaryContentPanelVisible());
        setPanelVisibility(noteListDisplay, logic.getGuiSettings().isNoteListPanelVisible());
        setPanelVisibility(noteContentDisplay, logic.getGuiSettings().isNoteContentPanelVisible());
        configSplit();
    }

    /**
     * Configure window after shown
     */
    public void handleShown() {
        setupDisplayPanel();
        commandBox.requestFocus();

        addSplitPaneListener();
        dictionaryListPanel.openContentDisplay();
    }

    /**
     * * Set the visiable of the panels.
     */
    private void handlePanelVisibility(UiActionOption uiActionOption, boolean visible) {

        switch (uiActionOption) {
        case DICTIONARY:
            setPanelVisibility(dictionaryContentDisplay, visible);
            setPanelVisibility(dictionaryListDisplay, visible);
            break;
        case DICTIONARY_LIST:
            setPanelVisibility(dictionaryListDisplay, visible);
            break;
        case DICTIONARY_CONTENT:
            setPanelVisibility(dictionaryContentDisplay, visible);
            break;
        case DICTIONARY_DEFINITIONS:
            setPanelVisibility(dictionaryContentDisplay, visible);
            break;
        case NOTE:
            setPanelVisibility(noteContentDisplay, visible);
            setPanelVisibility(noteListDisplay, visible);
            break;
        case NOTE_LIST:
            setPanelVisibility(noteListDisplay, visible);
            break;
        case NOTE_CONTENT:
            setPanelVisibility(noteContentDisplay, visible);
            break;
        case LIST:
            setPanelVisibility(dictionaryListDisplay, visible);
            setPanelVisibility(noteListDisplay, visible);
            break;
        case CONTACT:
            setPanelVisibility(contactDisplay, visible);
            break;
        case ALL:
            setPanelVisibility(contactDisplay, visible);
            setPanelVisibility(dictionaryListDisplay, visible);
            setPanelVisibility(dictionaryContentDisplay, visible);
            setPanelVisibility(noteListDisplay, visible);
            setPanelVisibility(noteContentDisplay, visible);
            break;
        case NONE:
            //Do nothing
            break;
        default:
            assert false : uiActionOption.toString() + " UiAction is not handle";
        }
    }

    /**
     * Open a panel of a selected type.
     *
     * @param uiActionOption desire display to open.
     */
    private void handleOpen(UiActionOption uiActionOption) {
        handlePanelVisibility(uiActionOption, true);
    }

    /**
     * Close display panel of a selected type.
     *
     * @param uiActionOption desire display to close.
     */
    private void handleClose(UiActionOption uiActionOption) {
        handlePanelVisibility(uiActionOption, false);
    }

    /**
     * Enter Edit Mode.
     */
    private void handleEditModeEnter() {
        setPanelVisibility(noteContentDisplay, true);
        noteContentPanel.enterEditMode();
    }


    /**
     * Exit Edit Mode.
     */
    private void handleEditModeExit() {
        noteContentPanel.exitEditMode();
    }



    /**
     * Executes the command and returns the result.
     *
     * @see seedu.dictionote.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);

            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            executeUiAction(commandResult.getUiAction(), commandResult.getUiActionOption());

            configSplit();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setErrorFeedbackToUser(e.getMessage());
            configSplit();
            throw e;
        }
    }

    /**
     * Executes the Ui Action desired from the command.
     *
     * @see seedu.dictionote.logic.Logic#execute(String)
     */
    private void executeUiAction(UiAction action, UiActionOption uiActionOption) {
        switch (action) {
        case HELP:
            handleHelp();
            break;
        case EXIT:
            handleExit();
            break;
        case OPEN:
            handleOpen(uiActionOption);
            break;
        case CLOSE:
            handleClose(uiActionOption);
            break;
        case EDITMODEENTER:
            handleEditModeEnter();
            break;
        case EDITMODEEXIT:
            handleEditModeExit();
            break;
        case NONE:
            break;
        default:
            assert false : action.toString() + " UiAction is not handle";
            break;
        }
    }
}
