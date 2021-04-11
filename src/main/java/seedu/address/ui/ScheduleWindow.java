package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.PanelList.LessonListPanel;

/**
 * Controller for a schedule page
 */
public class ScheduleWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ScheduleWindow.class);
    private static final String FXML = "ScheduleWindow.fxml";
    private Logic logic;
    private LessonListPanel sundayLessonListPanel;
    private LessonListPanel mondayLessonListPanel;
    private LessonListPanel tuesdayLessonListPanel;
    private LessonListPanel wednesdayLessonListPanel;
    private LessonListPanel thursdayLessonListPanel;
    private LessonListPanel fridayLessonListPanel;
    private LessonListPanel saturdayLessonListPanel;

    @FXML
    private StackPane sundayListPanelPlaceholder;
    @FXML
    private StackPane mondayListPanelPlaceholder;
    @FXML
    private StackPane tuesdayListPanelPlaceholder;
    @FXML
    private StackPane wednesdayListPanelPlaceholder;
    @FXML
    private StackPane thursdayListPanelPlaceholder;
    @FXML
    private StackPane fridayListPanelPlaceholder;
    @FXML
    private StackPane saturdayListPanelPlaceholder;
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

        sundayLessonListPanel = new LessonListPanel(logic.getSundayLesson());
        sundayListPanelPlaceholder.getChildren().add(sundayLessonListPanel.getRoot());

        mondayLessonListPanel = new LessonListPanel(logic.getMondayLesson());
        mondayListPanelPlaceholder.getChildren().add(mondayLessonListPanel.getRoot());

        tuesdayLessonListPanel = new LessonListPanel(logic.getTuesdayLesson());
        tuesdayListPanelPlaceholder.getChildren().add(tuesdayLessonListPanel.getRoot());

        wednesdayLessonListPanel = new LessonListPanel(logic.getWednesdayLesson());
        wednesdayListPanelPlaceholder.getChildren().add(wednesdayLessonListPanel.getRoot());

        thursdayLessonListPanel = new LessonListPanel(logic.getThursdayLesson());
        thursdayListPanelPlaceholder.getChildren().add(thursdayLessonListPanel.getRoot());

        fridayLessonListPanel = new LessonListPanel(logic.getFridayLesson());
        fridayListPanelPlaceholder.getChildren().add(fridayLessonListPanel.getRoot());

        saturdayLessonListPanel = new LessonListPanel(logic.getSaturdayLesson());
        saturdayListPanelPlaceholder.getChildren().add(saturdayLessonListPanel.getRoot());

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
