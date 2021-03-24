package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_POST;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.RunCommand;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;

public class RunCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RunCommand.MESSAGE_USAGE);

    private RunCommandParser parser = new RunCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RunCommand.MESSAGE_USAGE);

        //to-do yong liang with the new implementation of Address, the commented out
        // strings would technically be a url

        // missing method prefix
        // assertParseFailure(parser, VALID_METHOD_POST + ADDRESS_DESC_FACT, expectedMessage);

        // missing address
        assertParseFailure(parser, METHOD_DESC_POST + PREFIX_ADDRESS, expectedMessage);
    }
    @Test
    public void parse_missingParts_failure() {
        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "-x get -u ur\\l", Address.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "get https://google.com", Address.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " -x get -u http://the-one-api.dev/v2/book";
        String userQuickInput = "http://the-one-api.dev/v2/book";
        String userQuickInput2 = "the-one-api.dev/v2/book";
        String userQuickInput3 = "http://localhost:3000";
        String userQuickInput4 = "localhost:3000";

        RunCommand expectedCommand = new RunCommand(
                new Endpoint(new Method("get"), new Address("http://the-one-api.dev/v2/book"),
                        new HashSet<>(), new HashSet<>()));

        RunCommand expectedCommand2 = new RunCommand(
            new Endpoint(new Method("get"), new Address("http://localhost:3000"),
                    new HashSet<>(), new HashSet<>()));
        assertParseSuccess(parser, userInput, expectedCommand);
        assertParseSuccess(parser, userQuickInput, expectedCommand);
        assertParseSuccess(parser, userQuickInput2, expectedCommand);
        assertParseSuccess(parser, userQuickInput3, expectedCommand2);
        assertParseSuccess(parser, userQuickInput4, expectedCommand2);

    }

}
