package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_SCHOOL;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_WORK;
import static seedu.address.logic.commands.CommandTestUtil.ENDDATE_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.ENDDATE_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.ENDTIME_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.ENDTIME_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENDDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENDTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STARTDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STARTTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.STARTDATE_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.STARTDATE_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_SCHOOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FUN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Time;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_EVENT_NAME_EVENTTWO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_EVENT_NAME_EVENTTWO, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_EVENT_NAME_EVENTTWO, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertParseFailure(parser, "1" + INVALID_STARTDATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, "1" + INVALID_STARTTIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid end date
        assertParseFailure(parser, "1" + INVALID_ENDDATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, "1" + INVALID_ENDTIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid start date followed by valid end date.
        assertParseFailure(parser, "1" + INVALID_STARTDATE_DESC + ENDDATE_DESC_EVENTONE,
                Date.MESSAGE_CONSTRAINTS);

        // valid start date followed by invalid start date.
        assertParseFailure(parser, "1" + STARTDATE_DESC_EVENTONE + INVALID_STARTDATE_DESC,
                Date.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Event} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FINAL + TAG_DESC_FUN + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FINAL + TAG_EMPTY + TAG_DESC_FUN, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FINAL + TAG_DESC_FUN, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_STARTDATE_DESC
                + VALID_EVENT_STARTTIME_EVENTTWO, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EVENTTWO
                + STARTDATE_DESC_EVENTTWO + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + CATEGORY_DESC_WORK + TAG_DESC_FINAL + TAG_DESC_FUN;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).withStartTime(VALID_EVENT_STARTTIME_EVENTTWO)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTTWO).withEndTime(VALID_EVENT_ENDTIME_EVENTTWO)
                .withCategories(VALID_EVENT_CATEGORY_SCHOOL, VALID_EVENT_CATEGORY_WORK)
                .withTags(VALID_EVENT_TAG_FINAL, VALID_EVENT_TAG_FUN).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + STARTDATE_DESC_EVENTONE + ENDDATE_DESC_EVENTONE;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EVENTTWO;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + STARTDATE_DESC_EVENTTWO;
        descriptor = new EditEventDescriptorBuilder().withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + STARTTIME_DESC_EVENTTWO;
        descriptor = new EditEventDescriptorBuilder().withStartTime(VALID_EVENT_STARTTIME_EVENTTWO).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end date
        userInput = targetIndex.getOneBased() + ENDDATE_DESC_EVENTTWO;
        descriptor = new EditEventDescriptorBuilder().withEndDate(VALID_EVENT_ENDDATE_EVENTTWO).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + ENDTIME_DESC_EVENTTWO;
        descriptor = new EditEventDescriptorBuilder().withEndTime(VALID_EVENT_ENDTIME_EVENTTWO).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // categories
        userInput = targetIndex.getOneBased() + CATEGORY_DESC_SCHOOL;
        descriptor = new EditEventDescriptorBuilder().withCategories(VALID_EVENT_CATEGORY_SCHOOL).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FUN;
        descriptor = new EditEventDescriptorBuilder().withTags(VALID_EVENT_TAG_FUN).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EVENTTWO
                + STARTDATE_DESC_EVENTTWO + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_WORK + TAG_DESC_FINAL + NAME_DESC_EVENTONE
                + STARTDATE_DESC_EVENTONE + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_SCHOOL + CATEGORY_DESC_WORK + TAG_DESC_FINAL + TAG_DESC_FUN;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
                .withCategories(VALID_EVENT_CATEGORY_SCHOOL, VALID_EVENT_CATEGORY_WORK)
                .withTags(VALID_EVENT_TAG_FINAL, VALID_EVENT_TAG_FUN).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_STARTDATE_DESC + STARTDATE_DESC_EVENTONE;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_EVENTONE + INVALID_ENDDATE_DESC + STARTDATE_DESC_EVENTONE
                + ENDDATE_DESC_EVENTONE;
        descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withEndDate(VALID_EVENT_ENDDATE_EVENTONE).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
