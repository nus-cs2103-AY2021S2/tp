package seedu.address.model.person;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.logging.Logger;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import seedu.address.commons.core.LogsCenter;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Person> {
    private static final int MATCH_PERCENTAGE = 60;
    private final List<String> keywords;
    private final Logger logger = LogsCenter.getLogger(getClass());

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword -> {
            int matchPercentage = match(person.getEmail().value, keyword);

            logger.fine(String.format("Email: %s, Word: %s, Match: %d",
                    person.getEmail().value, keyword, matchPercentage));
            return matchPercentage > MATCH_PERCENTAGE;
        });
    }

    private int match(String a, String b) {
        return FuzzySearch.partialRatio(a, b, str -> str.toLowerCase(Locale.ROOT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.containsAll(((EmailContainsKeywordsPredicate) other).keywords)
                && ((EmailContainsKeywordsPredicate) other).keywords.containsAll(keywords)); // state check
    }

}
