package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a schedule page
 */
public class ScheduleWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ScheduleWindow.class);
    private static final String FXML = "ScheduleWindow.fxml";
    private Logic logic;

    @FXML
    private ListView<String> sunList;
    @FXML
    private ListView<String> monList;
    @FXML
    private ListView<String> tuesList;
    @FXML
    private ListView<String> wedList;
    @FXML
    private ListView<String> thursList;
    @FXML
    private ListView<String> friList;
    @FXML
    private ListView<String> satList;
    @FXML
    private GridPane gridPane;
    @FXML
    private HBox scheduleList;

    /**
     * Creates a new ScheduleWindow.
     *
     * @param root Stage to use as the root of the ScheduleWindow.
     */
    public ScheduleWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        //ListView<String> sunList = new ListView<String>(names);
        //scheduleList.getChildren().add(sunList);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ScheduleWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the schedule window.
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
        logger.fine("Showing schedule window.");

        ObservableList<String> monLessons = logic.getLessonsForDay("monday");
        ObservableList<String> tuesLessons = logic.getLessonsForDay("tuesday");
        ObservableList<String> wedLessons = logic.getLessonsForDay("wednesday");
        ObservableList<String> thursLessons = logic.getLessonsForDay("thursday");
        ObservableList<String> friLessons = logic.getLessonsForDay("friday");
        ObservableList<String> satLessons = logic.getLessonsForDay("saturday");
        ObservableList<String> sunLessons = logic.getLessonsForDay("sunday");

        sunList.setItems(sunLessons);
        monList.setItems(monLessons);
        tuesList.setItems(tuesLessons);
        wedList.setItems(wedLessons);
        thursList.setItems(thursLessons);
        friList.setItems(friLessons);
        satList.setItems(satLessons);

        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the schedule window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the schedule window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the schedule window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
