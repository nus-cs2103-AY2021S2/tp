package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_UPDATED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_WEEKLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DESCRIPTION,
                PREFIX_EVENT_DATE, PREFIX_EVENT_TIME, PREFIX_EVENT_WEEKLY);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateEventCommand.MESSAGE_USAGE));
        }

        Index projectIndex;

        try {
            projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, pe);
        }

        Index targetEventIndex;

        try {
            targetEventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, pe);
        }

        UpdateEventCommand.UpdateEventDescriptor updateEventDescriptor = new UpdateEventCommand.UpdateEventDescriptor();

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            updateEventDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_DATE).isPresent()) {
            updateEventDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_EVENT_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_TIME).isPresent()) {
            updateEventDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_EVENT_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_WEEKLY).isPresent()) {
            updateEventDescriptor.setIsWeekly(
                    ParserUtil.parseIsWeekly(argMultimap.getValue(PREFIX_EVENT_WEEKLY).get()));
        }

        if (!updateEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_UPDATED);
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
