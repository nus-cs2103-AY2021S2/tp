package seedu.address.model.residence;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Represents a Residence in ResidenceTracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Residence {

    // Identity fields
    private final ResidenceName residenceName;

    // Data fields
    private final ResidenceAddress residenceAddress;
    private final BookingList bookingList;
    private final Set<Tag> tags = new HashSet<>();
    private CleanStatusTag cleanStatusTag;

    /**
     * Every field must be present and not null.
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

    public BookingList getBookingDetails() {
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
                && otherResidence.getBookingDetails().equals(getBookingDetails())
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

}
