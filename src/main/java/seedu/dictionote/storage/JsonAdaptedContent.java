package seedu.dictionote.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.dictionary.Content;

/**
 * Jackson-friendly version of {@link Content}.
 */
class JsonAdaptedContent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Content's %s field is missing!";

    private final String week;
    private final String header;
    private final String maincontent;

    /**
     * Constructs a {@code JsonAdaptedContent} with the given person d.
     */
    @JsonCreator
    public JsonAdaptedContent(@JsonProperty("title") String week,
                              @JsonProperty("header") String header,
                              @JsonProperty("maincontent") String maincontent) {
        this.week = week;
        this.header = header;
        this.maincontent = maincontent;
    }

    /**
     * Converts a given {@code Content} into this class for Jackson use.
     */
    public JsonAdaptedContent(Content source) {
        week = source.getWeek();
        header = source.getHeader();
        maincontent = source.getMainContent();
    }

    /**
     * Converts this Jackson-friendly adapted object into the model's {@code Content} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted content.
     */
    public Content toModelType() throws IllegalValueException {
        if (week == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName()));
        }
        if (header == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName()));
        }
        if (maincontent == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName()));
        }
        return new Content(week, header, maincontent);
    }

}
