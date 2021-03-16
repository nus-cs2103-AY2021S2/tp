package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameAndSchoolContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> schoolKeywords;

    /**
     * Constructor of NameAndSchoolContainsKeywordsPredicate
     * @param nameKeywords List of keywords to be matched with the names
     * @param schoolKeywords List of keywords to be matched the school
     */
    public NameAndSchoolContainsKeywordsPredicate(List<String> nameKeywords, List<String> schoolKeywords) {
        this.nameKeywords = nameKeywords;
        this.schoolKeywords = schoolKeywords;
    }

    @Override
    public boolean test(Person person) {
        if (this.nameKeywords == null) {
            return schoolKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getSchool().fullSchoolName, keyword));
        } else if (this.schoolKeywords == null) {
            return nameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        } else {
            return nameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword))
                    || schoolKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getSchool().fullSchoolName, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameAndSchoolContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((NameAndSchoolContainsKeywordsPredicate) other).nameKeywords)
                && schoolKeywords.equals(((NameAndSchoolContainsKeywordsPredicate) other)
                .schoolKeywords)); // state check
    }

}
