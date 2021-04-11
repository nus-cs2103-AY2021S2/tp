package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.FilterKeywordChecker;
import seedu.address.model.insurance.predicates.PlansContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.predicates.TagsContainsKeywordsPredicate;

public class AttributeContainsKeywordsPredicate implements Predicate<Person> {


    private final List<String> keywords;

    /**
     * Constructs an {@code AttributeContainsKeywordsPredicate} to be used in the predicate test
     *
     * @param keywords a user filter search input.
     */
    public AttributeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        String keywordString = "";
        for (String s : keywords) {
            keywordString = keywordString + s + " ";
        }
        return keywordString;
    }

    @Override
    public boolean test(Person person) {

        return keywords.stream().anyMatch(keyword -> {
            FilterKeywordChecker input = new FilterKeywordChecker(keyword);
            if (input.isTag()) {
                return new TagsContainsKeywordsPredicate(input.value()).test(person);
            } else if (input.isAddress()) {
                return new AddressContainsKeywordsPredicate(input.value()).test(person);
            } else if (input.isGender()) {
                return new GenderContainsKeywordsPredicate(input.value()).test(person);
            } else if (input.isPlan()) {
                return new PlansContainsKeywordsPredicate(input.value()).test(person);
            } else if (input.isAge()) {
                return new AgeIsInRangePredicate(input.value()).test(person);
            } else {
                return true;
            }
        });

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AttributeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
