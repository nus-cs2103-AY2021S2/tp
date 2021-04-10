package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.commands.util.DeleteUtil.MESSAGE_DELETE_BY_STATUS_USAGE;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import seedu.taskify.logic.commands.DeleteByStatusCommand;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;

public class DeleteByStatusCommandParserTest {

    private DeleteByStatusCommandParser parser = new DeleteByStatusCommandParser();

    @ParameterizedTest
    @ValueSource(strings = {" expired  -all", " completed   -all", "uncompleted -all    "})
    public void parse_deleteByStatusAndArgsValid_returnsDeleteByStatusCommand(String input) {
        switch (input) {
        case " expired  -all":
            assertParseSuccess(parser, input, new DeleteByStatusCommand(new Status(StatusType.EXPIRED)));
            break;
        case " completed   -all":
            assertParseSuccess(parser, input, new DeleteByStatusCommand(new Status(StatusType.COMPLETED)));
            break;
        case "uncompleted -all    ":
            assertParseSuccess(parser, input, new DeleteByStatusCommand(new Status(StatusType.UNCOMPLETED)));
            break;
        default:
            assert false;
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {" expired all", "uncompleted --all", "Completed -all"})
    public void parse_deleteByStatusAndArgsInvalid_throwsParseException(String input) {
        assertParseFailure(parser, input, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_DELETE_BY_STATUS_USAGE));
    }
}
