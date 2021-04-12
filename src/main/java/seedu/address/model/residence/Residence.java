package seedu.address.model.residence;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.booking.Booking;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Represents a Residence in ResidenceTracker.
 * Guarantees: details are present and not null, field values are validated, immutable except {@code CleanStatusTag}.
 */
public class Residence implements Comparable<Residence> {

    // Identity fields
    private final ResidenceName residenceName;

    // Data fields
    private final ResidenceAddress residenceAddress;
    private final BookingList bookingList;
    private final Set<Tag> tags = new HashSet<>();
    private CleanStatusTag cleanStatusTag;

    /**
     * Every field must be present and not null except {@code BookingList}.
     */
    public Residence(ResidenceName residenceName, ResidenceAddress residenceAddress,
                     CleanStatusTag cleanStatusTag, Set<Tag> tags) {
        this.bookingList = new BookingList();
        requireAllNonNull(residenceName, residenceAddress, cleanStatusTag, tags);
        this.residenceName = residenceName;
        this.residenceAddress = residenceAddress;
        this.cleanStatusTag = cleanStatusTag;
        this.tags.addAll(tags);
    }

    /**
     * When BookingList is provided.
     */
    public Residence(ResidenceName residenceName, ResidenceAddress residenceAddress, BookingList bookingList,
                     CleanStatusTag cleanStatusTag, Set<Tag> tags) {
        this.bookingList = bookingList;
        requireAllNonNull(residenceName, residenceAddress, cleanStatusTag, tags);
        this.residenceName = residenceName;
        this.residenceAddress = residenceAddress;
        this.cleanStatusTag = cleanStatusTag;
        this.tags.addAll(tags);
    }

    public ResidenceName getResidenceName() {
        return residenceName;
    }

    public ResidenceAddress getResidenceAddress() {
        return residenceAddress;
    }

    public BookingList getBookingList() {
        return bookingList;
    }

    public CleanStatusTag getCleanStatusTag() {
        return cleanStatusTag;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public boolean hasBooking(Booking booking) {
        return bookingList.contains(booking);
    }

    /**
     * Returns a modified {@code Residence} with new {@code booking} added to it's BookingList.
     */
    public Residence addBooking(Booking booking) {
        assert !this.hasBooking(booking);
        bookingList.add(booking);
        return this;
    }

    /**
     * Returns true if both residences have the same residenceName.
     * This defines a weaker notion of equality between two residences.
     */
    public boolean isSameResidence(Residence otherResidence) {
        if (otherResidence == this) {
            return true;
        }

        return otherResidence != null
                && otherResidence.getResidenceName().equals(getResidenceName());
    }

    /**
     * Returns true if this residence has any bookings that start in the next 7 days.
     */
    public boolean hasUpcomingBooking() {
        return this.bookingList.getValue().stream()
                .filter(b -> b.getStart().isAfter(LocalDate.now())
                        && b.getStart().isBefore(LocalDate.now().plusDays(8)))
                .mapToInt(b -> 1).sum() > 0;
    }

    /**
     * Returns true if both residence have the same identity and data fields.
     * This defines a stronger notion of equality between two residence.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Residence)) {
            return false;
        }

        Residence otherResidence = (Residence) other;
        return otherResidence.getResidenceName().equals(getResidenceName())
                && otherResidence.getResidenceAddress().equals(getResidenceAddress())
                && otherResidence.getBookingList().equals(getBookingList())
                && otherResidence.getCleanStatusTag().equals(getCleanStatusTag())
                && otherResidence.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(residenceName, residenceAddress, bookingList, cleanStatusTag, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getResidenceName())
                .append("; Residence Address: ")
                .append(getResidenceAddress())
                .append("; Clean Status: ")
                .append(getCleanStatusTag());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    //@@author Zhen Yi
    @Override
    public int compareTo(Residence o) {
        if (this.getCleanStatusTag().getValue().equals("Unclean")
                && (o.getCleanStatusTag().getValue().equals("Clean"))) {
            return -1;
        } else if (this.getCleanStatusTag().getValue().equals("Clean")
                && (o.getCleanStatusTag().getValue().equals("Unclean"))) {
            return 1;
        } else {
            return 0;
        }
    }
}
