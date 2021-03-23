package seedu.cakecollate.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.AddCommand;
import seedu.cakecollate.logic.commands.ClearCommand;
import seedu.cakecollate.logic.commands.DeleteCommand;
import seedu.cakecollate.logic.commands.EditCommand;
import seedu.cakecollate.logic.commands.EditCommand.EditOrderDescriptor;
import seedu.cakecollate.logic.commands.ExitCommand;
import seedu.cakecollate.logic.commands.FindCommand;
import seedu.cakecollate.logic.commands.HelpCommand;
import seedu.cakecollate.logic.commands.ListCommand;
import seedu.cakecollate.logic.commands.RemindCommand;
import seedu.cakecollate.logic.commands.RequestCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.NameContainsKeywordsPredicate;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.ReminderDatePredicate;
import seedu.cakecollate.model.order.Request;
import seedu.cakecollate.testutil.EditOrderDescriptorBuilder;
import seedu.cakecollate.testutil.OrderBuilder;
import seedu.cakecollate.testutil.OrderUtil;

public class CakeCollateParserTest {

    private final CakeCollateParser parser = new CakeCollateParser();

    @Test
    public void parseCommand_add() throws Exception {
        Order order = new OrderBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(OrderUtil.getAddCommand(order));
        assertEquals(new AddCommand(order), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ORDER.getOneBased());
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        assertEquals(new DeleteCommand(indexList), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Order order = new OrderBuilder().build();
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(order).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ORDER.getOneBased() + " " + OrderUtil.getEditOrderDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ORDER, descriptor), command);
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
    public void parseCommand_remind() throws Exception {
        RemindCommand command = (RemindCommand) parser.parseCommand(
                RemindCommand.COMMAND_WORD + " " + 1);
        assertEquals(new RemindCommand(new ReminderDatePredicate(1)), command);
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

    @Test
    public void parseCommand_remark() throws Exception {
        final Request request = new Request("Some request.");
        RequestCommand command = (RequestCommand) parser.parseCommand(RequestCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ORDER.getOneBased() + " " + PREFIX_REQUEST + request.value);
        assertEquals(new RequestCommand(INDEX_FIRST_ORDER, request), command);
    }
}
