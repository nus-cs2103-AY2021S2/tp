package seedu.address.model.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DoctorMap {

    public static final Map<UUID, Doctor> DOCTOR_MAP = new HashMap<>();

    public static Map<UUID, Doctor> getDoctorMap() {
        return DOCTOR_MAP;
    }

    public static Doctor getDoctorFromUuid(UUID doctorUuid) {
        assert DOCTOR_MAP.containsKey(doctorUuid);
        return DOCTOR_MAP.get(doctorUuid);
    }

    /**
     * With the given {@code doctorList}, updates the values of {@code DOCTOR_HASH_MAP}
     * using the {@code Doctor}'s UUID as the key.
     */
    public static void updateDoctorHashMap(List<Doctor> doctorList) {
        for (Doctor dr: doctorList) {
            DOCTOR_MAP.put(dr.getUuid(), dr);
        }
    }
}
