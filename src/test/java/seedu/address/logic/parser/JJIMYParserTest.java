package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.customer.CustomerAddCommand;
import seedu.address.logic.commands.customer.CustomerClearCommand;
import seedu.address.logic.commands.customer.CustomerDeleteCommand;
import seedu.address.logic.commands.customer.CustomerEditCommand;
import seedu.address.logic.commands.customer.CustomerEditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.customer.CustomerFindCommand;
import seedu.address.logic.commands.customer.CustomerListCommand;
import seedu.address.logic.parser.components.CustomerParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameContainsWordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class JJIMYParserTest {

    private final JJIMYParser parser = new JJIMYParser();

    @Test
    public void customer_parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        CustomerAddCommand command = (CustomerAddCommand) parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + PersonUtil.getAddCommand(person));
        assertEquals(new CustomerAddCommand(person), command);
    }

    @Test
    public void customer_parseCommand_clear() throws Exception {
        assertTrue(parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerClearCommand.COMMAND_WORD)
                instanceof CustomerClearCommand);
        assertTrue(parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerClearCommand.COMMAND_WORD + " 3")
                instanceof CustomerClearCommand);
    }

    @Test
    public void customer_parseCommand_delete() throws Exception {
        CustomerDeleteCommand command = (CustomerDeleteCommand) parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerDeleteCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new CustomerDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void customer_parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        CustomerEditCommand command = (CustomerEditCommand) parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new CustomerEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCompo(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCompo(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void customer_parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        CustomerFindCommand command = (CustomerFindCommand) parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerFindCommand.COMMAND_WORD + " "
                        + PersonUtil.getPersonFind(keywords));
        assertEquals(new CustomerFindCommand(new PersonNameContainsWordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCompo(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCompo(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void customer_parseCommand_list() throws Exception {
        assertTrue(parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerListCommand.COMMAND_WORD)
                instanceof CustomerListCommand);
        assertTrue(parser.parseCompo(
                CustomerParser.COMPONENT_WORD + " " + CustomerListCommand.COMMAND_WORD + " 3")
                instanceof CustomerListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCompo(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE), () -> parser.parseCompo("unknownCommand"));
    }
}
