package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_DISPLAY;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

import java.time.LocalDateTime;

/**
 * Represents a Appointment of a Patient.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS_MIN_DATE = "Date must be in the future";
    public static final String MESSAGE_CONSTRAINTS_DATE_FORMAT = "Date format: "
            + "DDMMYYYYhhmm or DDMMhhmm. If the year is omitted, the current year is"
            + "assumed.";

    private String zoomMeetingUrl;
    private LocalDateTime date;

    /**
     * Every field must be present and not null.
     */
    public Appointment(LocalDateTime date) {
        requireNonNull(date);
        this.date = date;
    }

    public String getZoomMeetingUrl() {
        return zoomMeetingUrl;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // for storage into JSON
    public String getDateStorage() {
        return date.format(DATE_FORMAT_STORAGE);
    }
    // for displaying, doesnt have year
    public String getDateDisplay() {
        return date.format(DATE_FORMAT_DISPLAY);
    }
}
