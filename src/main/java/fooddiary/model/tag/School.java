package fooddiary.model.tag;

import java.util.ArrayList;

public enum School {
    BIZ,
    CLB,
    DENT,
    FASS,
    FOE,
    MED,
    NUSS,
    PGP,
    SCIENCE,
    SDE,
    SOC,
    USC,
    UTOWN,
    UHALL,
    VENTUS,
    YIH,
    INVALID;

    private static ArrayList<String> schools = new ArrayList<>();

    /**
     * Checks if a String given fits any of the school name in School.
     *
     * @param input
     * @return boolean
     */
    public static boolean matches(String input) {
        if (schools.size() == 0) {
            for (School school : School.values()) {
                schools.add(school.name());
            }
        }
        return schools.contains(input.toUpperCase());
    }

    /**
     * Finds a School for user to save as,
     * if category is not found, classified as others.
     *
     * @param input
     * @return a School based on what user has input
     */
    public static School find(String input) {
        for (School school : School.values()) {
            if (school.name().toUpperCase().contains(input.toUpperCase())) {
                return school;
            }
        }
        return School.INVALID;
    }

    /**
     * Lists all Schools
     * @return list of school
     */
    public static String listAll() {
        StringBuilder list = new StringBuilder("");
        for (School school : School.values()) {
            if (!school.name().equals("INVALID")) {
                list.append(school.name());
                list.append("\n");
            }
        }
        return list.toString();
    }

}
