---
layout: page
title: User Guide
---

CoLAB (Command Line Address Book) is a **desktop app for students currently enrolled in a university to manage their school projects.** It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CoLAB can get your project management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `colab.jar` from [here](https://github.com/AY2021S2-CS2103T-T11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for CoLAB.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listC`** and pressing Enter will list all contacts.<br>
   Some example commands you can try:

   * **`viewP 1`** : Displays the first project.

   * **`addP`**`n/My Project` : Adds a new project named `My Project` to CoLAB.

   * **`tabT`** : Displays the todos tab.

   * **`addTto`**`1 d/My Task` : Adds a todo with the description `My Task` to the first project.

   * **`deleteP`**`3` : Deletes the third project in the list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **Features**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Projects

#### Viewing a project: `viewP`

Displays a panel with details of a specified project.

Format: `viewP PROJECT_INDEX`

* Displays a panel with details of the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `viewP 1` Displays the first project. 
* `viewP 2` Displays the second project.

#### Viewing the overview tab: `tabO`

Switches to the overview tab of the project that is currently displayed.

Format: `tabO`

* This command can only be used when a project is currently being displayed.

#### Viewing the todos tab: `tabT`

Switches to the todos tab of the project that is currently displayed.

Format: `tabT`

* This command can only be used when a project is currently being displayed.

#### Adding a project: `addP`

Adds a project to CoLAB.

Format: `addP n/PROJECT_NAME`

Examples:
* `addP n/CS2103T Team Project`
* `addP n/CS2101 OP2`

#### Adding an event to a project: `addEto`

Adds an event to a specified project.

Format: `addEto PROJECT_INDEX d/DESCRIPTION i/INTERVAL at/REPEATABLE_DATE`

* Adds an event to the project at the specified `PROJECT_INDEX`. 
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu. 
* The index **must be a positive integer** 1, 2, 3, …​
* `INTERVAL` must be one of the following values:
    * `NONE`
    * `DAILY`
    * `WEEKLY`
    * `FORTNIGHTLY`
    * `MONTHLY`
    * `YEARLY`
* `REPEATABLE_DATE` must be in `dd-MM-yyyy` format.

Examples:
* `addEto 1 d/Project Meeting i/WEEKLY at/24-04-2021`
* `addEto 2 d/CS2101 Presentation i/NONE at/24-04-2021`

#### Adding a deadline to a project: `addDto`

Adds a deadline to a specified project.

Format: `addDto PROJECT_INDEX d/DESCRIPTION by/REPEATABLE_DATE`

* Adds a deadline to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu. 
* The index **must be a positive integer** 1, 2, 3, …​
* `REPEATABLE_DATE` must be in `dd-MM-yyyy` format.

Examples:
* `addDto 1 d/Milestone v1.2 by/01-03-2021`
* `addDto 2 d/CS2101 Project Submission by/15-03-2021`

#### Adding a todo to a project: `addTto`

Adds a todo to a specified project.

Format: `addTto PROJECT_INDEX d/DESCRIPTION`

* Adds a deadline to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addTto 1 d/Add unit tests`
* `addTto 2 d/Finish slides for presentation`

#### Add a contact to a project: `addCto`

Adds a contact to a specified project.

Format: `addCto PROJECT_INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Adds a contact to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `addCto 1 n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addCto 2 n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Deleting a project: `deleteP`

Deletes a specified project from CoLAB.

Format: `deleteP PROJECT_INDEX`

* Deletes a project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​
  
Examples:
* `deleteP 1`
* `deleteP 2`

#### Deleting an event from a project: `deleteE`

Deletes a specified event from a specified project.

Format: `deleteE PROJECT_INDEX r/EVENT_INDEX`

* Deletes the event at the specified `EVENT_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `EVENT_INDEX` refers to the number shown beside the event when viewing the project. 
* Both indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteE 1 r/1`
* `deleteE 2 r/2`

#### Deleting a deadline from a project: `deleteD`

Deletes a specified deadline from a specified project.

Format: `deleteD PROJECT_INDEX r/DEADLINE_INDEX`

* Deletes the deadline at the specified `DEADLINE_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `DEADLINE_INDEX` refers to the number shown beside the deadline when viewing the project.
* Both indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteD 1 r/1`
* `deleteD 2 r/2`

#### Deleting a todo from a project: `deleteT`

Deletes a specified todo from a specified project.

Format: `deleteT PROJECT_INDEX r/TODO_INDEX`

* Deletes the todo at the specified `TODO_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `TODO_INDEX` refers to the number shown beside the todo when viewing the project.
* Both indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteT 1 r/1`
* `deleteT 2 r/2`

#### Deleting a contact from a project: `deleteCfrom`

Deletes a specified contact from a specified project.

Format: `deleteCfrom PROJECT_INDEX r/CONTACT_INDEX`

* Deletes the contact at the specified `CONTACT_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `CONTACT_INDEX` refers to the number shown beside the contact when viewing the project.
* Both indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteCfrom 1 r/1`
* `deleteCfrom 2 r/2`

### Contacts

#### Adding a person: `add`

Adds a person to CoLAB.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Listing all persons : `listC`

Shows a list of all persons in CoLAB.

Format: `listC`

#### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Editing a person : `edit`

Edits an existing person in CoLAB.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

#### Clearing all entries : `clear`

Clears all entries from CoLAB.

Format: `clear`

### Other commands

#### View Today Panel : `today`

Shows the today panel.

Format: `today`

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

CoLAB's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

CoLAB's data is saved as a JSON file `[JAR file location]/data/colab.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CoLAB will discard all data and start with an empty data file at the next run.
</div>

### Coming soon

#### View more details about a person `[coming soon]`

Format: `view INDEX`

* Shows more details about the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `view 1` deletes the 1st person in the results of the `find` command.

#### Undo/Redo `[coming soon]`

_Details coming soon ..._

#### Archiving data files `[coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CoLAB home folder.

--------------------------------------------------------------------------------------------------------------------

## **Command summary**

Action | Format, Examples
--------|------------------
**Add Contact** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Add Project** | `addP n/PROJECT_NAME`
**Add Deadline to Project** | `addDto PROJECT_INDEX d/DESCRIPTION by/REPEATABLE_DATE`
**Add Event to Project** | `addEto PROJECT_INDEX d/DESCRIPTION i/INTERVAL at/REPEATABLE_DATE`
**Add Participant to Project** | `addCto PROJECT_INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`
**Add Todo to Project** | `addTto PROJECT_INDEX d/DESCRIPTION`
**Delete Contact** | `delete INDEX`<br> e.g., `delete 3`
**Delete Project** | `deleteP PROJECT_INDEX`
**Delete Deadline from Project** | `deleteD PROJECT_INDEX r/DEADLINE_INDEX`
**Delete Event from Project** | `deleteE PROJECT_INDEX r/EVENT_INDEX`
**Delete Participant from Project** | `deleteCfrom PROJECT_INDEX r/CONTACT_INDEX`
**Delete Todo from Project** | `deleteT PROJECT_INDEX r/TODO_INDEX`
**Clear Contacts** | `clear`
**Edit Contact** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find Contact** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List Contacts** | `listC`
**View Project** | `viewP PROJECT_INDEX`
**View Overview Tab** | `tabO`
**View Todos Tab** | `tabT`
**View Today Panel** | `today`
**Help** | `help`

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
