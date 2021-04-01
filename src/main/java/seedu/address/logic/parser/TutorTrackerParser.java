package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.appointmentcommands.AddAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.DeleteAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.EditAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.FindAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.ListAppointmentCommand;
import seedu.address.logic.commands.appointmentcommands.ViewAppointmentCommand;
import seedu.address.logic.commands.budgetcommands.AddBudgetCommand;
import seedu.address.logic.commands.budgetcommands.DeleteBudgetCommand;
import seedu.address.logic.commands.budgetcommands.EditBudgetCommand;
import seedu.address.logic.commands.budgetcommands.ViewBudgetCommand;
import seedu.address.logic.commands.eventcommands.ViewEventCommand;
import seedu.address.logic.commands.eventcommands.ViewTimeTableCommand;
import seedu.address.logic.commands.favouritecommands.FavouriteCommand;
import seedu.address.logic.commands.favouritecommands.ListFavouriteCommand;
import seedu.address.logic.commands.favouritecommands.UnfavouriteCommand;
import seedu.address.logic.commands.filtercommands.AddAppointmentFilterCommand;
import seedu.address.logic.commands.filtercommands.AddPersonFilterCommand;
import seedu.address.logic.commands.filtercommands.DeleteAppointmentFilterCommand;
import seedu.address.logic.commands.filtercommands.DeletePersonFilterCommand;
import seedu.address.logic.commands.gradecommands.AddGradeCommand;
import seedu.address.logic.commands.gradecommands.DeleteGradeCommand;
import seedu.address.logic.commands.gradecommands.EditGradeCommand;
import seedu.address.logic.commands.gradecommands.ListGradeCommand;
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
import seedu.address.logic.commands.tutorcommands.AddNoteCommand;
import seedu.address.logic.commands.tutorcommands.DeleteCommand;
import seedu.address.logic.commands.tutorcommands.DeleteNoteCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.commands.tutorcommands.EditNoteCommand;
import seedu.address.logic.commands.tutorcommands.FindCommand;
import seedu.address.logic.commands.tutorcommands.ListCommand;
import seedu.address.logic.commands.tutorcommands.ListNoteCommand;
import seedu.address.logic.commands.tutorcommands.ViewCommand;
import seedu.address.logic.parser.appointmentparser.AddAppointmentCommandParser;
import seedu.address.logic.parser.appointmentparser.DeleteAppointmentCommandParser;
import seedu.address.logic.parser.appointmentparser.EditAppointmentCommandParser;
import seedu.address.logic.parser.appointmentparser.FindAppointmentCommandParser;
import seedu.address.logic.parser.appointmentparser.ViewAppointmentCommandParser;
import seedu.address.logic.parser.budgetparser.AddBudgetCommandParser;
import seedu.address.logic.parser.budgetparser.DeleteBudgetCommandParser;
import seedu.address.logic.parser.budgetparser.EditBudgetCommandParser;
import seedu.address.logic.parser.budgetparser.ViewBudgetCommandParser;
import seedu.address.logic.parser.eventparser.ViewEventCommandParser;
import seedu.address.logic.parser.eventparser.ViewTimeTableCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.favouriteparser.FavouriteCommandParser;
import seedu.address.logic.parser.favouriteparser.UnfavouriteCommandParser;
import seedu.address.logic.parser.filterparser.AddAppointmentFilterCommandParser;
import seedu.address.logic.parser.filterparser.AddPersonFilterCommandParser;
import seedu.address.logic.parser.filterparser.DeleteAppointmentFilterCommandParser;
import seedu.address.logic.parser.filterparser.DeletePersonFilterCommandParser;
import seedu.address.logic.parser.gradeparser.AddGradeCommandParser;
import seedu.address.logic.parser.gradeparser.DeleteGradeCommandParser;
import seedu.address.logic.parser.gradeparser.EditGradeCommandParser;
import seedu.address.logic.parser.reminderparser.AddReminderCommandParser;
import seedu.address.logic.parser.reminderparser.DeleteReminderCommandParser;
import seedu.address.logic.parser.reminderparser.EditReminderCommandParser;
import seedu.address.logic.parser.scheduleparser.AddScheduleCommandParser;
import seedu.address.logic.parser.scheduleparser.DeleteScheduleCommandParser;
import seedu.address.logic.parser.scheduleparser.EditScheduleCommandParser;
import seedu.address.logic.parser.scheduleparser.ViewScheduleCommandParser;
import seedu.address.logic.parser.tutorparser.AddCommandParser;
import seedu.address.logic.parser.tutorparser.AddNoteCommandParser;
import seedu.address.logic.parser.tutorparser.DeleteCommandParser;
import seedu.address.logic.parser.tutorparser.DeleteNoteCommandParser;
import seedu.address.logic.parser.tutorparser.EditCommandParser;
import seedu.address.logic.parser.tutorparser.EditNoteCommandParser;
import seedu.address.logic.parser.tutorparser.FindCommandParser;
import seedu.address.logic.parser.tutorparser.ViewCommandParser;

