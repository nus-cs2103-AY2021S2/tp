package seedu.address.model.insurancepolicy;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class InsurancePolicyTest {
    private static final String POLICY_ID_CONTAINS_ANGULAR_BRACKET = "Policy>1234>www.google.com";
    private static final String CORRECT_POLICY_WITH_URL = "Policy1234>www.google.com";
    private static final String CORRECT_POLICY_NO_URL = "Policy1234";
    private static final String DEFAULT_POLICY_ID = "P#123";
    private static final String DEFAULT_POLICY_URL = "www.aviva.com";
    private static final String COMPLEX_POLICY_URL_WITH_URL_PARAMS = "https://www.google.com/page/?q=query&b=bye";
    private static final String INVALID_URL = ")(%*!#)($@";

    @Test
    public void isValidPolicyId() {
        // valid input of Policy ID without URL
        assertTrue(InsurancePolicy.isValidPolicyId(CORRECT_POLICY_NO_URL));

        // valid input of Policy ID with URL
        assertTrue(InsurancePolicy.isValidPolicyId(CORRECT_POLICY_WITH_URL));

        // invalid input of Policy ID with angular bracket in the ID
        assertFalse(InsurancePolicy.isValidPolicyId(POLICY_ID_CONTAINS_ANGULAR_BRACKET));
    }

    public void isValidPolicyUrl() {
        // valid input of policy URL
        assertTrue(InsurancePolicy.isValidPolicyId(CORRECT_POLICY_NO_URL));

        // valid input if a complex policy URL
        assertTrue(InsurancePolicy.isValidPolicyId(COMPLEX_POLICY_URL_WITH_URL_PARAMS));

        // invalid input of policy URL
        assertFalse(InsurancePolicy.isValidPolicyId(INVALID_URL));
    }

    @Test
    public void execute_checkEquals_success() {
        assertEquals(new InsurancePolicy(DEFAULT_POLICY_ID, DEFAULT_POLICY_URL),
                new InsurancePolicy(DEFAULT_POLICY_ID, DEFAULT_POLICY_URL));
    }

}
