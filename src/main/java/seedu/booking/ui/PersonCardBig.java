package seedu.booking.ui;

import seedu.booking.model.person.Person;

/**
 * An UI component that displays more detailed information of a {@code Person}.
 */
public class PersonCardBig extends PersonCard {

    private static final String FXML = "PersonListCardBig.fxml";

    /**
     * Creates a {@code PersonCardBig} with the given {@code Person} and index to display.
     */
    public PersonCardBig(Person person) {
        super(FXML, person, -1);
    }
}
