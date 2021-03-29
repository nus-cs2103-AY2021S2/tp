package seedu.address.model.person;

public class PersonStreak {
    private final Person person;
    private final Streak streak;

    public PersonStreak(Person person, Streak streak) {
        this.person = person;
        this.streak = streak;
    }
}
