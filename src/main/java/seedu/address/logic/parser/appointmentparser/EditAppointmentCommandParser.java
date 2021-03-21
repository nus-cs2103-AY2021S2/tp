package seedu.address.logic.parser.appointmentparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.appointmentcommands.EditAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT_NAME,
                        PREFIX_DATE,
                        PREFIX_TIME_FROM, PREFIX_TIME_TO, PREFIX_LOCATION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAppointmentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SUBJECT_NAME).isPresent()) {
            editAppointmentDescriptor.setSubjectName(ParserUtil.parseSubjectName(
                    argMultimap.getValue(PREFIX_SUBJECT_NAME).get()));
        }
        // TODO: Implement better handling of date and times (and combinations)
        if (argMultimap.getValue(PREFIX_DATE).isPresent() || argMultimap.getValue(PREFIX_TIME_FROM).isPresent()) {
            if (!argMultimap.getValue(PREFIX_DATE).isPresent() || !argMultimap.getValue(PREFIX_TIME_FROM).isPresent()) {
                throw new ParseException("Both date and time must be specified.");
            }

            String dateString = argMultimap.getValue(PREFIX_DATE).get();
            String timeFromString = argMultimap.getValue(PREFIX_TIME_FROM).get();
            String dateTimeString = dateString + " " + timeFromString;
            editAppointmentDescriptor.setDateTime(ParserUtil.parseDateTime(dateTimeString));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editAppointmentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_LOCATION).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }
}
