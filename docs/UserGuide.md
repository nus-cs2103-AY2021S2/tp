---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction
**SunRez** is a desktop app designed for college residential staff to efficiently manage student housing services. It is optimized for use via a Command Line Interface (CLI).

**SunRez** has the following features:
* Keeps track of maintenance issues
* Keeps track of student records
* Keeps track of room records

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `sunrez.jar` from [here](https://github.com/AY2021S2-CS2103-T14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SunRez.

4. Double-click the file to start the app. A GUI like the one pictured below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`rlist`** : Lists all residents.

    * **`radd`**`n/Joseph Tan p/84666774 e/e0103994@u.nus.edu y/2` : Adds a resident named `Joseph Tan` with phone number `84666774`, email `e0103994@u.nus.edu`, a 2nd year student.

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
  e.g. `d/DESCRIPTION [t/TIMESTAMP]` can be used as `d/Broken chair t/2020/3/23 11:59pm` or as `d/Broken chair`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
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
* `radd n/John Doe p/91234567 e/e0123456@u.nus.edu y/3` Adds a resident named `John Doe` with phone number `91234567`, email `e0123456@u.nus.edu`, and as a 3rd year student, without any room allocated.


### List all residents : `rlist`

Shows a list of all residents in the system sorted by alphabetical order.

Format: `rlist`


### Find residents : `rfind`

Finds residents whose names contain any of the given keywords.

Format: `rfind KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Residents matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `rfind john` returns `john` and `John Doe`.
* `rfind alex david` returns `Alex Yeoh`, `Alexander Graham`, and `David Li`.


### Edit a resident record : `redit`

Edits the existing resident record at a specified index.

Format: `redit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]`
* `INDEX` refers to the index number shown in the displayed resident list. `INDEX` **must be a positive integer: 1, 2, 3, …**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `redit` will be blocked if the resident has an active room allocation. `dealloc` before making further edits. 

Example:
* `redit 1 p/91234567 e/e0123456@u.nus.edu` Edits the phone number and email address of the 1st resident to be `91234567` and `e0123456@u.nus.edu` respectively.


### Delete a resident : `rdel`

Deletes the resident record at a specified index.

Format: `rdel INDEX`
* `INDEX` refers to the index number shown in the displayed resident list. `INDEX` **must be a positive integer 1,2,3, ...**.
*  A resident `allocated` to a room cannot be deleted until it is first `deallocated`. 

Example:
* `rdel 1` deletes the 1st resident in the resident list.


### Add a room : `oadd`

Adds a room to the housing management system.

Format: `oadd r/ROOM_NO t/TYPE o/OCCUPATION_STATUS [g/TAG]`

Example:
* `oadd r/10-112 t/corridor_ac o/Y g/SHN` Adds a room numbered `10-112` of type `corridor_ac` with the tag `SHN` and occupation status `Y(es)`.


### List all rooms : `olist`

Shows a list of all rooms in the system sorted by room number.

Format: `olist`


### Find rooms : `ofind`

Finds all rooms that contain any of the given keywords.

Format: `ofind KEYWORD [MORE_KEYWORDS]`
* The search matches any part of the room number. e.g. `10` will match `10-111` and `14-101`.
* The order of the keywords does not matter. e.g. `11- 10-` will match `10-100`, `10-101`, `11-100`, and `11-101`.
* Only the room number is searched.
* Rooms matching at least one keyword will be returned (i.e. OR search). e.g. `10 20` will return `10-100`, `11-120`.

Examples:
* `ofind 10-` returns `10-100`, `10-101`, and `10-102`.
* `ofind 10- 15-` returns `10-100`, `10-101`, `15-100`, and`15-101`.
* `ofind 10` returns `09-100`, `09-110`, `10-100`, and `10-101`.


### Edit a room record : `oedit`

Edits the existing room record at a specified index.

Format: `oedit INDEX [r/ROOM_NO] [t/TYPE] [o/OCCUPATION_STATUS] [g/TAG]`
* `INDEX` refers to the index number shown in the displayed room list. `INDEX` **must be a positive integer 1, 2, 3, …**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Example:
* `oedit 1 o/Y g/SHN` Edits the status of the 1st room and tag to be `Occupied` and `Y` respectively.


### Delete a room : `odel`

Deletes the room at a specified index.

Format: `odel INDEX`
* `INDEX` refers to the index number shown in the displayed resident list. `INDEX` **must be a positive integer 1,2,3, ...**.

Example:
* `odel 1` Deletes the 1st room in the room list.

### Allocate resident to room 
Allocates an existing resident to an existing room. 

Format: `allocate r/NAME n/ROOM_NO`
* `NAME` and `ROOM_NO` must already exist. 
*  Both fields must be provided. 

Example:
* `alloc r/John Tan n/03-100`

### Deallocate resident from room
Deallocates an existing resident from an existing room.

Format: `dealloc r/NAME n/ROOM_NO`
* `NAME` and `ROOM_NO` must already exist.
* The allocation must already exist. 
*  Both fields must be provided.

Example:
* `dealloc r/John Tan n/03-100`

### Add an open issue : `iadd`

Adds an issue to the housing management system.

Format: `iadd r/ROOM_NO d/DESCRIPTION [t/TIMESTAMP] [s/STATUS] [c/CATEGORY]`

Example:
* `iadd r/10-100 d/Broken light c/Furniture` Creates an issue for room number `10-100` with description `Broken light` under the category `Furniture`.


### List all issues : `ilist`

Shows a list of all issues in the system sorted by their timestamp.

Format: `ilist`


### Find issues : `ifind`

Finds all issues that contain any of the given keywords.

Format: `ifind KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g. `broken` will match `Broken`.
* The order of the keywords does not matter. e.g. `Broken light` will match `light broken`.
* Only the description is searched.
* Issues matching at least one keyword will be returned (i.e. OR search). e.g. `Broken window` will return `Broken light`, `Dirty window`, and `Broken window`.

