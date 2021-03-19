package seedu.address.model.flashcard;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a flash card in FlashBack.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Question question;
    private final Answer answer;
    private final Category category;

    // Data fields
    private final Priority priority;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private final Statistics stats;

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Question question, Answer answer, Category category, Priority priority,
                     Remark remark, Set<Tag> tags) {
        requireAllNonNull(question, answer, category, priority, tags);
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.priority = priority;
        this.remark = remark;
        this.tags.addAll(tags);
        this.stats = new Statistics();
    }

    public Flashcard(Question question, Answer answer, Category category, Priority priority,
                     Remark remark, Set<Tag> tags, Statistics stats) {
        requireAllNonNull(question, answer, category, priority, tags, stats);
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.priority = priority;
        this.remark = remark;
        this.tags.addAll(tags);
        this.stats = stats;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Category getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public Remark getRemark() {
        return remark;
    }

    public Statistics getStats() {
        return stats;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both cards have the same questions.
     * This defines a weaker notion of equality between two flash cards.
     */
    public boolean isSameCard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getQuestion().equals(getQuestion());
    }

    /**
     * Returns true if both cards have the same identity and data fields.
     * This defines a stronger notion of equality between two cards.
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
                && otherFlashcard.getCategory().equals(getCategory())
                && otherFlashcard.getPriority().equals(getPriority())
                && otherFlashcard.getTags().equals(getTags())
                && otherFlashcard.getRemark().equals(getRemark())
                && otherFlashcard.getStats().equals(getStats());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer, category, priority, tags, remark, stats);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(getQuestion())
                .append("\nAnswer: ")
                .append(getAnswer())
                .append("\nCategory: ")
                .append(getCategory())
                .append("\nPriority: ")
                .append(getPriority())
                .append("\nStatistics");
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
