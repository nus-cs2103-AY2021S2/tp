---
layout: page
title: User Guide
---

PartyPlanet is a **desktop app for managing birthdays of contacts, optimised for use via a Command Line
Interface** (CLI) while still having the benefits of Graphical User Interface (GUI). If you can type fast,
PartyPlanet can get the planning of your birthday celebrations done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `PartyPlanet.jar` from [here](https://github.com/AY2021S2-CS2103-W16-3/tp/releases).

3. Double-click the file to start the app.<br>
   ![Ui](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n John Doe`.

* Items in square brackets are optional.<br>
  e.g. `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe`.

* Items with `...` after them can be used any number of times.<br>
  e.g. `[-t TAG]...` can be used as ` `, `-t friend`, `-t friend -t family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -p PHONE`, the alternative `-p PHONE -n NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

## Glossary of parameters

| Parameter | Description |
|---|---|
| `ADDRESS` | Any value |
| `BIRTHDAY` | Valid date, with or without a year:{::nomarkdown}<ul><li>Year must be non-negative if specified, and birthday must be in the past</li><li>If the year is incompatible with the date, the closest valid date will be matched<br>e.g. <code>29 Feb 2021</code> is mapped to <code>28 Feb 2021</code></li><li>Accepted date formats are listed below, case-insensitive:<ul><li>ISO format: <code>--01-09</code> / <code>1997-01-09</code></li><li>Dot delimited: <code>9.1</code> / <code>9.1.1997</code></li><li>Slash delimited: <code>9/1</code> / <code>9/1/1997</code></li><li>Long DMY format: <code>9 Jan</code> / <code>9 Jan 1997</code></li><li>Full DMY format: <code>9 January</code> / <code>9 January 1997</code></li><li>Long YMD format: <code>Jan 9</code> / <code>Jan 9 1997</code></li><li>Full YMD format: <code>January 9</code> / <code>January 9 1997</code></li></ul></li></ul>{:/} |
| `COMMAND` | Any valid command listed [below](#party-planet-commands) |
| `DATE` | Valid date with a year:{::nomarkdown}<ul><li>Year must be present and non-negative</li><li>See <code>BIRTHDAY</code> parameter above for available date formats</li></ul>{:/} |
| `DETAIL` | Any value |
| `EMAIL` | In the format `USER@DOMAIN`:{::nomarkdown}<ul><li><code>USER</code> can only contain alphanumerics and any of <code>!#$%&'*+/=?`{&#124;}~^.-</code></li><li><code>DOMAIN</code> must be at least two characters long, start and end with two alphanumerics, and consist only of alphanumerics, periods or hyphens</li></ul>{:/} |
| `INDEX` | Positive integer representing the ID present in the filtered list |
| `NAME` | Any value containing only alphanumerics and spaces |
| `PHONE` | Any number at least three digits long |
| `REMARK` | Any value |
| `SORT_FIELD` | Any valid option, specified below in `list` and `elist` commands |
| `SORT_ORDER` | Any of the following:{::nomarkdown}<ul><li><code>a</code>, <code>asc</code>, <code>ascending</code> (ascending order)</li><li><code>d</code>, <code>desc</code>, <code>descending</code> (descending order)</li></ul>{:/} |
| `TAG` | Any value containing only alphanumeric characters |

<div markdown="block" class="alert alert-info">

**:information_source: Additional notes on parameter parsing:**<br>

* Parameters cannot accept specific syntax that denote a valid prefix within the command.<br>
  e.g. `ADDRESS` fields containing `-a` with leading and trailing spaces (such as in `Blk 123 Yishun -a Singapore 760123`)
  will not parse fully since the `-a` is marked as a separate prefix.

* All parameters will have leading and trailing spaces removed before processing.

</div>

## Party Planet Commands

### Adding contacts : `add`

Adds a person to PartyPlanet's Contacts List.

Format: `add -n NAME [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]​ [-r REMARK]​`<br>

Examples:
* `add -n James Ho -p 22224444 -e jamesho@example.com -a 123, Clementi Rd, 1234665 -t friend -t colleague -b 1 Jan
  -r allergic to nuts` Adds a new person James Ho with specified details.

### Deleting contacts : `delete`

Deletes person(s) from PartyPlanet's Contact List.

Format: `delete [{INDEX [INDEX]... | [--any] -t TAG [-t TAG]...}]`
* If no parameters: `delete`
  * Deletes all persons in the displayed person list
* If provided with index(es): `delete INDEX [INDEX]...`
  * Deletes the person at the specified `INDEX`.
  * All indexes refers to the index number shown in the displayed person list.
* If provided with tags: `delete [--any] -t TAG [-t TAG]...`
  * Delete every person who is tagged with all/any (`--any` specified) of the specified tags, in the displayed person list.

Examples:
* `delete` deletes all contacts in current filtered list
* `delete 3` deletes contact at 3rd index.
* `delete 3 4 5` deletes contacts at 3rd, 4th and 5th index.
* `delete -t colleague -t cs2103` deletes contact with both tag "colleague" and "cs2103".
* `delete --any -t colleague -t cs2103` deletes contacts with either tag "colleague" or tag "cs2103"

### Editing contacts : `edit`

Edits an existing person in PartyPlanet's Contact List.

Format: `edit {INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY] [-r REMARK] | --remove -t TAG [-t TAG}…​}`

* Editing specific person: `edit INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY] [-r REMARK]`
  * Edits the person at the specified `INDEX`.
  * Existing values will be updated to the input values.
  * When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
  * You can remove all the person’s tags by typing `-t` without specifying any tags after it.
* `--remove` flag used: `edit --remove -t TAG [-t TAG}…​`
  * All specified tags will be removed from persons in displayed list.

Examples:
* `edit 2 -n James Lee -e jameslee@example.com` Edits the contact name to be “James Lee” and email address to be “jameslee@example.com”.
* `edit 2 -n Betsy Crower -t` Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.
* `edit --remove -t friends` Removes the `friends` tag from Alex Yeoh and Charlotte Oliveiro.

### Listing contacts : `list`

Shows a list of all persons in PartyPlanet's Contact List.

Format: `list [--exact] [--any] [-n NAME]... [-t TAG]... [-s SORT_FIELD] [-o SORT_ORDER]`
* List out all contacts by default if no arguments specified.
* `-n` and `-t` can be specified to filter the list by name and/or tag.
  * Search is case-insensitive, e.g. `hans` will match `Hans`.
  * Partial matches to names and tags are performed by default, e.g. `lliam` will match `williams`.
  * If exact match is desired, specify an additional `--exact` flag.
  * If multiple names/tags are specified, specifying `--any` filters contacts that fulfill any prefix match.
* `-s` list out all contacts sorted according to `SORT_FIELD`. Possible values of `SORT_FIELD`:
  * `n`: names in ascending lexicographical order
  * `b`: birthdays from oldest to youngest
* `-o` list out all contacts sorted according to `SORT_ORDER`. Possible values of `SORT_ORDER`:
  * `asc`: ascending lexicographical order
  * `desc`: descending lexicographical order

Examples:
* `list` Lists out all the contacts in PartyPlanet.
* `list -s asc` Lists out all the contacts in ascending lexicographical order.
* `list -t friend` Lists out all contacts containing the tag "friend"
* `list -n alice -t friend` Lists out all contacts whose name is "Alice" and have the "friend" tag
* `list --any -n alice -t friend` Lists out all contacts whose name is "Alice" or who have the "friend" tag
* `list --exact -n alice -t friend` Lists out all contacts whose name contain "Alice" and who have tags that contain "friend"
* `list --exact --any -n alice -t friend` Lists out all contacts whose name contain "Alice" or who have tags that contain "friend"

## Event List Commands

### Adding events : `eadd`

Adds an event to PartyPlanet's Events list. Similar to `add` for person contacts.

Format: `eadd -n NAME [-d DATE] [-r DETAIL]`

* The date must be in a valid date format with year, e.g. 2022-05-07, 2 feb 2021

Examples:
* `eadd -n April Fools -d 2021-04-01 -r Prank the april babies!` Adds a new event April Fools with specified details.

### Editing events : `eedit`

Edits an existing event in PartyPlanet's Events List. Similar to `edit`.

Format: `eedit INDEX [-n NAME] [-d DATE] [-r DETAIL]`

* Edits the event at the specified `INDEX`.
  * The index refers to the index number shown in the displayed events list.
* Existing values will be updated to the input values.

Examples:
* `eedit 3 -r Celebrate during first combined practice` Edits the remark of the 3rd event to specified remark.

### Listing events: `elist`

Shows a list of all events in PartyPlanet's Event List. Similar to `list`.

Format: `elist [--exact] [--any] [-n NAME] [-r DETAIL]... [-s SORT] [-o ORDER]`

* List out all events by default if no arguments specified.
* `-n` and `-r` can be specified to filer the list by name and/or detail.
  * Search is case-insensitive, e.g. `cHriStmAs` will match `Christmas`.
  * Partial matches to names and details are performed by default, e.g. `key` will match `turkey`.
  * If exact match is desired, specify an additional `--exact` flag.
  * If multiple names/tags are specified, specifying `--any` filters contacts that fulfill any prefix match.
* -s list out all events sorted according to SORT_FIELD. Possible values of SORT_FIELD:
  * n: names in ascending lexicographical order
  * d: event dates from past to present
* -o list out all events sorted according to SORT_ORDER. Possible values of SORT_ORDER:
  * asc: ascending lexicographical order
  * desc: descending lexicographical order

Examples:
* `elist --exact -n Graduation party -r Get job` Lists out all events whose name is exactly "Graduation party" and remark is exactly "Get job"
* `elist --any -n Christmas -r tarts` Lists out all events whose name contains "Christmas" or whose remarks contain "tarts"
* `elist -s d` Lists out all events in chronological order

### Marking events as done : `edone`

Marks event(s) in PartyPlanet's Events List as done.

Format: `edone INDEX [INDEX]…​`

* `INDEX` must be a positive integer within the number of events in Events List.

Examples:
* `edone 2 3 5` Marks the 2nd, 3rd and 5th events as done.

### Deleting events : `edelete`

Deletes event(s) from PartyPlanet's Events List. Similar to `delete`.

Format: `edelete [INDEX [INDEX]...]`

* If no parameters: `edelete`
  * Deletes all events in the current events list.
* If provided with index(es): `edelete INDEX [INDEX]...`
  * Deletes the event(s) at the specified `INDEX`.
  * All indexes must be a positive integer valid in the list.

Examples:

* `edelete` deletes all events in the current Events List.
* `edelete 1 2 3` deletes events at 1st, 2nd and 3rd indexes.

## General Commmands

### Showing help : `help`

Shows a message explaining a list of available commands.

Format: `help [COMMAND]`
* List all available commands.
* `[COMMAND]` a single parameter requesting help for a specific command's syntax.
* Any additional parameters will be ignored.
* If command is not understood then all available commands will be listed.

Examples:
* `help` lists all available commands.
* `help list` shows the syntax and description for the `list` command.

### Autocomplete: `TAB`

The Autocomplete feature helps autocomplete when editing a Person or an Event to save the user time from retyping details. Currently, the feature only works for commands `edit` and `eedit`.

For any valid and empty prefix that the user inputs, the relevant details will be autocompleted on `TAB` keypress down.

Format: 

Edit: `edit INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY] [-r REMARK] TAB` 

EEdit: `eedit INDEX [-n NAME] [-d DATE] [-r DETAIL] TAB`

Note: Valid INDEX must be used in order for Autocomplete to function.

### Undoing actions : `undo`

Undoes the most recent action that changed PartyPlanet's Contact or Event List.
Can be invoked repeatedly until there is no more history from the current session.

Format: `undo`

Shortcut: `CTRL + Z`

### Redoing actions : `redo`

Redoes the previous action that changed PartyPlanet's Contact or Event List.
Can be invoked repeatedly until there are no more previously executed actions from the current session.

Format: `redo`

Shortcut: `CTRL + SHIFT + Z` or `CTRL + Y`

### Toggle theme : `theme`

Toggles between Dark and Pastel theme

Format: `theme`

### Leaving app : `exit`

Exits the app.

Format: `exit`

### InputHistory / Keyboard shortcuts :

Retrieves previously entered input.

* Entering new command adds new entry to InputHistory.
* InputHistory will save last 20 inputs.
* Pressing `Up` arrow key in the text input panel reverts to earlier input.
* Pressing `Down` arrow key undoes the history revert.
* At the most recent input, pressing `Down` arrow key once more clears the text box.
* `ESC` key clears the text box.
* `CTRL + Z` key combination undoes the last change to the address or event books.
* `CTRL + SHIFT + Z` or `CTRL + Y` key combinations redo the last undone change to the address or event books.

### Coming Soon (Additional Features)
* Archiving of Data Files
* Custom fonts
* Even more themes
* Mass Operations (e.g. mass edit)
* Calendar View
* _and much more!_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: **What is the name of your application?**<br>
**A**: PartyPlanet

**Q**: **How much does it cost?**<br>
**A**: Free!

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add -n NAME [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY] [-r REMARK]` <br> e.g., `add -n James Ho -p 96280000 -t friend -t colleague -r allergic to nuts`
**EAdd** | `eadd -n NAME [-d DATE] [-r REMARK]` <br> e.g. `eadd -n April Fools -d 2021-04-01 -r Prank the april babies!`
**Delete** | `delete [{INDEX [INDEX]…​ | [--any] -t TAG [-t TAG]...}]`<br> e.g. `delete` <br> e.g. `delete 3 4 5` <br> e.g., `delete -t colleague`
**EDelete** | `edelete [INDEX [INDEX]...]` <br> e.g. `edelete 1 2 3`
**EDone** | `edone INDEX [INDEX]…​` <br> e.g. `edone 2 3 5`
**Edit** | `edit {INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY] [-r DETAIL] | --remove -t TAG [-t TAG}…​}`<br> e.g.,`edit 2 -n James Lee -e jameslee@example.com`<br> e.g., `edit --remove -t colleague`
**EEdit** | `eedit INDEX [-n NAME] [-d DATE] [-r DETAIL]` <br> e.g. `eedit 3 -r Celebrate during first combined practice`
**List** | `list [-s SORT_ORDER]`<br> e.g., `list`<br> e.g., `list -s asc`
**EList** | `elist [--exact] [--any] [-n NAME] [-r DETAIL] ... [-s SORT] [-o ORDER]` <br> e.g. `elist --any -n Christmas -r tarts`
**Undo** | `undo`
**Redo** | `redo`
**Help** | `help [COMMAND]`<br> e.g., `help`<br> e.g.,`help list`
**Toggle theme** | `theme`
**Exit** | `exit`

--------------------------------------------------------------------------------------------------------------------

**Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)
* Image used: [VectorStock](https://www.vectorstock.com/royalty-free-vector/birthday-hat-cartoon-vector-22619168)
