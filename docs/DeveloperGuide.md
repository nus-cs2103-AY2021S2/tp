---
layout: page
title: Developer Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------


<div style="page-break-after: always;"></div>

## **Introduction**

### Purpose

The purpose of this document is to cover the multi-level design architecture of Dictionote, so that the
intended audience of this document can understand the relationship between components that make up Dictionote.

### Audience

This developer guide is for anyone who wants to understand the internal software architecture of Dictionote.
The following groups of people are the intended audience:
* Dictionote developers: anyone who wish to upgrade Dictionote to support more functions.
* CS2103/T students: students of CS2103/T who want to improve their efficiency and productivity in learning.

### Dictionote Overview

Dictionote is a desktop app for CS2103/T Students, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). Dictionote in general helps to organise content and
definitions from the CS2103/T textbook, provide Note-keeping functions to facilitate learning, and enhance the ability
for students to connect and learn together with their cohort mates.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the
[diagrams](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/docs/diagrams/) folder. 
Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) 
to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/dictionote/Main.java) 
and [`MainApp`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API 
  `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface 
and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues 
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/dictionote/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `NoteListPanel`, `NoteCard` etc. 
All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files 
that are in the `src/main/resources/view` folder. For example, 
the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) 
is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.
* Use `Logic` component to update the UI with new settings.
* `DictionaryContentPanel` use` DictionaryListPanelConfig` to detect `DictionaryListPanel`display status
* Model Component can request for content change using `DictionaryContentConfig` and `NoteContentConfig`

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/dictionote/logic/Logic.java)

1. `Logic` uses the `DictionoteBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

<div style="page-break-after: always;"></div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/dictionote/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the data for `Dictionote`, including `ContactsList`, `NoteBook`, `DictionaryBook` and `DefinitionBook`.
* exposes an unmodifiable `ObservableList<Contact>`,`ObservableList<Note>`,`ObservableList<Content>`,`ObservableList<Definition>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list changed.
* does not depend on any of the other three components.

The `ContactsList` stores the `Contact` of the user. A `Contact` consists of `Name`, `Email`, `Phone`, `Address`, `FrequencyCounter`, and a custom `Tag` class.
The `NoteBook` stores the `Notes` of the user. A `Note` also has a `Tag` class. The `Dictionary` stores both the `Definition` and `Content` in the `Dictionote`. 
<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `ContactList` and `NoteBook`, which `Contact` and `Note` references, respectively. This allows `ContactList` and `NoteBook` to only require one `Tag` object per unique `Tag` in each "Book", instead of each `Contact` and  `Note` needing their own `Tag` object, respectively.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W13-1/tp/tree/master/src/main/java/seedu/dictionote/storage/Storage.java)

The `Storage` component,
* can save instances of the following classses in JSON format and read it back:
    * `UserPref`.
    * `ContactsList`.
    * `NoteBook`.
    * `Dictionary`.
    * `DefinitionBook`.
* depends on a few classes in the `Model` component; however, such dependencies are not shown in the diagram for simplicity's sake.

### Common classes

Classes used by multiple components are in the `seedu.dictionote.commons` package.

--------------------------------------------------------------------------------------------------------------------


<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Contact features

#### Sending an email to a contact

##### Implementation

This feature is implemented as a command, `EmailCommand`, that extends `Command`. It is an index-dependent command, meaning that the user must provide an index number when typing the command as a reference to a specific contact on the contacts list. In addition, the command has an optional index number argument that refers to a note within the note book.

The index number was selected to refer to a particular contact. This is due to its character length being shorter in most cases than any other field of information in `Contact` objects, making it both simple and sufficient. Likewise, notes are referred to by their index numbers for the same reasons.

The `execute()` method attempts to open a new window of the user's operating system (OS) default mail client. This is done by navigating to a `mailto` link with the contact's email address added to the end. In case a note index number is provided, the contents of the note located at the specified index number will be copied to the messages's body field.

If the command is executed successfully, the target contact's frequency counter (see [*Sorting the contacts list by most-frequently contacted*](./DeveloperGuide.md#sorting-the-contacts-list-by-most-frequently-contacted) below) will be incremented by one.

As an example, consider running Dictionote on a Windows 10 machine with Microsoft Outlook as the OS default mail client:

* Assume that the current state of the application is as follows (note the exisiting contacts on the left-side of the application's window and the note on the right):

![ContactEmailFeatureInitState](images/ContactEmailFeatureInitState.png)

* After typing in `emailcontact 2 ni\1` and executing it, the result would be:

![ContactEmailFeatureExecute](images/ContactEmailFeatureExecute.png)

* A new window, belonging to Microsoft Outlook's `New Message` function, will pop up:

![ContactEmailFeatureOSClient](images/ContactEmailFeatureOSClient.png)

* Note that the email of the selected contact, Bob (referred to in the command by his index number), is automatically written in the `To...` field of the email's header information.
* Also note that the body of the message contains the contents of the first note on the list (that is, `QA stands for Quality Assurance`).

In case the user does not have a mail client software set as default in their OS, Dictionote will try to navigate to the `mailto` link through the user's default browser (i.e., the `mailto` link will be treated as an ordinary URL link).

##### Design Considerations

* **Alternative 1 (current choice):** make use of the OS mail client to facilitate email features.
    * Pros: Easy to implement; utilizes a pre-existing and standardized system for invoking mail clients.
    * Cons: Requires the user to have a mail client installed on their OS, which is then set to be the default mail client of the system.

* **Alternative 2:** implement basic email features directly into Dictionote.
    * Pros: Does not depened on the existence of external software in the OS.
    * Cons: Much harder to implement, as it requires the implementation of network-related functions to handle the connections to email servers.


<div style="page-break-after: always;"></div>

#### Sorting the contacts list by most-frequently contacted

##### Implementation

This feature is implemented as a command, `MostFreqContact`, that extends `Command`. It can execute without arguments, and it ignores any of them if provided.

The `execute()` method attempts to sort all contacts stored within the contacts list in descending order based on each contact's **frequency counter**, which is an attribute holding a non-negative integer value that represents the number of attempts an email was sent to that contact. The sorting is handled by the `sorted` method of the `ObservableList` class, of which an instance stores the contacts.

As an example, consider running Dictionote as follows:

* Assume that the current state of the application is as follows (note the exisiting contacts on the left-side of the application's window):

![ContactMostFreqFeatureInitState](images/ContactMostFreqFeatureInitState.PNG)

* In addition, assume the successful execution of the following commands:
    * `emailcontact 3` three times.
    * `emailcontact 2` one time.
    * `emailcontact 1` two times.

* After typing in `mostfreqcontact` and executing it, the result would be:

![ContactMostFreqFeatureExecute](images/ContactMostFreqFeatureExecute.PNG)

* Note that the ordering of the contacts in the contacts list had changed, with Charlie (formerly with index number 3) being the first on the list, followed by Alice (formerly with index number 1) and finally Bob (formerly with index number 2).

##### Design Considerations

* **Alternative 1 (current choice):** add a new attribute to the `Contact` class in order to keep track of the number of attempts to send an email.
    * Pros: Preserves the functionality of the `Contact` class before the implementation of the new feature; minimal integration issues with other `Contact`-dependent classes and methods.
    * Cons: Requires the creation of a new class to represent this attribute; adds an extra attribute to the contacts list's JSON storage file, which might requires more space (although it may be minimal).

* **Alternative 2:** create a `HashTable` to store a mapping between the contacts and the number of attempts to send an email of which they were the recipients.
    * Pros: Zero modifications on the `Contact` class; `Contact` objects will not contain any attributes required by this feature.
    * Cons: Difficult to integrate with the other classes and methods related to `Contact` objects (e.g., deletion of a contact from the list must propagate to the `HashTable`).


### UI features
#### Opening and closing UI through command result
#####  Implementation

Dictionote has an interactive user interface that allows the user to open and close any panel through command.
Furthermore, when any command is executed, 
Dictionote should be able to change the user interface based on the command type/requirement.
All commands should be able to open and close its required UI panel. 
The user will also be able to open and close the UI via user command.
The feature is implemented using `CommandResult`, which is returned by all `Command` in the system.

`CommandResult` contain a `String` object call `feedbackToUser`, a `UiAction` enum call `uiAction`
and `UiActionOption` enum call `uiActionOption`. `feedbackToUser` will
be show on the `ResultDisplay` indicating the command feedback after execution.
`uiAction` indicate the action the command want the `UI` to take.
e.g `UiAction.OPEN`, `UiAction.CLOSE`, ... etc. `UiActionOption` is only applicable to some `UiAction`.
It indicate the specific option available for the `UiAction`.
e.g `UiActionOption.CONTACT` for `UiAction.OPEN` mean open contact panel.

The following is the sequence diagram for executing a command to open a panel.

![OpenCommandSequenceDiagram](images/OpenCommandSequenceDiagram.png)

#### Design Consideration

* **Alternative 1 (current choice):** Make use of the existing `CommandResult` class
    * Pros: Make use of the existing system and easy to implement
    * Cons: All command will have to decide on the response. (or use the default setting) (desire behaviour)
* Alternative 2: Make use of the `Model` component as an intermediary between Command and UI.
  The command will call a method available on the `Model` component to change the UI settings,
  the `UI` will listen to the setting on the `Model` component.
    * Pros: Only the class that requires to change the UI will be needed to call the method.
    * Cons: Increasing coupling.
    
    
<div style="page-break-after: always;"></div>

#### Command that manipulation UI settings

#####  Implementation

While all `Command` has the ability to open and close the UI. There are some UI settings that are more specific.
In this case, we don't want all commands to implement its behavior, so using `CommandResult` isn't ideal.
Since the `UI` component is already uses `GuiSettings` to store its settings when Dictionote close.
We can make use of the existing structure by making the `UI` component 
to actively listen for changes to the `GuiSettings`.
The features are implemented in all `toggledivider` and `setdividerposition` commands.

The command will change the `GuiSettings` located in `Model` componenet. 
When the `CommandResult` is returned to the `UI` component.
The `UI` component will check for changes in `GuiSettings` and update it Ui according.

The following is the sequence diagram for executing a command to set note divider position.

![OpenCommandSequenceDiagram](images/ToggleCommandSequenceDiagram.png)

In the sequence diagram, `executeUiAction(action,option)` is called.
This is to show that the command ensure that the user receives proper feedback when the divider position changes.
The command will open all panels that were affected by the divider position change.
If the affected panel remain closed, the user will not be able to notice the changes.
Note divider affect both `NoteListPanel` and `NoteContentPanel`, hence if its  is closed, they will be open. 
The execution of the command will make both `NoteListPanel` and `NoteContentPanel` visible,
and the note divider positions will be adjusted to the desired position

#### Design Consideration

* Alternative 1 Make use of the existing `CommandResult` class
    * Pros: Make use of the existing system and easy to implement
    * Cons: All command will have to decide on the response. (or use the default setting)
* **Alternative 2(current choice):**: Make use of the `Model` component as an intermediary between Command and UI.
  The command will call a method available on the `Model` component to change the UI settings, 
  the `UI` will listen to the setting on the `Model` component.
    * Pros: Only the class that requires to change the UI will be needed to call the method.
    * Cons: Increasing coupling.


<div style="page-break-after: always;"></div>

### Note Features

#### Converting a note into a .txt file

Dictionote provides a method for its users to convert a note into a text file that are easily editable in other softwares.

This feature is implemented as a command, `ConvertTxtNoteCommand`, that extends `Command`. It is an index-dependent command, meaning that the user must provide an index number when typing the command as a reference to a specific note on the note list.

The `execute()` method will calls a `ConvertTxtNote` function, which uses Java's `BufferedWriter` and `FileWriter` class to write remotely.

Here is the example of the command usage. Assume initially, the state of the application is shown below:

![ConvertNoteToTxtStart](images/ConvertNoteToTxtStart.png)

* After typing in `converttxt 1` and executing it, the result would be:

![ConvertNoteToTxtEnd](images/ConvertNoteToTxtEnd.png)

* The resulting text file can be seen in the `data` folder.

![ConvertNoteToTxtResult](images/ConvertNoteToTxtResult.png)

* Here is the resulting note.

![ConvertNoteToTxtContent](images/ConvertNoteToTxtContent.png)

#### Design Consideration

* **Alternative 1 (current choice):** make use of the Java's `FileWriter` class to help us write files.
    * Pros: Easy to implement; utilizes a pre-existing and standardized system for file writing.
    * Cons: Need to modify code later for enhancement later.

* **Alternative 2:** make our customized note-to-text converter class.
    * Pros: Enable us to convert more than text files and have specific usage for our code.
    * Cons: Very hard to implement, not enough time and knowledge.

<div style="page-break-after: always;"></div>

#### Merge two notes into one

Dictionote provides a method for its users to merge two notes into one combined note.

This feature is implemented as a command, `MergeNoteCommand`, that extends `Command`. It is an index-dependent command, meaning that the user must provide 2 indexes number when typing the command as a reference to the notes that would like to be merged.

The `execute()` method will calls a `mergeNote` function which would delete both notes from NoteBook and add a new combined note into the NoteBook.

Here is the example of the command usage. Assume initially, the state of the application is shown below:

![ConvertNoteToTxtEnd](images/MergeNoteBefore.png)

After typing in `mergenote 1 2` and executing it, the result would be:

![ConvertNoteToTxtEnd](images/MergeNoteAfter.png)

#### Design Consideration

* **Alternative 1 (current choice):** Implement MergeNoteCommand which extends Command.
    * Pros: Easy to implement; match the design pattern already inherited from AB3.
    * Cons: More lines of code.

* **Alternative 2:** Make MergeNoteCommand as a function from the model immediately.
    * Pros: Less Lines of Code and simpler due to not extending the Command class.
    * Cons: Might be confusing as some of the commands are already implented and might not be consistent with other commands.


<div style="page-break-after: always;"></div>

### Dictionary features

#### Copying Content to a Note

#####  Implementation

Dictionote provides a way for users to copy over the content from the dictionary to a note.
This feature is implemented as a command, `copytonote`, combining 2 models, namely the 'Content', and the 'Note' Models.  

The `execute()` method will first call on `getFilteredCurrentDictionaryList()` to get the currently shown list in the dictionary panel.   
The method will then get the index keyed in by the user and find the corresponding content from the list at the index.   
A `String` object will then be created to store this information by getting the content body of the Content using the `get()` method.  
The nextstep is to produce a new note by calling the `new Note()` constructor, with the note content being the `String` object created by the `get()` method.   
Finally, the method will call on `addNote()` to add the created note into the notes list.  
The `execute()` method will facilitate the display of the new note in the note list panel.

The following is the sequence diagram for executing the `execute()` command in copying a content to a note.

![CopyToNote](images/CopyToNoteDiagram.png)

<div style="page-break-after: always;"></div>

#### Design Consideration

* **Alternative 1 (current choice):** Make use of the existing `Note` and `Content` models.
    * Pros: Make use of the existing system and easy to implement
    * Cons: Different methods and constructors have to be called since there exists 2 different models, which can induce type casting conflicts or get confusing. 
* Alternative 2: Create a new model that will contain a mix of the `Note` and `Content` models.
    * Pros: No constructors have to called and there won't be a need to worry about type casting failures.
    * Cons: Increasing workload and no other features will use this new model.


<div style="page-break-after: always;"></div>

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

* Tech-Savvy CS2103T Student
* want to ask questions
* need to find answers
* prefers to take notes

**Value proposition**:

* Main problem: no organized platform to facilitate extra learning outside of class.
* Sub-Problems
    * Easy and quick way for students to get answers for CS2103T questions.
    * Easy for students to take notes.
    * Easy way for students to find their classmates.

* Limitations:
    * Unable to provide students with thorough materials/all the answers.
    * Unable to play videos from lectures.

<div style="page-break-after: always;"></div>

### User stories

| Priority | As a …​                                                   | I want to …​                                          | So that I can…​                                            | Category               |
| -------- | -------------------------------------------------------------| -------------------------------------------------------- | ------------------------------------------------------------- | ---------------------- |
|***UI***| | | |
| `* *`    | CS2103 Student                                               | View note and dictionary side-by-side                    | Easily copy dictionary content to note                        | Essential     |
| `* *`    | CS2103 Student                                               | Open and close Contact panel                             | Have more space for other content                             | Non-essential |
| `* *`    | CS2103 Student                                               | Open and close Dictionary Content panel                  | Have more space for other content                             | Non-essential |
| `* *`    | CS2103 Student                                               | Open and close Dictionary List panel                     | Have more space for other content                             | Non-essential |
| `* *`    | CS2103 Student                                               | Open and close Note Content panel                        | Have more space for other content                             | Non-essential |
| `* *`    | CS2103 Student                                               | Open and close Note List  panel                          | Have more space for other content                             | Non-essential |
| `* *`    | CS2103 Student                                               | Save my UI configuration                                 | Save my time on re-adjusting the Ui everytime I open the app  | Non-essential |
| `* *`    | CS2103 Student                                               | Change my UI configuration                               | Do no need to adjust the UI using mouse                       | Non-essential |
| `* *`    | CS2103 Student                                               | Change my UI orientation                                 | Use the space available more efficiently                      | Non-essential |
|***Dictionary*** | | | | |
| `* * *`  | CS2103T student who find it troublesome to use the website   | Search for a definition of an SE term                    | Understand what it means                                      | Essential|
| `* * *`  | CS2103T student                                              | Find content I need                                      | Save time having to dig through the textbook                  | Essential|
| `* * *`  | CS2103T student                                              | List all the contents in the dictionary                  | View the extensive list of contents                           | Essential|
| `* * *`  | CS2103T student                                              | List all the definitions in the dictionary               | View the extensive list of contents                           | Essential|
| `*`      | CS2103T student                                              | Track my progress when reading through a summary         | Continue my preparation from where I left off                 | Non-essential|
| `* *`    | CS2103T student                                              | Copy specific contents in the dictionary to the notes    | Keep track of the important content on my personal note list  | Non-essential|
|***Note*** |  |  |  | |
| `* * *`  | CS2103T student                                              | Take a new note                                          | Have easy access to my materials whenever I need them         | Essential  |
| `* * * ` | CS2103T student                                              | Delete an existing note                                  | Remove out-of-date notes.                                     | Essential  |
| `* * * ` | CS2103T student                                              | Edit a note                                              | Revise a small typo in the note.                              | Essential  |
| `* * * ` | CS2103T student                                              | Look at all notes                                        | Remember what is the content of the note                      | Essential  |
| `* * * ` | CS2103T student                                              | Show a specific note                                     | To read the content of a specific note in detail              | Essential  |
| `* * * ` | CS2103T student                                              | Edit a note in edit mode                                 | Modify the content of the note easily.                        | Essential  |
| `* * `   | CS2103T student                                              | Tag a note                                               | I can access notes easily.                                    | Non-Essential  |
| `* * `   | CS2103T student                                              | Track the date and time the note is created              | Find the note according to the time created                   | Non-Essential  |
| `* * `   | CS2103T student                                              | Sort a note alphabetically                               | I can read the notes in order.                                | Non-Essential  |
| `* * `   | CS2103T student                                              | Search a note using keyword                              | Find out what notes contain the specific keyword.             | Non-Essential  |
| `* * `   | CS2103T student                                              | Mark a note as undone                                    | Remember which part of the notes I have not done yet.         | Non-Essential  |
| `* * `   | CS2103T student                                              | Mark all notes as undone                                 | Reset all the features I have marked as done.                 | Non-Essential  |
| `* `     | CS2103T student                                              | Track the date and time the note is last modified        | Find the note according to the time last modify.              | Non-Essential  |
| `* `     | CS2103T student                                              | Mark a content of a note as done                         | Remember which part of the notes I have done.                 | Non-Essential  |
|***Contact***| | | | |
| `* * *`  | CS2103T Student                                              | Add my contacts                                          | Easily manage the contacts list                               | Essential     |
| `* * *`  | CS2103T Student                                              | Edit my contacts                                         | Easily manage the contacts list                               | Essential     |
| `* * *`  | CS2103T Student                                              | Delete my contacts                                       | Easily manage the contacts list                               | Essential     |
| `* * *`  | CS2103T Student                                              | Look at all contacts                                     | Easily manage the contacts list                               | Essential     |
| `* *`    | CS2103T Student                                              | Tag a contact with a word                                | Find contacts based on their tags                             | Non-essential |
| `* *`    | CS2103T Student                                              | Search for contacts using tags                           | Contact anyone from a particular tag                          | Non-essential |
| `* *`    | CS2103T Student who wants to connect with others that I know | Email anyone from my contacts list                       | Ask questions, discuss topics, or exchange notes with them    | Non-essential |
| `* *`    | Extroverted student who likes to discuss things with friends | Locate the contacts whom I frequently contact                       | Save time having to look for them in my address book    | Non-essential |
| `* *`    | CS2103T student | Send a note by email                       | Easily discuss the contents of a particular note with one of my contacts    | Non-essential |
| `* *`    | CS2103T Student | Search the email of my friends             | I can easily find for whom a particular email address is.    | Non-essential |
|***Guide*** | | | | |
| `* * *`  | CS2103T student who is bad at remembering commands           | Access the list of commands with brief explanation       | Save time having to search through user guide for details     | Essential  |
| `* * *`  | CS2103T student who uses commands often                      | Scan through the list of commands for a quick refresher  | Save time having to search through user guide for all command | Essential  |


### Use cases

(For all use cases below, the **System** is the `Dictionote` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Close a panel**

**MSS**
1.  User requests to close a specific display panel.
2.  Dictionote close the display Panel.

    Use case ends.

**Extensions**

* 1a. The given display panel is invalid.
    * 1a1. Dictionote shows an error message.

      Use case ends.

**Use case: UC02 - Edit and save a note in edit mode**

**MSS**
1.  User requests to edit in edit mode.
2.  Dictionote enable edit mode.
3.  User edit note and request to save the note.
4.  Dictionote save edited note.
5.  Dictionote exit edit mode.

    Use case ends.

**Extensions**

* 1a. No note is shown in note content panel.
    * 1a1. Dictionote shows an error message.

      Use case ends.

* 3a. Dictionote found the note the user trying to save were a duplicate of another note in the system.
    * 3a1. Dictionote shows an error message.
    * 3a2. User edit note with new content and request to save the note.

      Steps 3a1-3a2 are repeated until the note the User is trying to save is not a duplicate.

      Use case resumes from step 4.

* \* a. At any time, User chooses to exit edit mode.
    * \*a1. Dictionote discard edited note.
    * \*a2. Dictionote exit edit mode. 
      
      Use case ends.




**Use case: UC03 -  Delete a contact**

**MSS**

1.  User requests to list contacts.
2.  Dictionote shows a list of contacts.
3.  User requests to delete a specific contact in the list.
4.  Dictionote deletes the contact.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Dictionote shows an error message.

      Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**Use case: UC04 -  Clear the contacts list**

**MSS**

1.  User requests to clear their contacts list.
2.  Dictionote clears the contacts list by deleting all contacts.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.
  
**Use case: UC05 -  Send an email to a contact**

**Actors:** User, Operating System (OS), Mail Application, Web Browser.

**MSS**

1.  User requests to list contacts.
2.  Dictionote shows a list of contacts.
3.  User requests to send an email to the contact located at the specified index number.
4.  Dictionote invokes the OS's mail application with the appropriate fields filled in.
5.  User types in a subject for the message.
6.  User sends the message through the mail application.

  Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
  
* 3a. User provides a note index whose contents are requested to be sent.

  Use case resumes from step 4.

* 4a. OS does not have a default mail application set.
    * 4a1. OS invokes its default web browser.
    * 4a2. Web browser opens a new, empty tab.
    
  Use case ends.

<div style="page-break-after: always;"></div>

**Use case: UC06 -  Delete a note**

**MSS**

1.  User requests to list notes.
2.  Dictionote shows a list of notes.
3.  User requests to delete a specific note in the list.
4.  Dictionote deletes the note.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Dictionote shows an error message.

      Use case resumes at step 2.

**Use case: UC07 -  Sort notes by Last Edit Time**

**MSS**

1.  User requests to list notes.
2.  Dictionote shows a list of notes.
3.  User requests to sort notes based on the last edit time.
4.  Dictionote shows a list of notes, sorted by last edit time.

    Use case ends.
    
**Extensions**

* 2a. The list is empty.

  Use case ends.

<div style="page-break-after: always;"></div>

**Use case: UC08 -  Find Content in the Dictionary**

**MSS**

1.  User requests to list content.
2.  Dictionote shows a list of content.
3.  User requests to find content using a certain set of keywords.
4.  Dictionote shows the list of contents matching those keywords.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

**Use case: UC09 -  Copy Content to Note**

**MSS**

1.  User requests to list content.
2.  Dictionote shows a list of content.
3.  User requests to show the content at a specific index.
4.  Dictionote shows the content.
5.  User requests to content the content at a specific index to a note.
6.  Dictionte adds a new note with the note body being the content at that index.

**Extensions**

* 1a. The list is empty.

  Use case ends.
* 3a. The given index is invalid.

    * 3a1. Dictionote shows an error message.

      Use case resumes at step 2.
* 5a. The given index is invalid.

    * 5a1. Dictionote shows an error message.

      Use case resumes at step 4.

**Use case: UC10 -  List all notes**

**MSS**

1.  User requests to list notes.
2.  Dictionote shows a list of notes which might or might not be empty.

    Use case ends.

**Use case: UC11 -  Show a specific note**

**MSS**

1.  User requests to show a specific note.
2.  Dictionote shows note requested by the user in the rightmost panel.

    Use case ends.

**Extensions**

* 1a. The requested index is out of bounds. Then an index invalid exception will arise.

  Use case ends.

### Non-Functional Requirements

#### User Requirement
1. Typing Preferred User
    * user should be targeting user who can type fast
    * user should have above average typing speed for regular English text

#### Software Requirement
1. Single User
    * should be for single user.
1. Human Editable File
    * data should be stored locally and should be in a human editable text file.
1. Single File
    * should work with a single JAR file
1. File Size
    * file sizes of the deliverables should not exceed the `100MB`


#### Dependency Requirement

1. Platform Independent
    * should work on Windows, Linux and OS-X platform.
    * avoid using OS-dependent libraries and OS-specific features
1. Java version
    * Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
    * should work without requiring an installer
1. Portable
    * should work without requiring an installer
1. External Software
    * Third party frameworks/libraries is subjected to approval, and only if they,
        * are free, open-source, and have permissive license.
        * do not require any installation by the user of your software.
        * do not violate other constraint.
1. No Database Management System
    * should not use any database management system to store data.

#### Documentation Requirement

1. PDF Friendly
    * The Developer Guide and User Guide should be PDF-friendly.
    * do not use expandable panels, embedded videos, animated GIFs etc.
1. File Size
    * file sizes of documents should not exceed the `15MB`


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X



<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually. Note that some tests use specific data in their examples, which might need to be inserted first using CRUD commands; however, it is possible to replace such specific data with already-existing ones found by default in the application.

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

1. _{ more test cases …​ }_

### Adding a contact

1. Adding a contact that shares its phone number with another existing contact.

    1. Prerequisites: At least one contact is present in the list.
	
	1. Assumptions: One contact is present with the phone number `11223344`.

    1. Test case: `addcontact p/11223344 n/<ANY_NAME> a/<ANY_ADDRESS> e/<ANY_UNIQUE_EMAIL>`<br>
       Expected: An error message is displayed, telling that another existing contact already has the specified phone number or email.
	   
	* Note that this behavior should apply for any phone number that is already used for another contact in the list.
	
1. Adding a contact that shares its email with another existing contact.

    1. Prerequisites: At least one contact is present in the list.
	
	1. Assumptions: One contact is present with the email `taken@email.com`.

    1. Test case: `addcontact e/taken@email.com n/<ANY_NAME> a/<ANY_ADDRESS> p/<ANY_UNIQUE_PHONE_NUMBER>`<br>
       Expected: An error message is displayed, telling that another existing contact already has the specified phone number or email.
	   
	* Note that this behavior should apply for any email that is already used for another contact in the list.
	
### Editing a contact

Similar to *Adding a contact* above (shared phone numbers and/or emails).

### Deleting a note

1. Deleting a note while all notes are being shown

    1. Prerequisites: List all notes using the `listnote` command. Multiple notes in the list.

    1. Test case: `deletenote 1`<br>
       Expected: First note is deleted from the list. Details of the deleted note shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `deletenote 0`<br>
       Expected: No note is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `deletenote`, `deletenote x`, `...` (where `x` is larger than the list size)<br>
       Expected: Similar to previous.


### Showing a note

1. Showing a specific note

    1. Prerequisites: List all notes using the `listnote` command. Multiple notes in the list.

    1. Test case: `shownote 1`<br>
       Expected: First note is shown on the rightmost panel. Details of the showed note is shown there.

    1. Test case: `shownote 0`<br>
       Expected: No note is showed. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `shownote`, `shownote x`, `...` (where `x` is larger than the list size)<br>
       Expected: Similar to previous.

### Finding Content

1. Finding a specific Content in the Dictionary.

  1. Prerequisites: List all contents using the `listcontent` command. Multiple contents in the list.

  1. Test case: `findcontent hello`<br>
     Expected: No content is shown. The panel should be empty.

  1. Test case: `findcontent implement`<br>
     Expected: 4 contents should be shown in the Dictionary list panel.

  1. Other incorrect delete commands to try: `findcontent`, `findcontent `, `...` (where ` ` is a space)<br>
     Expected: Error message should be shown, stating that there is an 'invalid command format'.

<div style="page-break-after: always;"></div>

### Showing content/definition

1. Showing a specific content/definition

  1. Prerequisites: Have some contents available in the Dictionary List Panel, commands to facilitate that will be `listcontent`, `findcontent programming`, `listdef`, etc.

  1. Test case: `showdc 1`<br>
     Expected: First content in the Dictionary List Panel is shown on the Dictionary Content Panel. Details of the showed content is shown there.

  1. Test case: `showdc 0`<br>
     Expected: No content is showed. Error details shown in the status message. Status bar remains the same.

  1. Other incorrect delete commands to try: `showdc`, `showdc x`, `...` (where `x` is larger than the list size)<br>
     Expected: Similar to previous.

### Modifying the UI through command

For more information regarding the panel layout and divider position, 
please refer to the user guide - UI section for more details.


#### Opening and closing display panel

Available `OPTION` for open and close command : `-a`, `-c`, `-d`, `dc`, `dl`, `-n`, `-nc`, `nl`, `-l`

1. Opening a panel through open command 
    
    1. Prerequisites: Close all panel using `close -a` for better visibility

    1. Test Case : `open -c`
       <br> Expected : Contact panel will be open

    1. Test Case : `open -c`
      <br> Expected : Contact will remain open

    1. Repeat the previous steps for all `OPTION` 
       <br> (check user guide for more detail about the option.)
       
    1. Test Case : `open -x`
      <br> Expected : Invalid command messages will be shown
      
    1. Other incorrect open commands to try: `open`, `open x`, `...` (where x is not an `Option`)<br>
       Expected: Similar to previous.

<div style="page-break-after: always;"></div>

1. Close a panel through open command

    1. Prerequisites: Close all panel using `open -a` for better visibility

    1. Test Case : `close -c`
       <br> Expected : Contact panel will be close

    1. Test Case : `close -c`
       <br> Expected : Contact will remain close

    1. Repeat the previous steps for all `OPTION`
       <br> (check user guide for more detail about the option.)

    1. Test Case : `close -x`
       <br> Expected : Invalid command messages will be shown

    1. Other incorrect open commands to try: `close`, `close x`, `...` (where x is not an `Option`)<br>
       Expected: Similar to previous.

#### Setting divider position via command

List of availble set divider position command : `setdividerc`, `setdividerd`, `setdividern`, `setdividerm`
<br> Range of valid `POSITION` for set divider command :  1 to 9 (Inclusively)

1. Setting position of a panel through open command

    1. Prerequisites: Close all panel using `close -c` for better visibility

    1. Test Case : `setdividerc 5`
       <br> Expected : contact divider will be set at the halfway mark. contact panel should be open.
       <br> (check user guide for more detail for where each position represent.)

    1. Test Case : `setdividerc 5`
       <br> Expected : Contact will remain open

    1. Test Case : `setdividerc 0`
       <br> Expected : Invalid command messages will be shown

    1. Other incorrect open commands to try: `setdividerc`, `setdividerc x`, `...` (where x is not within the valid range)
       <br> Expected: Similar to previous.
       
2. Repeat the previous test case with `setdivider`, `setdividerd`, and `setdividerm`.

#### Toggling divider orientation via command

List of availble toggle divider orientation command : `toggledividerd`, `toggledividern`

1. Setting position of a panel through open command

    1. Test Case : `toggledividerd`
       <br> Expected : Dictionary divider orientation will change to horizontal if it vertical, vice-verse. 
       if note list panel or note content panel is closed, it will be open.

    1. Test Case : `toggledividerd`
      <br> Expected : Dictionary divider  orientation will change to horizontal if it vertical, vice-verse.

    1. Test Case : `toggledividerd`
      <br> Expected : Dictionary divider  orientation will change to horizontal if it vertical, vice-verse.

2. Repeat the previous test case with `toggledividern`.


### Saving data

1. Dealing with missing/corrupted data files

    1. If the data files are corrupted. The data will be wiped clean and an empty data file will be generated.
