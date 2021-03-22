package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EQUATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GENERAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_KEYWORDS;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.flashcard.FlashcardFilterPredicate;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_oneField_success() {
        // one word
        FlashcardFilterPredicate expectedPredicate = new FlashcardFilterPredicate(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList(VALID_TAG_EQUATION));
        assertParseSuccess(parser, TAG_DESC_EQUATION, new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_EQUATION,
                new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_EQUATION
                + PREAMBLE_WHITESPACE, new FilterCommand(expectedPredicate));

        // multiple word
        expectedPredicate = new FlashcardFilterPredicate(new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList(VALID_TAG_EQUATION, VALID_TAG_GENERAL));
        assertParseSuccess(parser, TAG_DESC_EQUATION + " " + VALID_TAG_GENERAL,
                new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_EQUATION + " " + VALID_TAG_GENERAL,
                new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_EQUATION
                + PREAMBLE_WHITESPACE + VALID_TAG_GENERAL, new FilterCommand(expectedPredicate));
    }

    @Test
    public void parse_multipleFields_success() {
        // two fields
        FlashcardFilterPredicate expectedPredicate =
                new FlashcardFilterPredicate(Arrays.asList(VALID_QUESTION_EINSTEIN.split("\\s+")),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList(VALID_TAG_EQUATION));
        assertParseSuccess(parser, QUESTION_DESC_EINSTEIN + TAG_DESC_EQUATION,
                new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + QUESTION_DESC_EINSTEIN + PREAMBLE_WHITESPACE
                + TAG_DESC_EQUATION, new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + QUESTION_DESC_EINSTEIN + PREAMBLE_WHITESPACE
                + TAG_DESC_EQUATION + PREAMBLE_WHITESPACE, new FilterCommand(expectedPredicate));

        // all fields
        expectedPredicate = new FlashcardFilterPredicate(Arrays.asList(VALID_QUESTION_EINSTEIN.split("\\s+")),
                Arrays.asList(VALID_CATEGORY_EINSTEIN), Arrays.asList(VALID_PRIORITY_EINSTEIN),
                Arrays.asList(VALID_TAG_EQUATION));
        assertParseSuccess(parser, QUESTION_DESC_EINSTEIN + TAG_DESC_EQUATION
                + CATEGORY_DESC_EINSTEIN + PRIORITY_DESC_EINSTEIN, new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_EINSTEIN
                + PREAMBLE_WHITESPACE + PREAMBLE_WHITESPACE
                + TAG_DESC_EQUATION + PREAMBLE_WHITESPACE + CATEGORY_DESC_EINSTEIN
                + PREAMBLE_WHITESPACE + PREAMBLE_WHITESPACE + PRIORITY_DESC_EINSTEIN,
                new FilterCommand(expectedPredicate));
        assertParseSuccess(parser, QUESTION_DESC_EINSTEIN + PREAMBLE_WHITESPACE
                + TAG_DESC_EQUATION + PREAMBLE_WHITESPACE + PREAMBLE_WHITESPACE
                + PREAMBLE_WHITESPACE + PREAMBLE_WHITESPACE
                + CATEGORY_DESC_EINSTEIN + PREAMBLE_WHITESPACE
                + PRIORITY_DESC_EINSTEIN, new FilterCommand(expectedPredicate));
    }

    @Test
    public void parse_allFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
        assertParseFailure(parser, "testing", expectedMessage);
        assertParseFailure(parser, "testing again", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);
        // empty fields
        assertParseFailure(parser, PREFIX_QUESTION.getPrefix(), expectedMessage);
        assertParseFailure(parser, PREFIX_TAG.getPrefix(), expectedMessage);
        assertParseFailure(parser, PREFIX_CATEGORY.getPrefix(), expectedMessage);
        assertParseFailure(parser, PREFIX_PRIORITY.getPrefix(), expectedMessage);
        assertParseFailure(parser, PREFIX_QUESTION.getPrefix() + " " + PREFIX_TAG.getPrefix(),
                expectedMessage);
        assertParseFailure(parser, QUESTION_DESC_EINSTEIN + " " + PREFIX_TAG.getPrefix(),
                MESSAGE_INVALID_KEYWORDS);
    }
}
