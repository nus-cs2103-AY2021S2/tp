package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));

        // multiple companies - last company accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_AMY
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple job titles - last job title accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_AMY + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_AMY + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();

        // zero tags
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + COMPANY_DESC_AMY
                        + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + REMARK_DESC_AMY,
                new AddCommand(expectedPerson));

        expectedPerson = new PersonBuilder(AMY).withRemark("").build();
        // no remark
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + COMPANY_DESC_AMY
                        + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB, expectedMessage);

        // missing company prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_COMPANY_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB, expectedMessage);

        // missing job title prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + VALID_JOB_TITLE_BOB + ADDRESS_DESC_BOB + REMARK_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + VALID_ADDRESS_BOB + REMARK_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + VALID_ADDRESS_BOB + REMARK_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid company
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_COMPANY_DESC
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                Company.MESSAGE_CONSTRAINTS);

        // invalid job title
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + INVALID_JOB_TITLE_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                JobTitle.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + REMARK_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + COMPANY_DESC_BOB
                + JOB_TITLE_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // empty name argument
        assertValidCommandToAliasSuccess(parser, EMPTY_NAME_DESC);

        // empty email argument
        assertValidCommandToAliasSuccess(parser, EMPTY_EMAIL_DESC);

        // empty company argument
        assertValidCommandToAliasSuccess(parser, EMPTY_COMPANY_DESC);

        // empty job title argument
        assertValidCommandToAliasSuccess(parser, EMPTY_JOB_TITLE_DESC);

        // empty address argument
        assertValidCommandToAliasSuccess(parser, EMPTY_ADDRESS_DESC);

        // empty phone argument
        assertValidCommandToAliasSuccess(parser, EMPTY_PHONE_DESC);

        // empty tag argument
        assertValidCommandToAliasSuccess(parser, EMPTY_TAG_DESC);

        // empty remark argument
        assertValidCommandToAliasSuccess(parser, EMPTY_REMARK_DESC);

        // empty last name argument
        assertValidCommandToAliasSuccess(parser, PHONE_DESC_AMY + EMAIL_DESC_AMY + COMPANY_DESC_AMY
                + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY + EMPTY_NAME_DESC);

        // empty last email argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + COMPANY_DESC_AMY
                + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY + EMPTY_EMAIL_DESC);

        // empty last company argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY + EMPTY_COMPANY_DESC);

        // empty last job title argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY + EMPTY_JOB_TITLE_DESC);

        // empty last address argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY + EMPTY_ADDRESS_DESC);

        // empty last phone argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + COMPANY_DESC_AMY
                + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY + EMPTY_PHONE_DESC);

        // empty last tag argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + REMARK_DESC_AMY + EMPTY_TAG_DESC);

        // empty last remark argument
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + EMPTY_REMARK_DESC);

        // whitespace only preamble
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple names
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple phones
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple emails
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple companies
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple job titles
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_AMY + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple addresses
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple tags
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + REMARK_DESC_BOB);

        // allow multiple remarks
        assertValidCommandToAliasSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + JOB_TITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_AMY
                + REMARK_DESC_BOB);

        // multiple names - last name accepted - empty name argument at start of user input discarded
        assertValidCommandToAliasSuccess(parser, EMPTY_NAME_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY
                + NAME_DESC_AMY);

        // multiple phones - last phone accepted - empty phone argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + EMPTY_PHONE_DESC + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY
                + PHONE_DESC_AMY);

        // multiple emails - last email accepted - empty email argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMPTY_EMAIL_DESC
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY
                + EMAIL_DESC_AMY);

        // multiple companies - last address accepted - empty address argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY
                + EMPTY_COMPANY_DESC);

        // multiple job titles - last address accepted - empty address argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY
                + EMPTY_JOB_TITLE_DESC);

        // multiple addresses - last address accepted - empty address argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + EMPTY_ADDRESS_DESC + TAG_DESC_FRIEND + REMARK_DESC_AMY
                + ADDRESS_DESC_AMY);

        // multiple remarks - last address accepted - empty address argument in middle of user input discarded
        assertValidCommandToAliasSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + EMPTY_REMARK_DESC
                + REMARK_DESC_AMY);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        // empty name argument at start of user input
        assertValidCommandToAliasFailure(parser, EMPTY_NAME_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY);

        // empty phone argument in middle of user input
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + EMPTY_PHONE_DESC + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY);

        // empty email argument in middle of user input
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMPTY_EMAIL_DESC
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY);

        // empty company argument in middle of user input
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + EMPTY_COMPANY_DESC + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY);

        // empty job title argument in middle of user input
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + EMPTY_JOB_TITLE_DESC + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REMARK_DESC_AMY);

        // empty address argument in middle of user input
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + EMPTY_ADDRESS_DESC + TAG_DESC_FRIEND + REMARK_DESC_AMY);

        // empty tag argument at start of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, EMPTY_TAG_DESC + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + REMARK_DESC_AMY);

        // empty tag argument in middle of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMPTY_TAG_DESC
                + EMAIL_DESC_AMY + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + REMARK_DESC_AMY);

        // multiple tags - empty tag argument at start of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, EMPTY_TAG_DESC + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND
                + REMARK_DESC_AMY);

        // multiple tags - empty tag argument in the middle of user input (tags not allowed to be empty at all)
        assertValidCommandToAliasFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMPTY_TAG_DESC
                + EMAIL_DESC_AMY + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND
                + REMARK_DESC_AMY);

        // invalid name
        assertValidCommandToAliasFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_AMY);

        // invalid phone
        assertValidCommandToAliasFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_AMY);

        // invalid email
        assertValidCommandToAliasFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_AMY);

        // invalid company
        assertValidCommandToAliasFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_COMPANY_DESC + JOB_TITLE_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_AMY);

        // invalid job title
        assertValidCommandToAliasFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_AMY + INVALID_JOB_TITLE_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + REMARK_DESC_AMY);

        // invalid address
        assertValidCommandToAliasFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + REMARK_DESC_AMY);

        // invalid tag
        assertValidCommandToAliasFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_BOB + INVALID_TAG_DESC + REMARK_DESC_AMY);

        // non-empty preamble
        assertValidCommandToAliasFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
                + REMARK_DESC_AMY);
    }

}
