package seedu.address.model.person;

import java.util.List;
import java.util.Objects;

/**
 * Represents a streak in the address book.
 * Guaranteed to be immutable
 */
public class Streak {

    private final int value;

    private Streak(int value) {
        this.value = value;
    }

    public static Streak empty() {
        return new Streak(0);
    }

    public static Streak from(Goal goal, List<Event> meetings) {
        // Do the nasty calculations here
        return new Streak(0);
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
}
