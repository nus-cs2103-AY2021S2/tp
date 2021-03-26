package seedu.weeblingo.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.weeblingo.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.ExitCommand;
import seedu.weeblingo.logic.commands.HelpCommand;
import seedu.weeblingo.logic.commands.LearnCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;

public class WeeblingoParserTest {

    private final WeeblingoParser parser = new WeeblingoParser();


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_learn() throws Exception {
        assertTrue(parser.parseCommand(LearnCommand.COMMAND_WORD) instanceof LearnCommand);
        assertTrue(parser.parseCommand(LearnCommand.COMMAND_WORD + " 3") instanceof LearnCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