Examples:
* `ifind chair` returns `Broken chair` and `Chair missing wheel`.
* `ifind wardrobe table` returns `Wardrobe door broke`, `Table unstable`, and `Table stuck in wardrobe`.

### Edit an issue record : `iedit`

Edits the existing issue record at a specified index.

Format: `iedit INDEX [r/ROOM] [d/DESCRIPTION] [t/TIMESTAMP] [s/STATUS] [c/CATEGORY]`
* `INDEX` refers to the index number shown in the displayed issue list. `INDEX` **must be a positive integer 1, 2, 3, …**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Example:
* `iedit 1 r/20-109 s/Closed` Edits the room number and status of the 1st issue to be `20-109` and `Closed` respectively.


### Close an issue : `iclose`

Marks as closed the issue at a specified index.

Format: `iclose INDEX`
* `INDEX` refers to the index number shown in the displayed issue list. `INDEX` **must be a positive integer 1, 2, 3, …**.

Example:
* `iclose 1` Closes the 1st issue.


### Delete an issue : `idel`

Deletes the issue at a specified index.

Format: `idel INDEX`
* `INDEX` refers to the index number shown in the displayed resident list. `INDEX` **must be a positive integer 1,2,3, ...**.

Example:
* `idel 1` Deletes the 1st issue.

### View Command History : `history`

Displays the user's valid command history, sorted from most to least recent.

Format: `history [COUNT]`
* `COUNT` refers to the number of most recent command entries to display. `COUNT` **must be a positive integer 1,2,3, ...**.

Examples:
* `history` Displays all command entries.
* `history 5` Displays the 5 most recent command entries.

### Add alias : `alias`

Adds a user-defined alias, which represents a shortcut to a longer command.

Format: `alias a/ALIAS_NAME cmd/COMMAND`

Examples:
* `alias a/ol cmd/olist` Adds `ol` alias which is a shortcut for `olist` command.
* `alias a/fNemo cmd/rfind Nemo` Adds `fNemo` alias which is a shortcut for `rfind Nemo` command.

### Exit the program : `exit`

Exits the program.

Format: `exit`

### Access Command History

Previous successful commands can be accessed via the UP and DOWN arrow keys on the keyboard. UP selects the previous command. DOWN selects the next command.

Example usage:
1. Enter some commands as per normal.
1. Make sure the command box is in focus (e.g. click on it).
1. Press the UP arrow key on your keyboard.
1. SunRez should display the most recent successful command in the command box.
    * Hit ENTER to run that command again.
    * Alternatively, select other commands via the UP and DOWN arrow keys.

### Save the data

SunRez data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Edit the data file

SunRez data is saved as a JSON file `[JAR file location]/data/sunrez.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">
**Caution**: <br>
If your changes to the data file makes its format invalid, SunRez will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous **SunRez** home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Show help** | `help`
**Add a resident** | `radd n/NAME p/PHONE_NUMBER e/EMAIL y/YEAR` <br> e.g. `radd n/Joseph Tan p/84666774 e/e0103994@u.nus.edu y/2 r/01-234`
**List all residents** | `rlist`
**Find residents** | `rfind KEYWORD [MORE_KEYWORDS]` <br> e.g. `rfind bob bobby`
**Edit a resident record** | `redit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/ROOM]` <br> e.g. `redit 1 p/91234567 e/e0123456@u.nus.edu`
**Delete a resident** |  `rdel INDEX` <br> e.g. `rdel 1`
**Add a room** |  `oadd r/ROOM_NO t/TYPE o/OCCUPATION_STATUS [g/TAG]` <br> e.g. `oadd n/17-101 t/corridor_ac o/Y g/SHN`
**List all rooms** |  `olist`
**Find rooms** |  `ofind KEYWORD [MORE_KEYWORDS]` <br> e.g. `ofind 10- 15-`
**Edit a room record** |  `oedit INDEX [r/ROOM_NO] [t/TYPE] [g/TAG] [o/OCCUPATION_STATUS]` <br> e.g. `oedit 1 o/Y`
**Delete a room** | `odel INDEX` <br> e.g. `odel 1`
**Allocate a Resident to Room** | `alloc n/NAME r/ROOM_NO` <br> e.g. `alloc n/John Tan r/03-100` 
**Deallocate a Resident from Room** | `dealloc n/NAME r/ROOM_NO` <br> e.g. `dealloc n/John Tan r/03-100`
**Add an open issue** | `iadd r/ROOM_NO d/DESCRIPTION [t/TIMESTAMP] [s/STATUS] [c/CATEGORY]` <br> e.g. `iadd r/10-100 d/Broken light c/Furniture`
**List all issues** | `ilist`
**Find issues** | `ifind KEYWORD [MORE_KEYWORDS]` <br> e.g. `ifind wardrobe table`
**Edit an issue record** | `iedit INDEX [r/ROOM] [d/DESCRIPTION] [t/TIMESTAMP] [s/STATUS] [c/CATEGORY]` <br> e.g. `iedit 1 r/20-109 s/Closed`
**Close an issue** | `iclose INDEX` <br> e.g. `iclose 1`
**Delete an issue** | `idel INDEX` <br> e.g. `idel 1`
**View command history** | `history [COUNT]` <br> e.g. `history 5`
**Add alias** | `alias a/ALIAS_NAME cmd/COMMAND` <br> e.g. `alias a/il cmd/ilist`
**Exit the app** | `exit`
