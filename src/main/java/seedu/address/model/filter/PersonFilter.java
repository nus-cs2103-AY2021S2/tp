package seedu.address.model.filter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import seedu.address.model.subject.TutorSubject;

public class PersonFilter implements Predicate<Person> {
    private final Set<Predicate<Name>> nameFilters;
    private final Set<Predicate<Gender>> genderFilters;
    private final Set<Predicate<Phone>> phoneFilters;
    private final Set<Predicate<Email>> emailFilters;
    private final Set<Predicate<Address>> addressFilters;

    private final Set<Predicate<SubjectName>> subjectNameFilters;
    private final Set<Predicate<SubjectLevel>> subjectLevelFilters;
    private final Set<Predicate<SubjectRate>> subjectRateFilters;
    private final Set<Predicate<SubjectExperience>> subjectExperienceFilters;
    private final Set<Predicate<SubjectQualification>> subjectQualificationFilters;

    private Predicate<Name> composedNameFilter;
    private Predicate<Gender> composedGenderFilter;
    private Predicate<Phone> composedPhoneFilter;
    private Predicate<Email> composedEmailFilter;
    private Predicate<Address> composedAddressFilter;

    private Predicate<SubjectName> composedSubjectNameFilter;
    private Predicate<SubjectLevel> composedSubjectLevelFilter;
    private Predicate<SubjectRate> composedSubjectRateFilter;
    private Predicate<SubjectExperience> composedSubjectExperienceFilter;
    private Predicate<SubjectQualification> composedSubjectQualificationFilter;

    private final ObservableList<String> stringList = FXCollections.observableArrayList();
    private final ObservableList<String> unmodifiableStringList =
            FXCollections.unmodifiableObservableList(stringList);

    /**
     * Constructs an empty {@code PersonFilter} that shows all people by default.
     */
    public PersonFilter() {
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

        composeFilters();
        buildStringList();
    }

    /**
     * Constructs a {@code PersonFilter} from lists of filters.
     * Every field must be present and not null.
     */
    public PersonFilter(Set<Predicate<Name>> nameFilters,
            Set<Predicate<Gender>> genderFilters,
            Set<Predicate<Phone>> phoneFilters,
            Set<Predicate<Email>> emailFilters,
            Set<Predicate<Address>> addressFilters,
            Set<Predicate<SubjectName>> subjectNameFilters,
            Set<Predicate<SubjectLevel>> subjectLevelFilters,
            Set<Predicate<SubjectRate>> subjectRateFilters,
            Set<Predicate<SubjectExperience>> subjectExperienceFilters,
            Set<Predicate<SubjectQualification>> subjectQualificationFilters) {
        requireAllNonNull(nameFilters,
                genderFilters,
                phoneFilters,
                emailFilters,
                addressFilters,
                subjectNameFilters,
                subjectLevelFilters,
                subjectRateFilters,
                subjectExperienceFilters,
                subjectQualificationFilters);

        this.nameFilters = nameFilters;
        this.genderFilters = genderFilters;
        this.phoneFilters = phoneFilters;
        this.emailFilters = emailFilters;
        this.addressFilters = addressFilters;

        this.subjectNameFilters = subjectNameFilters;
        this.subjectLevelFilters = subjectLevelFilters;
        this.subjectRateFilters = subjectRateFilters;
        this.subjectExperienceFilters = subjectExperienceFilters;
        this.subjectQualificationFilters = subjectQualificationFilters;

        composeFilters();
        buildStringList();
    }

    /**
     * Checks if person filter contains any of the filters from another person filter.
     */
    public boolean has(PersonFilter personFilter) {
        return !Collections.disjoint(this.nameFilters, personFilter.nameFilters)
                || !Collections.disjoint(this.genderFilters, personFilter.genderFilters)
                || !Collections.disjoint(this.phoneFilters, personFilter.phoneFilters)
                || !Collections.disjoint(this.emailFilters, personFilter.emailFilters)
                || !Collections.disjoint(this.addressFilters, personFilter.addressFilters)
                || !Collections.disjoint(this.subjectNameFilters, personFilter.subjectNameFilters)
                || !Collections.disjoint(this.subjectLevelFilters, personFilter.subjectLevelFilters)
                || !Collections.disjoint(this.subjectRateFilters, personFilter.subjectRateFilters)
                || !Collections.disjoint(this.subjectExperienceFilters, personFilter.subjectExperienceFilters)
                || !Collections.disjoint(this.subjectQualificationFilters, personFilter.subjectQualificationFilters);
    }

    /**
     * Add all filters from another person filter and merge them.
     */
    public PersonFilter add(PersonFilter personFilter) {
        nameFilters.addAll(personFilter.nameFilters);
        genderFilters.addAll(personFilter.genderFilters);
        phoneFilters.addAll(personFilter.phoneFilters);
        emailFilters.addAll(personFilter.emailFilters);
        addressFilters.addAll(personFilter.addressFilters);

        subjectNameFilters.addAll(personFilter.subjectNameFilters);
        subjectLevelFilters.addAll(personFilter.subjectLevelFilters);
        subjectRateFilters.addAll(personFilter.subjectRateFilters);
        subjectExperienceFilters.addAll(personFilter.subjectExperienceFilters);
        subjectQualificationFilters.addAll(personFilter.subjectQualificationFilters);

        composeFilters();
        buildStringList();
        return this;
    }

