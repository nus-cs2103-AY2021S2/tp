package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Comparator;


/**
 * Compares meetings based on meetingDate and sorts them chronologically.
 */
public class TaskDateComparator extends TaskComparator implements Comparator<Task> {

    public static final String SORT_CRITERIA = "Date";
    public static final String MESSAGE_INVALID_TIME = "Date is not in valid format.";

    @Override
    public int compare(Task task1, Task task2) {
        assert task1 != null;
        assert task2 != null;
        LocalDate date1 = task1.getDate().getDate();
        LocalDate date2 = task2.getDate().getDate();
        if (date1 == null && date2 != null) {
            return 1;
        } else if (date1 != null && date2 == null) {
            return -1;
        } else if (date1 == null && date2 == null) {
            return 1;
        } else {
            return date1.compareTo(date2);
        }
    }
}

