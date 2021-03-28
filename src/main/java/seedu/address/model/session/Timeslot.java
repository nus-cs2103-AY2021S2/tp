package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Session's timeslot in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeslot(String)}
 */
public class Timeslot {
    public static final String INFIX = "to";
    public static final String MESSAGE_CONSTRAINTS = "Timeslots must not be null and must be in the format:\n "
            + Time.TIME_FORMAT + " (start time) " + INFIX + " " + Time.TIME_FORMAT + " (end time) "
            + "and adhere to the following constraint:\n"
            + "the ending time should be strictly after the starting time.";

    private Time start;
    private Time end;

    /**
     * Constructs an {@code Timeslot}.
     *
     * @param timeslot A valid timeslot.
     */
    public Timeslot(String timeslot) {
        requireNonNull(timeslot);
        checkArgument(isValidTimeslot(timeslot), MESSAGE_CONSTRAINTS);
        String[] splitTimeslot = timeslot.split(INFIX);
        this.start = new Time(splitTimeslot[0].trim());
        this.end = new Time(splitTimeslot[1].trim());
    }

    /**
     * Returns if a given string is a valid timeslot.
     */
    public static boolean isValidTimeslot(String timeslot) {
        String[] splitTimeslot = timeslot.split("\\s+");
        if (splitTimeslot.length != 3) { //wrong number of inputs
            return false;
        } else if (!splitTimeslot[1].trim().equals(INFIX)) { //wrong infix
            return false;
        } else if (!Time.isValidTime(splitTimeslot[0].trim())) { //wrong format for start time
            return false;
        } else if (!Time.isValidTime(splitTimeslot[2].trim())) { //wrong format for end time
            return false;
        } else {
            Time start = new Time(splitTimeslot[0].trim());
            Time end = new Time(splitTimeslot[2].trim());
            return start.isBefore(end);
        }
    }

    @Override
    public String toString() {
        return this.start.toString() + " to " + this.end.toString();
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
}
