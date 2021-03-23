package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindMembersCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewMembersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DetailsContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddMemberCommand command = (AddMemberCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddMemberCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteMemberCommand command = (DeleteMemberCommand) parser.parseCommand(
                DeleteMemberCommand.COMMAND_WORD + " " + VALID_NAME_AMY);
        assertEquals(new DeleteMemberCommand(ParserUtil.parseName(VALID_NAME_AMY)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(person).build();
        EditMemberCommand command = (EditMemberCommand) parser.parseCommand(EditMemberCommand.COMMAND_WORD + " "
                + VALID_NAME_AMY + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditMemberCommand(ParserUtil.parseName(VALID_NAME_AMY), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindMembersCommand command = (FindMembersCommand) parser.parseCommand(
                FindMembersCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindMembersCommand(new DetailsContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ViewMembersCommand.COMMAND_WORD) instanceof ViewMembersCommand);
        assertTrue(parser.parseCommand(ViewMembersCommand.COMMAND_WORD + " 3") instanceof ViewMembersCommand);
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