/**
 * Parses user input.
 */
public class TutorTrackerParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case EditNoteCommand.COMMAND_WORD:
            return new EditNoteCommandParser().parse(arguments);

        case ListNoteCommand.COMMAND_WORD:
            return new ListNoteCommand();

        case FavouriteCommand.COMMAND_WORD:
            return new FavouriteCommandParser().parse(arguments);

        case UnfavouriteCommand.COMMAND_WORD:
            return new UnfavouriteCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListFavouriteCommand.COMMAND_WORD:
            return new ListFavouriteCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        /* Appointment Commands */
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case FindAppointmentCommand.COMMAND_WORD:
            return new FindAppointmentCommandParser().parse(arguments);

        case ViewAppointmentCommand.COMMAND_WORD:
            return new ViewAppointmentCommandParser().parse(arguments);

        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();

        /* Grade Commands */
        case AddGradeCommand.COMMAND_WORD:
            return new AddGradeCommandParser().parse(arguments);

        case EditGradeCommand.COMMAND_WORD:
            return new EditGradeCommandParser().parse(arguments);

        case DeleteGradeCommand.COMMAND_WORD:
            return new DeleteGradeCommandParser().parse(arguments);

        case ListGradeCommand.COMMAND_WORD:
            return new ListGradeCommand();

        case AddPersonFilterCommand.COMMAND_WORD:
            return new AddPersonFilterCommandParser().parse(arguments);

        case DeletePersonFilterCommand.COMMAND_WORD:
            return new DeletePersonFilterCommandParser().parse(arguments);

        case AddAppointmentFilterCommand.COMMAND_WORD:
            return new AddAppointmentFilterCommandParser().parse(arguments);

        case DeleteAppointmentFilterCommand.COMMAND_WORD:
            return new DeleteAppointmentFilterCommandParser().parse(arguments);

        case AddBudgetCommand.COMMAND_WORD:
            return new AddBudgetCommandParser().parse(arguments);

        case EditBudgetCommand.COMMAND_WORD:
            return new EditBudgetCommandParser().parse(arguments);

        case DeleteBudgetCommand.COMMAND_WORD:
            return new DeleteBudgetCommandParser().parse(arguments);

        case ViewBudgetCommand.COMMAND_WORD:
            return new ViewBudgetCommandParser().parse(arguments);

        /* Schedule Commands */
        case AddScheduleCommand.COMMAND_WORD:
            return new AddScheduleCommandParser().parse(arguments);

        case EditScheduleCommand.COMMAND_WORD:
            return new EditScheduleCommandParser().parse(arguments);

        case DeleteScheduleCommand.COMMAND_WORD:
            return new DeleteScheduleCommandParser().parse(arguments);

        case ListScheduleCommand.COMMAND_WORD:
            return new ListScheduleCommand();

        case ViewScheduleCommand.COMMAND_WORD:
            return new ViewScheduleCommandParser().parse(arguments);

        case ViewTimeTableCommand.COMMAND_WORD:
            return new ViewTimeTableCommandParser().parse(arguments);

        case ViewEventCommand.COMMAND_WORD:
            return new ViewEventCommandParser().parse(arguments);

        /* Reminder Commands */
        case AddReminderCommand.COMMAND_WORD:
            return new AddReminderCommandParser().parse(arguments);

        case EditReminderCommand.COMMAND_WORD:
            return new EditReminderCommandParser().parse(arguments);

        case DeleteReminderCommand.COMMAND_WORD:
            return new DeleteReminderCommandParser().parse(arguments);

        case ListReminderCommand.COMMAND_WORD:
            return new ListReminderCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
