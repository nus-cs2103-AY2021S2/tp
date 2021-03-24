package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.budget.Budget;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String SUBJECT_LIST_INVALID_LENGTH = "Each Tutor Subject must have all fields defined.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String budgetValue} into {@code Budget}
     * @param budgetValue Value to set as budget.
     * @throws ParseException
     */
    public static Budget parseBudget(String budgetValue) throws ParseException {
        requireNonNull(budgetValue);
        String trimmedBudget = budgetValue.trim();
        if (!Budget.isValidBudget(trimmedBudget)) {
            throw new ParseException(Budget.MESSAGE_CONSTRAINTS);
        }
        return new Budget(budgetValue);
    }

    /**
     * Parses a {@code String subjectName} into a {@code SubjectName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectName} is invalid.
     */
    public static SubjectName parseSubjectName(String subjectName) throws ParseException {
        requireNonNull(subjectName);
        String trimmedSubjectName = subjectName.trim();
        if (!SubjectName.isValidName(trimmedSubjectName)) {
            throw new ParseException(SubjectName.MESSAGE_CONSTRAINTS);
        }
        return new SubjectName(trimmedSubjectName);
    }

    /**
     * Parses a {@code String subjectLevel} into a {@code SubjectLevel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectLevel} is invalid.
     */
    public static SubjectLevel parseSubjectLevel(String subjectLevel) throws ParseException {
        requireNonNull(subjectLevel);
        String trimmedSubjectLevel = subjectLevel.trim();
        if (!SubjectLevel.isValidLevel(trimmedSubjectLevel)) {
            throw new ParseException(SubjectLevel.MESSAGE_CONSTRAINTS);
        }
        return new SubjectLevel(trimmedSubjectLevel);
    }

    /**
     * Parses a {@code String subjectRate} into a {@code SubjectRate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectRate} is invalid.
     */
    public static SubjectRate parseSubjectRate(String subjectRate) throws ParseException {
        requireNonNull(subjectRate);
        String trimmedSubjectRate = subjectRate.trim();
        if (!SubjectRate.isValidRate(trimmedSubjectRate)) {
            throw new ParseException(SubjectRate.MESSAGE_CONSTRAINTS);
        }
        return new SubjectRate(trimmedSubjectRate);
    }

    /**
     * Parses a {@code String subjectExperience} into a {@code SubjectExperience}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectExperience} is invalid.
     */
    public static SubjectExperience parseSubjectExperience(String subjectExperience) throws ParseException {
        requireNonNull(subjectExperience);
        String trimmedSubjectExperience = subjectExperience.trim();
        if (!SubjectExperience.isValidExperience(trimmedSubjectExperience)) {
            throw new ParseException(SubjectExperience.MESSAGE_CONSTRAINTS);
        }
        return new SubjectExperience(trimmedSubjectExperience);
    }

    /**
     * Parses a {@code String subjectQualification} into a {@code SubjectQualification}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subjectQualification} is invalid.
     */
    public static SubjectQualification parseSubjectQualification(String subjectQualification) throws ParseException {
        requireNonNull(subjectQualification);
        String trimmedSubjectQualification = subjectQualification.trim();
        if (!SubjectQualification.isValidQualification(trimmedSubjectQualification)) {
            throw new ParseException(SubjectQualification.MESSAGE_CONSTRAINTS);
        }
        return new SubjectQualification(trimmedSubjectQualification);
    }

    /**
     * Parses {@code List} of {@code String subjectNames}, {@code String subjectLevels},
     * {@code String subjectRates}, {@code String subjectExperiences},
     * and {@code String subjectQualifications} into a {@code SubjectList}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the number of items in each {@code List} is not equal or
     * if any items are invalid.
     */
    public static SubjectList parseSubjectList(
            List<String> subjectNames,
            List<String> subjectLevels,
            List<String> subjectRates,
            List<String> subjectExperiences,
            List<String> subjectQualifications
    ) throws ParseException {
        requireNonNull(subjectNames);
        requireNonNull(subjectLevels);
        requireNonNull(subjectRates);
        requireNonNull(subjectExperiences);
        requireNonNull(subjectQualifications);

        int numSubjects = subjectNames.size();
        if (subjectLevels.size() != numSubjects
                || subjectRates.size() != numSubjects
                || subjectExperiences.size() != numSubjects
                || subjectQualifications.size() != numSubjects) {
            throw new ParseException(SUBJECT_LIST_INVALID_LENGTH);
        }

        SubjectList subjectList = new SubjectList();

        for (int i = 0; i < numSubjects; i++) {
            SubjectName subjectName = parseSubjectName(subjectNames.get(i));
            SubjectLevel subjectLevel = parseSubjectLevel(subjectLevels.get(i));
            SubjectRate subjectRate = parseSubjectRate(subjectRates.get(i));
            SubjectExperience subjectExperience = parseSubjectExperience(subjectExperiences.get(i));
            SubjectQualification subjectQualification = parseSubjectQualification(subjectQualifications.get(i));

            TutorSubject tutorSubject = new TutorSubject(
                    subjectName,
                    subjectLevel,
                    subjectRate,
                    subjectExperience,
                    subjectQualification);

            subjectList.add(tutorSubject);
        }

        return subjectList;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static AppointmentDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim().toUpperCase();
        if (!AppointmentDateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentDateTime(trimmedDateTime);
    }
}
