package seedu.address.commons.util;

import static seedu.address.commons.core.Messages.MESSAGE_ADD_EDIT_COMMAND_ERROR;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_CLASH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_END_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LONG_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SHORT_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_MINUTES;
import static seedu.address.commons.core.Messages.MESSAGE_TIME_FROM_GREATER_THAN;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.event.Event;

/**
 * Helper functions for handling {@code Appointment} and {@code Schedule} datetime validation.
 */
public class DateTimeValidationUtil {

    /**
     * Checks if the {@code Event}'s datetime fulfills the constraints.
     */
    public static boolean validateDateTime(Model model, Event... events) throws CommandException {
        requireAllNonNull(model, events);

        Event toAdd;
        Optional<Event> optionalPreEditEvent;

        if (events.length == 1) {
            toAdd = events[0];
            optionalPreEditEvent = Optional.empty();
        } else if (events.length == 2) {
            toAdd = events[0];
            optionalPreEditEvent = Optional.of(events[1]);
        } else {
            throw new CommandException(MESSAGE_ADD_EDIT_COMMAND_ERROR);
        }

        assert toAdd != null;
        AppointmentDateTime timeFrom = toAdd.getTimeFrom();
        AppointmentDateTime timeTo = toAdd.getTimeTo();

        assert timeFrom != null && timeTo != null;

        /* TIME_FROM must be before TIME_NOW */
        if (toAdd.isInvalidTimeRange()) {
            throw new CommandException(MESSAGE_TIME_FROM_GREATER_THAN);
        }

        /* TIME_FROM and TIME_NOW must be in the future */
        if (toAdd.getTimeFrom().isBeforeNow() || toAdd.getTimeTo().isBeforeNow()) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        /* Start Time must be 6:00 AM onwards */
        if (timeFrom.isInvalidStartTime()) {
            throw new CommandException(MESSAGE_INVALID_START_TIME);
        }

        /* End Time must not be after 11:00 PM */
        if (timeTo.isInvalidEndTime()) {
            throw new CommandException(MESSAGE_INVALID_END_TIME);
        }

        double timeToHours = timeTo.toTime().getHour() + (timeTo.toTime().getMinute() / 60.0);
        double timeFromHours = timeFrom.toTime().getHour() + (timeFrom.toTime().getMinute() / 60.0);
        double timeDiff = timeToHours - timeFromHours;

        /* Time Slot must be at least 1 hour */
        if (timeDiff < 1) {
            throw new CommandException(MESSAGE_INVALID_SHORT_HOURS);
        }

        /* Time Slot must not exceed 8 hours */
        if (timeDiff > 8) {
            throw new CommandException(MESSAGE_INVALID_LONG_HOURS);
        }

        /* Time must be in 30 minutes or 60 minutes block */
        if (!timeFrom.isValidMinutes() || !timeTo.isValidMinutes()) {
            throw new CommandException(MESSAGE_INVALID_TIME_MINUTES);
        }

        /* The Event cannot clash with existing appointments & schedules */
        if (optionalPreEditEvent.isPresent()) {
            if (model.hasClashingDateTime(toAdd, optionalPreEditEvent.get())) {
                throw new CommandException(MESSAGE_DATE_CLASH);
            }
        } else {
            if (model.hasClashingDateTime(toAdd)) {
                throw new CommandException(MESSAGE_DATE_CLASH);
            }
        }
        return true;
    }

    /**
     * Checks if the {@code AppointmentDateTime} from JsonAdapted fulfills the constraints.
     * @throws IllegalValueException if there is an illegal value detected
     */
    public static boolean validateJsonAdaptedEvent(AppointmentDateTime timeFrom, AppointmentDateTime timeTo)
            throws IllegalValueException {
        /* TIME_FROM must be before TIME_NOW */
        if (!timeFrom.isBefore(timeTo)) {
            throw new IllegalValueException(MESSAGE_TIME_FROM_GREATER_THAN);
        }

        /* Start Time must be 6:00 AM onwards */
        if (timeFrom.isInvalidStartTime()) {
            throw new IllegalValueException(MESSAGE_INVALID_START_TIME);
        }

        /* End Time must not be after 11:00 PM */
        if (timeTo.isInvalidEndTime()) {
            throw new IllegalValueException(MESSAGE_INVALID_END_TIME);
        }

        double timeToHours = timeTo.toTime().getHour() + (timeTo.toTime().getMinute() / 60.0);
        double timeFromHours = timeFrom.toTime().getHour() + (timeFrom.toTime().getMinute() / 60.0);
        double timeDiff = timeToHours - timeFromHours;

        /* Time Slot must be at least 1 hour */
        if (timeDiff < 1) {
            throw new IllegalValueException(MESSAGE_INVALID_SHORT_HOURS);
        }

        /* Time Slot must not exceed 8 hours */
        if (timeDiff > 8) {
            throw new IllegalValueException(MESSAGE_INVALID_LONG_HOURS);
        }

        /* Time must be in 30 minutes or 60 minutes block */
        if (!timeFrom.isValidMinutes() || !timeTo.isValidMinutes()) {
            throw new IllegalValueException(MESSAGE_INVALID_TIME_MINUTES);
        }

        return true;
    }
}
