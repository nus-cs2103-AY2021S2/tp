---
layout: page
title: A-Bash Book Developer Guide
navigation_title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
## **Introduction**

A-Bash Book (ABB) is a Command Line Interface (CLI) based Contact Management System built to address the growing demands 
of businesses with an increasing business ecosystem. Especially in a climate where large amounts of business information 
are being stored in various decentralised places. ABB acts as a centralised platform for users to store contact details 
of business partners or colleagues.

This guide serves to aid A-Bash Book (ABB) developers by describing the **Design**, **Architecture** and **Implementation**
behind each feature. **Design Considerations** can be found for certain features that would require extra attention when
modifying or enhancing the feature. **Future Enhancements** for some features can also indicate the future state of the feature.

Various **UML Diagrams** are used to help describe the feature flow as well as the associations between objects and classes, 
to ensure this guide serves as a utilitarian guide. 


## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the alias data.
* exposes an unmodifiable `ObservableList<CommandAlias>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-T12-3/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.
* can save the aliases data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### Filtering PersonCard

![Sequence Diagram of Filtering Display](images/FilterDisplaySequenceDiagram.png)

A new `DisplayFilterPredicate` is added in `Model`.

Executing a `FilterCommand` will trigger an update of the `DisplayFilterPredicate` that is stored in
`PersonListPanel`.

`PersonListView` will need to be re-drawn since certain UI elements will have its visibility updated.
Re-drawing of the `PersonListView` will re-create all the `PersonCard`, allowing it to show or hide
UI elements based on the `DisplayFilterPredicate`. This has to be done so that the dimension of the
hidden UI element will not be included during the layoutBounds calculations.

#### Design Considerations

##### Aspect: Implementation for predicate

* **Alternative 1 (current choice):** Store the predicate in `Model` and expose it
  to `UI (MainWindow)` via `Logic`
    * Pros: Separation of Concerns principle (SoC) is applied here to improve modularity.
      The `Model` deals with the creation of the predicate while the `UI (MainWindow)` retrieves the
      predicate from `Model` via `Logic`.
    * Cons: Will require new getter methods to the `Logic` and `Model` class as long as
* **Alternative 2:** Store the predicate as a global variable
    * Pros: Simple to implement as a global variable is accessible by both the UI components and
      Model.
    * Cons: Creates implicit links between code segments.

### Autocomplete

There are 3 autocomplete features currently implemented.

1) Commands
2) Flags
3) Index

#### Commands

##### Implementation

![Sequence Diagram of Autocomplete Commands](images/AutocompleteCommandsDiagram.png)

<div markdown="span" class="alert alert-info">:information_source:

Autocomplete Commands currently only implemented for `EDIT` and `DELETE`.

</div>

The current implementation consists of a Ui component called `AutoCompleteListPanel` which is made up of `AutocompleteCells`.
Each `AutoCompleteCell` contains a command word. Command words are retrieved by calling `LogicManager#getAutocompleteCommands()` and populated
by `MainWindow#fillInnerParts()`.

The method `CommandBox#setKeyUpCallback()` triggers `AutocompleteListPanel#updateList()` on every release of a key. This updates the existing command panel
with the correct filtered commands.
- The `setKeyUpCallback()` uses `addEventFilter()` and detects when a key is released before triggering the function to handle it.

Event filters are added to the root by `MainWindow` and the corresponding keys (<kbd>`TAB`</kbd>, <kbd>`UP`</kbd>, <kbd>`DOWN`</kbd>, <kbd>ENTER</kbd>)
are listened to.

- On `TAB` key release, `AutocompleteListPanel#processTabKey() ` will be called to handle the
  toggling between commands.

**[Expected Behaviour]**
![TabToggleCommand](images/TabToggleCommand.png)

#### Flags

##### Implementation

![Sequence Diagram of Autocomplete Flags](images/AutocompleteFlagsDiagram.png)

<div markdown="span" class="alert alert-info">:information_source:

Autocomplete Flags is currently only implemented for `ADD` and `EDIT` commands.

</div>

The current implementation consists of `MainWindow`, `CommandBox` and `LogicManager`.

`MainWindow` has an eventListener listening to the <kbd>Tab</kbd> key. `LogicManager#getAvailableFlags()` is called to 
attempt to get the available flags for the command.

