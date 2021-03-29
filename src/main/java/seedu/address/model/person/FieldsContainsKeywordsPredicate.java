package seedu.address.model.person;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import seedu.address.commons.core.LogsCenter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Email}, {@code Tags} or {@code Remark} matches any of the keywords
 * given.
 */
public class FieldsContainsKeywordsPredicate implements Predicate<Person>, Comparator<Person> {
    private static final int MATCH_PERCENTAGE = 60;
    private final List<String> keywords;
    private final ArrayList<Predicate<Person>> predicates;
    private final Logger logger = LogsCenter.getLogger(getClass());

    public FieldsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        predicates = new ArrayList<>();
        predicates.add(new EmailContainsKeywordsPredicate(keywords));
        predicates.add(new RemarkContainsKeywordsPredicate(keywords));
        predicates.add(new TagContainsKeywordsPredicate(keywords));
        predicates.add(new NameContainsKeywordsPredicate(keywords));
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword -> predicates.stream().anyMatch(predicate -> predicate.test(person)));
    }

    @Override
    public int compare(Person o1, Person o2) {
        int o1Similarity = keywords
                .stream()
                .map(keyword -> (int)predicates.stream().map(predicate -> test(o1)).filter(bool -> bool).count())
                .max(Integer::compare)
                .orElse(0);
        int o2Similarity = keywords
                .stream()
                .map(keyword -> (int)predicates.stream().map(predicate -> test(o2)).filter(bool -> bool).count())
                .max(Integer::compare)
                .orElse(0);
        return o2Similarity - o1Similarity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FieldsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
