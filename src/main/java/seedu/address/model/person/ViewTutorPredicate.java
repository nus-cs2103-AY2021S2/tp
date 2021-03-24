package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ViewTutorPredicate implements Predicate<Person> {
    private final List<Person> targetTutors;

    public ViewTutorPredicate(Person targetTutor) {
        this.targetTutors = new ArrayList<>(List.of(targetTutor));
    }

    public ViewTutorPredicate(List<Person> targetTutors) {
        this.targetTutors = targetTutors;
    }

    @Override
    public boolean test(Person tutor) {

        for (Person p: targetTutors) {
            if (p.equals(tutor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.ViewTutorPredicate // instanceof handles nulls
                && targetTutors.equals(((ViewTutorPredicate) other).targetTutors)); // state check
    }
}
