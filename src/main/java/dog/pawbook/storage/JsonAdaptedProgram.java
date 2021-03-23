package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.DateOfProgram;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.tag.Tag;
import javafx.util.Pair;

@JsonTypeName(Program.ENTITY_WORD)
public class JsonAdaptedProgram extends JsonAdaptedEntity {
    private final String dateOfProgram;
    private final List<Integer> dogs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given program details.
     */
    @JsonCreator
    public JsonAdaptedProgram(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
                            @JsonProperty("address") String dateOfProgram,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("dogs") List<Integer> dogs) {
        super(id, name, tagged);
        this.dateOfProgram = dateOfProgram;
        if (dogs != null) {
            this.dogs.addAll(dogs);
        }
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedProgram(Pair<Integer, Program> idProgramPair) {
        super(idProgramPair);
        Program source = idProgramPair.getValue();
        dateOfProgram = source.getDateOfProgram().value;
    }

    /**
     * Converts this Jackson-friendly adapted program object into the model's {@code Program} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    @Override
    public Pair<Integer, Entity> toModelType() throws IllegalValueException {
        CommonAttributes commonAttributes = checkAndGetCommonAttributes();
        final int modelID = commonAttributes.id;
        final Name modelName = commonAttributes.name;
        final Set<Tag> modelTags = commonAttributes.tags;
        if (dateOfProgram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DateOfProgram.class.getSimpleName()));
        }
        if (!DateOfProgram.isValidDate(dateOfProgram)) {
            throw new IllegalValueException(DateOfProgram.MESSAGE_CONSTRAINTS);
        }
        final DateOfProgram modelDop = new DateOfProgram(dateOfProgram);
        Program model = new Program(modelName, modelDop, modelTags);
        return new Pair<>(modelID, model);
    }
}
