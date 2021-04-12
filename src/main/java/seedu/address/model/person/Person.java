package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.lesson.Day;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Optional<School> school;
    private final Optional<Email> email;
    private final Optional<Name> guardianName;
    private final Optional<Phone> guardianPhone;
    private final Optional<Address> address;
    private final Optional<Level> level;
    private final Set<Subject> subjects = new HashSet<>();
    private final Set<Lesson> lessons = new HashSet<>();


    /**
     * Student's name and phone number must be present.
     */
    public Person(Name name, Phone phone, Optional<School> school, Optional<Email> email, Optional<Address> address,
                  Optional<Name> guardianName, Optional<Phone> guardianPhone, Optional<Level> level,
                  Set<Subject> subjects, Set<Lesson> lessons) {
        requireAllNonNull(name, phone, school, email, address, guardianName,
                guardianPhone, level, subjects, lessons);
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.email = email;
        this.address = address;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.level = level;
        this.subjects.addAll(subjects);
        this.lessons.addAll(lessons);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Optional<School> getSchool() {
        return school;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<Name> getGuardianName() {
        return guardianName;
    }

    public Optional<Phone> getGuardianPhone() {
        return guardianPhone;
    }

    public Optional<Level> getLevel() {
        return level;
    }

    /**
     * Returns an immutable subject set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

    public Set<Lesson> getLessons() {
        return Collections.unmodifiableSet(lessons);
    }

    /**
     * Returns an ArrayList consisting of all the days on which a person has lessons on.
     */
    public ArrayList<Day> getLessonsDays() {
        ArrayList<Day> days = new ArrayList<>();
        if (!lessons.isEmpty()) {
            Iterator<Lesson> lesson = lessons.iterator();
            while (lesson.hasNext()) {
                days.add(lesson.next().getDay());
            }
        }
        return days;
    }

    /**
     * Returns true if both persons have the same phone number.
     * Phone is a unique identifier of a person.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both persons have the same name and different phone numbers.
     * This defines a notion of potential equality between two persons.
     */
    public boolean isPotentialSamePerson(Person otherPerson) {
        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (!otherPerson.getPhone().equals(getPhone()));
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
                && otherPerson.getSchool().equals(getSchool())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGuardianName().equals(getGuardianName())
                && otherPerson.getGuardianPhone().equals(getGuardianPhone())
                && otherPerson.getSubjects().equals(getSubjects())
                && otherPerson.getLessons().equals(getLessons());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, school, email, address, guardianName, guardianPhone, subjects, lessons);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone());

        Optional<School> school = getSchool();
        if (school.isPresent()) {
            builder.append("; School: ")
                    .append(school.get());
        }

        Optional<Email> email = getEmail();
        if (email.isPresent()) {
            builder.append("; Email: ")
                    .append(email.get());
        }

        Optional<Address> address = getAddress();
        if (address.isPresent()) {
            builder.append("; Address: ")
                    .append(address.get());
        }

        Optional<Name> guardianName = getGuardianName();
        if (guardianName.isPresent()) {
            builder.append("; Guardian's Name: ")
                    .append(guardianName.get());
        }

        Optional<Phone> guardianPhone = getGuardianPhone();
        if (guardianPhone.isPresent()) {
            builder.append("; Guardian's Phone: ")
                    .append(guardianPhone.get());
        }

        Optional<Level> level = getLevel();
        if (level.isPresent()) {
            builder.append("; Level: ")
                    .append(level.get());
        }

        Set<Subject> subjects = getSubjects();
        if (!subjects.isEmpty()) {
            builder.append("; Subjects: ");
            subjects.forEach(builder::append);
        }

        Set<Lesson> lessonDetailsSet = getLessons();
        if (!lessonDetailsSet.isEmpty()) {
            builder.append("; Lessons: ");
            lessonDetailsSet.forEach(builder::append);
        }
        return builder.toString();
    }

}
