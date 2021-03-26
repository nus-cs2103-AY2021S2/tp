package seedu.address.model.grade;

public enum GradeEnum {
    A("A"), B("B"), C("C"), D("D"), E("E"),
    F("F"), S("S"), U("U");

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only be alphabetic letter from A to F, S and U, "
                    + "and it should not be blank";
    public final String gradeLetter;

    GradeEnum(String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    public String getGradeLetter() {
        return this.gradeLetter;
    }

    /**
     * Returns true if a given string is a valid grade letter.
     */
    public static boolean isValidGrade(String test) {
        for (GradeEnum gradeEnum : GradeEnum.values()) {
            if (gradeEnum.gradeLetter.equals(test)) {
                return true;
            }
        }
        return false;
    }
}
