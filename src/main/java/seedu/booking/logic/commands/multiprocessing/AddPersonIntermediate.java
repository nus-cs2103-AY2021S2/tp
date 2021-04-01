package seedu.booking.logic.commands.multiprocessing;

import java.util.Set;

import seedu.booking.logic.commands.AddPersonCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;

public class AddPersonIntermediate implements Intermediate<AddPersonCommand> {

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;

    /**
     * Initialised a Person Intermediate to store temporary user input
     */
    public AddPersonIntermediate() {
        this.name = null;
        this.phone = null;
        this.email = null;
        this.tags = null;
    }

    public void setName(Name name) {
        this.name = name;
        System.out.println("Intermediate: " + this.name.toString());
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
        System.out.println("Intermediate: " + this.phone.toString());
    }

    public void setEmail(Email bookerEmail) {
        this.email = bookerEmail;
        System.out.println("Intermediate: " + this.email.toString());
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;

        final StringBuilder builder = new StringBuilder();
        if (!this.tags.isEmpty()) {
            this.tags.forEach(builder::append);
        }
        System.out.println("Intermediate: " + builder.toString());
    }

    /**
     * Creates a Person with the existing user input info
     */
    public Person createPerson() {
        return new Person(this.name, this.phone, this.email, this.tags);
    }

    @Override
    public AddPersonCommand parse() throws ParseException {
        Person person = new Person(this.name, this.phone, this.email, this.tags);
        return new AddPersonCommand(person);
    }
}
