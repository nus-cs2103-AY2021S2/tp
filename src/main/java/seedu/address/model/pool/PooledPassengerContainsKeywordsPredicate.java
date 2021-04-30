package seedu.address.model.pool;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class PooledPassengerContainsKeywordsPredicate implements Predicate<Pool> {
    private final List<String> keywords;

    public PooledPassengerContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Pool pool) {
        return keywords.stream()
                .anyMatch(keyword ->
                        pool.getPassengers().stream().anyMatch(passenger -> {
                            String passengerNameLowerCase = passenger.getName().toString()
                                    .toLowerCase().replaceAll("\\s+", " ");
                            return passengerNameLowerCase.contains(keyword);
                        }));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PooledPassengerContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PooledPassengerContainsKeywordsPredicate) other).keywords)); // state check
    }

}
