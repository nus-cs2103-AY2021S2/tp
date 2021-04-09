package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_CONTACT_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class EditContactCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);

    private EditContactCommandParser parser = new EditContactCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no name specified: edit n/Amy Bee
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified: edit Amy Bee
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_NOT_EDITED);

        // no name and no field specified: edit
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser,
                VALID_NAME_ALICE + INVALID_NAME_DESC, ContactName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser,
                VALID_NAME_ALICE + INVALID_PHONE_DESC, ContactPhone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser,
                VALID_NAME_ALICE + INVALID_EMAIL_DESC, ContactEmail.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser,
                VALID_NAME_ALICE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser,
                VALID_NAME_ALICE + INVALID_PHONE_DESC + EMAIL_DESC_AMY, ContactPhone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser,
                VALID_NAME_ALICE + PHONE_DESC_BOB + INVALID_PHONE_DESC, ContactPhone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Contact} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
                VALID_NAME_ALICE + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                VALID_NAME_ALICE + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                VALID_NAME_ALICE + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_NAME_ALICE + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                ContactName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_NAME_ALICE + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactName(VALID_NAME_AMY)
                .withContactPhone(VALID_PHONE_BOB).withContactEmail(VALID_EMAIL_AMY)
                .withContactTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditContactCommand expectedCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = VALID_NAME_ALICE + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactPhone(VALID_PHONE_BOB)
                .withContactEmail(VALID_EMAIL_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        ContactName targetName = FIRST_CONTACT_NAME_ALICE;
        String userInput = targetName + NAME_DESC_AMY;
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactName(VALID_NAME_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetName + PHONE_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withContactPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditContactCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetName + EMAIL_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withContactEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditContactCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetName + TAG_DESC_FRIEND;
        descriptor = new EditContactDescriptorBuilder().withContactTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditContactCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = VALID_NAME_ALICE + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactPhone(VALID_PHONE_BOB)
                .withContactEmail(VALID_EMAIL_BOB).withContactTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditContactCommand expectedCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = VALID_NAME_ALICE + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactPhone(VALID_PHONE_BOB).build();
        EditContactCommand expectedCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = VALID_NAME_ALICE + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditContactDescriptorBuilder()
                .withContactPhone(VALID_PHONE_BOB).withContactEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = VALID_NAME_ALICE + TAG_EMPTY;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withContactTags().build();
        EditContactCommand expectedCommand = new EditContactCommand(FIRST_CONTACT_NAME_ALICE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
