package seedu.timeforwheels.model.customer;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.timeforwheels.commons.util.StringUtil;
import seedu.timeforwheels.model.tag.Tag;

/**
 * Tests that a {@code Customer}'s {@code Name} matches any of the keywords given.
 */
public class AttributeContainsKeywordsPredicate implements Predicate<Customer> {
    private final List<String> keywords;

    public AttributeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Customer customer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(customer.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(customer.getAddress().value, keyword)
                        || StringUtil.containsWordIgnoreCase(customer.getPhone().value, keyword)
                        || StringUtil.containsWordIgnoreCase(customer.getDate().value, keyword)
                        || StringUtil.containsWordIgnoreCase(customer.getDone().value, keyword)
                        || StringUtil.containsWordIgnoreCase(customer.getEmail().value, keyword)
                        || StringUtil.containsWordIgnoreCase(customer.getRemark().value, keyword)
                        || matchTags(keyword, customer.getTag()));
    }

    /**
     * Returns true if keyword matches a certain tag
     *
     * @param keyword
     * @param tags
     * @return if a match is found
     */
    public boolean matchTags(String keyword, Set<Tag> tags) {
        boolean result = false;
        if (tags.isEmpty()) {
            return false;
        } else {
            for (Tag tag : tags) {
                if (StringUtil.containsWordIgnoreCase(tag.tagName, keyword)) {
                    result = true;
                }
            }
            return result;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AttributeContainsKeywordsPredicate) other).keywords)); // state check
    }
}
