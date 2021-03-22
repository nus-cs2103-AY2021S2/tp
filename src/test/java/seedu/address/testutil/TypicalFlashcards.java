package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GENERAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FlashBack;
import seedu.address.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard PYTHAGOREAN = new FlashcardBuilder().withQuestion("Pythagorean theorem")
            .withPriority("Low").withCategory("Maths")
            .withAnswer("a^2 + b^2 = c^2").withRemark("")
            .withTags("Equation", "Common", "Easy").build();
    public static final Flashcard EINSTEIN = new FlashcardBuilder().withQuestion("Einstein's Equation")
            .withPriority("Mid")
            .withCategory("Physics").withAnswer("e = mc^2").withRemark("")
            .withTags("Equation", "Common", "Famous").build();
    public static final Flashcard NEWTON = new FlashcardBuilder().withQuestion("Newton's Second Law of Motion")
            .withAnswer("Force = Mass * Acceleration").withCategory("Physics").withPriority("Low")
            .withRemark("").withTags("Famous", "Common").build();
    public static final Flashcard ATP = new FlashcardBuilder().withQuestion("What is ATP?")
            .withAnswer("Adenosine Triphosphate").withCategory("Biology").withPriority("Low")
            .withTags("Acronym", "Biology").withRemark("").build();
    public static final Flashcard MERGE = new FlashcardBuilder()
            .withQuestion("What is the time complexity of merge sort?").withAnswer("O(nlogn)")
            .withCategory("Computer Science").withPriority("High").withRemark("")
            .withTags("Runtime", "Sort").build();
    public static final Flashcard RECURSION = new FlashcardBuilder().withQuestion("What is recursion?")
            .withAnswer("recursion").withCategory("Computer Science").withPriority("Low").withRemark("")
            .withTags("Random", "Fun").build();
    public static final Flashcard ACID = new FlashcardBuilder()
            .withQuestion("What is the formula for hydrochloric acid?").withAnswer("HCl")
            .withCategory("Chemistry").withPriority("High").withRemark("")
            .withTags("Formula", "Acid").build();

    // Manually added
    public static final Flashcard DARWIN = new FlashcardBuilder()
            .withQuestion("When did Charles Darwin stop believing in Christianity?")
            .withAnswer("After the Voyage of the Beagle")
            .withCategory("History").withPriority("Mid").withRemark("")
            .withTags("Darwin", "Religion").build();
    public static final Flashcard IDA = new FlashcardBuilder().withQuestion("What is no in German?").withAnswer("nein")
            .withCategory("German").withPriority("Low").withRemark("").withTags("Language", "Common").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard EINS = new FlashcardBuilder().withQuestion(VALID_QUESTION_EINSTEIN)
            .withAnswer(VALID_ANSWER_EINSTEIN).withCategory(VALID_CATEGORY_EINSTEIN)
            .withPriority(VALID_PRIORITY_EINSTEIN).withTags(VALID_TAG_GENERAL).withRemark("").build();
    public static final Flashcard AT = new FlashcardBuilder().withQuestion(VALID_QUESTION_OCTOPUS)
            .withAnswer(VALID_ANSWER_OCTOPUS).withCategory(VALID_CATEGORY_OCTOPUS)
            .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION, VALID_TAG_GENERAL)
            .withRemark("").build();

    public static final String KEYWORD_MATCHING_SCIENCE = "Science"; // A keyword that matches Science

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code FlashBack} with all the typical flashcards.
     */
    public static FlashBack getTypicalFlashBack() {
        FlashBack fb = new FlashBack();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            fb.addCard(flashcard);
        }
        return fb;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(PYTHAGOREAN, EINSTEIN, NEWTON, ATP, MERGE, RECURSION, ACID));
    }
}
