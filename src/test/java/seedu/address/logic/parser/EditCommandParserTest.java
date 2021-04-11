package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.EVENTNAME_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.EVENTNAME_DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.EVENTPRIORITY_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.EVENTPRIORITY_DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.EVENTSTATUS_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.EVENTSTATUS_DESC_CS2107;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENTSTATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2107;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_CS2107;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.identifier.Identifier;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStatus;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // String specified at index
        assertParseFailure(parser, VALID_NAME_CS2030,
                ParserUtil.MESSAGE_INVALID_IDENTIFIER);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "",
                ParserUtil.MESSAGE_EMPTY_IDENTIFIER + EditCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EVENTNAME_DESC_CS2030,
                ParserUtil.MESSAGE_NEGATIVE_OR_ZERO_IDENTIFIER);

        // zero index
        assertParseFailure(parser, "0" + EVENTNAME_DESC_CS2030,
                ParserUtil.MESSAGE_NEGATIVE_OR_ZERO_IDENTIFIER);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
                ParserUtil.MESSAGE_ADDITIONAL_ARTEFACTS + EditCommand.MESSAGE_USAGE);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string",
                ParserUtil.MESSAGE_ADDITIONAL_ARTEFACTS + EditCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, EventName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_DESCRIPTION_CS2030,
                EventName.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + INVALID_EVENTSTATUS_DESC, EventStatus.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EVENTSTATUS_DESC + VALID_DESCRIPTION_CS2030,
                EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Identifier targetIndex = IDENTIFIER_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2107 + EVENTNAME_DESC_CS2107
                + EVENTSTATUS_DESC_CS2107 + EVENTPRIORITY_DESC_CS2107;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2107)
                .withDescription(VALID_DESCRIPTION_CS2107)
                .withEventStatus(VALID_STATUS_CS2107)
                .withEventPriority(VALID_PRIORITY_CS2107)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Identifier targetIndex = IDENTIFIER_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2030 + EVENTNAME_DESC_CS2030;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2030)
                .withName(VALID_NAME_CS2030).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // eventname
        Identifier targetIndex = IDENTIFIER_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + EVENTNAME_DESC_CS2030;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2030).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2030;
        descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2030).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EventStatus
        userInput = targetIndex.getOneBased() + EVENTSTATUS_DESC_CS2030;
        descriptor = new EditEventDescriptorBuilder().withEventStatus(VALID_STATUS_CS2030).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EventPriority
        userInput = targetIndex.getOneBased() + EVENTPRIORITY_DESC_CS2030;
        descriptor = new EditEventDescriptorBuilder().withEventPriority(VALID_PRIORITY_CS2030).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Identifier targetIndex = IDENTIFIER_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2030 + EVENTNAME_DESC_CS2030
                + EVENTSTATUS_DESC_CS2030 + DESCRIPTION_DESC_CS2030 + EVENTNAME_DESC_CS2030;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2030)
                .withName(VALID_NAME_CS2030).withEventStatus(VALID_STATUS_CS2030).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Identifier targetIdentifier = IDENTIFIER_FIRST_EVENT;
        String userInput = targetIdentifier.getOneBased() + INVALID_NAME_DESC + EVENTNAME_DESC_CS2107;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2107).build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIdentifier.getOneBased() + DESCRIPTION_DESC_CS2107 + INVALID_NAME_DESC + EVENTNAME_DESC_CS2107
                + EVENTSTATUS_DESC_CS2107;
        descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2107)
                .withName(VALID_NAME_CS2107).withEventStatus(VALID_STATUS_CS2107).build();
        expectedCommand = new EditCommand(targetIdentifier, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
