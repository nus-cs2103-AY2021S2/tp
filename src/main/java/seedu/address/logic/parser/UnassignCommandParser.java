package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_ID;

import java.util.Set;

import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Unassignment;
import seedu.address.model.person.PersonId;
import seedu.address.model.session.SessionId;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class UnassignCommandParser implements Parser<UnassignCommand> {
    @Override
    public UnassignCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_TUTOR_ID,
                PREFIX_CLASS_ID);

        //No class given
        if (!argMultimap.getValue(PREFIX_CLASS_ID).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }

        //No people given
        if (!argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()
                && !argMultimap.getValue(PREFIX_TUTOR_ID).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }

        SessionId sessionId = ParserUtil.parseSessionId(PREFIX_CLASS_ID + argMultimap.getValue(PREFIX_CLASS_ID).get());
        Set<PersonId> studentIds = ParserUtil.parseStudentIds(argMultimap.getAllValues(PREFIX_STUDENT_ID));

        PersonId tutorId = null;
        if (argMultimap.getValue(PREFIX_TUTOR_ID).isPresent()) {
            tutorId = ParserUtil.parseTutorId(argMultimap.getValue(PREFIX_TUTOR_ID).get());
        }

        Unassignment unassignment = new Unassignment(studentIds, tutorId, sessionId);
        return new UnassignCommand(unassignment);
    }
}
