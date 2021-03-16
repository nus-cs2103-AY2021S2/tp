package seedu.storemando.model.expirydate;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.storemando.model.item.Item;

/**
 * Tests that a {@code Item}'s {@code expiry date} is within the stipulated days from today.
 */
public class ItemExpiringPredicate implements Predicate<Item> {
    private final Long numOfDays;

    public ItemExpiringPredicate(Long numOfDays) {
        this.numOfDays = numOfDays;
    }

    @Override
    public boolean test(Item item) {
        try {
            LocalDate itemExpiryDate = item.getExpiryDate().expiryDate;
            LocalDate today = LocalDate.now();
            Long daysDifference = DAYS.between(today, itemExpiryDate);
            return daysDifference <= numOfDays;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ItemExpiringPredicate // instanceof handles nulls
            && numOfDays == ((ItemExpiringPredicate) other).numOfDays); // state check
    }
}
