package seedu.address.model.person.functions;

import java.util.function.Function;

import seedu.address.model.person.Person;
import seedu.address.model.person.level.Level;

public class PersonLevelUpFunction implements Function<Person, Person> {
    public PersonLevelUpFunction() {}
    @Override
    public Person apply(Person p) {
        /*
        Optional<Level> newLevel;
        if (p.getLevel().isPresent()) {
            newLevel = Optional.of(Level.levelUp(p.getLevel().get()));
        } else {
            newLevel = Optional.empty();
        }
        return Level.changeLevel(p, newLevel);
         */
        Person newPerson = Level.clonePerson(p);
        newPerson.getLevel().ifPresent(x -> x.levelUp());
        return newPerson;
    }


}
