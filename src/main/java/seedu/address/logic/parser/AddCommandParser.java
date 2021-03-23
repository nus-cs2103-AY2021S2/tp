package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Faculty;
import seedu.address.model.student.MatriculationNumber;
import seedu.address.model.student.MedicalDetails;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.SchoolResidence;
import seedu.address.model.student.Student;
import seedu.address.model.student.VaccinationStatus;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert args.length() >= 8 : "there must be at least 8 arguments";
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MATRICULATION_NUMBER, PREFIX_FACULTY,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_VACCINATION_STATUS, PREFIX_MEDICAL_DETAILS,
                        PREFIX_SCHOOL_RESIDENCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MATRICULATION_NUMBER, PREFIX_FACULTY, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_VACCINATION_STATUS, PREFIX_EMAIL, PREFIX_MEDICAL_DETAILS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        MatriculationNumber matriculationNumber = ParserUtil.parseMatric(argMultimap
                .getValue(PREFIX_MATRICULATION_NUMBER).get());
        Faculty faculty = ParserUtil.parseFaculty(argMultimap.getValue(PREFIX_FACULTY).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        VaccinationStatus vaccinationStatus = ParserUtil.parseVacStatus(argMultimap
                .getValue(PREFIX_VACCINATION_STATUS).get());
        MedicalDetails medicalDetails = ParserUtil.parseMedicalDetails(argMultimap.getValue(PREFIX_MEDICAL_DETAILS)
                .get());
        SchoolResidence schoolResidence = ParserUtil.parseSchoolRes(argMultimap.getValue(PREFIX_SCHOOL_RESIDENCE));

        Student student = new Student(name, matriculationNumber, faculty, phone, email, address, vaccinationStatus,
                medicalDetails, schoolResidence);

        return new AddCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
