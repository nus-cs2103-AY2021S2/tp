package seedu.address.model.person.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

<<<<<<< Updated upstream:src/test/java/seedu/address/model/person/comparator/NameComparatorTest.java
public class NameComparatorTest {
=======
public class PersonComparatorTest {
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/comparator/PersonComparatorTest.java

    @Test
    public void compare() {
        Person firstPerson = ALICE;
        Person secondPerson = CARL;
<<<<<<< Updated upstream:src/test/java/seedu/address/model/person/comparator/NameComparatorTest.java
        NameComparator nameComparator = new NameComparator();
        assertTrue(nameComparator.compare(firstPerson, secondPerson) < 0);
=======
        PersonComparator personComparator = new PersonComparator();
        assertTrue(personComparator.compare(firstPerson, secondPerson) < 0);
>>>>>>> Stashed changes:src/test/java/seedu/address/model/person/comparator/PersonComparatorTest.java
    }
}
