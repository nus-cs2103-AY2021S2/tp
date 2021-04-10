package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
import seedu.address.model.subject.Subject;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameSchoolAndSubjectContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> schoolKeywords;
    private final List<Subject> subjectKeywords;

    /**
     * Constructor of NameAndSchoolContainsKeywordsPredicate
     *
     * @param nameKeywords List of keywords to be matched with the names
     * @param schoolKeywords List of keywords to be matched the school
     * @param subjectKeywords List of keywords to be matched with subjects
     */
    public NameSchoolAndSubjectContainsKeywordsPredicate(List<String> nameKeywords,
                                                         List<String> schoolKeywords, List<Subject> subjectKeywords) {
        this.nameKeywords = nameKeywords;
        this.schoolKeywords = schoolKeywords;
        this.subjectKeywords = subjectKeywords;
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
        if (!person.getSchool().isPresent()) {
            return false;
        }
        return schoolKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getSchool().get().fullSchoolName, keyword));
    }

    /**
     * Evaluates if the keyword matches the person's subjects
     * @param person The person to be compared to
     * @return A boolean value representing the evaluation results
     */
    public boolean testBySubject(Person person) {
        if (person == null) {
            return false;
        }
        return subjectKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getSubjects().stream().map(subject -> subject.subjectName)
                                .collect(Collectors.joining(" ")), keyword.subjectName));
    }

    @Override
    public boolean test(Person person) {
        boolean isNamePresent = testByName(this.nameKeywords != null ? person : null);
        boolean isSchoolPresent = testBySchool(this.schoolKeywords != null ? person : null);
        boolean isSubjectPresent = testBySubject(this.subjectKeywords != null ? person : null);
        return isNamePresent || isSchoolPresent || isSubjectPresent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameSchoolAndSubjectContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((NameSchoolAndSubjectContainsKeywordsPredicate) other).nameKeywords)
                && schoolKeywords.equals(((NameSchoolAndSubjectContainsKeywordsPredicate) other)
                .schoolKeywords)
                && subjectKeywords.equals(((NameSchoolAndSubjectContainsKeywordsPredicate) other)
                .subjectKeywords)); // state check
    }

}
