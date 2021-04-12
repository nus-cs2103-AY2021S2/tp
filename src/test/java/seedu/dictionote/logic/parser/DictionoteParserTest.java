package seedu.dictionote.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_DEFINITION;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_MAINCONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TERM;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_WEEK;
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
import seedu.dictionote.logic.commands.AddContentCommand;
import seedu.dictionote.logic.commands.AddDefinitionCommand;
import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.logic.commands.ClearContactCommand;
import seedu.dictionote.logic.commands.CloseCommand;
import seedu.dictionote.logic.commands.ConvertTxtNoteCommand;
import seedu.dictionote.logic.commands.CopyContentToNoteCommand;
import seedu.dictionote.logic.commands.DeleteContactCommand;
import seedu.dictionote.logic.commands.DeleteNoteCommand;
import seedu.dictionote.logic.commands.EditContactCommand;
import seedu.dictionote.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.dictionote.logic.commands.EditModeCommand;
import seedu.dictionote.logic.commands.EditModeQuitCommand;
import seedu.dictionote.logic.commands.EditModeSaveCommand;
import seedu.dictionote.logic.commands.EditNoteCommand;
import seedu.dictionote.logic.commands.EmailContactCommand;
import seedu.dictionote.logic.commands.ExitCommand;
import seedu.dictionote.logic.commands.FindContactCommand;
import seedu.dictionote.logic.commands.FindContentCommand;
import seedu.dictionote.logic.commands.FindDefinitionCommand;
import seedu.dictionote.logic.commands.FindNoteCommand;
import seedu.dictionote.logic.commands.HelpCommand;
import seedu.dictionote.logic.commands.ListCommandCommand;
import seedu.dictionote.logic.commands.ListCommandContactCommand;
import seedu.dictionote.logic.commands.ListCommandDictionaryCommand;
import seedu.dictionote.logic.commands.ListCommandNoteCommand;
import seedu.dictionote.logic.commands.ListCommandUiCommand;
import seedu.dictionote.logic.commands.ListContactCommand;
import seedu.dictionote.logic.commands.ListContentCommand;
import seedu.dictionote.logic.commands.ListDefinitionCommand;
import seedu.dictionote.logic.commands.ListNoteCommand;
import seedu.dictionote.logic.commands.MarkAllAsUndoneNoteCommand;
import seedu.dictionote.logic.commands.MarkAsDoneNoteCommand;
import seedu.dictionote.logic.commands.MarkAsUndoneNoteCommand;
import seedu.dictionote.logic.commands.MergeNoteCommand;
import seedu.dictionote.logic.commands.MostFreqContactCommand;
import seedu.dictionote.logic.commands.OpenCommand;
import seedu.dictionote.logic.commands.SetContactDividerPositionCommand;
import seedu.dictionote.logic.commands.SetDictionaryDividerPositionCommand;
import seedu.dictionote.logic.commands.SetMainDividerPositionCommand;
import seedu.dictionote.logic.commands.SetNoteDividerPositionCommand;
import seedu.dictionote.logic.commands.ShowDictionaryContentCommand;
import seedu.dictionote.logic.commands.ShowNoteCommand;
import seedu.dictionote.logic.commands.SortNoteByTimeCommand;
import seedu.dictionote.logic.commands.SortNoteCommand;
import seedu.dictionote.logic.commands.ToggleDictionaryOrientationCommand;
import seedu.dictionote.logic.commands.ToggleNoteOrientationCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.EmailContainsKeywordsPredicate;
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
        assertTrue(parser.parseCommand(ClearContactCommand.COMMAND_WORD) instanceof ClearContactCommand);
        assertTrue(parser.parseCommand(ClearContactCommand.COMMAND_WORD + " 3") instanceof ClearContactCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
            DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_editModeNote() throws Exception {
        assertTrue(parser.parseCommand(EditModeCommand.COMMAND_WORD) instanceof EditModeCommand);
        assertTrue(parser.parseCommand(EditModeCommand.COMMAND_WORD + " 3") instanceof EditModeCommand);
    }

    @Test
    public void parseCommand_editModeExit() throws Exception {
        assertTrue(parser.parseCommand(EditModeQuitCommand.COMMAND_WORD) instanceof EditModeQuitCommand);
        assertTrue(parser.parseCommand(EditModeQuitCommand.COMMAND_WORD + " 3") instanceof EditModeQuitCommand);
    }

    @Test
    public void parseCommand_editModeSave() throws Exception {
        assertTrue(parser.parseCommand(EditModeSaveCommand.COMMAND_WORD) instanceof EditModeSaveCommand);
        assertTrue(parser.parseCommand(EditModeSaveCommand.COMMAND_WORD + " 3") instanceof EditModeSaveCommand);
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
    public void parseCommand_listCommandDictionary() throws Exception {
        assertTrue(parser.parseCommand(ListCommandDictionaryCommand.COMMAND_WORD)
            instanceof ListCommandDictionaryCommand);
        assertTrue(parser.parseCommand(ListCommandDictionaryCommand.COMMAND_WORD + " 3")
            instanceof ListCommandDictionaryCommand);
    }

    @Test
    public void parseCommand_listCommandNote() throws Exception {
        assertTrue(parser.parseCommand(ListCommandNoteCommand.COMMAND_WORD)
            instanceof ListCommandNoteCommand);
        assertTrue(parser.parseCommand(ListCommandNoteCommand.COMMAND_WORD + " 3")
            instanceof ListCommandNoteCommand);
    }

    @Test
    public void parseCommand_listCommandContact() throws Exception {
        assertTrue(parser.parseCommand(ListCommandContactCommand.COMMAND_WORD)
            instanceof ListCommandContactCommand);
        assertTrue(parser.parseCommand(ListCommandContactCommand.COMMAND_WORD + " 3")
            instanceof ListCommandContactCommand);
    }

    @Test
    public void parseCommand_listCommandUi() throws Exception {
        assertTrue(parser.parseCommand(ListCommandUiCommand.COMMAND_WORD)
            instanceof ListCommandUiCommand);
        assertTrue(parser.parseCommand(ListCommandUiCommand.COMMAND_WORD + " 3")
            instanceof ListCommandUiCommand);
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
        List<String> emailKeywords = Arrays.asList("@aexample.com", "@bexample.com", "@cexample.net");

        FindContactCommand command = (FindContactCommand) parser.parseCommand(
            FindContactCommand.COMMAND_WORD
                + " "
                + nameKeywords.stream().map(nk -> PREFIX_NAME + nk).collect(Collectors.joining(" "))
                + " "
                + emailKeywords.stream().map(ek -> PREFIX_EMAIL + ek).collect(Collectors.joining(" "))
                + " "
                + tagKeywords.stream().map(tk -> PREFIX_TAG + tk).collect(Collectors.joining(" "))

        );

        assertEquals(new FindContactCommand(
            new NameContainsKeywordsPredicate(nameKeywords),
            new EmailContainsKeywordsPredicate(emailKeywords),
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
    public void parseCommand_setContactDividerPosition() throws Exception {
        assertTrue(parser.parseCommand(SetContactDividerPositionCommand.COMMAND_WORD + " 6")
            instanceof SetContactDividerPositionCommand);
    }

    @Test
    public void parseCommand_setDictionaryDividerPosition() throws Exception {
        assertTrue(parser.parseCommand(SetDictionaryDividerPositionCommand.COMMAND_WORD + " 6")
            instanceof SetDictionaryDividerPositionCommand);
    }

    @Test
    public void parseCommand_setNoteDividerPosition() throws Exception {
        assertTrue(parser.parseCommand(SetNoteDividerPositionCommand.COMMAND_WORD + " 6")
            instanceof SetNoteDividerPositionCommand);
    }

    @Test
    public void parseCommand_setMainDividerPosition() throws Exception {
        assertTrue(parser.parseCommand(SetMainDividerPositionCommand.COMMAND_WORD + " 6")
            instanceof SetMainDividerPositionCommand);
    }

    @Test
    public void parseCommand_toggleNotePanel() throws Exception {
        assertTrue(parser.parseCommand(
            ToggleNoteOrientationCommand.COMMAND_WORD) instanceof ToggleNoteOrientationCommand);
        assertTrue(parser.parseCommand(
            ToggleNoteOrientationCommand.COMMAND_WORD + " 3") instanceof ToggleNoteOrientationCommand);
    }

    @Test
    public void parseCommand_toggleDictionaryPanel() throws Exception {
        assertTrue(parser.parseCommand(
            ToggleDictionaryOrientationCommand.COMMAND_WORD) instanceof ToggleDictionaryOrientationCommand);
        assertTrue(parser.parseCommand(
            ToggleDictionaryOrientationCommand.COMMAND_WORD + " 3")
            instanceof ToggleDictionaryOrientationCommand);
    }

    @Test
    public void parseCommand_editNote() throws Exception {
        assertTrue(parser.parseCommand(EditNoteCommand.COMMAND_WORD + " 1 " + PREFIX_CONTENT + "1")
            instanceof EditNoteCommand);
    }

    @Test
    public void parseCommand_findNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(FindNoteCommand.COMMAND_WORD + " c/1")
            instanceof FindNoteCommand);
    }

    @Test
    public void parseCommand_findDefinition() throws Exception {
        assertTrue(parser.parseCommand(FindDefinitionCommand.COMMAND_WORD + " 1")
            instanceof FindDefinitionCommand);
    }

    @Test
    public void parseCommand_findContent() throws Exception {
        assertTrue(parser.parseCommand(FindContentCommand.COMMAND_WORD + " 1")
            instanceof FindContentCommand);
    }

    @Test
    public void parseCommand_copyContentToNote() throws Exception {
        assertTrue(parser.parseCommand(CopyContentToNoteCommand.COMMAND_WORD + " 1")
            instanceof CopyContentToNoteCommand);
    }

    @Test
    public void parseCommand_mostFreqContactCommand() throws Exception {
        assertTrue(parser.parseCommand(MostFreqContactCommand.COMMAND_WORD)
            instanceof MostFreqContactCommand);
    }


    @Test
    public void parseCommand_listNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(ListNoteCommand.COMMAND_WORD)
            instanceof ListNoteCommand);
    }

    @Test
    public void parseCommand_listContentCommand() throws Exception {
        assertTrue(parser.parseCommand(ListContentCommand.COMMAND_WORD)
            instanceof ListContentCommand);
    }

    @Test
    public void parseCommand_listDefinitionCommand() throws Exception {
        assertTrue(parser.parseCommand(ListDefinitionCommand.COMMAND_WORD)
            instanceof ListDefinitionCommand);
    }

    @Test
    public void parseCommand_markAllAsUndoneNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(MarkAllAsUndoneNoteCommand.COMMAND_WORD)
            instanceof MarkAllAsUndoneNoteCommand);
    }

    @Test
    public void parseCommand_markAsDoneNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(MarkAsDoneNoteCommand.COMMAND_WORD + " 1")
            instanceof MarkAsDoneNoteCommand);
    }

    @Test
    public void parseCommand_markAsUndoneNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(MarkAsUndoneNoteCommand.COMMAND_WORD + " 1")
            instanceof MarkAsUndoneNoteCommand);
    }

    @Test
    public void parseCommand_convertTxtNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(ConvertTxtNoteCommand.COMMAND_WORD + " 1")
            instanceof ConvertTxtNoteCommand);
    }

    @Test
    public void parseCommand_sortNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(SortNoteCommand.COMMAND_WORD)
            instanceof SortNoteCommand);
    }

    @Test
    public void parseCommand_sortNoteByTimeCommand() throws Exception {
        assertTrue(parser.parseCommand(SortNoteByTimeCommand.COMMAND_WORD)
            instanceof SortNoteByTimeCommand);
    }

    @Test
    public void parseCommand_mergeNoteCommand() throws Exception {
        assertTrue(parser.parseCommand(MergeNoteCommand.COMMAND_WORD + " 1 1")
            instanceof MergeNoteCommand);
    }

    @Test
    public void parseCommand_deleteNote() throws Exception {
        assertTrue(parser.parseCommand(DeleteNoteCommand.COMMAND_WORD + " 1") instanceof DeleteNoteCommand);
    }

    @Test
    public void parseCommand_addDefinitionCommand() throws Exception {
        assertTrue(parser.parseCommand(AddDefinitionCommand.COMMAND_WORD + " " + PREFIX_TERM + "1 "
            + PREFIX_DEFINITION + "1")
            instanceof AddDefinitionCommand);
    }

    @Test
    public void parseCommand_addContentCommand() throws Exception {
        assertTrue(parser.parseCommand(AddContentCommand.COMMAND_WORD + " " + PREFIX_WEEK + "1 "
            + PREFIX_HEADER + "1 " + PREFIX_MAINCONTENT + "1")
            instanceof AddContentCommand);
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
