package seedu.weeblingo.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.weeblingo.commons.exceptions.IllegalValueException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.Question;
import seedu.weeblingo.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedFlashcard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private final String question;
    private final String answer;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTag> userTagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFlashcard} with the given flashcard details.
     */
    @JsonCreator
    public JsonAdaptedFlashcard(@JsonProperty("question") String question, @JsonProperty("answer") String answer,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("user-tagged") List<JsonAdaptedTag> userTagged) {
        this.question = question;
        this.answer = answer;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (userTagged != null) {
            this.userTagged.addAll(userTagged);
        }
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedFlashcard(Flashcard source) {
        question = source.getQuestion().value;
        answer = source.getAnswer().value;
        tagged.addAll(source.getWeeblingoTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        userTagged.addAll(source.getUserTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted flashcard object into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Flashcard toModelType() throws IllegalValueException {
        final List<Tag> flashcardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            flashcardTags.add(tag.toModelType());
        }

        final List<Tag> flashcardUserTags = new ArrayList<>();
        for (JsonAdaptedTag userTag : userTagged) {
            flashcardUserTags.add(userTag.toModelType());
        }

        if (question == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName()));
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        final Question modelquestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }
        final Answer modelAnswer = new Answer(answer);

        final Set<Tag> modelTags = new HashSet<>(flashcardTags);

        final Set<Tag> modelUserTags = new HashSet<>(flashcardUserTags);

        return new Flashcard(modelquestion, modelAnswer, modelTags, modelUserTags);
    }

}
