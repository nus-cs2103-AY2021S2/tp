package seedu.address.model.person;


public class PersonIdPredicate extends PersonPredicate {
    private final PersonId personId;

    public PersonIdPredicate(PersonId personId) {
        this.personId = personId;
    }

    @Override
    public boolean test(Person person) {
        return person.getPersonId().equals(this.personId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonIdPredicate // instanceof handles nulls
                && personId.equals(((PersonIdPredicate) other).personId)); // state check
    }

    public PersonId getPersonId() {
        return this.personId;
    }
}
