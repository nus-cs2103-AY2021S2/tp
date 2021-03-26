package seedu.iscam.logic.parser;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_PLAN_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.PLAN_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.PLAN_DESC_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.iscam.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.iscam.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.iscam.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.iscam.testutil.TypicalClients.AMY;
import static seedu.iscam.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.testutil.ClientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + PLAN_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + PLAN_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + PLAN_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + PLAN_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedClient));

        // multiple addresses - last location accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_AMY
                + LOCATION_DESC_BOB + PLAN_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedClient));

        // multiple tags - all accepted
        Client expectedClientMultipleTags = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB
                + PLAN_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedClientMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Client expectedClient = new ClientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + LOCATION_DESC_AMY + PLAN_DESC_AMY,
                new AddCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing iscam prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_LOCATION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_LOCATION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB
                + PLAN_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + LOCATION_DESC_BOB
                + PLAN_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + LOCATION_DESC_BOB
                + PLAN_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_LOCATION_DESC
                + PLAN_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Location.MESSAGE_CONSTRAINTS);

        // invalid plan
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB
                + INVALID_PLAN_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, InsurancePlan.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB
                + PLAN_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_LOCATION_DESC
                + PLAN_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + PLAN_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
