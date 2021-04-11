package seedu.address.model.person;


public class PersonTypePredicate extends PersonPredicate {
    private final PersonType personType;

    public PersonTypePredicate(PersonType personType) {
        this.personType = personType;
    }

    @Override
    public boolean test(Person person) {
        return person.getPersonType().equals(this.personType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTypePredicate // instanceof handles nulls
                && personType.equals(((PersonTypePredicate) other).personType)); // state check
    }
    public PersonType getPersonType() {
        return this.personType;
    }
}
