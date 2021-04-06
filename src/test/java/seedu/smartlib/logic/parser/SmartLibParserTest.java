package seedu.smartlib.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_READER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.logic.commands.ClearCommand;
import seedu.smartlib.logic.commands.DeleteReaderCommand;
import seedu.smartlib.logic.commands.ExitCommand;
import seedu.smartlib.logic.commands.FindReaderCommand;
import seedu.smartlib.logic.commands.HelpCommand;
import seedu.smartlib.logic.commands.ListReaderCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.testutil.ReaderBuilder;
import seedu.smartlib.testutil.ReaderUtil;

public class SmartLibParserTest {

    private final SmartLibParser parser = new SmartLibParser();

    @Test
    public void parseCommand_add() throws Exception {
        Reader reader = new ReaderBuilder().build();
        AddReaderCommand command = (AddReaderCommand) parser.parseCommand(ReaderUtil.getAddCommand(reader));
        assertEquals(new AddReaderCommand(reader), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteReaderCommand command = (DeleteReaderCommand) parser.parseCommand(
                DeleteReaderCommand.COMMAND_WORD + " " + INDEX_FIRST_READER.getOneBased());
        assertEquals(new DeleteReaderCommand(INDEX_FIRST_READER), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindReaderCommand command = (FindReaderCommand) parser.parseCommand(
                FindReaderCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindReaderCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListReaderCommand.COMMAND_WORD) instanceof ListReaderCommand);
        assertTrue(parser.parseCommand(ListReaderCommand.COMMAND_WORD + " 3") instanceof ListReaderCommand);
    }

    @Test
    public void parseCommand_borrow() throws Exception {
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

}
