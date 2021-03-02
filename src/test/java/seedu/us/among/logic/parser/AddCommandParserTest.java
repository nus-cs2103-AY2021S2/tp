package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.us.among.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.us.among.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalEndpoints.AMY;
import static seedu.us.among.testutil.TypicalEndpoints.BOB;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.testutil.EndpointBuilder;

public class AddCommandParserTest {
        private AddCommandParser parser = new AddCommandParser();

        @Test
        public void parse_allFieldsPresent_success() {
                Endpoint expectedEndpoint = new EndpointBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

                // whitespace only preamble
                assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                                new AddCommand(expectedEndpoint));

                // multiple names - last name accepted
                assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                                new AddCommand(expectedEndpoint));

                // multiple phones - last phone accepted
                assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                                new AddCommand(expectedEndpoint));

                // multiple emails - last email accepted
                assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                                new AddCommand(expectedEndpoint));

                // multiple addresses - last address accepted
                assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                                new AddCommand(expectedEndpoint));

                // multiple tags - all accepted
                Endpoint expectedEndpointMultipleTags = new EndpointBuilder(BOB)
                                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
                assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                new AddCommand(expectedEndpointMultipleTags));
        }

        @Test
        public void parse_optionalFieldsMissing_success() {
                // zero tags
                Endpoint expectedEndpoint = new EndpointBuilder(AMY).withTags().build();
                assertParseSuccess(parser, NAME_DESC_AMY + ADDRESS_DESC_AMY, new AddCommand(expectedEndpoint));
        }

        @Test
        public void parse_compulsoryFieldMissing_failure() {
                String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

                // missing name prefix
                assertParseFailure(parser, VALID_NAME_BOB + ADDRESS_DESC_BOB, expectedMessage);

                // missing address prefix
                assertParseFailure(parser, NAME_DESC_BOB + VALID_ADDRESS_BOB, expectedMessage);

                // all prefixes missing
                assertParseFailure(parser, VALID_NAME_BOB + VALID_ADDRESS_BOB, expectedMessage);
        }

        @Test
        public void parse_invalidValue_failure() {
                // invalid name
                assertParseFailure(parser, INVALID_NAME_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                Method.MESSAGE_CONSTRAINTS);

                // invalid address
                assertParseFailure(parser, NAME_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                Address.MESSAGE_CONSTRAINTS);

                // invalid tag
                assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                                Tag.MESSAGE_CONSTRAINTS);

                // two invalid values, only first invalid value reported
                assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ADDRESS_DESC, Method.MESSAGE_CONSTRAINTS);

                // non-empty preamble
                assertParseFailure(parser,
                                PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
                                                + TAG_DESC_FRIEND,
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
}
