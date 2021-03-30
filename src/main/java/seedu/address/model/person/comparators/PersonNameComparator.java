package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Compares two {@code Person}s according to the order of their {@code Tag}.
 */
public class PersonNameComparator implements Comparator<Person> {
    public PersonNameComparator(){}

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
