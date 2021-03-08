package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_FACT;
/*
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_METHOD_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_GET;
*/
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_POST;
/*
import static seedu.us.among.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.us.among.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_COOL;
 */
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
/*
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_COOL;
 */
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
/*
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalEndpoints.GET1;
import static seedu.us.among.testutil.TypicalEndpoints.POST;
 */

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.AddCommand;
/*
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.testutil.EndpointBuilder;
 */

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    /* to-do
    @Test
    public void parse_allFieldsPresent_success() {
        Endpoint expectedEndpoint = new EndpointBuilder(POST).withTags(VALID_TAG_COOL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_CAT,
                new AddCommand(expectedEndpoint));

        // multiple names - last name accepted
        assertParseSuccess(parser, METHOD_DESC_GET + METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_CAT,
                new AddCommand(expectedEndpoint));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_CAT,
                new AddCommand(expectedEndpoint));

        // multiple emails - last email accepted
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_CAT,
                new AddCommand(expectedEndpoint));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_RANDOM + ADDRESS_DESC_FACT + TAG_DESC_CAT,
                new AddCommand(expectedEndpoint));

        // multiple tags - all accepted
        Endpoint expectedEndpointMultipleTags = new EndpointBuilder(POST).withTags(VALID_TAG_COOL, VALID_TAG_CAT)
                .build();
        assertParseSuccess(parser, METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_COOL + TAG_DESC_CAT,
                new AddCommand(expectedEndpointMultipleTags));
    }
    */

    /* to-do
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Endpoint expectedEndpoint = new EndpointBuilder(GET1).withTags().build();
        assertParseSuccess(parser, METHOD_DESC_GET + ADDRESS_DESC_RANDOM, new AddCommand(expectedEndpoint));
    }
    */

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

    /*
    to-do
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

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_METHOD_DESC + INVALID_ADDRESS_DESC, Method.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + METHOD_DESC_POST + ADDRESS_DESC_FACT + TAG_DESC_COOL + TAG_DESC_CAT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
   */
}
