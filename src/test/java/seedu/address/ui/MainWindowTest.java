package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;

import java.nio.file.Path;
import java.time.LocalDate;

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
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.todo.Todo;
import seedu.address.storage.JsonColabFolderStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.DeadlineBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TodoBuilder;

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
        logic = new LogicManager(new ModelManager(getTypicalColabFolder(), new UserPrefs()), storageManager);
        FxToolkit.setupStage(stage -> {
            this.stage = stage;
            mainWindow = new MainWindow(stage, logic);
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

    @Test
    public void contacts_success() {
        inputCommand("addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01");

        // assert contact has been added
        Contact contactToAdd = new ContactBuilder()
                .withName("John Doe")
                .withPhone("98765432")
                .withEmail("johnd@example.com")
                .withAddress("John street, block 123, #01-01")
                .build();
        assertTrue(logic.getFilteredContactList().contains(contactToAdd));

        // contacts window is displayed
        assertTrue(mainWindowHandle.contains(MainWindow.CONTACT_LIST_PANEL_ID));

        int contactIndex = logic.getFilteredContactList().size();

        // update contact
        inputCommand("updateC " + contactIndex + " p/91234567 e/johndoe@example.com");
        Contact contactToUpdate = new ContactBuilder(contactToAdd)
                .withPhone("91234567")
                .withEmail("johndoe@example.com")
                .build();

        assertTrue(logic.getFilteredContactList().contains(contactToUpdate));
        assertTrue(mainWindowHandle.contains(MainWindow.CONTACT_LIST_PANEL_ID));

        // delete contact
        inputCommand("deleteC " + contactIndex);
        assertFalse(logic.getFilteredContactList().contains(contactToUpdate));
        assertTrue(mainWindowHandle.contains(MainWindow.CONTACT_LIST_PANEL_ID));
    }

    @Test
    public void projects_success() {
        inputCommand("addP n/project");

        // assert project has been added
        Project projectToAdd = new ProjectBuilder()
                .withName("project")
                .build();

        assertTrue(logic.getFilteredProjectsList().contains(projectToAdd));

        // project window is displayed
        assertTrue(mainWindowHandle.contains(MainWindow.PROJECT_PANEL_ID));

        int projectIndex = logic.getFilteredProjectsList().size();

        // update project
        inputCommand("updateP " + projectIndex + " n/newprojectname");
        Project projectToUpdate = new ProjectBuilder(projectToAdd)
                .withName("newprojectname")
                .build();

        assertTrue(logic.getFilteredProjectsList().contains(projectToUpdate));
        assertTrue(mainWindowHandle.contains(MainWindow.PROJECT_PANEL_ID));

        // delete project
        inputCommand("deleteP " + projectIndex);
        assertFalse(logic.getFilteredProjectsList().contains(projectToUpdate));
        assertTrue(mainWindowHandle.contains(MainWindow.TODAY_PANEL_ID));
    }

    @Test
    public void todos_success() {
        // create project
        inputCommand("addP n/project");
        Project project = new ProjectBuilder()
                .withName("project")
                .build();
        int projectIndex = logic.getFilteredProjectsList().size();

        // add todo
        inputCommand("addT " + projectIndex + " d/todo");
        Todo todoToAdd = new TodoBuilder()
                .withDescription("todo")
                .build();
        project.addTodo(todoToAdd);

        assertTrue(logic.getFilteredProjectsList().contains(project));

        // mark as done
        inputCommand("markT " + projectIndex + " i/1");
        project.markTodo(0);
        assertTrue(logic.getFilteredProjectsList().contains(project));

        inputCommand("updateT " + projectIndex + " i/1 d/newtodo");
        todoToAdd.setDescription("newtodo");
        assertTrue(logic.getFilteredProjectsList().contains(project));

        // delete todo
        inputCommand("deleteT " + projectIndex + " i/1");
        assertFalse(logic.getFilteredProjectsList().contains(project));

        // delete project
        inputCommand("deleteP " + projectIndex);
    }

    @Test
    public void deadlines_success() {
        // create project
        inputCommand("addP n/project");
        Project project = new ProjectBuilder()
                .withName("project")
                .build();
        int projectIndex = logic.getFilteredProjectsList().size();

        // add deadline
        inputCommand("addD " + projectIndex + " d/deadline by/20-02-2021");
        Deadline deadlineToAdd = new DeadlineBuilder()
                .withDescription("deadline")
                .withByDate(LocalDate.of(2021, 2, 20))
                .build();
        project.addDeadline(deadlineToAdd);

        assertTrue(logic.getFilteredProjectsList().contains(project));

        // mark as done
        inputCommand("markD " + projectIndex + " i/1");
        project.markDeadline(0);
        assertTrue(logic.getFilteredProjectsList().contains(project));

        inputCommand("updateD " + projectIndex + " i/1 d/newdeadline");
        deadlineToAdd.setDescription("newdeadline");
        assertTrue(logic.getFilteredProjectsList().contains(project));

        // delete deadline
        inputCommand("deleteD " + projectIndex + " i/1");
        assertFalse(logic.getFilteredProjectsList().contains(project));

        // delete project
        inputCommand("deleteP " + projectIndex);
    }

    private void inputCommand(String command) {
        guiRobot.clickOn("#commandTextField");
        textInputControl.setText(command);
        guiRobot.pauseForHuman();
        guiRobot.interact(() -> guiRobot.push(KeyCode.ENTER));
        guiRobot.pauseForHuman();
    }
}
