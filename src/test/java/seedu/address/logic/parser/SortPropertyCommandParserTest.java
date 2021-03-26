package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_PROPERTY_SORTING_KEY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SORTING_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_SORTING_KEY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORTING_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_PROPERTY_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortPropertyCommand;
import seedu.address.logic.commands.sort.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.model.sort.descriptor.PropertySortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;
import seedu.address.testutil.SortPropertyDescriptorBuilder;

public class SortPropertyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPropertyCommand.MESSAGE_USAGE);

    private SortPropertyCommandParser parser = new SortPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sorting order specified
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD + DEADLINE_PROPERTY_SORTING_KEY,
                MESSAGE_INVALID_FORMAT);

        // no sorting key specified
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD + DESC_SORTING_ORDER,
                MESSAGE_INVALID_FORMAT);

        // no sorting order and no sorting key specified
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD + " or/desc"
                + DEADLINE_PROPERTY_SORTING_KEY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD + INVALID_SORTING_ORDER
                + DEADLINE_PROPERTY_SORTING_KEY, SortingOrder.MESSAGE_CONSTRAINTS); // invalid sorting order
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD + DESC_SORTING_ORDER
                        + INVALID_PROPERTY_SORTING_KEY,
                PropertySortingKey.MESSAGE_CONSTRAINTS); // invalid property sorting key

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, SortPropertyCommand.COMMAND_WORD + INVALID_SORTING_ORDER
                + INVALID_PROPERTY_SORTING_KEY, SortingOrder.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = SortPropertyCommand.COMMAND_WORD + DESC_SORTING_ORDER + DEADLINE_PROPERTY_SORTING_KEY;
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE).build();
        SortPropertyCommand expectedCommand = new SortPropertyCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SortPropertyCommand.COMMAND_WORD + DESC_SORTING_ORDER + DEADLINE_PROPERTY_SORTING_KEY
                + DESC_SORTING_ORDER;
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE).build();
        SortPropertyCommand expectedCommand = new SortPropertyCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = SortPropertyCommand.COMMAND_WORD + INVALID_SORTING_ORDER + DESC_SORTING_ORDER
                + DEADLINE_PROPERTY_SORTING_KEY;
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE).build();
        SortPropertyCommand expectedCommand = new SortPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
