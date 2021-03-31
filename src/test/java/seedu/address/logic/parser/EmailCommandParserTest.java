package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.logic.parser.EmailCommandParser.SPECIAL_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.NEGATIVE_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.ZERO_INDEX_STRING;

import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EmailCommand;

public class EmailCommandParserTest {

    private final EmailCommandParser parser = new EmailCommandParser();

    @Test
    public void parse_emailShown_success() {
        EmailCommand emailCommand = new EmailCommand();
        assertParseSuccess(parser, SPECIAL_INDEX, emailCommand);
    }

    @Test
    public void parse_validIndex_success() {
        EmailCommand emailCommand = new EmailCommand(Collections.singletonList(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, VALID_INDEX_STRING, emailCommand);
    }

    @Test
    public void parse_validIndexWithWhitepace_success() {
        EmailCommand emailCommand = new EmailCommand(Collections.singletonList(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING + PREAMBLE_WHITESPACE,
                emailCommand);
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
    public void parse_validIndexesWithWhitespace_success() {
        EmailCommand emailCommand = new EmailCommand(VALID_INDEXES);
        String inputIndexes = VALID_INDEXES.stream()
                .map(Index::getOneBased).map(String::valueOf)
                .collect(Collectors.joining(PREAMBLE_WHITESPACE));
        assertParseSuccess(parser, inputIndexes, emailCommand);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, INVALID_INDEX_STRING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validEmailCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, "");

        // valid index
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING);

        // valid index with whitespace
        assertValidCommandToAliasSuccess(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING + PREAMBLE_WHITESPACE);

        String inputIndexes = VALID_INDEXES.stream()
                .map(Index::getOneBased).map(String::valueOf)
                .collect(Collectors.joining(" "));
        // valid indexes
        assertValidCommandToAliasSuccess(parser, inputIndexes);

        // shown
        assertValidCommandToAliasSuccess(parser, SPECIAL_INDEX);
    }

    @Test
    public void parse_invalidEmailCommandAlias_returnsFalse() {
        assertValidCommandToAliasFailure(parser, NEGATIVE_INDEX_STRING);
        assertValidCommandToAliasFailure(parser, ZERO_INDEX_STRING);
        assertValidCommandToAliasFailure(parser, INVALID_INDEX_STRING);
    }
}
