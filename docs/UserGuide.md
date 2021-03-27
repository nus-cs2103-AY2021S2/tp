---
layout: page
title: User Guide
---

# Welcome to Focuris User Guide!

Focuris is a **desktop application** for **managing events** with a KanBan board, which is a board where your **events displayed according to the level of completion** of each event, which consists of Backlog, Todo, In-Progress and Done.

## Here's why you should use Focuris:

- Focuris is made specially for **university students** like you to keep track of your tasks to complete.
- Use of a Command Line Interface (CLI) allows fast typists to manage your event boards faster than applications with a Graphical User Interface (GUI).
- Gain ability to prioritize your tasks by looking at their priority level, from **Low** to **High**.
- Get instant overview on the level of completion of each of your tasks.

## Table Of Contents

<!-- prettier-ignore-start -->
<!-- AUTO-GENERATED TOC - START -->

* Table of Contents
{:toc}

<!-- AUTO-GENERATED TOC - END -->
<!-- prettier-ignore-end -->

---

## 1. Quick start

1. Ensure you have Java `11` or above installed on your Computer.

1. Download the latest `focuris.jar` from [here](https://github.com/AY2021S2-CS2103T-W15-4/tp/releases/tag/v1.3.trial).

1. Copy the file to the folder you want to use as the _home folder_ for your Focuris.

1. Double-click the file to start the application. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`todo`**`n/CS2030 d/Assignment` : Adds an event named `CS2030` to the application with status `TODO`.

   - **`log`**`n/CS2040 d/Tutorial` : Adds an event named `CS2040` to the application with status `BACKLOG`.

   - **`prog`**`n/CS2100 d/Lab` : Adds an event named `CS2100` to the application with status `IN PROGRESS`.

   - **`delete`**`3` : Deletes the event with the identifier of 3 shown on the KanBan board.

   - **`exit`** : Exits the application.

1. Refer to the [Features](#features) below for details of each command.

[Return to Table of Contents](#table-of-contents)

## 2. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo n/NAME d/DESCRIPTION`, `NAME` is a parameter which can be used as `todo n/CS2030`.

- Items in square brackets are optional.<br>
  e.g `n/NAME d/DESCRIPTION [p/PRIORITY]` can be used as `n/CS2030 d/Assignement p/HIGH` or as `n/CS2030 d/Assignment`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME d/DESCRIPTION`, `d/DESCRIPTION n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/walk d/run`, only `d/run` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

[Return to Table of Contents](#table-of-contents)

### 2.1 Summary

#### 2.1.1 Command Summary

| Command                           | Description                                         | Format, Examples                                                                                           |
| --------------------------------- | --------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| [**Todo**](#231-todo-command)     | Creates a new event with status **Todo**            | `todo n/NAME d/DESCRIPTION [p/PRIORITY]` <br> e.g., `todo n/CS2040 d/Assignment`                           |
| [**Log**](#232-log-command)       | Creates a new event with status **Backlog**         | `log n/NAME d/DESCRIPTION [p/PRIORITY]` <br> e.g., `log n/CS2030 d/Lab`                                    |
| [**Prog**](#233-prog-command)     | Creates a new event with status **In-Progress**     | `prog n/NAME d/DESCRIPTION [p/PRIORITY]` <br> e.g., `prog n/CS2100 d/Tutorial`                             |
| [**Done**](#235-done-command)     | Changes the status of an existing event to **Done** | `done IDENTIFIER` <br> e.g., `done 2`                                                                      |
| [**Delete**](#237-delete-command) | **Deletes** an existing event                       | `delete IDENTIFIER`<br> e.g., `delete 3`                                                                   |
| [**Edit**](#234-edit-command)     | **Edits** an existing event's attributes            | `edit IDENTIFIER [n/NAME] [s/STATUS] [d/DESCRIPTION] [p/PRIORITY]`<br> e.g.,`edit 2 n/CS2030 d/Assignment` |
| [**Find**](#236-find-command)     | **Finds** an existing event by specific keywords    | `find KEYWORD [KEYWORD]...`<br> e.g., `find James Jake`                                                    |
| [**Help**](#221-help-command)     | Displays **help menu** pop-up                       | `help`                                                                                                     |
| [**Exit**](#222-exit-command)     | **Exits** the application                           | `exit`                                                                                                     |

[Return to Table of Contents](#table-of-contents)

#### 2.1.2 Attribute Summary

| [Attribute](#attributes) | Prefix    | Description                              | Constraints                                                                                                                                             |
| ------------------------ | --------- | ---------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------- |
| NAME                     | `n/`      | Name of your Event                       | Event names should not contain any [special characters](#special-characters) or be blank                                                                |
| DESCRIPTION              | `d/`      | Description of your Event                | Event descriptions should not contain any [special characters](#special-characters) or be blank                                                         |
| PRIORITY                 | `p/`      | Priority of your Event                   | Event priorities should be either `high`, `medium` or `low`. Priorities are case insensitive, meaning `high` or `HiGH` would be understood the same way |
| IDENTIFIER               | No Prefix | Identifier of your Event in Focuris      | Event identifier should exist in the KanBan board.                                                                                                      |
| KEYWORD                  | No Prefix | Keywords for searching Events in Focuris | No Constraints                                                                                                                                          |

[Return to Table of Contents](#table-of-contents)

### 2.2 General

#### 2.2.1 `help` Command

Shows a message explaining how to access the help page.

##### Format: `help`

##### Expected Outcome:

- Pop-up displays link to the user guide.

![help message](images/helpMessageNew.png)

[Return to Table of Contents](#table-of-contents)

#### 2.2.2 `exit` Command

Exits the application.

##### Format: `exit`

##### Expected Outcome:

- Application window closes.

[Return to Table of Contents](#table-of-contents)

#### 2.2.3 Saving of your data

Event data in Focuris is saved **automatically** whenever you execute any command that makes changes to events. As such, there is no need for you to trigger manually saving of data.

[Return to Table of Contents](#table-of-contents)

### 2.2.4 Editing your data file

Focuris' data is saved as a JSON file `[JAR_FILE_LOCATION]/data/eventbook.json`.

- JAR_FILE_LOCATION: The folder in which your `eventbook.jar` application is stored in.

<div markdown="block" class="alert alert-info">**Advanced Users:**
If you are an advanced user, you are welcome to update the data file by editing the data file directly.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Focuris will discard all your existing data and start with an empty data file on the next run.
</div>

[Return to Table of Contents](#table-of-contents)

### 2.3 Event Commands

### 2.3.1 `todo` Command

Adds an event with status `TODO` to Focuris.

Format: `todo n/NAME d/DESCRIPTION [p/PRIORITY]`

| Example Command                                         | Expected Outcome |
| ------------------------------------------------------- | ---------------- |
| `todo n'Household Chores d/Cleaning the kitchen p/high` | Hello world      |

Examples:

- `todo n/Household Chores d/Cleaning the kitchen p/HIGH`
- `todo n/CS2030 d/Assignment p/LOW`
- `todo n/Lunch with John d/At VivoCity p/MEDIUM`
- `todo n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

Expected Outcome:

[Return to Table of Contents](#table-of-contents)

### 2.3.2 `log` Command

Adds an event with status BACKLOG to Focuris.

Format: `log n/NAME d/DESCRIPTION [p/PRIORITY]`

Examples:

- `log n/Household Chores d/Cleaning the kitchen p/HIGH`
- `log n/CS2030 d/Assignment p/MEDIUM`
- `log n/Lunch with John d/At VivoCity`
- `log n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

[Return to Table of Contents](#table-of-contents)

### 2.3.3 `prog` Command

Adds an event with status `IN PROGRESS` to Focuris.

Format: `prog n/NAME d/DESCRIPTION [p/PRIORITY]`

Examples:

- `prog n/Household Chores d/Cleaning the kitchen`
- `prog n/CS2030 d/Assignment p/LOW`
- `prog n/Lunch with John d/At VivoCity`
- `prog n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

[Return to Table of Contents](#table-of-contents)

### 2.3.4 `edit` Command

Edits an existing event in Focuris.

Format: `edit IDENTIFIER [n/NAME] [d/DESCRIPTION] [s/STATUS] [p/PRIORITY]`

- Edits the event at the specified `IDENTIFIER`.
- The identifier refers to the index number shown in the respective displayed event list.
- The identifier **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:

- `edit 1 n/CS2030 d/Assignment` Edits the event name and event description of the 1st event to be `CS2030` and `Assignment` respectively.
- `edit 2 s/log ` Edits the status of the 2nd event to be `BACKLOG`

[Return to Table of Contents](#table-of-contents)

### 2.3.5 `done` Command

Changes the specified event status to DONE in Focuris.

Format: `done IDENTIFIER`

- Changes the event status at the specified `IDENTIFIER` to DONE.
- The identifier refers to the index number shown in the displayed event list.
- The identifier **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `done 2` changes the status of the 2nd event to DONE in Focuris.
- `find CS2100` followed by `done 1` changes the status of the 1st event to DONE in the results of the `find` command.

[Return to Table of Contents](#table-of-contents)

### 2.3.6 `find` Command

Finds events whose names contain any of the given keywords.

Format: `find KEYWORD [KEYWORD]...`

- The search is case-insensitive. e.g `cs2040` will match `CS2040`
- The order of the keywords does not matter. e.g. `Household Chores` will match `Chores Household`
- Only the name is searched.
- Only full words will be matched e.g. `Chore` will not match `Chore`
- Events matching at least one keyword will be returned (i.e. OR search). e.g. `Household` will return `Household Tidy`, `Household Clean`

Examples:

- `find CS2103` returns `CS2103` and `CS2103T`
- `find assignment` returns `CS2101 assignment`, `CS2103 assignment`

[Return to Table of Contents](#table-of-contents)

### 2.3.7 `delete` Command

Deletes the specified event from Focuris.

Format: `delete IDENTIFIER`

- Deletes the event at the specified `INDEX`
- The identifier refers to the index number shown in the displayed event list.
- The identifier **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd event in Focuris.
- `find CS2100` followed by `delete 1` deletes the 1st event in the results of the `find` command.

[Return to Table of Contents](#table-of-contents)

## 3. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Focuris home folder.

[Return to Table of Contents](#table-of-contents)

## 4. Glossary

<a name="special-characters"><bold>Special Characters</bold>: Characters that are neither alphabets, from A to Z, nor digits, from 0 to 9.</a>

<a name="attributes"><bold>Attributes</bold>: Attributes are names of different inputs that a command takes in.</a>

[Return to Table of Contents](#table-of-contents)
