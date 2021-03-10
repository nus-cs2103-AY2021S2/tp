package seedu.hippocampus.model.person.predicates;

import java.util.function.Predicate;

import seedu.hippocampus.model.person.Person;

public class TagsContainsTagPredicate implements Predicate<Person> {
    private final String tag;

    public TagsContainsTagPredicate(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tag -> tag.tagName.equals(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsTagPredicate // instanceof handles nulls
                && tag.equals(((TagsContainsTagPredicate) other).tag)); // state check
    }

}
