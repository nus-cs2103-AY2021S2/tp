package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.CAIFAN;
import static seedu.budgetbaby.testutil.TypicalFinancialRecord.LUNCH;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.AddFrCommand;

public class AddFrCommandParserTest {

    private AddFrCommandParser parser = new AddFrCommandParser();

    @Test
    public void parse_validArgs_returnsAddFrCommand() {
        assertParseSuccess(parser, " d/Lunch a/10.0 t/01-01-2021", new AddFrCommand(LUNCH));
    }

    @Test
    public void parse_trailiingSpaces_returnsAddFrCommand() {
        assertParseSuccess(parser, " d/ Lunch a/ 10.0 t/ 01-01-2021", new AddFrCommand(LUNCH));
    }

    @Test
    public void parse_withTags_returnsAddFrCommand() {
        assertParseSuccess(parser, " d/Caifan a/4.50 t/11-02-2021 c/food", new AddFrCommand(CAIFAN));
    }

}
