---
layout: page
title: User Guide
---

ResidenceTracker (RT) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RT can get your residence management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `residencetracker.jar` from [here](https://github.com/AY2021S2-CS2103-T16-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ResidenceTracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all residences in the app.

   * **`add`**`n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 clean/n book/y` : Adds a residence named `Clementi HDB` to the ResidenceTracker.

   * **`delete`**`3` : Deletes the 3rd residence shown in the current list.

   * **`clear`** : Deletes all residences.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Amber Heights`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Amber Heights t/friend` or as `n/Amber Heights`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME a/ADDRESS`, `a/ADDRESS n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `a/Jurong West St 60 a/Jurong West St 70`, only `a/Jurong West St 70` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a residence: `add`

Adds a new residence to the list of residences,default for CLEANING_STATUS and BOOKING_STATUS is ‘cleaned’ and ‘not-booked’ respectively.

Format: ` add n/NAME_OF_APARTMENT a/ADDRESS [b/BOOKING_DETAILS] [clean/[y or n]] [t/TAG] `

Examples:
* `add n/Melville Park a/22 Simei Street 1, #10-02, S529948`
* `add n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 b/4 adults clean/n `

### Listing all residences: `list`

Shows a list of all residences in the app.

Format: `list`

### Editing a residence: `edit`

Edits the bookingList/cleaning status of an existing residence

Format: `edit b/3 adults [INDEX] clean/n [INDEX]`

* Edits the residence status at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields (either clean or book status) must be provided.
* If both fields are provided, `INDEX` field should be of the same value.

Examples:
*  `edit clean/y 1` Edits the clean status of the 1st residence on the list from `Unclean` to `Clean`. Booking status remains unchanged from original value.
*  `edit b/3 adults clean/y 2 `  Edits the bookingList details of the 2nd residence on the list from `4 adults` to `3 adults` and the clean status of the same residence on the list from `Unclean` to `Clean`.

### Locating residences by name: `find`

Finds residences whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `duxton` will match `Duxton`
* The order of the keywords does not matter. e.g. `Gardens Bay` will match `Bay Gardens`
* Only the name is searched.
* Only full words will be matched e.g. `Dux` will not match `Duxton`
* Apartments matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Gardens Bay` will return `Botanic Gardens`, `Bay Area`

Examples:
* `find heights` returns `Hillview Heights` and `Aspen Heights`
* `find east coast` returns `East View`, `West Coast`<br>

### Deleting a residence: `delete`

Deletes the specified residence from the list of residences.

Format: `delete INDEX`

* Deletes the residence at the specified `INDEX`.
* The index refers to the index number shown in the displayed residences list (i.e. **NOT** zero-indexed).
* The index must be a **positive integer** (>0)

Examples:
* `list` followed by `delete 3` deletes the 3rd residence in the list of residences.

### Clearing all entries : `clear`

Clears all entries from the residence tracker.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ResidenceTracker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ResidenceTracker data are saved as a JSON file `[JAR file location]/data/ResidenceTracker.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ResidenceTracker will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ResidenceTracker home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME_OF_RESIDENCE a/ADDRESS [b/BOOKING_DETAILS] [clean/[y or n]] [t/TAG] …​` <br> e.g., `add n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 b/4 adults clean/n`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit b/UPDATED_BOOKING_DETAILS [INDEX] clean/[y or n] [INDEX]`<br> e.g.,`edit b/3 adults 2 clean/n 2`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Heights`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
