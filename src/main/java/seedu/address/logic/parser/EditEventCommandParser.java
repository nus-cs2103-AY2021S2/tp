package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditEventCommand object
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STARTDATE, PREFIX_STARTTIME,
                        PREFIX_ENDDATE, PREFIX_ENDTIME, PREFIX_CATEGORY, PREFIX_TAG);

        Index index;

        try {
            index = SocheduleParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setName(SocheduleParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTDATE).isPresent()) {
            editEventDescriptor.setStartDate(SocheduleParserUtil.parseDate(
                    argMultimap.getValue(PREFIX_STARTDATE).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTTIME).isPresent()) {
            editEventDescriptor.setStartTime(SocheduleParserUtil.parseTime(
                    argMultimap.getValue(PREFIX_STARTTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_ENDDATE).isPresent()) {
            editEventDescriptor.setEndDate(SocheduleParserUtil.parseDate(
                    argMultimap.getValue(PREFIX_ENDDATE).get()));
        }
        if (argMultimap.getValue(PREFIX_ENDTIME).isPresent()) {
            editEventDescriptor.setEndTime(SocheduleParserUtil.parseTime(
                    argMultimap.getValue(PREFIX_ENDTIME).get()));
        }

        SocheduleParserUtil.parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(editEventDescriptor::setTags);
        SocheduleParserUtil.parseCategoriesForEdit(argMultimap.getAllValues(PREFIX_CATEGORY))
                .ifPresent(editEventDescriptor::setCategories);

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor);
    }
}
