package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOOKING1;
import static seedu.address.logic.commands.CommandTestUtil.TENANT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_END1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_START1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCALDATE_END1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCALDATE_START1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOKING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOOKING1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;
import seedu.address.testutil.EditBookingDescriptorBuilder;


public class EditBookingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE);

    private EditBookingCommandParser parser = new EditBookingCommandParser();

    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    @Test
    public void parse_missingParts_failure() {
        //no index specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        //index 0 specified => should show non zero unsigned integer
        assertParseFailure(parser, " r/0 b/0 ", ParserUtil.MESSAGE_INVALID_INDEX);

        //no field specified
        assertParseFailure(parser, " r/1 b/1 ", EditBookingCommand.MESSAGE_NOT_EDITED);

        //no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, " r/-5 b/1" + TENANT_NAME_DESC_AMY, ParserUtil.MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, " r/0 b/0" + TENANT_NAME_DESC_AMY, ParserUtil.MESSAGE_INVALID_INDEX);

        // string
        assertParseFailure(parser, " r/one b/one" + TENANT_NAME_DESC_AMY, ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, " r/1 b/1 some random string", ParserUtil.MESSAGE_INVALID_INDEX);

        //invalid prefix being parsed as preamble
        assertParseFailure(parser, " r/1 b/1 z/nameToChange", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " r/1 b/1 " + INVALID_NAME_DESC, TenantName.MESSAGE_CONSTRAINTS);
        // invalid address
        assertParseFailure(parser, " r/1 b/1 " + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid dates
        assertParseFailure(parser, " r/1 b/1 s/2000-2000", ParserUtil.MESSAGE_INVALID_DATE_FORMAT);
    }

    @Test
    public void parse_allFieldsSuccess() {
        Index bookingIndex = INDEX_SECOND_BOOKING;
        Index residenceIndex = INDEX_SECOND_RESIDENCE;
        String userInput = " r/" + residenceIndex.getOneBased() + " b/" + bookingIndex.getOneBased()
                + TENANT_NAME_DESC_AMY + PHONE_DESC_BOOKING1
                + " s/" + VALID_BOOKING_START1 + " e/" + VALID_BOOKING_END1;
        EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withName(VALID_NAME_BOOKING1)
                .withPhone(VALID_PHONE_BOOKING1)
                .withStartDate(VALID_LOCALDATE_START1)
                .withEndDate(VALID_LOCALDATE_END1).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(residenceIndex, bookingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSuccess() {
        Index bookingIndex = INDEX_SECOND_BOOKING;
        Index residenceIndex = INDEX_SECOND_RESIDENCE;
        String userInput = " r/" + residenceIndex.getOneBased() + " b/" + bookingIndex.getOneBased()
                + TENANT_NAME_DESC_AMY + PHONE_DESC_BOOKING1;
        EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withName(VALID_NAME_BOOKING1)
                .withPhone(VALID_PHONE_BOOKING1).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(residenceIndex, bookingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_oneFieldSuccess() {
        //TenantName
        Index bookingIndex = INDEX_SECOND_BOOKING;
        Index residenceIndex = INDEX_SECOND_RESIDENCE;
        String userInput = " r/" + residenceIndex.getOneBased() + " b/" + bookingIndex.getOneBased()
                + TENANT_NAME_DESC_AMY;
        EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withName(VALID_NAME_BOOKING1).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(residenceIndex, bookingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //Phone
        userInput = " r/" + residenceIndex.getOneBased() + " b/" + bookingIndex.getOneBased()
                + PHONE_DESC_BOOKING1;
        descriptor = new EditBookingDescriptorBuilder()
                .withPhone(VALID_PHONE_BOOKING1).build();
        expectedCommand = new EditBookingCommand(residenceIndex, bookingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //Start Date
        userInput = " r/" + residenceIndex.getOneBased() + " b/" + bookingIndex.getOneBased()
                + " s/" + VALID_BOOKING_START1;
        descriptor = new EditBookingDescriptorBuilder()
                .withStartDate(VALID_LOCALDATE_START1).build();
        expectedCommand = new EditBookingCommand(residenceIndex, bookingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //End Date
        userInput = " r/" + residenceIndex.getOneBased() + " b/" + bookingIndex.getOneBased()
                + " e/" + VALID_BOOKING_END1;
        descriptor = new EditBookingDescriptorBuilder()
                .withEndDate(VALID_LOCALDATE_END1).build();
        expectedCommand = new EditBookingCommand(residenceIndex, bookingIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
