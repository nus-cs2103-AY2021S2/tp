package seedu.address.model.task;

import java.time.LocalDate;

import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;

public class AttributeManager {
    private final Date date;
    private final Description description;
    private final Duration duration;
    private final RecurringSchedule recurringSchedule;
    private final Status status;
    private final Title title;

    /**
     * AttributeManager provides access to the Task's attributes functionalities.
     *
     * @param task The task's attribute we are interested to access.
     */
    public AttributeManager(Task task) {
        this.date = task.getDate();
        this.description = task.getDescription();
        this.duration = task.getDuration();
        this.recurringSchedule = task.getRecurringSchedule();
        this.status = task.getStatus();
        this.title = task.getTitle();
    }

    public boolean dateOver() {
        return date.over();
    }

    /**
     * Checks if the Recurring schedule end date given by the user has expired.
     *
     * @return Boolean indicating if the end date is before the current system date.
     */
    public boolean hasExpired() {
        return recurringSchedule.isExpired();
    }

    /**
     * Checks if the Recurring schedule end date is valid considering leap years and months of 30, 31 as well
     *
     * @return Boolean if the end date in Recurring Schedule is valid
     */
    public boolean hasInvalidDateRange() {
        return recurringSchedule.isInvalidDateRange();
    }

    /**
     * Checks if the provided date has the same date as this task's date.
     *
     * @param date Date to check this task's date with.
     * @return Boolean indicating whether the provided date is on the same day as this task's date.
     */
    public boolean hasSameDate(Date date) {
        return this.date.value != null && this.date.equals(date);
    }

    /**
     * Checks if the provided date string is in the schedule of this task's recurring schedule.
     * Will return false if there is no dates in the schedule.
     *
     * @param dateString Date in string format, to check if it is in this task's schedule.
     * @return Boolean indicating if the provided date is in the schedule.
     */
    public boolean isOnRecurringScheduleDate(String dateString) {
        return this.recurringSchedule.isInRecurringSchedule(dateString);
    }

    /**
     * Checks if the Date attribute contains any data.
     *
     * @return true if the String of Date isEmpty, false otherwise.
     */
    public boolean isEmptyDate() {
        return date.isEmptyValue();
    }

    /**
     * Checks if the Duration attribute contains any data.
     *
     * @return true if the String of Duration isEmpty, false otherwise.
     */
    public boolean isEmptyDuration() {
        return duration.isEmptyValue();
    }

    /**
     * Checks if the Title attribute contains any data.
     *
     * @return true if the String of Title isEmpty, false otherwise.
     */
    public boolean isEmptyTitle() {
        return title.isEmptyValue();
    }

    /**
     * Checks if the RecurringSchedule attribute contains any data.
     *
     * @return true if the String of RecurringSchedule isEmpty, false otherwise.
     */
    public boolean isEmptyRecurringSchedule() {
        return recurringSchedule.isEmptyValue();
    }

    /**
     * Returns true if this task is already done.
     */
    public boolean isDone() {
        return status.isDone();
    }

    public boolean isWithinSevenDays(LocalDate currentDate) {
        return date.isWithinSevenDays(currentDate);
    }

    public String getTitleString() {
        return title.fullTitle;
    }

    /**
     * Get the LocalDate value of the Date attribute.
     *
     * @return LocalDate of the date object.
     */
    public LocalDate getDate() {
        return date.getDate();
    }
}
