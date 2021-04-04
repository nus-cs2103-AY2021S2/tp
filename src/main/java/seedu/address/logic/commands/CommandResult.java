package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.CalendarDirection;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Calendar should be navigated.
     */
    private final boolean isCalendarNavigation;

    /**
     * Direction that calendar should be navigated.
     */
    private final CalendarDirection calendarDirection;

    /**
     * The application should exit.
     */
    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isCalendarNavigation, CalendarDirection calendarDirection,
            boolean isExit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isCalendarNavigation = isCalendarNavigation;

        boolean isCalendarNavigationNone = calendarDirection == CalendarDirection.NONE;
        assert !(isCalendarNavigation && isCalendarNavigationNone)
                : "Command result cannot be calendar navigation and have no calendar direction at the same time";

        this.calendarDirection = calendarDirection;
        this.isExit = isExit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, CalendarDirection.NONE, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isCalendarNavigation() {
        return isCalendarNavigation;
    }

    public CalendarDirection getCalendarDirection() {
        return calendarDirection;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isCalendarNavigation == otherCommandResult.isCalendarNavigation
                && calendarDirection == otherCommandResult.calendarDirection
                && isExit == otherCommandResult.isExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isCalendarNavigation, calendarDirection, isExit);
    }

}
