package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final School school;
    private final Phone phone;
    private final Email email;
    private final Name guardianName;
    private final Phone guardianPhone;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Lesson> lessons = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, School school, Phone phone, Email email, Address address, Name guardianName,
                  Phone guardianPhone, Set<Tag> tags, Set<Lesson> lessons) {
        requireAllNonNull(name, phone, email, address, tags, lessons);
        this.name = name;
        this.school = school;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.tags.addAll(tags);
        this.lessons.addAll(lessons);
    }

    public Name getName() {
        return name;
    }

    public School getSchool() {
        return school;
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

    public Name getGuardianName() {
        return guardianName;
    }

    public Phone getGuardianPhone() {
        return guardianPhone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<Lesson> getLessons() {
        return Collections.unmodifiableSet(lessons);
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
                && otherPerson.getSchool().equals(getSchool())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGuardianName().equals(getGuardianName())
                && otherPerson.getGuardianPhone().equals(getGuardianPhone())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLessons().equals(getLessons());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, school, phone, email, address, guardianName, guardianPhone, tags, lessons);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; School: ")
                .append(getSchool())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Guardian's Name: ")
                .append(getGuardianName())
                .append("; Guardian's Phone: ")
                .append(getGuardianPhone());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Lesson> lessonDetailsSet = getLessons();
        if (!lessonDetailsSet.isEmpty()) {
            builder.append("; Lessons: ");
            lessonDetailsSet.forEach(builder::append);
        }
        return builder.toString();
    }

}
