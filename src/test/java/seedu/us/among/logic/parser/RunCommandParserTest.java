package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
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

        // missing method prefix
        assertParseFailure(parser, VALID_METHOD_POST + ADDRESS_DESC_FACT, expectedMessage);

        // missing address
        assertParseFailure(parser, METHOD_DESC_POST + PREFIX_ADDRESS, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_METHOD_POST + VALID_ADDRESS_FACT, expectedMessage);
    }
    @Test
    public void parse_missingParts_failure() {
        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "website", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-xget -uurl", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "get https://google.com", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "google.com", MESSAGE_INVALID_FORMAT); // requires https or http prefix
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " -x get -u https://the-one-api.dev/v2/book";
        String userQuickInput = "https://the-one-api.dev/v2/book";
        RunCommand expectedCommand = new RunCommand(
                new Endpoint(new Method("get"), new Address("https://the-one-api.dev/v2/book"),
                        new HashSet<>(), new HashSet<>()));
        assertParseSuccess(parser, userInput, expectedCommand);
        assertParseSuccess(parser, userQuickInput, expectedCommand);
    }

}
