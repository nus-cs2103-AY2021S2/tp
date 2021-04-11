package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.session.SessionId;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private PersonType personType;
    private PersonId personId;
    private List<SessionId> sessions = new ArrayList<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
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

    public Address getAddress() {
        return address;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public void setPersonId(PersonId personId) {
        this.personId = personId;
    }

    public List<SessionId> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionId> sessions) {
        this.sessions = sessions;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name and phone number OR the same name and email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        boolean hasSameNameAndPhone = otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone());
        boolean hasSameNameAndEmail = otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail());

        return hasSameNameAndPhone || hasSameNameAndEmail;
    }

    public boolean isStudent() {
        return this.personType.isStudent();
    }

    public boolean isTutor() {
        return this.personType.isTutor();
    }

    public boolean hasSession() {
        return !this.sessions.isEmpty();
    }

    /**
     * Adds a session to the list of sessions that this person is assigned to
     * @param session
     */
    public void addSession(SessionId session) {
        this.sessions.add(session);
    }

    /**
     * Removes a session to the list of sessions that this person is assigned to
     * @param session
     */
    public void removeSession(SessionId session) {
        this.sessions.remove(session);
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
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
