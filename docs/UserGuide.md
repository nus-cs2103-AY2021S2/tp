---
layout: page
title: User Guide
---


ResidenceTracker (RT) is a **desktop app for managing residences, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RT can get your residence management tasks done faster than traditional GUI apps.

In addition to being able to managing several residences at once with just a few key strokes of the keyboard,
ResidenceTracker helps users to also keep track of any bookings a residence has, overseeing multiple residences with ease. 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `residencetracker.jar` from [here](https://github.com/AY2021S2-CS2103-T16-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ResidenceTracker.

1. Double-click the file to start the app. The GUI similar to that shown below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all residences in the app.
   * **`add`**`n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 c/n` : Adds a residence named `Clementi HDB` to the ResidenceTracker.
   * **`edit`**`1 c/y` : edit the first residence clean status as Clean.
   * **`delete`**`3` : Deletes the 3rd residence shown in the current list.
   * **`clear`** : Deletes all residences.
   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command and do make sure to checkout the Refer to the [FAQ](#faq) section as well.

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

Shows a message with the current list of commands along with examples.
It also provides a button to copy the URL link to access the help page.

Format: `help`

Shortcut: press `F1`

![help message](images/helpMessage.png)

### Adding a residence: `add`

Adds a new residence to the list of residences, default for clean status is ‘clean’. Valid clean status inputs are `y`,`n`, `clean` and `unclean`

* Names cannot be empty. Trailing white spaces before and after a valid name will be ignored. e.g `  BLK 3  ` will be used and displayed as `BLK 3`
* Names should only contain alpha-numeric characters. e.g `Block71 Ayer Rajah`  
* Valid clean statuses are case-insensitive, e.g `c/Y` is the same as `c/y`, `c/clean` is the same as `c/ClEaN`.
* Address can take any values (even emojis) but only alphanumeric characters and symbols will be visible on the residence tracker. `@!df34!@//` is a valid address.
* Tags should only contain alphanumeric characters. Symbols and spaces are not valid.
* Valid tags are case-sensitive so will be used exactly as provided by the user. e.g `POPular` will be used and displayed as `POPular`

Format: ` add n/RESIDENCE_NAME a/ADDRESS [c/VALID_CLEAN_STATUS] [t/TAG]... `

Examples:
* `add n/Melville Park a/22 Simei Street 1, #10-02, S529948`
* `add n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 c/n`

### Listing all residences: `list`

Shows the full list of all residences in the app.

Format: `list`

* The displayed list of residences will be sorted by their clean status.
* Unclean residences come before clean residences.  
* After any edit to a filtered residence list that is returned by commands `find` and `remind`, the GUI displays this full residence list again.

### Listing residences with bookings starting in the next 7 days: `remind`

Shows a list of all residences with bookings starting in the next 7 days.

Format: `remind`

* Next 7 days: If today is 1st April, residences with bookings starting on 2nd April to 8th April (inclusive) will be listed.
* The displayed list of residences is always sorted.
* Unclean residences come before clean residences.

### Editing a residence: `edit`

Edits the given fields of an existing residence (excludes bookings, see `editb` instead to edit bookings).

Format: `edit RESIDENCE_INDEX [n/RESIDENCE_NAME] [a/ADDRESS] [c/VALID_CLEAN_STATUS] [t/TAG]`

* Edits the residence at the specified `RESIDENCE_INDEX`.
* The `RESIDENCE_INDEX` refers to the index number shown in the displayed residence list (i.e. start from index 1).
* The `RESIDENCE_INDEX` must be a **positive integer** 1, 2, 3, …​
* At least one field must be provided.
* If this command is used to edit tags, all tags for this residence need to be specified.
* Address can contain any alphanumeric character and symbols. `@!df34!@//` is considered a valid address.
* Valid clean statuses is case-insensitive, e.g `c/Y` is the same as `c/y`, `c/clean` is the same as `c/ClEaN`.
* Tags should only contain alphanumeric characters, symbols and spaces are not valid.
* Editing of tags overwrites all existing tags.

Examples:
*  `edit 1 c/y` Edits the clean status of the 1st residence on the list to `Clean`.
*  `edit 2 n/Nashville`  Edits the name of the 2nd residence on the list from to `Nashville`.
*  `edit 1 t/tag1 t/tag2` Edits the 1st residence on the list to have tags `tag1` and `tag2`.

### Locating residences by name: `find`

Finds residences whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`

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

Deletes the specified residence from the list of residences shown.

Format: `delete RESIDENCE_INDEX`

* Deletes the residence at the specified `RESIDENCE_INDEX`.
* The `RESIDENCE_INDEX` refers to the index number shown in the displayed residences list (i.e. start from index 1).
* The `RESIDENCE_INDEX` must be a **positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 3` deletes the 3rd residence in the list of residences shown.

### Update multiple Clean status: `status`

Update Clean status of multiple residences at once.

Format: `status clean RESIDENCE_INDEX1 RESIDENCE_INDEX2..` or `status unclean RESIDENCE_INDEX1 RESIDENCE_INDEX2..`

* Updates the clean status of the residences' with the specified `RESIDENCE_INDEX`.
* The `RESIDENCE_INDEX` refers to the index number shown in the displayed residences list (i.e. start from index 1).
* The `RESIDENCE_INDEX` must be a **positive integer** 1, 2, 3, …​
* More than 1 residence indexes can be specified.
* The update will not occur at all if any of the indexes provided is invalid (i.e no partial exceution of the command).
* After updating, the residence list will sort automatically to show Unclean residences before the clean residences.

Examples:
* `status clean 1 3` update the 1st and 3rd residences clean status to `Clean`.
* `status unclean 2 5` update the 2nd and 5th residences clean status to `Unclean`.

### Adding a booking: `addb`

Adds a new booking to the specified residence.

Format: `addb RESIDENCE_INDEX n/TENANT_NAME p/TENANT_PHONE s/START_DATE e/END_DATE`

* Adds a booking to the residence at the specified `RESIDENCE_INDEX`.
* The `RESIDENCE_INDEX` refers to the index number shown in the displayed residences list (i.e. start from index 1).
* The `RESIDENCE_INDEX` must be a **positive integer** 1, 2, 3, …​
* The `TENANT_NAME` is similar to `RESIDENCE_NAME`.
* The phone must only include numbers and must be at least 3 characters long. e.g `p/999` `p/12345678`
* The dates must follow the format DD-MM-YYYY. e.g `s/01-02-2021`
* The `START_DATE` has to be before than the `END_DATE`.
* The booking period `START_DATE` to `END_DATE` should not overlap with the booking period of other bookings for the given residence.

Examples:
* `addb 1 n/John p/91234567 s/01-01-2021 e/02-01-2021`
* `addb 2 n/Jane Tan p/65812567 s/31-12-2021 e/05-01-2022`

### Deleting a booking from a residence: `deleteb`

Deletes the specified booking from the specified residence.

Format: `deleteb r/RESIDENCE_INDEX b/BOOKING_INDEX`

* Deletes the booking at the specified `BOOKING_INDEX` from the residence at the specified `RESIDENCE_INDEX`
* `RESIDENCE_INDEX` and `BOOKING_INDEX` refers to the index number as shown in ResidenceTracker (i.e. start from index 1).
* `RESIDENCE_INDEX` and `BOOKING_INDEX` must be a **positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteb r/3 b/2` deletes the 2nd booking from the 3rd residence.

### Editing a booking: `editb`

Edits the specified booking from the specified residence.

Format: `editb r/RESIDENCE_INDEX b/BOOKING_INDEX [n/TENANT_NAME] [p/TENANT_PHONE] [s/START_DATE] [e/END_DATE]` 

* `RESIDENCE_INDEX` and `BOOKING_INDEX` refers to the index number as shown in ResidenceTracker (i.e. start from index 1).
* `RESIDENCE_INDEX` and `BOOKING_INDEX` must be a **positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* It is invalid to edit the `START_DATE` to be later than the `END_DATE`. Likewise, it is invalid to update the `END_DATE`
to be earlier than the `START_DATE`.
* It is invalid to edit `START_DATE` or `END_DATE` such that it overlaps with the booking period of other bookings.
* Existing booking details will be overwritten by the input values.

Examples:
* `editb r/1 b/2 p/90069009 s/03-28-2021` Edits the phone number and start date of 2nd booking of the 1st residence to be
`90069009` and `03-28-2021` respectively.

### Clearing all entries : `clear`

Clears all entries from the residence tracker. Useful when users wish to clear all the sample data at once.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ResidenceTracker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ResidenceTracker data are saved as a JSON file `[JAR file location]/data/residencetracker.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ResidenceTracker will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ResidenceTracker home folder.

**Q**: What does the colour of the bookings respresent?<br>
**A**: The colours are as follows
* Red - refers to bookings that have passed
* Orange - refers to bookings that are ongoing (A booking that starts or ends today is also considered ongoing)
* Green - refers to bookings that have not started yet 

**Q**: How can I keep viewing the filtered residence list returned by `remind` and `find` commands after I make edits?<br>
**A**: Unfortunately, that is not a functionality we have built yet, but we hear you! The next iteration will be sure to incorporate such features for a better user experience and we hope you can stick with us while we improve the product.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Residence** | `add n/NAME_OF_RESIDENCE a/ADDRESS [clean/[y or n]] [t/TAG] …​` <br> e.g., `add n/Clementi HDB a/459A Clementi Ave 3, #04-257, S121459 clean/n`
**Add Booking to Residence** | `addb RESIDENCE_INDEX n/NAME_OF_BOOKER p/PHONE_OF_BOOKER s/START_TIME e/END_TIME` <br> e.g., `add 2 n/John a/91234567 s/01-01-2021 e/02-01-2021`
**Delete Residence** | `delete INDEX`<br> e.g., `delete 3`
**Delete Booking of Residence** | `deleteb r/RESIDENCE_INDEX b/BOOKING_INDEX`<br> e.g., `delete r/3 b/2`
**Edit Residence** | `edit INDEX [n/RESIDENCE_NAME] [a/ADDRESS] [c/VALID_CLEAN_STATUS] [t/TAG]`<br> e.g.,`edit 2 c/n`
**Edit Booking of Residence** | `editb r/RESIDENCE_INDEX b/BOOKING_INDEX [n/TENANT_NAME] [p/PHONE] [s/START_DATE] [e/END_DATE]` <br> e.g., `editb r/1 b/2 p/90069009 s/03-28-2021`
**Update Multiple Clean Status** | `status clean INDEX1 INDEX2..` or `status unclean INDEX1 INDEX2..`<br> e.g., `status clean 1 3`
**Find Residence** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Heights`
**Remind Residences with Upcoming Bookings** | `remind`
**List All Residences** | `list`
**Help** | `help`
**Exit** | `exit`
**Clear** | `clear`
