package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-t11-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final int TABLE_ITEM_HEIGHT = 32;
    private static final int ADDITIONAL_MARGIN = 30;
    private static final double WINDOW_HEIGHT = 800;
    private static final double WINDOW_WIDTH = 900;

    /**
     * Creates wrapping ability for cells in TableView.
     */
    private static final Callback<TableColumn<CommandHelper, String>, TableCell<CommandHelper, String>>
            WRAPPING_CELL_FACTORY = new Callback<TableColumn<CommandHelper, String>,
            TableCell<CommandHelper, String>>() {
                @Override
                public TableCell<CommandHelper, String> call(TableColumn<CommandHelper, String> param) {
                    TableCell<CommandHelper, String> tableCell = new TableCell<CommandHelper, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            if (item == getItem()) {
                                return;
                            }

                            super.updateItem(item, empty);

                            if (item == null) {
                                super.setText(null);
                                super.setGraphic(null);
                            } else {
                                super.setText(null);
                                Label l = new Label(item);
                                l.setWrapText(true);
                                VBox box = new VBox(l);
                                l.heightProperty().addListener((observable, oldValue, newValue) -> {
                                    box.setPrefHeight(newValue.doubleValue() + 7);
                                    Platform.runLater(() -> this.getTableRow().requestLayout());
                                });
                                l.setTextFill(Color.web("white"));
                                super.setGraphic(box);
                            }
                        }
                    };
                    return tableCell;
                }
            };

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandHelper> generalTableView;
    @FXML
    private TableView<CommandHelper> studentTableView;
    @FXML
    private TableView<CommandHelper> sessionTableView;
    @FXML
    private TableView<CommandHelper> feeTableView;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);

        root.setWidth(WINDOW_WIDTH);
        root.setHeight(WINDOW_HEIGHT);

        setUpTable(generalTableView, getGeneralCommands());
        setUpTable(studentTableView, getStudentCommands());
        setUpTable(sessionTableView, getSessionCommands());
        setUpTable(feeTableView, getFeeCommands());
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Populates the table with all the command information.
     */
    private void setUpTable(TableView<CommandHelper> table, ObservableList<CommandHelper> commands) {
        TableColumn<CommandHelper, String> commandTitle = new TableColumn<>("Command Title");
        commandTitle.setCellFactory(WRAPPING_CELL_FACTORY);
        commandTitle.setCellValueFactory(new PropertyValueFactory<>("commandTitle"));

        TableColumn<CommandHelper, String> commandUsage = new TableColumn<>("Command Usage");
        commandUsage.setCellFactory(WRAPPING_CELL_FACTORY);
        commandUsage.setCellValueFactory(new PropertyValueFactory<>("commandUsage"));

        table.setItems(commands);

        table.setSelectionModel(null);
        table.prefHeightProperty()
                .bind(Bindings.size(table.getItems()).multiply(TABLE_ITEM_HEIGHT).add(ADDITIONAL_MARGIN));

        commandTitle.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        commandUsage.prefWidthProperty().bind(table.widthProperty().multiply(0.80));

        table.getColumns().add(commandTitle);
        table.getColumns().add(commandUsage);
    }

    private static ObservableList<CommandHelper> getGeneralCommands() {
        return FXCollections.observableArrayList(
                new CommandHelper("Open help panel", "help"),
                new CommandHelper("List all", "list"),
                new CommandHelper("Clear all data", "clear"),
                new CommandHelper("Exit", "exit")
        );
    }

    private static ObservableList<CommandHelper> getStudentCommands() {
        return FXCollections.observableArrayList(
                new CommandHelper("Add student", "add_student n/NAME p/STUDENT_PHONE_NUMBER "
                        + "e/EMAIL a/ADDRESS l/STUDY_LEVEL g/GUARDIAN_PHONE_NUMBER r/RELATIONSHIP_WITH_GUARDIAN"),
                new CommandHelper("Find student", "find_student KEYWORD [MORE_KEYWORDS]"),
                new CommandHelper("Edit student", "edit_student STUDENT_INDEX [n/NAME] "
                        + "[p/STUDENT_PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [l/STUDY_LEVEL] [g/GUARDIAN_PHONE_NUMBER]"
                        + " [r/RELATIONSHIP_WITH_GUARDIAN]"),
                new CommandHelper("Delete Student", "delete_student STUDENT_INDEX"),
                new CommandHelper("List emails", "emails")
        );
    }

    private static ObservableList<CommandHelper> getSessionCommands() {
        return FXCollections.observableArrayList(
                new CommandHelper("Add single session", "add_session n/STUDENT_NAME d/DATE "
                        + "t/TIME k/DURATION s/SUBJECT f/FEE"),
                new CommandHelper("Add recurring session", "add_rec_session n/STUDENT_NAME "
                        + "d/START_DATE e/END_DATE b/INTERVAL t/TIME k/DURATION s/SUBJECT f/FEE"),
                new CommandHelper("Delete single session", "delete_session n/STUDENT_NAME i/SESSION_INDEX"),
                new CommandHelper("Delete recurring session", "delete_rec_session "
                        + "n/STUDENT_NAME i/SESSION_INDEX d/DATE t/TIME")
        );
    }

    private static ObservableList<CommandHelper> getFeeCommands() {
        return FXCollections.observableArrayList(
                new CommandHelper("Get fee", "fee n/STUDENT_NAME m/MONTH y/YEAR")
        );
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
