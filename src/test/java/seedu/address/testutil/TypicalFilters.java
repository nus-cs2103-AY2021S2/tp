package seedu.address.testutil;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.filter.AddressFilter;
import seedu.address.model.filter.AppointmentDateTimeFilter;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.EmailFilter;
import seedu.address.model.filter.ExclusiveFilterSet;
import seedu.address.model.filter.FilterSet;
import seedu.address.model.filter.GenderFilter;
import seedu.address.model.filter.InclusiveFilterSet;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.PhoneFilter;
import seedu.address.model.filter.SubjectExperienceFilter;
import seedu.address.model.filter.SubjectLevelFilter;
import seedu.address.model.filter.SubjectNameFilter;
import seedu.address.model.filter.SubjectQualificationFilter;
import seedu.address.model.filter.SubjectRateFilter;
import seedu.address.model.filter.TutorFilter;
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

public class TypicalFilters {
    public static class FilterStub implements Predicate<String> {
        private String filter;

        public FilterStub(String filter) {
            this.filter = filter;
        }

        @Override
        public boolean test(String value) {
            return value.contains(filter);
        }
    }

    public static final String FILTERABLE_A = "amz";
    public static final String FILTERABLE_B = "059";
    public static final String FILTERABLE_C = "!%)";

    public static final Predicate<String> FILTER_A = new FilterStub("amz");
    public static final Predicate<String> FILTER_B = new FilterStub("059");
    public static final Predicate<String> FILTER_C = new FilterStub("!%)");

    public static final Set<Predicate<String>> SET_A = Stream.of(FILTER_A).collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_B = Stream.of(FILTER_B).collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_C = Stream.of(FILTER_C).collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_AB = Stream.of(FILTER_A, FILTER_B)
            .collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_AC = Stream.of(FILTER_A, FILTER_C)
            .collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_ABC = Stream.of(FILTER_A, FILTER_B, FILTER_C)
            .collect(Collectors.toSet());

    public static final FilterSet<String> INCLUSIVE_FILTERSET_EMPTY = new InclusiveFilterSet<>();
    public static final FilterSet<String> INCLUSIVE_FILTERSET_A = new InclusiveFilterSet<>(SET_A);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_B = new InclusiveFilterSet<>(SET_B);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_C = new InclusiveFilterSet<>(SET_C);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_AB = new InclusiveFilterSet<>(SET_AB);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_AC = new InclusiveFilterSet<>(SET_AC);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_ABC = new InclusiveFilterSet<>(SET_ABC);

    public static final FilterSet<String> EXCLUSIVE_FILTERSET_EMPTY = new ExclusiveFilterSet<>();
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_A = new ExclusiveFilterSet<>(SET_A);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_B = new ExclusiveFilterSet<>(SET_B);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_C = new ExclusiveFilterSet<>(SET_C);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_AB = new ExclusiveFilterSet<>(SET_AB);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_AC = new ExclusiveFilterSet<>(SET_AC);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_ABC = new ExclusiveFilterSet<>(SET_ABC);

    public static TutorFilter getBensonNoSubjectsFilter() {
        Tutor benson = TypicalTutors.BENSON;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>();
        Set<Predicate<Gender>> genderFilters = new LinkedHashSet<>();
        Set<Predicate<Phone>> phoneFilters = new LinkedHashSet<>();
        Set<Predicate<Email>> emailFilters = new LinkedHashSet<>();
        Set<Predicate<Address>> addressFilters = new LinkedHashSet<>();

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectLevel>> subjectLevelFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectRate>> subjectRateFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectExperience>> subjectExperienceFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectQualification>> subjectQualificationFilters = new LinkedHashSet<>();

        nameFilters.add(new NameFilter(benson.getName().fullName));
        genderFilters.add(new GenderFilter(benson.getGender().personGender));
        phoneFilters.add(new PhoneFilter(benson.getPhone().value));
        emailFilters.add(new EmailFilter(benson.getEmail().value));
        addressFilters.add(new AddressFilter(benson.getAddress().value));

        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        return tutorFilter;
    }

