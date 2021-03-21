package seedu.module.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.model.ModuleBook;
import seedu.module.model.ModuleManager;
import seedu.module.model.ReadOnlyModuleBook;
import seedu.module.model.task.Task;

/**
 * An Immutable ModuleBook that is serializable to JSON format.
 */
@JsonRootName(value = "modulebook")
class JsonSerializableModuleBook {

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_MODULE_NOT_ALLOWED = "Module code is not allowed.";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleBook} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableModuleBook(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyModuleBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableModuleBook}.
     */
    public JsonSerializableModuleBook(ReadOnlyModuleBook source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module book into the model's {@code ModuleBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleBook toModelType() throws IllegalValueException {
        ModuleBook moduleBook = new ModuleBook();
        ModuleManager.initExistingModulesInStr();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (moduleBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            if (!ModuleManager.moduleIsValid(task.getModule().toString())) {
                throw new IllegalValueException(MESSAGE_MODULE_NOT_ALLOWED);
            }
            moduleBook.addTask(task);
        }
        return moduleBook;
    }

}
