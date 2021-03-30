//package seedu.booking.logic.parser;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.booking.logic.commands.CommandTestUtil.*;
//import static seedu.booking.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_HALL;
//import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.booking.testutil.TypicalVenues.HALL;
//import static seedu.booking.testutil.TypicalBookings.BOOKING_FIELD;
//import org.junit.jupiter.api.Test;
//
//import seedu.booking.logic.commands.AddBookingCommand;
//import seedu.booking.logic.commands.AddVenueCommand;
//import seedu.booking.model.booking.Booking;
//import seedu.booking.model.venue.Venue;
//import seedu.booking.testutil.BookingBuilder;
//import seedu.booking.testutil.VenueBuilder;
//
//
//
//class AddBookingCommandParserTest {
//    private AddBookingCommandParser parser = new AddBookingCommandParser();
//
//    @Test
//    public void parse_allFieldsPresent_success() {
//        Booking expectedBooking = new BookingBuilder(BOOKING_FIELD).build();
//
//        // whitespace only preamble
//        assertParseSuccess(parser,
//                PREAMBLE_WHITESPACE + VENUE_NAME_DESC_HALL
//                        + VENUE_CAPACITY_DESC_HALL + VENUE_DESCRIPTION_DESC_HALL,
//                new AddBookingCommand(expectedBooking));
//
//        // multiple names - last name accepted
//        assertParseSuccess(parser, VENUE_NAME_DESC_FIELD + VENUE_NAME_DESC_HALL
//                        + VENUE_CAPACITY_DESC_HALL + VENUE_DESCRIPTION_DESC_HALL,
//                new AddBookingCommand(expectedBooking));
//    }
//
//
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VENUE_CAPACITY_DESC_HALL,
//                expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VALID_VENUE_CAPACITY_HALL
//                        + VALID_VENUE_DESCRIPTION_HALL,
//                expectedMessage);
//    }
//
//    @Test
//    public void parse_optionalFieldMissing_success() {
//        Venue expectedVenueWithoutCapacity = new VenueBuilder(HALL)
//                .withCapacity(VenueBuilder.DEFAULT_CAPACITY).build();
//        Venue expectedVenueWithoutDescription = new VenueBuilder(HALL)
//                .withDescription(VenueBuilder.DEFAULT_DESCRIPTION).build();
//
//        // missing capacity prefix
//        assertParseSuccess(parser, VENUE_NAME_DESC_HALL
//                        + VENUE_DESCRIPTION_DESC_HALL,
//                new CreateVenueCommand(expectedVenueWithoutCapacity));
//
//        // missing description prefix
//        assertParseSuccess(parser, VENUE_NAME_DESC_HALL
//                        + VENUE_CAPACITY_DESC_HALL,
//                new CreateVenueCommand(expectedVenueWithoutDescription));
//
//    }
//
//
//    // need to add MESSAGE_CONSTRAINTS
//    @Test
//    public void parse_invalidValue_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateVenueCommand.MESSAGE_USAGE);
//
//        // invalid capacity
//        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + INVALID_VENUE_CAPACITY_DESC, expectedMessage);
//        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + INVALID_VENUE_CAPACITY_DESC2, expectedMessage);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VENUE_NAME_DESC_HALL + VENUE_CAPACITY_DESC_HALL,
//                expectedMessage);
//    }
//}