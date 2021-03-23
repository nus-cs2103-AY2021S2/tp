package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.insurance.InsurancePlanName;
import seedu.address.model.insurance.InsurancePremium;
import seedu.address.model.meeting.Meeting;
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
    private final Gender gender;
    private final Birthdate birthdate;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final InsurancePlanName planName;
    private final InsurancePremium premium;

    //Functional fields
    private final Optional<Meeting> meeting;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Gender gender, Birthdate birthdate, Set<Tag> tags) {
        this(name, phone, email, address, gender, birthdate, tags, Optional.empty(), null, null);

    }

    /**
     * Full Constructor that is only called internally for testing.
     */
    public Person(Name name, Phone phone, Email email, Address address, Gender gender, Birthdate birthdate,
                  Set<Tag> tags, Optional<Meeting> meeting, InsurancePlanName planName, InsurancePremium premium) {
        requireAllNonNull(name, phone, email, address, gender, birthdate, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthdate = birthdate;
        this.tags.addAll(tags);
        this.meeting = meeting;
        this.planName = planName;
        this.premium = premium;
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

    public Gender getGender() {
        return gender;
    }

    public Birthdate getBirthdate() {
        return birthdate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an Optional object containing a Meeting.
     */
    public Optional<Meeting> getMeeting() {
        return meeting;
    }

    /**
     * Returns the person's insurance plan name.
     */
    public InsurancePlanName getPlanName() {
        return planName;
    }

    /**
     * Returns the person's insurance premium.
     */
    public InsurancePremium getPremium() {
        return premium;
    }

    /**
     * Creates a Person object that is identical to the original, but contains a new Meeting.
     */
    public Person setMeeting(Optional<Meeting> meeting) {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, planName, premium);
    }

    /**
     * Creates a Person object that is identical to the original, but contains a new InsurancePlanName.
     */
    public Person addPlanName(InsurancePlanName newPlanName) {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, newPlanName, premium);
    }

    /**
     * Creates a Person object that is identical to the original, but contains a new InsurancePremium.
     */
    public Person addPremium(InsurancePremium newPremium) {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, planName, newPremium);
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
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getBirthdate().equals(getBirthdate())
                && otherPerson.getTags().equals(getTags())
                && (otherPerson.getPlanName() == null
                    ? getPlanName() == null
                    : otherPerson.getPlanName().equals(getPlanName()))
                && (otherPerson.getPremium() == null
                    ? getPremium() == null
                    : otherPerson.getPremium().equals(getPremium()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, birthdate, tags, planName, premium);
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
                .append("; Gender: ")
                .append(getGender())
                .append("; Birthdate: ")
                .append(getBirthdate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Plan Name: ")
                .append(getPlanName())
                .append("; Yearly Premium: ")
                .append(getPremium());
        return builder.toString();
    }

}
