package seedu.address.logic.parser.gradeparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GRADED_ITEM_DESC_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.GRADED_ITEM_DESC_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADED_ITEM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_ITEM_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_ITEM_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_NAME_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_NAME_SCIENCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradecommands.EditGradeCommand;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.testutil.EditGradeDescriptorBuilder;

public class EditGradeCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGradeCommand.MESSAGE_USAGE);

    private EditGradeCommandParser parser = new EditGradeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_SUBJECT_NAME_MATHS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditGradeCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + SUBJECT_DESC_MATHS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + SUBJECT_DESC_MATHS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_SUBJECT_DESC,
                Grade.SUBJECT_CONSTRAINTS); // invalid subject name
        assertParseFailure(parser, "1" + INVALID_GRADED_ITEM_DESC,
                GradedItem.MESSAGE_CONSTRAINTS); // invalid graded item
        assertParseFailure(parser, "1" + INVALID_GRADE_DESC,
                GradeEnum.MESSAGE_CONSTRAINTS); // invalid grade

        // invalid graded item followed by valid grade
        assertParseFailure(parser, "1" + INVALID_GRADED_ITEM_DESC
                + GRADE_DESC_MATHS, GradedItem.MESSAGE_CONSTRAINTS);

        // valid graded item followed by invalid grade. The test case for invalid graded item followed by valid grade
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + GRADED_ITEM_DESC_MATHS
                + INVALID_GRADE_DESC, GradeEnum.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_SUBJECT_DESC + INVALID_GRADED_ITEM_DESC
                        + VALID_GRADE_MATHS,
                Grade.SUBJECT_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + SUBJECT_DESC_MATHS
                + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS;

        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_MATHS)
                .withGradedItem(VALID_GRADED_ITEM_MATHS).withGrade(VALID_GRADE_MATHS).build();
        EditGradeCommand expectedCommand = new EditGradeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS;

        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withGradedItem(VALID_GRADED_ITEM_MATHS)
                .withGrade(VALID_GRADE_MATHS).build();
        EditGradeCommand expectedCommand = new EditGradeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // subject name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + SUBJECT_DESC_MATHS;
        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_MATHS).build();
        EditGradeCommand expectedCommand = new EditGradeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // graded item
        userInput = targetIndex.getOneBased() + GRADED_ITEM_DESC_MATHS;
        descriptor = new EditGradeDescriptorBuilder()
                .withGradedItem(VALID_GRADED_ITEM_MATHS).build();
        expectedCommand = new EditGradeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // grade
        userInput = targetIndex.getOneBased() + GRADE_DESC_MATHS;
        descriptor = new EditGradeDescriptorBuilder().withGrade(VALID_GRADE_MATHS).build();
        expectedCommand = new EditGradeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + SUBJECT_DESC_MATHS + SUBJECT_DESC_SCIENCE
                + GRADED_ITEM_DESC_MATHS + GRADED_ITEM_DESC_SCIENCE
                + GRADE_DESC_MATHS + GRADE_DESC_SCIENCE;

        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_SCIENCE)
                .withGradedItem(VALID_GRADED_ITEM_SCIENCE).withGrade(VALID_GRADE_SCIENCE)
                .build();
        EditGradeCommand expectedCommand = new EditGradeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_SUBJECT_DESC + SUBJECT_DESC_MATHS;
        EditGradeCommand.EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_MATHS).build();
        EditGradeCommand expectedCommand = new EditGradeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_SUBJECT_DESC + SUBJECT_DESC_MATHS
                + GRADED_ITEM_DESC_MATHS + GRADE_DESC_MATHS;
        descriptor = new EditGradeDescriptorBuilder().withSubject(VALID_SUBJECT_NAME_MATHS)
                .withGradedItem(VALID_GRADED_ITEM_MATHS).withGrade(VALID_GRADE_MATHS).build();
        expectedCommand = new EditGradeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
