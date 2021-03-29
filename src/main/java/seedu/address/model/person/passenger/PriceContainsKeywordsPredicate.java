package seedu.address.model.person.passenger;

import java.util.function.Predicate;


/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class PriceContainsKeywordsPredicate implements Predicate<Passenger> {
    private final Double price;

    public PriceContainsKeywordsPredicate(Double price) {
        this.price = price;
    }

    @Override
    public boolean test(Passenger passenger) {
        if (passenger.getPrice().isPresent()) {
            Price priceToCompare = passenger.getPrice().get();
            Double userPrice = Double.parseDouble(priceToCompare.toString());

            return userPrice >= this.price;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceContainsKeywordsPredicate // instanceof handles nulls
                && price.equals(((PriceContainsKeywordsPredicate) other).price)); // state check
    }

}
