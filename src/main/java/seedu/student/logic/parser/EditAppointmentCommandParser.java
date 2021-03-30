package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import seedu.student.logic.commands.EditAppointmentCommand;
import seedu.student.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.MatriculationNumber;

public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {
    private static final int INDEX_OF_MATRIC = 0;

    @Override
    public EditAppointmentCommand parse(String args) throws ParseException {
        assert args.length() >= 3;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_START_TIME);
        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_START_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE));
        }
        MatriculationNumber matriculationNumber;

        try {
            matriculationNumber = ParserUtil.parseMatric(args.trim().split(" ")[INDEX_OF_MATRIC]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        editAppointmentDescriptor.setMatriculationNumber(matriculationNumber);
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editAppointmentDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editAppointmentDescriptor.setStartTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }
        return new EditAppointmentCommand(matriculationNumber, editAppointmentDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
