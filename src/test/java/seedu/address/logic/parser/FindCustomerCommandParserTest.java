package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCustomerCommand;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.predicates.CustomerNamePredicate;
import seedu.address.model.util.ModelCompositePredicate;

public class FindCustomerCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        final List<String> keywords = Arrays.asList("Alice", "Bob");

        // no leading and trailing whitespaces
        FindCustomerCommand expectedFindCustomerCommand =
                new FindCustomerCommand(new ModelCompositePredicate<Customer>(new CustomerNamePredicate(keywords)));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + "Alice Bob", expectedFindCustomerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
            expectedFindCustomerCommand);
    }

}
