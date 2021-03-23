package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    private static final String MY_POLICIES = "%s's Policies";

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Set<Tag> tags = new HashSet<>();
    private final List<InsurancePolicy> policies = new ArrayList<>();
    private final Optional<Meeting> meeting;

    /**
     * Every field is present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, List<InsurancePolicy> policies, Meeting meeting) {
        requireAllNonNull(name, phone, email, address, tags, policies);
        this.name = name;
        this.phone = Optional.of(phone);
        this.email = Optional.of(email);
        this.address = Optional.of(address);
        this.tags.addAll(tags);
        this.policies.addAll(policies);
        this.meeting = Optional.of(meeting);
    }

    /**
     * Temporary constructor to allow missing meeting argument.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, List<InsurancePolicy> policies) {
        requireAllNonNull(name, phone, email, address, tags, policies);
        this.name = name;
        this.phone = Optional.of(phone);
        this.email = Optional.of(email);
        this.address = Optional.of(address);
        this.tags.addAll(tags);
        this.policies.addAll(policies);
        this.meeting = Optional.empty();
    }

    /**
     * Temporary constructor to allow missing policies argument.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = Optional.of(phone);
        this.email = Optional.of(email);
        this.address = Optional.of(address);
        this.tags.addAll(tags);
        this.meeting = Optional.empty();
    }

    /**
     * Creates a code{Person} with the specified code{attribute} using the input code{person}.
     * Other than name and tags, other unspecified field is empty.
     */
    public Person(Person person, Attribute attribute) {
        requireAllNonNull(person, attribute);
        this.name = person.name;
        switch (attribute) {
        case POLICY_ID:
            this.phone = Optional.empty();
            this.email = Optional.empty();
            this.address = Optional.empty();
            this.policies.addAll(person.policies);
            this.meeting = Optional.empty();
            break;
        case PHONE:
            this.phone = Optional.of(person.getPhone().get());
            this.email = Optional.empty();
            this.address = Optional.empty();
            this.meeting = Optional.empty();
            break;
        case ADDRESS:
            this.phone = Optional.empty();
            this.email = Optional.empty();
            this.address = Optional.of(person.getAddress().get());
            this.meeting = Optional.empty();
            break;
        case EMAIL:
            this.phone = Optional.empty();
            this.email = Optional.of(person.getEmail().get());
            this.address = Optional.empty();
            this.meeting = Optional.empty();
            break;
        default:
            this.phone = Optional.empty();
            this.email = Optional.empty();
            this.address = Optional.empty();
            this.meeting = Optional.empty();
        }
        this.tags.addAll(person.tags);
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<Meeting> getMeeting() {
        return meeting;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable policy arraylist, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<InsurancePolicy> getPolicies() {
        return Collections.unmodifiableList(policies);
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getPolicies().equals(getPolicies())
                && otherPerson.getMeeting().equals(getMeeting());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, policies, meeting);
    }

    public boolean hasPolicies() {
        return policies.size() > 0;
    }

    public String getPersonNameAndAllPoliciesInString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format(MY_POLICIES, name)).append("\n");
        policies.forEach(string -> builder.append(string).append("\n"));
        return builder.substring(0, builder.length() - 1);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        if (this.phone.isPresent()) {
            builder.append("; Phone: ").append(phone.get());
        }
        if (this.email.isPresent()) {
            builder.append("; Email: ").append(email.get());
        }
        if (this.address.isPresent()) {
            builder.append("; Address: ").append(address.get());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<InsurancePolicy> policies = getPolicies();
        if (!policies.isEmpty()) {
            builder.append("; Policies: ");
            policies.forEach(policyString -> builder.append(policyString).append(", "));
            builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1);
        }

        if (this.meeting.isPresent()) {
            builder.append("; Meeting: ").append(meeting.get());
        }

        return builder.toString();
    }

}
