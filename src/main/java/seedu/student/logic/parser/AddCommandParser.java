package seedu.student.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.student.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.student.commons.core.LogsCenter;
import seedu.student.logic.LogicManager;
import seedu.student.logic.commands.AddCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.Address;
import seedu.student.model.student.Email;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MedicalDetails;
import seedu.student.model.student.Name;
import seedu.student.model.student.Phone;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;
import seedu.student.model.student.VaccinationStatus;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert args.length() >= 8 : "there must be at least 8 arguments";
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FACULTY,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_VACCINATION_STATUS, PREFIX_MEDICAL_DETAILS,
                        PREFIX_SCHOOL_RESIDENCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FACULTY, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_VACCINATION_STATUS, PREFIX_EMAIL, PREFIX_MEDICAL_DETAILS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        MatriculationNumber matriculationNumber;

        try {
            matriculationNumber = ParserUtil.parseMatric(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatriculationNumber.MESSAGE_CONSTRAINTS));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
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
        logger.info("----[Student to be added][" + student.toString() + "]");
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
