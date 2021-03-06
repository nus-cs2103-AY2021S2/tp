package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Timeslot {
    public static final String INFIX = "to"
    public static final String MESSAGE_CONSTRAINTS = "Timeslots must be in the format: "
            + Time.TIME_FORMAT + " (start time) " + INFIX + " " + Time.TIME_FORMAT + " (end time) "
            + "and adhere to the following constraint:\n"
            + "the ending time should be strictly after the starting time.";

    private Time start;
    private Time end;

    public Timeslot(String timeslot) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        String splitTimeslot = timeslot.split(INFIX);
        this.start = new Time(splitTimeslot[0].trim());
        this.end = new Time(splitTimeslot[1].trim());
    }

    public static boolean isValidTimeslot(String timeslot) {
        String splitTimeslot = timeslot.split("\\s+");
        if (splitTimeslot.length !== 3) { //wrong number of inputs
            return false;
        } else if (!splitTimeslot[1].trim().equals(INFIX)) { //wrong infix
            return false
        } else if (!Time.isValidTime(splitTimeslot[0].trim())) { //wrong format for start time
            return false;
        } else if (!Time.isValidTime(splitTimeslot[2].trim())) { //wrong format for end time
            return false;
        } else {
            Time start = new Time(splitTimeslot[0].trim());
            Time end = new Time(splitTimeslot[1].trim());
            return start.isBefore(end);
        }
    }

    @Override
    public String toString() {
        return this.start.toString() + " - " + this.end.toString();
    }
}
