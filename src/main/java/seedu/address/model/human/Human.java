package seedu.address.model.human;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Human. Abstract class to group shared variables between driver and person.
 */
public abstract class Human {
    protected final Name name;
    protected final Phone phone;


    /**
     * Every field must be present and not null.
     */
    public Human(Name name, Phone phone) {
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
