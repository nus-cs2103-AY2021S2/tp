package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.commands.ClearCommand;
import fooddiary.logic.commands.DeleteCommand;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.commands.EditCommand.EditEntryDescriptor;
import fooddiary.logic.commands.ExitCommand;
import fooddiary.logic.commands.FindCommand;
import fooddiary.logic.commands.HelpCommand;
import fooddiary.logic.commands.ListCommand;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.NameContainsKeywordsPredicate;
import fooddiary.testutil.EditEntryDescriptorBuilder;
import fooddiary.testutil.EntryBuilder;
import fooddiary.testutil.EntryUtil;





public class FoodDiaryParserTest {

    private final FoodDiaryParser parser = new FoodDiaryParser();

    @Test
    public void parseCommand_add() throws Exception {
        Entry entry = new EntryBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EntryUtil.getAddCommand(entry));
        assertEquals(new AddCommand(entry), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ENTRY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Entry entry = new EntryBuilder().build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(entry).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ENTRY.getOneBased() + " " + EntryUtil.getEditEntryDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ENTRY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
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
