package seedu.address.commons.core;

import seedu.address.logic.commands.ViewProjectCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_WELCOME = "Welcome to CoLAB!\nIf you are lost type `help` in the "
            + "command box below or press the help menu button near the top of your screen.";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX = "The groupmate index provided is invalid";
    public static final String MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX = "The project index provided is invalid";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX = "The deadline index provided is invalid";
    public static final String MESSAGE_INVALID_TODO_DISPLAYED_INDEX = "The todo index provided is invalid";

    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_ADD_TODO_SUCCESS = "New todo added: %1$s";
    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "New deadline added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in this project.";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in this project.";
    public static final String MESSAGE_DUPLICATE_DEADLINE = "This deadline already exists in this project.";

    public static final String MESSAGE_DELETE_DEADLINE_SUCCESS = "Deleted Deadline: %1$s";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %1$s";
    public static final String MESSAGE_DELETE_TODO_SUCCESS = "Deleted Todo: %1$s";

    public static final String MESSAGE_MARK_DEADLINE_SUCCESS = "Marked Deadline as done: %1$s";
    public static final String MESSAGE_MARK_TODO_SUCCESS = "Marked Todo as done: %1$s";

    public static final String MESSAGE_UI_PROJECT_NOT_DISPLAYED = "No project displayed. Display a project using the "
            + ViewProjectCommand.COMMAND_WORD + " command.";

    public static final String MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String MESSAGE_PARSER_WEEKLY_CONSTRAINTS =
            "Repeat Weekly should be one of: 'Y', 'N', 'y' or 'n'";

    public static final String MESSAGE_PARSER_DATE_CONSTRAINTS =
                    "Date should be a valid date in one of the following formats:\n"
                    + "\t- dd-MM-yyyy\t(e.g. 01-01-2021)\n"
                    + "\t- ddMMyyyy\t(e.g. 01012021)\n"
                    + "\t- dd/MM/yyyy\t(e.g. 01/01/2021)\n"
                    + "\t- dd.MM.yyyy\t(e.g. 01.01.2021)";

    public static final String MESSAGE_PARSER_TIME_CONSTRAINTS =
            "Time should be a valid time in one of the following formats:\n"
                    + "\t- HH:mm\t(e.g. 17:30)\n"
                    + "\t- HHmm\t(e.g. 1730)";

    public static final String MESSAGE_NO_EVENTS_TO_DISPLAY_TODAY = "You have no events today!";
    public static final String MESSAGE_NO_DEADLINES_TO_DISPLAY_TODAY = "You have no deadlines today!";
    public static final String MESSAGE_NO_TODOS_TO_DISPLAY = "You have no todos in this project!";
    public static final String MESSAGE_NO_GROUPMATES_TO_DISPLAY = "You have no groupmates in this project!";
    public static final String MESSAGE_NO_DEADLINES_TO_DISPLAY = "You have no events in this project!";
    public static final String MESSAGE_NO_EVENTS_TO_DISPLAY = "You have no deadlines in this project!";

}