`LogicManager#getAvailableFlags()` returns null if command is incomplete. Instead, <kbd>Tab</kbd> key results in
`Command Autocompletion`.

`LogicManager#getAvailableFlags()` checks if the supplied command string is supported by `Flag Autocompletion`. 
`LogicManager#filterExistingCommands()` is called to determine which flags are already in the `command string`.
This is important as this check is required everytime <kbd>Tab</kbd> key is pressed.

When `LogicManager#getAvailableFlags()` returns a populated list of strings, it continues to check the last tag in
`command string`. If the flag has no content, that flag will be replaced by `CommandBox#setTextvalue()`. If the flag has
content, the next available flag will be appended by `CommandBox#setAndAppendFlag()`.

#### Index

![Sequence Diagram of Autocomplete Index](images/AutocompleteIndexDiagram.png)

<div markdown="span" class="alert alert-info">:information_source:

Autocomplete Index is currently only implemented for `EDIT` and `DELETE` commands.

</div>

The current implementation consists of `MainWindow`, `PersonListPanel`, `CommandBox`.

On <kbd>Up/Down</kbd> key press, the `PersonListPanel#selectNext()` and `PersonListPanel#selectPrev()` methods which will be called 
to handle the toggling between contacts. Then the `CommandBox#setAndAppendIndex()` method appends the index to the
existing `command string` in `CommandBox`.

[Expected Behaviour]
![UpDownToggleCommand](images/UpDownToggleContact.png)

### Future Enhancements

Autocomplete commands could include alias commands.

### Design Considerations

Initially, autocomplete flags were untoggleable, and would just append to the `command string` regardless of content. 
This was originally implemented to allow users to, but to maintain consistency in the future. Consider keeping similar
autocomplete functionalities to be toggleable.

When adding new flags, `LogicManager#getAutocompleteFlags()` should be updated with the new flags.
When adding new commands, `LogicManager#getAutocompleteCommands()` should be updated with the new commands.
When adding autocomplete flag support for new commands, `LogicManager#isAutocompleteFlag()` and 
`LogicManager#getAvailableFlags()` should add the new commands.


### Remark

The current implementation is such that `Remark` is added as an attribute of the `Person` class. `Remark` is intended 
as a way to allow users to add any kind of comment about a specified contact, and therefore does not require any 
validity check (an empty remark is also valid). Accordingly, `Remark` is an optional field that can be specified when 
adding/editing a contact. When editing a person's `Remark` and no value is provided, said person's `Remark` will be set 
as empty.

![EditRemarkActivityDiagram](images/EditRemarkActivityDiagram.png)

Initially, an alternative implementation was considered: to introduce a new `Remark` command which would be used to add
remarks to a contact. However the current implementation is used instead, in favour of consistency. `Remark` is 
after all an attribute of a `Person`. No other such attribute has its own dedicated command. As such, Remark is 
ultimately implemented as a field to the `add`/`edit` commands, which is consistent with all the other Person 
attributes.

### Find

The current implementation of the `find` command only searches the name, email, remark and tag fields. Potential
improvements of the feature is to search all fields including phone number, address, company, and job title.

The implementation of general search is via a `FieldsContainsKeywordsPredicate` predicate class. This predicate simply
propagate the keywords down to each individual predicate. Its Test function is basically the boolean or of all the
individual specific field predicate's test function.

Below is the class diagram for the entire Find command
![FindCommandPredicateDiagram](images/FindPredicateClassDiagram.png)


### Fuzzy Find

