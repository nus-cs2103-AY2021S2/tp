package seedu.address.model.person;

import java.util.function.Predicate;

public class ModeOfContactPredicate implements Predicate<Person> {
    private final ModeOfContact modeOfContact;

    public ModeOfContactPredicate(ModeOfContact modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    @Override
    public boolean test(Person person) {
        return person.getModeOfContact().equals(modeOfContact);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModeOfContactPredicate // instanceof handles nulls
                && modeOfContact.equals(((ModeOfContactPredicate) other).modeOfContact)); // state check
    }
}
