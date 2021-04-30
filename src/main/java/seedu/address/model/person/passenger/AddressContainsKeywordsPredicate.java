package seedu.address.model.person.passenger;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Passenger> {
    private final List<String> keywords;

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Passenger passenger) {
        return keywords.stream()
                .anyMatch(keyword -> {
                    String passengerAddressLowerCase = passenger.getAddress().toString()
                            .toLowerCase().replaceAll("\\s+", " ");

                    return passengerAddressLowerCase.contains(keyword);
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords)); // state check
    }

}
