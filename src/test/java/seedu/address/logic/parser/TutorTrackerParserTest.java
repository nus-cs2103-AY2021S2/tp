package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DESC_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_FROM_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_TO_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.appointmentcommands.ListAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.ViewAppointmentCommand;
import seedu.address.logic.commands.eventcommands.ViewEventCommand;
import seedu.address.logic.commands.eventcommands.ViewTimeTableCommand;
import seedu.address.logic.commands.remindercommands.AddReminderCommand;
import seedu.address.logic.commands.remindercommands.DeleteReminderCommand;
import seedu.address.logic.commands.remindercommands.EditReminderCommand;
import seedu.address.logic.commands.remindercommands.ListReminderCommand;
import seedu.address.logic.commands.schedulecommands.AddScheduleCommand;
import seedu.address.logic.commands.schedulecommands.DeleteScheduleCommand;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand;
import seedu.address.logic.commands.schedulecommands.ListScheduleCommand;
import seedu.address.logic.commands.schedulecommands.ViewScheduleCommand;
import seedu.address.logic.commands.tutorcommands.AddCommand;
import seedu.address.logic.commands.tutorcommands.DeleteCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.commands.tutorcommands.FindCommand;
import seedu.address.logic.commands.tutorcommands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateViewPredicate;
import seedu.address.model.tutor.NameContainsKeywordsPredicate;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.EditReminderDescriptorBuilder;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.EditTutorDescriptorBuilder;
import seedu.address.testutil.ReminderBuilder;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TutorBuilder;
import seedu.address.testutil.TutorUtil;

public class TutorTrackerParserTest {

    private final TutorTrackerParser parser = new TutorTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Tutor tutor = new TutorBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TutorUtil.getAddCommand(tutor));
        assertEquals(new AddCommand(tutor), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Tutor tutor = new TutorBuilder().build();
        EditCommand.EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder(tutor).build();
        descriptor.setIsFavourite(null);
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + TutorUtil.getEditTutorDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
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
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_viewAppointment() throws Exception {
        ViewAppointmentCommand command = (ViewAppointmentCommand) parser.parseCommand(
                ViewAppointmentCommand.COMMAND_WORD + " " + "2021-05-24");
        assertEquals(new ViewAppointmentCommand(new DateViewPredicate(APPOINTMENT_FIRST_DATE)), command);
    }

    @Test
    public void parseCommand_listAppointments() throws Exception {
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD) instanceof ListAppointmentCommand);
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD + " 3") instanceof ListAppointmentCommand);
    }

    @Test
    public void parseCommand_viewTimetable() throws Exception {
        ViewTimeTableCommand command = (ViewTimeTableCommand) parser.parseCommand(
                ViewTimeTableCommand.COMMAND_WORD + " " + "2021-05-24");
        assertEquals(new ViewTimeTableCommand(APPOINTMENT_FIRST_DATE.toDate()), command);

        command = (ViewTimeTableCommand) parser.parseCommand(ViewTimeTableCommand.COMMAND_WORD);
        assertEquals(new ViewTimeTableCommand(LocalDate.now()), command);
    }

    @Test
    public void parseCommand_viewEvent() throws Exception {
        ViewEventCommand command = (ViewEventCommand) parser.parseCommand(
                ViewEventCommand.COMMAND_WORD + " " + "2021-05-24");
        assertEquals(new ViewEventCommand(APPOINTMENT_FIRST_DATE), command);
    }

    @Test
    public void parseCommand_addSchedule() throws Exception {
        Schedule schedule = new ScheduleBuilder().build();
        AddScheduleCommand command = (AddScheduleCommand) parser.parseCommand(
                AddScheduleCommand.COMMAND_WORD + TITLE_DESC_ONE + DESC_DESC_ONE + DATE_DESC_ONE
                        + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE);
        assertEquals(new AddScheduleCommand(schedule), command);
    }

    @Test
    public void parseCommand_deleteSchedule() throws Exception {
        DeleteScheduleCommand command = (DeleteScheduleCommand) parser.parseCommand(
                DeleteScheduleCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteScheduleCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editSchedule() throws Exception {
        Schedule schedule = new ScheduleBuilder().build();
        EditScheduleCommand.EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder(schedule).build();
        EditScheduleCommand command = (EditScheduleCommand) parser.parseCommand(EditScheduleCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased() + " " + TITLE_DESC_ONE + DESC_DESC_ONE + DATE_DESC_ONE
                + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE);
        assertEquals(new EditScheduleCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_viewSchedule() throws Exception {
        ViewScheduleCommand command = (ViewScheduleCommand) parser.parseCommand(
                ViewScheduleCommand.COMMAND_WORD + " " + "2021-05-24");
        assertEquals(new ViewScheduleCommand(new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE)), command);
    }

    @Test
    public void parseCommand_listSchedules() throws Exception {
        assertTrue(parser.parseCommand(ListScheduleCommand.COMMAND_WORD) instanceof ListScheduleCommand);
        assertTrue(parser.parseCommand(ListScheduleCommand.COMMAND_WORD + " 3") instanceof ListScheduleCommand);
    }

    @Test
    public void parseCommand_addReminder() throws Exception {
        Reminder reminder = new ReminderBuilder().build();
        AddReminderCommand command = (AddReminderCommand) parser.parseCommand(
                AddReminderCommand.COMMAND_WORD + REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_ONE);
        assertEquals(new AddReminderCommand(reminder), command);
    }

    @Test
    public void parseCommand_deleteReminder() throws Exception {
        DeleteReminderCommand command = (DeleteReminderCommand) parser.parseCommand(
                DeleteReminderCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteReminderCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editReminder() throws Exception {
        Reminder reminder = new ReminderBuilder().build();
        EditReminderCommand.EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder(reminder).build();
        EditReminderCommand command = (EditReminderCommand) parser.parseCommand(EditReminderCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased() + " " + REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_ONE);
        assertEquals(new EditReminderCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_listReminders() throws Exception {
        assertTrue(parser.parseCommand(ListReminderCommand.COMMAND_WORD) instanceof ListReminderCommand);
        assertTrue(parser.parseCommand(ListReminderCommand.COMMAND_WORD + " 3") instanceof ListReminderCommand);
    }
}
