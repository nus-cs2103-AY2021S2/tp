package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private final List<Meeting> meetings = new ArrayList<>();

    private boolean isShowPolicyList = false;

    /**
     * Every field is present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, List<InsurancePolicy> policies, List<Meeting> meeting) {
        requireAllNonNull(name, phone, email, address, tags, policies, meeting);
        this.name = name;
        this.phone = Optional.of(phone);
        this.email = Optional.of(email);
        this.address = Optional.of(address);
        this.tags.addAll(tags);
        this.policies.addAll(policies);
        this.meetings.addAll(meeting);
        this.isShowPolicyList = true;
    }

    /**
     * Constructor to be used when {@code Person} does not have any associated policies.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = Optional.of(phone);
        this.email = Optional.of(email);
        this.address = Optional.of(address);
        this.tags.addAll(tags);
        this.isShowPolicyList = false;
    }

    /**
     * Creates a code{Person} with the specified code{attribute} using the input code{person}.
     * Other than name and tags, other unspecified field is empty.
     */
    public Person(Person person, Set<Attribute> attributes) {
        requireAllNonNull(person, attributes);
        this.name = person.name;
        if (attributes.contains(Attribute.POLICY_ID)) {
            this.policies.addAll(person.policies);
            this.isShowPolicyList = true;
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
        if (attributes.contains(Attribute.MEETING)) {
            this.meetings.addAll(person.meetings);
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

    public boolean isShowPolicyList() {
        return this.isShowPolicyList;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    private List<Tag> getSortedTags() {
        Set<Tag> tags = getTags();
        List<Tag> listOfTags = new ArrayList<>(tags);
        Collections.sort(listOfTags, Comparator.comparing(tag -> tag.tagName));
        return listOfTags;
    }

    /**
     * Returns an immutable policy arraylist, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<InsurancePolicy> getPolicies() {
        return Collections.unmodifiableList(policies);
    }

    /**
     * Returns an immutable meeting arraylist, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Meeting> getMeetings() {
        return Collections.unmodifiableList(meetings);
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
        return Objects.hash(name, phone, email, address, tags, policies, meetings);
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

        List<Tag> tags = getSortedTags();
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

        if (!meetings.isEmpty()) {
            builder.append("; Meetings: ");
            meetings.forEach(meetingString -> builder.append(meetingString).append(", "));
            builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

}
