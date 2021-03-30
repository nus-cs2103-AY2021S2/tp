package seedu.address.model.person.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class HasTagPredicate implements Predicate<Person> {

    public HasTagPredicate() {
    }

    @Override
    public boolean test(Person person) {
        ArrayList<Tag> tagList = new ArrayList<>(person.getTags());
        return tagList.isEmpty() ? false : true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasLessonPredicate); // instanceof handles nulls
    }

}
