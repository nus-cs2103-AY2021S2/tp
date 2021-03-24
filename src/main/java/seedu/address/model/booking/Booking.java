package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Booking in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    public static final String MESSAGE_CONSTRAINTS = "Bookings should be of the format n/PERSON_NAME p/PERSON_PHONE "
            + "start/BOOKING_START_DATE end/BOOKING_END_DATE and adhere to the following constraints:\n"
            + "1. Names should only contain alphanumeric characters and spaces, and they should not be blank. "
            + "2. Phone numbers should only contain numbers, and they should be at least 3 digits long. "
            + "The start and end dates must adhere to the following:\n"
            + "    - be of the format DDMMYYYY\n"
            + "    - end date must be after start date";

    private final Name name;
    private final Phone phone;
    private final LocalDate start;
    private final LocalDate end;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Every field must be present and not null.
     */
    public Booking(Name name, Phone phone, LocalDate start, LocalDate end) {
        requireAllNonNull(name, phone, start, end);
        this.name = name;
        this.phone = phone;
        this.start = start;
        this.end = end;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    /**
     * Returns if a given booking is a valid booking.
     */
    public static boolean isValidBooking(Booking test) {
        return Name.isValidName(test.getName().toString())
                && Phone.isValidPhone(test.getPhone().toString())
                && test.getEnd().isAfter(test.getStart());
    }

    /**
     * Returns true if both bookings have the same name.
     * This defines a weaker notion of equality between two bookings.
     */
    public boolean isSameBooking(Booking otherBooking) {
        if (otherBooking == this) {
            return true;
        }

        return otherBooking != null
                && otherBooking.getName().equals(getName());
    }

    /**
     * Returns true if both bookings have the same start and end dates.
     * This defines a stronger notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getStart().equals(getStart())
                && otherBooking.getEnd().equals(getEnd());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, start, end);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Start: ")
                .append(getStart().format(dateTimeFormatter))
                .append("; End: ")
                .append(getEnd().format(dateTimeFormatter));

        return builder.toString();
    }

    /**
     * Returns true if booking overlaps with another booking.
     * This happens if the start and end dates of the booking are neither before the start date of the other booking
     * or after the end date of the other booking.
     */
    public boolean doesOverlap(Booking otherBooking) {
        boolean isBeforeOtherBooking = this.getEnd().isBefore(otherBooking.getStart());
        boolean isAfterOtherBooking = this.getStart().isAfter(otherBooking.getEnd());

        return !(isBeforeOtherBooking||isAfterOtherBooking);
    }

}
