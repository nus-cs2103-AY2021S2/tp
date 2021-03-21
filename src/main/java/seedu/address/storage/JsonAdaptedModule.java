package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.plan.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {
    private String moduleTitle;
    private String moduleCode;
    private int moduleCredits;
    private String descriptions;
    private boolean isDone;
    private String grade;

    private List<JsonAdaptedModule> prerequisites = new ArrayList<>();
    private List<JsonAdaptedModule> preclusions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule}.
     */
    public JsonAdaptedModule(@JsonProperty("moduleTitle") String moduleTitle,
                             @JsonProperty("moduleCode") String moduleCode,
                             @JsonProperty("moduleCredits") int moduleCredits,
                             @JsonProperty("descriptions") String descriptions,
                             @JsonProperty("isDone") boolean isDone,
                             @JsonProperty("grade") String grade,
                             @JsonProperty("prerequisites") List<JsonAdaptedModule> prerequisites,
                             @JsonProperty("preclusions") List<JsonAdaptedModule> preclusions) {
        this.moduleTitle = moduleTitle;
        this.moduleCode = moduleCode;
        this.moduleCredits = moduleCredits;
        this.descriptions = descriptions;
        this.isDone = isDone;
        this.grade = grade;
        if (prerequisites != null) {
            this.prerequisites.addAll(prerequisites);
        }
        if (preclusions != null) {
            this.preclusions.addAll(preclusions);
        }
    }

    /**
     * Converts a given {@code module} into this class for Json use.
     */
    public JsonAdaptedModule(Module module) {
        this.moduleTitle = module.getModuleTitle();
        this.moduleCode = module.getModuleCode();
        this.moduleCredits = module.getMCs();
        this.descriptions = module.getDescriptions();
        this.isDone = module.isDone();
        this.grade = module.getGrade();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted plan.
     */
    public Module toModelType() throws IllegalValueException {
        Module module = new Module(moduleTitle, moduleCode, moduleCredits);
        if (!this.grade.equals("null")) {
            module.setGrade(this.grade);
        }
        return module;
    }
}
