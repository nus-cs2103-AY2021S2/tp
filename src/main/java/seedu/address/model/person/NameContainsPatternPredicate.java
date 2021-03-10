package seedu.address.model.person;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Tests that a {@code Person}'s {@code Name} matches the pattern given.
 */
public class NameContainsPatternPredicate implements Predicate<Person> {
    private final Pattern pattern;

    public NameContainsPatternPredicate(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean test(Person person) {
        return pattern.matcher(person.getName().toString()).find();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        NameContainsPatternPredicate patternPredicate = (NameContainsPatternPredicate) other;
        return pattern.toString().equals(patternPredicate.pattern.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }
}
