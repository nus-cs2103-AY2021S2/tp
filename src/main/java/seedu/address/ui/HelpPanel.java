package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;

/**
 * Controller for a help page
 */
public class HelpPanel extends UiPart<Region> {
    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-t11-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "The detailed user guide can be found at: " + USERGUIDE_URL;
    public static final String URL_COPIED = "URL has been copied.";

    private static final int ROW_HEIGHT = 30;
    private static final int SAFETY_MARGIN = 35;

    private static final String FXML = "HelpPanel.fxml";

    private MainWindow mainWindow;

    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessage;
    @FXML
    private TableView<CommandSyntax> projectsTableView;
    @FXML
    private TableView<CommandSyntax> todosTableView;
    @FXML
    private TableView<CommandSyntax> deadlinesTableView;
    @FXML
    private TableView<CommandSyntax> eventsTableView;
    @FXML
    private TableView<CommandSyntax> groupmatesTableView;
    @FXML
    private TableView<CommandSyntax> contactsTableView;
    @FXML
    private TableView<CommandSyntax> othersTableView;

    /**
     * Creates a new HelpPanel.
     */
    public HelpPanel(MainWindow mainWindow) {
        super(FXML);
        helpMessage.setText(HELP_MESSAGE);
        this.mainWindow = mainWindow;

        setUpTable(projectsTableView, getProjectCommands());
        setUpTable(todosTableView, getTodoCommands());
        setUpTable(deadlinesTableView, getDeadlineCommands());
        setUpTable(eventsTableView, getEventCommands());
        setUpTable(groupmatesTableView, getGroupmateCommands());
        setUpTable(contactsTableView, getContactCommands());
        setUpTable(othersTableView, getOtherCommands());
    }

    private void setUpTable(TableView<CommandSyntax> table, ObservableList<CommandSyntax> commands) {
        table.setItems(commands);
        table.setSelectionModel(null);
        table.prefHeightProperty()
                .bind(Bindings.size(table.getItems()).multiply(ROW_HEIGHT).add(SAFETY_MARGIN));

        TableColumn<CommandSyntax, String> commandCol = new TableColumn<>("Command");
        commandCol.setSortable(false);
        commandCol.setCellValueFactory(new PropertyValueFactory<>("command"));

        TableColumn<CommandSyntax, String> usageCol = new TableColumn<>("Usage");
        usageCol.setSortable(false);
        usageCol.setCellValueFactory(new PropertyValueFactory<>("usage"));

        commandCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        usageCol.prefWidthProperty().bind(table.widthProperty().multiply(0.86));

        commandCol.setResizable(false);
        usageCol.setResizable(false);

        commandCol.setReorderable(false);
        usageCol.setReorderable(false);

        table.getColumns().add(commandCol);
        table.getColumns().add(usageCol);
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
        mainWindow.setFeedbackToUser(URL_COPIED);
    }

    private static ObservableList<CommandSyntax> getProjectCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("project", "project PROJECT_INDEX"),
                new CommandSyntax("overview", "overview"),
                new CommandSyntax("todos", "todos"),
                new CommandSyntax("addP", "addP n/PROJECT_NAME"),
                new CommandSyntax("updateP", "updateP PROJECT_INDEX n/PROJECT_NAME"),
                new CommandSyntax("deleteP", "deleteP PROJECT_INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getTodoCommands() {
        return FXCollections.observableArrayList(

                new CommandSyntax("addT", "addT PROJECT_INDEX d/DESCRIPTION"),
                new CommandSyntax("updateT", "updateT PROJECT_INDEX i/TODO_INDEX d/DESCRIPTION"),
                new CommandSyntax("deleteT", "deleteT PROJECT_INDEX i/TODO_INDEX"),
                new CommandSyntax("markT", "markT PROJECT_INDEX i/TODO_INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getDeadlineCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("addD", "addD PROJECT_INDEX d/DESCRIPTION by/DATE"),
                new CommandSyntax("updateD",
                        "updateD PROJECT_INDEX i/DEADLINE_INDEX [d/DESCRIPTION] [by/DATE]"),
                new CommandSyntax("deleteD", "deleteD PROJECT_INDEX i/DEADLINE_INDEX"),
                new CommandSyntax("markD", "markD PROJECT_INDEX i/DEADLINE_INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getEventCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("addE",
                        "addE PROJECT_INDEX d/DESCRIPTION on/DATE at/TIME w/REPEAT_WEEKLY"),
                new CommandSyntax("updateE",
                        "updateE PROJECT_INDEX i/EVENT_INDEX [d/DESCRIPTION] [on/DATE] [at/TIME] "
                                + "[w/REPEAT_WEEKLY]"),
                new CommandSyntax("deleteE", "deleteE PROJECT_INDEX i/EVENT_INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getGroupmateCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("addG", "addG PROJECT_INDEX n/NAME [r/ROLE]..."),
                new CommandSyntax("updateG",
                        "updateG PROJECT_INDEX i/GROUPMATE_INDEX [n/NAME] [r/ROLE]..."),
                new CommandSyntax("deleteG", "deleteG PROJECT_INDEX i/GROUPMATE_INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getContactCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("addC", "addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]..."),
                new CommandSyntax("findC", "findC KEYWORD [KEYWORD]..."),
                new CommandSyntax("updateC",
                        "updateC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]..."),
                new CommandSyntax("deleteC", "deleteC INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getOtherCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("today", "today"),
                new CommandSyntax("contacts", "contacts"),
                new CommandSyntax("undo", "undo"),
                new CommandSyntax("redo", "redo"),
                new CommandSyntax("help", "help"),
                new CommandSyntax("clear", "clear"),
                new CommandSyntax("exit", "exit")
        );
    }
}
