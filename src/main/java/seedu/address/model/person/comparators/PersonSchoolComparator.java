package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.School;

/**
 * Compares two {@code Person}s according to the order of their {@code Tag}.
 */
public class PersonSchoolComparator implements Comparator<Person> {
    public PersonSchoolComparator(){}
    @Override
    public int compare(Person p1, Person p2) {
        School thisSchool = p1.getSchool().get();
        School otherSchool = p2.getSchool().get();
        return thisSchool.compareTo(otherSchool);
    }
}
