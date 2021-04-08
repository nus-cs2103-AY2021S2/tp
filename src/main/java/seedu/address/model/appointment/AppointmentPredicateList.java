package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

/**
 * List of {@code Predicate<Appointment>}, supports equality comparisons
 * with other {@code AppointmentPredicateLists}, and combining of all
 * predicates it holds into a single predicate.
 */
public class AppointmentPredicateList {
    private List<Predicate<Appointment>> predicates;

    public AppointmentPredicateList(List<Predicate<Appointment>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Combines all predicates in its internal list into a single
     * predicate.
     * @return {@code Predicate<Appointment>} that is combined with logical AND
     */
    public Predicate<Appointment> combine() {
        return this.predicates.stream().reduce(Predicate::and).orElse(x -> true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentPredicateList
                    && this.predicates.equals(((AppointmentPredicateList) other).predicates));
    }
}
