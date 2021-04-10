package seedu.address.model.person.comparator;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class ContactComparator implements Comparator<Person> {

    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        int compareByName = firstPerson.getName().compareTo(secondPerson.getName());
        if (compareByName != 0) {
            return compareByName;
        }
        return firstPerson.getEmail().compareTo(secondPerson.getEmail());
    }
}
