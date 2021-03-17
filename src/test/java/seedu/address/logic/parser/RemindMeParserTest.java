package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.addcommand.AddPersonCommand;
import seedu.address.logic.commands.deletecommand.DeletePersonCommand;
import seedu.address.logic.commands.findcommand.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class RemindMeParserTest {

    private final RemindMeParser remindMeParser = new RemindMeParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand)
                remindMeParser.parseCommand(PersonUtil.getaddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(remindMeParser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(remindMeParser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) remindMeParser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON), command);
    }



    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(remindMeParser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(remindMeParser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
        assertTrue(remindMeParser.parseCommand(ExitCommand.COMMAND_CHAR) instanceof ExitCommand);
        assertTrue(remindMeParser.parseCommand(ExitCommand.COMMAND_CHAR + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) remindMeParser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(remindMeParser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(remindMeParser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
        assertTrue(remindMeParser.parseCommand(HelpCommand.COMMAND_CHAR) instanceof HelpCommand);
        assertTrue(remindMeParser.parseCommand(HelpCommand.COMMAND_CHAR + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(remindMeParser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(remindMeParser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_calender() throws Exception {
        assertTrue(remindMeParser.parseCommand(CalendarCommand.COMMAND_WORD) instanceof CalendarCommand);
        assertTrue(remindMeParser.parseCommand(CalendarCommand.COMMAND_WORD + " 3") instanceof CalendarCommand);
        assertTrue(remindMeParser.parseCommand(CalendarCommand.COMMAND_CHAR) instanceof CalendarCommand);
        assertTrue(remindMeParser.parseCommand(CalendarCommand.COMMAND_CHAR + " 3") instanceof CalendarCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> remindMeParser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> remindMeParser.parseCommand("unknownCommand"));
    }
}
