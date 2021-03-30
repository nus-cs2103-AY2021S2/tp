package dog.pawbook.model.managedentity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import javafx.util.Pair;

/**
 * Predicate to determine if a {@code Program} falls on a given date. To be used in conjunction with
 * {@code IS_PROGRAM_PREDICATE}.
 */
public class ProgramOccursOnDatePredicate implements Predicate<Pair<Integer, Entity>> {
    private final LocalDate date;

    /**
     * Constructs a predicate that returns true for programs that occur on the given {@code date}.
     */
    public ProgramOccursOnDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Pair<Integer, Entity> idEntityPair) {
        assert idEntityPair.getValue() instanceof Program;
        Program program = (Program) idEntityPair.getValue();
        return program.getSessions().stream()
                .map(Session::getDate)
                .map(LocalDateTime::toLocalDate)
                .anyMatch(date::equals);
    }
}
