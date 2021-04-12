package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.DeleteStudentCommand;
import seedu.student.model.student.MatriculationNumber;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteStudentCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteStudentCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteStudentCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, MATRIC_NUMBER_FIRST_STUDENT,
                new DeleteStudentCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_STUDENT)));
    }

    @Test
    public void parse_validArgsTwo_returnsDeleteCommand() {
        assertParseSuccess(parser, MATRIC_NUMBER_THIRD_STUDENT,
                new DeleteStudentCommand(new MatriculationNumber(MATRIC_NUMBER_THIRD_STUDENT)));
    }

    @Test
    public void parse_invalidSingularCharArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleCharArgs_throwsParseException() {
        assertParseFailure(parser, "abc def",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSingularNumericArgs_throwsParseException() {
        assertParseFailure(parser, "7",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleNumericArgs_throwsParseException() {
        assertParseFailure(parser, "1 23 4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMatricNumber_throwsParseException() {
        assertParseFailure(parser, "A12345678X",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSingularArgs_throwsParseException() {
        assertParseFailure(parser, "a12nh45./?",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCaseSensitiveArgs_throwsParseException() {
        // A1234567X exists in the StudentBook
        assertParseFailure(parser, "a1234567X",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSpacingArgs_throwsParseException() {
        // A1234567X exists in the StudentBook
        assertParseFailure(parser, "A123 4567X",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
    }

}
