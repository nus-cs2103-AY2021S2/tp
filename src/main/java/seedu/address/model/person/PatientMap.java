package seedu.address.model.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PatientMap {

    private static final Map<UUID, Patient> PATIENT_MAP = new HashMap<>();

    public static Map<UUID, Patient> getPatientMap() {
        return PATIENT_MAP;
    }

    public static Patient getPatientFromUuid(UUID patientUuid) {
        assert PATIENT_MAP.containsKey(patientUuid);
        return PATIENT_MAP.get(patientUuid);
    }

    /**
     * With the given {@code patientList}, updates the values of {@code PATIENT_HASH_MAP}
     * using the {@code Patient}'s UUID as the key.
     */
    public static void updatePatientHashMap(List<Patient> patientList) {
        for (Patient pt: patientList) {
            PATIENT_MAP.put(pt.getUuid(), pt);
        }
    }
}
