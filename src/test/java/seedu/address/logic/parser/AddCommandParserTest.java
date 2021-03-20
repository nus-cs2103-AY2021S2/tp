package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIPDAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIPTIME;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TRIPDAY_DESC_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.TRIPTIME_DESC_EVENING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_EVENING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPassengers.AMY;
import static seedu.address.testutil.TypicalPassengers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PassengerBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Passenger expectedPassenger = new PassengerBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        //todo edit PRICE_DESC_BOB here if needed

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPassenger));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPassenger));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPassenger));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPassenger));

        // multiple tags - all accepted
        Passenger expectedPassengerMultipleTags = new PassengerBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY
                + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPassengerMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Passenger expectedPassenger = new PassengerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + TRIPDAY_DESC_FRIDAY
                        + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB,
                new AddCommand(expectedPassenger));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //todo edit PRICE_DESC_BOB here if needed

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + ADDRESS_DESC_BOB
                        + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ADDRESS_BOB
                        + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB, expectedMessage);

        // missing tripDay prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_TRIPDAY_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB, expectedMessage);

        // missing tripTime prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TRIPDAY_DESC_FRIDAY + VALID_TRIPTIME_EVENING + PRICE_DESC_BOB, expectedMessage);

        // missing price prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TRIPDAY_DESC_FRIDAY + VALID_TRIPTIME_EVENING + VALID_PRICE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_ADDRESS_BOB
                        + VALID_TRIPDAY_FRIDAY + VALID_TRIPTIME_EVENING + VALID_PRICE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        //todo edit PRICE_DESC_BOB here if needed

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY
                + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY
                + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + TRIPDAY_DESC_FRIDAY
                + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tripDay
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TRIPDAY
                + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TripDay.MESSAGE_CONSTRAINTS);

        // invalid tripTime
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY
                + INVALID_TRIPTIME + PRICE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TripTime.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY
                + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (Name)
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                        + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (Address)
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                        + INVALID_TRIPDAY + TRIPTIME_DESC_EVENING + PRICE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (TripDay)
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_TRIPDAY + INVALID_TRIPTIME + PRICE_DESC_BOB,
                TripDay.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
