package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import seedu.address.model.person.Answer;
import static seedu.address.testutil.TypicalFlashcards.AMY;
import static seedu.address.testutil.TypicalFlashcards.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Question;
import seedu.address.model.person.Flashcard;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

//    @Test
//    public void parse_allFieldsPresent_success() {
//        Flashcard expectedFlashcard = new FlashcardBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
//
//        // whitespace only preamble
//        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_B
//                + ANSWER_DESC_B + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));
//
//
//        // multiple questions - last question accepted
//        assertParseSuccess(parser, QUESTION_DESC_A + QUESTION_DESC_B
//                + ANSWER_DESC_B + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));
//
//        // multiple answers - last answer accepted
//        assertParseSuccess(parser,  QUESTION_DESC_B + ANSWER_DESC_A
//                + ANSWER_DESC_B + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));
//
//        // multiple tags - all accepted
//        Flashcard expectedFlashcardMultipleTags = new FlashcardBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
//                .build();
//        assertParseSuccess(parser, QUESTION_DESC_B + ANSWER_DESC_B
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedFlashcardMultipleTags));
//    }

//    @Test
//    public void parse_optionalFieldsMissing_success() {
//        // zero tags
//        Flashcard expectedFlashcard = new FlashcardBuilder(AMY).withTags().build();
//        assertParseSuccess(parser, QUESTION_DESC_A + ANSWER_DESC_A,
//                new AddCommand(expectedFlashcard));
//    }

//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
//
//        // missing question prefix
//        assertParseFailure(parser, VALID_QUESTION_B + ANSWER_DESC_B,
//                expectedMessage);
//
//        // missing answer prefix
//        assertParseFailure(parser, QUESTION_DESC_B + VALID_ANSWER_B,
//                expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_QUESTION_B + VALID_ANSWER_B,
//                expectedMessage);
//    }

//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid question
//        assertParseFailure(parser, INVALID_QUESTION_DESC + ANSWER_DESC_B
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Question.MESSAGE_CONSTRAINTS);
//
//        // invalid answer
//        assertParseFailure(parser, QUESTION_DESC_B + INVALID_ANSWER_DESC
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Answer.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser, QUESTION_DESC_B + ANSWER_DESC_B
//                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
//
//        // two invalid values, only first invalid value reported
//        assertParseFailure(parser, INVALID_QUESTION_DESC + INVALID_ANSWER_DESC,
//                Question.MESSAGE_CONSTRAINTS);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_B
//                + ANSWER_DESC_B + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//    }
}
