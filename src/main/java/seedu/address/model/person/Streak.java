package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a streak in the address book.
 * Guaranteed to be immutable
 */
public class Streak implements Comparable<Streak> {

    private final int value;

    private Streak(int value) {
        this.value = value;
    }

    public static Streak empty() {
        return new Streak(0);
    }

    /**
     * Returns a Streak created from the given {@code goal} and {@code List<Meeting>}
     */
    public static Streak from(Goal goal, List<Meeting> meetings) {
        requireAllNonNull(goal, meetings);

        if (goal.isNoneFrequency() || meetings.isEmpty()) {
            return Streak.empty();
        }

        List<LocalDate> dates = meetings.stream()
                .map(Event::getDate)
                .sorted(LocalDate::compareTo)
                .collect(Collectors.toList());

        return calculateToday(goal, dates);
    }

    private static Streak calculateToday(Goal goal, List<LocalDate> dates) {
        requireAllNonNull(goal, dates);

        int currentStreak = 1;
        int idx = 1;
        LocalDate currentDate = dates.get(0);
        while (idx < dates.size()) {
            LocalDate deadline = goal.getGoalDeadline(currentDate);
            LocalDate nextDate = dates.get(idx);

            if (nextDate.compareTo(deadline) <= 0) {
                currentStreak += 1;
            } else {
                currentStreak = 1;
            }

            idx++;
            currentDate = nextDate;
        }

        // Check that it is possible to continue the streak
        LocalDate latestDate = currentDate;
        LocalDate latestGoalDeadline = goal.getGoalDeadline(latestDate);

        if (LocalDate.now().compareTo(latestGoalDeadline) > 0) {
            return new Streak(0);
        }

        return new Streak(currentStreak);
    }

    public int getValue() {
        return value;
    }

    public String toUi() {
        return String.format("%d", value);
    }

    @Override
    public int compareTo(Streak other) {
        // Highest value comes first
        return -Integer.compare(value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Streak streak = (Streak) o;
        return value == streak.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Streak{"
                + "value=" + value
                + '}';
    }
}
