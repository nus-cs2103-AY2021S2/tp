package seedu.address.logic.parser.filterparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FILTER_PROVIDED;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.filter.AddressFilter;
import seedu.address.model.filter.AppointmentDateTimeFilter;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.EmailFilter;
import seedu.address.model.filter.GenderFilter;
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
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;

/**
 * Contains utility methods used for parsing strings in the various filter parser classes.
 */
public class FilterParserUtil {
    /**
     * Parses a {@code String nameFilter} into a {@code NameFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nameFilter} is invalid.
     */
    public static NameFilter parseNameFilter(String nameFilter) throws ParseException {
        requireNonNull(nameFilter);
        String trimmedNameFilter = nameFilter.trim();
        if (!NameFilter.isValidNameFilter(trimmedNameFilter)) {
            throw new ParseException(NameFilter.MESSAGE_CONSTRAINTS);
        }
        return new NameFilter(trimmedNameFilter);
    }

    /**
     * Parses a {@code String genderFilter} into a {@code GenderFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code genderFilter} is invalid.
     */
    public static GenderFilter parseGenderFilter(String genderFilter) throws ParseException {
        requireNonNull(genderFilter);
        String trimmedGenderFilter = genderFilter.trim();
        if (!GenderFilter.isValidGenderFilter(trimmedGenderFilter)) {
            throw new ParseException(GenderFilter.MESSAGE_CONSTRAINTS);
        }
        return new GenderFilter(trimmedGenderFilter);
    }

    /**
     * Parses a {@code String phoneFilter} into a {@code PhoneFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phoneFilter} is invalid.
     */
    public static PhoneFilter parsePhoneFilter(String phoneFilter) throws ParseException {
        requireNonNull(phoneFilter);
        String trimmedPhoneFilter = phoneFilter.trim();
        if (!PhoneFilter.isValidPhoneFilter(trimmedPhoneFilter)) {
            throw new ParseException(PhoneFilter.MESSAGE_CONSTRAINTS);
        }
        return new PhoneFilter(trimmedPhoneFilter);
    }

    /**
     * Parses a {@code String emailFilter} into a {@code EmailFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code emailFilter} is invalid.
     */
    public static EmailFilter parseEmailFilter(String emailFilter) throws ParseException {
        requireNonNull(emailFilter);
        String trimmedEmailFilter = emailFilter.trim();
        if (!EmailFilter.isValidEmailFilter(trimmedEmailFilter)) {
            throw new ParseException(EmailFilter.MESSAGE_CONSTRAINTS);
        }
        return new EmailFilter(trimmedEmailFilter);
    }

    /**
     * Parses a {@code String addressFilter} into a {@code AddressFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code addressFilter} is invalid.
     */
    public static AddressFilter parseAddressFilter(String addressFilter) throws ParseException {
        requireNonNull(addressFilter);
        String trimmedAddressFilter = addressFilter.trim();
        if (!AddressFilter.isValidAddressFilter(trimmedAddressFilter)) {
            throw new ParseException(AddressFilter.MESSAGE_CONSTRAINTS);
        }
        return new AddressFilter(trimmedAddressFilter);
    }

    /**
     * Parses a {@code String subjectNameFilter} into a {@code SubjectNameFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectNameFilter} is invalid.
     */
    public static SubjectNameFilter parseSubjectNameFilter(String subjectNameFilter) throws ParseException {
        requireNonNull(subjectNameFilter);
        String trimmedSubjectNameFilter = subjectNameFilter.trim();
        if (!SubjectNameFilter.isValidSubjectNameFilter(trimmedSubjectNameFilter)) {
            throw new ParseException(SubjectNameFilter.MESSAGE_CONSTRAINTS);
        }
        return new SubjectNameFilter(trimmedSubjectNameFilter);
    }

    /**
     * Parses a {@code String subjectLevelFilter} into a {@code SubjectLevelFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectLevelFilter} is invalid.
     */
    public static SubjectLevelFilter parseSubjectLevelFilter(String subjectLevelFilter) throws ParseException {
        requireNonNull(subjectLevelFilter);
        String trimmedSubjectLevelFilter = subjectLevelFilter.trim();
        if (!SubjectLevelFilter.isValidSubjectLevelFilter(trimmedSubjectLevelFilter)) {
            throw new ParseException(SubjectLevelFilter.MESSAGE_CONSTRAINTS);
        }
        return new SubjectLevelFilter(trimmedSubjectLevelFilter);
    }

