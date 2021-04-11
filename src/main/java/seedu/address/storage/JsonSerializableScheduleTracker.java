package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;

public class JsonSerializableScheduleTracker {

    public static final String MESSAGE_DUPLICATE_SCHEDULE = "Schedule list contains duplicate schedules(s).";
    public static final String MESSAGE_MISMATCH_DATE = "Schedule list contains schedule(s) "
            + "with mismatch TIME_FROM and TIME_TO date.";

    private final List<JsonAdaptedSchedule> schedules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableScheduleTracker} with the given schedules.
     */
    @JsonCreator
    public JsonSerializableScheduleTracker(@JsonProperty("schedules") List<JsonAdaptedSchedule> scheduleList) {
        this.schedules.addAll(scheduleList);
    }

    /**
     * Converts a given {@code ReadOnlyScheduleTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableScheduleTracker}.
     */
    public JsonSerializableScheduleTracker(ReadOnlyScheduleTracker source) {
        this.schedules.addAll(source.getScheduleList().stream()
                .map(JsonAdaptedSchedule::new).collect(Collectors.toList()));
    }


    /**
     * Converts this schedule tracker into the model's {@code ScheduleTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ScheduleTracker toModelType() throws IllegalValueException {
        ScheduleTracker scheduleTracker = new ScheduleTracker();
        for (JsonAdaptedSchedule jsonAdaptedSchedule : schedules) {
            Schedule schedule = jsonAdaptedSchedule.toModelType();
            if (scheduleTracker.hasSchedule(schedule)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCHEDULE);
            }

            if (!schedule.isSameDate()) {
                throw new IllegalValueException(MESSAGE_MISMATCH_DATE);
            }

            scheduleTracker.addSchedule(schedule);
        }
        return scheduleTracker;
    }
}
