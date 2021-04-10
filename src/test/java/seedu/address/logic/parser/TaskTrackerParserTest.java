package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DoTodayCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.DueInCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NotesCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.util.OperationFlag;
import seedu.address.logic.util.SortingFlag;
import seedu.address.model.person.DeadlineDateInRangePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class TaskTrackerParserTest {

    private final TaskTrackerParser parser = new TaskTrackerParser();

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
    public void parseCommand_done() throws Exception {
        DoneCommand command = (DoneCommand) parser.parseCommand(
                DoneCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DoneCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_doToday() throws Exception {
        OperationFlag add = new OperationFlag("-a");
        OperationFlag delete = new OperationFlag("-r");

        // Add function
        DoTodayCommand command = (DoTodayCommand) parser.parseCommand(
                DoTodayCommand.COMMAND_WORD + " -a " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DoTodayCommand(INDEX_FIRST_TASK, add), command);

        // Delete operation
        command = (DoTodayCommand) parser.parseCommand(
                DoTodayCommand.COMMAND_WORD + " -r " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DoTodayCommand(INDEX_FIRST_TASK, delete), command);
    }

    @Test
    public void parseCommand_dueIn() throws Exception {
        DeadlineDateInRangePredicate predicate = new DeadlineDateInRangePredicate(7); // 7 days
        DueInCommand command = (DueInCommand) parser.parseCommand(
                DueInCommand.COMMAND_WORD + " day/7");
        assertEquals(new DueInCommand(predicate), command);
        command = (DueInCommand) parser.parseCommand(
                DueInCommand.COMMAND_WORD + " week/1");
        assertEquals(new DueInCommand(predicate), command);
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
    public void parseCommand_notes() throws Exception {
        final String notes = "Some notes.";
        NotesCommand command = (NotesCommand) parser.parseCommand(NotesCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_NOTES + notes);
        assertEquals(new NotesCommand(INDEX_FIRST_TASK, new Notes(notes)), command);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortingFlag date = new SortingFlag(SortingFlag.DATE_TIME_FLAG);
        SortingFlag name = new SortingFlag(SortingFlag.TASK_NAME_FLAG);
        SortingFlag code = new SortingFlag(SortingFlag.MODULE_CODE_FLAG);
        SortingFlag ptag = new SortingFlag(SortingFlag.PRIORITY_TAG_FLAG);
        SortingFlag weightage = new SortingFlag(SortingFlag.WEIGHTAGE_FLAG);
        SortCommand command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortingFlag.DATE_TIME_FLAG);
        assertEquals(new SortCommand(date), command);
        command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortingFlag.TASK_NAME_FLAG);
        assertEquals(new SortCommand(name), command);
        command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortingFlag.MODULE_CODE_FLAG);
        assertEquals(new SortCommand(code), command);
        command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortingFlag.PRIORITY_TAG_FLAG);
        assertEquals(new SortCommand(ptag), command);
        command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortingFlag.WEIGHTAGE_FLAG);
        assertEquals(new SortCommand(weightage), command);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
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
