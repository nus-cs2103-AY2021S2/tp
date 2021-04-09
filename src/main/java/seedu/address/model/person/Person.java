package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person. Abstract class to group shared variables between driver and passenger.
 */
public abstract class Person {
    protected final Name name;
    protected final Phone phone;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone) {
        requireAllNonNull(name, phone);
        this.name = name;
        this.phone = phone;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }
}
