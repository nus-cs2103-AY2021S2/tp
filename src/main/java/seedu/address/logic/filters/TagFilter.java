package seedu.address.logic.filters;

import java.util.Arrays;
import java.util.Objects;

import seedu.address.model.customer.Customer;

/**
 * This class is used as a predicate for a {@code Tag}, where given a space separated list of tags, the {@code test}
 * checks whether any of them has atleast a partial match with the {@code Customer} object passed into the {@code test}
 * function.
 */
public class TagFilter extends Filter {
    /**
     * Constructor to create the filter from a given filter string.
     * @param filterString the filter string against which to test
     */
    public TagFilter(String filterString) {
        super(filterString.trim());
        Objects.requireNonNull(filterString);
    }

    /**
     * Tests whether the given {@code Customer} has at least one tag which contains at least one of the tag keywords
     * given in the filter string provided to this object at creation time.
     *
     * @param customer the {@code Customer} object whose tags we have to match
     * @return whether the {@code Customer} object matches the tags requirements
     */
    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        return customer.getTags().stream().map(x -> x.tagName).anyMatch(x ->
            Arrays.stream(filterString.split(" ")).anyMatch(x::startsWith)
        );
        /*
        Object[] tags = customer.getTags().toArray();
        boolean containsTag = false;
        for (Object tag : tags) {
            if (((Tag) tag).tagName.trim().contains(filterString)) {
                containsTag = true;
                break;
            }
        }
        return containsTag;
        */
    }
}
