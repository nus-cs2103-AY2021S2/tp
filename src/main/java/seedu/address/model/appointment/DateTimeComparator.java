package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DateTimeComparator implements Comparator<Appointment> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public int compare(Appointment a1, Appointment a2) {
        LocalDateTime d1 = LocalDateTime.parse(a1.getDateTime().toString(), DATE_TIME_FORMATTER);
        LocalDateTime d2 = LocalDateTime.parse(a2.getDateTime().toString(), DATE_TIME_FORMATTER);

        return d1.compareTo(d2);
    }
}
