package seedu.address.logic.parser.gradeparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GRADED_ITEM_DESC_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.GRADED_ITEM_DESC_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADED_ITEM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_ITEM_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_NAME_MATHS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGrades.MATHS_GRADE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.gradecommands.AddGradeCommand;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.testutil.GradeBuilder;

public class AddGradeCommandParserTest {

    private AddGradeCommandParser parser = new AddGradeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Grade expectedGrade = new GradeBuilder(MATHS_GRADE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SUBJECT_DESC_MATHS
                        + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS,
                new AddGradeCommand(expectedGrade));

        // multiple subjects - last subject accepted
        assertParseSuccess(parser, SUBJECT_DESC_SCIENCE + SUBJECT_DESC_MATHS
                        + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS,
                new AddGradeCommand(expectedGrade));

        // multiple graded items - last graded item accepted
        assertParseSuccess(parser, SUBJECT_DESC_MATHS
                        + GRADED_ITEM_DESC_SCIENCE + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS,
                new AddGradeCommand(expectedGrade));

        // multiple grades - last grade accepted
        assertParseSuccess(parser, SUBJECT_DESC_MATHS + GRADED_ITEM_DESC_MATHS
                        + GRADE_DESC_SCIENCE + GRADE_DESC_MATHS,
                new AddGradeCommand(expectedGrade));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGradeCommand.MESSAGE_USAGE);

        // missing subject prefix
        assertParseFailure(parser, VALID_SUBJECT_NAME_MATHS
                + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS, expectedMessage);

        // missing graded item prefix
        assertParseFailure(parser, SUBJECT_DESC_MATHS
                + VALID_GRADED_ITEM_MATHS + GRADE_DESC_MATHS, expectedMessage);

        // missing grade prefix
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADED_ITEM_DESC_MATHS
                + VALID_GRADE_MATHS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid subject
        assertParseFailure(parser, INVALID_SUBJECT_DESC + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS,
                Grade.SUBJECT_CONSTRAINTS);

        // invalid graded item
        assertParseFailure(parser, SUBJECT_DESC_MATHS + INVALID_GRADED_ITEM_DESC + GRADE_DESC_MATHS,
                GradedItem.MESSAGE_CONSTRAINTS);

        // invalid grade
        assertParseFailure(parser, SUBJECT_DESC_MATHS + GRADED_ITEM_DESC_MATHS + INVALID_GRADE_DESC,
                GradeEnum.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_SUBJECT_DESC + GRADED_ITEM_DESC_MATHS + INVALID_GRADE_DESC,
                Grade.SUBJECT_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SUBJECT_DESC_MATHS
                        + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGradeCommand.MESSAGE_USAGE));
    }
}