    /**
     * Parses a {@code String subjectRateFilter} into a {@code SubjectRateFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectRateFilter} is invalid.
     */
    public static SubjectRateFilter parseSubjectRateFilter(String subjectRateFilter) throws ParseException {
        requireNonNull(subjectRateFilter);
        String trimmedSubjectRateFilter = subjectRateFilter.trim();
        if (!SubjectRateFilter.isValidSubjectRateFilter(trimmedSubjectRateFilter)) {
            throw new ParseException(SubjectRateFilter.MESSAGE_CONSTRAINTS);
        }
        return new SubjectRateFilter(trimmedSubjectRateFilter);
    }

    /**
     * Parses a {@code String subjectExperienceFilter} into a {@code SubjectExperienceFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectExperienceFilter} is invalid.
     */
    public static SubjectExperienceFilter parseSubjectExperienceFilter(String subjectExperienceFilter)
            throws ParseException {
        requireNonNull(subjectExperienceFilter);
        String trimmedSubjectExperienceFilter = subjectExperienceFilter.trim();
        if (!SubjectExperienceFilter.isValidSubjectExperienceFilter(trimmedSubjectExperienceFilter)) {
            throw new ParseException(SubjectExperienceFilter.MESSAGE_CONSTRAINTS);
        }
        return new SubjectExperienceFilter(trimmedSubjectExperienceFilter);
    }

    /**
     * Parses a {@code String subjectQualificationFilter} into a {@code SubjectQualificationFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectQualificationFilter} is invalid.
     */
    public static SubjectQualificationFilter parseSubjectQualificationFilter(String subjectQualificationFilter)
            throws ParseException {
        requireNonNull(subjectQualificationFilter);
        String trimmedSubjectQualificationFilter = subjectQualificationFilter.trim();
        if (!SubjectQualificationFilter.isValidSubjectQualificationFilter(trimmedSubjectQualificationFilter)) {
            throw new ParseException(SubjectQualificationFilter.MESSAGE_CONSTRAINTS);
        }
        return new SubjectQualificationFilter(trimmedSubjectQualificationFilter);
    }

