package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE_INDEX;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.Deadline;

/**
 * Parses input arguments and creates a new UpdateDeadlineCommand object
 */
public class UpdateDeadlineCommandParser implements Parser<UpdateDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateDeadlineCommand.
     * and returns an UpdateDeadlineCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public UpdateDeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_UPDATE_INDEX, PREFIX_DESCRIPTION, PREFIX_DEADLINE_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_UPDATE_INDEX, PREFIX_DESCRIPTION, PREFIX_DEADLINE_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateDeadlineCommand.MESSAGE_USAGE));
        }

        Index projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        Index targetDeadlineIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_UPDATE_INDEX).get());
        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DEADLINE_DATE).get());

        CompletableDeadline deadline = new Deadline(description, date);

        return new UpdateDeadlineCommand(projectIndex, targetDeadlineIndex, deadline);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
