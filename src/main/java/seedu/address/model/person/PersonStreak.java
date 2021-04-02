package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Class representation of a pair of Person and Streak.
 */
public class PersonStreak implements Comparable<PersonStreak> {
    private final Person person;
    private final Streak streak;

    /**
     * Every field must be present and not null.
     */
    public PersonStreak(Person person, Streak streak) {
        requireAllNonNull(person, streak);
        this.person = person;
        this.streak = streak;
    }

    /**
     * Creates a PersonStreak from the given {@code Person}
     */
    public static PersonStreak fromPerson(Person p) {
        requireNonNull(p);
        return new PersonStreak(p, Streak.from(p.getGoal(), p.getMeetings()));
    }

    public Person getPerson() {
        return person;
    }

    public Streak getStreak() {
        return streak;
    }

    public boolean isActiveGoal() {
        return !person.getGoal().isNoneFrequency();
    }

    @Override
    public int compareTo(PersonStreak other) {
        int cmpStreak = streak.compareTo(other.streak);
        if (cmpStreak != 0) {
            return cmpStreak;
        }

        String thisName = person.getName().fullName;
        String otherName = other.person.getName().fullName;
        return thisName.compareTo(otherName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonStreak that = (PersonStreak) o;
        return Objects.equals(person, that.person) && Objects.equals(streak, that.streak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, streak);
    }
}
