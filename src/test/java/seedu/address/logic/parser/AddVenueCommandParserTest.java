package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.address.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_HALL;
import static seedu.address.logic.commands.CommandTestUtil.VENUE_NAME_DESC_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VENUE_NAME_DESC_HALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalVenues.HALL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddVenueCommand;
import seedu.address.model.venue.Venue;
import seedu.address.testutil.VenueBuilder;

public class AddVenueCommandParserTest {
    private AddVenueCommandParser parser = new AddVenueCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Venue expectedVenue = new VenueBuilder(HALL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VENUE_NAME_DESC_HALL + VENUE_CAPACITY_DESC_HALL,
                new AddVenueCommand(expectedVenue));

        // multiple names - last name accepted
        assertParseSuccess(parser, VENUE_NAME_DESC_FIELD + VENUE_NAME_DESC_HALL
                + VENUE_CAPACITY_DESC_HALL,
                new AddVenueCommand(expectedVenue));
    }

    /*
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

     */

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVenueCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VENUE_CAPACITY_DESC_HALL,
                expectedMessage);

        // missing capacity prefix
        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + VALID_VENUE_CAPACITY_HALL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VALID_VENUE_CAPACITY_HALL,
                expectedMessage);
    }

    /*
    @Test
    public void parse_invalidValue_failure() {
        // invalid capacity
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

     */
}
