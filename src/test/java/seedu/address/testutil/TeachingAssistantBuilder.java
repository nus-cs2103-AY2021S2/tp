package seedu.address.testutil;

import seedu.address.model.TeachingAssistant;
import seedu.address.model.contact.Contact;


/**
 * A utility class to help with building TeachingAssistant objects.
 * Example usage: <br>
 *     {@code TeachingAssistant ab = new TeachingAssistantBuilder().withContact("John", "Doe").build();}
 */
public class TeachingAssistantBuilder {

    private TeachingAssistant teachingAssistant;

    public TeachingAssistantBuilder() {
        teachingAssistant = new TeachingAssistant();
    }

    public TeachingAssistantBuilder(TeachingAssistant teachingAssistant) {
        this.teachingAssistant = teachingAssistant;
    }

    /**
     * Adds a new {@code Contact} to the {@code TeachingAssistant} that we are building.
     */
    public TeachingAssistantBuilder withContact(Contact contact) {
        teachingAssistant.addContact(contact);
        return this;
    }

    public TeachingAssistant build() {
        return teachingAssistant;
    }
}
