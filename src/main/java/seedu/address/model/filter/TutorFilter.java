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
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;

public class TutorFilter implements Predicate<Tutor> {
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
    public TutorFilter() {
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
    public TutorFilter(Set<Predicate<Name>> nameFilters,
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
    public boolean hasAny(TutorFilter tutorFilter) {
        return !Collections.disjoint(this.nameFilters, tutorFilter.nameFilters)
                || !Collections.disjoint(this.genderFilters, tutorFilter.genderFilters)
                || !Collections.disjoint(this.phoneFilters, tutorFilter.phoneFilters)
                || !Collections.disjoint(this.emailFilters, tutorFilter.emailFilters)
                || !Collections.disjoint(this.addressFilters, tutorFilter.addressFilters)
                || !Collections.disjoint(this.subjectNameFilters, tutorFilter.subjectNameFilters)
                || !Collections.disjoint(this.subjectLevelFilters, tutorFilter.subjectLevelFilters)
                || !Collections.disjoint(this.subjectRateFilters, tutorFilter.subjectRateFilters)
                || !Collections.disjoint(this.subjectExperienceFilters, tutorFilter.subjectExperienceFilters)
                || !Collections.disjoint(this.subjectQualificationFilters, tutorFilter.subjectQualificationFilters);
    }

    /**
     * Checks if person filter contains all of the filters from another person filter.
     */
    public boolean hasAll(TutorFilter tutorFilter) {
        return this.nameFilters.containsAll(tutorFilter.nameFilters)
                && this.genderFilters.containsAll(tutorFilter.genderFilters)
                && this.phoneFilters.containsAll(tutorFilter.phoneFilters)
                && this.emailFilters.containsAll(tutorFilter.emailFilters)
                && this.addressFilters.containsAll(tutorFilter.addressFilters)
                && this.subjectNameFilters.containsAll(tutorFilter.subjectNameFilters)
                && this.subjectLevelFilters.containsAll(tutorFilter.subjectLevelFilters)
                && this.subjectRateFilters.containsAll(tutorFilter.subjectRateFilters)
                && this.subjectExperienceFilters.containsAll(tutorFilter.subjectExperienceFilters)
                && this.subjectQualificationFilters.containsAll(tutorFilter.subjectQualificationFilters);
    }

    /**
     * Add all filters from another person filter and merge them.
     */
    public TutorFilter add(TutorFilter tutorFilter) {
        nameFilters.addAll(tutorFilter.nameFilters);
        genderFilters.addAll(tutorFilter.genderFilters);
        phoneFilters.addAll(tutorFilter.phoneFilters);
        emailFilters.addAll(tutorFilter.emailFilters);
        addressFilters.addAll(tutorFilter.addressFilters);

        subjectNameFilters.addAll(tutorFilter.subjectNameFilters);
        subjectLevelFilters.addAll(tutorFilter.subjectLevelFilters);
        subjectRateFilters.addAll(tutorFilter.subjectRateFilters);
        subjectExperienceFilters.addAll(tutorFilter.subjectExperienceFilters);
        subjectQualificationFilters.addAll(tutorFilter.subjectQualificationFilters);

        composeFilters();
        buildStringList();
        return this;
    }

    /**
     * Remove filters from this person filter according to another person filter.
     */
    public TutorFilter remove(TutorFilter tutorFilter) {
        nameFilters.removeAll(tutorFilter.nameFilters);
        genderFilters.removeAll(tutorFilter.genderFilters);
        phoneFilters.removeAll(tutorFilter.phoneFilters);
        emailFilters.removeAll(tutorFilter.emailFilters);
        addressFilters.removeAll(tutorFilter.addressFilters);

        subjectNameFilters.removeAll(tutorFilter.subjectNameFilters);
        subjectLevelFilters.removeAll(tutorFilter.subjectLevelFilters);
        subjectRateFilters.removeAll(tutorFilter.subjectRateFilters);
        subjectExperienceFilters.removeAll(tutorFilter.subjectExperienceFilters);
        subjectQualificationFilters.removeAll(tutorFilter.subjectQualificationFilters);

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

        if (!(other instanceof TutorFilter)) {
            return false;
        }

        TutorFilter otherTutorFilter = (TutorFilter) other;
        return otherTutorFilter.nameFilters.equals(nameFilters)
                && otherTutorFilter.genderFilters.equals(genderFilters)
                && otherTutorFilter.phoneFilters.equals(phoneFilters)
                && otherTutorFilter.emailFilters.equals(emailFilters)
                && otherTutorFilter.addressFilters.equals(addressFilters)
                && otherTutorFilter.subjectNameFilters.equals(subjectNameFilters)
                && otherTutorFilter.subjectLevelFilters.equals(subjectLevelFilters)
                && otherTutorFilter.subjectRateFilters.equals(subjectRateFilters)
                && otherTutorFilter.subjectExperienceFilters.equals(subjectExperienceFilters)
                && otherTutorFilter.subjectQualificationFilters.equals(subjectQualificationFilters);
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
    public boolean test(Tutor tutor) {
        if (tutor == null) {
            return false;
        }

        boolean isFiltered = true;
        isFiltered = isFiltered && composedNameFilter.test(tutor.getName());
        isFiltered = isFiltered && composedGenderFilter.test(tutor.getGender());
        isFiltered = isFiltered && composedPhoneFilter.test(tutor.getPhone());
        isFiltered = isFiltered && composedEmailFilter.test(tutor.getEmail());
        isFiltered = isFiltered && composedAddressFilter.test(tutor.getAddress());

        boolean isAnySubjectFiltered = false;
        if (tutor.getSubjectList().asUnmodifiableObservableList().isEmpty()) {
            isAnySubjectFiltered = true;
        }

        for (TutorSubject subject : tutor.getSubjectList()) {
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
