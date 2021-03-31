package seedu.address.model.person;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.logging.Logger;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import seedu.address.commons.core.LogsCenter;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Person>, Comparator<Person> {
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
    public int compare(Person o1, Person o2) {
        int o1Similarity = keywords
                .stream()
                .map(keyword -> match(o1.getEmail().value, keyword))
                .max(Integer::compare)
                .orElse(0);
        int o2Similarity = keywords
                .stream()
                .map(keyword -> match(o2.getEmail().value, keyword))
                .max(Integer::compare)
                .orElse(0);
        return o2Similarity - o1Similarity == 0
                ? o1.getEmail().value.compareTo(o2.getEmail().value)
                : o2Similarity - o1Similarity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.containsAll(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
