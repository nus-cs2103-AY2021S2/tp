
---
layout: page
title: User Guide
---

FriendDex is a **relationship management tool for CLI enthusiasts** looking to enhance their social life while not compromising on getting things done quickly. Managing your relationship goals should not be any more tedious than coding.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `frienddex.jar` from [here](https://github.com/AY2021S2-CS2103T-W14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your FriendDex.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the FriendDex.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.<br>
  e.g. `p/ INDEX…​` must have at least one `INDEX`, `p/1`, `p/1 2` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Creating a friend group: `group`

Creates a new friend group to FriendDex with a specified name and adds all the people at the specified
indexes to the group.

Format: `group n/GROUP_NAME p/INDEX…​`

* At least one index must be provided.
* If the group name already exists, the persons at the specified `INDEX…​` will be added to the group.
* If some persons specified already exist in the group, they will be ignored.

Examples:
* `group n/Close Friends  p/1 2 3 4 5`

### Adding a person: `add`

Adds a person to FriendDex.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/DATETIME [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/19-01-1998`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com b/19-03-1998 a/Newgate Prison p/1234567 t/criminal`

### Adding a profile picture: `picture`

Adds a profile picture to an existing person in FriendDex.

Format: `picture INDEX FILE_PATH`

* The image of the person should be at `FILE_PATH`.

Examples:
* `picture 1 /Users/john/Desktop/jake.png`

### Listing all persons : `list`

Shows a list of all persons in FriendDex. 
Can optionally provide group name to only list all the friends within a group.

Format: `list [n/GROUP_NAME]`

### Editing a person : `edit`

Edits an existing person in FriendDex.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/DATETIME] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Recording a meeting: `meeting`

Records a meeting with an existing person in FriendDex.

Format: `meeting INDEX d/DATETIME desc/DESCRIPTION`

Examples:
* `meeting 1 d/16-02-2021 1130 desc/We had lunch together!`
* `meeting 2 d/17-02-2021 1930 desc/We went to see the sunset!`

### Adding a special date: `add-date`

Adds a special date for an existing person in FriendDex.

Format: `add-date INDEX d/DATE desc/DESCRIPTION`

Examples:
* `add-date 1 d/16-02-2021 desc/Anniversary`
* `add-date 2 d/17-02-2021 desc/Dog's birthday`

### Deleting a special date: `del-date`

Deletes a special date from an existing person in FriendDex.

Format: `del-date INDEX i/DATE_INDEX`

Examples:
* `del-date 1 i/1`
* `del-date 2 i/3`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS] [p/]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* If the `p/` flag is set, then the argument(s) `KEYWORD [MORE KEYWORDS]` shall be treated as a regular expression.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find ^a.*h$ p/` returns `Alex Yeoh`

### Deleting a person : `delete`

Deletes the specified person from FriendDex.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in FriendDex.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from FriendDex.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Styling the application : `theme`

Format `theme THEME_PATH`

* Applies the theme specified in `THEME_PATH`.
* The current applied theme will be saved and applied on subsequent sessions.

Example:
* `theme theme/solarized.dark.json` applies the theme `solarized.dark.json` located at `./theme/`.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If the theme supplied is not found or unreadable, then the default theme will be applied.
</div>

See also:
* [Theme](#theme)

--------------------------------------------------------------------------------------------------------------------

## Dashboard
### Upcoming Events

FriendDex displays your upcoming events on the right panel, such as upcoming birthdays and special dates.

--------------------------------------------------------------------------------------------------------------------

## Data Storage
### Saving the data

FriendDex data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FriendDex data is saved as a JSON file `[JAR file location]/data/frienddex.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FriendDex will discard all data and start with an empty data file at the next run.
</div>

## Theme
### Theme format

A valid theme is a JSON object containing the following fields:
| Name         | Type         | Description                                                                                                                                                             |
|--------------|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `foreground` | `String`     | The foreground color of the application in valid hex color string                                                                                                       |
| `background` | `String`     | The background color of the application in valid hex color string                                                                                                       |
| `color`      | `String[16]` | Colors 0 to 15 of the application in valid hex color strings. Refer to [XTerm colors](https://invisible-island.net/xterm/manpage/xterm.html#h3-VT100-Widget-Resources). |

A sample theme (Monokai Dark)
```
{
  "color": [
    "#272822",
    "#f92672",
    "#a6e22e",
    "#f4bf75",
    "#66d9ef",
    "#ae81ff",
    "#a1efe4",
    "#f8f8f2",
    "#75715e",
    "#f92672",
    "#a6e22e",
    "#f4bf75",
    "#66d9ef",
    "#ae81ff",
    "#a1efe4",
    "#f9f8f5"
  ],
  "foreground": "#f8f8f2",
  "background": "#272822"
}
```

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FriendDex home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BIRTHDAY] [t/TAG]…​`<br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Group** | `group n/GROUP_NAME p/[INDEX...]`<br> e.g. `group n/Close Friends p/1 2 3 4`
**Add Profile Picture** | `add-picture INDEX FILE_PATH`<br> e.g. `picture 1 /Users/john/Desktop/jake.png`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`
**Add a meeting** | `add-meeting INDEX d/DATE t/TIME desc/DESCRIPTION`<br> e.g. `add-meeting 2 d/17-02-2021 t/1930 desc/We went to see the sunset!`
**Remove a meeting** | `del-meeting INDEX i/MEETING_INDEX`<br> e.g. `del-meeting 1 i/2`
**Add a date** | `add-date INDEX d/DATE desc/DESCRIPTION`<br> e.g. `add-date 1 d/16-02-2021 desc/Anniversary`
**Delete a date** | `del-date INDEX i/DATE_INDEX`<br> e.g. `del-date 1 i/1`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`
**Theme** | `theme THEME_PATH`<br> e.g. `theme theme/solarized.dark.json`
**List** | `list [n\GROUP_NAME]` <br> e.g. `list n\Close Friends`
**Help** | `help`
