package seedu.weeblingo.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.Question;
import seedu.weeblingo.model.tag.Tag;
import seedu.weeblingo.storage.LocalDatabasePopulator;
/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "あ";
    public static final String DEFAULT_ANSWER = "a";
    public static final Tag DEFAULT_TAG = new Tag("gojuon");

    private Question question;
    private Answer answer;
    private Set<Tag> tags;
    private Set<Tag> userTags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = new HashSet<>();
        tags.add(DEFAULT_TAG);
        userTags = new HashSet<>();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        question = flashcardToCopy.getQuestion();
        answer = flashcardToCopy.getAnswer();
        tags = new HashSet<>(flashcardToCopy.getWeeblingoTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTags(String ... tags) {
        this.tags = LocalDatabasePopulator.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>}
     * and set it to the user tags of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withUserTags(String ... tags) {
        this.userTags = LocalDatabasePopulator.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code answer} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the {@code question} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, tags, userTags);
    }

}
