package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.commands.CommandTestUtil.BARCODE_DESC_TEMP;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_BARCODE_DESC1;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_BARCODE_DESC2;
import static seedu.smartlib.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_BARCODE;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.smartlib.logic.commands.ReturnCommand;
import seedu.smartlib.model.book.Barcode;

public class ReturnCommandParserTest {

    private ReturnCommandParser parser = new ReturnCommandParser();


    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE);

        // missing barcode prefix
        assertParseFailure(parser, VALID_BARCODE , expectedMessage);

    }


    @Test
    public void parse_invalidValue_failure() {
        // EP: invalid barcode which is less than 10 digits
        assertParseFailure(parser, INVALID_BARCODE_DESC1, Barcode.MESSAGE_CONSTRAINTS);

        // EP: invalid barcode which is more than 10 digits
        assertParseFailure(parser, INVALID_BARCODE_DESC2, Barcode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + BARCODE_DESC_TEMP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
    }
}
