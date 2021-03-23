package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;
import javafx.util.Pair;

@JsonTypeName(Program.ENTITY_WORD)
public class JsonAdaptedProgram extends JsonAdaptedEntity {
    private final List<Session> sessionList = new ArrayList<>();
    private final List<Integer> enrolledDogs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given program details.
     */
    @JsonCreator
    public JsonAdaptedProgram(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
                            @JsonProperty("sessionList") List<Session> sessionList,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("enrolledDogs") List<Integer> enrolledDogs) {
        super(id, name, tagged);
        if (enrolledDogs != null) {
            this.enrolledDogs.addAll(enrolledDogs);
        }
        if (sessionList != null) {
            this.sessionList.addAll(sessionList);
        }
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedProgram(Pair<Integer, Program> idProgramPair) {
        super(idProgramPair);
        Program source = idProgramPair.getValue();
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
        Set<Session> modelSession = new HashSet<>(sessionList);
        Program model = new Program(modelName, modelSession, modelTags);
        return new Pair<>(modelID, model);
    }
}
