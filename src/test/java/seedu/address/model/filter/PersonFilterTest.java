package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class PersonFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonFilter(null));
    }

    @Test
    public void test_emptyFilter_identity() {
        PersonFilter personFilter = new PersonFilter();

        ObservableList<Person> typicalPersons = FXCollections.unmodifiableObservableList(
            FXCollections.observableArrayList(TypicalPersons.getTypicalPersons()));
        FilteredList<Person> filteredTypicalPersons = typicalPersons.filtered(personFilter);

        assertEquals(typicalPersons, filteredTypicalPersons);
    }

    @Test
    public void test_nameFilter_success() {
        Person amy = TypicalPersons.AMY;
        Person bob = TypicalPersons.BOB;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(amy.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        assertTrue(personFilter.test(amy));
        assertFalse(personFilter.test(bob));
        assertFalse(personFilter.test(null));
    }

    @Test
    public void add_personFilter_success() {
        PersonFilter personFilter = new PersonFilter();

        Person amy = TypicalPersons.AMY;
        Person bob = TypicalPersons.BOB;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(amy.getName().fullName));
        personFilter.add(new PersonFilter(nameFilters));

        assertTrue(personFilter.test(amy));
        assertFalse(personFilter.test(bob));

        nameFilters.add(new NameFilter(bob.getName().fullName));
        personFilter.add(new PersonFilter(nameFilters));

        assertTrue(personFilter.test(amy));
        assertTrue(personFilter.test(bob));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonFilter().add(null));
    }

    @Test
    public void remove_personFilter_success() {
        Person amy = TypicalPersons.AMY;
        Person bob = TypicalPersons.BOB;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(amy.getName().fullName));
        nameFilters.add(new NameFilter(bob.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        assertTrue(personFilter.test(amy));
        assertTrue(personFilter.test(bob));

        nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(bob.getName().fullName));
        personFilter.remove(new PersonFilter(nameFilters));

        assertTrue(personFilter.test(amy));
        assertFalse(personFilter.test(bob));

        nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(amy.getName().fullName));
        personFilter.remove(new PersonFilter(nameFilters));

        assertTrue(personFilter.test(amy));
        assertTrue(personFilter.test(bob));
    }

    @Test
    public void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonFilter().remove(null));
    }
}
