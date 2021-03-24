package fooddiary.model.tag;

import java.util.ArrayList;

public enum School {
    SOC,
    FASS,
    BIZ,
    SCIENCE,
    FOE,
    UTOWN,
    NUSS,
    PGP,
    USC,
    CLB,
    UHALL,
    SDE,
    MED,
    DENT,
    INVALID;

    private static ArrayList<String> schools = new ArrayList<>();

    public static boolean matches(String input) {
        if (schools.size() == 0) {
            for (School school : School.values()) {
                schools.add(school.name().toLowerCase());
            }
        }
        return schools.contains(input.toLowerCase());
    }

    public static School find(String categoryInput) {
        for (School school : School.values()) {
            if (school.name().toUpperCase().contains(categoryInput.toUpperCase())) {
                return school;
            }
        }
        return School.INVALID;
    }

}
