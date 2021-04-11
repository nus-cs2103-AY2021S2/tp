package seedu.address.model.person.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class PersonComparatorTest {

    @Test
    public void compare() {
        Person firstPerson = ALICE;
        Person secondPerson = CARL;
        PersonComparator personComparator = new PersonComparator();
        assertTrue(personComparator.compare(firstPerson, secondPerson) < 0);
    }
}
