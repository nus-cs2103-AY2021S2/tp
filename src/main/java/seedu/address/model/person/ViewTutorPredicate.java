package seedu.address.model.person;

import java.util.function.Predicate;

public class ViewTutorPredicate implements Predicate<Person> {
    private final Person targetTutor;

    public ViewTutorPredicate(Person targetTutor) {
        this.targetTutor = targetTutor;
    }

    @Override
    public boolean test(Person tutor) {
        return targetTutor.equals(tutor);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.ViewTutorPredicate // instanceof handles nulls
                && targetTutor.equals(((ViewTutorPredicate) other).targetTutor)); // state check
    }
}
