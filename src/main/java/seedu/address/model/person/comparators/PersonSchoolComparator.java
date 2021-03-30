package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Compares two {@code Person}s according to the order of their {@code Tag}.
 */
public class PersonSchoolComparator implements Comparator<Person> {
    public PersonSchoolComparator(){}
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getSchool().compareTo(p2.getSchool());
    }
}

