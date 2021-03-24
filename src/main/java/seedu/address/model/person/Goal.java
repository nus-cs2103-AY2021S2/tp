package seedu.address.model.person;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Function;

public class Goal {

    public enum Frequency {
        WEEKLY, MONTHLY, YEARLY
    };

    public static final HashMap<Frequency, Function<LocalDate, LocalDate>> ADD_MAP = new HashMap<>();

    static {
        ADD_MAP.put(Frequency.WEEKLY, x -> x.plusMonths(1));
        ADD_MAP.put(Frequency.MONTHLY, x -> x.plusMonths(1));
        ADD_MAP.put(Frequency.YEARLY, x -> x.plusYears(1));
    }

    private final Frequency frequency;

    public Goal(Frequency frequency) {
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public LocalDate getNext(LocalDate date) {
        if (date == null) {
            return null;
        }
        return ADD_MAP.get(this.getFrequency()).apply(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Goal goal = (Goal) o;

        return frequency == goal.frequency;
    }

    @Override
    public int hashCode() {
        return frequency != null ? frequency.hashCode() : 0;
    }
}
