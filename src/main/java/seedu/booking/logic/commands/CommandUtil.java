package seedu.booking.logic.commands;

import java.util.List;

import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Id;

/**
 * Contains utility methods used for commands in the various *Command classes.
 */
public class CommandUtil {
    /**
     * Looks up {@code id} from {@code bookingList} and returns it.
     * trimmed.
     */
    public static Booking getBookingById(Id id, List<Booking> bookingList) {
        return bookingList.stream()
                .filter(booking -> booking.getId().equals(id)).findFirst().orElse(null);
    }
}
