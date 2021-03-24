package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.DATA_DESC_DEFAULT;
import static seedu.us.among.logic.commands.CommandTestUtil.DATA_DESC_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.HEADER_DESC_DEFAULT;
import static seedu.us.among.logic.commands.CommandTestUtil.HEADER_DESC_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_DATA_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_HEADER_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_METHOD_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_GET;
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.us.among.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_COOL;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_COOL;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalEndpoints.POST;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.testutil.EndpointBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();


    @Test
    public void parse_allFieldsPresent_success() {
        Endpoint expectedEndpoint = new EndpointBuilder(POST).withTags(VALID_TAG_COOL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + METHOD_DESC_POST
                        + ADDRESS_DESC_FACT + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT + TAG_DESC_COOL,
                new AddCommand(expectedEndpoint));

        // multiple methods - last name accepted
        assertParseSuccess(parser, METHOD_DESC_GET + METHOD_DESC_POST
                        + ADDRESS_DESC_FACT + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT + TAG_DESC_COOL,
                new AddCommand(expectedEndpoint));

        // multiple data - last data accepted
        assertParseSuccess(parser, METHOD_DESC_POST
                        + ADDRESS_DESC_FACT + DATA_DESC_NEW + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT + TAG_DESC_COOL,
                new AddCommand(expectedEndpoint));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_RANDOM + ADDRESS_DESC_FACT
                        + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT + TAG_DESC_COOL,
                new AddCommand(expectedEndpoint));

        // multiple header - all accepted
        Endpoint expectedEndpointMultipleHeaders = new EndpointBuilder(POST)
                .withHeaders(VALID_HEADER_PAIR_NEW, VALID_HEADER_PAIR).withTags(VALID_TAG_COOL).build();

        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT
                        + DATA_DESC_DEFAULT + HEADER_DESC_NEW + HEADER_DESC_DEFAULT + TAG_DESC_COOL,
                new AddCommand(expectedEndpointMultipleHeaders));

        // multiple tags - all accepted
        Endpoint expectedEndpointMultipleTags = new EndpointBuilder(POST).withTags(VALID_TAG_COOL, VALID_TAG_CAT)
                .build();
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT
                        + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT + TAG_DESC_CAT + TAG_DESC_COOL,
                new AddCommand(expectedEndpointMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Endpoint expectedEndpoint = new EndpointBuilder(POST).withTags().build();
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT
                + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT, new AddCommand(expectedEndpoint));

        // zero headers
        Endpoint expectedEndpointTwo = new EndpointBuilder(POST).withHeaders().withTags(VALID_TAG_COOL).build();
        System.out.println(expectedEndpointTwo);
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT
                + DATA_DESC_DEFAULT + TAG_DESC_COOL, new AddCommand(expectedEndpointTwo));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_METHOD_POST + ADDRESS_DESC_FACT, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, METHOD_DESC_POST + VALID_ADDRESS_FACT, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_METHOD_POST + VALID_ADDRESS_FACT, expectedMessage);
    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_METHOD_DESC + ADDRESS_DESC_FACT + TAG_DESC_COOL + TAG_DESC_CAT,
                Method.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, METHOD_DESC_POST + INVALID_ADDRESS_DESC + TAG_DESC_COOL + TAG_DESC_CAT,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT + INVALID_TAG_DESC + VALID_TAG_COOL,
                Tag.MESSAGE_CONSTRAINTS);

        //invalid header
        assertParseFailure(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT + INVALID_HEADER_DESC + TAG_DESC_CAT,
                Header.MESSAGE_CONSTRAINTS);

        //invalid data
        assertParseFailure(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT + INVALID_DATA_DESC + TAG_DESC_CAT,
                Data.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_METHOD_DESC + INVALID_ADDRESS_DESC, Method.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_COOL + TAG_DESC_CAT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

}