The current implementation of `find` command uses the Java port of Python's 
[fuzzywuzzy algorithm](https://github.com/xdrop/fuzzywuzzy). Current implementation matches using partial match of more
than 60% similarity.

#### Considerations

Key requirements for fuzzy search is the following

- 3 character name matches 
  - Fuzzy matching should support 3 character names with delta of 1 character
    - `Eva` and `Iva` - 66% similarity
    - `Tim` and `Tom` - 66% similarity
- Partial name matches
  - Name matching should support partial matches where shortened nicknames are used
    - `Ben` and `Benjamin`
    - `Sam` and `Samantha`
    - `Jon` and `Jonathan`
- String matching should be one way
  - Queries should match with data and not the other way round
    - `Tom` should partially match with `Thomas`
    - `Thomas` should not partially match with `Tom`
    
#### Side Effects and Missed Matches

Due to the above considerations, partial matching is chosen for partial name matches and 60% threshold is chosen for 3 
character names. However, side effects occur with these design choices.

- Middle of name matches
  - `Sam` matches with `Benjamin` with 66% partial similarity due to `jam` in `benjamin` being 1 character delta from 
    `sam`
    
Additionally, there are also some missed out features

- Phonetically similar name matches
  - `Shawn` doesnt match with `Sean` due to it below the 60% simiarity threshold
    
#### Potential changes

In the future, a combination of full word and partial matches can be used with weighted metrics to avoid middle of name
matches. To avoid both issue, string fuzzy search may not be sufficient. Levenshtein distance is not able to account for
phonetic differences in names and expected result when doing name searches.

### Alias feature

Allows the user to create shortcut command (also known as command alias) to the actual command in 
`alias { add | delete | list } [ALIAS] [COMMAND]` format. The `ALIAS` must be one word and cannot be an existing command, 
while the `COMMAND` must be a valid existing command.

#### Implementation

The `AliasCommand` is split into three sub-commands `AddAliasCommand`, `DeleteAliasCommand` and `ListAliasCommand`.
Supporting these classes are the `AliasCommandParser`, `AddAliasCommandParser`, `DeleteAliasCommandParser` and 
`ListAliasCommandParser` which helps to parse user input into their respective alias sub-commands.

![AliasCommandClassDiagram](images/AliasCommandClassDiagram.png)

![AliasCommandParserClassDiagram](images/AliasCommandParserClassDiagram.png)

Step 1. The user input will be parsed through the `AddressBookParser` which will then pass the user input to the
`AliasCommandParser` when it checks that the user input is trying to execute an alias command.

Step 2. The user input will be parsed through the `AliasCommandParser` which will then pass the user input to either 
`AddAliasCommandParser`, `DeleteAliasCommandParser` or `ListAliasCommandParser` after it checks which alias sub-command 
the user input is trying to execute.

Step 3. The user input will be parsed through the `AddAliasCommandParser`, `DeleteAliasCommandParser` or 
`ListAliasCommandParser` and the respective `Parser` will check if the user input is valid.
* `ALIAS` must be one word and not an existing command
* `COMMAND` must be a valid existing command.

Step 4. Once the user input is successfully parsed, a `AddAliasCommand`, `DeleteAliasCommand` or `ListAliasCommand`
will be initialised and returned from their respective `Parser` classes and executed subsequently.

![AliasCommandSequenceDiagram](images/AliasCommandSequenceDiagram.png)

![AddAliasCommandParserSequenceDiagram](images/AddAliasCommandParserSequenceDiagram.png)

![DeleteAliasCommandParserSequenceDiagram](images/DeleteAliasCommandParserSequenceDiagram.png)

Notes: 
* `AddAliasCommand` will check if alias exists in `model` before adding as duplicate alias is not allowed.
* `DeleteAliasCommand` will check if alias exists in `model` before deleting as alias must exist for it to be deleted.

#### Design Considerations

##### Aspect: Implementation for `alias` command

* **Alternative 1 (current choice)**: Create a separate `AliasCommand` with sub-commands
    * Pros: `AliasCommand` will be independent from `AddCommand`. Easier to implement, test and debug.
    * Cons: `alias add` compared to `add alias` might be less intuitive for users.
* **Alternative 2**: Implement in `AddCommand` with `alias` as a sub-command of `add`. e.g. `add alias`.
    * Pros: `add alias` compared to `alias add` might be more intuitive for users.
    * Cons: Will require huge changes to `AddCommand`. `AddCommand` will require more testing and debugging.
    
### Tag feature

Allows the user to create and delete one or more `tag` from one or more person in 
`tag { add | delete | INDEX... } -t TAG...` format. Tags are case-insensitive, therefore `Photoshop` and `photoshop` are
treated as the same tag. There must be at one index and one tag for the command to be valid.

#### Implementation

The `TagCommand` is split into two sub-commands `AddTagCommand` and `DeleteTagCommand`. Supporting these classes are the 
`TagCommandParser`, `AddTagCommandParser` and `DeleteTagCommandParser` which helps to parse user input into their 
respective tag sub-commands.

![TagCommandClassDiagram](images/TagCommandClassDiagram.png)

![TagCommandParserClassDiagram](images/TagCommandParserClassDiagram.png)

Step 1. The user input will be parsed through the `AddressBookParser` which will then pass the user input to the
`TagCommandParser` when it checks that the user input is trying to execute a tag command.

Step 2. The user input will be parsed through the `TagCommandParser` which will then pass the user input to either 
`AddTagCommandParser` or `DeleteTagCommandParser` after it checks which tag sub-command the user input is trying to 
execute.

Step 3. The user input will be parsed through the `AddTagCommandParser` or `DeleteTagCommandParser` and the respective 
`Parser` will check if the user input is valid.
* The index argument can only be `shown`, `selected` or `INDEX...`.
* `INDEX...` must be valid positive integers.
* `-t TAG...` must be valid tags which are alphanumeric.

Step 4. Once the user input is successfully parsed, a `AddTagCommand` or `DeleteTagCommand` will be initialised and 
returned from their respective `Parser` classes and executed subsequently.

![TagCommandSequenceDiagram](images/TagCommandSequenceDiagram.png)

![AddTagCommandParserSequenceDiagram](images/AddTagCommandParserSequenceDiagram.png)

![DeleteTagCommandParserSequenceDiagram](images/DeleteTagCommandParserSequenceDiagram.png)

Notes:
* Tags are stored in a `HashSet` in `Person` class.
* `tag add` command can be executed successfully even if the persons already have the tags. The tags will just not be 
  added by the `HashSet` due to the property of `HashSet`.
* `tag delete` command can be executed successfully even if the persons does not have the tags. The tags will just not 
  be deleted by the `HashSet` due to the property of `HashSet`.

#### Design Considerations

##### Aspect: Command result for `tag` command

* **Alternative 1 (current choice)**: Command results will show how many persons the command has been executed on, but 
  not the actual number of persons which tags are added to or deleted from.
    * Pros: Easy to implement, test and debug. The goal of the command will still be achieved even when the tags are not
      added or deleted, e.g. a `delete tag` command deleting `Photoshop` tag from a person without the tag will still
      result in the person without the tag.
    * Cons: Command results does not reflect the exact number of persons tags are added to or deleted from when the 
      command is executed. An additional note in the command result will be required to warn users of this behaviour.
* **Alternative 2**: Command results will show exactly the number of persons tags are added to or deleted from.
    * Pros: Command results are clearer for users as it will reflect the exact number of persons tags are added to or
      deleted from when the command is executed.
    * Cons: Will require many checks to show the exact number of persons modified, and it gets even more complicated
      when adding multiple tags to multiple persons or deleting multiple tags from multiple persons.
      
### Selecting Persons

SelectCommand allows a user to select Person object(s) to apply actions on.

#### Overview of Implementation

{% include image.html url="images/SelectCommandClassDiagram.png" description="Select class diagram" %}
{% include image.html url="images/SelectCommandParserSequenceDiagram.png" description="Select parser sequence diagram" %}
{% include image.html url="images/SelectCommandParserSequenceDiagram_ref.png" description="Select parser sequence diagram ref" %}

#### Implementation

`ModelManager` contains a list of `Person` object which are selected by the user.
When `SelectShowCommand` is called, a predicate will be applied onto the `filteredPersonList` to show
only the selected list of `Person` objects. The application of predicate follows the same method as
`FindCommand` and `ListCommand`.

#### Design Considerations

##### Aspect: Implementation for `select` command

* Alternative 1 (current choice): Use a separate `List<Person>` to store the selected person
  objects.
    * Pros: Simple implementation with Separation of Concerns (SoC) principle applied as the `Model`
      stores the selected persons while `UI` retrieves a predicate to act upon (which will be
      updated everytime the user makes a new selection).
    * Cons: Model will have to ensure that after a person object is deleted, the object reference
      has to be deleted.
* Alternative 2: Use a global static `List<Person>` to store the selected person objects.
    * Pros: Simple to implement and access by both the `UI` and `Model` components.
    * Cons: Create implicit links between code segments.

### Email Person(s)

The email command allows the user to open the operating system's email client with the 'to' field
filled up with the email of contacts.

#### Implementation

The current implementation consists of using
the ['mailto' URI scheme](https://tools.ietf.org/html/rfc6068) to trigger the operating system's
email client.

#### Design Considerations

##### Aspect: Implementation for `email` command

* Alternative 1 (current choice): Use 'mailto' URI scheme
    * Pros: Simple implementation as the operating system will be in charge of resolving and opening
      the email client.
    * Cons: Impossible to determine if the operating system has opened the email client
      successfully.

No other possible alternatives as it would be overly complex at this point in time.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Users who need to manage a significant number of professional contacts.
* Users who prefer desktop apps over other types.
* Users who can type fast.
* Users who prefers typing to mouse interactions.
* Users who is reasonably comfortable using CLI apps.
* Users who prefer a Bash-like experience.

**Value proposition**: 
* Manage contacts faster than a typical mouse/GUI driven app, via keyboard commands.
* Enter commands at ease with convenience features such as auto-complete.
* Simultaneously manage several contacts with bulk actions such as select.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority     | As a …​                                 | I want to …​                                             | So that I can…​                                                              |
| --------     | ------------------------------------------ | ------------------------------------------------------------| --------------------------------------------------------------------------------|
| `* * *`      | new user                                   | see usage instructions                                      | refer to instructions when I forget how to use the App                          |
| `* * *`      | user                                       | add a new person                                            |                                                                                 |
| `* * *`      | user                                       | delete a person                                             | remove entries that I no longer need                                            |
| `* * *`      | user                                       | find a person by name                                       | locate details of persons without having to go through the entire list          |
| __`* * *`__  | user                                       | find my friends via their email address                     | find my friends easily                                                          |
| __`* * *`__  | user                                       | autocomplete my commands                                    | minimise the amount of typing for a command                                     |
| __`* * *`__  | user                                       | set my own commands alias                                   | type and execute commands faster                                                |    
| __`* * *`__    | experienced Bash user                      | use the app with Bash-like commands and options format    | work smoothly with a highly familiar and intuitive user experience              |
| __`* *`__    | user                                       | find my friends without typing their exact full name        | find my friends easily                                                          |
| __`* *`__    | user                                       | find my friends that have names with similar spelling easily| find my friends easily                                                          |
| __`* *`__    | user                                       | add remarks to my contacts                                  | easily keep track of information/comments regarding a specific contact          |
| __`* *`__    | user                                       | hide private contact details                                | minimize chance of someone else seeing them by accident                         |
| `*`          | user with many persons in the address book | sort persons by name                                        | locate a person easily                                                          |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use Case: Delete a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  AddressBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

* *a. At any time, User can press tab to autocomplete the field if possible
    * *a1. User confirm suggestion by continuing his command
    * *a2. User rejects suggestion by deleting the suggestion
     
      Use case ends.

**Use case: Setting a command alias**

**MSS**

1. User frequently uses a command
2. User sets a command alias for frequent command
3. AddressBook adds alias to existing command list

   Use case ends.

**Extensions**

* 2a. The alias name or command name is empty 
  
    Use case ends.
  

* 2b. The command name is empty
  
    Use case ends.


**Use case: Edit remarks of an existing contact**

**MSS**

1. User wants to edit the remarks of a specific contact
2. User provides a new remark to the specified contact
3. AddressBook updates the existing contact to have the specified remark

   Use case ends.

**Extensions**

* 2a. The provided remark is empty
    * 2a1. The remarks of the specified contact is emptied
      
      Use case ends.
      
*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for
   typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin
   commands) should be able to accomplish most of the tasks faster using commands than using the
   mouse.
4. Should work without any internet connection.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Delete persons

1. Delete one person

    1. Prerequisites: Must have at least one person in the list

    1. Test case: `delete 1`<br>
       Expected: Delete the first person in the list.

1. Delete multiple persons

    1. Prerequisites: Must have at least 3 person in the list

    1. Test case: `delete 1 2 3`<br>
       Expected: Persons at index 1, 2 and 3 are deleted.

1. Delete shown person(s) in the list

    1. Prerequisites: Must have at least 1 person in the list.

    1. Test case: `delete shown`<br>
       Expected: All person(s) in the visible person list are deleted.

1. Delete selected person(s)

    1. Prerequisites: Must have at least 1 person selected

    1. Test case: `delete selected`<br>
       Expected: All selected person(s) will be deleted.

1. Invalid test cases

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains
       the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than
       the list size)<br>
       Expected: Similar to previous.

