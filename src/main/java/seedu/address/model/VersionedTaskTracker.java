package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedTaskTracker extends TaskTracker {
    private List<TaskTracker> taskTrackerStateList = new ArrayList<TaskTracker>();
    private int currentStatePointer;

    public VersionedTaskTracker() {
        this.currentStatePointer = 0;
    }

    public void commit(TaskTracker taskToBeCommitted) {
        taskTrackerStateList.add(taskToBeCommitted);
        currentStatePointer = taskTrackerStateList.size() - 1;
    }

    public TaskTracker undo() {
        currentStatePointer -= 1;
        return taskTrackerStateList.get(currentStatePointer);
    }

    public TaskTracker redo() {
        if (canRedoTaskTracker()) {
            currentStatePointer += 1;
        }
        return taskTrackerStateList.get(currentStatePointer);
    }

    private boolean canRedoTaskTracker() {
        if (currentStatePointer + 1 <= taskTrackerStateList.size() - 1) {
            return true;
        }

        return false;
    }
}
