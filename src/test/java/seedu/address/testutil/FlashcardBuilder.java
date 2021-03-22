package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.flashcard.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "Pythagorean theorem";
    public static final String DEFAULT_ANSWER = "a^2 + b^2 = c^2";
    public static final String DEFAULT_CATEGORY = "Maths";
    public static final String DEFAULT_PRIORITY = "High";
    public static final String DEFAULT_REMARK = "";

    private Question question;
    private Answer answer;
    private Category category;
    private Priority priority;
    private Remark remark;
    private Set<Tag> tags;
    private Statistics stats;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        category = new Category(DEFAULT_CATEGORY);
        priority = new Priority(DEFAULT_PRIORITY);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        stats = new Statistics();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        question = flashcardToCopy.getQuestion();
        answer = flashcardToCopy.getAnswer();
        category = flashcardToCopy.getCategory();
        priority = flashcardToCopy.getPriority();
        remark = flashcardToCopy.getRemark();
        tags = new HashSet<>(flashcardToCopy.getTags());
        stats = flashcardToCopy.getStats();
    }

    /**
     * Sets the {@code Question} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withCategory(String category) {
        this.category = new Category(category);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Statistics} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withStats(int reviewCount, int correctCount) {
        this.stats = new Statistics(reviewCount, correctCount);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, category, priority, remark, tags, stats);
    }

}
