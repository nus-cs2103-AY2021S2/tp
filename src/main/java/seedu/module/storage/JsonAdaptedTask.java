package seedu.module.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String startTime;
    private final String deadline;
    private final String module;
    private final String description;
    private final String workload;
    private final String doneStatus;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("startTime") String startTime,
            @JsonProperty("deadline") String deadline, @JsonProperty("module") String module,
            @JsonProperty("description") String description, @JsonProperty("workload") String workload,
            @JsonProperty("doneStatus") String doneStatus, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().fullName;
        if (!source.isDeadline()) {
            startTime = source.getStartTime().value;
        } else {
            startTime = "";
        }
        deadline = source.getDeadline().value;
        module = source.getModule().value;
        description = source.getDescription().value;
        workload = source.getWorkload().toString();
        doneStatus = source.getDoneStatus().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        Time modelStartTime;
        boolean isDeadLine;
        if (startTime == null || startTime.equals("")) {
            modelStartTime = null;
            isDeadLine = true;
        } else {
            if (!Time.isValidDeadline(startTime)) {
                throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
            }
            modelStartTime = new Time(startTime);
            isDeadLine = false;
        }

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Time.class.getSimpleName()));
        }
        if (!Time.isValidDeadline(deadline)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelDeadline = new Time(deadline);

        if (module == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }
        if (!Module.isValidModuleFormat(module)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        final Module modelModule = new Module(module);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (workload == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Workload.class.getSimpleName()));
        }
        if (!Workload.isValidWorkload(workload)) {
            throw new IllegalValueException(Workload.MESSAGE_CONSTRAINTS);
        }
        final Workload modelWorkload = new Workload(workload);

        if (doneStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DoneStatus.class.getSimpleName()));
        }
        if (!DoneStatus.isValidDoneStatus(doneStatus)) {
            throw new IllegalValueException(DoneStatus.MESSAGE_CONSTRAINTS);
        }

        final DoneStatus modelDoneStatus = new DoneStatus(doneStatus);

        final Set<Tag> modelTags = new HashSet<>(taskTags);

        if (isDeadLine) {
            return new Task(modelName, modelDeadline, modelModule, modelDescription,
                    modelWorkload, modelDoneStatus, modelTags);
        } else {
            return new Task(modelName, modelStartTime, modelDeadline, modelModule, modelDescription,
                    modelWorkload, modelDoneStatus, modelTags);
        }

    }

}
