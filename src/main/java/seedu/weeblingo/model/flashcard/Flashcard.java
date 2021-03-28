package seedu.weeblingo.model.flashcard;

import static seedu.weeblingo.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.weeblingo.model.tag.Tag;

/**
 * Represents a Flashcard in the answer book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Question question;

    // Data fields
    private final Answer answer;
    private final Set<Tag> weeblingoTags = new HashSet<>();
    private final Set<Tag> userTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Question question, Answer answer, Set<Tag> weeblingoTags) {
        requireAllNonNull(question, answer, weeblingoTags);
        this.question = question;
        this.answer = answer;
        this.weeblingoTags.addAll(weeblingoTags);
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getWeeblingoTags() {
        return Collections.unmodifiableSet(weeblingoTags);
    }

    /**
     * Returns true if both flashcards have the same question.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getQuestion().equals(getQuestion());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Flashcard)) {
            return false;
        }

        Flashcard otherFlashcard = (Flashcard) other;
        return otherFlashcard.getQuestion().equals(getQuestion())
                && otherFlashcard.getAnswer().equals(getAnswer())
                && otherFlashcard.getWeeblingoTags().equals(getWeeblingoTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer, weeblingoTags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; question: ")
                .append(getQuestion())
                .append("; answer: ")
                .append(getAnswer());

        Set<Tag> tags = getWeeblingoTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
