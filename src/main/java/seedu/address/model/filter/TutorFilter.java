package seedu.address.model.filter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

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

/**
 * TutorFilter that contains all tutor filters active in the application.
 */
public class TutorFilter implements Predicate<Tutor> {
    private final FilterSet<Name> nameFilters;
    private final FilterSet<Gender> genderFilters;
    private final FilterSet<Phone> phoneFilters;
    private final FilterSet<Email> emailFilters;
    private final FilterSet<Address> addressFilters;

    private final FilterSet<SubjectName> subjectNameFilters;
    private final FilterSet<SubjectLevel> subjectLevelFilters;
    private final FilterSet<SubjectRate> subjectRateFilters;
    private final FilterSet<SubjectExperience> subjectExperienceFilters;
    private final FilterSet<SubjectQualification> subjectQualificationFilters;

    private final ObservableList<String> stringList = FXCollections.observableArrayList();
    private final ObservableList<String> unmodifiableStringList =
            FXCollections.unmodifiableObservableList(stringList);

    /**
     * Constructs an empty {@code PersonFilter} that shows all people by default.
     */
    public TutorFilter() {
        this.nameFilters = new InclusiveFilterSet<>();
        this.genderFilters = new InclusiveFilterSet<>();
        this.phoneFilters = new InclusiveFilterSet<>();
        this.emailFilters = new InclusiveFilterSet<>();
        this.addressFilters = new InclusiveFilterSet<>();

        this.subjectNameFilters = new InclusiveFilterSet<>();
        this.subjectLevelFilters = new InclusiveFilterSet<>();
        this.subjectRateFilters = new ExclusiveFilterSet<>();
        this.subjectExperienceFilters = new ExclusiveFilterSet<>();
        this.subjectQualificationFilters = new InclusiveFilterSet<>();

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

        this.nameFilters = new InclusiveFilterSet<>(nameFilters);
        this.genderFilters = new InclusiveFilterSet<>(genderFilters);
        this.phoneFilters = new InclusiveFilterSet<>(phoneFilters);
        this.emailFilters = new InclusiveFilterSet<>(emailFilters);
        this.addressFilters = new InclusiveFilterSet<>(addressFilters);

        this.subjectNameFilters = new InclusiveFilterSet<>(subjectNameFilters);
        this.subjectLevelFilters = new InclusiveFilterSet<>(subjectLevelFilters);
        this.subjectRateFilters = new InclusiveFilterSet<>(subjectRateFilters);
        this.subjectExperienceFilters = new InclusiveFilterSet<>(subjectExperienceFilters);
        this.subjectQualificationFilters = new InclusiveFilterSet<>(subjectQualificationFilters);

        buildStringList();
    }

    /**
     * Checks if person filter contains any of the filters from another person filter.
     */
    public boolean hasAny(TutorFilter tutorFilter) {
        return nameFilters.hasAny(tutorFilter.nameFilters)
                || genderFilters.hasAny(tutorFilter.genderFilters)
                || phoneFilters.hasAny(tutorFilter.phoneFilters)
                || emailFilters.hasAny(tutorFilter.emailFilters)
                || addressFilters.hasAny(tutorFilter.addressFilters)
                || subjectNameFilters.hasAny(tutorFilter.subjectNameFilters)
                || subjectLevelFilters.hasAny(tutorFilter.subjectLevelFilters)
                || subjectRateFilters.hasAny(tutorFilter.subjectRateFilters)
                || subjectExperienceFilters.hasAny(tutorFilter.subjectExperienceFilters)
                || subjectQualificationFilters.hasAny(tutorFilter.subjectQualificationFilters);
    }

    /**
     * Checks if person filter contains all of the filters from another person filter.
     */
    public boolean hasAll(TutorFilter tutorFilter) {
        return nameFilters.hasAll(tutorFilter.nameFilters)
                && genderFilters.hasAll(tutorFilter.genderFilters)
                && phoneFilters.hasAll(tutorFilter.phoneFilters)
                && emailFilters.hasAll(tutorFilter.emailFilters)
                && addressFilters.hasAll(tutorFilter.addressFilters)
                && subjectNameFilters.hasAll(tutorFilter.subjectNameFilters)
                && subjectLevelFilters.hasAll(tutorFilter.subjectLevelFilters)
                && subjectRateFilters.hasAll(tutorFilter.subjectRateFilters)
                && subjectExperienceFilters.hasAll(tutorFilter.subjectExperienceFilters)
                && subjectQualificationFilters.hasAll(tutorFilter.subjectQualificationFilters);
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
        isFiltered = isFiltered && nameFilters.test(tutor.getName());
        isFiltered = isFiltered && genderFilters.test(tutor.getGender());
        isFiltered = isFiltered && phoneFilters.test(tutor.getPhone());
        isFiltered = isFiltered && emailFilters.test(tutor.getEmail());
        isFiltered = isFiltered && addressFilters.test(tutor.getAddress());

        boolean isAnySubjectFiltered = false;
        if (tutor.getSubjectList().asUnmodifiableObservableList().isEmpty()) {
            isAnySubjectFiltered = true;
        }

        for (TutorSubject subject : tutor.getSubjectList()) {
            boolean isSubjectFiltered = true;
            isSubjectFiltered = isSubjectFiltered && subjectNameFilters.test(subject.getName());
            isSubjectFiltered = isSubjectFiltered && subjectLevelFilters.test(subject.getLevel());
            isSubjectFiltered = isSubjectFiltered && subjectRateFilters.test(subject.getRate());
            isSubjectFiltered = isSubjectFiltered && subjectExperienceFilters.test(subject.getExperience());
            isSubjectFiltered = isSubjectFiltered && subjectQualificationFilters.test(subject.getQualification());
            isAnySubjectFiltered = isAnySubjectFiltered || isSubjectFiltered;
        }
        isFiltered = isFiltered && isAnySubjectFiltered;

        return isFiltered;
    }

    private void buildStringList() {
        stringList.clear();

        stringList.addAll(nameFilters.toStringList());
        stringList.addAll(genderFilters.toStringList());
        stringList.addAll(phoneFilters.toStringList());
        stringList.addAll(emailFilters.toStringList());
        stringList.addAll(addressFilters.toStringList());
        stringList.addAll(subjectNameFilters.toStringList());
        stringList.addAll(subjectLevelFilters.toStringList());
        stringList.addAll(subjectRateFilters.toStringList());
        stringList.addAll(subjectExperienceFilters.toStringList());
        stringList.addAll(subjectQualificationFilters.toStringList());
    }
}
