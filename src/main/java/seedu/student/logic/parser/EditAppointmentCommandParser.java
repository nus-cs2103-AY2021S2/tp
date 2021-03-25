package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.student.commons.core.index.Index;
import seedu.student.logic.commands.EditAppointmentCommand;
import seedu.student.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.student.logic.parser.exceptions.ParseException;

public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    @Override
    public EditAppointmentCommand parse(String args) throws ParseException {
        //assert args.length() >= 2
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MATRICULATION_NUMBER, PREFIX_DATE, PREFIX_START_TIME,
                        PREFIX_END_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).isPresent()) {
            editAppointmentDescriptor.setMatriculationNumber(ParserUtil.parseMatric(argMultimap
                    .getValue(PREFIX_MATRICULATION_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editAppointmentDescriptor.setDate(LocalDate.parse(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editAppointmentDescriptor.setStartTime(LocalTime.parse(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editAppointmentDescriptor.setEndTime(LocalTime.parse(argMultimap.getValue(PREFIX_END_TIME).get()));
        }
        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }
        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }


}
