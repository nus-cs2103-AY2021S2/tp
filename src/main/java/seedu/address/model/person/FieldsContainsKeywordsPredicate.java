package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Email}, {@code Tags} or {@code Remark} matches any of the keywords
 * given.
 */
public class FieldsContainsKeywordsPredicate implements Predicate<Person> {
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.containsAll(((FieldsContainsKeywordsPredicate) other).keywords)
                && ((FieldsContainsKeywordsPredicate) other).keywords.containsAll(keywords)); // state check
    }

}
