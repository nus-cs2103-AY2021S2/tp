package seedu.dictionote.model.contact;

import seedu.dictionote.model.note.Note;

/**
 * Represents a container for objects that represent some fields of a {@code mailto} link.
 * <p>
 * These {@code mailto} fields are:
 *     - {@code to} (the email's recipient): represented by a {@code Contact}.
 *     - {@code body} (the email's message body): represented by a {@code Note}.
 * Note that the {@code to} field is required, while the {@code body} field is optional.
 */
public class MailtoLink {
    private Contact to;
    private Note body;

    /**
     * Constructs a new {@code MailtoLink} object with the given contact as the recipient
     * of the to-be-composed email.
     *
     * @param to The contact whose email address represents the 'to' field in the email
     *           message.
     */
    public MailtoLink(Contact to) {
        setTo(to);
        // Body is optional; default value is null (i.e., the body is empty).
    }

    public MailtoLink setTo(Contact to) {
        assert to != null : "Receiving contact cannot be null.";
        this.to = to;
        return this; // returns itself to allow chaining.
    }

    public MailtoLink setBody(Note body) {
        this.body = body;
        return this; // (see setTo above)
    }

    public Contact getTo() {
        return to;
    }

    /**
     * Returns the current object as a valid {@code mailto} link string.
     * <p>
     * Possible formats:
     *     - {@code mailto:\<contact's email address\>} if no note is provided.
     *     - {@code mailto:\<contact's email address\>?body=\<note's contents\>} if a note is provided.
     *
     * @return A string formatted as a {@code mailto} link.
     */
    @Override
    public String toString() {
        String link = "mailto:" + to.getEmail();

        if (body != null) {
            link += "?body=" + body.getNote();
        }

        return link;
    }
}
