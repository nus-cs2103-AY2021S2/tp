package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxToolkit;

import guitests.guihandles.MainWindowHandle;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonColabFolderStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class MainWindowTest extends GuiUnitTest {
    @TempDir
    public Path temporaryFolder;

    private MainWindow mainWindow;
    private MainWindowHandle mainWindowHandle;
    private Stage stage;
    private Logic logic;
    private TextInputControl textInputControl;

    @BeforeEach
    public void setUp() throws Exception {
        JsonColabFolderStorage jsonColabFolderStorage =
                new JsonColabFolderStorage(temporaryFolder.resolve("colab.json"));
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(jsonColabFolderStorage, jsonUserPrefsStorage);
        FxToolkit.setupStage(stage -> {
            this.stage = stage;
            mainWindow = new MainWindow(stage, new LogicManager(new ModelManager(getTypicalColabFolder(),
                    new UserPrefs()), storageManager));
            mainWindow.fillInnerParts();
            mainWindowHandle = new MainWindowHandle(stage);
            mainWindowHandle.focus();
        });
        FxToolkit.showStage();

        textInputControl = guiRobot.lookup("#commandTextField").queryTextInputControl();
    }

    @Test
    public void viewContacts_success() {
        inputCommand("contacts");
        assertTrue(mainWindowHandle.contains(MainWindow.CONTACT_LIST_PANEL_ID));
    }

    @Test
    public void viewProject_success() {
        inputCommand("project 1");
        assertTrue(mainWindowHandle.contains(MainWindow.PROJECT_PANEL_ID));
    }

    @Test
    public void viewToday_success() {
        inputCommand("today");
        assertTrue(mainWindowHandle.contains(MainWindow.TODAY_PANEL_ID));
    }

    @Test
    public void viewHelp_success() {
        inputCommand("help");
        assertTrue(mainWindowHandle.contains(MainWindow.HELP_PANEL_ID));
    }

    private void inputCommand(String command) {
        guiRobot.clickOn("#commandTextField");
        textInputControl.setText(command);
        guiRobot.pauseForHuman();
        guiRobot.interact(() -> guiRobot.push(KeyCode.ENTER));
        guiRobot.pauseForHuman();
    }
}
