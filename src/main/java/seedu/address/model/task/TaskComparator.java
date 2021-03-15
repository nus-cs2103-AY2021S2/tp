package seedu.address.model.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task a, Task b) {
        if (a.getDate().isBefore(b.getDate())) {
            return -1;
        } else {
            return 1;
        }
    }
}
