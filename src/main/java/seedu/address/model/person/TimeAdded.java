package seedu.address.model.person;

import java.sql.Timestamp;

public class TimeAdded {
    public final Timestamp timestamp;
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param timeAdded A valid phone number.
     */
    public TimeAdded(String timeAdded) {
        value = timeAdded;
        timestamp = Timestamp.valueOf(timeAdded);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return value;
    }
}
