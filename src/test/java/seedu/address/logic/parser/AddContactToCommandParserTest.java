package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX_STANDALONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactToCommand;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddContactToCommandParserTest {
    private AddContactToCommandParser parser = new AddContactToCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Contact expectedContact = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // all field appear once
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddContactToCommand(expectedProjectIndex, expectedContact)
        );

        // multiple names - last name accepted
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddContactToCommand(expectedProjectIndex, expectedContact));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddContactToCommand(expectedProjectIndex, expectedContact));

        // multiple emails - last email accepted
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddContactToCommand(expectedProjectIndex, expectedContact));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddContactToCommand(expectedProjectIndex, expectedContact));

        // multiple tags - all accepted
        Contact expectedContactMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddContactToCommand(expectedProjectIndex, expectedContactMultipleTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactToCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, INDEX_STANDALONE_ONE + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_FRIEND,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, INVALID_INDEX_STANDALONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, MESSAGE_INVALID_INDEX);

        // invalid name
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);
    }
}
