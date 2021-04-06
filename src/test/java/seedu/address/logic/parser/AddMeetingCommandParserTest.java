package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.DateUtil.DATE_INPUT_FORMATTER;
import static seedu.address.commons.util.TimeUtil.TIME_INPUT_FORMATTER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.person.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandParserTest {
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    private final MeetingBuilder meeting1Builder = new MeetingBuilder()
            .withDate(LocalDate.of(2020, 12, 24))
            .withTime(LocalTime.of(12, 15))
            .withDescription("sample description");

    private final Meeting meeting1 = meeting1Builder.build();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 d/24-12-2020 t/1215 desc/sample description",
                new AddMeetingCommand(Index.fromOneBased(1), meeting1));
    }

    @Test
    public void parse_boundaryDateTime_success() {
        LocalDate dateYesterday = LocalDate.now().minusDays(1);
        LocalDate dateToday = LocalDate.now();

        LocalTime timeNow = LocalTime.now().withSecond(0).withNano(0);
        LocalTime timeBeforeNow = timeNow.minusMinutes(1);

        String dateYesterdayStr = DATE_INPUT_FORMATTER.format(dateYesterday);
        String dateTodayStr = DATE_INPUT_FORMATTER.format(dateToday);

        String timeNowStr = TIME_INPUT_FORMATTER.format(timeNow);
        String timeBeforeNowStr = TIME_INPUT_FORMATTER.format(timeBeforeNow);

        // dateYesterday
        Meeting meeting2 = meeting1Builder.withDate(dateYesterday).build();
        assertParseSuccess(parser, "1 d/" + dateYesterdayStr + " t/1215 desc/sample description",
                new AddMeetingCommand(Index.fromOneBased(1), meeting2));

        // dateToday, timeNow
        Meeting meeting3 = meeting1Builder.withDate(dateToday).withTime(timeNow).build();
        assertParseSuccess(parser, "1 d/" + dateTodayStr + " t/" + timeNowStr + " desc/sample description",
                new AddMeetingCommand(Index.fromOneBased(1), meeting3));

        // dateToday, timeBeforeNow
        Meeting meeting4 = meeting1Builder.withDate(dateToday).withTime(timeBeforeNow).build();
        assertParseSuccess(parser, "1 d/" + dateTodayStr + " t/" + timeBeforeNowStr + " desc/sample description",
                new AddMeetingCommand(Index.fromOneBased(1), meeting4));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, "d/24-12-2020 t/1215 desc/sample description", expectedMessage);

        // missing date
        assertParseFailure(parser, "1 t/1215 desc/sample description", expectedMessage);

        // missing time
        assertParseFailure(parser, "1 d/24-12-2020 desc/sample description", expectedMessage);

        // missing description
        assertParseFailure(parser, "1 d/24-12-2020 t/1215", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // wrong date format
        assertParseFailure(parser, "1 d/24 Dec 2020 t/1215 desc/sample description",
                DateUtil.MESSAGE_CONSTRAINT);

        // wrong time format
        assertParseFailure(parser, "1 d/24-12-2020 t/12:15pm desc/sample description",
                TimeUtil.MESSAGE_CONSTRAINT);

        // empty description
        assertParseFailure(parser, "1 d/24-12-2020 t/1215 desc/", Meeting.DESCRIPTION_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_boundaryDateTime_failure() {
        LocalDate dateTomorrow = LocalDate.now().plusDays(1);
        LocalDate dateToday = LocalDate.now();

        LocalTime timeAfterNow = LocalTime.now().withSecond(0).withNano(0).plusMinutes(1);

        String dateTomorrowStr = DATE_INPUT_FORMATTER.format(dateTomorrow);
        String dateTodayStr = DATE_INPUT_FORMATTER.format(dateToday);

        String timeAfterNowStr = TIME_INPUT_FORMATTER.format(timeAfterNow);

        // dateTomorrow
        assertParseFailure(parser, "1 d/" + dateTomorrowStr + " t/1215 desc/sample description",
                String.format(Messages.MESSAGE_DATE_AFTER_TODAY, DateUtil.toErrorMessage(dateTomorrow)));

        // dateToday, timeAfterNow
        assertParseFailure(parser, "1 d/" + dateTodayStr + " t/" + timeAfterNowStr + " desc/sample description",
                String.format(Messages.MESSAGE_TIME_AFTER_NOW, TimeUtil.toErrorMessage(timeAfterNow)));
    }
}
