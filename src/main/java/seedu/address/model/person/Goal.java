package seedu.address.model.person;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Function;

public class Goal {

    public static final String MESSAGE_CONSTRAINTS = "Goal should only be of type WEEKLY, MONTHLY, YEARLY, or NONE";

    public enum Frequency {
        WEEKLY, MONTHLY, YEARLY, NONE
    };

    public static final HashMap<String, Frequency> ENUM_MAP = new HashMap<>();

    static {
        ENUM_MAP.put("w", Frequency.WEEKLY);
        ENUM_MAP.put("m", Frequency.MONTHLY);
        ENUM_MAP.put("y", Frequency.YEARLY);
        ENUM_MAP.put("n", Frequency.NONE);
        ENUM_MAP.put("week", Frequency.WEEKLY);
        ENUM_MAP.put("month", Frequency.MONTHLY);
        ENUM_MAP.put("year", Frequency.YEARLY);
        ENUM_MAP.put("weekly", Frequency.WEEKLY);
        ENUM_MAP.put("monthly", Frequency.MONTHLY);
        ENUM_MAP.put("yearly", Frequency.YEARLY);
        ENUM_MAP.put("none", Frequency.NONE);
    }

    private static final HashMap<Frequency, Function<LocalDate, LocalDate>> FREQUENCY_MAP = new HashMap<>();

    private final Frequency frequency;

    static {
        FREQUENCY_MAP.put(Frequency.WEEKLY, x -> x.plusWeeks(1));
        FREQUENCY_MAP.put(Frequency.MONTHLY, x -> x.plusMonths(1));
        FREQUENCY_MAP.put(Frequency.YEARLY, x -> x.plusYears(1));
    }

    public Goal() {
        this.frequency = Frequency.NONE;
    }

    public Goal(Frequency frequency) {
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public LocalDate getNext(LocalDate date) {
        if (date == null || this.getFrequency().equals(Frequency.NONE)) {
            return null;
        }
        return FREQUENCY_MAP.get(this.getFrequency()).apply(date);
    }

    public static boolean isValidGoal(String goal) {
        return goal.equals("WEEKLY") || goal.equals("MONTHLY") || goal.equals("YEARLY") || goal.equals("NONE");
    }

    @Override
    public String toString() {
        return this.frequency.toString();
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
