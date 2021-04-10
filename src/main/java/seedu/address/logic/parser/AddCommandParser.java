package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_SCHOOL, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_GUARDIAN_NAME, PREFIX_GUARDIAN_PHONE, PREFIX_LEVEL,
                        PREFIX_SUBJECT, PREFIX_LESSON);

        //name and phone must be present when adding in a new student
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        //compulsory details
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

        //optional details
        Optional<School> school;
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            school = ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SCHOOL).get());
        } else {
            school = Optional.empty();
        }

        Optional<Email> email;
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        } else {
            email = Optional.empty();
        }

        Optional<Address> address;
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        } else {
            address = Optional.empty();
        }

        Optional<Name> guardianName;
        if (argMultimap.getValue(PREFIX_GUARDIAN_NAME).isPresent()) {
            guardianName = ParserUtil.parseGuardianName(argMultimap.getValue(PREFIX_GUARDIAN_NAME).get());
        } else {
            guardianName = Optional.empty();
        }

        Optional<Phone> guardianPhone;
        if (argMultimap.getValue(PREFIX_GUARDIAN_PHONE).isPresent()) {
            guardianPhone = ParserUtil.parseGuardianPhone(argMultimap.getValue(PREFIX_GUARDIAN_PHONE).get());
        } else {
            guardianPhone = Optional.empty();
        }

        Optional<Level> level;
        if (argMultimap.getValue(PREFIX_LEVEL).isPresent()) {
            level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
        } else {
            level = Optional.empty();
        }

        Set<Subject> subjectList = ParserUtil.parseSubjects(argMultimap.getAllValues(PREFIX_SUBJECT));
        Set<Lesson> lessonsList = ParserUtil.parseLessons(argMultimap.getAllValues(PREFIX_LESSON));

        Person person = new Person(name, phone, school, email, address, guardianName, guardianPhone,
                level, subjectList, lessonsList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
