---
layout: page
title: User Guide
---

MeetBuddy is a **desktop app for managing contacts and daily meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, MeetBuddy can get your contacts and meetings management done faster than traditional GUI apps. In addition, MeetBuddy is meant for users to organise their meetups
with friends, family, or work colleagues. It provides a better user experience for users who have alot of contacts having online Gravatar accounts, where there is automatic syncing of
their profile pictures. 


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `meetbuddy.jar` from [here](https://github.com/AY2021S2-CS2103-T16-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MeetBuddy.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listp`** : Lists all contacts.

   * **`add`**`n/John Doe ph/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to MeetBuddy.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below (after glossary) for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Glossary 

| First Header  | Second Header |
| ------------- | ------------- |
| Content Cell  | Content Cell  |
| Content Cell  | Content Cell  |
--------------------------------------------------------------------------------------------------------------------


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [g/GROUP]` can be used as `n/John Doe g/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[g/GROUP]…​` can be used as ` ` (i.e. 0 times), `g/friend`, `g/friend g/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME ph/PHONE_NUMBER`, `ph/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `ph/12341234 ph/56785678`, only `ph/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
* Note that for all date arguments, if the day is less than or equal to 31 and 
there is no such day corresponding to the given month and year of the date, the program is smart enough to handle 
 such cases and automatically corrects the date to the nearest valid end-of-month date. For example 
`31-02-2021` will be auto-corrected to `28-02-2021` because there is no such date. 
  But `32-02-2021` will not be autocorrected and the user will be prompted with an error message.


</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `addp`

Adds a person to MeetBuddy.

Format: `add n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GROUP]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of groups (including 0)
</div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
In MeetBuddy we assume that all the persons in the contact list have different names
</div>

Examples:
* `add n/John Doe ph/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe g/CS2103 e/betsycrowe@example.com a/Newgate Prison ph/1234567 g/badminton`

### Listing all persons : `listp`

Shows a list of all persons in MeetBuddy.

Format: `listp`

### Editing a person : `editp`

Edits an existing person in MeetBuddy.

Format: `edit INDEX [n/NAME] [ph/PHONE] [e/EMAIL] [a/ADDRESS] [g/GROUP]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.
* When editing groups, the existing groups of the person will be removed i.e adding of groups is not cumulative.
* You can remove all the person’s groups by typing `g/` without
    specifying any groups after it.

Examples:
*  `edit 1 ph/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower g/` Edits the personName of the 2nd person to be `Betsy Crower` and clears all existing groups.

### Locating persons by personName: `findp`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the personName are searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating persons by group: `findpg`

Finds persons whose groups contain any of the given keywords.

Format: `findg KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `badminton` will match `Badminton`
* The order of the keywords does not matter. e.g. `findg tennis table` will list every person in the table tennis group
* Only the groups are searched.
* Only full words will be matched e.g. `badminton` will not match `badmintons`
* Persons whose group match at least one keyword will be returned (i.e. `OR` search).
  
Examples:
* `findg badminton` returns the list of contacts that are in the badminton group

### Deleting a person : `deletep`

Deletes the specified person from MeetBuddy.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in MeetBuddy.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Sorting of contacts : `sortp`

Sorts the contacts displayed according to a specified field.

Format: `sortp by/FIELD d/DIRECTION`

* Sorts according to the field specified by `FIELD`
* `FIELD` is only restricted to the following cases:
    * Sort by name : `NAME`
    * Sort by email : `EMAIL`
    * Sort by phone number : `PHONE`
    * Sort by address : `ADDRESS`
* `DIRECTION` is only restricted to the following cases:
    * Sort by ascending alphabetical order : `ASC`
    * Sort by descending alphabetical order : `DESC`

Examples

### Adding a meeting: `addm`

Adds a meeting to MeetBuddy.

Format: `addm n/NAME st/TIME ed/TIME [des/DESCRIPTIONS] [pr/PRIORITY] [p/PERSON RELATED INDEX]… [g/GROUP]…​`
* Note that meetings must be of minimum length of 15 mins and maximum length of 7 days. For example a meeting cannot be 15 March 16:00 - 22 March 16:00, but can be from 15 March 16:00 - 22 march 15:59.
* Priority should be an integer from 1 to 5. If the priority is not specified, it will be automatically set as 1.
* Description can be empty.
* A meeting can have any number of groups (including 0).
* Person Related Index refers to the index of the contact list shown in the GUI. The index should be an integer and not out of bounds. If duplicated person index is in the input, the program will automatically remove the duplication.

Examples:
* `addm n/CS2103 Lecture st/2021-03-12 14:00 ed/2021-03-12 16:00 desc/Week 7 pr/3 g/lectures g/SoC p/1 p/2`

### Listing all meetings : `listm`

Shows a list of all meetings in MeetBuddy.

Format: `listm`

### Editing a meeting : `editm`

Edits an existing meeting in MeetBuddy.

Format: `editm INDEX [n/NAME] [st/START TIME] [ed/END TIME] [desc/DESCRIPTION] [pr/PRIORITY] [p/PERSON RELATED INDEX] [g/GROUP]...​`

* Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list. The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.
* When editing person related, the existing related contacts will be removed. You must specify the index correctly.
* When editing groups, the existing groups in the meeting will be removed i.e adding of groups is not cumulative.
* You can remove all the meeting’s groups by typing `g/` without specifying any groups after it.

Examples:
*  `editm 1 n/CS2103 Lecture g/SOC g/friends` Edits the name of the 1st meeting to be `CS2103 Lecture`, and its groups to be `SOC` and `friends`.
*  `editm 2 n/CS2106 Lab g/` Edits the name of the 2nd meeting to be `CS2106 Lab` and clears all existing groups in the meeting.
*  `editm 2 n/CS2106 Lab p/1` Edits the name of the 2nd meeting to be `CS2106 Lab` and change the contacts related field to the first person in your contact list (If he/she exists).
### Deleting a meeting: `deletem`

Deletes a meeting in MeetBuddy.

Format: `deletem INDEX`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listm` followed by `delete 2` deletes the 2nd meeting in MeetBuddy.

### Adding persons related for a meeting: `addptm`

Adds persons related to a meeting in MeetBuddy.

Format: `addptm INDEX p/PERSON RELATED INDEX1 [p/PERSON RELATED INDEX2]… ​`
* The index refers to the meeting index, and it **must be a positive integer** 1, 2, 3, …​
* The user must input at least one person related index field.
* Person Related Index refers to the index of the contact list shown in the GUI. The index should be an integer and not out of bounds. If duplicated person index is in the input, the program will automatically remove the duplication.

Examples:
* `addptm 1 p/1 p/2 p/2` Adds the person on index 1 and 2 into the contacts related field in meeting 1.

### Deleting persons related from a meeting: `deletepfm`

Deletes persons related from a meeting in MeetBuddy.

Format: `deletepfm INDEX p/PERSON RELATED INDEX1 [p/PERSON RELATED INDEX2]… ​`
* The index refers to the meeting index, and it **must be a positive integer** 1, 2, 3, …​
* The user must input at least one person related index field.
* Person Related Index refers to the index of the contact list shown in the GUI. The index should be an integer and not out of bounds. If duplicated person index is in the input, the program will automatically remove the duplication.
* The related persons that the user wants to delete must exist in the contacts related field.


Examples:
* `deletepfm 1 p/1 p/2 p/2` Deletes the person on index 1 and 2 from the contacts related field in meeting 1.

### Showing of persons in a meeting : `showm`

Shows all persons present in a specified meeting.

Format: `showm INDEX`
* The index refers to the meeting index, and it **must be a positive integer** 1, 2, 3, …​
* The meeting must also exist in the list of meetings shown on the GUI.
* The command will display the list of contacts associated with a meeting.
* This command, however, will not show the list of contacts associated with groups that are in a meeting.
* For instance, a meeting can have the group `table tennis club`, but contacts in the `table tennis club` will not be shown with `showm`. Only those who are directly added into the meeting will be shown.
* This is because a user may have the meeting with the `table tennis club` but not everyone in the club. The user may only wish to have the meeting with a user-defined circle of friends within the club depending on the purpose.

### Sorting of meetings : `sortm`


Sorts the meetings displayed according to a specified field.

Format: `sortm by/FIELD d/DIRECTION`

* Sorts according to the field specified by `FIELD`
* `FIELD` is only restricted to the following cases:
    * Sort by name : `NAME`
    * Sort by start time : `START`
    * Sort by end time : `END`
    * Sort by priority : `PRIORITY`
    * Sort by description : `DESCRIPTION`
* `DIRECTION` is only restricted to the following cases:
    * Sort by ascending order : `ASC`
    * Sort by descending order : `DESC`


### Listing all persons and meetings : `list`

Shows a list of all persons and meetings in MeetBuddy.

Format: `list`

##Timetable feature

### Viewing Timetable: 
 No command is necessary. Just click on the timetable tab to switch view from meeting list to timetable. The timetable
 consists of 7 rows, each row corresponds a day of a week in the timetable, which default is from 7 am to 6.59 am on the next day. 
 Each meeting is represented as an orange rectangular slot that will be placed in the timetable according to the following rules
 
 * If the meetings happen on a date corresponding to a column, it will be slotted into that column
 * The vertical axis if the timetable is the time, and slots are vertically placed according to their start time.
 * The length of the meeting slot is proportional to the timespan of the meeting.
 
 Note that it will correctly update and display all meetings. Meetings that fall outside the range of the timetable 
 will be filtered off. Some things to note:
 
 * Default when starting the application, the timetable will have the first ( leftmost column ) representing today's date.
 * Meetings can overlap across columns.
 * Setting small meeting times around the edge of the timetable will cause display issues, For example, setting a meeting 
 to 6:44-7:01 might cause display issues from the 7 - 7.01 will not display the date or time.
 * Note that you can scroll to view more slots.
 
 
### Set Timetable date : `setTimetable`
 
 Sets a timetable to start on a specified date. Updates the display accordingly.
 
 Format: 'setTimetable DATE'
 
 * DATE must be a string strictly following the format `YYYY-mm-dd`
 * If no date is specified, for example `setTimetable` without DATE parameter is keyed in, it will default set to
 today's current date.
 
 
### Profile picture:

Instantly updates your contacts with their Gravatar profile picture, if your contact has their email linked
to their gravatar account. The profile picture will be automatically displayed upon adding the contact.
If there is a problem obtaining the image, a default blue circle icon will be displayed
instead.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MeetBuddy data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Person Contact data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Users are not suggested updating data directly by editing that data file.
Meeting data are saved as a JSON file '[JAR file location]/data/meetingbook.json'. Users are not suggested updating data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MeetBuddy will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MeetBuddy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `addp n/NAME ph/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GROUP]…​` <br> e.g., `addp n/James Ho ph/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 g/CS2106 g/badminton` <br> <br> `addm n/NAME st/START TIME ed/END TIME desc/DESCRIPTION pr/PRIORITY [g/GROUP]...[p/INDEX OF PERSON RELATED]...​` <br> e.g., `addm n/CS2103 Lecture st/2021-03-12 14:00 ed/2021-03-12 16:00 desc/Week 7 pr/3 g/lectures g/SoC p/1 p/2`
**Delete** | `deletep INDEX`<br> e.g., `deletep 3` <br> <br> `deletem INDEX`<br> e.g., `deletem 3`
**Edit** | `editp INDEX [n/NAME] [ph/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GROUP]…​`<br> e.g.,`editp 2 n/James Lee e/jameslee@example.com` <br> <br> `editm INDEX [n/NAME] [st/START TIME] [ed/END TIME] [desc/DESCRIPTION] [pr/PRIORITY] [p/PERSON RELATED INDEX] [g/GROUP]...​`<br> e.g.,`editm 2 n/CS2103 Lecture`
**Find** | `findp KEYWORD [MORE_KEYWORDS]`<br> e.g., `findp James Jake` <br> <br> `findpg KEYWORD [MORE_KEYWORDS]`<br> e.g., `findpg badminton` <br>
**List** | `list`, `listm`, `listp`
**Sort** | `sortp by/FIELD d/DIRECTION` <br>  `sortm by/FIELD d/DIRECTION`
**Help** | `help`
**SetTimetable**| `setTimetable DATE`
**AddPersonRelatedToAMeeting**|`addptm INDEX p/PERSON RELATED INDEX1 p/PERSON RELATED INDEX2… ​`
**DeletePersonRelatedFromAMeeting**|`deletepfm INDEX p/PERSON RELATED INDEX1 p/PERSON RELATED INDEX2… ​`