package seedu.address.model.human;

import seedu.address.model.human.person.Phone;

public abstract class Human {
    protected final Name name;
    protected final Phone phone;

    public Human(Name name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }
}
