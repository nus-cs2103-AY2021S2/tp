package seedu.storemando.model.item.predicate;

import java.util.function.Predicate;

import seedu.storemando.model.item.Item;

/**
 * Tests that a {@code Item}'s {@code location} matches any of the keywords given.
 */
public class LocationContainsPredicate implements Predicate<Item> {
    /**
     * The keyword that is use to be filter on.
     */
    private final String keyword;

    public LocationContainsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Item item) {
        return keyword.equals(item.getLocation().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LocationContainsPredicate // instanceof handles nulls
            && keyword.equals(((LocationContainsPredicate) other).keyword)); // state check
    }

}
