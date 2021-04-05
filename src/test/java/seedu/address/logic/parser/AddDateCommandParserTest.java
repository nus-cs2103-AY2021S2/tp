package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSpecialDates.DATE_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.AddDateCommand;
import seedu.address.model.person.SpecialDate;

public class AddDateCommandParserTest {

    private final AddDateCommandParser parser = new AddDateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 d/10-10-2019 desc/Anniversary",
                new AddDateCommand(Index.fromOneBased(1), DATE_ONE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDateCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, "d/10-10-2019 desc/Anniversary", expectedMessage);

        // missing date
        assertParseFailure(parser, "1 desc/Anniversary", expectedMessage);

        // missing description
        assertParseFailure(parser, "1 d/10-10-2019", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date format
        assertParseFailure(parser, "1 d/10 Oct 2019 desc/Anniversary", DateUtil.MESSAGE_CONSTRAINT);

        // empty description
        assertParseFailure(parser, "1 d/10-10-2019 desc/", SpecialDate.DESCRIPTION_MESSAGE_CONSTRAINTS);
    }
}
