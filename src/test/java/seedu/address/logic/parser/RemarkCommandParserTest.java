package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_JANE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_JANE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {

    private static final Remark DEFAULT_REMARK = new Remark(VALID_REMARK_AMY);
    private static final Remark EMPTY_REMARK = new Remark(VALID_REMARK_BOB);
    private static final Remark NON_DEFAULT_REMARK = new Remark(VALID_REMARK_JANE);

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_validArgs_returnsRemarkCommand() {
        // default remark
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + REMARK_DESC_AMY,
               new RemarkCommand(INDEX_FIRST_PERSON, DEFAULT_REMARK));
        // empty remark
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + REMARK_DESC_BOB,
                new RemarkCommand(INDEX_FIRST_PERSON, EMPTY_REMARK));
        // non-default remark
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + REMARK_DESC_JANE,
                new RemarkCommand(INDEX_FIRST_PERSON, NON_DEFAULT_REMARK));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "k", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCompulsoryField_throwsParseException() {
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
    }
}
