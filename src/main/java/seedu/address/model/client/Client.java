package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
<<<<<<< HEAD:src/main/java/seedu/address/model/person/Person.java
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Remark remark;
=======
    private Name name;
    private Phone phone;
    private Email email;
>>>>>>> 927734c55b70b76bddffd94916e0fcf0419d400d:src/main/java/seedu/address/model/client/Client.java

    // Data fields
    private Address address;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
<<<<<<< HEAD:src/main/java/seedu/address/model/person/Person.java
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
=======
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
>>>>>>> 927734c55b70b76bddffd94916e0fcf0419d400d:src/main/java/seedu/address/model/client/Client.java
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.remark = remark;
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

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

<<<<<<< HEAD:src/main/java/seedu/address/model/person/Person.java
        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags());
=======
        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getTags().equals(getTags());
>>>>>>> 927734c55b70b76bddffd94916e0fcf0419d400d:src/main/java/seedu/address/model/client/Client.java
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, remark);
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
                .append(getAddress())
                .append("; Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
