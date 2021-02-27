---
layout: page
title: User Guide
---

App-Ointment is a desktop app for for managing and scheduling patient appointments, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, App-Ointment can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `App-Ointment.jar` from [here](https://github.com/AY2021S2-CS2103-W17-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your App-Ointment App.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : [Coming Soon]

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : [Coming Soon]

   * **`delete`**`3` : [Coming Soon]

   * **`clear`** : [Coming Soon]

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

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

</div>

### Adding a person: `add`
[Coming Soon]
Format:

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div><br>

Examples:

### Listing all persons : `list`
[Coming Soon]
Format:

### Editing a person : `edit`
[Coming Soon]
Format:

Examples:

### Deleting a person : `delete`
[Coming Soon]
Format:

Examples:

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

App-Ointment data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

App-Ointment data are saved as a JSON file `[JAR file location]/data/App-Ointment.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, App-Ointment will discard all data and start with an empty data file at the next run.
</div><br>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous App-Ointment home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary
[Coming Soon]
Action | Format, Examples
--------|------------------
**Add** | 
**Delete** |
**Edit** |
**List** |
