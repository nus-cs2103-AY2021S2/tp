package seedu.dictionote.model.contact;

import java.util.Objects;
import java.util.stream.Collectors;

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
     * Transforms the given string into a URI-compatible string by encoding all of its characters.
     * <p>
     * Each character in the string is transformed to its ASCII value in hexadecimal and prefixed
     * with a percent (%) sign.
     *
     * Examples:
     *     - " " becomes "%20".
     *     - "z" becomes "%7A".
     *
     * Refer to <a href="https://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a> for details.
     *
     * @param str The string to be encoded.
     * @return An encoded version of the given string that is compatible with URI links/objects.
     */
    static String encodeUriCompatible(String str) {
        assert str != null : "Given string cannot be null.";
        return str
                .chars()
                .mapToObj(Integer::toHexString)
                .map(s -> s.length() == 1 ? "0" + s : s)
                .map(s -> "%" + s)
                .collect(Collectors.joining());
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
            // Encode the contents of the note to ensure its validity as a URI string.
            link += "?body=" + encodeUriCompatible(body.getNote());
        }

        return link;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof MailtoLink
                && to.equals(((MailtoLink) obj).to)
                && Objects.equals(body, ((MailtoLink) obj).body)); // this.body may be null.
    }
}
