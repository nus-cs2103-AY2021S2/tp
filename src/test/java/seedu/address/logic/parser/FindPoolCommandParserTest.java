package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_NO_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPoolCommand;
import seedu.address.model.person.Name;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

public class FindPoolCommandParserTest {
    private final String AMY_FIRST_NAME = VALID_NAME_AMY.split("\\s+")[0];
    private final String BOB_FIRST_NAME = VALID_NAME_BOB.split("\\s+")[0];

    private FindPoolCommandParser parser = new FindPoolCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPoolCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongPrefix_throwsParseException() {
        assertParseFailure(parser,
                PREFIX_ADDRESS + VALID_NAME_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindPoolCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_namePrefixEmptyArg_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_NAME + "     ", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_NAME + INVALID_NAME_NO_PREFIX, Name.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // one keyword, no leading and trailing whitespaces
        FindPoolCommand expectedFindPoolCommand =
                new FindPoolCommand(new PooledPassengerContainsKeywordsPredicate(Arrays.asList("Amy")));
        assertParseSuccess(parser, " " + PREFIX_NAME + AMY_FIRST_NAME, expectedFindPoolCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PREFIX_NAME + AMY_FIRST_NAME + " \n \t", expectedFindPoolCommand);
    }

    @Test
    public void parse_validNameMultiArgs_returnsFindCommand() {
        // more than one keyword, no leading and trailing whitespaces - n/Amy n/Bob
        FindPoolCommand expectedFindPoolCommand =
                new FindPoolCommand(new PooledPassengerContainsKeywordsPredicate(
                        Arrays.asList(AMY_FIRST_NAME, BOB_FIRST_NAME)));
        assertParseSuccess(parser, " " + PREFIX_NAME
                + AMY_FIRST_NAME + " " + PREFIX_NAME + BOB_FIRST_NAME, expectedFindPoolCommand);

        // multiple whitespaces and tabs between keywords
        assertParseSuccess(parser, " \n " + PREFIX_NAME + AMY_FIRST_NAME + " "
                + PREFIX_NAME + BOB_FIRST_NAME + "\n \t", expectedFindPoolCommand);
    }

    @Test
    public void parse_validNameArgWithWhitespace_returnsFindCommand() {
        // no leading and trailing whitespaces - n/Amy Bee
        FindPoolCommand expectedFindPoolCommand =
                new FindPoolCommand(new PooledPassengerContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY)));
        assertParseSuccess(parser, NAME_DESC_AMY, expectedFindPoolCommand);
    }
}
