package seedu.address.model.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Compares meetings based on meetingDate and sorts them chronologically.
 */
public class TaskDateComparator extends TaskComparator implements Comparator<Task> {

    public static final String SORT_CRITERIA = "Date";
    public static final String MESSAGE_INVALID_TIME = "Date is not in valid format.";

    @Override
    public int compare(Task task1, Task task2) {
        String date1 = task1.getDeadline().toString();
        String date2 = task2.getDeadline().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return date1.compareTo(date2);
    }
}

