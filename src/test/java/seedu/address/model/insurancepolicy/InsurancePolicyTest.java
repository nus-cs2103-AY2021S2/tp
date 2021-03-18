package seedu.address.model.insurancepolicy;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class InsurancePolicyTest {
    private static final String POLICY_ID_CONTAINS_CARROT = "Policy>1234>www.google.com";
    private static final String CORRECT_POLICY_WITH_URL = "Policy1234>www.google.com";
    private static final String CORRECT_POLICY = "Policy1234";
    private static final String DEFAULT_POLICY_ID = "P#123";
    private static final String DEFAULT_POLICY_URL = "www.aviva.com";

    @Test
    public void execute_checkPolicyIdWithCarrotValidity_false() {
        assertFalse(InsurancePolicy.isValidPolicyId(POLICY_ID_CONTAINS_CARROT));
    }

    @Test
    public void execute_checkCorrectPolicyValid_true() {
        assertTrue(InsurancePolicy.isValidPolicyId(CORRECT_POLICY));
    }

    @Test
    public void execute_checkCorrectPolicyWithUrl_true() {
        assertTrue(InsurancePolicy.isValidPolicyId(CORRECT_POLICY_WITH_URL));
    }

    @Test
    public void execute_checkEquals_success() {
        assertEquals(new InsurancePolicy(DEFAULT_POLICY_ID, DEFAULT_POLICY_URL),
                new InsurancePolicy(DEFAULT_POLICY_ID, DEFAULT_POLICY_URL));
    }

}
