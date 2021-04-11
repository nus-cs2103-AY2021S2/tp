package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.commands.CommandTestUtil.BARCODE_DESC_TEMP;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_BARCODE_DESC1;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_BARCODE_DESC2;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_BARCODE;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.BorrowCommand;
import seedu.smartlib.model.book.Barcode;

public class BorrowCommandParserTest {
    private BorrowCommandParser parser = new BorrowCommandParser();


    @Test
    public void parse_compulsoryFieldMissing_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE);

        // missing barcode prefix
        assertParseFailure(parser, VALID_BARCODE + NAME_DESC_AMY, expectedMessage);

        // missing reader prefix

        assertParseFailure(parser, BARCODE_DESC_TEMP + VALID_NAME_AMY , expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // EP: invalid barcode which is less than 10 digits
        assertParseFailure(parser, INVALID_BARCODE_DESC1 + NAME_DESC_AMY, Barcode.MESSAGE_CONSTRAINTS);

        // EP: invalid barcode which is more than 10 digits
        assertParseFailure(parser, INVALID_BARCODE_DESC2 + NAME_DESC_AMY, Barcode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + BARCODE_DESC_TEMP + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE));

        // EP: invalid readername
        assertParseFailure(parser, BARCODE_DESC_TEMP + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
    }
}
