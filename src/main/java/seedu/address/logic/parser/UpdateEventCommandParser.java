package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.UpdateEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateEventCommand object
 */
public class UpdateEventCommandParser implements Parser<UpdateEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code UpdateEventCommand}.
     * and returns an {@code UpdateEventCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public UpdateEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_UPDATE_INDEX, PREFIX_DESCRIPTION,
                PREFIX_REPEATABLE_INTERVAL, PREFIX_REPEATABLE_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_UPDATE_INDEX) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateEventCommand.MESSAGE_USAGE));
        }

        Index projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        Index targetEventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_UPDATE_INDEX).get());

        UpdateEventCommand.UpdateEventDescriptor updateEventDescriptor = new UpdateEventCommand.UpdateEventDescriptor();

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            updateEventDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_REPEATABLE_INTERVAL).isPresent()) {
            updateEventDescriptor.setInterval(
                    ParserUtil.parseInterval(argMultimap.getValue(PREFIX_REPEATABLE_INTERVAL).get()));
        }
        if (argMultimap.getValue(PREFIX_REPEATABLE_DATE).isPresent()) {
            updateEventDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_REPEATABLE_DATE).get()));
        }

        if (!updateEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateEventCommand(projectIndex, targetEventIndex, updateEventDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
