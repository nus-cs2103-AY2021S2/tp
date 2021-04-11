package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.EMPTY_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NEGATIVE_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_ZERO_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_THREE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditOwnerCommand;
import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.EditOwnerDescriptorBuilder;

public class EditOwnerCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOwnerCommand.MESSAGE_USAGE);

    private final EditOwnerCommandParser parser = new EditOwnerCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no ID specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, String.valueOf(ID_ONE), EditOwnerCommand.MESSAGE_NOT_EDITED);

        // no ID and no field specified
        assertParseFailure(parser, EMPTY_STRING, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative ID
        assertParseFailure(parser, INVALID_NEGATIVE_ID_STRING + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero ID
        assertParseFailure(parser, INVALID_ZERO_ID_STRING + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, ID_ONE + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, ID_ONE + " i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, ID_ONE + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ID_ONE + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ID_ONE + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, ID_ONE + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ID_ONE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, ID_ONE + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, ID_ONE + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Owner} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, ID_ONE + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_ONE + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_ONE + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                ID_ONE + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        int targetId = ID_TWO;
        String userInput = targetId + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        int targetId = ID_ONE;
        String userInput = targetId + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        int targetId = ID_THREE;
        String userInput = targetId + NAME_DESC_AMY;
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetId + PHONE_DESC_AMY;
        descriptor = new EditOwnerDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetId + EMAIL_DESC_AMY;
        descriptor = new EditOwnerDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetId + ADDRESS_DESC_AMY;
        descriptor = new EditOwnerDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetId + TAG_DESC_FRIEND;
        descriptor = new EditOwnerDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        int targetId = ID_ONE;
        String userInput = targetId + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedTags_acceptsUnique() {
        int targetId = ID_ONE;
        String userInput = targetId + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_DESC_ALL + TAG_DESC_ALL
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder()
                .withTags(VALID_TAG_ALL, VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        int targetId = ID_ONE;
        String userInput = targetId + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetId + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB + PHONE_DESC_BOB;
        descriptor = new EditOwnerDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditOwnerCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        int targetId = ID_THREE;
        String userInput = targetId + TAG_EMPTY;

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withTags().build();
        EditOwnerCommand expectedCommand = new EditOwnerCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
