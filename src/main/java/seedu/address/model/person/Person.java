package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in Tutor Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Gender gender;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final SubjectList subjectList;
    private final Set<Tag> tags = new HashSet<>();

    private boolean isFavourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Address address,
                  SubjectList subjectList, Set<Tag> tags) {
        requireAllNonNull(name, gender, phone, email, address, subjectList, tags);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subjectList = subjectList;
        this.tags.addAll(tags);
        this.isFavourite = false;
    }

    /**
     * Additional constructor to take in whether the Tutor is a favourite
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Address address,
                  SubjectList subjectList, Set<Tag> tags, boolean isFavourite) {
        requireAllNonNull(name, gender, phone, email, address, subjectList, tags);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subjectList = subjectList;
        this.tags.addAll(tags);
        this.isFavourite = isFavourite;
    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
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

    public SubjectList getSubjectList() {
        return subjectList;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
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
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSubjectList().equals(getSubjectList())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, gender, phone, email, address, subjectList, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Gender: ")
                .append(getGender())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        List<TutorSubject> subjectList = getSubjectList().asUnmodifiableObservableList();
        if (!subjectList.isEmpty()) {
            builder.append("; Subjects: ");
            subjectList.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
