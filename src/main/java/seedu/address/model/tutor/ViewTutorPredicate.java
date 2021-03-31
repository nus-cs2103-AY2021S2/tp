package seedu.address.model.tutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ViewTutorPredicate implements Predicate<Tutor> {
    private final List<Tutor> targetTutors;

    public ViewTutorPredicate(Tutor targetTutor) {
        this.targetTutors = new ArrayList<>(List.of(targetTutor));
    }

    public ViewTutorPredicate(List<Tutor> targetTutors) {
        this.targetTutors = targetTutors;
    }

    @Override
    public boolean test(Tutor tutor) {

        for (Tutor p: targetTutors) {
            if (p.isSameTutor(tutor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tutor.ViewTutorPredicate // instanceof handles nulls
                && targetTutors.equals(((ViewTutorPredicate) other).targetTutors)); // state check
    }
}
