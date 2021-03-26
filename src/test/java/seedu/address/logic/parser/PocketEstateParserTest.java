package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.clear.ClearAppointmentCommand;
import seedu.address.logic.commands.find.FindAppointmentCommand;
import seedu.address.logic.commands.find.FindPropertyCommand;
import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyPredicateList;

public class PocketEstateParserTest {

    private final PocketEstateParser parser = new PocketEstateParser();

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearAppointmentCommand.COMMAND_WORD) instanceof ClearAppointmentCommand);
        assertTrue(parser.parseCommand(ClearAppointmentCommand.COMMAND_WORD + " 3") instanceof ClearAppointmentCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find_appointment() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindAppointmentCommand command = (FindAppointmentCommand) parser.parseCommand(
                FindAppointmentCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindAppointmentCommand(new AppointmentContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_find_property() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPropertyCommand command = (FindPropertyCommand) parser.parseCommand(
                FindPropertyCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPropertyCommand(new PropertyPredicateList(
                Arrays.asList(new PropertyContainsKeywordsPredicate[]{
                    new PropertyContainsKeywordsPredicate(keywords)}))), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);
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
