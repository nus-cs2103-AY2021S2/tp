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
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.TAG_DESC_COOL;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_DATA_PAIR;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_GET;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_COOL;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_THIRD_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.logic.commands.EditCommand.EditEndpointDescriptor;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.testutil.EditEndpointDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_METHOD_GET, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + METHOD_DESC_GET, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + METHOD_DESC_GET, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_METHOD_DESC, Method.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_DATA_DESC, Data.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid data followed by valid header
        assertParseFailure(parser, "1" + INVALID_DATA_DESC + HEADER_DESC_DEFAULT, Data.MESSAGE_CONSTRAINTS);

        // valid header followed by invalid header. The test case for invalid header followed by valid header
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + HEADER_DESC_DEFAULT + INVALID_HEADER_DESC, Header.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code
        // Endpoint} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_COOL + TAG_DESC_CAT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_COOL + TAG_EMPTY + TAG_DESC_CAT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_COOL + TAG_DESC_CAT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_METHOD_DESC + INVALID_ADDRESS_DESC, Method.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENDPOINT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_CAT
                + ADDRESS_DESC_RANDOM
                + METHOD_DESC_GET
                + DATA_DESC_DEFAULT
                + HEADER_DESC_DEFAULT
                + TAG_DESC_COOL;

        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withName(VALID_METHOD_GET)
                .withAddress(VALID_ADDRESS_RANDOM)
                .withData(VALID_DATA_PAIR)
                .withHeaders(VALID_HEADER_PAIR)
                .withTags(VALID_TAG_CAT, VALID_TAG_COOL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ENDPOINT;
        String userInput = targetIndex.getOneBased() + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT;

        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withData(VALID_DATA_PAIR)
                .withHeaders(VALID_HEADER_PAIR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ENDPOINT;
        String userInput = targetIndex.getOneBased() + METHOD_DESC_GET;
        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withName(VALID_METHOD_GET).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_RANDOM;
        descriptor = new EditEndpointDescriptorBuilder().withAddress(VALID_ADDRESS_RANDOM).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // data
        userInput = targetIndex.getOneBased() + DATA_DESC_DEFAULT;
        descriptor = new EditEndpointDescriptorBuilder().withData(VALID_DATA_PAIR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // header
        userInput = targetIndex.getOneBased() + HEADER_DESC_DEFAULT;
        descriptor = new EditEndpointDescriptorBuilder().withHeaders(VALID_HEADER_PAIR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_COOL;
        descriptor = new EditEndpointDescriptorBuilder().withTags(VALID_TAG_COOL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ENDPOINT;
        String userInput = targetIndex.getOneBased()
                + ADDRESS_DESC_RANDOM + DATA_DESC_NEW + HEADER_DESC_NEW + TAG_DESC_COOL
                + ADDRESS_DESC_FACT + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT + TAG_DESC_CAT;

        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder()
                .withAddress(VALID_ADDRESS_FACT)
                .withData(VALID_DATA_PAIR)
                .withHeaders(VALID_HEADER_PAIR_NEW, VALID_HEADER_PAIR)
                .withTags(VALID_TAG_COOL, VALID_TAG_CAT)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ENDPOINT;
        String userInput = targetIndex.getOneBased() + INVALID_DATA_DESC + DATA_DESC_DEFAULT;
        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withData(VALID_DATA_PAIR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + ADDRESS_DESC_FACT
                + INVALID_DATA_DESC
                + DATA_DESC_DEFAULT
                + HEADER_DESC_DEFAULT;

        descriptor = new EditEndpointDescriptorBuilder()
                .withAddress(VALID_ADDRESS_FACT)
                .withData(VALID_DATA_PAIR)
                .withHeaders(VALID_HEADER_PAIR)
                .build();

        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ENDPOINT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
