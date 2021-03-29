package seedu.address.model.person;

import java.util.Objects;

public class PersonStreak implements Comparable<PersonStreak> {
    private final Person person;
    private final Streak streak;

    public PersonStreak(Person person, Streak streak) {
        this.person = person;
        this.streak = streak;
    }

    public static PersonStreak fromPerson(Person p) {
        return new PersonStreak(p, Streak.from(p.getGoal(), p.getMeetings()));
    }

    @Override
    public int compareTo(PersonStreak o) {
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
