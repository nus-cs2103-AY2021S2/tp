package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.group.Group;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final PersonName personName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Group> groups = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Person(PersonName personName, Phone phone, Email email, Address address, Set<Group> groups) {
        requireAllNonNull(personName, phone, email, address, groups);
        this.personName = personName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.groups.addAll(groups);
    }

    public PersonName getName() {
        return personName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    public boolean inGroup(Group group) {
        return groups.contains(group);
    }

    /**
     * Returns true if both persons have the same personName.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGroups().equals(getGroups());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(personName, phone, email, address, groups);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Group> groups = getGroups();
        if (!groups.isEmpty()) {
            builder.append("; Groups: ");
            groups.forEach(builder::append);
        }
        return builder.toString();
    }

}
