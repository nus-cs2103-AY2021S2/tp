package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.flashcard.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedFlashcard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private final String question;
    private final String answer;
    private final String category;
    private final String priority;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Statistics stats;

    /**
     * Constructs a {@code JsonAdaptedFlashcard} with the given flash card details.
     */
    @JsonCreator
    public JsonAdaptedFlashcard(@JsonProperty("question") String question, @JsonProperty("answer") String answer,
                                @JsonProperty("category") String category, @JsonProperty("priority") String priority,
                                @JsonProperty("remark") String remark,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                @JsonProperty("statistics") Statistics stats) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.priority = priority;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.stats = stats;
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedFlashcard(Flashcard source) {
        question = source.getQuestion().fullQuestion;
        answer = source.getAnswer().value;
        category = source.getCategory().value;
        priority = source.getPriority().value;
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        stats = source.getStats();
    }

    /**
     * Converts this Jackson-friendly adapted card object into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted card.
     */
    public Flashcard toModelType() throws IllegalValueException {
        final List<Tag> cardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            cardTags.add(tag.toModelType());
        }

        if (question == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Question.class.getSimpleName()));
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        final Question modelQuestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }
        final Answer modelAnswer = new Answer(answer);

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Category.class.getSimpleName()));
        }
        if (!Category.isValidCategory(category)) {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = new Category(category);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(cardTags);

        if (stats == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Statistics.class.getSimpleName()));
        }
        if (!Statistics.isValidStats(stats)) {
            throw new IllegalValueException(Statistics.MESSAGE_CONSTRAINT);
        }
        final Statistics modelStats = stats;


        return new Flashcard(modelQuestion, modelAnswer, modelCategory, modelPriority, modelRemark, modelTags, modelStats);
    }

}