### Selecting persons

1. Select one person

    1. Test case: `select 1`<br>
       Expected: First person is selected in the list.

1. Selecting persons multiple persons

    1. Prerequisites: List must contain at least 3 persons.

    1. Test case: `select 1 2 3`<br>
       Expected: 1st, 2nd and 3rd person is marked as selected.

    1. Test case: `select shown`<br>
       Expected: All person(s) in the current list will be selected

1. Selecting person(s) after find

    1. Prerequisites: `find` command executed.

    1. Test case: `select shown`<br>
       Expected: All person(s) that satisfies the `find` command will be selected

1. Clearing selection

    1. Prerequisites: Must have at least 1 person selected

    1. Test case: `select clear`<br>
       Expected: All person(s) that are selected will be un-selected.

### Email persons

Prerequisites: Must have an email client installed.

1. Email one person

    1. Prerequisites: Must have at least 1 person in the list

    1. Test case: `email 1`<br>
       Expected: Email client is opened with the "to" field filled with the email of the person at
       index 1.

1. Email multiple persons

    1. Prerequisites: Must have at least 3 person in the list

    1. Test case: `email 1 2 3`<br>
       Expected: Email client is opened with the "to" field filled with the email of the persons at
       index 1, 2 and 3.

1. Email shown person(s) in the list

    1. Prerequisites: Must have at least 1 person in the list.

    1. Test case: `email shown`<br>
       Expected: Email client is opened with the "to" field filled with the email(s) of the person(
       s) in the person list.

