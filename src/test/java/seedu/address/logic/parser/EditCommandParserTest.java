package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.NEGATIVE_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.ZERO_INDEX_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // valid index
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING);

        // empty name argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + EMPTY_NAME_DESC);

        // empty email argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + EMPTY_EMAIL_DESC);

        // empty address argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + EMPTY_ADDRESS_DESC);

        // empty phone argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + EMPTY_PHONE_DESC);

        // empty tag argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + EMPTY_TAG_DESC);

        // empty last name argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + EMPTY_NAME_DESC);

        // empty last email argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + NAME_DESC_AMY
                + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + EMPTY_EMAIL_DESC);

        // empty last address argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NAME_DESC_AMY + TAG_DESC_FRIEND + EMPTY_ADDRESS_DESC);

        // empty last phone argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + EMPTY_PHONE_DESC);

        // empty last tag argument
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + NAME_DESC_AMY + EMPTY_TAG_DESC);

        // allow multiple names
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_AMY + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND);

        // allow multiple phones
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND);

        // allow multiple emails
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND);

        // allow multiple addresses
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND);

        // allow multiple tags
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND);

        // multiple names - last name accepted - empty name argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_NAME_DESC
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + NAME_DESC_AMY);

        // multiple emails - last email accepted - empty email argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_EMAIL_DESC
                + NAME_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + EMAIL_DESC_AMY);

        // multiple phones - last phone accepted - empty phone argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + NAME_DESC_AMY + EMPTY_PHONE_DESC
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY);

        // multiple addresses - last address accepted - empty address argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_ADDRESS_DESC
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND + ADDRESS_DESC_AMY);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        // negative index
        assertValidCommandToAliasFailure(parser, NEGATIVE_INDEX_STRING);

        // zero index
        assertValidCommandToAliasFailure(parser, ZERO_INDEX_STRING);

        // invalid arguments being parsed
        assertValidCommandToAliasFailure(parser, INVALID_INDEX_STRING);

        // invalid index
        assertValidCommandToAliasFailure(parser, INVALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND);

        // empty name argument in middle of user input
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_NAME_DESC
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND);

        // empty email argument in middle of user input
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_EMAIL_DESC
                + NAME_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND);

        // empty address argument in middle of user input
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_ADDRESS_DESC
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND);

        // empty phone argument in middle of user input
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + NAME_DESC_AMY + EMPTY_PHONE_DESC
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND);

        // empty tag argument at start of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + EMPTY_TAG_DESC + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY);

        // empty tag argument in middle of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_TAG_DESC
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY);

        // multiple tags - empty tag argument at start of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + EMPTY_TAG_DESC + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND);

        // multiple tags - empty tag argument in the middle of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + PHONE_DESC_AMY + EMPTY_TAG_DESC
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND);

        // invalid name
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + INVALID_NAME_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND);

        // invalid phone
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + NAME_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND);

        // invalid email
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND);

        // invalid address
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND);

        // invalid tag
        assertValidCommandToAliasFailure(parser, VALID_INDEX_STRING + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + TAG_DESC_FRIEND);
    }
}
