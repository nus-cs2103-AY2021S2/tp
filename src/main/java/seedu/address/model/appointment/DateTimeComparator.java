package seedu.address.model.appointment;

import static seedu.address.model.appointment.DateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

public class DateTimeComparator implements Comparator<Appointment> {
    private static final Logger logger = LogsCenter.getLogger(DateTimeComparator.class);

    @Override
    public int compare(Appointment a1, Appointment a2) {
        // logger.info("comparing appointments...");
        LocalDateTime d1 = LocalDateTime.parse(a1.getDateTime().toString(), DATE_TIME_FORMATTER);
        LocalDateTime d2 = LocalDateTime.parse(a2.getDateTime().toString(), DATE_TIME_FORMATTER);

        // logger.info("d1.compareTo(d2) = " + d1.compareTo(d2));
        return d1.compareTo(d2);
    }
}
