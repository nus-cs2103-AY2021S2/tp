package seedu.address.testutil;

import seedu.address.model.name.Name;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;

public class TypicalClients {
    public static final Client CLIENT_ALICE = new Client(new Name("Alice"), new Contact("91234567"),
                            new Email("alice@gmail.com"), new AskingPrice("$800,000"));
    public static final Client CLIENT_BOB = new Client(new Name("Bob"), new Contact("91234567"),
            new Email("bob@yandex.com"), new AskingPrice("$2,000,000"));
    public static final Client CLIENT_EVE = new Client(new Name("Eve"), new Contact("91234567"),
            new Email("eve@hotmail.com"), new AskingPrice("$100,000"));
}
