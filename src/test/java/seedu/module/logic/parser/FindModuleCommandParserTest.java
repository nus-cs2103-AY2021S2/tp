package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.FindModuleCommand;
import seedu.module.model.task.Module;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the NotDoneCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the NotDoneCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class FindModuleCommandParserTest {
    private FindModuleCommandParser parser = new FindModuleCommandParser();

    @Test
    public void parse_validArgs_returnsFindModuleCommand() {
        assertParseSuccess(parser, "CS2106", new FindModuleCommand(new Module("CS2106").toString()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "Fa sa", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindModuleCommand.MESSAGE_USAGE));
    }
}
