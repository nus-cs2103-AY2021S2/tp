package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.logic.commands.FavouriteCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FavouriteCommand;

public class FavouriteCommandParserTest {
    private FavouriteCommandParser parser = new FavouriteCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFavouriteCommand() {
        assertParseSuccess(parser, "1", new FavouriteCommand(INDEX_FIRST_CONTACT, true));
    }

    @Test
    public void parse_validArgs_returnsUnfavouriteCommand() {
        assertParseSuccess(parser, "1 o/remove", new FavouriteCommand(INDEX_FIRST_CONTACT, false));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidOption_throwsParseException() {
        String invalidOption = "random";
        String expectedMessage = String.format(MESSAGE_INVALID_OPTION, invalidOption);
        assertParseFailure(parser, "2 o/" + invalidOption, expectedMessage);
    }

}
