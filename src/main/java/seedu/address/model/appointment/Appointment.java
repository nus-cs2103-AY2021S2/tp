package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;

/**
 * Represents an Appointment in the appointment book.
 * Guarantees: field values are validated, immutable.
 */
public class Appointment {
    private final Name name;
    private final Remark remarks;
    private final Date date;
    private final Time time;

    /**
     * Constructs an {@code Appointment} with all information.
     * Every field must be present and not null.
     */
    public Appointment(Name name, Remark remarks, Date date, Time time) {
        requireAllNonNull(name, remarks, date, time);
        this.name = name;
        this.remarks = remarks;
        this.date = date;
        this.time = time;
    }

    public Name getName() {
        return name;
    }

    public Remark getRemarks() {
        return remarks;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    /**
     * Returns true if both appointments have the same pair of date and time.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getDate().equals(getDate())
                && otherAppointment.getTime().equals(getTime());
    }

    /**
     * Returns true if both appointments have the same identity and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getName().equals(getName())
            && otherAppointment.getRemarks().equals(getRemarks())
            && otherAppointment.getDate().equals(getDate())
            && otherAppointment.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, remarks, date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Remarks: ")
            .append(getRemarks())
            .append("; Date: ")
            .append(getDate())
            .append("; Time: ")
            .append(getTime());

        return builder.toString();
    }
}
