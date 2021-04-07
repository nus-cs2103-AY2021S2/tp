package seedu.address.logic.parser.findcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommand.FindEventCommand;
import seedu.address.logic.commands.findcommand.FindModuleCommand;
import seedu.address.logic.commands.findcommand.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.DescriptionContainsKeywordsPredicate;
import seedu.address.model.module.TitleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parseCommand_emptyArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parseCommand("       "));
    }

    @Test
    public void parseCommand_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parseCommand(" *"));
        assertThrows(ParseException.class, () -> parser.parseCommand(" p/"));
        assertThrows(ParseException.class, () -> parser.parseCommand(" abd m/"));
    }

    @Test
    public void parseCommand_findModuleCommand() throws ParseException {
        // prefixes not present -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" b/"));

        // a/ or e/ present with m/ -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" m/MOD1 a/a"));
        assertThrows(ParseException.class, () -> parser.parseCommand(" m/MOD2 e/a"));

        // preamble not empty -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" abc m/"));

        // valid args -> returns FindModuleCommand
        String input = " m/MOD1";
        FindModuleCommand command = new FindModuleCommand(new TitleContainsKeywordsPredicate(
                Collections.singletonList("MOD1")
        ));
        assertEquals(command, parser.parseCommand(input));
    }

    @Test
    public void parseCommand_findEventCommand() throws ParseException {
        // prefixes not present -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" b/"));

        // preamble not empty -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" abc g/"));

        // valid args -> returns FindModuleCommand
        String input = " g/EVENT1";
        FindEventCommand command = new FindEventCommand(new DescriptionContainsKeywordsPredicate(
                Collections.singletonList("EVENT1")
        ));
        assertEquals(command, parser.parseCommand(input));
    }

    @Test
    public void parseCommand_findPersonCommand() throws ParseException {
        // prefixes not present -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" b/"));

        // preamble not empty -> throws
        assertThrows(ParseException.class, () -> parser.parseCommand(" abc n/"));

        // valid args -> returns FindModuleCommand
        String input = " n/NAME1";
        FindPersonCommand command = new FindPersonCommand(new NameContainsKeywordsPredicate(
                Collections.singletonList("NAME1")
        ));
        assertEquals(command, parser.parseCommand(input));
    }
}