1. Email selected person(s)

    1. Prerequisites: Must have at least 1 person selected

    1. Test case: `email selected`<br>
       Expected: Email client is opened with the "to" field filled with the email(s) of the person(
       s)

### Edit persons

1. Edit one person

    1. Prerequisites: Must have at least one person in the list

    1. Test case: `edit 1 -p 99998888`<br>
       Expected: Phone number of person at index one is updated to "99998888".

1. Edit multiple persons

    1. Prerequisites: Must have at least 3 person in the list

    1. Test case: `edit 1 2 3 -a 21 Lower Kent Ridge Rd`<br>
       Expected: Address of persons at index 1, 2 and 3 is updated to "21 Lower Kent Ridge Rd".

1. Edit shown person(s) in the list

    1. Prerequisites: Must have at least 1 person in the list.

    1. Test case: `edit shown -a 21 Lower Kent Ridge Rd`<br>
       Expected: All person(s) address in the visible person list is updated to "21 Lower Kent Ridge
       Rd".

1. Edit selected person(s)

    1. Prerequisites: Must have at least 1 person selected

    1. Test case: `edit selected -a 21 Lower Kent Ridge Rd`<br>
       Expected: All selected person(s) address will be updated to "21 Lower Kent Ridge Rd".

### Filter fields

