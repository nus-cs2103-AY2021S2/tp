### [Feature] Autocompleting `Edit` and `EEdit` Command

Editing the details of `Person`s and `Event`s is a tedious job due to the user requiring to retype the majority of the detail for a small change.

The autocomplete feature allows the user to quickkly autocomplete details from the `Person` or `Event` according to the prefixes specified.

#### Implementation
* Syntax for Autocomplete: `{edit | eedit} INDEX [PREFIXES...]` + <kbd>`TAB`</kbd>
* The user is expected to keypress the <kbd>`TAB`</kbd> key after typing the command in order to activate the autocomplete feature.

Given below is an example usage scenario and how `Autocomplete` will work.

1. The user executes `edit 1 -r` + <kbd>`TAB`</kbd> command to autocomplete `Person` 1's Remark.

2. `UI` calls `autocomplete("edit 1 -r")` of `LogicManager` to handle the input.

3. `LogicManager` calls `parseCommand("edit 1 -r")` of `AutocompleteParser` to parse the input. This returns an `AutocompleteUtil`.

4. `LogicManage` calls `parse(model)` of `AutocompleteUtil` which processes the input and retrieves the relevant `Person`'s details from the `Model`.

5. `AutocompleteUtil` creates the autocompleted output String (`commandResult`) and returns it to `LogicManager`.

6. `LogicManger` returns the `commandResult` to `UI`.

7. `UI` updates `CommandBox` to reflect the `commandResult`.

Given below is the full Sequence Diagram for interactions for the `edit 1 -r` + <kbd>`TAB`</kbd> API call.

![Sequence Diagram for Autocomplete Edit Remark](images/AutocompleteSequenceDiagram.png)
