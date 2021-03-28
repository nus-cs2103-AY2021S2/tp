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
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;
    public static final String URL_COPIED = "URL has been copied.";

    private static final int ROW_HEIGHT = 25;
    private static final int SAFETY_MARGIN = 5;

    private static final String FXML = "HelpPanel.fxml";

    private MainWindow mainWindow;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandSyntax> projectsTableView;

    @FXML
    private TableView<CommandSyntax> contactsTableView;

    /**
     * Creates a new HelpPanel.
     */
    public HelpPanel(MainWindow mainWindow) {
        super(FXML);
        helpMessage.setText(HELP_MESSAGE);
        this.mainWindow = mainWindow;

        setUpTable(projectsTableView, getProjectCommands());
        setUpTable(contactsTableView, getContactCommands());
    }

    private void setUpTable(TableView<CommandSyntax> table, ObservableList<CommandSyntax> commands) {
        table.setItems(commands);
        table.setFixedCellSize(ROW_HEIGHT);
        table.setSelectionModel(null);
        table.prefHeightProperty()
                .bind(Bindings.size(table.getItems()).multiply(ROW_HEIGHT).add(SAFETY_MARGIN));

        TableColumn<CommandSyntax, String> commandCol = new TableColumn<>("Command");
        commandCol.setSortable(false);
        commandCol.setCellValueFactory(new PropertyValueFactory<>("command"));

        TableColumn<CommandSyntax, String> usageCol = new TableColumn<>("Usage");
        usageCol.setSortable(false);
        usageCol.setCellValueFactory(new PropertyValueFactory<>("usage"));

        commandCol.prefWidthProperty().bind(table.widthProperty().multiply(0.16));
        usageCol.prefWidthProperty().bind(table.widthProperty().multiply(0.82));

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
                new CommandSyntax("addP", "addP n/PROJECT_NAME"),
                new CommandSyntax("addEto", "addEto PROJECT_INDEX d/DESCRIPTION i/INTERVAL at/DATE"),
                new CommandSyntax("addDto", "addDto PROJECT_INDEX d/DESCRIPTION by/DATE"),
                new CommandSyntax("addTto", "addTto PROJECT_INDEX d/DESCRIPTION"),
                new CommandSyntax("addCto",
                        "addCto PROJECT_INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…"),
                new CommandSyntax("deleteP", "deleteP PROJECT_INDEX"),
                new CommandSyntax("deleteE", "deleteE PROJECT_INDEX r/EVENT_INDEX"),
                new CommandSyntax("deleteD", "deleteD PROJECT_INDEX r/DEADLINE_INDEX"),
                new CommandSyntax("deleteT", "deleteT PROJECT_INDEX r/TODO_INDEX"),
                new CommandSyntax("deleteCfrom", "deleteCfrom PROJECT_INDEX r/CONTACT_INDEX")
        );
    }

    private static ObservableList<CommandSyntax> getContactCommands() {
        return FXCollections.observableArrayList(
                new CommandSyntax("add", "add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…"),
                new CommandSyntax("find", "find KEYWORD [MORE_KEYWORDS]"),
                new CommandSyntax("edit",
                        "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…"),
                new CommandSyntax("delete", "delete INDEX")
        );
    }
}
