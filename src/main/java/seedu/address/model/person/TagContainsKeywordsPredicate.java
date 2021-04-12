package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private static final int MATCH_PERCENTAGE = 60;
    private final List<String> keywords;
    private final Logger logger = LogsCenter.getLogger(getClass());

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword -> {
            int matchPercentage = matchAllTags(person, keyword);

            logger.fine(String.format("Tags: %s, Word: %s, Match: %d",
                    extractTagsToString(person),
                    keyword,
                    matchPercentage));
            return matchPercentage > MATCH_PERCENTAGE;
        });
    }

    private int match(String a, String b) {
        return StringUtil.oneWayPartialFuzzyMatch(b, a);
    }

    private int matchAllTags(Person person, String keyword) {
        return person
            .getTags()
            .stream()
            .map(tag -> match(tag.tagName, keyword))
            .max(Integer::compareTo)
            .orElse(0);
    }

    private String extractTagsToString(Person person) {
        return person.getTags().stream().map(tag -> tag.tagName)
                .reduce("", (t1, t2) -> t1 + " " + t2);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.containsAll(((TagContainsKeywordsPredicate) other).keywords)
                && ((TagContainsKeywordsPredicate) other).keywords.containsAll(keywords)); // state check
    }

}
