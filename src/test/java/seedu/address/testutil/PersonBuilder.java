package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Answer;
import seedu.address.model.person.Question;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_QUESTION = "あ";
    public static final String DEFAULT_ANSWER = "a";

    private Phone phone;
    private Question question;
    private Answer answer;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        phone = new Phone(DEFAULT_PHONE);
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        phone = personToCopy.getPhone();
        question = personToCopy.getQuestion();
        answer = personToCopy.getAnswer();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code answer} of the {@code Person} that we are building.
     */
    public PersonBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code question} of the {@code Person} that we are building.
     */
    public PersonBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    public Person build() {
        return new Person(phone, question, answer, tags);
    }

}
