package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineDateInRangePredicate;

public class ShowCommandParserTest {

    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyArg_returnsShowCommand() {
        Optional<DeadlineDate> emptyPredicate = Optional.empty();
        ShowCommand expectedFindCommand =
                new ShowCommand(new DeadlineDateInRangePredicate(emptyPredicate, emptyPredicate));
        assertParseSuccess(parser, "", expectedFindCommand);
        assertParseSuccess(parser, "  ", expectedFindCommand); // whitespaces
        assertParseSuccess(parser, "   sdadasdas ", expectedFindCommand); // random
    }

    @Test
    public void parse_invalidArg_returnsShowCommand() {
        Optional<DeadlineDate> emptyPredicate = Optional.empty();
        ShowCommand expectedFindCommand =
                new ShowCommand(new DeadlineDateInRangePredicate(emptyPredicate, emptyPredicate));
        assertParseSuccess(parser, "   sdadasdas ", expectedFindCommand); // random
        assertParseSuccess(parser, "   10-11-2020 ", expectedFindCommand); // date without the prefix
    }

    @Test
    public void parse_validArgs_returnsShowCommand() {
        Optional<DeadlineDate> emptyPredicate = Optional.empty();
        Optional<DeadlineDate> firstPredicate = Optional.of(new DeadlineDate("10-11-2022"));
        Optional<DeadlineDate> secondPredicate = Optional.of(new DeadlineDate("10-11-2023"));

        ShowCommand expectedShowCommand =
                new ShowCommand(new DeadlineDateInRangePredicate(firstPredicate, emptyPredicate));
        // start given
        assertParseSuccess(parser, " start/10-11-2022", expectedShowCommand);
        assertParseSuccess(parser, "    start/10-11-2022", expectedShowCommand); //whitespaces

        // end given
        expectedShowCommand =
                new ShowCommand(new DeadlineDateInRangePredicate(emptyPredicate, secondPredicate));
        assertParseSuccess(parser, " end/10-11-2023", expectedShowCommand);
        assertParseSuccess(parser, "    end/10-11-2023", expectedShowCommand); //whitespaces

        // both given
        expectedShowCommand =
                new ShowCommand(new DeadlineDateInRangePredicate(firstPredicate, secondPredicate));
        assertParseSuccess(parser, " start/10-11-2022 end/10-11-2023", expectedShowCommand);
        //whitespaces at start
        assertParseSuccess(parser, "      start/10-11-2022 end/10-11-2023", expectedShowCommand);
        //whitespaces at end
        assertParseSuccess(parser, " start/10-11-2022 end/10-11-2023      ", expectedShowCommand);
        //whitespaces between
        assertParseSuccess(parser, " start/10-11-2022    end/10-11-2023", expectedShowCommand); //whitespaces between
        //whitespaces at start and between
        assertParseSuccess(parser, "     start/10-11-2022    end/10-11-2023", expectedShowCommand);
        //whitespaces at start and between and end
        assertParseSuccess(parser, "     start/10-11-2022    end/10-11-2023    ", expectedShowCommand);
    }

}
