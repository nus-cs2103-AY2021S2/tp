package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SpecialDate;

/**
 * Parses input arguments and creates a new DateCommand object
 */
public class AddDateCommandParser implements Parser<AddDateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDateCommand
     * and returns a AddDateCommand object for execution
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDateCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDateCommand.MESSAGE_USAGE));
        }

        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        SpecialDate specialDate = ParserUtil.parseSpecialDate(date, description);

        return new AddDateCommand(index, specialDate);
    }
}
