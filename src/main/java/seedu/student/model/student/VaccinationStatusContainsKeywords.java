package seedu.student.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Vaccination Status} matches the keyword given.
 */
public class VaccinationStatusContainsKeywords implements Predicate<Student> {
    private final String keyword;

    public VaccinationStatusContainsKeywords(String vaccinationStatus) {
        this.keyword = vaccinationStatus.toUpperCase();
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals((student.getVaccinationStatus().toString().toUpperCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccinationStatusContainsKeywords // instanceof handles nulls
                && keyword.equals(((VaccinationStatusContainsKeywords) other).keyword)); // state check
    }
}
