### [Feature] Undo/redo

#### Implementation

The undo/redo mechanism is facilitated by `StateHistory` and `State`. It extends `PartyPlanet` with an undo/redo history, stored internally as an `ArrayList<State>` with a `currentStatePointer`, where a `State` stores the `AddressBook` and `EventBook` at any given point in time. Additionally, it implements the following operations:

* `StateHistory#addState()` —  Saves the current book state in its history.
* `StateHistory#previousState()` — Restores the previous state from its history.
* `StateHistory#nextState()` — Restores a previously undone state from its history.

These operations are exposed in the `Model` interface as `Model#addState()`, `Model#undo()` and `Model#redo()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `StateHistory` will be initialized with the initial address and event book state, and the `currentStatePointer` pointing to that single `State`.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#addState()`, causing the modified state of the address book after the `delete 5` command executes to be saved in a new `State`, which is stored in `StateHistory`, and the `currentStatePointer` is shifted to the newly inserted `State`.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add -n David` to add a new person. The `add` command also calls `Model#addState()`, causing another `State` to be saved into the `StateHistory`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#addState()`, so the state will not be saved into the `StateHistory`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undo()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous state, and restores the address and event books to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial state, then there are no previous states to restore. The `Model#undo()` command catches an `IndexOutOfBoundsException` thrown by `StateHistory#previousState()` if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redo()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address and event books to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at the last index, pointing to the latest state, then there are no undone states to restore. The `Model#redo()` command catches an `IndexOutOfBoundsException` thrown by `StateHistory#nextState()` if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#addState()`, `Model#undo()` or `Model#redo()`. Thus, the `StateHistory` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#addState()`. Since the `currentStatePointer` is not pointing at the end of the `StateHistory`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add -n David` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/AddStateActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address and event books.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.
