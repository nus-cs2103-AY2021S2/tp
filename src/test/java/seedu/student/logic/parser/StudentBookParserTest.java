package seedu.student.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.student.testutil.Assert.assertThrows;
import static seedu.student.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.AddCommand;
import seedu.student.logic.commands.ClearCommand;
import seedu.student.logic.commands.DeleteCommand;
import seedu.student.logic.commands.EditCommand;
import seedu.student.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.student.logic.commands.ExitCommand;
import seedu.student.logic.commands.FilterCommand;
import seedu.student.logic.commands.FindCommand;
import seedu.student.logic.commands.HelpCommand;
import seedu.student.logic.commands.ListCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MatriculationNumberContainsKeywordsPredicate;
import seedu.student.model.student.Student;
import seedu.student.model.student.VaccinationStatusContainsKeywords;
import seedu.student.testutil.EditStudentDescriptorBuilder;
import seedu.student.testutil.StudentBuilder;
import seedu.student.testutil.StudentUtil;

public class StudentBookParserTest {

    private final StudentBookParser parser = new StudentBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + MATRIC_NUMBER_FIRST_STUDENT);
        assertEquals(new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_STUDENT)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        String keywords = "NOT_VACCINATED";
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new FilterCommand(new VaccinationStatusContainsKeywords(keywords)), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String keywords = "A0287543E";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new FindCommand(new MatriculationNumberContainsKeywordsPredicate(keywords)), command);
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
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
