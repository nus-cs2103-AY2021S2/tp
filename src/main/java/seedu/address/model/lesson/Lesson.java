package seedu.address.model.lesson;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

public class Lesson implements Comparable<Lesson> {

    public static final String MESSAGE_CONSTRAINTS = "Lesson should only consist of two words - the lesson day, "
            + "followed by the lesson time. \nExample: monday 1500";
    public static final int INDEX_OF_DAY = 0;
    public static final int INDEX_OF_TIME = 1;
    public static final int SIZE_OF_ARRAY = 2;

    public final Day day;
    public final Time time;

    private final Set<Person> persons = new HashSet<>();

    /**
     * Constructs a {@code Lesson}.
     *
     * @param lesson Lesson details consisting of day and time.
     */
    public Lesson(String lesson) {
        requireAllNonNull(lesson);
        String[] details = getDetails(lesson);
        checkArgument(isValidLesson(details), MESSAGE_CONSTRAINTS);
        this.day = new Day(details[INDEX_OF_DAY]);
        this.time = new Time(details[INDEX_OF_TIME]);
    }

    /**
     * Constructs a {@code Lesson}.
     *
     * @param lesson Lesson details consisting of day and time.
     */
    public Lesson(String lesson, Set<Person> persons) {
        this(lesson);
        requireAllNonNull(persons);
        this.persons.addAll(persons);
    }

    public Day getDay() {
        return day;
    }

    public String getDayInString() {
        return day.toString();
    }

    public Set<Person> getPersonSet() {
        return this.persons;
    }

    public Time getTime() {
        return time;
    }

    public String getTimeInString() {
        return time.timeOfTuition;
    }

    public boolean isEmptyLesson() {
        return this.persons.isEmpty();
    }

    public int getNumberOfPerson() {
        return this.persons.size();
    }

    /**
     * Adds a person to the lesson.
     *
     * @param person person to be added.
     */
    public void addPerson(Person person) {
        if (!containsPerson(person)) {
            persons.add(person);
        }
    }

    /**
     * Removes a person from the lesson.
     *
     * @param person person to be removed.
     */
    public void removePerson(Person person) {
        if (containsPerson(person)) {
            persons.remove(person);
        }
    }

    public Set<Person> getPerson() {
        return Collections.unmodifiableSet(persons);
    }

    public String getPersonInString() {
        return persons.stream().map(person -> person.getName().fullName)
                .collect(Collectors.joining(", "));
    }

    /**
     * Returns true if the lesson already stores the given person.
     *
     * @param person Person to be checked if it already exists.
     * @return a boolean value indicating if the lesson already stores the given person.
     */
    public boolean containsPerson(Person person) {
        for (Person p : persons) {
            if (p.isSamePerson(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Separates the input string into individual strings for processing.
     *
     * @param input User input.
     * @return An array of strings for creation of a Lesson.
     */
    public static String[] getDetails(String input) {
        String trimmedInput = input.trim();
        String[] details = trimmedInput.split(" ");
        return details;
    }

    /**
     * Returns true if a given string is a valid lesson.
     */
    public static boolean isValidLesson(String[] test) {
        if (test.length != SIZE_OF_ARRAY) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns true if both lessons have the same day and time.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getDay().equals(getDay())
                && otherLesson.getTime().equals(getTime());
    }

    @Override
    public int compareTo(Lesson other) {
        if (this.day.compareTo(other.day) == -1) {
            return -1;
        } else if (this.day.compareTo(other.day) == 1) {
            return 1;
        } else {
            if (this.time.compareTo(other.time) == -1) {
                return -1;
            } else if (this.time.compareTo(other.time) == 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getDay().equals(getDay())
                && otherLesson.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(day, time);
    }

    @Override
    public String toString() {
        return "[" + day.toString() + " " + time.toString() + "]";
    }

    public String formatString() {
        return day.toString() + " " + time.toString();
    }

}
