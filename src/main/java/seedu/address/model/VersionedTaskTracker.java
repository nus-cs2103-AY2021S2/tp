package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskTracker with an undo/redo history
 */
public class VersionedTaskTracker extends TaskTracker {
    private List<TaskTracker> taskTrackerStateList = new ArrayList<TaskTracker>();
    private int currentStatePointer;

    /**
     * Creates a VersionedTaskTracker
     * Initializes the currentStatePointer as 0
     */
    public VersionedTaskTracker() {
        this.currentStatePointer = 0;
    }

    /**
     * Commits the taskTrackerToBeCommitted into the taskTrackerStateList
     *
     * @param taskTrackerToBeCommitted the TaskTracker to be committed
     */
    public void commit(TaskTracker taskTrackerToBeCommitted) {
        if (!isCurrentStateNewest()) {
            purgeRedundantStates();
        }
        taskTrackerStateList.add(taskTrackerToBeCommitted);
        currentStatePointer = taskTrackerStateList.size() - 1;
    }

    /**
     * Undoes the last change by reverting to the previous TaskTracker state
     *
     * @return previous TaskTracker as stored in the taskTrackerStateList
     */
    public TaskTracker undo() {
        currentStatePointer -= 1;

        return taskTrackerStateList.get(currentStatePointer);
    }

    /**
     * Redoes the "undo" change by reverting to the TaskTracker state before the Undo
     *
     * @return previous TaskTracker before the "undo" as stored in the taskTrackerStateList
     */
    public TaskTracker redo() {
        if (canRedoTaskTracker()) {
            currentStatePointer += 1;
        }

        return taskTrackerStateList.get(currentStatePointer);
    }

    /**
     * Checks if there is a valid TaskTracker state for redo
     *
     * @return True if there is a valid TaskTracker state for redo
     */
    public boolean canRedoTaskTracker() {
        if (currentStatePointer + 1 <= taskTrackerStateList.size() - 1) {
            return true;
        }

        return false;
    }

    /**
     * Checks if there is a valid TaskTracker state for undo
     *
     * @return True if there is a valid TaskTracker state for undo
     */
    public boolean canUndoTaskTracker() {
        return currentStatePointer > 0;
    }

    private boolean isCurrentStateNewest() {
        if (currentStatePointer < taskTrackerStateList.size() - 1) {
            return false;
        }
        return true;
    }

    private void purgeRedundantStates() {
        taskTrackerStateList.subList(currentStatePointer + 1, taskTrackerStateList.size()).clear();
    }


}
