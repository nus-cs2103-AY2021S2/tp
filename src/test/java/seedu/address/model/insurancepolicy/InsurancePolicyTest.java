package seedu.address.model.insurancepolicy;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class InsurancePolicyTest {
    private static final String DEFAULT_POLICY_ID = "P#123";
    private static final String DEFAULT_POLICY_URL = "www.aviva.com";

    @Test
    public void execute_checkEquals_success() {
        assertEquals(new InsurancePolicy(DEFAULT_POLICY_ID, DEFAULT_POLICY_URL),
                new InsurancePolicy(DEFAULT_POLICY_ID, DEFAULT_POLICY_URL));
    }

}
