package dog.pawbook.logic.commands;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.owner.Owner;

public class CommandUtil {
    /**
     * Remove a {@code dogId} from an Owner matching the given {@code ownerId} stored in the {@code model}.
     */
    static void disconnectFromOwner(Model model, int ownerId, int dogId) {
        assert model.hasEntity(ownerId) && model.getEntity(ownerId) instanceof Owner : "Owner ID is invalid!";

        Owner owner = (Owner) model.getEntity(ownerId);
        Set<Integer> newDogIdSet = new HashSet<>(owner.getDogIdSet());

        assert newDogIdSet.contains(dogId);

        newDogIdSet.remove(dogId);
        model.setEntity(ownerId, new Owner(owner.getName(), owner.getPhone(), owner.getEmail(),
                owner.getAddress(), owner.getTags(), newDogIdSet));
    }
}
