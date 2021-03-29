package seedu.smartlib.model.record;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.smartlib.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Name} matches any of the keywords given.
 */
public class RecordContainsBookNamePredicate implements Predicate<Record> {

    private final List<String> keywords;

    /**
     * Creates a RecordContainsBookNamePredicate.
     *
     * @param keywords list of keywords
     */
    public RecordContainsBookNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the bookname of the record contains any of the keywords.
     *
     * @param record the record to be tested.
     * @return true if the book contains any of the keywords, and false otherwise.
     */
    @Override
    public boolean test(Record record) {
        record.getBookName();
        ArrayList<String> bookInfo = new ArrayList<>();
        bookInfo.add(record.getBookName().toString());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAnyIgnoreCase(
                        bookInfo, keyword));
    }

    /**
     * Checks if this RecordContainsBookNamePredicate is equal to another RecordContainsBookNamePredicate.
     *
     * @param other the other RecordContainsBookNamePredicate to be compared.
     * @return true if this RecordContainsBookNamePredicate is equal to the other RecordContainsBookNamePredicate,
     *         and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.smartlib.model.record.RecordContainsBookNamePredicate
                && keywords.equals(((seedu.smartlib.model.record.RecordContainsBookNamePredicate) other).keywords));
    }

}

