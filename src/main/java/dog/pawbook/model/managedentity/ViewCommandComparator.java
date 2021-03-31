package dog.pawbook.model.managedentity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

public class ViewCommandComparator implements Comparator<Pair<Integer, Entity>> {
    private final Map<Class<? extends Entity>, Integer> classValueMap = new HashMap<>();

    /**
     * Constructs a View Command Comparator.
     * @param targetEntityClass class of target entity.
     */
    public ViewCommandComparator(Class<? extends Entity> targetEntityClass) {
        classValueMap.put(targetEntityClass, 0);
        if (targetEntityClass.equals(Dog.class)) {
            classValueMap.put(Owner.class, 1);
            classValueMap.put(Program.class, 2);
        } else if (targetEntityClass.equals(Owner.class)) {
            classValueMap.put(Dog.class, 1);
            classValueMap.put(Program.class, 2);
        } else {
            classValueMap.put(Dog.class, 1);
            classValueMap.put(Owner.class, 2);
        }
    }

    @Override
    public int compare(Pair<Integer, Entity> firstPair, Pair<Integer, Entity> secondPair) {
        Class<? extends Entity> firstEntityClass = firstPair.getValue().getClass();
        Class<? extends Entity> secondEntityClass = secondPair.getValue().getClass();

        assert classValueMap.containsKey(firstEntityClass);
        assert classValueMap.containsKey(secondEntityClass);

        int firstEntityValue = classValueMap.get(firstEntityClass);
        int secondEntityValue = classValueMap.get(secondEntityClass);

        return firstEntityValue - secondEntityValue;
    }
}
