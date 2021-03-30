package seedu.plan.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.plan.commons.core.Messages;
import seedu.plan.logic.commands.Command;
import seedu.plan.logic.commands.CurrentSemesterCommand;
import seedu.plan.logic.parser.exceptions.ParseException;


class CurrentSemesterCommandParserTest {
    @Test
    void validInput_success() throws ParseException {
        String input = " s/1";
        Command actualCommand = new CurrentSemesterCommandParser().parse(input);
        Command expectedCommand = new CurrentSemesterCommand(1);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    void invalidPreamble_noLeadingSpace_throwParseException() {
        String input = "s/1";
        assertThrows(ParseException.class, () -> new CurrentSemesterCommandParser().parse(input));
    }

    @Test
    void nullInput_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentSemesterCommandParser().parse(null));
    }

    @Test
    void invalidPrefix_noSem_throwParseException() {
        assertThrows(ParseException.class, () -> new CurrentSemesterCommandParser().parse(" p/1"));
        assertThrows(ParseException.class, () -> new CurrentSemesterCommandParser().parse(" S/1"));
        assertThrows(ParseException.class, () -> new CurrentSemesterCommandParser().parse(" s1"));
        assertThrows(ParseException.class, () -> new CurrentSemesterCommandParser().parse(" test"));
    }

    @Test
    void boundaryTest_prefixValue_success() throws ParseException {
        // CurrentSemesterCommandParser does not validate semester number
        // It only creates a command representation of the user's input
        int[] testValues = new int[]{
            Integer.MIN_VALUE, Integer.MIN_VALUE + 1, -1, 0, 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE,
        };

        for (int testVal : testValues) {
            assertEquals(
                    new CurrentSemesterCommand(testVal),
                    new CurrentSemesterCommandParser().parse(" s/" + testVal)
            );
        }
    }

    @Test
    void exceptionMessage_invalidPrefix_exceptionMessageEqual() {
        String expected = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                CurrentSemesterCommand.MESSAGE_USAGE);
        String[] invalidInputs = new String[] { "s/1", "s1", "", "p1", "S/1", "sem/1", "s/0.1" };
        for (String invalidInput : invalidInputs) {
            try {
                new CurrentSemesterCommandParser().parse(invalidInput);
                fail();
            } catch (ParseException pe) {
                assertEquals(expected, pe.getMessage());
            }
        }
    }
}