    /**
     * Remove filters from this person filter according to another person filter.
     */
    public PersonFilter remove(PersonFilter personFilter) {
        nameFilters.removeAll(personFilter.nameFilters);
        genderFilters.removeAll(personFilter.genderFilters);
        phoneFilters.removeAll(personFilter.phoneFilters);
        emailFilters.removeAll(personFilter.emailFilters);
        addressFilters.removeAll(personFilter.addressFilters);

        subjectNameFilters.removeAll(personFilter.subjectNameFilters);
        subjectLevelFilters.removeAll(personFilter.subjectLevelFilters);
        subjectRateFilters.removeAll(personFilter.subjectRateFilters);
        subjectExperienceFilters.removeAll(personFilter.subjectExperienceFilters);
        subjectQualificationFilters.removeAll(personFilter.subjectQualificationFilters);

        composeFilters();
        buildStringList();
        return this;
    }

    /**
     * Returns the string list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<String> asUnmodifiableObservableList() {
        return unmodifiableStringList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonFilter)) {
            return false;
        }

        PersonFilter otherPersonFilter = (PersonFilter) other;
        return otherPersonFilter.nameFilters.equals(nameFilters)
                && otherPersonFilter.genderFilters.equals(genderFilters)
                && otherPersonFilter.phoneFilters.equals(phoneFilters)
                && otherPersonFilter.emailFilters.equals(emailFilters)
                && otherPersonFilter.addressFilters.equals(addressFilters)
                && otherPersonFilter.subjectNameFilters.equals(subjectNameFilters)
                && otherPersonFilter.subjectLevelFilters.equals(subjectLevelFilters)
                && otherPersonFilter.subjectRateFilters.equals(subjectRateFilters)
                && otherPersonFilter.subjectExperienceFilters.equals(subjectExperienceFilters)
                && otherPersonFilter.subjectQualificationFilters.equals(subjectQualificationFilters);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nameFilters, genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);
    }

    @Override
    public String toString() {
        return String.join(", ", stringList);
    }

    @Override
    public boolean test(Person person) {
        if (person == null) {
            return false;
        }

        boolean isFiltered = true;
        isFiltered = isFiltered && composedNameFilter.test(person.getName());
        isFiltered = isFiltered && composedGenderFilter.test(person.getGender());
        isFiltered = isFiltered && composedPhoneFilter.test(person.getPhone());
        isFiltered = isFiltered && composedEmailFilter.test(person.getEmail());
        isFiltered = isFiltered && composedAddressFilter.test(person.getAddress());

        boolean isAnySubjectFiltered = false;
        if (person.getSubjectList().asUnmodifiableObservableList().isEmpty()) {
            isAnySubjectFiltered = true;
        }

        for (TutorSubject subject : person.getSubjectList()) {
            boolean isSubjectFiltered = true;
            isSubjectFiltered = isSubjectFiltered && composedSubjectNameFilter.test(subject.getName());
            isSubjectFiltered = isSubjectFiltered && composedSubjectLevelFilter.test(subject.getLevel());
            isSubjectFiltered = isSubjectFiltered && composedSubjectRateFilter.test(subject.getRate());
            isSubjectFiltered = isSubjectFiltered && composedSubjectExperienceFilter.test(subject.getExperience());
            isSubjectFiltered = isSubjectFiltered && composedSubjectQualificationFilter.test(
                    subject.getQualification());
            isAnySubjectFiltered = isAnySubjectFiltered || isSubjectFiltered;
        }
        isFiltered = isFiltered && isAnySubjectFiltered;

        return isFiltered;
    }

    private void composeFilters() {
        this.composedNameFilter = nameFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedGenderFilter = genderFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedPhoneFilter = phoneFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedEmailFilter = emailFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedAddressFilter = addressFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedSubjectNameFilter = subjectNameFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedSubjectLevelFilter = subjectLevelFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);

        this.composedSubjectRateFilter = subjectRateFilters.stream()
                .reduce((x, y) -> x.and(y))
                .orElse(x -> true);

        this.composedSubjectExperienceFilter = subjectExperienceFilters.stream()
                .reduce((x, y) -> x.and(y))
                .orElse(x -> true);

        this.composedSubjectQualificationFilter = subjectQualificationFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);
    }

    private void buildStringList() {
        stringList.clear();

        stringList.addAll(nameFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(genderFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(phoneFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(emailFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(addressFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(subjectNameFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(subjectLevelFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(subjectRateFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(subjectExperienceFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
        stringList.addAll(subjectQualificationFilters.stream().map(x -> x.toString()).collect(Collectors.toList()));
    }
}
