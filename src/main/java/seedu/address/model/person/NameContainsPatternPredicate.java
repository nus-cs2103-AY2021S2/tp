package seedu.address.model.person;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NameContainsPatternPredicate that = (NameContainsPatternPredicate) o;
        return Objects.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }
}
