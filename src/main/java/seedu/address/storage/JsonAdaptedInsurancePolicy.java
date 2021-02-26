package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.insurancepolicy.InsurancePolicy;

/**
 * Jackson-friendly version of {@link InsurancePolicy}.
 */
class JsonAdaptedInsurancePolicy {

    private final String policyId;

    /**
     * Constructs a {@code JsonAdaptedInsurancePolicy} with the given {@code policyId}.
     */
    @JsonCreator
    public JsonAdaptedInsurancePolicy(String policyId) {
        this.policyId = policyId;
    }

    /**
     * Converts a given {@code InsurancePolicy} into this class for Jackson use.
     */
    public JsonAdaptedInsurancePolicy(InsurancePolicy source) {
        policyId = source.policyId;
    }

    @JsonValue
    public String getPolicyId() {
        return policyId;
    }

    /**
     * Converts this Jackson-friendly adapted InsurancePolicy object into the model's {@code InsurancePolicy} object.
     */
    public InsurancePolicy toModelType() {
        return new InsurancePolicy(policyId);
    }

}
