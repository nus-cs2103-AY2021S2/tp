package seedu.address.model.grade;

public enum GradeEnum {
    A1("A1"), A2("A2"),
    B3("B3"), B4("B4"),
    C5("C5"), C6("C6"),
    D7("D7"), E8("E8"), F9("F9");

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should follow the Singapore-Cambridge GCE \"O\" level grading system: "
                    + "A1, A2, B3, B4, C5, C6, D7, E8, F9, all letters should be in upper case "
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
