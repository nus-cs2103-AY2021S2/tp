---
layout: page
title: User Guide
---

<!-- TOC adapted from
https://github.com/AY2021S1-CS2103T-W16-3/tp/pull/179/commits/aec461182c194c9ca2c67d7c407fcabb376191ff
-->
<div class="toc-no-bullet-points">
  * Table of Contents
  {:toc}
</div>

## **1. Introduction**

<div align="center">
  <img alt="logo" src="images/logo.png">
</div>

Welcome to the user guide of **CoLAB**! Are you a university student in search of a reliable app to keep track of your school projects? Do you struggle to keep track of the many todos and deadlines for various school projects? Do you also tend to forget the various group meetings you have scheduled? You have come to the right place!

CoLAB (Collaboration Lab) is a **desktop app for students currently enrolled in a university to manage their school projects.** It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CoLAB can get your project management tasks done faster than traditional GUI apps. CoLAB's main features include:

- Project management
- Contacts management
- Today View

With CoLAB, you can efficiently manage all your school projects through our comprehensive project management tools. We have put in a lot of time and effort into designing a user-friendly User Interface (UI) such that CoLAB remains intuitive to both first-time users and seasoned ones. We look forward to seeing what you accomplish with CoLAB with a clean and inviting UI. What are you waiting for? Get your journey started with the [Quick Start section](#3quick-start)!

## **2. About**

This section serves to familiarise you with information and terms that would help you make the best use of this user guide.

### 2.1 Structure of this Document

We have included a large set of features in CoLAB to give you the greatest flexibility over what you can do with it. As such, this User Guide has been structured in a manner that allows you to easily find what you are looking for.

In [Section 2.2: Reading this Document](#22-reading-this-document), you will find useful tips on efficiently reading this document.

In [Section 3: Quick Start](#3quick-start), you will find a quick start guide to get you started with using CoLAB.

In [Section 4: Features](#4features), you will find documentation on all of CoLAB's commands and features.

In [Section 5: FAQ](#5faq), you may find answers to some questions you may have on using CoLAB.

In [Section 6: Command Summary](#6command-summary), you will a summary on CoLAB's commands and features.

You can also easily sift through CoLAB's various commands and features by referring to the Table of Contents at the beginning of this User Guide.

### 2.2 Reading this Document

This subsection serves to provide explanations on the symbols, syntax and technical terms used throughout this User Guide. Familiarising yourself with this subsection will make the User Guide easier to read.

#### 2.2.1 Sections of the Application Window

Different sections of the application window will be referred to by the names described in the image shown below:

![App Window Sections](images/app_window_sections.png)

#### 2.2.2 Special Formats

[TODO]

#### 2.2.3 Command Format

Commands shown in this user guide follow these rules.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addC n/NAME`, `NAME` is a parameter which can be used as `addC n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

#### 2.2.4 Command Parameters

This subsection serves to list out the parameters used in this document. We have set reasonable constraints on the parameters to ensure that the UI displays your information correctly.

##### `ADDRESS`

* The address of a contact.
* Addresses can take any values, but it should not be blank.

##### `DATE`

* The date of a deadline or event.
* Date can be only be entered in one of these formats:
    * 23-11-2021
    * 23/11/2021
    * 23.11.2021
    * 23112021
* The year should be a non-negative number between 0000 and 9999.

##### `DESCRIPTION`

* The description of a deadline, event or todo.

##### `EMAIL`

* The email address of a contact.
* Emails should be of the format local-part@domain.
* The local-part should only contain alphanumeric characters and these special characters, <code>!#$%&'*+/=?`{|}~^.-</code>.
* This is followed by a '@' and then a domain name.
* The domain name must:
    * be at least 2 characters long
    * start and end with alphanumeric characters
    * consist of alphanumeric characters, a period or a hyphen for the characters in between, if any.

##### `INDEX`

* Indexes are used to identify specific items in CoLAB. There are 6 types of indexes:
    * `CONTACT_INDEX` refers to the number shown beside the contact in the Main Panel when contacts are displayed.
    * `DEADLINE_INDEX` refers to the number shown beside the deadline in the Main Panel when a project is displayed.
    * `EVENT INDEX` refers to the number shown beside the event in the Main Panel when a project is displayed.
    * `GROUPMATE_INDEX` refers to the number shown beside the groupmate in the Main Panel when a project is displayed.
    * `PROJECT_INDEX` refers to the number shown beside the project name in the side panel.
    * `TODO_INDEX` refers to the number shown beside the todo in the Main Panel when a project is displayed.
* Indexes **must be a positive integer** 1, 2, 3, …​

##### `REPEAT_WEEKLY`

* The weekly repetition status of an event.
* Weekly repetition can be only be entered in one of these formats:
    * Y
    * y
    * N
    * n
* `Y` being Yes & `N` being No w.r.t to the Repeat Weekly status.

##### `KEYWORD`

* The keyword used to search for contacts.
* `MORE KEYWORDS` also follows this definition.

##### `NAME`

* The name of a contact/groupmate/project.
* Names should only contain alphanumeric characters, and it should not be blank.

##### `PHONE_NUMBER`

* The phone number of a contact.
* Phone numbers should only contain numbers, and it should be at least 3 digits long.

##### `ROLE`

* The role associated with a groupmate in a project.
* Roles should consist of alphanumeric characters, hyphens and underscores.

##### `TAG`

* The tag associated with a contact.
* Tags should consist of alphanumeric characters.

##### `TIME`

* The time of an event.
* Time can be only be entered in one of these formats:
    * 1730
    * 17:30
* The Hour Field (First 2 digits) should be a non-negative number between 00 and 23 (inclusive).
* The Minute Field (Last 2 digits) should be a non-negative number between 00 and 59 (inclusive).

--------------------------------------------------------------------------------------------------------------------

## **3. Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `colab.jar` from [here](https://github.com/AY2021S2-CS2103T-T11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for CoLAB.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`contacts`** and pressing Enter will list all contacts.<br>
   Some example commands you can try:

   * **`project 1`** : Displays the first project.

   * **`addP`**`n/My Project` : Adds a new project named `My Project` to CoLAB.

   * **`todos`** : Displays the todos of the currently displayed project.

   * **`addT`**`1 d/My Task` : Adds a todo with the description `My Task` to the first project.

   * **`deleteP`**`3` : Deletes the third project in the list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#4features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **4. Features**

This section contains documentation on CoLAB's features and commands.

It is split into 4 subsections,
* [Projects](#41-projects)
    * [Todos](#411-todos)
    * [Deadlines](#412-deadlines)
    * [Events](#413-events)
    * [Groupmates](#414-groupmates)
* [Contacts](#42-contacts)
* [Navigating the UI](#43-navigating-the-ui)
* [Others](#44-others)

### **4.1 Projects**

Each Project may have `Todos`, `Deadlines`, `Events` & `Groupmates` that are relevant and specific to it. Commands related to Projects will be discussed first followed by sections on `Todos`, `Deadlines`, `Events` & `Groupmates`.

#### Adding a project: `addP`

Adds a project to CoLAB.

Format: `addP n/NAME`

Examples:
* `addP n/CS2103T Team Project`
* `addP n/CS2101 OP2`

#### Updating a project: `updateP`

Updates a specified project in CoLAB.

Format `updateP PROJECT_INDEX n/NAME`

* Updates name of the project at the specified `PROJECT_INDEX` to new name `NAME`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `updateP 1 n/2103 TP`
* `updateP 2 n/2103 IP` 

#### Deleting a project: `deleteP`

Deletes a specified project from CoLAB.

Format: `deleteP PROJECT_INDEX`

* Deletes a project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteP 1`
* `deleteP 2`

### _**4.1.1 Todos**_

`Todos` contain a `DESCRIPTION` field and are useful for tasks that have no due date.

Each `Project` may have `Todos`. Hence, each of the commands related to `Todos` are done w.r.t the `Project` identified by `PROJECT_INDEX`.

#### Adding a todo to a project: `addT`

Adds a todo to a specified project.

Format: `addT PROJECT_INDEX d/DESCRIPTION`

* Adds a todo to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addT 1 d/Add unit tests`
* `addT 2 d/Finish slides for presentation`

#### Updating a todo of a project: `updateT`

Updates a specified todo of a specified project.

Format: `updateT PROJECT_INDEX i/TODO_INDEX d/DESCRIPTION`

* Updates the description of the todo at the specified `TODO_INDEX` of the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `TODO_INDEX` refers to the number shown beside the todo when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `updateT 1 i/2 d/Review new PR`
* `updateT 2 i/1 d/Merge new PR`

#### Marking a todo as done: `markT`

Marks a todo from a specified project as done.

Format: `markT PROJECT_INDEX i/TODO_INDEX`

* Marks the todo at the specified `TODO_INDEX` from the project at the specified `PROJECT_INDEX` as done.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `TODO_INDEX` refers to the number shown beside the todo when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `markT 1 i/1
* `markT 2 i/3`

#### Deleting a todo from a project: `deleteT`

Deletes a specified todo from a specified project.

Format: `deleteT PROJECT_INDEX i/TODO_INDEX`

* Deletes the todo at the specified `TODO_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `TODO_INDEX` refers to the number shown beside the todo when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `deleteT 1 i/1`
* `deleteT 2 i/2`

### _**4.1.2 Deadlines**_

`Deadlines` contain `DESCRIPTION` & `DATE` field and are useful for tasks that have a due date.

Each `Project` may have `Deadlines`. Hence, each of the commands related to `Deadlines` are done w.r.t the `Project` identified by `PROJECT_INDEX`.

#### Adding a deadline to a project: `addD`

Adds a deadline to a specified project.

Format: `addD PROJECT_INDEX d/DESCRIPTION by/DATE`

* Adds a deadline to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​
* `DATE` must be in `dd-MM-yyyy`, `ddMMyyyy`, `dd/MM/yyyy` or `dd.MM.yyyy` format.
* `DATE` is limited to the `yyyy` range of 0000 to 9999.

Examples:
* `addD 1 d/Milestone v1.2 by/01-03-2021`
* `addD 2 d/CS2101 Project Submission by/15-03-2021`

#### Updating a deadline of a project: `updateD`

Updates a specified deadline of a specified project.

Format: `updateD PROJECT_INDEX i/DEADLINE_INDEX [d/DESCRIPTION] [by/DATE]`

* Updates the description or the deadline date of the deadline at the specified `DEADLINE_INDEX` of the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `DEADLINE_INDEX` refers to the number shown beside the deadline when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​
* `DATE` must be in `dd-MM-yyyy`, `ddMMyyyy`, `dd/MM/yyyy` or `dd.MM.yyyy` format.
* `DATE` is limited to the `yyyy` range of 0000 to 9999.

Examples:
* `updateD 1 i/2 d/Finish v1.3`
* `updateD 2 i/1 by/31-03-2021`

#### Marking a deadline as done: `markD`

Marks a deadline from a specified project as done.

Format: `markD PROJECT_INDEX i/DEADLINE_INDEX`

* Marks the deadline at the specified `DEADLINE_INDEX` from the project at the specified `PROJECT_INDEX` as done.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `DEADLINE_INDEX` refers to the number shown beside the deadline when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `markD 1 i/1
* `markD 2 i/3`

#### Deleting a deadline from a project: `deleteD`

Deletes a specified deadline from a specified project.

Format: `deleteD PROJECT_INDEX i/DEADLINE_INDEX`

* Deletes the deadline at the specified `DEADLINE_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `DEADLINE_INDEX` refers to the number shown beside the deadline when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `deleteD 1 i/1`
* `deleteD 2 i/2`

### _**4.1.3 Events**_

`Events` contain `DESCRIPTION`, `DATE`, `TIME` & `REPEAT_WEEKLY` field and are useful for events that occur on a `DATE` at a `TIME` that may be set to `REPEAT_WEEKLY`.

Each `Project` may have `Events`. Hence, each of the commands related to `Events` are done w.r.t the `Project` identified by `PROJECT_INDEX`.

#### Adding an event to a project: `addE`

Adds an event to a specified project.

Format: `addE PROJECT_INDEX d/DESCRIPTION on/DATE at/TIME w/REPEAT_WEEKLY`

* Adds an event to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​
* `DATE` must be in `dd-MM-yyyy`, `ddMMyyyy`, `dd/MM/yyyy` or `dd.MM.yyyy` format.
* `DATE` is limited to the `yyyy` range of 0000 to 9999.
* `TIME` must be in `HHmm` or `HH:mm` format.
* `TIME` is limited to the `yyyy` range of 0000 to 9999.
* `REPEAT_WEEKLY` must be one of the following values:
    * `Y`
    * `N`
    * `y`
    * `n`
* `Y` being Yes & `N` being No w.r.t to the Repeat Weekly status.

Examples:
* `addE 1 d/Project Meeting on/24-04-2021 at/2000 w/Y`
* `addE 2 d/CS2101 Presentation on/14-04-2021 at/1015 w/n`

#### Updating an event of a project `updateE`

Updates a specified event of a specified project.

Format: `updateE PROJECT_INDEX i/EVENT_INDEX [d/DESCRIPTION] [on/DATE] [at/TIME] [w/REPEAT_WEEKLY]`

* Updates the detail (description, date, time, weekly recurrence) of the event at the specified `EVENT_INDEX` of the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `EVENT_INDEX` refers to the number shown beside the event when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​
* `DATE` must be in `dd-MM-yyyy`, `ddMMyyyy`, `dd/MM/yyyy` or `dd.MM.yyyy` format.
* `DATE` is limited to the `yyyy` range of 0000 to 9999.
* `TIME` must be in `HHmm` or `HH:mm` format.
* `TIME` is limited to the `yyyy` range of 0000 to 9999.
* `REPEAT_WEEKLY` must be one of the following values:
    * `Y`
    * `N`
    * `y`
    * `n`
* `Y` being Yes & `N` being No w.r.t to the Repeat Weekly status.

Examples:
* `updateE 1 i/1 d/Project Meeting on/24-04-2021 w/Y`
* `updateE 2 i/2 on/14-04-2021 at/1015 w/n`

#### Deleting an event from a project: `deleteE`

Deletes a specified event from a specified project.

Format: `deleteE PROJECT_INDEX i/EVENT_INDEX`

* Deletes the event at the specified `EVENT_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `EVENT_INDEX` refers to the number shown beside the event when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `deleteE 1 i/1`
* `deleteE 2 i/2`

### _**4.1.4 Groupmates**_

`Groupmates` contain `NAME` & `ROLE` field and are useful to keep track of `Groupmates` involved in a `Project` with their `ROLE` for the `Project`.

Each `Project` may have `Groupmates`. Hence, each of the commands related to `Groupmates` are done w.r.t the `Project` identified by `PROJECT_INDEX`.

#### Add a groupmate to a project: `addG`

Adds a groupmate to a specified project.

Format: `addG PROJECT_INDEX n/NAME [r/ROLE]…​`

* Adds a groupmate to the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `addG 1 n/John Doe`
* `addG 2 n/Betsy Crowe r/member`

#### Updating a groupmate : `updateG`

Updates an existing groupmate in a specified project.

Format: `updateG PROJECT_INDEX i/GROUPMATE_INDEX [n/NAME] [r/ROLE]…​`

* Updates the groupmate at the specified `GROUPMATE_INDEX` of the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `GROUPMATE_INDEX` refers to the number shown beside the groupmate when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When updating roles, the existing roles of the groupmate will be removed i.e. adding of roles is not cumulative.
* You can remove all the roles of the groupmate by typing `r/` without
  specifying any roles after it.

Examples:
*  `updateG 1 i/1 p/91234567 n/Leslie Knope` Updates the name of the 1st groupmate of the 1st project to be `Leslie Knope`.
*  `updateG 2 i/2 n/Betsy Crower r/` Updates the name of the 2nd groupmate of the 2nd project to be `Betsy Crower` and clears all existing roles.
*  `updateG 2 i/3 n/Betsy Crower r/group-leader r/tester` Updates the name of the 3rd groupmate of the 1st project to be `Betsy Crower` and changes her roles to `group-leader` and `tester`.

#### Deleting a groupmate from a project: `deleteG`

Deletes a specified groupmate from a specified project.

Format: `deleteG PROJECT_INDEX i/GROUPMATE_INDEX`

* Deletes the groupmate at the specified `GROUPMATE_INDEX` from the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The `GROUPMATE_INDEX` refers to the number shown beside the groupmate when viewing the project.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:
* `deleteG 1 i/1`
* `deleteG 2 i/2`

### **4.2 Contacts**

Each Contact may have a `NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS` & multiple `TAGS`. 

#### Adding a contact: `addC`

Adds a contact to CoLAB.

Format: `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addC n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Updating a contact : `updateC`

Updates an existing contact in CoLAB.

Format: `updateC CONTACT_INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Updates the contact at the specified `CONTACT_INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When updating tags, the existing tags of the contact will be removed i.e. adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `updateC 1 p/91234567 e/johndoe@example.com` Updates the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `updateC 2 n/Betsy Crower t/` Updates the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

#### Locating contacts by name: `findC`

Finds contacts whose names contain any of the given keywords.

Format: `findC KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findC John` returns `john` and `John Doe`
* `findC alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'findC alex david'](images/findAlexDavidResult.png)

#### Deleting a contact : `deleteC`

Deletes the specified contact from the address book.

Format: `deleteC CONTACT_INDEX`

* Deletes the contact at the specified `CONTACT_INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteC 2` deletes the 2nd contact in the address book.
* `findC Betsy` followed by `deleteC 1` deletes the 1st contact in the results of the `findC` command.

### **4.3 Navigating the UI**

Although most of the buttons you see on the screen are clickable, the UI has been designed primarily to be navigated using the command line interface.

#### Listing all contacts : `contacts`

Shows a list of all contacts in CoLAB.

Format: `contacts`

#### Viewing a project: `project`

Displays a panel with details of a specified project.

Format: `project PROJECT_INDEX`

* Displays a panel with details of the project at the specified `PROJECT_INDEX`.
* The `PROJECT_INDEX` refers to the number shown beside the project in the side menu.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `project 1` Displays the first project.
* `project 2` Displays the second project.

#### Viewing the overview of a project: `overview`

Views the overview of the project that is currently displayed.

Format: `overview`

* This command can only be used when a project is currently being displayed.

#### Viewing the todos of a project: `todos`

Views the todos of the project that is currently displayed.

Format: `todos`

* This command can only be used when a project is currently being displayed.

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

### **4.4 Others**

#### Saving the data

CoLAB's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

CoLAB's data is saved as a JSON file `[JAR file location]/data/colab.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CoLAB will discard all data and start with an empty data file at the next run.
</div>

#### Clearing all entries : `clear`

Clears all entries from CoLAB. Both contacts and projects will be cleared.

Format: `clear`

#### Undoing previous command : `undo`

Restores CoLAB to the state before the previous undoable command was executed.

Format: `undo'

* Undoable commands are start with `add`, `update` or `delete`.
* All other commands are not undoable.

Example:

* `deleteP 1` Deletes the first project in the list.
* `undo` Reverses the `deleteP 1` command. 

#### Redoing previous command : `redo`

Reverses the most recent undo command.

Format: `redo'

--------------------------------------------------------------------------------------------------------------------

## **5.FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CoLAB home folder.

--------------------------------------------------------------------------------------------------------------------

## **6.Command summary**

### Projects

Action | Format, Examples
--------|------------------
**Add Project** | `addP n/NAME`
**Add Deadline to Project** | `addDto PROJECT_INDEX d/DESCRIPTION by/DATE`
**Add Event to Project** | `addEto PROJECT_INDEX d/DESCRIPTION on/DATE at/TIME w/REPEAT_WEEKLY`
**Add Groupmate to Project** | `addG PROJECT_INDEX n/NAME [r/ROLE]…​`
**Add Todo to Project** | `addTto PROJECT_INDEX d/DESCRIPTION`
**Mark Deadline as done** | `markD PROJECT_INDEX i/DEADLINE_INDEX`
**Mark Todo as done** | `markT PROJECT_INDEX i/TODO_INDEX`
**Update Project** | `updateP PROJECT_INDEX n/NAME`
**Update Deadline** | `updateD PROJECT_INDEX i/DEADLINE_INDEX [d/DESCRIPTION] [by/DATE]`
**Update Event** | `updateE PROJECT_INDEX i/EVENT_INDEX [d/DESCRIPTION] [on/DATE] [at/TIME] [w/REPEAT_WEEKLY]`
**Update Groupmate** | `updateG PROJECT_INDEX i/GROUPMATE_INDEX [n/NAME] [r/ROLE]…​`
**Update Todo** | `updateT PROJECT_INDEX i/TODO_INDEX d/DESCRIPTION`
**Delete Project** | `deleteP PROJECT_INDEX`
**Delete Deadline from Project** | `deleteD PROJECT_INDEX i/DEADLINE_INDEX`
**Delete Event from Project** | `deleteE PROJECT_INDEX i/EVENT_INDEX`
**Delete Groupmate from Project** | `deleteG PROJECT_INDEX i/GROUPMATE_INDEX`
**Delete Todo from Project** | `deleteT PROJECT_INDEX i/TODO_INDEX`

### Contacts

Action | Format, Examples
--------|------------------
**Add Contact** | `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addC n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete Contact** | `deleteC CONTACT_INDEX`<br> e.g., `deleteC 3`
**Update Contact** | `updateC CONTACT_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`updateC 2 n/James Lee e/jameslee@example.com`
**Find Contact** | `findC KEYWORD [MORE_KEYWORDS]`<br> e.g., `findC James Jake`

### Navigating the UI

Action | Format, Examples
--------|------------------
**View Contacts** | `contacts`
**View Project** | `project PROJECT_INDEX`
**View Overview** | `overview`
**View Todos** | `todos`
**View Today Panel** | `today`
**Help** | `help`

### Others

Action | Format, Examples
--------|------------------
**Clear All Entries** | `clear`

## **7.Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
- Libraries used:
    - [JavaFX](https://openjfx.io/)
    - [Jackson](https://github.com/FasterXML/jackson)
    - [JUnit5](https://github.com/junit-team/junit5)
    - [TestFX](https://github.com/TestFX/TestFX)
