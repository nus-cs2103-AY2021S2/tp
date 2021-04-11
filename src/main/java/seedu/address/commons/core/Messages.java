package seedu.address.commons.core;

import seedu.address.logic.commands.ViewProjectCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_WELCOME = "Welcome to CoLAB!\nIf you are lost type \"help\" "
            + "in the command box below or press the help menu button near the top of your screen.";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid.";
    public static final String MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX = "The groupmate index provided is invalid.";
    public static final String MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX = "The project index provided is invalid.";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid.";
    public static final String MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX = "The deadline index provided is invalid.";
    public static final String MESSAGE_INVALID_TODO_DISPLAYED_INDEX = "The todo index provided is invalid.";

    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "Listing your contacts!";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "New event %1$s added to project %2$s";
    public static final String MESSAGE_ADD_TODO_SUCCESS = "New todo %1$s added to project %2$s";
    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "New deadline %1$s added to project %2$s";
    public static final String MESSAGE_ADD_GROUPMATE_SUCCESS = "New groupmate %1$s added to project %2$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in this project.";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in this project.";
    public static final String MESSAGE_DUPLICATE_DEADLINE = "This deadline already exists in this project.";
    public static final String MESSAGE_DUPLICATE_GROUPMATE = "This groupmate already exists in this project.";

    public static final String MESSAGE_DELETE_DEADLINE_SUCCESS = "Deleted deadline %1$s from project %2$s";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted event %1$s from project %2$s";
    public static final String MESSAGE_DELETE_TODO_SUCCESS = "Deleted todo %1$s from project %2$s";
    public static final String MESSAGE_DELETE_GROUPMATE_SUCCESS = "Deleted groupmate %1$s from project %2$s";

    public static final String MESSAGE_MARK_DEADLINE_SUCCESS = "Marked deadline as done: %1$s";
    public static final String MESSAGE_MARK_TODO_SUCCESS = "Marked todo as done: %1$s";

    public static final String MESSAGE_UI_PROJECT_NOT_DISPLAYED = "No project displayed. Display a project using the "
            + ViewProjectCommand.COMMAND_WORD + " command.";

    public static final String MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS =
            "Description can take any values, and it should not be blank";

    public static final String MESSAGE_PARSER_WEEKLY_CONSTRAINTS =
            "Repeat Weekly should be one of: 'Y', 'N', 'y' or 'n'";

    public static final String MESSAGE_PARSER_DATE_CONSTRAINTS =
                    "Date should be a valid date and in one of the following formats:\n"
                    + "24-11-2021\n"
                    + "24112021\n"
                    + "24/11/2021\n"
                    + "24.11.2021";

    public static final String MESSAGE_PARSER_TIME_CONSTRAINTS =
            "Time should be a valid time (from 00:00 to 23:59) and in one of the following formats:\n"
                    + "17:30\n"
                    + "1730";

    public static final String MESSAGE_NO_EVENTS_TO_DISPLAY_TODAY = "You have no events today!";
    public static final String MESSAGE_NO_DEADLINES_TO_DISPLAY_TODAY = "You have no deadlines today!";
    public static final String MESSAGE_NO_TODOS_TO_DISPLAY = "You have no todos in this project!";
    public static final String MESSAGE_NO_GROUPMATES_TO_DISPLAY = "You have no groupmates in this project!";
    public static final String MESSAGE_NO_DEADLINES_TO_DISPLAY = "You have no events in this project!";
    public static final String MESSAGE_NO_EVENTS_TO_DISPLAY = "You have no deadlines in this project!";

}
