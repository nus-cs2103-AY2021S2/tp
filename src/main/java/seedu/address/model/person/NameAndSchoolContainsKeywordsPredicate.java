package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameAndSchoolContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> schoolKeywords;
    private final List<String> tagKeywords;

    /**
     * Constructor of NameAndSchoolContainsKeywordsPredicate
     *
     * @param nameKeywords List of keywords to be matched with the names
     * @param schoolKeywords List of keywords to be matched the school
     * @param tagKeywords List of keywords to be matched with tags
     */
    public NameAndSchoolContainsKeywordsPredicate(List<String> nameKeywords,
                                                  List<String> schoolKeywords, List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.schoolKeywords = schoolKeywords;
        this.tagKeywords = tagKeywords;
    }

    /**
     * Evaluates if the keyword matches the person's name
     * @param person The person to be compared to
     * @return A boolean value representing the evaluation results
     */
    public boolean testByName(Person person) {
        if (person == null) {
            return false;
        }
        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    /**
     * Evaluates if the keyword matches the person's school
     * @param person The person to be compared to
     * @return A boolean value representing the evaluation results
     */
    public boolean testBySchool(Person person) {
        if (person == null) {
            return false;
        }
        return schoolKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getSchool().fullSchoolName, keyword));
    }

    /**
     * Evaluates if the keyword matches the person's tags
     * @param person The person to be compared to
     * @return A boolean value representing the evaluation results
     */
    public boolean testByTag(Person person) {
        if (person == null) {
            return false;
        }
        return tagKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getTags().stream().map(tag -> tag.tagName)
                                .collect(Collectors.joining(" ")), keyword));
    }

    @Override
    public boolean test(Person person) {
        boolean isNamePresent = testByName(this.nameKeywords != null ? person : null);
        boolean isSchoolPresent = testBySchool(this.schoolKeywords != null ? person : null);
        boolean isTagPresent = testByTag(this.tagKeywords != null ? person : null);
        return isNamePresent || isSchoolPresent || isTagPresent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameAndSchoolContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((NameAndSchoolContainsKeywordsPredicate) other).nameKeywords)
                && schoolKeywords.equals(((NameAndSchoolContainsKeywordsPredicate) other)
                .schoolKeywords)
                && tagKeywords.equals(((NameAndSchoolContainsKeywordsPredicate) other)
                .tagKeywords)); // state check
    }

}
