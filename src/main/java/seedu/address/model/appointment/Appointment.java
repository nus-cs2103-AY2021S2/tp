package seedu.address.model.appointment;

import java.util.Objects;

import seedu.address.model.name.Name;

public class Appointment {
    // Mandatory fields
    private final Name name;
    private final Remark remarks;
    private final Date date;

    // Optional fields
    private final Time time;

    /**
     * Constructs an {@code Appointment} without any optional fields.
     */
    public Appointment(Name name, Remark remarks, Date date) {
        this.name = name;
        this.remarks = remarks;
        this.date = date;
        time = null;
    }

    /**
     * Constructs an {@code Appointment} with all information.
     * Every field must be present and not null.
     */
    public Appointment(Name name, Remark remarks, Date date, Time time) {
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
     * Returns true if both appointments have the same name and date.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getName().equals(getName())
                && otherAppointment.getDate().equals(getDate());
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
                && otherAppointment.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, remarks, date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Remarks: ")
                .append(getRemarks())
                .append("; Date: ")
                .append(getDate());

        if (time != null) {
            builder.append("; Time: ").append(getTime());
        }
        return builder.toString();
    }
}
