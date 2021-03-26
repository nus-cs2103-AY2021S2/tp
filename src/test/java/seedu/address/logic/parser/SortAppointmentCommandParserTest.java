package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_APPOINTMENT_SORTING_KEY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SORTING_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_SORTING_KEY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORTING_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_APPOINTMENT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortAppointmentCommand;
import seedu.address.logic.commands.sort.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.model.sort.descriptor.AppointmentSortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;
import seedu.address.testutil.SortAppointmentDescriptorBuilder;

public class SortAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortAppointmentCommand.MESSAGE_USAGE);

    private SortAppointmentCommandParser parser = new SortAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sorting order specified
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD + DATETIME_APPOINTMENT_SORTING_KEY,
                MESSAGE_INVALID_FORMAT);

        // no sorting key specified
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD + DESC_SORTING_ORDER,
                MESSAGE_INVALID_FORMAT);

        // no sorting order and no sorting key specified
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD + " or/desc"
                + DATETIME_APPOINTMENT_SORTING_KEY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD + INVALID_SORTING_ORDER
                + DATETIME_APPOINTMENT_SORTING_KEY, SortingOrder.MESSAGE_CONSTRAINTS); // invalid sorting order
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD + DESC_SORTING_ORDER
                + INVALID_APPOINTMENT_SORTING_KEY,
                AppointmentSortingKey.MESSAGE_CONSTRAINTS); // invalid appointment sorting key

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, SortAppointmentCommand.COMMAND_WORD + INVALID_SORTING_ORDER
                        + INVALID_APPOINTMENT_SORTING_KEY, SortingOrder.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = SortAppointmentCommand.COMMAND_WORD + DESC_SORTING_ORDER + DATETIME_APPOINTMENT_SORTING_KEY;
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME).build();
        SortAppointmentCommand expectedCommand = new SortAppointmentCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SortAppointmentCommand.COMMAND_WORD + DESC_SORTING_ORDER + DATETIME_APPOINTMENT_SORTING_KEY
                + DESC_SORTING_ORDER;
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME).build();
        SortAppointmentCommand expectedCommand = new SortAppointmentCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = SortAppointmentCommand.COMMAND_WORD + INVALID_SORTING_ORDER + DESC_SORTING_ORDER
                + DATETIME_APPOINTMENT_SORTING_KEY;
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME).build();
        SortAppointmentCommand expectedCommand = new SortAppointmentCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
