package seedu.smartlib.model.book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.smartlib.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Name} matches any of the keywords given.
 */
public class BookNameContainsKeywordsPredicate implements Predicate<Book> {

    private final List<String> keywords;

    /**
     * Creates a BookNameContainsKeywordPredicate.
     *
     * @param keywords list of keywords
     */
    public BookNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the book contains any of the keywords.
     *
     * @param book the book to be tested.
     * @return true if the book contains any of the keywords, and false otherwise.
     */
    @Override
    public boolean test(Book book) {
        ArrayList<String> bookInfo = new ArrayList<>();
        bookInfo.add(book.getName().toString());
        bookInfo.add(book.getAuthor().toString());
        bookInfo.add(book.getGenre().toString());
        bookInfo.add(book.getPublisher().toString());
        bookInfo.add(book.getIsbn().toString());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAnyIgnoreCase(
                        bookInfo, keyword));
    }

    /**
     * Checks if this BookNameContainsKeywordPredicate is equal to another BookNameContainsKeywordPredicate.
     *
     * @param other the other BookNameContainsKeywordPredicate to be compared.
     * @return true if this BookNameContainsKeywordPredicate is equal to the other BookNameContainsKeywordPredicate,
     *         and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BookNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

