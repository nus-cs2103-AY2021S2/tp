package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_SCHOOL;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_WORK;
import static seedu.address.logic.commands.CommandTestUtil.ENDDATE_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.ENDDATE_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.ENDTIME_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.ENDTIME_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENDDATEPAST_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENDDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENDTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STARTDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STARTTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STARTDATE_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.STARTDATE_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_SCHOOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FUN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.EVENTONE;
import static seedu.address.testutil.TypicalEvents.EVENTTWO;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Event;
import seedu.address.model.event.Time;
import seedu.address.testutil.EventBuilder;


public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(EVENTTWO).withTags(VALID_EVENT_TAG_FUN).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + TAG_DESC_FUN, new AddEventCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_EVENTONE + NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + TAG_DESC_FUN, new AddEventCommand(expectedEvent));

        // multiple start dates - last start date accepted
        assertParseSuccess(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTONE + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + TAG_DESC_FUN, new AddEventCommand(expectedEvent));

        // multiple start times - last start time accepted
        assertParseSuccess(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTONE + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + TAG_DESC_FUN, new AddEventCommand(expectedEvent));

        // multiple end dates - last end date accepted
        assertParseSuccess(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTONE + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + TAG_DESC_FUN, new AddEventCommand(expectedEvent));

        // multiple end times - last end time accepted
        assertParseSuccess(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTONE + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL + TAG_DESC_FUN, new AddEventCommand(expectedEvent));

        // multiple categories - all accepted
        Event expectedEventMultipleCategories = new EventBuilder(EVENTTWO)
                .withCategories(VALID_EVENT_CATEGORY_WORK, VALID_EVENT_CATEGORY_SCHOOL)
                .build();
        assertParseSuccess(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                        + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                        + CATEGORY_DESC_SCHOOL + CATEGORY_DESC_WORK + TAG_DESC_FUN,
                new AddEventCommand(expectedEventMultipleCategories));

        // multiple tags - all accepted
        Event expectedEventMultipleTags = new EventBuilder(EVENTTWO)
                .withTags(VALID_EVENT_TAG_FINAL, VALID_EVENT_TAG_FUN).build();
        assertParseSuccess(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                        + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                        + CATEGORY_DESC_SCHOOL + TAG_DESC_FINAL + TAG_DESC_FUN,
                new AddEventCommand(expectedEventMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Event expectedEvent = new EventBuilder(EVENTONE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK, new AddEventCommand(expectedEvent));
    }

    @Test
    @Disabled
    public void parse_pastDateTime_failure() {
        // zero tags
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + INVALID_ENDDATEPAST_DESC + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK, expectedMessage);
    }

    @Test
    @Disabled
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_EVENT_NAME_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL, expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, NAME_DESC_EVENTTWO + VALID_EVENT_STARTDATE_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL, expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + VALID_EVENT_STARTTIME_EVENTTWO + ENDDATE_DESC_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL, expectedMessage);

        // missing end date prefix
        assertParseFailure(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + VALID_EVENT_ENDDATE_EVENTTWO + ENDTIME_DESC_EVENTTWO
                + CATEGORY_DESC_SCHOOL, expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + VALID_EVENT_ENDTIME_EVENTTWO
                + CATEGORY_DESC_SCHOOL, expectedMessage);

        // missing category prefix
        assertParseFailure(parser, NAME_DESC_EVENTTWO + STARTDATE_DESC_EVENTTWO
                + STARTTIME_DESC_EVENTTWO + ENDDATE_DESC_EVENTTWO + VALID_EVENT_ENDTIME_EVENTTWO
                + VALID_EVENT_CATEGORY_SCHOOL, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_EVENT_NAME_EVENTTWO + VALID_EVENT_STARTDATE_EVENTTWO
                + VALID_EVENT_STARTTIME_EVENTTWO + VALID_EVENT_ENDDATE_EVENTTWO + VALID_EVENT_ENDTIME_EVENTTWO
                + VALID_EVENT_CATEGORY_SCHOOL, expectedMessage);
    }

    @Test
    @Disabled
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK + TAG_DESC_FINAL, Name.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertParseFailure(parser, NAME_DESC_EVENTONE + INVALID_STARTDATE_DESC
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK + TAG_DESC_FINAL, Date.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + INVALID_STARTTIME_DESC + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK + TAG_DESC_FINAL, Time.MESSAGE_CONSTRAINTS);

        // invalid end date
        assertParseFailure(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + INVALID_ENDDATE_DESC + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK + TAG_DESC_FINAL, Date.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + INVALID_ENDTIME_DESC
                + CATEGORY_DESC_WORK + TAG_DESC_FINAL, Time.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + INVALID_CATEGORY_DESC + TAG_DESC_FINAL, Category.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_EVENTONE + STARTDATE_DESC_EVENTONE
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_STARTDATE_DESC
                + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                + CATEGORY_DESC_WORK, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STARTDATE_DESC_EVENTONE
                        + STARTTIME_DESC_EVENTONE + ENDDATE_DESC_EVENTONE + ENDTIME_DESC_EVENTONE
                        + CATEGORY_DESC_WORK + TAG_DESC_FINAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }

}
