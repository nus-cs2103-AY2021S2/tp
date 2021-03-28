package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.testutil.TypicalPersons;

public class PersonFilterTest {
    private Set<Predicate<Name>> nameFilters;
    private Set<Predicate<Gender>> genderFilters;
    private Set<Predicate<Phone>> phoneFilters;
    private Set<Predicate<Email>> emailFilters;
    private Set<Predicate<Address>> addressFilters;

    private Set<Predicate<SubjectName>> subjectNameFilters;
    private Set<Predicate<SubjectLevel>> subjectLevelFilters;
    private Set<Predicate<SubjectRate>> subjectRateFilters;
    private Set<Predicate<SubjectExperience>> subjectExperienceFilters;
    private Set<Predicate<SubjectQualification>> subjectQualificationFilters;

    @BeforeEach
    public void setUp() {
        this.nameFilters = new LinkedHashSet<>();
        this.genderFilters = new LinkedHashSet<>();
        this.phoneFilters = new LinkedHashSet<>();
        this.emailFilters = new LinkedHashSet<>();
        this.addressFilters = new LinkedHashSet<>();

        this.subjectNameFilters = new LinkedHashSet<>();
        this.subjectLevelFilters = new LinkedHashSet<>();
        this.subjectRateFilters = new LinkedHashSet<>();
        this.subjectExperienceFilters = new LinkedHashSet<>();
        this.subjectQualificationFilters = new LinkedHashSet<>();
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonFilter(null,
                null, null, null, null, null, null, null, null, null));
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

        nameFilters.add(new NameFilter(amy.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        assertTrue(personFilter.test(amy));
        assertFalse(personFilter.test(bob));
        assertFalse(personFilter.test(null));
    }

    @Test
    public void has_personFilter_success() {
        Person amy = TypicalPersons.AMY;
        Person bob = TypicalPersons.BOB;

        nameFilters.add(new NameFilter(amy.getName().fullName));
        PersonFilter personFilterOne = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(amy.getName().fullName));
        nameFilters.add(new NameFilter(bob.getName().fullName));
        PersonFilter personFilterTwo = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        assertTrue(personFilterTwo.has(personFilterOne));
        assertFalse(personFilterTwo.has(new PersonFilter()));
    }

    @Test
    public void has_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonFilter().has(null));
    }

    @Test
    public void add_personFilter_success() {
        PersonFilter personFilter = new PersonFilter();

        Person amy = TypicalPersons.AMY;
        Person bob = TypicalPersons.BOB;

        nameFilters.add(new NameFilter(amy.getName().fullName));
        personFilter.add(new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters));

        assertTrue(personFilter.test(amy));
        assertFalse(personFilter.test(bob));

        nameFilters.add(new NameFilter(bob.getName().fullName));
        personFilter.add(new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters));

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

        nameFilters.add(new NameFilter(amy.getName().fullName));
        nameFilters.add(new NameFilter(bob.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        assertTrue(personFilter.test(amy));
        assertTrue(personFilter.test(bob));

        nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(bob.getName().fullName));
        personFilter.remove(new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters));

        assertTrue(personFilter.test(amy));
        assertFalse(personFilter.test(bob));

        nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(amy.getName().fullName));
        personFilter.remove(new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters));

        assertTrue(personFilter.test(amy));
        assertTrue(personFilter.test(bob));
    }

    @Test
    public void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonFilter().remove(null));
    }
}
