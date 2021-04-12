package seedu.partyplanet.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.partyplanet.testutil.Assert.assertThrows;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.commands.AddCommand;
import seedu.partyplanet.logic.commands.DeleteCommand;
import seedu.partyplanet.logic.commands.DeleteContactCommand;
import seedu.partyplanet.logic.commands.EAddCommand;
import seedu.partyplanet.logic.commands.EDeleteClearCommand;
import seedu.partyplanet.logic.commands.EDeleteCommand;
import seedu.partyplanet.logic.commands.EDoneCommand;
import seedu.partyplanet.logic.commands.EEditCommand;
import seedu.partyplanet.logic.commands.EEditCommand.EditEventDescriptor;
import seedu.partyplanet.logic.commands.EListCommand;
import seedu.partyplanet.logic.commands.EditCommand;
import seedu.partyplanet.logic.commands.EditFieldCommand;
import seedu.partyplanet.logic.commands.EditFieldCommand.EditPersonDescriptor;
import seedu.partyplanet.logic.commands.ExitCommand;
import seedu.partyplanet.logic.commands.HelpCommand;
import seedu.partyplanet.logic.commands.ListCommand;
import seedu.partyplanet.logic.commands.RedoCommand;
import seedu.partyplanet.logic.commands.ToggleThemeCommand;
import seedu.partyplanet.logic.commands.UndoCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.testutil.EditEventDescriptorBuilder;
import seedu.partyplanet.testutil.EditPersonDescriptorBuilder;
import seedu.partyplanet.testutil.EventBuilder;
import seedu.partyplanet.testutil.EventUtil;
import seedu.partyplanet.testutil.PersonBuilder;
import seedu.partyplanet.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of()), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditFieldCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

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
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_eAdd() throws Exception {
        Event event = new EventBuilder().build();

        assertTrue(parser.parseCommand(EventUtil.getEAddCommand(event)) instanceof EAddCommand);
    }

    @Test
    public void parseCommand_eEdit() throws Exception {
        Event event = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(event).build();
        EEditCommand command = (EEditCommand) parser.parseCommand(EEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EVENT.getOneBased() + " " + EventUtil.getEditEventDescriptorDetails(descriptor));

        assertEquals(new EEditCommand(INDEX_FIRST_EVENT, descriptor), command);
    }

    @Test
    public void parseCommand_eDelete() throws Exception {
        assertTrue(parser.parseCommand(EDeleteCommand.COMMAND_WORD) instanceof EDeleteClearCommand);
    }

    @Test
    public void parseCommand_eDone() throws Exception {
        EDoneCommand command = (EDoneCommand) parser.parseCommand(
                EDoneCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());

        assertEquals(new EDoneCommand(List.of(INDEX_FIRST_PERSON), List.of()), command);
    }

    @Test
    public void parseCommand_eList() throws Exception {
        assertTrue(parser.parseCommand(EListCommand.COMMAND_WORD) instanceof EListCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_toggleTheme() throws Exception {
        assertTrue(parser.parseCommand(ToggleThemeCommand.COMMAND_WORD) instanceof ToggleThemeCommand);
        assertTrue(parser.parseCommand(ToggleThemeCommand.COMMAND_WORD + " 3") instanceof ToggleThemeCommand);
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
