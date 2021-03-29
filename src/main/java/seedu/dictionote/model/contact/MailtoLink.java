package seedu.dictionote.model.contact;

import seedu.dictionote.model.note.Note;

public class MailtoLink {
    private Contact to;
    private Note body;

    public MailtoLink(Contact to) {
        setTo(to);
    }

    public MailtoLink setTo(Contact to) {
        this.to = to;
        return this;
    }

    public MailtoLink setBody(Note body) {
        this.body = body;
        return this;
    }

    public Contact getTo() {
        return to;
    }

    @Override
    public String toString() {
        String link = "mailto:" + to.getEmail();

        if (body != null) {
            link += "?body=" + body.getNote();
        }

        return link;
    }
}
