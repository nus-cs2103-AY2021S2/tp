---
layout: page
title: User Guide
---

# SunRez v1.2 User Guide
SunRez is a **desktop app for managing student housing services, optimized for use via a Command Line Interface** (CLI). SunRez keeps track of maintenance issues, student and room records and offers efficient management for users who can type fast.

- [Quick start](#quick-start)
- [Features](#features)
    * [Show help : `help`](#show-help--help)
    * [Add a resident : `radd`](#add-a-resident--radd)
    * [List all residents : `rlist`](#list-all-residents--rlist)
    * [Find residents : `rfind`](#find-residents--rfind)
    * [Edit a resident record : `redit`](#edit-a-resident-record--redit)
    * [Delete a resident : `rdel`](#delete-a-resident--rdel)
    * [Add a room : `oadd`](#add-a-room--oadd)
    * [List all rooms : `olist`](#list-all-rooms--olist)
    * [Find rooms : `ofind`](#find-rooms--ofind)
    * [Edit a room record : `oedit`](#edit-a-room-record----oedit)
    * [Delete a room : `odel`](#delete-a-room----odel)
    * [Add an open issue : `iadd`](#add-an-open-issue--iadd)
    * [List all issues : `ilist`](#list-all-issues--ilist)
    * [Find issues : `ifind`](#find-issues--ifind)
    * [Edit an issue record : `iedit`](#edit-an-issue-record--iedit)
    * [Close an issue : `iclose`](#close-an-issue--iclose)
    * [Delete an issue : `idel`](#delete-an-issue--idel)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `sunrez.jar` from [here](https://github.com/AY2021S2-CS2103-T14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SunRez.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`rlist`** : Lists all residents.

    * **`radd`**`n/Joseph Tan p/84666774 e/e0103994@u.nus.edu y/2` : Adds a resident named `Joseph Tan` with phone number `84666774`, email `e0103994@u.nus.edu`, and as a 2nd year student.

    * **`rdel`**`3` : Deletes the 3rd resident shown in the current resident list.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `d/DESCRIPTION [t/TIMESTAMP]` can be used as `d/Broken chair t/2020-03-23` or as `d/Broken chair`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/John Doe n/Jane Doe`, only `n/Jane Doe` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `d/DESCRIPTION [t/TIMESTAMP]` can be used as `d/Broken chair t/2020-03-23` or as `d/Broken chair`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/John Doe n/Jane Doe`, only `n/Jane Doe` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Show help : `help`

Shows a message explaining how to access the help page.

Format: `help`


### Add a resident : `radd`

Adds a resident to the housing management system.

Format: `radd n/NAME p/PHONE_NUMBER e/EMAIL y/YEAR`

Examples:

* `radd n/Joseph Tan p/84666774 e/e0103994@u.nus.edu y/2` Adds a resident named `Joseph Tan` with phone number `84666774`, email `e0103994@u.nus.edu`, and as a 2nd year student.
* `radd n/John Doe p/91234567 e/e0123456@u.nus.edu y/3` Adds a resident named `John Doe` with phone number `91234567`, email `e0123456@u.nus.edu`, and as a 3rd year student.


### List all residents : `rlist`

Shows a list of all residents in the system sorted by alphabetical order.

Format: `rlist`


### Find residents : `rfind`

Finds residents whose names contain any of the given keywords.

Format: `rfind KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Residents matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

* `rfind john` returns `john` and `John Doe`.
* `rfind alex david` returns `Alex Yeoh`, `Alexander Graham`, and `David Li`.


### Edit a resident record : `redit`

Edits an existing resident record.

Format: `redit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]`

* Edits the resident record at the specified `INDEX`. The index refers to the index number shown in the displayed resident list. The index must be a positive integer: 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Example:

* `redit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st resident to be `91234567` and `johndoe@example.com` respectively.


### Delete a resident : `rdel`

Deletes a resident record at the specified index.

Format: `rdel INDEX`
* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed resident list.
* The index **must be a positive integer 1,2,3, ...**

Example:

* `rdel 1` deletes the 1st resident in the resident list.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary


Action | Format, Examples
--------|------------------
**Show help** | `help`
**Add a resident** | `radd n/NAME p/PHONE_NUMBER e/EMAIL y/YEAR` <br> e.g. `radd n/Joseph Tan p/84666774 e/e0103994@u.nus.edu y/2`
**List all residents** | `rlist`
**Find residents** | `rfind KEYWORD [MORE_KEYWORDS]` <br> e.g. `rfind bob bobby`
**Edit a resident record** | `redit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]` <br> e.g. `redit 1 p/91234567 e/johndoe@example.com`
**Delete a resident** |  `rdel INDEX` <br> e.g. `rdel 1`
