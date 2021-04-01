package seedu.address.testutil;

import seedu.address.model.TutorBook;
import seedu.address.model.tutor.Tutor;

/**
 * A utility class to help with building TutorBook objects.
 * Example usage: <br>
 *     {@code TutorBook ab = new TutorBookBuilder().withTutor("John", "Doe").build();}
 */
public class TutorBookBuilder {

    private TutorBook tutorBook;

    public TutorBookBuilder() {
        tutorBook = new TutorBook();
    }

    public TutorBookBuilder(TutorBook tutorBook) {
        this.tutorBook = tutorBook;
    }

    /**
     * Adds a new {@code Tutor} to the {@code TutorBook} that we are building.
     */
    public TutorBookBuilder withPerson(Tutor tutor) {
        tutorBook.addTutor(tutor);
        return this;
    }

    public TutorBook build() {
        return tutorBook;
    }
}
