package seedu.address.model.task.repeatable;

import java.time.LocalDate;
import java.util.Comparator;

import seedu.address.model.task.Repeatable;

/**
 * A comparator for the repeatable class.
 */
public class RepeatableComparator implements Comparator<Repeatable> {
    @Override
    public int compare(Repeatable r1, Repeatable r2) {
        int compareDays = Integer.compare(daysTo(r1), daysTo(r2));

        return compareDays == 0 ? r1.getDescription().compareTo(r2.getDescription()) : compareDays;
    }

    private int daysTo(Repeatable repeatable) {
        LocalDate today = LocalDate.now();
        LocalDate date = repeatable.getDate();

        if (repeatable.getIsWeekly()) {
            int dayOfRepeatable = date.getDayOfWeek().getValue();
            int dayOfToday = today.getDayOfWeek().getValue();

            if (dayOfRepeatable < dayOfToday) {
                dayOfRepeatable += 7;
            }

            return dayOfRepeatable - dayOfToday;
        } else {
            return today.until(date).getDays();
        }
    }
}
