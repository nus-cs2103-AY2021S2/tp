package seedu.student.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import seedu.student.commons.core.index.Index;
import seedu.student.commons.util.StringUtil;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Address;
import seedu.student.model.student.Email;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MedicalDetails;
import seedu.student.model.student.Name;
import seedu.student.model.student.Phone;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.VaccinationStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String matriculationNumber} into a {@code MatriculationNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code matriculationNumber} is invalid.
     */
    public static MatriculationNumber parseMatric(String matriculationNumber) throws ParseException {
        requireNonNull(matriculationNumber);
        String trimmedMatric = matriculationNumber.trim();
        if (!MatriculationNumber.isValidMatric(trimmedMatric)) {
            throw new ParseException(MatriculationNumber.MESSAGE_CONSTRAINTS);
        }
        return new MatriculationNumber(trimmedMatric);
    }

    /**
     * Parses a {@code String faculty} into a {@code faculty}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code faculty} is invalid.
     */
    public static Faculty parseFaculty(String faculty) throws ParseException {
        requireNonNull(faculty);
        String trimmedFaculty = faculty.trim();
        if (!Faculty.isValidFaculty(trimmedFaculty)) {
            throw new ParseException(Faculty.MESSAGE_CONSTRAINTS);
        }
        return new Faculty(trimmedFaculty);
    }

    /**
     * Parses a {@code String residence} into a {@code SchoolResidence}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code residence} is invalid.
     */
    public static SchoolResidence parseResidence(String residence) throws ParseException {
        requireNonNull(residence);
        String trimmedResidence = residence.trim();
        try {
            return new SchoolResidence(trimmedResidence);
        } catch (IllegalArgumentException e) {
            throw new ParseException(SchoolResidence.MESSAGE_CONSTRAINTS);
        }
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
     * Parses a {@code String vaccinationStatus} into a {@code vaccinationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code vaccinationStatus} is invalid.
     */
    public static VaccinationStatus parseVacStatus(String vaccinationStatus) throws ParseException {
        requireNonNull(vaccinationStatus);
        String trimmedVacStatus = vaccinationStatus.trim();
        if (!VaccinationStatus.isValidStatus(trimmedVacStatus)) {
            throw new ParseException(VaccinationStatus.MESSAGE_CONSTRAINTS);
        }
        return new VaccinationStatus(trimmedVacStatus.toLowerCase());
    }

    /**
     * Parses a {@code String medicalDetails} into a {@code medicalDetails}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code medicalDetails} is invalid.
     */
    public static MedicalDetails parseMedicalDetails(String medicalDetails) throws ParseException {
        requireNonNull(medicalDetails);
        String trimmedMedDetails = medicalDetails.trim();
        if (!MedicalDetails.isValidMedicalDetails(trimmedMedDetails)) {
            throw new ParseException(MedicalDetails.MESSAGE_CONSTRAINTS);
        }
        return new MedicalDetails(trimmedMedDetails);
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
     * Parses a {@code String schoolResidence} into an {@code schoolResidence}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code schoolResidence} is invalid.
     */
    public static SchoolResidence parseSchoolRes(Optional<String> schoolResidence) throws ParseException {
        requireNonNull(schoolResidence);
        if (schoolResidence.isEmpty()) {
            return new SchoolResidence("DOES_NOT_LIVE_ON_CAMPUS");
        } else {
            String trimmedSchoolRes = schoolResidence.get().trim();
            if (!SchoolResidence.isValidResidence(trimmedSchoolRes)) {
                throw new ParseException(SchoolResidence.MESSAGE_CONSTRAINTS);
            }
            return new SchoolResidence(trimmedSchoolRes);
        }
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate);
        } catch (DateTimeParseException e) {
            throw new ParseException(Appointment.MESSAGE_DATE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String time} into a {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        try {
            LocalTime parsedTime = LocalTime.parse(trimmedTime);
            if (!Appointment.isValidTime(parsedTime)) {
                throw new Exception();
            }
            return parsedTime;
        } catch (Exception e) {
            throw new ParseException(Appointment.MESSAGE_TIME_CONSTRAINTS);
        }
    }
}
