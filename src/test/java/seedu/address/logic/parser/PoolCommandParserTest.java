package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMMUTER_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.COMMUTER_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMUTER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIPDAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIPTIME;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FEMALE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TRIPDAY_DESC_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.TRIPDAY_DESC_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.TRIPTIME_DESC_EVENING;
import static seedu.address.logic.commands.CommandTestUtil.TRIPTIME_DESC_MORNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMUTER_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMUTER_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FEMALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_STR_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_STR_MORNING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PoolCommand;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;

public class PoolCommandParserTest {
    private PoolCommandParser parser = new PoolCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Driver driver = new DriverBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        Set<Index> commuters = new CommuterBuilder().build();
        Set<Tag> tags = SampleDataUtil.getTagSet(VALID_TAG_IT);
        TripDay tripDay = new TripDay(VALID_TRIPDAY_MONDAY);
        TripTime tripTime = new TripTime(VALID_TRIPTIME_MORNING);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                new PoolCommand(driver, commuters, tripDay, tripTime, tags));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                new PoolCommand(driver, commuters, tripDay, tripTime, tags));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                new PoolCommand(driver, commuters, tripDay, tripTime, tags));

        // multiple tripDays - last tripDay accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_FRIDAY + TRIPDAY_DESC_MONDAY
                + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TRIPTIME_DESC_MORNING + TAG_DESC_FRIEND,
                new PoolCommand(driver, commuters, tripDay, tripTime, tags));

        // multiple tripTimes - last tripTime accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_EVENING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TRIPTIME_DESC_MORNING + TAG_DESC_FRIEND,
                new PoolCommand(driver, commuters, tripDay, tripTime, tags));

        // multiple tags - all accepted
        Set<Tag> multipleTags = SampleDataUtil.getTagSet(VALID_TAG_IT, VALID_TAG_FEMALE);
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND + TAG_DESC_FEMALE,
                new PoolCommand(driver, commuters, tripDay, tripTime, multipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Driver driver = new DriverBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        Set<Index> commuters = new CommuterBuilder().build();
        Set<Tag> tags = new HashSet<>();
        TripDay tripDay = new TripDay(VALID_TRIPDAY_MONDAY);
        TripTime tripTime = new TripTime(VALID_TRIPTIME_MORNING);

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2,
                new PoolCommand(driver, commuters, tripDay, tripTime, tags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PoolCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2, expectedMessage);

        // missing tripDay prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_TRIPDAY_STR_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2, expectedMessage);

        // missing tripTime prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + VALID_TRIPTIME_STR_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2, expectedMessage);

        // missing one commuter prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + VALID_COMMUTER_1, expectedMessage);

        // missing all commuter prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + VALID_COMMUTER_1 + VALID_COMMUTER_2, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_TRIPDAY_STR_MONDAY
                + VALID_TRIPTIME_STR_MORNING + VALID_COMMUTER_1 + VALID_COMMUTER_2, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid tripDay
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_TRIPDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                TripDay.MESSAGE_CONSTRAINTS);

        // invalid tripTime
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + INVALID_TRIPTIME + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                TripTime.MESSAGE_CONSTRAINTS);

        // invalid commuter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                        + TRIPTIME_DESC_MORNING + INVALID_COMMUTER + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (Name)
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + TRIPTIME_DESC_MORNING + COMMUTER_DESC_1 + COMMUTER_DESC_2 + INVALID_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (Phone)
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + TRIPDAY_DESC_MONDAY
                + INVALID_TRIPTIME + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (TripDay)
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_TRIPDAY
                + INVALID_TRIPTIME + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                TripDay.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported (TripTime)
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                + INVALID_TRIPTIME + COMMUTER_DESC_1 + COMMUTER_DESC_2 + INVALID_TAG_DESC,
                TripTime.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + TRIPDAY_DESC_MONDAY
                        + INVALID_TRIPTIME + COMMUTER_DESC_1 + COMMUTER_DESC_2 + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PoolCommand.MESSAGE_USAGE));
    }
}
