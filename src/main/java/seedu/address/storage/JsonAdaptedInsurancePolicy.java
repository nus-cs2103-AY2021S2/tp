package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.insurancepolicy.InsurancePolicy;

/**
 * Jackson-friendly version of {@link InsurancePolicy}.
 */
public class JsonAdaptedInsurancePolicy {

    private static final String NO_URL_JSON = "no_url";
    private static final String NO_URL_DISPLAY = "No URL!";

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
     * Converts a given {@code InsurancePolicy} into this class for Jackson use.
     */
    public JsonAdaptedInsurancePolicy(InsurancePolicy source) {
        policyId = source.policyId;
        policyUrl = source.getOptionalPolicyUrl().orElse(NO_URL_DISPLAY);
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
            return new String[] {policyIdAndUrl[0], NO_URL_DISPLAY};
        }

        policyIdAndUrl[1] = definePolicyUrl(policyIdAndUrl[1]);
        return policyIdAndUrl;
    }

    private static String definePolicyUrl(String urlIfPresent) {
        if (urlIfPresent.equals(NO_URL_JSON)) {
            return NO_URL_DISPLAY;
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
