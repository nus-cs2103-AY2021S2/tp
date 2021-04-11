---
layout: page
title: User Guide
---

# Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# What is A-Bash Book?

A-Bash Book (ABB) is a Command Line Interface (CLI) based Employee and Business Relations Management System.

ABB utilises command patterns similar to Bash, hence the name, A-Bash Book (ABB).

ABB is built to address the growing demands of businesses especially in a climate where large amounts of business information are being stored in various places. As the need to centralise data and optimise workflow increases, businesses are challenging current standards of retrieving operational data to achieve minimum lead times.

With similarities to Bash, we hope to minimise the learning curve for people with existing Bash experience, yet intuitive for new users to pick up.

This User Guide is designed for employees who require additional information on ABB functionalities.

--------------------------------------------------------------------------------------------------------------------

# How to Use the User Guide

Use the [Table of Contents](#table-of-contents) to quickly navigate around the User Guide.

The following typographic conventions and usage conventions occur in the User Guide:

| Text Type                                                                | Meaning                         |
| ------------------------------------------------------------------------ | ------------------------------- |
| `Code`                                                                   | Command text                    |
| <kbd>Keyboard Input</kbd>                                                | Keyboard actions                |
| **Bolded Text**                                                          | Important words to take note of |
| <div markdown="span" class="alert alert-info">:information_source:</div> | Tips and useful information     |
| <div markdown="span" class="alert alert-danger">:exclamation:</div>      | Warning message                 |

--------------------------------------------------------------------------------------------------------------------

# Quickstart Guide

1. Ensure that **Java 11** or above is installed in the computer.

1. Download the latest **abb.jar** from [here](https://github.com/AY2021S2-CS2103T-T12-3/tp/releases).

1. Copy the file to the target folder to use as the _home folder_ to contain the A-Bash Book data.
   See [FAQ: What is the Home Folder?](#what-is-the-home-folder) to understand more.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note that the app contains some sample data.<br>
   ![Ui](images/UG_UI%20Guide.png)

1. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.<br>
   Some example commands to try:

    * **`help`** : Opens this User Guide.

    * **`list`** : Lists all contacts.

    * **`add`**`-n John Doe -p 98765432 -e johnd@example.com -a John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# CLI Syntax

<div markdown="block" class="alert alert-info">
**:information_source: Note for seasoned CLI users:**<br>

This section is intended for non-technical users to understand the CLI syntax. While A-Bash Book
adopts Bash syntax, the implementation is not in parity with Bash's syntax. Feel free to skip
to [Features](#features).

</div>

Command Line Interface (CLI) is a text-based interface in which commands are entered. A-Bash Book is
CLI-based and will only interpret commands that are structured properly. This is called the
**syntax** where a specific arrangement of words and arguments form a command that A-Bash Book can
understand.

## Examples of Command

Here are some examples of how a command is documented in A-Bash Book:

- `select { clear | shown | show | INDEX... }`
- `edit { shown | selected | INDEX... } [-n NAME] [-r REMARK] [-t TAG]...`
- `tag delete { shown | selected | INDEX... } -t TAG [-t TAG]...`

## Uppercase Words

Words in `UPPER_CASE` are the parameters to be supplied by the user.

e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n John Doe`.

## Optional Arguments

Square brackets `[ ]` are used around an optional argument.

If there are multiple optional arguments, each argument is enclosed in its own set of square
brackets.

e.g `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe` but
not `-t friend`.

## Arguments That Can Repeat

Ellipsis (`...`) indicates the option to use an argument multiple times including zero times.

e.g. `[-t TAG]...` can be used as `""` (i.e. 0 times), `-t friend`, `-t friend -t family` etc.

e.g. `-t TAG [-t TAG]...` specifies that there must be at least one tag and can be used
as `-t family` and `-t family -t cousin -t child`.

## Mutually Exclusive Arguments

Braces (also known as _curly braces_) `{ }` are used around arguments where the user must choose **
only one** of the items inside the braces.

Vertical bars (also known as _pipes_) are used to separate the items. There can be more than two
mutually exclusive choices.

e.g. `select { clear | shown | show | INDEX...}` can be used as:

- `select clear`
- `select shown`
- `select show`
- `select 1`
- `select 1 2 3`

Invalid examples are:

- `select clear show`
- `select clear 1 2 3`

## Miscellaneous Information

- If a parameter is expected only once in the command, but is specified multiple times, only the
  last occurrence of the parameter will be taken.<br>
  e.g. `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`
  and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- `shown` is a special index that refers to all persons that are visible in the filtered person
  list. e.g. `find surname` shows 10 persons. Executing `select shown` will select all 10 of the
  persons.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

For non-technical users, please refer to the [CLI Syntax](#cli-syntax) before proceeding.

</div>

## Viewing help : `help`

Displays the entire User Guide for ease of reference.

![Help Message UI](images/helpMessage.png)

**Format**: `help`


## Adding a person: `add`

Adds a person to the address book.

**Format**: `add -n NAME -p PHONE_NUMBER -e EMAIL -c COMPANY -j JOB_TITLE -a ADDRESS [-r REMARK] [-t TAG]...`

<div markdown="block" class="alert alert-info">

:information_source: **Notes on `add` command:**
* A person can have no remark.
* A person can have any number of tags (including 0).

</div>

![Add Command UI](images/UG_Add%20Command.png)

**Examples**:

| Example                                                                                                                                | Description                                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `add -n John Doe -p 98765432 -e johnd@example.com -c Google -j HR Manager -a John street`                                              | Adds a person named `John Doe`, with phone number `91234567`, email address `johndoe@example.com`, company `Google`, job title `HR Manager` and address `John street`.                                                                                                       |
| `add -n Betsy Crowe -p 1234567 -e betsycrowe@example.com -c Amazon -j Manager -a Betsy Avenue -t Recruiter -t Intern Manager`          | Adds a person named `Betsy Crowe`, with phone number `1234567`, email address `betsycrowe@example.com`, company `Amazon`, job title `Manager` and address `Betsy Avenue`. This person is also tagged with the following tags: `Recruiter` and `Intern Manager`.              |
| `add -n Charlie -p 7654321 -e charlie@example.com -c Facebook -j Software Engineer -a Charlie Road -t IT Support -r Emergency contact` | Adds a person named `Charlie`, with phone number `7654321`, email address `charlie@example.com`, company `Facebook`, job title `Software Engineer` and address `Charlie Road`. This person is also tagged with the tag `IT Support`, and has the remark `Emergency contact`. |

## Listing all persons : `list`

Lists all persons in the address book.

**Format**: `list`

## Editing a person : `edit`

Edits an existing person in the address book.

**Format**: `edit { shown | selected | INDEX... } [-n NAME] [-p PHONE] [-c COMPANY] [-j JOB_TITLE] [-e EMAIL] [-a ADDRESS] [-r REMARK] [-t TAG]...`

<div markdown="block" class="alert alert-info">

**:information_source: Notes on `edit` command:**<br>

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer**, i.e 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing remark, the existing remark of the person will be removed, i.e adding of remark is not cumulative.
* When editing tags, the existing tags of the person will be removed, i.e adding of tags is not cumulative.
* To remove the person’s remark, type `-r ` without specifying any remark after it.
* To remove all the person’s tags, type `-t ` without specifying any tags after it.
* To edit all the shown person, type `edit shown`
* To edit all the selected person, type `edit selected` followed by the arguments

</div>

![Edit Command UI](images/UG_Edit%20Command.png)

**Examples**:

| Example                                     | Description                                                                                                         |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| `edit 1 -p 91234567 -e johndoe@example.com` | Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively. |
| `edit 2 -n Betsy Crower -t `                | Edits the name of the 2nd person to be `Betsy Crower` and clears all of her existing tags.                          |
| `edit 3 -r `                                | Clears any existing remark of the 3rd person.                                                                       |
| `edit shown -r `                            | Clears any existing remark of all the displayed persons in person list.                                             |
| `edit selected -r `                         | Clears any existing remark of all the selected persons.                                                             |

<div markdown="block" class="alert alert-info">

**:bulb: Bulk Edit**

To bulk edit, either do:
* `edit 1 2 3` to edit persons at indexes 1, 2 and 3 or,
* `edit shown` to edit all the shown persons or,
* `edit selected` to edit all the selected persons

</div>

## Locating persons by name: `find`

Finds persons whose field(s) contain any of the given keywords.
<div markdown="block" class="alert alert-info">

**:information_source: Currently Searchable fields:** Name, Email, Tag, Remark<br>

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes on `find` command:**<br>

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Words are partially matched. e.g `sam` will match `Samantha`
* Similar words are matched. e.g `Shawn` with match `Shaun`
* Partially similar words will also be matched as a result of the above. e.g `Ben` will match `Elizabeth`
    * `bet` in `Elizabeth` is 1 character away from `Ben`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

</div>

![Find Command UI](images/UG_Find%20Command.png)

### Searching all searchable fields

**Format**: `find KEYWORD [MORE_KEYWORDS]`

**Examples**:

| Example           | Description                                                                                                                                       |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| `find Jon`        | Returns any person that matches `jon` partially in any of the searchable fields<br> e.g. a person tagged as `Janitor` (`Jon` is similar to `Jan`) |
| `find alex david` | Returns any person that matches `alex` or`david` partially in any of the searchable fields<br> e.g. people named `Alex Yeoh`, `David Li`          |

### Searching by specific fields

**Format**: `find FIELD_PREFIX KEYWORD [MORE_KEYWORDS]`

<div markdown="block" class="alert alert-info">

:information_source: Refer to [Field Summary table](#field-summary) for a full list prefixes.<br>
**Currently Searchable fields:** Name, Email, Tag, Remark

</div>

<div markdown="block" class="alert alert-info">

:information_source: You can only search 1 field at a time.<br>
`find -n Alice -t HR` is an invalid command

</div>

**Examples**:

| Example             | Description                                                                                               |
| ------------------- | --------------------------------------------------------------------------------------------------------- |
| `find -n Alice Ben` | Returns people named `Alicia Yen` (Similar) and `Benjamin Koh` (Partial)                                  |
| `find -t Market`    | Returns people tagged with `Marketing` (Partial)                                                          |
| `find -r Manager`   | Returns people with `Management Intern` (Similar) and `Human Resource Manager` (Partial) in their remarks |


## Deleting a person : `delete`

Deletes the specified person(s) from the address book.

**Format**: `delete { shown | selected | INDEX... }`

<div markdown="block" class="alert alert-info">

**:information_source: Notes on `delete` command:**<br>

* Deletes the person at the specified `INDEX`/`INDEX...` or shown person list or selected list.
* The command **operates on the shown list** that may be modified by an earlier command.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer**, i.e 1, 2, 3, ...
* To delete all the shown person, type `delete shown`
* To delete all the selected person, type `delete selected`

</div>

![Delete Command UI](images/UG_Delete%20Command.png)

**Examples**:

| Example                                      | Description                                                                                                           |
| -------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| `list`<br>`delete 2`                         | `list` displays all entries.<br>`delete 2` deletes the second entry in the list shown.                                |
| `find Betsy`<br>`delete 1`                   | `find Betsy` filters entries to the find result.<br> `delete 1` deletes the first entry in the filtered results list. |
| `select 1 2 3`   <br>      `delete selected` | Deletes selected entries 1, 2 and 3                                                                                   |
| `delete shown`                               | Deletes all the displayed persons in the person list                                                                  |

<div markdown="block" class="alert alert-info">

**:bulb: Bulk Delete**

To bulk delete, either do `delete 1 2 3` to delete indexes 1, 2 and 3
or `delete shown` to delete all the shown persons.

</div>

## Selecting Persons : `select`

Enables user to incrementally select multiple person objects to apply actions on.

**Format**: `select { clear | shown | show | INDEX... }`

Sub Command Format:
* `select show`
* `select clear`
* `select shown`
* `select INDEX...`

The selected person(s) will have a highlighted index number to indicate selection status.

![Select UI Example](images/UG_Select%20Indicator.png)

Examples:

| Example        | Description                                                                                                                        |
| -------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `select show`  | Shows the selected person(s)                                                                                                       |
| `select shown` | Selects all the person in the visible person list (visible person list refers to the list of persons that are currently displayed) |
| `select clear` | Clears the current selection                                                                                                       |
| `select 1`     | Select the person with index 1 (incremental selection)                                                                             |
| `select 1 2`   | Select persons with index 1 and 2 (incremental selection)                                                                          |

<div markdown="block" class="alert alert-info">

**:information_source: Incremental Selection and `shown`:**

- Incremental selection means that selected items are "stacked" together. e.g. Executing `select 1`
  followed by `select 2` will result in person with index number 1 and 2 being selected.

- `shown` refers to all the person objects in the current visible person list (visible person list
  refers to the list of persons that are currently displayed).

</div>

<div markdown="block" class="alert alert-info">

**:bulb: Tip:**

`select` command is best used with the following commands:

- [`edit` command](#editing-a-person--edit)
- [`delete` command](#deleting-a-person--delete)
- [`tag` command](#tag--tag)
- [`email` command](#email--email)

</div>

## Email Persons: `email`

Email command allows user to email selected persons using the operating system's default email
client. Email client must be configured to allow command separated email values.

<div markdown="block" class="alert alert-info">

information_source: It is a known issue that Outlook for Windows do not have "Commas to Separate
Multiple Email Recipients" feature turned on. Follow the guide here to enable
it: <https://www.lifewire.com/commas-to-separate-email-recipients-1173680>

information_source: It is not possible for A-Bash Book to detect if the email client is opened. If
executing `email` command does not trigger the email client to appear, please check that an email
client is installed and configured properly.

</div>

**Format**: `email { shown | selected | INDEX... }`

| Example          | Description                               |
| ---------------- | ----------------------------------------- |
| `email shown`    | Email all persons show in the person list |
| `email selected` | Email selected persons                    |
| `email 1`        | Email person at index 1                   |
| `email 1 2`      | Email person at index 1 and 2             |


## Clearing all entries : `clear`

Clears all entries from the address book.

<div markdown="span" class="alert alert-danger">

:exclamation: **This action is irreversible.** Do not run this command with actual data unless you want to delete all entries.

</div>

**Format**: `clear`

## Exiting the program : `exit`

Exits the program.

**Format**: `exit`

## Command Auto Completion

Command Auto Completion automatically fills a command in the command box by pressing the <kbd>Tab</kbd> key.

**Examples**:

To execute the command `delete`,

Typing `del` followed by <kbd>Tab</kbd> will auto complete `del` to `delete`.

In the command box, it is possible to toggle through **Existing** and **Aliased** [Coming Soon] commands with <kbd>Tab</kbd>.

Extra auto completion features exist for some commands.
See the table below for more information.

| Command                        | Description                                                                                                                                           |
| ------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `delete`                       | The <kbd>Up</kbd>/<kbd>Down</kbd> arrow keys toggles through contacts. Appends the `index` of a contact in focus to existing text in the command box. |
| `edit`                       | The <kbd>Up</kbd>/<kbd>Down</kbd> arrow keys toggles through contacts. Appends the `index` of a contact in focus to existing text in the command box. |
| `Other Commands`| [Coming Soon] |

## Command Flag Auto Completion

Command flags are specific options that can be keyed in to add more information in a command.

E.g `add -n John Doe -p 94326543 -e john@doe.com -a 45 Address #06-90`

Command: `add`

Command flags: `-n` , `-p`, `-e`, `-a`.

**Usage**

Press the <kbd>Tab</kbd> key for any of the commands below to automatically add command flags to the command box.    

| Supported Commands                        | Available command flags|
| ------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `add`                       | `-n` , `-p`, `-e`, `-a`, `-r`, `-t` |
| `edit`                       | `-n` , `-p`, `-e`, `-a`, `-r`, `-t` |

**Example Usages**

:information_source: **Note on `add` command:** a space character should follow the add command for flags to begin 
autocompletion.

| Current text in command box                        | Press <kbd>Tab</kbd> once |
| ------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `add␣`                      | `add -n ` |
| `add -n John Doe -p 94326543 -e john@doe.com`                      | `add -n John Doe -p 94326543 -e john@doe.com -a ` |
| `edit 3`                       | `edit 3 -n` |
| `edit 3 -n John Doe -p 94326543`                       | `edit 3 -n John Doe -p 94326543 -e ` |

## Alias : `alias`

Alias creates shortcut command of the actual command.

### Add Alias: `alias add`

Adds an alias to address book.

**Format**: `alias add ALIAS COMMAND`

**Examples**:

| Example                             | Description                                                                                                                                                                                |
| ----------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `alias add ls list`                 | Associates a new `ls` command to list, such that the ls command will behave identically to the list command (i.e ls will now generate the list of all contacts).                           |
| `alias add ls list -n  -p  -e  -t ` | Associates a new `ls` command to list, such that the `ls` command will behave identically to the list command with the options (i.e `list -n  -p  -e  -t`).                                |
| `alias add f find`                  | Associates a new `f` command to `find`, such that the `f` command will behave identically to the `find` command (i.e f Alex Yeoh will now return contacts equals or similar to Alex Yeoh). |

### Delete Alias: `alias delete`

Deletes an existing alias from address book.

**Format**: `alias delete ALIAS`

**Examples**:

| Example           | Description            |
| ----------------- | ---------------------- |
| `alias delete ls` | Removes the alias `ls` |
| `alias delete d`  | Removes the alias `d`  |

### List Alias: `alias list`

Lists all alias(es) in the address book.

**Format**: `alias list`

## Filter Field Visibility: `filter`

Filter command toggles visibility of fields based on user input options.

**Format**: `filter [-OPTION]...`

Each option should start with a hyphen `-` e.g. `-OPTION` and be separated by a white-space. Options
which are excluded will be hidden. Refer to [field summary](#field-summary) for all the available
options.

<div markdown="span" class="alert alert-info">

:information_source: Refer to [Field Summary table](#field-summary) for the available options

</div>

![Filter Command UI](images/UG_Filter%20Command.png)

**Examples**:

| example        | description                                              |
| -------------- | -------------------------------------------------------- |
| `filter`       | Shows all fields                                         |
| `filter -a`    | shows the contact's name and address only.               |
| `filter -a -p` | shows the contact's name, address and phone number only. |

## Live command suggestion [coming soon]

<!-- In addition to tab auto complete feature, A-Bash Book will also attempt to suggest commands available to the user.

![](https://via.placeholder.com/350x150/000000/FFFFFF?text=Hi)

Users will be able to press tab to cycle through the available options. -->

## Tag : `tag`

Tag allows addition and deletion of specific tags of person.

### Add Tag: `tag add`

Adds tags to person in address book.

**Format**: `tag add { shown | selected | INDEX ... } -t TAG ...`

**Examples**:

| Example                                     | Description                                                                  |
| ------------------------------------------- | ---------------------------------------------------------------------------- |
| `tag add shown -t Photoshop`                | Adds `Photoshop` tag to the people shown in the UI.                          |
| `tag add selected -t Illustrator`           | Adds `Illustrator` tag to the people selected.                               |
| `tag add 1 2 3 -t Photoshop -t Illustrator` | Adds `Photoshop` and `Illustrator` tags to people at index `1`, `2` and `3`. |

### Delete Tag: `tag delete`

Deletes tags from person in address book.

**Format**: `tag delete { shown | selected | INDEX... } -t TAG [-t TAG]...`

**Examples**:

| Example                                        | Description                                                                       |
| ---------------------------------------------- | --------------------------------------------------------------------------------- |
| `tag delete shown -t Photoshop`                | Deletes `Photoshop` tag from the people shown in the UI.                          |
| `tag delete selected -t Illustrator`           | Deletes `Illustrator` tag from the people selected.                               |
| `tag delete 1 2 3 -t Photoshop -t Illustrator` | Deletes `Photoshop` and `Illustrator` tags from people at index `1`, `2` and `3`. |

## Saving the data

A-Bash Book data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

A-Bash Book data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-danger">

:exclamation: **Caution:**

If changes to the data file renders its format invalid, A-Bash Book will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

# FAQ

## What is the Home Folder?

A home folder is a file system folder where A-Bash Book stores data.

```
foldername (Home Folder)
├── a-bash-book.jar
├── addressbook.log.0
├── config.json
├── data
│   ├── addressbook.json
│   └── aliases.json
├── preferences.json
```

## How do I transfer my data to another Computer?

Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous A-Bash Book home folder.

--------------------------------------------------------------------------------------------------------------------

# Command Summary

| Action           | Format, Examples                                                                                                                                                                                                                   |
| ---------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**          | `add -n NAME -p PHONE_NUMBER -e EMAIL -c COMPANY -j JOB_TITLE -a ADDRESS [-r REMARK] [-t TAG]...` <br> e.g., `add -n James Ho -p 22224444 -e jamesho@example.com -a 123, Clementi Rd, 1234665 -r Likes ramen -t friend -t colleague` |
| **Clear**        | `clear`                                                                                                                                                                                                                            |
| **Delete**       | `delete { shown | selected | INDEX... }` <br> e.g., `delete 3`                                                                                                                                                                                                |
| **Edit**         | `edit { shown | selected | INDEX... } [-n NAME] [-p PHONE] [-c COMPANY] [-j JOB_TITLE] [-e EMAIL] [-a ADDRESS] [-r REMARK] [-t TAG]...` <br> e.g.,`edit 2 -n James Lee -e jameslee@example.com`                                                          |
| **Find**         | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find James Jake`                                                                                                                                                                         |
| **Filter**       | `filter [-OPTION]...` <br> e.g., `filter -p -a` to see only the phone number and address                                                                                                                                    |
| **Select**         | `select { clear | shown | show | INDEX... }`                                                                                                                                                                                                                             |
| **Email**         | `email { shown | selected | INDEX... }`                                                                                                                                                                                                                             |
| **List**         | `list`                                                                                                                                                                                                                             |
| **Help**         | `help`                                                                                                                                                                                                                             |
| **Add Alias**    | `alias add [ALIAS] [COMMAND]`<br> e.g. `alias add ls list`                                                                                                                                                                         |
| **Delete Alias** | `alias delete [ALIAS]`<br> e.g. `alias delete ls`                                                                                                                                                                                  |
| **List Alias**   | `alias list`                                                                                                                                                                                                                       |
| **Add Tag**      | `tag add { shown | selected | INDEX... } -t TAG...` <br> e.g., `tag add 1 2 3 -t Photoshop -t Illustrator`                                                                                                                       |
| **Delete Tag**   | `tag delete { shown | selected | INDEX... } -t TAG ...` <br> e.g., `tag delete shown -t Illustrator`                                                                                                                              |

# Field Summary

| Mandatory | Field        | Command Flag | Restrictions                                                                                                                                                                                                                                                                                                                                                                                                          |
| --------- | ------------ | ------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Yes       | Name         | `-n`         | Names should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                              |
| Yes       | Email        | `-e`         | Emails should be of the format local-part@domain and adhere to the following constraints:<br>1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (!#$%&'*+/=?`{|}~^.-) `.<br>2. This is followed by a '@' and then a domain name. The domain name must:<br>    - be at least 2 characters long<br>    - start and end with alphanumeric characters |
| Yes       | Company      | `-c`         | Company can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                               |
| Yes       | Job Title    | `-j`         | Job Title can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                             |
| Yes       | Address      | `-a`         | Addresses can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                             |
| Yes       | Phone Number | `-p`         | Phone numbers should only contain numbers, and it should be at least 3 digits long                                                                                                                                                                                                                                                                                                                                    |
| No        | Tag          | `-t`         | Tags names should be alphanumeric and contains no spaces or symbols                                                                                                                                                                                                                                                                                                                                                   |
| No        | Remark       | `-r`         | None                                                                                                                                                                                                                                                                                                                                                                                                                  |

# Glossary

| Term     | Explanation                                                                      |
| -------- | -------------------------------------------------------------------------------- |
| Bash     | The well known terminal interpreter                                              |
| CLI      | Command Line Interface. A text-based interface in which commands can be entered. |
| GUI      | Graphical User Interface                                                         |
| JSON     | JavaScript Object Notation, data storage format                                  |
| Terminal | The Command Line Interface where text-based commands are entered.                |
