package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.FindCommand;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
        ;
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        MatriculationNumber matriculationNumber = new MatriculationNumber("A0202893R");
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new StudentContainsMatriculationNumberPredicate(matriculationNumber),
                                new AppointmentListContainsMatriculationNumberPredicate(matriculationNumber),
                                new AppointmentContainsMatriculationNumberPredicate(matriculationNumber));
        assertParseSuccess(parser, "A0202893R", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n A0202893R \n \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {

        // invalid matriculation number with all numbers
        assertParseFailure(parser, "0234567891", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number not starting with alphabet A
        assertParseFailure(parser, "E0491242F" , String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with only starting alphabet of A
        assertParseFailure(parser, "A" , String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with starting alphabet as A but no ending alphabet
        assertParseFailure(parser, "A02942125", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with starting alphabet as A, an ending alphabet but
        // more than 9 alphanumeric characters

        assertParseFailure(parser, "A023490931T", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with starting alphabet as A, an ending alphabet but
        // less than 9 alphanumeric characters

        assertParseFailure(parser, "A0234T", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with starting alphabet as A, an ending alphabet
        // and 9 alphanumeric characters but the second to eight-th position are alphanumeric characters

        assertParseFailure(parser, "A027E34D", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with starting alphabet as A, no ending alphabet
        // and 9 alphanumeric characters but the second to eight-th position are alphanumeric characters

        assertParseFailure(parser, "A027E341", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // invalid matriculation number with no starting alphabet as A, an ending alphabet
        // and 9 alphanumeric characters but the second to eight-th position are alphanumeric characters

        assertParseFailure(parser, "Q0123683R", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }
}
