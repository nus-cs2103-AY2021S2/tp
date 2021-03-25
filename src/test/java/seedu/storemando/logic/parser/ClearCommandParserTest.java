package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ClearCommand;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

public class ClearCommandParserTest {

    private final ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        ClearCommand expectedClearCommand = new ClearCommand();
        assertParseSuccess(parser, "  ", expectedClearCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "chocolate",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validLocationArgs_returnsClearCommand() {
        String[] locationKeyword = {"kitchen"};
        ClearCommand expectedClearCommand =
            new ClearCommand(new LocationContainsKeywordsPredicate(Arrays.asList(locationKeyword)));
        assertParseSuccess(parser, " l/kitchen", expectedClearCommand);
    }

}
