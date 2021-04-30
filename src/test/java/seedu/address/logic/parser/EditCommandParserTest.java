//@@author
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX;
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
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_GOLF;
import static seedu.address.logic.commands.CommandTestUtil.TRIPDAY_DESC_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.TRIPDAY_DESC_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.TRIPTIME_DESC_EVENING;
import static seedu.address.logic.commands.CommandTestUtil.TRIPTIME_DESC_MORNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_EVENING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPassengerDescriptorBuilder;

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
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TRIPDAY, TripDay.MESSAGE_CONSTRAINTS); // invalid tripDay
        assertParseFailure(parser, "1" + INVALID_TRIPTIME, TripTime.MESSAGE_CONSTRAINTS); // invalid tripTime
        assertParseFailure(parser, "1" + INVALID_PRICE, Price.MESSAGE_CONSTRAINTS); // invalid price
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Passenger} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_GOLF + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_GOLF, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_GOLF, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY
                        + VALID_TRIPDAY_FRIDAY + VALID_TRIPTIME_EVENING, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_GOLF + ADDRESS_DESC_AMY
                + NAME_DESC_AMY + TAG_DESC_FRIEND + TRIPDAY_DESC_MONDAY + TRIPTIME_DESC_MORNING + PRICE_DESC_AMY;

        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_AMY)
                .withTripDay(VALID_TRIPDAY_MONDAY).withTripTime(VALID_TRIPTIME_MORNING).withPrice(VALID_PRICE_AMY)
                .withTags(VALID_TAG_HR, VALID_TAG_IT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB;

        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder()
                .withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPassengerDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPassengerDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
        //@@author chewterence
        // tripDay
        userInput = targetIndex.getOneBased() + TRIPDAY_DESC_FRIDAY;
        descriptor = new EditPassengerDescriptorBuilder().withTripDay(VALID_TRIPDAY_FRIDAY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tripTime
        userInput = targetIndex.getOneBased() + TRIPTIME_DESC_EVENING;
        descriptor = new EditPassengerDescriptorBuilder().withTripTime(VALID_TRIPTIME_EVENING).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price
        userInput = targetIndex.getOneBased() + PRICE_DESC_AMY;
        descriptor = new EditPassengerDescriptorBuilder().withPrice(VALID_PRICE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPassengerDescriptorBuilder().withTags(VALID_TAG_IT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    //@@author
    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPTIME_DESC_EVENING + TAG_DESC_GOLF;

        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTripDay(VALID_TRIPDAY_FRIDAY)
                .withTripTime(VALID_TRIPTIME_EVENING).withTags(VALID_TAG_IT, VALID_TAG_HR)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPassengerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
