package seedu.address.model.person.passenger;

import java.util.function.Predicate;


/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class PriceIsGreaterThanAmountPredicate implements Predicate<Passenger> {
    private final Double price;

    public PriceIsGreaterThanAmountPredicate(Double price) {
        this.price = price;
    }

    @Override
    public boolean test(Passenger passenger) {
        if (passenger.getPrice().isPresent()) {
            Price priceToCompare = passenger.getPrice().get();
            double userPrice = Double.parseDouble(priceToCompare.toString());

            return userPrice >= this.price;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceIsGreaterThanAmountPredicate // instanceof handles nulls
                && price.equals(((PriceIsGreaterThanAmountPredicate) other).price)); // state check
    }

}
