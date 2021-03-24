package seedu.address.model.schedule;

import java.util.Comparator;

public class ScheduleComparator implements Comparator<Schedule> {

    @Override
    public int compare(Schedule a, Schedule b) {
        if (a.getStartDate().isBefore(b.getStartDate())) {
            return -1;
        } else {
            return 1;
        }
    }
}
