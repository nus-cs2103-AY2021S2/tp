package seedu.address.model.person.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Compares two {@code Person}s according to the order of their {@code Tag}.
 */
public class PersonTagComparator implements Comparator<Person> {
    public PersonTagComparator(){}
    @Override
    public int compare(Person p1, Person p2) {

        ArrayList<Tag> thisTagList = new ArrayList<>(p1.getTags());
        ArrayList<Tag> otherTagList = new ArrayList<>(p2.getTags());

        if (thisTagList.isEmpty() && !otherTagList.isEmpty()) {
            return 1;
        } else if (!thisTagList.isEmpty() && otherTagList.isEmpty()) {
            return -1;
        } else if (thisTagList.isEmpty() && otherTagList.isEmpty()) {
            return 0;
        }

        Collections.sort(thisTagList);
        Collections.sort(otherTagList);

        Tag thisTag = thisTagList.get(0);
        Tag otherTag = otherTagList.get(0);

        return thisTag.compareTo(otherTag);
    }
}
