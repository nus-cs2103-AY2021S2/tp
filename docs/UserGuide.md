---
layout: page
title: User Guide
---

#Welcome to Car@leads
Car@leads is a **desktop app for a car salesperson to manage customer contacts** (CLI).
<br> Users can add new customer contacts which include details such as customer's car brand and car type preferences along with other personal information.
<br> Users can filter through the customer data using specific filters, such as filtering out customers who prefer a specific car brand and follow up with them for sales opportunities.
<br> Car@leads is optimized for use via a Command Line Interface, while still having the benefits of a Graphical User Interface (GUI).
<br> If you can type fast, Car@leads can get your contact management tasks done faster than traditional GUI apps.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   <br>Note how the app contains some sample data to provide a example of how app usage.
   <br>Remember to clear these sample data using the clear command before use. <br>
   ![Ui](images/Ui.png)
1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`add`**`n/Bob Ang p/88765432 e/bobhnd@example.com a/John street, block 123, #01-01 b/1998 07 10  c/BMW+Coupe|2030 01 01 c/Porsche+SUV|2030 01 01`
      : Adds a contact named `Bob Ang`

    * **`find`**`n/Bob Ang p/88765432 c/BMW+Coupe|2030 01 01 c/Porsche+SUV|2030 01 01`
      : Finds a contact named `Bob Ang`

    * **`delete`**`John Doe` : Deletes 'John Doe' contact from contact list .

    * **`list`** : Lists all contacts.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

    * **`help`** : Get a legend of commands.

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


### Adding a customer: `add`

Adds a customer to the contact list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/OWNED_CARBRAND+OWNED_CARTYPE|COE_EXPIRY_DATE [t/TAG]…​`
`n/add n/Bob Ang p/88765432 e/bobhnd@example.com a/John street, block 123, #01-01 c/BMW+Coupe|2030 01 01 c/Porsche+SUV|2030 01 01`

Note that the `COE_EXPIRY_DATE` input order: YYYY{space}MM{space}DD

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A customer can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/Honda City`
* `add n/Betsy Crowe c/Honda City t/friend e/betsycrowe@example.com x/2011 03 27 a/Newgate Prison p/1234567 t/criminal`

### Finding a customer: `find` {TO BE EDITED}

Find customers from the contact list that matches specified filters.

Format: `find  n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/OWNED_CARBRAND+OWNED_CARTYPE|COE_EXPIRY_DATE [t/TAG]…​`
`n/find [e/bob /AND p/98761234] OR b/1999 10 11`

Note that the `COE_EXPIRY_DATE` input order: YYYY{space}MM{space}DD

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A customer can have any number of tags (including 0)
</div>

Examples:
* `Find c/Honda City`


### Deleting a customer : `delete`

Deletes the specified customer from the contact list.

Format: `delete name`

* Deletes the customer with the specified `name`.
* The name refers to the customer name shown in the displayed customer contact list.
* The name **must be valid** does not contain special characters  …​

### Listing all customers : `list`

Shows a list of all customers in the system

Format: `list`

### Clear all customers : `clear`
* **`clear`** : Deletes all customers in the system.


### Exiting app: `exit`
* **`exit`** : Exits the app.

### Quick help: `help`
* **`help`** : Overview of commands and input syntax.
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/OWNED_CAR+COE_EXPIRY_DATE [t/TAG]…​`<br> e.g., `n/add n/Bob Ang p/88765432 e/bobhnd@example.com a/John street, block 123, #01-01 c/BMW Coupe|2030 01 01 c/Porsche|2030 01 01`
**find TO BE EDITED** | `find [e/bob /AND p/98761234] OR b/1999 10 11`
**delete** | `delete NAME`<br> e.g., `delete John doe`
**list** | `list`
**clear** | `clear`
**exit** | `exit`
**help** | `help`
