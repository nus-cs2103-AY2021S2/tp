package seedu.dictionote.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTENT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalUiActions.EXPECTED_UI_OPTION;
import static seedu.dictionote.testutil.TypicalUiActions.VALID_UI_OPTIONS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.AddContactCommand;
import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.logic.commands.ClearCommand;
import seedu.dictionote.logic.commands.CloseCommand;
import seedu.dictionote.logic.commands.DeleteContactCommand;
import seedu.dictionote.logic.commands.EditContactCommand;
import seedu.dictionote.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.dictionote.logic.commands.EditModeExitCommand;
import seedu.dictionote.logic.commands.EditModeNoteCommand;
import seedu.dictionote.logic.commands.EmailContactCommand;
import seedu.dictionote.logic.commands.ExitCommand;
import seedu.dictionote.logic.commands.FindContactCommand;
import seedu.dictionote.logic.commands.HelpCommand;
import seedu.dictionote.logic.commands.ListCommandCommand;
import seedu.dictionote.logic.commands.ListContactCommand;
import seedu.dictionote.logic.commands.OpenCommand;
import seedu.dictionote.logic.commands.ShowDictionaryContentCommand;
import seedu.dictionote.logic.commands.ShowNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.contact.TagsContainKeywordsPredicate;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.ContactBuilder;
import seedu.dictionote.testutil.ContactUtil;
import seedu.dictionote.testutil.EditContactDescriptorBuilder;
import seedu.dictionote.testutil.NoteUtil;



public class DictionoteParserTest {

    private final DictionoteParser parser = new DictionoteParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_addNote() throws Exception {
        Note note = new Note("this is a sample CS2103 note haha", new HashSet<>());
        AddNoteCommand command = (AddNoteCommand) parser.parseCommand(NoteUtil.getAddNoteCommand(note));
        assertEquals(new AddNoteCommand(note), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
            DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_editModeNote() throws Exception {
        assertTrue(parser.parseCommand(EditModeNoteCommand.COMMAND_WORD) instanceof EditModeNoteCommand);
        assertTrue(parser.parseCommand(EditModeNoteCommand.COMMAND_WORD + " 3") instanceof EditModeNoteCommand);
    }

    @Test
    public void parseCommand_editModeExit() throws Exception {
        assertTrue(parser.parseCommand(EditModeExitCommand.COMMAND_WORD) instanceof EditModeExitCommand);
        assertTrue(parser.parseCommand(EditModeExitCommand.COMMAND_WORD + " 3") instanceof EditModeExitCommand);
    }

    @Test
    public void parseCommand_editContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
            + INDEX_FIRST_CONTACT.getOneBased() + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_emailContact() throws Exception {
        EmailContactCommand command = (EmailContactCommand) parser.parseCommand(
                EmailContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new EmailContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_listContact() throws Exception {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD) instanceof ListContactCommand);
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD + " 3") instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_listCommand() throws Exception {
        assertTrue(parser.parseCommand(ListCommandCommand.COMMAND_WORD) instanceof ListCommandCommand);
        assertTrue(parser.parseCommand(ListCommandCommand.COMMAND_WORD + " 3") instanceof ListCommandCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findContact() throws Exception {
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        List<String> tagKeywords = Arrays.asList("friends", "family", "tutors");

        FindContactCommand command = (FindContactCommand) parser.parseCommand(
            FindContactCommand.COMMAND_WORD
                    + " "
                    + nameKeywords.stream().map(nk -> PREFIX_NAME + nk).collect(Collectors.joining(" "))
                    + " "
                    + tagKeywords.stream().map(tk -> PREFIX_TAG + tk).collect(Collectors.joining(" ")));

        assertEquals(new FindContactCommand(
                new NameContainsKeywordsPredicate(nameKeywords),
                new TagsContainKeywordsPredicate(tagKeywords)), command);
    }

    @Test
    public void parseCommand_showNote() throws Exception {
        ShowNoteCommand command = (ShowNoteCommand) parser.parseCommand(
            ShowNoteCommand.COMMAND_WORD + " " + INDEX_FIRST_NOTE.getOneBased());
        assertEquals(new ShowNoteCommand(INDEX_FIRST_NOTE), command);
    }

    @Test
    public void parseCommand_showDictionaryContent() throws Exception {
        ShowDictionaryContentCommand command = (ShowDictionaryContentCommand) parser.parseCommand(
            ShowDictionaryContentCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTENT.getOneBased());
        assertEquals(new ShowDictionaryContentCommand(INDEX_FIRST_CONTENT), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
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
