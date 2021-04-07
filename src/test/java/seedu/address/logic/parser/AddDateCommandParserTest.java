package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.DateUtil.DATE_INPUT_FORMATTER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.AddDateCommand;
import seedu.address.model.person.SpecialDate;
import seedu.address.testutil.SpecialDateBuilder;

public class AddDateCommandParserTest {

    private final AddDateCommandParser parser = new AddDateCommandParser();

    private final SpecialDateBuilder specialDate1Builder = new SpecialDateBuilder()
            .withDate(LocalDate.of(2019, 10, 10))
            .withDescription("Anniversary");

    private final SpecialDate specialDate1 = specialDate1Builder.build();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 d/10-10-2019 desc/Anniversary",
                new AddDateCommand(Index.fromOneBased(1), specialDate1));
    }

    @Test
    public void parse_boundaryDate_success() {
        LocalDate dateYesterday = LocalDate.now().minusDays(1);
        LocalDate dateToday = LocalDate.now();

        String dateYesterdayStr = DATE_INPUT_FORMATTER.format(dateYesterday);
        String dateTodayStr = DATE_INPUT_FORMATTER.format(dateToday);

        // dateYesterday
        SpecialDate spd2 = specialDate1Builder.withDate(dateYesterday).build();
        assertParseSuccess(parser, "1 d/" + dateYesterdayStr + " desc/Anniversary",
                new AddDateCommand(Index.fromOneBased(1), spd2));

        // dateToday
        SpecialDate spd3 = specialDate1Builder.withDate(dateToday).build();
        assertParseSuccess(parser, "1 d/" + dateTodayStr + " desc/Anniversary",
                new AddDateCommand(Index.fromOneBased(1), spd3));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDateCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, "d/10-10-2019 desc/Anniversary", expectedMessage);

        // missing date
        assertParseFailure(parser, "1 desc/Anniversary", expectedMessage);

        // missing description
        assertParseFailure(parser, "1 d/10-10-2019", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date format
        assertParseFailure(parser, "1 d/10 Oct 2019 desc/Anniversary", DateUtil.MESSAGE_CONSTRAINT);

        // empty description
        assertParseFailure(parser, "1 d/10-10-2019 desc/", SpecialDate.DESCRIPTION_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_boundaryDate_failure() {
        LocalDate dateTomorrow = LocalDate.now().plusDays(1);

        String dateTomorrowStr = DATE_INPUT_FORMATTER.format(dateTomorrow);

        // dateTomorrow
        assertParseFailure(parser, "1 d/" + dateTomorrowStr + " desc/Anniversary",
                String.format(Messages.MESSAGE_DATE_AFTER_TODAY, DateUtil.toErrorMessage(dateTomorrow)));
    }
}
