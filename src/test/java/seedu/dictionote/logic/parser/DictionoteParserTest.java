package seedu.dictionote.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalUiActions.EXPECTED_UI_OPTION;
import static seedu.dictionote.testutil.TypicalUiActions.VALID_UI_OPTIONS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.AddContactCommand;
import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.logic.commands.ClearCommand;
import seedu.dictionote.logic.commands.CloseCommand;
import seedu.dictionote.logic.commands.DeleteCommand;
import seedu.dictionote.logic.commands.EditContactCommand;
import seedu.dictionote.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.dictionote.logic.commands.ExitCommand;
import seedu.dictionote.logic.commands.FindCommand;
import seedu.dictionote.logic.commands.HelpCommand;
import seedu.dictionote.logic.commands.ListCommand;
import seedu.dictionote.logic.commands.OpenCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.ContactBuilder;
import seedu.dictionote.testutil.ContactUtil;
import seedu.dictionote.testutil.EditContactDescriptorBuilder;
import seedu.dictionote.testutil.NoteUtil;



public class DictionoteParserTest {

    private final DictionoteParser parser = new DictionoteParser();

    @Test
    public void parseCommand_add() throws Exception {
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_addNote() throws Exception {
        Note note = new Note("this is a sample CS2103 note haha");
        AddNoteCommand command = (AddNoteCommand) parser.parseCommand(NoteUtil.getAddNoteCommand(note));
        assertEquals(new AddNoteCommand(note), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
            + INDEX_FIRST_CONTACT.getOneBased() + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_CONTACT, descriptor), command);
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
    public void parseCommand_open() throws Exception {
        for (int i = 0; i < VALID_UI_OPTIONS.length; i++) {
            OpenCommand command = (OpenCommand) parser.parseCommand(
                OpenCommand.COMMAND_WORD + " " + VALID_UI_OPTIONS[i]);
            assertEquals(new OpenCommand(EXPECTED_UI_OPTION[i]), command);
        }
    }

    @Test
    public void parseCommand_close() throws Exception {
        for (int i = 0; i < VALID_UI_OPTIONS.length; i++) {
            CloseCommand command = (CloseCommand) parser.parseCommand(
                CloseCommand.COMMAND_WORD + " " + VALID_UI_OPTIONS[i]);
            assertEquals(new CloseCommand(EXPECTED_UI_OPTION[i]), command);
        }
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
