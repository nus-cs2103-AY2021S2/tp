package seedu.address.model.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PatientMap {
    
    private static final Map<UUID, Patient> PATIENT_HASH_MAP = new HashMap<>();
    
    public static Map<UUID, Patient> getPatientHashMap() {
        return PATIENT_HASH_MAP;
    }

    public static Patient getPatientFromUuid(UUID patientUuid) {
        assert PATIENT_HASH_MAP.containsKey(patientUuid);
        return PATIENT_HASH_MAP.get(patientUuid);
    }

    /**
     * With the given {@code patientList}, updates the values of {@code PATIENT_HASH_MAP}
     * using the {@code Patient}'s UUID as the key.
     */
    public static void updatePatientHashMap(List<Patient> patientList) {
        for (Patient pt: patientList) {
            PATIENT_HASH_MAP.put(pt.getUuid(), pt);
        }
    }
}
