package seedu.address.model.tag.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

public class TagsContainsKeywordsPredicate implements Predicate<Person> {


    private final String tag;

    /**
     * Constructs an {@code TagsContainsKeywordsPredicate} to be used in the predicate test
     *
     * @param keywords a user filter search input.
     */
    public TagsContainsKeywordsPredicate(String keywords) {
        this.tag = keywords;
    }


    @Override
    public boolean test(Person person) {

        return tag == null || StringUtil.containsTagIgnoreCase(person.getTags(), tag);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && tag.equals(((TagsContainsKeywordsPredicate) other).tag)); // state check
    }

}
