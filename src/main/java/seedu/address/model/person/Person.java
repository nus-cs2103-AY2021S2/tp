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
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Set<Tag> tags = new HashSet<>();
    private final List<InsurancePolicy> policies = new ArrayList<>();

    /**
     * Every field is present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<InsurancePolicy> policies) {
        requireAllNonNull(name, phone, email, address, tags, policies);
        this.name = name;
        this.phone = Optional.of(phone);
        this.email = Optional.of(email);
        this.address = Optional.of(address);
        this.tags.addAll(tags);
        this.policies.addAll(policies);
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
    }

    /**
     * Creates a code{Person} with the specified code{attribute} using the input code{person}.
     * Other than name and tags, other unspecified field is empty.
     */
    public Person(Person person, List<Attribute> attributes) {
        requireAllNonNull(person, attributes);
        this.name = person.name;
        if (attributes.contains(Attribute.POLICY_ID)) {
            this.policies.addAll(person.policies);
        }
        if (attributes.contains(Attribute.PHONE)) {
            this.phone = Optional.of(person.getPhone().get());
        } else {
            this.phone = Optional.empty();
        }
        if (attributes.contains(Attribute.ADDRESS)) {
            this.address = Optional.of(person.getAddress().get());
        } else {
            this.address = Optional.empty();
        }
        if (attributes.contains(Attribute.EMAIL)) {
            this.email = Optional.of(person.getEmail().get());
        } else {
            this.email = Optional.empty();
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
                && otherPerson.getPolicies().equals(getPolicies());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, policies);
    }

    public boolean hasPolicies() {
        return policies.size() > 0;
    }

    public String getPersonNameAndAllPolicies() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name).append("@");
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
        return builder.toString();
    }

}
