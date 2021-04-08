package seedu.module.ui;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.module.commons.core.LogsCenter;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Task;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    public ListView<Task> getTaskListView() {
        return taskListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);
            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else if (taskIsDone(task)) { //task is done, set to pink
                TaskCard addedTaskCard = new TaskCard(task, getIndex() + 1);
                setGraphic(addedTaskCard.getRoot());
                addedTaskCard.getRoot().setStyle("-fx-background-color: pink");
            } else { //task not done, check for remaining time
                // if remaining time > 3 days -> green
                // if remaining time > 1  but <= 3 days -> yellow
                // if remaining time <= 1 day -> orange
                TaskCard addedTaskCard = new TaskCard(task, getIndex() + 1);
                setGraphic(addedTaskCard.getRoot());
                String colorToAssign = classifyTimeLeftByColor(task);

                if (colorToAssign.equals("Expired")) {
                    addedTaskCard.getRoot().setStyle("-fx-background-color: #9370DB; ");
                } else if (colorToAssign.equals("Green")) {
                    addedTaskCard.getRoot().setStyle("-fx-background-color: #3CB371; ");
                } else if (colorToAssign.equals("Yellow")) {
                    addedTaskCard.getRoot().setStyle("-fx-background-color: #F0E68C; ");
                } else {
                    addedTaskCard.getRoot().setStyle("-fx-background-color: #FF6347; ");
                }
            }
        }

        /**
         * Returns the done status of a given task.
         *
         * @param task
         * @return boolean
         */

        protected boolean taskIsDone(Task task) {
            DoneStatus d = task.getDoneStatus();
            return d.getIsDone();
        }

        /**
         * Given a task, assign a String representing a color according to the time difference
         * between now and the deadline of the given task.
         *
         * @param task
         * @return "Green", "Yellow", "Orange" or "Expired"
         */
        protected String classifyTimeLeftByColor(Task task) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime t = task.getDeadline().time;

            double i = t.compareTo(now);
            if (i > 0) {
                double diff = MINUTES.between(now, t);
                double daydiff = diff / 1440; //use double to retain decimal places

                assert(daydiff >= 0);
                if (daydiff >= 3) {
                    return "Green";
                } else if (daydiff <= 3 && daydiff > 1) {
                    return "Yellow";
                } else {
                    return "Orange";
                }
            } else {
                return "Expired";
            }
        }
    }

}