    public static TutorFilter getBensonOneSubjectFilter() {
        Tutor benson = TypicalTutors.BENSON;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>();
        Set<Predicate<Gender>> genderFilters = new LinkedHashSet<>();
        Set<Predicate<Phone>> phoneFilters = new LinkedHashSet<>();
        Set<Predicate<Email>> emailFilters = new LinkedHashSet<>();
        Set<Predicate<Address>> addressFilters = new LinkedHashSet<>();

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectLevel>> subjectLevelFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectRate>> subjectRateFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectExperience>> subjectExperienceFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectQualification>> subjectQualificationFilters = new LinkedHashSet<>();

        nameFilters.add(new NameFilter(benson.getName().fullName));
        genderFilters.add(new GenderFilter(benson.getGender().personGender));
        phoneFilters.add(new PhoneFilter(benson.getPhone().value));
        emailFilters.add(new EmailFilter(benson.getEmail().value));
        addressFilters.add(new AddressFilter(benson.getAddress().value));

        TutorSubject subject = benson.getSubjectList().asUnmodifiableObservableList().get(0);
        subjectNameFilters.add(new SubjectNameFilter(subject.getName().name));
        subjectLevelFilters.add(new SubjectLevelFilter(subject.getLevel().level));
        subjectRateFilters.add(new SubjectRateFilter(subject.getRate().rate.toString()));
        subjectExperienceFilters.add(new SubjectExperienceFilter(subject.getExperience().experience.toString()));
        subjectQualificationFilters.add(new SubjectQualificationFilter(subject.getQualification().qualification));

        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        return tutorFilter;
    }

    public static TutorFilter getBensonTwoSubjectsFilter() {
        Tutor benson = TypicalTutors.BENSON;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>();
        Set<Predicate<Gender>> genderFilters = new LinkedHashSet<>();
        Set<Predicate<Phone>> phoneFilters = new LinkedHashSet<>();
        Set<Predicate<Email>> emailFilters = new LinkedHashSet<>();
        Set<Predicate<Address>> addressFilters = new LinkedHashSet<>();

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectLevel>> subjectLevelFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectRate>> subjectRateFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectExperience>> subjectExperienceFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectQualification>> subjectQualificationFilters = new LinkedHashSet<>();

        nameFilters.add(new NameFilter(benson.getName().fullName));
        genderFilters.add(new GenderFilter(benson.getGender().personGender));
        phoneFilters.add(new PhoneFilter(benson.getPhone().value));
        emailFilters.add(new EmailFilter(benson.getEmail().value));
        addressFilters.add(new AddressFilter(benson.getAddress().value));

        TutorSubject subject = benson.getSubjectList().asUnmodifiableObservableList().get(0);
        subjectNameFilters.add(new SubjectNameFilter(subject.getName().name));
        subjectLevelFilters.add(new SubjectLevelFilter(subject.getLevel().level));
        subjectRateFilters.add(new SubjectRateFilter(subject.getRate().rate.toString()));
        subjectExperienceFilters.add(new SubjectExperienceFilter(subject.getExperience().experience.toString()));
        subjectQualificationFilters.add(new SubjectQualificationFilter(subject.getQualification().qualification));

        subject = benson.getSubjectList().asUnmodifiableObservableList().get(1);
        subjectNameFilters.add(new SubjectNameFilter(subject.getName().name));
        subjectLevelFilters.add(new SubjectLevelFilter(subject.getLevel().level));
        subjectRateFilters.add(new SubjectRateFilter(subject.getRate().rate.toString()));
        subjectExperienceFilters.add(new SubjectExperienceFilter(subject.getExperience().experience.toString()));
        subjectQualificationFilters.add(new SubjectQualificationFilter(subject.getQualification().qualification));

        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        return tutorFilter;
    }

    public static AppointmentFilter getMathFilter() {
        Appointment maths = TypicalAppointments.MATHS_APPOINTMENT;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>();
        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>();
        Set<Predicate<AppointmentDateTime>> timeFromFilters = new LinkedHashSet<>();
        Set<Predicate<AppointmentDateTime>> timeToFilters = new LinkedHashSet<>();
        Set<Predicate<Address>> locationFilters = new LinkedHashSet<>();

        nameFilters.add(new NameFilter(maths.getName().fullName));
        subjectNameFilters.add(new SubjectNameFilter(maths.getSubject().name));
        timeFromFilters.add(new AppointmentDateTimeFilter(maths.getTimeFrom().toStorageString()));
        timeToFilters.add(new AppointmentDateTimeFilter(maths.getTimeTo().toStorageString()));
        locationFilters.add(new AddressFilter(maths.getLocation().value));

        AppointmentFilter appointmentFilter = new AppointmentFilter(
                nameFilters,
                subjectNameFilters,
                timeFromFilters,
                timeToFilters,
                locationFilters);

        return appointmentFilter;
    }
}
