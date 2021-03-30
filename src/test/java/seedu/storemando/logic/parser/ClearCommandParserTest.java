package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ClearCommand;
import seedu.storemando.model.item.LocationContainsPredicate;

public class ClearCommandParserTest {

    private final ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_emptyArg_returnsClearCommand() {
        ClearCommand expectedClearCommand = new ClearCommand();
        assertParseSuccess(parser, "  ", expectedClearCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "bedroom",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "n/bedroom",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validLocationArgs_returnsClearCommand() {
        String location = "kitchen";
        ClearCommand expectedClearCommand =
            new ClearCommand(new LocationContainsPredicate(location));
        assertParseSuccess(parser, " l/kitchen", expectedClearCommand);
        assertParseSuccess(parser, " l/    kitchen", expectedClearCommand);
        assertParseSuccess(parser, " l/kitchen    ", expectedClearCommand);
        assertParseSuccess(parser, " l/   kitchen   ", expectedClearCommand);
    }

}
