---
layout: page
title: User Guide
---

Welcome to the PocketEstate User Guide! Learn how to use PocketEstate to efficiently organize property data, manage clientele information and keep track of your upcoming appointments.

## Table of Contents
{: .no_toc}

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

### 1.1 What is PocketEstate?

PocketEstate is a desktop application for property agents to efficiently and effectively manage property data and appointment schedules. With PocketEstate, you can easily organize your property and clientele information, as well as keeping track of your schedules and deadlines.

PocketEstate is also highly optimized for users who can type fast and prefer typing over other means of input, allowing fast completion of most tasks solely via Command Line Interface (CLI).

### 1.2 Using this User Guide

This user guide uses various formatting styles to facilitate reading and to communicate ideas more effectively. Here are some important conventions to take note of when reading this user guide:

| Conventions               | Meaning                                                      |
|---------------------------|--------------------------------------------------------------|
| **Bold**                  | Important information                                        |
| :information_source: Note | Additional information                                       |
| :bulb: Tip                | Helpful tips                                                 |
| :exclamation: Caution     | Things to watch out for                                      |
| <kbd>Keyboard</kbd>       | Keyboard buttons                                             |
| `Code`                    | Examples                                                     |
| [SQUARE_BRACKETS]         | Optional parameters of a command                             |
| UPPER_CASE                | Parameters of a command to be supplied by the user           |
| ...                       | Parameters of a command that can be used multiple times      |

## 2. Quick start

### 2.1 Download and Launch

1. Ensure you have **Java 11 or above** installed in your Computer.

