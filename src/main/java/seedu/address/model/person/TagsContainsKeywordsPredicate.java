package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.AgeParser;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TagsContainsKeywordsPredicate implements Predicate<Person> {


    private final String tag;

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
