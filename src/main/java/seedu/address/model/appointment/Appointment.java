package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Address;
import seedu.address.model.Name;
import seedu.address.model.person.Person;

//import seedu.address.model.tag.Tag;

/**
 * Represents an Appointment in the appointment book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment implements Comparable<Appointment> {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final DateTime dateTime;
    private final Set<Person> contacts = new HashSet<>();

    //private final TimeAdded timeAdded;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Name name, Address address, DateTime dateTime, Set<Person> contacts) {
        requireAllNonNull(name, address, dateTime, contacts);
        this.name = name;
        this.address = address;
        this.dateTime = dateTime;
        this.contacts.addAll(contacts);
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns an immutable persons set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Person> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    /**
     * Returns true if both appointments have the same name.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getName().equals(getName());
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
                && otherAppointment.getAddress().equals(getAddress())
                && otherAppointment.getDateTime().equals(getDateTime())
                && otherAppointment.getContacts().equals(getContacts());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, dateTime, contacts);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Address: ")
                .append(getAddress())
                .append("; DateTime: ")
                .append(getDateTime());

        Set<Person> contacts = getContacts();
        if (!contacts.isEmpty()) {
            builder.append("; Contacts: ");
            contacts.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Appointment p) {
        return this.getName().fullName.compareToIgnoreCase(p.getName().fullName);
    }
}
