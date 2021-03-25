package seedu.weeblingo.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.logic.Logic;
import seedu.weeblingo.logic.commands.CommandResult;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.Quiz;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final ImageView teacherImage = new ImageView(
            new Image(this.getClass().getResourceAsStream("/images/teacher.png")));

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private FlashcardListPanel flashcardListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane flashcardListPanelPlaceholder;

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

        // can have a separate Panel here for the quiz mode, structure similar to learn mode
        // problem is fillInnerParts() is called in UiManager start method when the app starts,
        // need to find a way to alternate between the two panels

        // display menu mode at the launch of app

        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());


        // don't show flashcard panel at the start
        enterStartMode();

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFlashcardBookFilePath());
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
     * Shows the flashcard panel for learn mode.
     */
    private void enterLearnMode() {
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        flashcardListPanelPlaceholder.setVisible(true);
    }

    /**
     * Hides the flashcard panel for start mode.
     */
    private void enterStartMode() {
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        flashcardListPanelPlaceholder.setVisible(false);
    }

    /**
     * Shows the flashcard panel for learn mode.
     * TODO: make changes to GUI and data structure s.t. only one question is shown at a time
     * and only the question description is shown (since it is quiz).
     */
    private void enterQuizMode() {
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList(),
                true, FlashcardListPanel.QUIZ_LIST);
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        flashcardListPanelPlaceholder.setVisible(true);
    }

    /**
     * Starts the quiz by generating the Quiz object and then showing the first question.
     */
    private void startQuiz() {
        flashcardListPanel = new FlashcardListPanel(logic.startQuiz(), true, logic.getCurrentIndex());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        flashcardListPanelPlaceholder.getChildren().add(teacherImage);
        flashcardListPanelPlaceholder.setVisible(true);
    }

    /**
     * Shows the next question in the quiz.
     */
    private void getNextFlashcard() {
        flashcardListPanelPlaceholder.getChildren().clear();
        if (Quiz.hasSessionEnded()) {
            resultDisplay.setFeedbackToUser(Quiz.QUIZ_END_MESSAGE);
            flashcardListPanelPlaceholder.setVisible(false);
            return;
        }

        flashcardListPanel = new FlashcardListPanel(logic.getNextFlashcard(), true, logic.getCurrentIndex());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        flashcardListPanelPlaceholder.getChildren().add(teacherImage);
        flashcardListPanelPlaceholder.setVisible(true);
    }

    /**
     * Shows the answer of current question in the quiz.
     */
    private void checkAnswer() {
        flashcardListPanelPlaceholder.getChildren().clear();
        flashcardListPanel = new FlashcardListPanel(logic.getCurrentFlashcard(), false, logic.getCurrentIndex());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        flashcardListPanelPlaceholder.getChildren().add(teacherImage);
        flashcardListPanelPlaceholder.setVisible(true);
    }

    /**
     * Clears the Quiz instance.
     */
    private void clearQuizInstance() {
        flashcardListPanelPlaceholder.getChildren().clear();
        logic.clearQuizInstance();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            assert true;
            logger.warning("Showing help");
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

    public FlashcardListPanel getFlashcardListPanel() {
        return flashcardListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.weeblingo.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {


            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandText.equals("end")) {
                clearQuizInstance();
                enterStartMode();
            }

            if (commandText.equals("learn")) {
                enterLearnMode();
            }

            if (commandText.equals("quiz")) {
                enterQuizMode();
            }

            if (commandText.equals("start")) {
                startQuiz();
            }

            if (commandText.equals("next")) {
                getNextFlashcard();
            }

            if (commandText.equals("check")) {
                checkAnswer();
            }

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

    /**
     * Displays greetings message in resultDisplay.
     */
    public void displayGreetings() {
        resultDisplay.greetUser();
    }
}
