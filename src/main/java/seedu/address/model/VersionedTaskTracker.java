package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedTaskTracker extends TaskTracker {
    private List<ReadOnlyTaskTracker> taskTrackerStateList = new ArrayList<ReadOnlyTaskTracker>();
    private int currentStatePointer;

    public VersionedTaskTracker() {
        this.currentStatePointer = 0;
    }

    public void commit(ReadOnlyTaskTracker taskToBeCommitted) {
        taskTrackerStateList.add(taskToBeCommitted);
        currentStatePointer = taskTrackerStateList.size() - 1;
    }

    public ReadOnlyTaskTracker undo() {
        currentStatePointer -= 1;
        return taskTrackerStateList.get(currentStatePointer);
    }

    public ReadOnlyTaskTracker redo() {
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
