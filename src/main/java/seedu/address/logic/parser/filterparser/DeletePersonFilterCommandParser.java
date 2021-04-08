package seedu.address.logic.parser.filterparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.filtercommands.DeletePersonFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filter.TutorFilter;

/**
 * Parses input arguments and creates a new DeletePersonFilterCommand object
 */
public class DeletePersonFilterCommandParser implements Parser<DeletePersonFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePersonFilterCommand
     * and returns an DeletePersonFilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePersonFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SUBJECT_NAME, PREFIX_RATE, PREFIX_EDUCATION_LEVEL,
                        PREFIX_YEAR, PREFIX_QUALIFICATION);

        TutorFilter tutorFilter = FilterParserUtil.parseTutorFilter(
                argMultimap.getAllValues(PREFIX_NAME),
                argMultimap.getAllValues(PREFIX_GENDER),
                argMultimap.getAllValues(PREFIX_PHONE),
                argMultimap.getAllValues(PREFIX_EMAIL),
                argMultimap.getAllValues(PREFIX_ADDRESS),
                argMultimap.getAllValues(PREFIX_SUBJECT_NAME),
                argMultimap.getAllValues(PREFIX_EDUCATION_LEVEL),
                argMultimap.getAllValues(PREFIX_RATE),
                argMultimap.getAllValues(PREFIX_YEAR),
                argMultimap.getAllValues(PREFIX_QUALIFICATION));

        return new DeletePersonFilterCommand(tutorFilter);
    }
}
