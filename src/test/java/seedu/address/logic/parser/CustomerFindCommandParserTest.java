package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.customer.CustomerFindCommand;
import seedu.address.logic.parser.commands.customer.CustomerFindCommandParser;
import seedu.address.model.person.PersonNameContainsWordsPredicate;

public class CustomerFindCommandParserTest {

    private CustomerFindCommandParser parser = new CustomerFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CustomerFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        CustomerFindCommand expectedFindCommand =
                new CustomerFindCommand(new PersonNameContainsWordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
