package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleName;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.person.Task;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String moduleName;
    private final String moduleCode;
    private final String deadlineDate;
    private final String deadlineTime;
    private final String status;
    private final Integer weightage;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("moduleName") String moduleName,
                           @JsonProperty("moduleCode") String moduleCode,
                           @JsonProperty("deadlineDate") String deadlineDate,
                           @JsonProperty("deadlineTime") String deadlineTime,
                           @JsonProperty("status") String status,
                           @JsonProperty("weightage") Integer weightage,
                           @JsonProperty("remark") String remark,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.status = status;
        this.weightage = weightage;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        moduleName = source.getModuleName().fullName;
        moduleCode = source.getModuleCode().moduleCode;
        deadlineDate = source.getDeadlineDate().toString();
        deadlineTime = source.getDeadlineTime().toString();
        status = source.getStatus().toString();
        weightage = source.getWeightage().weightage;
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(moduleName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelModuleName = new ModuleName(moduleName);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (deadlineDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DeadlineDate.class.getSimpleName()));
        }
        if (!DeadlineDate.isValidDeadlineDate(deadlineDate)) {
            throw new IllegalValueException(DeadlineDate.MESSAGE_CONSTRAINTS);
        }
        final DeadlineDate modelDeadlineDate = new DeadlineDate(deadlineDate);

        if (deadlineTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DeadlineTime.class.getSimpleName()));
        }
        if (!DeadlineTime.isValidDeadlineTime(deadlineTime)) {
            throw new IllegalValueException(DeadlineTime.MESSAGE_CONSTRAINTS);
        }
        final DeadlineTime modelDeadlineTime = new DeadlineTime(deadlineTime);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        final Status modelStatus = new Status(status);

        if (weightage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Weightage.class.getSimpleName()));
        }
        if (!Weightage.isValidWeightage(weightage)) {
            throw new IllegalValueException(Weightage.MESSAGE_CONSTRAINTS);
        }
        final Weightage modelWeightage = new Weightage(weightage);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Task(modelModuleName, modelModuleCode, modelDeadlineDate,
                modelDeadlineTime, modelStatus, modelWeightage, modelRemark, modelTags);
    }

}