1. Download the latest `pocketestate.jar` from [here](https://github.com/AY2021S2-CS2103T-T13-4/tp/releases).

1. Copy the file to the folder you want to use as the **home folder** for PocketEstate.

1. **Double-click** the file to start the app. A Graphical User Interface (GUI) similar to the below should appear in a few seconds. Note how the app contains some sample data.<br><br>
   
   ![Ui](images/Ui.png)

### 2.2 Trying out

1. Let's try out your first command by adding a new property! Type <br>`add property n/Bishan t/Hdb a/Blk 150 Bishan Street 11 #02-101 p/570150 d/30-6-2021` into the command box and press <kbd>Enter</kbd> to execute it. After that, scroll down to the bottom of the property list to see your newly added property.
1. Want to view the properties in order of their asking price? Type the command <br>`sort property o/desc k/price` and press <kbd>Enter</kbd>. The property list will now be in descending order based on price, with the property having the highest asking price at the top.
1. Now let's try adding a new appointment. Type <br>`add appointment n/Meet Jacob for dinner r/At Lot One's food court d/19-4-2021 t/1930` and hit the <kbd>Enter</kbd> button. Scroll down to the bottom of the appointment list to see your newly added appointment.
1. Forgot when you are supposed to meet Simon again? Let's try finding out! Type <br>`find appointment simon` and press <kbd>Enter</kbd>. There it is! Your appointment with Simon is on Sep 20, 2021.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
 Some other example commands you can try:

{::nomarkdown}
<ul>
  <li>{:/}`list all`{::nomarkdown} : Lists all properties and appointments in the app.</li>
  <li>{:/}`delete property 2`{::nomarkdown} : Deletes the 2nd property shown in the current list of properties.</li>
  <li>{:/}`clear appointment`{::nomarkdown} : Clears all existing appointment data from the app.</li>
  <li>{:/}`exit`{::nomarkdown} : Exits the app.</li>
</ul>
{:/}

For a quick reference of the available commands and their syntax, refer to the [Command Summary](#6-command-summary) section.
</div>

--------------------------------------------------------------------------------------------------------------------

## 3. Commands

<div markdown="block" class="alert alert-info">**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add property n/NAME`, `NAME` is a parameter which can be used as <br>`add property n/Bishan`.

* Items in square brackets are optional.<br>
  e.g `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]` can be used as <br>`add property n/Bishan t/Hdb a/Blk 150 Bishan Street 11 #02-101 p/570150 d/30-6-2021` <br> or as <br>`add property n/Bishan t/Hdb a/Blk 150 Bishan Street 11 #02-101 p/570150 d/30-6-2021 r/Urgent to sell cn/George cc/91124788 ce/george_4788@gmail.com ca/$750,000 tags/Urgent, 4 bedrooms`.

* Items with `…` after them can be used multiple times, including zero times.<br>
  e.g. `[OPTION]...` can be used as `pl/$1,000,000`, `pl/$1,000,000 t/Condo` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME r/REMARKS`, `r/REMARKS n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/The Mayfair n/Mayfair`, only `n/Mayfair` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 3.1 General

#### 3.1.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 3.1.2 Exiting the program : `exit`

Exits the program.

Format: `exit`

### 3.2 Adding

#### 3.2.1 Adding a property: `add property`

Adds a property to the app.

Format: `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]​`

Description:
* There can be multiple tags but different tags should be separated with a comma. <br> e.g. `tags/TAGS_SEPARATED_BY_COMMAS` can be used as `tags/Freehold`, `tags/Freehold, 5 bedrooms`, `tags/Freehold, 5 bedrooms, Near MRT` etc.

Examples:
* `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021`
* `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021 r/Urgent to sell cn/Alice cc/91234567 ce/alice@gmail.com ca/$800,000 tags/4 bedrooms, No need for renovation`

#### 3.2.2 Adding an appointment: `add appointment`

Adds an appointment to the app.

Format: `add appointment n/NAME r/REMARKS d/DATE t/TIME​`

Examples:
* `add appointment n/Meet Alex r/At M Hotel d/17-2-2021 t/1500`

### 3.3 Editing

#### 3.3.1 Editing a property : `edit property`

Overwrites the information of the property according to the user input provided.

Format: `edit property INDEX [n/NAME] [t/PROPERTY_TYPE] [a/ADDRESS] [p/POSTAL_CODE] [d/DEADLINE] [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

There can be multiple tags but different tags should be separated with a comma. <br> e.g. `tags/TAGS_SEPARATED_BY_COMMAS` can be used as `tags/Freehold`, `tags/Freehold, 5 bedrooms`, `tags/Freehold, 5 bedrooms, Near MRT` etc.

</div>

Description:
* Edits the entry at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit property 1 r/Urgent to sell cn/Alice` Edits the remark and client name of the 1st property to be `Urgent to sell` and `Alice` respectively.

#### 3.3.2 Editing an appointment : `edit appointment`

Overwrites the information of the appointment according to the user input provided.

Format: `edit appointment INDEX [n/NAME] [r/REMARKS] [d/DATE] [t/TIME]`

Description:
* Edits the entry at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit appointment 3 d/21-03-2021 r/at M hotel` Edits the date and remark of the 3rd appointment to be `21-03-2021` and `at M hotel` respectively.

### 3.4 Deleting

#### 3.4.1 Removing an entry : `delete`

Deletes the specified property or appointment from the app.

Formats:
* `delete appointment INDEX`
* `delete property INDEX`

Description:
* Deletes the appointment or property at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …
* The field INDEX must be provided.

Examples:
*  `delete appointment 7` Deletes the `appointment` at index `7`.
*  `delete property 7` Deletes the `property` at index `7`.

### 3.5 Listing

#### 3.5.1 Listing all properties and appointments : `list all`

Shows a list of all properties and appointments in the app.

#### 3.5.2 Listing all properties : `list property`

Shows a list of all properties in the app.

#### 3.5.3 Listing all appointments : `list appointment`

Shows a list of all appointments in the app.


### 3.6 Updating status

#### 3.6.1 Updating the status of a property : `update`

Updates the status of a property from Option to Purchase, to Sales and Purchase Agreement to Completion

Formats:
* `update INDEX u/new AMOUNT`
* `update INDEX u/proceed`
* `update INDEX u/cancel`

Description:
* Updates the status of the property at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* The `new` keyword can only be used on a property without an existing status
* `proceed` or `cancel` can only be used on a property with an existing status
* `proceed` would move the status on to the next one. e.g. Option to Sales Agreement or Sales Agreement to Completion
* `cancel` would remove the status of the property

Examples:
*  `update 1 u/new 600000` Creates a new status with amount 600000 for the 1st property.
*  `update 3 u/proceed` Moves the status of the 3rd property to next one.

### 3.7 Sorting

#### 3.7.1 Sorting : `sort`

Sorts and shows a list of properties or appointments that is sorted according to the comparator provided.

Formats:
* `sort appointment o/SORTING_ORDER k/SORTING_KEY`
* `sort property o/SORTING_ORDER k/SORTING_KEY`

Description:
* Sorts appointment or property by the specified sorting key in ascending or descending order.
* The sorting key and sorting order fields must be specified.

Examples:
*  `sort appointment o/asc k/datetime` Sorts `appointment` by `datetime` in ascending order.
*  `sort property o/desc k/price` Sorts `property` by `price` in descending order.

### 3.8 Searching

#### 3.8.1 Searching properties: `find property`

Finds properties that match the criterion provided.

Formats:
* `find property [KEYWORD]... [OPTION]...`

Description:
* There can be 0 or more keywords and 0 or more options, but keywords and options cannot be both empty. All text are case insensitive. 

Options:
* `[t/PROPERTY_TYPE]`

    Search for properties whose property type field contain patterns specified in `[t/PROPERTY_TYPE]`. 
    
    The following property types are supported: 
    * hdb
    * condo
    * landed

* `[pm/PRICE_UPPER_LIMIT]`

    Search for properties with prices more than `[PRICE_UPPER_LIMIT]`. 

* `[pl/PRICE_LOWER_LIMIT]`

    Search for properties with prices less than `[PRICE_LOWER_LIMIT]`. 

Examples:
* `find property jurong west`
* `find property pm/500000`
* `find property bishan north t/hdb pl/$1,000,000`

#### 3.8.2 Searching appointments: `find appointment`

Finds appointments that match the criterion provided.

Formats:
* `find appointment [KEYWORD]...`

Description:
* There can be 0 or more keywords. Keywords are case insensitive. 

Examples:
* `find appointment bob`
* `find appointment sunday`

#### 3.8.3 Searching clients: `find client`

Finds appointments that matches the keywords and properties whose clients matches the same keywords. Both are done at the same time. 

Formats:
* `find client [KEYWORD]...`

Description:
* There can be 0 or more keywords. Keywords are case insensitive. 

### 3.9 Clearing

#### 3.9.1 Clearing all entries : `clear all`

Clears all properties and appointments from the app.

#### 3.9.2 Clearing all properties : `clear property`

Clears all properties from the app.

#### 3.9.3 Clearing all appointments : `clear appointment`

Clears all appointments from the app.

## 4. Storage

#### 4.1 Saving the data

PocketEstate data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually. PocketEstate data are saved as a JSON file after any command that modifies the data of the app.

The default storage data file paths used are:

* Property storage data file: `[JAR file location]/data/propertybook.json`. 
* Appointment storage data file: `[JAR file location]/data/appointmentbook.json`.

#### 4.2 Editing the data files

Advanced users are welcome to update data directly by editing the data files.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PocketEstate will discard all data and start with an empty data file at the next run. For example, if the property storage data file is corrupted but the appointment storage data file is in the correct format, PocketEstate will start with an empty data file for properties and use the original data file for appointments.
</div>

#### 4.3 Specifying the data files

It is also possible to specify your own property and/or appointment storage data files. In the `preferences.json` file that is generated when you first launch the app, you may edit the values of `propertyBookFilePath` and/or `appointmentBookFilePath` to your own property and appointment storage file paths respectively.

![Edit preferences.json file](images/editPreferencesJsonFile.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** For advanced users, it is also possible to specify your own configuration file, which contains your preferred preference file path. Refer to <a href="#71-launching-application-via-command-line">Appendix: Launching application via command line</a> for more information.

</div>

#### 4.4 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PocketEstate home folder.

--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`
**Add property** | `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]` <br><br> e.g., `add property n/Bishan t/Hdb a/Blk 150 Bishan Street 11 #02-101 p/570150 d/30-6-2021 r/Urgent to sell cn/George cc/91124788 ce/george_4788@gmail.com ca/$750,000 tags/Urgent, 4 bedrooms`
**Add appointment** | `add appointment n/NAME r/REMARKS d/DATE t/TIME` <br><br> e.g., `add appointment n/Meet Jacob for dinner r/At Lot One's food court d/19-4-2021 t/1930`
**Edit property** | `edit property INDEX [n/NAME] [t/PROPERTY_TYPE] [a/ADDRESS] [p/POSTAL_CODE] [d/DEADLINE] [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]`<br><br> e.g.,`edit property 1 r/Urgent to sell cn/Alice`
**Edit appointment** | `edit appointment INDEX [n/NAME] [r/REMARKS] [d/DATE] [t/TIME]`<br><br> e.g.,`edit appointment 3 d/28-03-2021 r/at M hotel`
**Remove an entry** | `delete appointment INDEX` <br> e.g. `delete appointment 7` <br><br> `delete property INDEX` <br> e.g. `delete property 7`
**List all** | `list all`
**List property** | `list property`
**List appointment** | `list appointment`
**Add new status** | `update INDEX u/new AMOUNT`<br><br> e.g.,`update 1 u/new 600000`
**Proceed to the next status** | `update INDEX u/proceed`
**Remove a status** | `update INDEX u/cancel`
**Sort** | `sort appointment o/SORTING_ORDER k/SORTING_KEY `<br> e.g., `sort appointment o/asc k/datetime`<br><br>`sort property o/SORTING_ORDER k/SORTING_KEY `<br> e.g., `sort property o/asc k/price`
**Find property** | `find property [KEYWORD]... [OPTION]...` <br><br> Options: <br>{::nomarkdown}<ul> <li>{:/}`[t/PROPERTY_TYPE]`{::nomarkdown}</li> <li>{:/}`[pl/PRICE_UPPER_LIMIT]`{::nomarkdown}</li> <li>{:/}`[pm/PRICE_LOWER_LIMIT]`{::nomarkdown}</li> </ul>{:/} e.g. `find property bishan north t/hdb pl/$1,000,000`
**Find appointment** | `find property [KEYWORD]` <br> e.g., `find appointment bob`
**Find client** | `find client [KEYWORD]` <br> e.g., `find client alice`
**Clear** | `clear property` <br> `clear appointment` <br> `clear all`

## 7. Appendix

### 7.1 Launching application via command line

For advanced users, it is also possible to launch the app via the command line. Doing so will allow you the option of specifying your own configuration file.  The configuration file can be used to specify the location of the preferences file, which contains the preferred storage file paths for both property and appointment data.

The default file paths used are:

* Configuration file: `[JAR file location]/config.json`
* Preferences file: `[JAR file location]/preferences.json`

<div markdown="span" class="alert alert-info">:information_source: **Note:** However, if the specified configuration file or the preferences file is in an incorrect format, the default file paths will be used.
</div>

To launch PocketEstate via the command line, 

1. First, open up your command prompt or terminal.
1. Then, `cd` into the base directory containing the `pocketestate.jar` file.
1. Run `java -jar pocketestate.jar --config={path_to_config_file.json}`, replacing `{path_to_config_file.json}` with your own JSON configuration file.<br>
E.g. `java -jar pocketestate.jar --config=myConfigFile.json`
