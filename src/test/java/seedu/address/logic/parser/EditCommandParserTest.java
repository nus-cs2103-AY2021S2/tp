package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.EVENTNAME_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.EVENTNAME_DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.EVENTSTATUS_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.EVENTSTATUS_DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENTSTATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_CS2107;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.IDENTIFIER_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.IDENTIFIER_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStatus;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_CS2030, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, EventName.MESSAGE_CONSTRAINTS); // invalid name
        //assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        //assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        //assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        //assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        //assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_DESCRIPTION_CS2030,
                EventName.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        //assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + INVALID_EVENTSTATUS_DESC, EventStatus.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = IDENTIFIER_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2107 + EVENTNAME_DESC_CS2107
                + EVENTSTATUS_DESC_CS2107;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2107)
                .withDescription(VALID_DESCRIPTION_CS2107)
                .withEventStatus(VALID_STATUS_CS2107).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = IDENTIFIER_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2030 + EVENTNAME_DESC_CS2030;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2030)
                .withName(VALID_NAME_CS2030).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // eventname
        Index targetIndex = IDENTIFIER_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + EVENTNAME_DESC_CS2030;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2030).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2030;
        descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2030).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // eventStatus
        userInput = targetIndex.getOneBased() + EVENTSTATUS_DESC_CS2030;
        descriptor = new EditEventDescriptorBuilder().withEventStatus(VALID_STATUS_CS2030).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = IDENTIFIER_FIRST_PERSON;
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
        Index targetIndex = IDENTIFIER_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + EVENTNAME_DESC_CS2107;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2107).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CS2107 + INVALID_NAME_DESC + EVENTNAME_DESC_CS2107
                + EVENTSTATUS_DESC_CS2107;
        descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_CS2107)
                .withName(VALID_NAME_CS2107).withEventStatus(VALID_STATUS_CS2107).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // Commented out for v1.2
    //    @Test
    //    public void parse_resetTags_success() {
    //        Index targetIndex = INDEX_THIRD_PERSON;
    //        String userInput = targetIndex.getOneBased() + TAG_EMPTY;
    //
    //        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
    //        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
    //
    //        assertParseSuccess(parser, userInput, expectedCommand);
    //    }
}
