package seedu.address.model.resident;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Resident in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Resident implements Comparable<Resident> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Year year;
    private final Room room;

    /**
     * Every field must be present and not null.
     */
    public Resident(Name name, Phone phone, Email email, Year year, Room room) {
        requireAllNonNull(name, phone, email, year, room);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.year = year;
        this.room = room;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Year getYear() {
        return year;
    }

    public Room getRoom() {
        return room;
    }

    /**
     * Returns true if both residents have the same name.
     * This defines a weaker notion of equality between two residents.
     */
    public boolean isSameResident(Resident otherResident) {
        if (otherResident == this) {
            return true;
        }

        return otherResident != null
                && otherResident.getName().equals(getName());
    }

    /**
     * Returns true if both residents have the same identity and data fields.
     * This defines a stronger notion of equality between two residents.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Resident)) {
            return false;
        }

        Resident otherResident = (Resident) other;
        return otherResident.getName().equals(getName())
                && otherResident.getPhone().equals(getPhone())
                && otherResident.getEmail().equals(getEmail())
                && otherResident.getYear().equals(getYear())
                && otherResident.getRoom().equals(getRoom());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, year, room);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Year: ")
                .append(getYear())
                .append("; Room: ")
                .append(getRoom());

        return builder.toString();
    }

    @Override
    public int compareTo(Resident r) {
        return name.compareTo(r.name);
    }
}
