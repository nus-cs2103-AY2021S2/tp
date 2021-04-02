package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.SortAscendingQuantityCommand;
import seedu.storemando.logic.commands.SortCommand;
import seedu.storemando.logic.commands.SortDescendingQuantityCommand;
import seedu.storemando.logic.commands.SortExpiryDateCommand;

class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "     ", expectedMessage);
    }

    @Test
    public void parse_invalidArgument_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        //check for correct number of words but wrong words given
        assertParseFailure(parser, "quantity invalid", expectedMessage);
        assertParseFailure(parser, "expirydate rising", expectedMessage);
        assertParseFailure(parser, "asc quantity", expectedMessage);
        assertParseFailure(parser, "expireddate", expectedMessage);
        assertParseFailure(parser, "quantity", expectedMessage);

        //check for incorrect number of words given
        assertParseFailure(parser, "quantity invalid command", expectedMessage);
        assertParseFailure(parser, "expirydate rising high", expectedMessage);
        assertParseFailure(parser, "expirydate quantity asc", expectedMessage);
    }

    @Test
    public void parse_validArgument_success() {
        SortExpiryDateCommand expectedExpiryDateCommand = new SortExpiryDateCommand();
        SortAscendingQuantityCommand expectedAscQuantityCommand = new SortAscendingQuantityCommand();
        SortDescendingQuantityCommand expectedDescQuantityCommand = new SortDescendingQuantityCommand();

        //check for valid increasing quantity user inputs
        assertParseSuccess(parser, "quantity asc", expectedAscQuantityCommand);
        assertParseSuccess(parser, "QuAnTiTy Asc", expectedAscQuantityCommand);
        assertParseSuccess(parser, "\n     QuAnTiTy Asc", expectedAscQuantityCommand);

        //check for valid decreasing quantity user inputs
        assertParseSuccess(parser, "quantity desc", expectedDescQuantityCommand);
        assertParseSuccess(parser, "QuAnTiTy DeSc", expectedDescQuantityCommand);
        assertParseSuccess(parser, "\n     QuAnTiTy DeSc", expectedDescQuantityCommand);

        //check for valid expiryDate user inputs
        assertParseSuccess(parser, "expirydate", expectedExpiryDateCommand);
        assertParseSuccess(parser, "eXpIrYdAtE", expectedExpiryDateCommand);
        assertParseSuccess(parser, "\n     eXpIrYdAtE", expectedExpiryDateCommand);
    }
}
