package seedu.dictionote.model.contact;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.ContactBuilder;
import seedu.dictionote.testutil.NoteBuilder;

public class MailtoLinkTest {
    public static final Contact CONTACT = new ContactBuilder().withEmail("contact@email.com").build();
    public static final Note NOTE = new NoteBuilder().withNote("This is a note.").build();

    // Note that the mailto link constructor makes use of the #setTo(Contact) method;
    // thus, constructor tests cover that method in addition to the actual constructor.
    @Test
    public void construct_withNullContact_throwsAssertionError() {
        assertThrows(AssertionError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new MailtoLink(null);
            }
        });
    }

    @Test
    public void construct_withNonNullContact_success() {
        MailtoLink ml = new MailtoLink(CONTACT);
        assertNotNull(ml);
        assertEquals(ml, new MailtoLink(CONTACT));
    }

    // Note that for MailtoLink objects, a null body note means that the link has an empty body;
    // therefore, MailtoLink objects with null body notes are valid.
    @Test
    public void setBody_nullNote_success() {
        MailtoLink ml = new MailtoLink(CONTACT);
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                ml.setBody(null);
            }
        });
    }

    @Test
    public void setBody_nonNullNote_success() {
        MailtoLink ml = new MailtoLink(CONTACT);
        ml.setBody(NOTE);
    }

    @Test
    public void encoder_nullString_throwsAssertionError() {
        assertThrows(AssertionError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                MailtoLink.encodeUriCompatible(null);
            }
        });
    }

    @Test
    public void encoder_nonNullString_success() {
        // This string contains all URI-reserved characters, in addition to an uppercase letter, a lowercase letter,
        // and a space.
        String toBeEncoded = "Az ;/?:@&=+$,";
        String expectedEncodedString = "%41%7a%20%3b%2f%3f%3a%40%26%3d%2b%24%2c";
        assertEquals(MailtoLink.encodeUriCompatible(toBeEncoded), expectedEncodedString);
    }

    @Test
    public void toString_mailtoLinkWithoutBody_success() {
        String expectedMailtoLink = "mailto:" + CONTACT.getEmail().value;
        assertEquals(new MailtoLink(CONTACT).toString(), expectedMailtoLink);
    }

    @Test
    public void toString_mailtoLinkWithBody_success() {
        String expectedMailtoLink =
                "mailto:" + CONTACT.getEmail().value + "?body=" + MailtoLink.encodeUriCompatible(NOTE.getNote());
        assertEquals(new MailtoLink(CONTACT).setBody(NOTE).toString(), expectedMailtoLink);
    }
}