Prerequisites: Must have at least one person in the list to view the changes.

1. Filter to show only names

    1. Test case: `filter -n`<br>
       Expected: Only names are shown.

1. Filter to show only names and addresses

    1. Test case: `filter -n -a`<br>
       Expected: Only names and addresses are shown.
       
    1. Test case: `filter -a`<br>
       Expected: Only names and addresses are shown.

1. Remove filter

    1. Prerequisites: Must have a filter applied (e.g. `filter -a -p`).

    1. Test case: `fitler`<br>
       Expected: All fields are shown.

### Editing Remark

1. Edit a person's remark to a non-empty remark.
   1. Prerequisites: List must contain at least 1 person.
   1. Test case: `edit 1 -r On leave`<br>
      Expected: First person's remark is changed to "On leave".


2. Edit a person's remark without providing remark value.
    1. Prerequisites: List must contain at least 1 person.
    1. Test case: `edit 1 -r`<br>
       Expected: First person's remark is now empty.

### Alias

1. Add alias

    1. Test case: `alias add ls list`<br>
       Expected: Executing `ls` will behave exactly like `list`.
       
1. Delete alias

    1. Prerequisites: Must have an alias named `ls` (e.g. `alias add ls list`).

    1. Test case: `alias delete ls`<br>
       Expected: `ls` alias deleted.

1. List alias

    1. Test case: `alias list`<br>
       Expected: All existing alias(es) are shown.

### Tag

1. Add tag

    1. Prerequisites: Must have at least 1 person in the list.

    1. Test case: `tag add shown -t Photoshop`<br>
       Expected: All shown person(s) will have `Photoshop` tag added. If `Photoshop` tag exists
       before execution, nothing will change for that person. The command result will display the
       total number of persons the command have successfully executed on and not the total number of
       persons the tags are added to.

    1. Prerequisites: Must have at least selected 1 person.

    1. Test case: `tag add selected -t Photoshop`<br>
       Expected: All selected person(s) will have `Photoshop` tag added. If `Photoshop` tag exists
       before execution, nothing will change for that person. The command result will display the
       total number of persons the command have successfully executed on and not the total number of
       persons the tags are added to.

