//@@ ZhangAnli
package dog.pawbook.logic.parser;

import static dog.pawbook.logic.commands.CommandTestUtil.EMPTY_STRING;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.ScheduleCommand;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;

public class ScheduleCommandParserTest {

    private ScheduleCommandParser parser = new ScheduleCommandParser();
    private Model model;

    @BeforeEach
    public void setupModel() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void parse_invalidDate_failure() {

        String MESSAGE_INVALID_DAY_OF_THE_MONTH = "Day of the month does not exist!";

        // invalid date input failure
        assertParseFailure(parser, "31-02-2021", MESSAGE_INVALID_DAY_OF_THE_MONTH);

        // invalid date input with whitespace failure
        assertParseFailure(parser, " " + "31-02-2021" + " ", MESSAGE_INVALID_DAY_OF_THE_MONTH);

    }

    @Test
    public void parse_validDate_success() {

        LocalDate sampleDate = LocalDate.of(2021, 1, 1);
        ScheduleCommand expectedCommand = new ScheduleCommand(sampleDate);

        // valid date success
        assertParseSuccess(parser, "01-01-2021", expectedCommand);

        // valid date with whitespace string success
        assertParseSuccess(parser, "  01-01-2021  ", expectedCommand);
    }

    @Test
    public void parse_emptyArg_success() {

        ScheduleCommand expectedCommand = new ScheduleCommand(LocalDate.now());

        // valid date success
        assertParseSuccess(parser, EMPTY_STRING, expectedCommand);

        // valid date with whitespace string success
        assertParseSuccess(parser, "  ", expectedCommand);
    }
}
