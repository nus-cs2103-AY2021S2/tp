package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private final Remark remark;

    // Data fields
    private final Address address;
    private final ModeOfContact modeOfContact;
    private final Blacklist blacklist;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  ModeOfContact modeOfContact, Blacklist blacklist, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, modeOfContact, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = new Remark(Remark.DEFAULT_REMARK);
        this.modeOfContact = modeOfContact;
        this.blacklist = blacklist;
        this.tags.addAll(tags);
    }

    /**
     * Overloaded constructor that takes in one additional argument to add a remark to a person.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark,
                  ModeOfContact modeOfContact, Blacklist blacklist, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, remark, modeOfContact, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.modeOfContact = modeOfContact;
        this.blacklist = blacklist;
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

    public ModeOfContact getModeOfContact() {
        return modeOfContact;
    }

    public Blacklist getBlacklist() {
        return blacklist;
    }

    public boolean getBlacklistStatus() {
        return blacklist.getStatus();
    }

    /**
     * Returns this person with a different blacklist status.
     * @return the newly created person
     */
    public Person toggleBlacklistStatus() {
        Blacklist newBlacklist = blacklist.toggleStatus();
        return new Person(name, phone, email, address,
                remark, modeOfContact, newBlacklist, tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
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
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getModeOfContact().equals(getModeOfContact())
                && otherPerson.getBlacklist().equals(getBlacklist())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, remark, modeOfContact, tags);
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
                .append(getRemark())
                .append("; Mode Of Contact: ")
                .append(getModeOfContact())
                .append("; Blacklist: ")
                .append(getBlacklist());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
