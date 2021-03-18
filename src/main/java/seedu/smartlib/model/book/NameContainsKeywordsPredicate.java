package seedu.smartlib.model.book;

import java.util.List;
import java.util.function.Predicate;

import seedu.smartlib.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Book> {

    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(book.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.smartlib.model.book.NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((seedu.smartlib.model.book.NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
