package seedu.address.model.person;

import static java.time.DayOfWeek.SUNDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.function.Function;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.parser.exceptions.ParseException;

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

    /**
     * Constructs a {@code Goal} instance of frequency type NONE.
     */
    public Goal() {
        this.frequency = Frequency.NONE;
    }

    /**
     * Constructs a {@code Goal} instance with the given frequency.
     */
    public Goal(Frequency frequency) {
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public boolean isNoneFrequency() {
        return this.getFrequency().equals(Frequency.NONE);
    }

    private static boolean isValidFrequencyString(String frequencyString) {
        return Goal.ENUM_MAP.containsKey(frequencyString);
    }

    /**
     * Calculates the next goal deadline from the given {@code date}. Deadlines are guaranteed to fall on Sundays.
     */
    public LocalDate getGoalDeadline(LocalDate date) {
        if (date == null || getFrequency().equals(Frequency.NONE)) {
            return null;
        }

        LocalDate deadline = FREQUENCY_MAP.get(getFrequency()).apply(date);
        return deadline.with(TemporalAdjusters.nextOrSame(SUNDAY));
    }

    public static boolean isValidGoal(String goal) {
        return goal.equals("WEEKLY") || goal.equals("MONTHLY") || goal.equals("YEARLY") || goal.equals("NONE");
    }

    /**
     * Converts a key defined in {@code ENUM_MAP} to frequency type.
     * @throws ParseException if the provided {@code frequencyString} is invalid.
     */
    public static Frequency parseFrequency(String frequencyString) throws ParseException {
        if (!Goal.isValidFrequencyString(frequencyString)) {
            throw new ParseException("Invalid frequency string " + frequencyString + " provided");
        }
        return Goal.ENUM_MAP.get(frequencyString);
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
