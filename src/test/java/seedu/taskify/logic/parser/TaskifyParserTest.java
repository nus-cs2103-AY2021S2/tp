package seedu.taskify.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.taskify.testutil.Assert.assertThrows;
import static seedu.taskify.testutil.TypicalIndexes.INDEXES_FIRST_TO_THIRD_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.taskify.testutil.TypicalInputs.DELETE_ALL_COMPLETED_INPUT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.taskify.logic.commands.AddCommand;
import seedu.taskify.logic.commands.ClearCommand;
import seedu.taskify.logic.commands.DeleteCommand;
import seedu.taskify.logic.commands.DeleteMultipleCommand;
import seedu.taskify.logic.commands.EditCommand;
import seedu.taskify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.taskify.logic.commands.ExitCommand;
import seedu.taskify.logic.commands.FindCommand;
import seedu.taskify.logic.commands.HelpCommand;
import seedu.taskify.logic.commands.ListCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.task.NameContainsKeywordsPredicate;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;
import seedu.taskify.testutil.EditTaskDescriptorBuilder;
import seedu.taskify.testutil.TaskBuilder;
import seedu.taskify.testutil.TaskUtil;

public class TaskifyParserTest {

    private final TaskifyParser parser = new TaskifyParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task task = new TaskBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TaskUtil.getAddCommand(task));
        assertEquals(new AddCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_deleteMultipleIndex() throws Exception {
        DeleteMultipleCommand command = (DeleteMultipleCommand) parser.parseCommand(
                DeleteMultipleCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " "
                        + INDEX_SECOND_TASK.getOneBased() + " " + INDEX_THIRD_TASK.getOneBased());
        assertEquals(new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK), command);
    }

    @Test
    public void parseCommand_deleteMultipleUsingIndexRange() throws Exception {
        DeleteMultipleCommand command = (DeleteMultipleCommand) parser.parseCommand(
                DeleteMultipleCommand.COMMAND_WORD + " " + "1-3");
        assertEquals(new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK), command);
    }

    @Test
    public void parseCommand_deleteTasksByStatus() throws Exception {
        DeleteMultipleCommand command = (DeleteMultipleCommand) parser.parseCommand(DELETE_ALL_COMPLETED_INPUT);
        assertEquals(new DeleteMultipleCommand(new Status(StatusType.COMPLETED)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                            + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_TASK, descriptor), command);
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
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
                        ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
