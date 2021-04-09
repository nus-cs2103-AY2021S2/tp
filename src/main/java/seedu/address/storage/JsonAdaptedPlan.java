package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.insurance.InsurancePlan;

/**
 * Jackson-friendly version of {@link InsurancePlan}.
 */
class JsonAdaptedPlan {

    private final String planString;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPlan(String planString) {
        this.planString = planString;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPlan(InsurancePlan source) {
        planString = source.original;
    }

    @JsonValue
    public String getPlanString() {
        return planString;
    }

    /**
     * Converts this Jackson-friendly adapted plan object into the model's {@code InsurancePlan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted plan.
     */
    public InsurancePlan toModelType() throws IllegalValueException {
        if (!InsurancePlan.isValidPlan(planString)) {
            throw new IllegalValueException(InsurancePlan.MESSAGE_CONSTRAINTS);
        }
        if (!InsurancePlan.isValidAmount(planString.split(" \\$", 2)[1].trim())) {
            throw new IllegalValueException(InsurancePlan.PREMIUM_CONSTRAINTS);
        }
        return new InsurancePlan(planString);
    }

}
