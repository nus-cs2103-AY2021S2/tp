package seedu.address.logic.parser;



import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Unassignment;
import seedu.address.model.person.PersonId;
import seedu.address.model.session.SessionId;





public class UnassignCommandParser implements Parser<UnassignCommand> {
    @Override
    public UnassignCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_TUTOR_ID,
                PREFIX_CLASS_ID);
        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS_ID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }
        PersonId studentId = null;
        PersonId tutorId = null;
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            studentId = ParserUtil.parsePersonId("s/" + argMultimap.getValue(PREFIX_STUDENT_ID).get());
        }
        if (argMultimap.getValue(PREFIX_TUTOR_ID).isPresent()) {
            tutorId = ParserUtil.parsePersonId("t/" + argMultimap.getValue(PREFIX_TUTOR_ID).get());
        }
        SessionId sessionId = ParserUtil.parseSessionId("c/" + argMultimap.getValue(PREFIX_CLASS_ID).get());
        Unassignment unassignment = new Unassignment(studentId, tutorId, sessionId);
        return new UnassignCommand(unassignment);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
