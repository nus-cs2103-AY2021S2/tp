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
    public Flashcard(Question question, Answer answer, Set<Tag> weeblingoTags, Set<Tag> userTags) {
        requireAllNonNull(question, answer, weeblingoTags);
        this.question = question;
        this.answer = answer;
        this.weeblingoTags.addAll(weeblingoTags);
        this.userTags.addAll(userTags);
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
     * Returns a copy of the non-default tags set by the user.
     */
    public Set<Tag> getUserTags() {
        return new HashSet<>(userTags);
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
     * Returns true if this flashcard has all of the tags specified, be it in the weeblingo tags or user tags.
     * @param tags The specified tags to check for.
     */
    public boolean checkHasTags(Set<Tag> tags) {
        if (tags.isEmpty()) {
            return true;
        }
        boolean check = true;
        Set<Tag> weeblingoTags = getWeeblingoTags();
        Set<Tag> userTags = getUserTags();
        for (Tag tag : tags) {
            check = check && (weeblingoTags.contains(tag) || userTags.contains(tag));
        }
        return check;
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
                && otherFlashcard.getWeeblingoTags().equals(getWeeblingoTags())
                && otherFlashcard.getUserTags().equals(getUserTags());
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
