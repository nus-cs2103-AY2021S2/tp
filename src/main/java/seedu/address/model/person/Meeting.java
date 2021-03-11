package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;

public class Meeting {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    /**
     * The constructor for a Meeting.
     */
    public Meeting(LocalDate date, LocalTime time, String description) {
        requireAllNonNull(date, time, description);
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String toUi() {
        return String.format("%s %s %s\n", DateUtil.toUi(date), TimeUtil.toUi(time), description);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime())
                && otherMeeting.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append("; ")
                .append(getTime())
                .append("; ")
                .append(getDescription());

        return builder.toString();
    }
}
