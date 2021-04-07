package seedu.iscam.storage.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.model.client.InsurancePlan;

/**
 * Jackson-friendly version of {@link InsurancePlan}.
 */
class JsonAdaptedClientPlan {

    private final String planName;

    /**
     * Constructs a {@code JsonAdaptedPlan} with the given {@code planName}.
     */
    @JsonCreator
    public JsonAdaptedClientPlan(String planName) {
        this.planName = planName;
    }

    /**
     * Converts a given {@code InsurancePlan} into this class for Jackson use.
     */
    public JsonAdaptedClientPlan(InsurancePlan source) {
        planName = source.planName;
    }

    @JsonValue
    public String getPlanName() {
        return planName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code InsurancePlan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted planName.
     */
    public InsurancePlan toModelType() throws IllegalValueException {
        if (!InsurancePlan.isValidPlan(planName)) {
            throw new IllegalValueException(InsurancePlan.MESSAGE_CONSTRAINTS);
        }
        return new InsurancePlan(planName);
    }

}
