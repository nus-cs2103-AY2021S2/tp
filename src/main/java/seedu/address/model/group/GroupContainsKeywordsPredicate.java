package seedu.address.model.group;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Group} matches any of the keywords given.
 */
public class GroupContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public GroupContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Group> groups = person.getGroups();
        return keywords.stream()
                .anyMatch(keyword ->
                        groups.stream().anyMatch(group ->
                                StringUtil.containsWordIgnoreCase(group.getGroupName(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.group.GroupContainsKeywordsPredicate // instanceof handles null
                && keywords.equals(((seedu.address.model.group.GroupContainsKeywordsPredicate) other).keywords));
        // state check
    }

}
