package seedu.address.logic.parser.scheduleparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditScheduleCommand object
 */
public class EditScheduleCommandParser implements Parser<EditScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditScheduleCommand
     * and returns an EditScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE,
                        PREFIX_TIME_FROM, PREFIX_TIME_TO, PREFIX_DESCRIPTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditScheduleCommand.MESSAGE_USAGE), pe);
        }

        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editScheduleDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }

        // TODO: Implement better handling of date and times (and combinations)
        if (argMultimap.getValue(PREFIX_DATE).isPresent() && argMultimap.getValue(PREFIX_TIME_FROM).isPresent()) {
            String dateString = argMultimap.getValue(PREFIX_DATE).get();
            String timeFromString = argMultimap.getValue(PREFIX_TIME_FROM).get();
            editScheduleDescriptor.setTimeFrom(ParserUtil.parseDateTime(dateString + " " + timeFromString));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent() && argMultimap.getValue(PREFIX_TIME_TO).isPresent()) {
            String dateString = argMultimap.getValue(PREFIX_DATE).get();
            String timeToString = argMultimap.getValue(PREFIX_TIME_TO).get();
            editScheduleDescriptor.setTimeTo(ParserUtil.parseDateTime(dateString + " " + timeToString));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editScheduleDescriptor
                    .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditScheduleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditScheduleCommand(index, editScheduleDescriptor);
    }
}
