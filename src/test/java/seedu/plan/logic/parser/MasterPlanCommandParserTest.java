package seedu.plan.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.Command;
import seedu.plan.logic.commands.MasterPlanCommand;
import seedu.plan.logic.parser.exceptions.ParseException;


class MasterPlanCommandParserTest {
    @Test
    void validInput_success() throws ParseException {
        String input = " p/1";
        Command actualCommand = new MasterPlanCommandParser().parse(input);
        Command expectedCommand = new MasterPlanCommand(Index.fromOneBased(1));
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    void invalidPreamble_noLeadingSpace_throwParseException() {
        String input = "p/1";
        assertThrows(ParseException.class, () -> new MasterPlanCommandParser().parse(input));
    }

    @Test
    void nullInput_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MasterPlanCommandParser().parse(null));
    }

    @Test
    void invalidPrefix_noPlan_throwParseException() {
        assertThrows(ParseException.class, () -> new MasterPlanCommandParser().parse(" P/1"));
        assertThrows(ParseException.class, () -> new MasterPlanCommandParser().parse(" s/1"));
        assertThrows(ParseException.class, () -> new MasterPlanCommandParser().parse(" p1"));
        assertThrows(ParseException.class, () -> new MasterPlanCommandParser().parse(" test"));
    }

    @Test
    void boundaryTest_prefixValue_success() throws ParseException {
        // MasterPlanCommandParser does not validate plan number
        // It only creates a command representation of the user's input
        int[] testValues = new int[]{ 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE };

        for (int testVal : testValues) {
            Index index = Index.fromOneBased(testVal);
            assertEquals(
                    new MasterPlanCommand(index),
                    new MasterPlanCommandParser().parse(" p/" + testVal)
            );
        }
    }

    @Test
    void exceptionMessage_invalidPrefix_exceptionMessageEqual() {
        String expected = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                MasterPlanCommand.MESSAGE_USAGE);
        String[] invalidInputs = new String[] { "p/1", "p1", "", "s1", "P/1", "plan/1", "p/0.1" };
        for (String invalidInput : invalidInputs) {
            try {
                new MasterPlanCommandParser().parse(invalidInput);
                fail();
            } catch (ParseException pe) {
                assertEquals(expected, pe.getMessage());
            }
        }
    }
}
