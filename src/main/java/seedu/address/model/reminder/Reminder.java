package seedu.address.model.reminder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;

public class Reminder {

    private final MeetingName meetingName;
    private final DateTime startDate;
    private final Priority priority;

    private Duration timeUntilStart;
    private Period calDaysUntilStart;


    public Reminder(MeetingName meetingName, DateTime startDate, Priority priority) {
        this.meetingName = meetingName;
        this.startDate = startDate;
        this.priority = priority;
        updateTimeAndDaysUntil();
    }

    public Reminder(Meeting meeting) {
        this.meetingName = meeting.getName();
        this.startDate = meeting.getStart();
        this.priority = meeting.getPriority();
        updateTimeAndDaysUntil();
    }

    public MeetingName getMeetingName() {
        return meetingName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void updateTimeAndDaysUntil() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime meetingTime = startDate.value;

        Duration timeUntil = Duration.between(currentTime, meetingTime);
        timeUntil = timeUntil.minusDays(timeUntil.toDaysPart());

        Period period = Period.between(currentTime.toLocalDate(), meetingTime.toLocalDate());
        this.timeUntilStart = timeUntil;
        this.calDaysUntilStart = period;
    }


    public String getTimeUntil() {
        String outString = String.format("%d years, %d months, %d days, %d hours, %d minutes, %d seconds",
                calDaysUntilStart.getYears(),
                calDaysUntilStart.getMonths(), calDaysUntilStart.getDays(), timeUntilStart.toHoursPart(),
                timeUntilStart.toMinutesPart(), timeUntilStart.toSecondsPart());
        return outString;
    }





}
