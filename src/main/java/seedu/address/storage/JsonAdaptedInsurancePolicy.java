package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.insurancepolicy.InsurancePolicy;

/**
 * Jackson-friendly version of {@link InsurancePolicy}.
 */
public class JsonAdaptedInsurancePolicy {

    private final String policyId;
    private final String policyUrl;

    /**
     * Constructs a {@code JsonAdaptedInsurancePolicy} with the given {@code policyId}.
     */
    @JsonCreator
    public JsonAdaptedInsurancePolicy(String policyAndUrlIfPresent) {
        String[] policyAndUrl = policyIdAndUrlParser(policyAndUrlIfPresent);
        this.policyId = policyAndUrl[0];
        this.policyUrl = policyAndUrl[1];
    }

    /**
     * Converts this a given {@code InsurancePolicy} into class for Jackson use.
     */
    public JsonAdaptedInsurancePolicy(InsurancePolicy source) {
        policyId = source.policyId;
        policyUrl = source.getPolicyUrlIfPresent().orElse(null);
    }

    /**
     * Parses raw input from JSON file and returns in a format usable by {@code InsurancePolicy} constructor.
     *
     * @param policyAndUrlIfPresent raw input form JSON file.
     * @return policy ID and policy URL (if any) stored in an array.
     */
    public static String[] policyIdAndUrlParser(String policyAndUrlIfPresent) {
        String[] policyIdAndUrl = policyAndUrlIfPresent.split(">", 2);

        if (policyIdAndUrl.length == 1) {
            return new String[] {policyIdAndUrl[0], null};
        }

        policyIdAndUrl[1] = definePolicyUrl(policyIdAndUrl[1]);
        return policyIdAndUrl;
    }

    private static String definePolicyUrl(String urlIfPresent) {
        if (urlIfPresent.equals("null")) {
            return null;
        }
        return urlIfPresent;
    }

    @JsonValue
    public String getPolicyInfo() {
        return policyId + ">" + policyUrl;
    }

    /**
     * Converts this Jackson-friendly adapted InsurancePolicy object into the model's {@code InsurancePolicy} object.
     */
    public InsurancePolicy toModelType() {
        return new InsurancePolicy(policyId, policyUrl);
    }

}
