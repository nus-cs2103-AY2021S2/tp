package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EmailCommand;

public class EmailCommandParserTest {

    private final EmailCommandParser parser = new EmailCommandParser();

    @Test
    public void parse_emailShown_success() {
        EmailCommand emailCommand = new EmailCommand();
        assertParseSuccess(parser, EmailCommandParser.SPECIAL_INDEX, emailCommand);
    }

    @Test
    public void parse_validIndexes_success() {
        EmailCommand emailCommand = new EmailCommand(VALID_INDEXES);
        String inputIndexes = VALID_INDEXES.stream()
                .map(Index::getOneBased).map(String::valueOf)
                .collect(Collectors.joining(" "));
        assertParseSuccess(parser, inputIndexes, emailCommand);
    }

    @Test
    public void parse_invalidIndexes_failure() {
        assertParseFailure(parser, INVALID_INDEX_STRING, EmailCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", EmailCommand.MESSAGE_USAGE);
    }
}
