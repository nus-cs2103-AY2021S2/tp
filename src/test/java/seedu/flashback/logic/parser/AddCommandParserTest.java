package seedu.flashback.logic.parser;

import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.commands.CommandTestUtil.ANSWER_DESC_EINSTEIN;
import static seedu.flashback.logic.commands.CommandTestUtil.ANSWER_DESC_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.CATEGORY_DESC_EINSTEIN;
import static seedu.flashback.logic.commands.CommandTestUtil.CATEGORY_DESC_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.flashback.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.flashback.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.flashback.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.flashback.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.flashback.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.flashback.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.flashback.logic.commands.CommandTestUtil.PRIORITY_DESC_EINSTEIN;
import static seedu.flashback.logic.commands.CommandTestUtil.PRIORITY_DESC_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.QUESTION_DESC_EINSTEIN;
import static seedu.flashback.logic.commands.CommandTestUtil.QUESTION_DESC_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.TAG_DESC_EQUATION;
import static seedu.flashback.logic.commands.CommandTestUtil.TAG_DESC_GENERAL;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_ANSWER_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_CATEGORY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_QUESTION_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_TAG_GENERAL;
import static seedu.flashback.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashback.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashback.testutil.TypicalFlashcards.AT;
import static seedu.flashback.testutil.TypicalFlashcards.EINS;

import org.junit.jupiter.api.Test;

import seedu.flashback.logic.commands.AddCommand;
import seedu.flashback.model.flashcard.Answer;
import seedu.flashback.model.flashcard.Category;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.Priority;
import seedu.flashback.model.flashcard.Question;
import seedu.flashback.model.tag.Tag;
import seedu.flashback.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedFlashcard = new FlashcardBuilder(AT).withTags(VALID_TAG_GENERAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_OCTOPUS
                + ANSWER_DESC_OCTOPUS + CATEGORY_DESC_OCTOPUS
                + PRIORITY_DESC_OCTOPUS + TAG_DESC_GENERAL, new AddCommand(expectedFlashcard));

        // multiple names - last name accepted
        assertParseSuccess(parser, QUESTION_DESC_EINSTEIN + QUESTION_DESC_OCTOPUS
                + ANSWER_DESC_OCTOPUS + CATEGORY_DESC_OCTOPUS
                + PRIORITY_DESC_OCTOPUS + TAG_DESC_GENERAL, new AddCommand(expectedFlashcard));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_EINSTEIN
                + ANSWER_DESC_OCTOPUS + CATEGORY_DESC_OCTOPUS
                + PRIORITY_DESC_OCTOPUS + TAG_DESC_GENERAL, new AddCommand(expectedFlashcard));

        // multiple emails - last email accepted
        assertParseSuccess(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_EINSTEIN + CATEGORY_DESC_OCTOPUS
                + PRIORITY_DESC_OCTOPUS + TAG_DESC_GENERAL, new AddCommand(expectedFlashcard));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_EINSTEIN
                + PRIORITY_DESC_OCTOPUS + TAG_DESC_GENERAL, new AddCommand(expectedFlashcard));

        // multiple tags - all accepted
        Flashcard expectedFlashcardMultipleTags = new FlashcardBuilder(AT)
                .withTags(VALID_TAG_GENERAL, VALID_TAG_EQUATION).build();
        assertParseSuccess(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_OCTOPUS
                + TAG_DESC_EQUATION + TAG_DESC_GENERAL, new AddCommand(expectedFlashcardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Flashcard expectedFlashcard = new FlashcardBuilder(EINS).withTags().build();
        assertParseSuccess(parser, QUESTION_DESC_EINSTEIN + ANSWER_DESC_EINSTEIN
                        + CATEGORY_DESC_EINSTEIN + PRIORITY_DESC_EINSTEIN,
                new AddCommand(expectedFlashcard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_QUESTION_OCTOPUS + ANSWER_DESC_OCTOPUS
                        + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_OCTOPUS, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + VALID_ANSWER_OCTOPUS
                        + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_OCTOPUS, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                        + VALID_CATEGORY_OCTOPUS + PRIORITY_DESC_OCTOPUS, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                        + CATEGORY_DESC_OCTOPUS + VALID_PRIORITY_OCTOPUS, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_OCTOPUS + VALID_ANSWER_OCTOPUS
                        + VALID_CATEGORY_OCTOPUS + VALID_PRIORITY_OCTOPUS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_QUESTION_DESC + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_OCTOPUS
                + TAG_DESC_EQUATION + TAG_DESC_GENERAL, Question.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + INVALID_ANSWER_DESC
                + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_OCTOPUS
                + TAG_DESC_EQUATION + TAG_DESC_GENERAL, Answer.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                + INVALID_CATEGORY_DESC + PRIORITY_DESC_OCTOPUS
                + TAG_DESC_EQUATION + TAG_DESC_GENERAL, Category.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_OCTOPUS + INVALID_PRIORITY_DESC
                + TAG_DESC_EQUATION + TAG_DESC_GENERAL, Priority.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, QUESTION_DESC_OCTOPUS + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_OCTOPUS + PRIORITY_DESC_OCTOPUS
                + INVALID_TAG_DESC + VALID_TAG_GENERAL, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_QUESTION_DESC + ANSWER_DESC_OCTOPUS
                + CATEGORY_DESC_OCTOPUS + INVALID_PRIORITY_DESC,
                Question.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_OCTOPUS
                + ANSWER_DESC_OCTOPUS + CATEGORY_DESC_OCTOPUS
                + PRIORITY_DESC_OCTOPUS + TAG_DESC_EQUATION + TAG_DESC_GENERAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