1. Delete tag

    1. Test case: `tag delete shown -t Photoshop`<br>
       Expected: All shown person(s) will have `Photoshop` tag removed. The command result will
       display the total number of persons the command have successfully executed on and not the
       total number of persons the tags are deleted from.

### Autocomplete

1. Command Autocomplete

    1. Test case: `e`<kbd>tab</kbd><br>
       Expected: `e` will be autocompleted to the next command in the command list panel (
       e.g. `edit`).

    1. Test case: `e`<kbd>tab</kbd> multiple times<br>
       Expected: `e` will be autocompleted to the next command in the command list panel and will
       cycle through the options.

1. Flag Autocomplete

    1. Test case: `add `<kbd>tab</kbd><br>
       Expected: Pressing <kbd>tab</kbd> multiple times will cycle through all the flags available
       for `add` command.

    1. Test case: `edit 1 `<kbd>tab</kbd> multiple times<br>
       Expected: Pressing <kbd>tab</kbd> multiple times will cycle through all the flags available
       for `edit` command.

### Find

1. Find All Fields

    1. Test case: `find coll`<br>
       Expected: Names, emails, tags and remarks containing `coll` will be shown.

1. Find by Specific Fields

    1. Test case: `find -t coll`<br>
       Expected: Tag(s) containing `coll` will be shown.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_


## **Appendix: Effort**

Our team has put in significant effort in enhancing the usability of AB-3 into a more CLI centric address book.
Much effort has been put in to replicate common controls and conventions seen in the popular terminal _Bash_. Our project
has over 13k lines of code and over 500 automated tests. We detailed some challenges faced and achievements made while 
implementing A-Bash Book in the subsequent sections.

### Challenges faced

- **Conversion to _Bash_ style arguments**
  - We note that _Bash_ uses the convention of `-` flags as well as enforcing a space after the arguments.
  - Much of the existing AB-3 command inputs are not following such conventions in _Bash_. To make such a change,
    we had to change `ArgumentMultimap` class which is the core of the program. Much time was put into validating that the
    commands are still work properly after such a change as the modification affects every single command that parses arguments.
- **Addition of command aliasing**
  - In order for us to implement one of our quality of life changes available in _Bash_, namely command aliasing, a deep
    understanding of the command flow is required. The addition of command aliasing requires a loop back of commands back
    into the parser system after it has been converted from its aliased form to its complete form.
- **Fuzzy string matching for contact finding**
  - While the algorithm for fuzzy matching was ultimately included via a library import of the common FuzzyWuzzy matching
    library, much effort was spent in considering the way strings should be matched. Documented in the [Fuzzy Find](#fuzzy-find)
    section, different considerations were made when deciding the level of tolerance, and how the way strings are matched.
- **UI additions**
  - An improvement was required to the UI to include the auto-complete command panel. Much of the UI code was also untestable
    within unit tests and require much manual testing. UI development also had to account for user limitations such as string
    length and screen size.
- **Attempt at WebView**
  - An attempt was made to include the WebView into our application for the user to have quick access to our user guide.
    However, after v1.3 was tested during PE-D, much issues surfaced from rendering issues when users use the webview to
    navigate outside the expected websites. It was eventually decided to scrap the WebView for a simple text based summary
    together with the existing link to our user guide.
- **Keyboard overrides**
  - To implement quality of life changes to be more keyboard centric, we implemented keyboard overrides to aid in selecting
    of contacts as well as to facilitate our autocomplete feature. Doing so required a big degree of understanding of JavaFX's
    UI event system. This is especially the case when we had to override the tab key for auto-complete which already had a 
    pre-existing function of navigating between UI elements.
- **Addition of special indices**
  - To support bulk commands, we added two special indices `shown` and `selected`. These two keywords can replace any `INDEX`
    in any command. To do so, we had to modify pre-existing commands to add support for multiple modifications in a single
    command. New commands also have to account for these two special indices during development.

### Achievements

- Streamlined and intuitive user experience for existing _Bash_ users.
- Improved the feature set and usability while still managing to maintain code coverage.

