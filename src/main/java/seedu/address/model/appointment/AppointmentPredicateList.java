package seedu.address.model.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * List of {@code Predicate<Appointment>}, supports equality comparisons
 * with other {@code AppointmentPredicateLists}, and combining of all
 * predicates it holds into a single predicate.
 */
public class AppointmentPredicateList {
    private List<Predicate<Appointment>> predicates;
    private List<AppointmentPredicateList> otherPredicates;

    /**
     * Constructs an AppointmentPredicateList with a list of predicates.
     */
    public AppointmentPredicateList(List<Predicate<Appointment>> predicates) {
        this.predicates = predicates;
        this.otherPredicates = new ArrayList<>();
    }

    /**
     * Constructs an AppointmentPredicateList with a list of predicates and another
     * list of AppointmentPredicateList that to form a disjunction each.
     */
    public AppointmentPredicateList(List<Predicate<Appointment>> predicates,
                                    List<AppointmentPredicateList> otherPredicates) {
        this.otherPredicates = otherPredicates;
        this.predicates = predicates;
    }

    /**
     * Combines all predicates in its internal list into a single
     * predicate.
     * @return {@code Predicate<Appointment>} that is combined with logical AND
     */
    public Predicate<Appointment> combine() {
        if (!otherPredicates.isEmpty()) {
            for (AppointmentPredicateList predicateList : otherPredicates) {
                this.predicates.add(predicateList.combineDisjunction());
            }
        }
        return this.predicates.stream().reduce(Predicate::and).orElse(x -> true);
    }

    public Predicate<Appointment> combineDisjunction() {
        return this.predicates.stream().reduce(Predicate::or).orElse(x -> true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentPredicateList
                    && this.predicates.equals(((AppointmentPredicateList) other).predicates)
                    && this.otherPredicates.equals(((AppointmentPredicateList) other).otherPredicates));
    }
}
