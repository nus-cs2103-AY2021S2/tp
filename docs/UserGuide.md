---
layout: page
title: User Guide
---

NUS Module Planner is a **desktop app for NUS students to manage and plan the modules to enrol in upcoming semesters, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUS Module Planner can get your module planning done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `XYZ.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all contacts.

   - **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   - **`clear`** : Deletes all contacts.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


# User Commands
## General Commands

### Check graduation : `validate`

Format: `validate [p/PLAN_NUMBER]`

If the optional argument `[p/PLAN_NUMBER]` is provided:

- Shows header
    - Shows plan number
    - Shows how many MCs the plan has
    - Shows how many MCs completed
    - Shows how many semesters remaining in plan
- Shows rows of modules placed in respective semesters
    - Shows X tables of X semesters
    - Each table has a header of how many MCs the semester will have
    - Each table shows module details

Otherwise:

- Shows list of plans that are still valid
    - Each row is a plan
        - Each plan has 4 column attributes:
            - Shows plan number
            - Shows how many MCs the plan has
            - Shows how many MCs completed
            - Shows how many semesters remaining in plan

> Tip: A plan is valid if the modules contained in its history match those of the current Master Plan.
> This ensures that any valid plan is a viable option for the user.

## Plan commands
### List a summary of all plans: `list plans`

Format: `list plans`
Tip: A user can view an individual plan to see more details about it. (See show p/PLAN_NUMBER)

Format: `master p/PLAN_NUMBER`

This command must be done by the user at least once before they can use other commands.
Marks the given plan as the master plan, and this plan should contain all the modules that the user has taken (if any).

Example output:
[IMG]

### Create Plan: `add/delete`

Format: `add/delete p/PLAN_NUMBER`

Shows 2 rows:
- Whether plan is added/deleted is successful/unsuccessful
- Plan number

Constraints:
- Trying to add a plan that already exist will not be allowed
- Trying to delete a plan that does not exist will not be allowed

### Set Plan as Master Plan: `master`

Format: `master p/PLAN_NUMBER`

This command must be done by the user at least once before they can use other commands.
Marks the given plan as the master plan, and this plan should contain all the modules that the user has taken (if any).

Example output:
[IMG]

## Semester commands

### Show the number of MCs the user is currently taking: `show MCs`
Format: `Show MCs`

Example output: `Current MCs this semester: xxx`

### Calculate and show the current CAP (Cumulative academic points) of the student: `show CAP`
Format: `Show CAP`

This command takes in the grades of modules user has marked as completed and entered their grade, and calculate their CAP using this formula:

![modular-system](https://user-images.githubusercontent.com/67280376/109455909-9e9f8380-7a92-11eb-9ea1-f49801578a95.png)


Example output `Current CAP is: xxx`

### Add/Delete Semester to/from Plan: `add/delete`
Format for adding: `add p/PLAN_NUMBER s/SEM_NUMBER`

Format for deleting: `delete p/PLAN_NUMBER s/SEM_NUMBER`

The output will show whether the operation was successful and include the semester number in its output.

Constraints:
* Trying to add a semester that already exist will not be allowed
* Trying to delete a semester that does not exist will not be allowed

### Set Semester as in-progress: `current semester`
Format: `current s/SEM_NUMBER`

Marks the supplied semester as the current semester of the master plan.
This indicates that all previous semesters are part of the user’s history and all future semesters have yet to be attempted.
The user will have to manually update the current semester as time progresses.

Example output:
[IMG]


### Show history: `history`
Format: `history`

The above command takes no arguments and shows the user a list of modules that they have completed up until before the *current semester*.

> Tip: The *current semester* is the semester that was marked using the `semester current` command.

Example output:
[IMG]




## Module commands

### Add/Delete module to/from semester: `add/delete`
Format for adding: `add m/MODULE_CODE p/PLAN_NUMBER s/SEM_NUMBER`

Format for deleting: `delete m/MODULE_CODE p/PLAN_NUMBER s/SEM_NUMBER`

> Tip: A user can view module info to see more details about it. (See `info`)

This command takes in three arguments, `MODULE_CODE`, `PLAN_NUMBER` and `SEM_NUMBER`, and outputs meta details about the module being added/deleted, as well as whether the addition/deletion was successful or not.   

The details to output are as follows:

* Module addition/deletion success status
* Semester number
* Module code

## Semester commands
### Show the number of MCs the user is currently taking: `show MCs`
Format: `Show mcs`

Example output: `The current MCs you are taking is xxx`

### Calculate and show the current CAP (Cumulative academic points) of the student: `show CAP`
Format: `show cap`

This command takes in the grades of modules user has marked as completed and entered their grade, and calculate their CAP using this formula:

![modular-system](https://user-images.githubusercontent.com/67280376/109455909-9e9f8380-7a92-11eb-9ea1-f49801578a95.png)

Example output `Current CAP is xxx`

### Add/Delete Semester to/from Plan: `add/delete`
Format for adding: `add p/PLAN_NUMBER s/SEM_NUMBER`

Format for deleting: `delete p/PLAN_NUMBER s/SEM_NUMBER`

The output will show whether the operation was successful and include the semester number in its output.

Constraints:
* Trying to add a semester that already exist will not be allowed
* Trying to delete a semester that does not exist will not be allowed


### Set Semester as in-progress: `semester current`
Format: `sem s/SEM_NUMBER current/`

Marks the supplied semester as the current semester of the master plan.
This indicates that all previous semesters are part of the user’s history and all future semesters have yet to be attempted.
The user will have to manually update the current semester as time progresses.

Example output:
[IMG]


### Show history: `history`

Format: `history`

The above command takes no arguments and shows the user a list of modules that they have completed up until before the *current semester*.

> Tip: The *current semester* is the semester that was marked using the `semester current` command.

Example output:
[IMG]




## Module commands

### Add/Delete module to/from semester: `add/delete`
Format for adding: `add m/MODULE_CODE p/PLAN_NUMBER s/SEM_NUMBER`

Format for deleting: `delete m/MODULE_CODE p/PLAN_NUMBER s/SEM_NUMBER`

> Tip: A user can view module info to see more details about it. (See `info`)

This command takes in three arguments, `MODULE_CODE`, `PLAN_NUMBER` and `SEM_NUMBER`, and outputs meta details about the module being added/deleted, as well as whether the addition/deletion was successful or not.
The details to output are as follows:
* Module addition/deletion success status
* Semester number
* Module code

Constraints:
* Trying to add a module that already exists will not be allowed
* Trying to add/delete a nonexistent module code/plan number/semester number will not be allowed


Prompts:
* Adding a module without prerequisites fulfilled results in a warning

### View module info: `info`
Format: `info m/MODULE_CODE`

> Tip: A user can also add a module to a plan/semester (See `add/delete`)

By default, this command takes in one optional argument, `MODULE_CODE` and outputs the module information including:
* Brief Description
* Number of MCs
* Semesters available
* Pre-requisites
* Preclusions

Constraints:
* Module has to exist

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`
