package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.plan.Description;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Plan}.
 */
class JsonAdaptedPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Plan's %s field is missing!";
    private final String description;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedSemester> semesters = new ArrayList<>();
    private final boolean isMasterPlan;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given plan details.
     */
    @JsonCreator
    public JsonAdaptedPlan(@JsonProperty("description") String address,
                           @JsonProperty("isMasterPlan") boolean isMasterPlan,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("semesters") List<JsonAdaptedSemester> semesters) {
        this.description = address;
        this.isMasterPlan = isMasterPlan;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (semesters != null) {
            this.semesters.addAll(semesters);
        }
    }

    /**
     * Converts a given {@code Plan} into this class for Json use.
     */
    public JsonAdaptedPlan(Plan source) {
        description = source.getDescription().value;
        isMasterPlan = source.getIsMasterPlan();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        semesters.addAll(source.getSemesters().stream()
                .map(JsonAdaptedSemester::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted plan object into the model's {@code Plan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted plan.
     */
    public Plan toModelType() throws IllegalValueException {
        final List<Tag> planTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            planTags.add(tag.toModelType());
        }

        final List<Semester> planSemesters = new ArrayList<>();
        for (JsonAdaptedSemester sem : semesters) {
            planSemesters.add(sem.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        final Set<Tag> modelTags = new HashSet<>(planTags);
        Plan plan = new Plan(modelDescription, modelTags, planSemesters);
        plan.setMasterPlan(isMasterPlan);
        return plan;
    }
}
