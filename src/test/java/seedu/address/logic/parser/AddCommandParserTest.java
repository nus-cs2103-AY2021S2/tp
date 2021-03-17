package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_DETAILS_DESC_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_DETAILS_DESC_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.CLEAN_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BOOKING_DETAILS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLEAN_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_RESERVED;
import static seedu.address.logic.commands.CommandTestUtil.UNCLEAN_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_DETAILS_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLEAN_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_RESERVED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalResidences.RESIDENCE1;
import static seedu.address.testutil.TypicalResidences.RESIDENCE2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.residence.Booking;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ResidenceBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Residence expectedResidence = new ResidenceBuilder(RESIDENCE2).withTags(VALID_TAG_RESERVED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE1 + CLEAN_STATUS_DESC
                + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_RESIDENCE1 + NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC
                + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC
                + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // multiple booking details - last booking detail accepted
        assertParseSuccess(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE1 + BOOKING_DETAILS_DESC_RESIDENCE2
                + CLEAN_STATUS_DESC + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // multiple clean tags - last clean tag accepted
        assertParseSuccess(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2
                + CLEAN_STATUS_DESC + UNCLEAN_STATUS_DESC + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // multiple tags - all accepted
        Residence expectedResidenceMultipleTags = new ResidenceBuilder(RESIDENCE2).withTags(VALID_TAG_REPAIR,
                VALID_TAG_RESERVED)
                .build();
        assertParseSuccess(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC
                + TAG_DESC_RESERVED + TAG_DESC_REPAIR, new AddCommand(expectedResidenceMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Residence expectedResidence = new ResidenceBuilder(RESIDENCE1).withTags().build();

        // zero bookings
        assertParseSuccess(parser, NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + CLEAN_STATUS_DESC + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // zero clean status tags
        assertParseSuccess(parser, NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + BOOKING_DETAILS_DESC_RESIDENCE1 + TAG_DESC_RESERVED, new AddCommand(expectedResidence));

        // zero tags
        assertParseSuccess(parser, NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + BOOKING_DETAILS_DESC_RESIDENCE1 + CLEAN_STATUS_DESC, new AddCommand(expectedResidence));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_RESIDENCE2 + VALID_ADDRESS_RESIDENCE2, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_RESIDENCE2 + VALID_ADDRESS_RESIDENCE2
                + VALID_BOOKING_DETAILS_RESIDENCE2 + VALID_CLEAN_TAG, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC, ResidenceName.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_RESIDENCE2 + INVALID_ADDRESS_DESC
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC, ResidenceAddress.MESSAGE_CONSTRAINTS);

        // invalid booking details
        assertParseFailure(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + INVALID_BOOKING_DETAILS_DESC + CLEAN_STATUS_DESC, Booking.MESSAGE_CONSTRAINTS);

        // invalid clean status tag
        assertParseFailure(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + INVALID_CLEAN_TAG_DESC, CleanStatusTag.getMessageConstraints());

        // invalid tag
        assertParseFailure(parser, NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ADDRESS_DESC
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC, ResidenceName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_RESIDENCE2 + ADDRESS_DESC_RESIDENCE2
                + BOOKING_DETAILS_DESC_RESIDENCE2 + CLEAN_STATUS_DESC + TAG_DESC_RESERVED + TAG_DESC_REPAIR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
