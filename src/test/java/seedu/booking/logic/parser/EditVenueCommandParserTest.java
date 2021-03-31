package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.INVALID_VENUE_CAPACITY_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.INVALID_VENUE_NAME_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.ORIGINAL_VENUE_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_VENUE1;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_VENUE2;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_VENUE1;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_VENUE2;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.EditVenueCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.VenueName;
import seedu.booking.testutil.EditVenueDescriptorBuilder;

public class EditVenueCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditVenueCommand.MESSAGE_USAGE);

    private EditVenueCommandParser parser = new EditVenueCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_VENUE_NAME_HALL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, ORIGINAL_VENUE_DESC_HALL, EditVenueCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VENUE_NAME_DESC_FIELD, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VENUE_NAME_DESC_FIELD, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() throws ParseException {
        assertParseFailure(parser, ORIGINAL_VENUE_DESC_HALL + INVALID_VENUE_NAME_DESC,
                VenueName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, ORIGINAL_VENUE_DESC_HALL + INVALID_VENUE_CAPACITY_DESC,
                Capacity.MESSAGE_CONSTRAINTS); // invalid capacity

        // invalid capacity followed by valid venue name
        assertParseFailure(parser, ORIGINAL_VENUE_DESC_HALL + INVALID_VENUE_CAPACITY_DESC
                + VENUE_NAME_DESC_VENUE1, Capacity.MESSAGE_CONSTRAINTS);

        // valid capacity followed by invalid capacity. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, ORIGINAL_VENUE_DESC_HALL + VALID_VENUE_CAPACITY_DESC
                + INVALID_VENUE_CAPACITY_DESC, Capacity.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, ORIGINAL_VENUE_DESC_HALL + INVALID_VENUE_CAPACITY_DESC
                         + INVALID_VENUE_NAME_DESC + VENUE_NAME_DESC_VENUE1,
                Capacity.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String targetVenueName = VALID_VENUE_NAME_HALL;
        String userInput = ORIGINAL_VENUE_DESC_HALL + VENUE_NAME_DESC_VENUE1 + VENUE_CAPACITY_DESC_HALL;

        EditVenueCommand.EditVenueDescriptor descriptor =
                new EditVenueDescriptorBuilder().withVenueName(VALID_VENUE_NAME_VENUE1)
                .withCapacity(VALID_VENUE_CAPACITY_HALL).build();
        EditVenueCommand expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String targetVenueName = VALID_VENUE_NAME_HALL;
        String userInput = ORIGINAL_VENUE_DESC_HALL + VENUE_NAME_DESC_VENUE1;

        EditVenueCommand.EditVenueDescriptor descriptor =
                new EditVenueDescriptorBuilder().withVenueName(VALID_VENUE_NAME_VENUE1).build();
        EditVenueCommand expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String targetVenueName = VALID_VENUE_NAME_HALL;
        String userInput = ORIGINAL_VENUE_DESC_HALL + VENUE_NAME_DESC_VENUE1;

        EditVenueCommand.EditVenueDescriptor descriptor =
                new EditVenueDescriptorBuilder().withVenueName(VALID_VENUE_NAME_VENUE1).build();
        EditVenueCommand expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // capacity
        targetVenueName = VALID_VENUE_NAME_HALL;
        userInput = ORIGINAL_VENUE_DESC_HALL + VENUE_CAPACITY_DESC_HALL;

        descriptor = new EditVenueDescriptorBuilder().withCapacity(VALID_VENUE_CAPACITY_HALL).build();
        expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String targetVenueName = VALID_VENUE_NAME_HALL;
        String userInput = ORIGINAL_VENUE_DESC_HALL + VENUE_NAME_DESC_VENUE1 + VENUE_CAPACITY_DESC_HALL
                + VENUE_CAPACITY_DESC_FIELD + VENUE_NAME_DESC_VENUE2;

        EditVenueCommand.EditVenueDescriptor descriptor =
                new EditVenueDescriptorBuilder().withVenueName(VALID_VENUE_NAME_VENUE2)
                        .withCapacity(VALID_VENUE_CAPACITY_FIELD).build();
        EditVenueCommand expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String targetVenueName = VALID_VENUE_NAME_HALL;
        String userInput = ORIGINAL_VENUE_DESC_HALL + INVALID_VENUE_CAPACITY_DESC + VENUE_CAPACITY_DESC_HALL;

        EditVenueCommand.EditVenueDescriptor descriptor =
                new EditVenueDescriptorBuilder().withCapacity(VALID_VENUE_CAPACITY_HALL).build();
        EditVenueCommand expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        targetVenueName = VALID_VENUE_NAME_HALL;
        userInput = ORIGINAL_VENUE_DESC_HALL + INVALID_VENUE_CAPACITY_DESC
                + VENUE_CAPACITY_DESC_FIELD + VENUE_NAME_DESC_VENUE2;

        descriptor = new EditVenueDescriptorBuilder().withVenueName(VALID_VENUE_NAME_VENUE2)
                        .withCapacity(VALID_VENUE_CAPACITY_FIELD).build();
        expectedCommand = new EditVenueCommand(new VenueName(targetVenueName), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

}
