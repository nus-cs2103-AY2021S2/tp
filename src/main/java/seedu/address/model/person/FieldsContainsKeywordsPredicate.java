package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Email}, {@code Tags} or {@code Remark} matches any of the keywords
 * given.
 */
public class FieldsContainsKeywordsPredicate implements Predicate<Person>, Comparator<Person> {
    private final List<String> keywords;
    private final ArrayList<Predicate<Person>> predicates;

    /**
     * Constructs a predicate for each of the fields using the keywords provided
     * @param keywords keywords for searching each of the fields
     */
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
        return predicates.stream().anyMatch(predicate -> predicate.test(person));
    }

    @Override
    public int compare(Person o1, Person o2) {
        int o1Similarity = keywords
                .stream()
                .map(keyword -> (int) predicates
                        .stream()
                        .map(predicate -> predicate.test(o1))
                        .filter(bool -> bool)
                        .count())
                .max(Integer::compare)
                .orElse(0);
        int o2Similarity = keywords
                .stream()
                .map(keyword -> (int) predicates
                        .stream()
                        .map(predicate -> predicate.test(o2))
                        .filter(bool -> bool)
                        .count())
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