    /**
     * Parses a {@code String appointmentDateTimeFilter} into a {@code AppointmentDateTimeFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code appointmentDateTimeFilter} is invalid.
     */
    public static AppointmentDateTimeFilter parseAppointmentDateTimeFilter(String appointmentDateTimeFilter)
            throws ParseException {
        requireNonNull(appointmentDateTimeFilter);
        String trimmedAppointmentDateTimeFilter = appointmentDateTimeFilter.trim();
        if (!AppointmentDateTimeFilter.isValidAppointmentDateTimeFilter(trimmedAppointmentDateTimeFilter)) {
            throw new ParseException(AppointmentDateTimeFilter.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentDateTimeFilter(trimmedAppointmentDateTimeFilter);
    }

    /**
     * Parses {@code List} of {@code String names}, {@code String genders}, {@code String phones},
     * {@code String emails}, {@code String addresses}, {@code String subjectNames},
     * {@code String subjectLevels}, {@code String subjectRates}, {@code String subjectExperiences},
     * and {@code String subjectQualifications} into a {@code TutorFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any items are invalid.
     */
    public static TutorFilter parseTutorFilter(
            List<String> names,
            List<String> genders,
            List<String> phones,
            List<String> emails,
            List<String> addresses,
            List<String> subjectNames,
            List<String> subjectLevels,
            List<String> subjectRates,
            List<String> subjectExperiences,
            List<String> subjectQualifications) throws ParseException {
        requireNonNull(names);
        requireNonNull(genders);
        requireNonNull(phones);
        requireNonNull(emails);
        requireNonNull(addresses);
        requireNonNull(subjectNames);
        requireNonNull(subjectLevels);
        requireNonNull(subjectRates);
        requireNonNull(subjectExperiences);
        requireNonNull(subjectQualifications);

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>();
        for (String value: names) {
            nameFilters.add(FilterParserUtil.parseNameFilter(value));
        }

        Set<Predicate<Gender>> genderFilters = new LinkedHashSet<>();
        for (String value: genders) {
            genderFilters.add(FilterParserUtil.parseGenderFilter(value));
        }

        Set<Predicate<Phone>> phoneFilters = new LinkedHashSet<>();
        for (String value: phones) {
            phoneFilters.add(FilterParserUtil.parsePhoneFilter(value));
        }

        Set<Predicate<Email>> emailFilters = new LinkedHashSet<>();
        for (String value: emails) {
            emailFilters.add(FilterParserUtil.parseEmailFilter(value));
        }

        Set<Predicate<Address>> addressFilters = new LinkedHashSet<>();
        for (String value: addresses) {
            addressFilters.add(FilterParserUtil.parseAddressFilter(value));
        }

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>();
        for (String value: subjectNames) {
            subjectNameFilters.add(FilterParserUtil.parseSubjectNameFilter(value));
        }

        Set<Predicate<SubjectLevel>> subjectLevelFilters = new LinkedHashSet<>();
        for (String value: subjectLevels) {
            subjectLevelFilters.add(FilterParserUtil.parseSubjectLevelFilter(value));
        }

        Set<Predicate<SubjectRate>> subjectRateFilters = new LinkedHashSet<>();
        for (String value: subjectRates) {
            subjectRateFilters.add(FilterParserUtil.parseSubjectRateFilter(value));
        }

        Set<Predicate<SubjectExperience>> subjectExperienceFilters = new LinkedHashSet<>();
        for (String value: subjectExperiences) {
            subjectExperienceFilters.add(FilterParserUtil.parseSubjectExperienceFilter(value));
        }

        Set<Predicate<SubjectQualification>> subjectQualificationFilters = new LinkedHashSet<>();
        for (String value: subjectQualifications) {
            subjectQualificationFilters.add(FilterParserUtil.parseSubjectQualificationFilter(value));
        }

        if (!CollectionUtil.isAnyNotEmpty(
                nameFilters,
                genderFilters,
                phoneFilters,
                emailFilters,
                addressFilters,
                subjectNameFilters,
                subjectLevelFilters,
                subjectRateFilters,
                subjectExperienceFilters,
                subjectQualificationFilters)) {
            throw new ParseException(MESSAGE_NO_FILTER_PROVIDED);
        }

        TutorFilter tutorFilter = new TutorFilter(
                nameFilters,
                genderFilters,
                phoneFilters,
                emailFilters,
                addressFilters,
                subjectNameFilters,
                subjectLevelFilters,
                subjectRateFilters,
                subjectExperienceFilters,
                subjectQualificationFilters);
        return tutorFilter;
    }

    /**
     * Parses {@code List} of {@code String names}, {@code String subjectNames},
     * {@code String timesFrom}, {@code String timesTo}, and {@code String locations}
     * into a {@code AppointmentFilter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any items are invalid.
     */
    public static AppointmentFilter parseAppointmentFilter(
            List<String> names,
            List<String> subjectNames,
            List<String> timesFrom,
            List<String> timesTo,
            List<String> locations) throws ParseException {
        requireNonNull(names);
        requireNonNull(subjectNames);
        requireNonNull(timesFrom);
        requireNonNull(timesTo);
        requireNonNull(locations);

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<>();
        for (String value: names) {
            nameFilters.add(FilterParserUtil.parseNameFilter(value));
        }

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<>();
        for (String value: subjectNames) {
            subjectNameFilters.add(FilterParserUtil.parseSubjectNameFilter(value));
        }

        Set<Predicate<AppointmentDateTime>> timeFromFilters = new LinkedHashSet<>();
        for (String value: timesFrom) {
            timeFromFilters.add(FilterParserUtil.parseAppointmentDateTimeFilter(value));
        }

        Set<Predicate<AppointmentDateTime>> timeToFilters = new LinkedHashSet<>();
        for (String value: timesTo) {
            timeToFilters.add(FilterParserUtil.parseAppointmentDateTimeFilter(value));
        }

        Set<Predicate<Address>> locationFilters = new LinkedHashSet<>();
        for (String value: locations) {
            locationFilters.add(FilterParserUtil.parseAddressFilter(value));
        }

        if (!CollectionUtil.isAnyNotEmpty(
                nameFilters,
                subjectNameFilters,
                timeFromFilters,
                timeToFilters,
                locationFilters)) {
            throw new ParseException(MESSAGE_NO_FILTER_PROVIDED);
        }

        AppointmentFilter appointmentFilter = new AppointmentFilter(
                nameFilters,
                subjectNameFilters,
                timeFromFilters,
                timeToFilters,
                locationFilters);
        return appointmentFilter;
    }
}
