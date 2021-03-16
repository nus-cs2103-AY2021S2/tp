#User Guide

---

Taskify is a **desktop app for students manage their tasks (academics/personal/CCA) in a systematic and efficient manner, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Taskify can get your task management done faster than traditional GUI apps.

* Table of Contents
  

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `taskify.jar` from [here](https://github.com/AY2021S2-CS2103T-W14-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Taskify.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all tasks.

    * **`add`**`[TASK Description]` : Adds a task with the specified description to the task list

    * **`delete`**`3` : Deletes the task with the specified task number.

    * **`help`** : Show all the commands and formats.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of more commands and details.

--------------------------------------------------------------------------------------------------------------------

## Features
[Coming Soon]

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.



### Command Action: `keyword(first word of command)`

Command Desription

Format: `command format`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `example 1`
* `example 2`


Details coming soon [in v2.0]

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I do something?<br>
**A**: Steps to do something<br>
[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary
[Coming Soon]

Command | Format | Examples
--------|--------|----------
**Name** | `Format`| `Exmaple`
