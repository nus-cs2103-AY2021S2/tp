package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable with the exception of groups.
 * The groups inside the set groups may change as people are added into these groups.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Birthday birthday;
    private final Goal goal;

    // Data fields
    private final Address address;
    private final Picture picture;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Event> dates = new ArrayList<>();
    private final List<Event> meetings = new ArrayList<>();

    /**
     * Bare minimum fields to create a Person. Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Birthday birthday, Goal goal, Address address, Set<Tag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.goal = goal;
        this.address = address;
        this.picture = null;
        this.tags.addAll(tags);
    }

    /**
     * Used for immutable editing
     */
    public Person(Name name, Phone phone, Email email, Birthday birthday, Goal goal, Address address, Picture picture,
            Set<Tag> tags, List<Event> dates, List<Event> meetings) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.goal = goal;
        this.address = address;
        this.picture = picture;
        this.tags.addAll(tags);
        this.dates.addAll(dates);
        this.meetings.addAll(meetings);
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

    public Birthday getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<Picture> getPicture() {
        return Optional.ofNullable(picture);
    }

    public Goal getGoal() {
        return this.goal;
    }

    public Person withPicture(Picture picture) {
        return new Person(name, phone, email, birthday, goal, address, picture, tags, dates, meetings);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public List<Event> getDates() {
        return Collections.unmodifiableList(dates);
    }

    public Person withDates(List<Event> dates) {
        return new Person(name, phone, email, birthday, goal, address, picture, tags, dates, meetings);
    }

    public Person withGoal(Goal goal) {
        return new Person(name, phone, email, birthday, goal, address, picture, tags, dates, meetings);
    }

    public List<Event> getMeetings() {
        return Collections.unmodifiableList(meetings);
    }

    public Person withMeetings(List<Event> meetings) {
        return new Person(name, phone, email, birthday, goal, address, picture, tags, dates, meetings);
    }

    public LocalDate getGoalDeadline(LocalDate date) {
        if (this.meetings.isEmpty()) {
            // user has never met up with this person before
            return DateUtil.ZERO_DAY;
        }
        return this.goal.getNext(this.meetings.stream()
                .map(Event::getDate)
                .filter(x -> x.isBefore(date))
                .max(LocalDate::compareTo).orElse(DateUtil.ZERO_DAY));
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
                && otherPerson.getBirthday().equals(getBirthday())
                && otherPerson.getGoal().equals(getGoal())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getPicture().equals(getPicture())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getDates().equals(getDates())
                && otherPerson.getMeetings().equals(getMeetings());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, birthday, goal, address, picture, tags, dates, meetings);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Birthday: ")
                .append(getBirthday())
                .append("; Goal: ")
                .append(getGoal())
                .append("; Address: ")
                .append(getAddress())
                .append("; Picture: ")
                .append(getPicture());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Event> dates = getDates();
        if (!dates.isEmpty()) {
            builder.append("; Dates: ");
            dates.forEach(builder::append);
        }

        List<Event> meetings = getMeetings();
        if (!meetings.isEmpty()) {
            String meetingsStr = meetings
                    .stream()
                    .map(Event::toString)
                    .collect(Collectors.joining(", "));
            builder.append("; Meetings: ");
            builder.append(meetingsStr);
        }

        return builder.toString();
    }
}
