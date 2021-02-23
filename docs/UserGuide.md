---
layout: page
title: User Guide
---

Chesse Inventory Management (CHIM) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Content
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

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

### Adding a customer: `addcustomer`

Adds a customer to the CHIM.

Format: `addcustomer n/CUSTOMER_NAME p/PHONE_NUMBER a/ADDRESS`

Example: `addcustomer n/John Doe p/65555555 a/Blk 436 Serangoon Gardens St 26 #01-01`


### Remove an order: `removeorder`

Removes a specific order from CHIM, identified by its index.

Format: `removeorder ORDER_INDEX`
* Removes the order at the specified `ORDER_INDEX`. The index must be a positive integer.

Example: `removeorder 2`
* Removes the second order in the list of orders.


### List orders: `listorder`

Displays all incomplete orders in CHIM.

Format: `listorder`


### List cheese: `listcheese`

Displays the current inventory count. If a cheese type is specified, then display the current inventory count for that particular cheese.

Format: `listcheese [t/CHEESE_TYPE]`

Examples: `listcheese Mozzarella`


### List customer: `listcustomer`

Displays the customers in CHIM.

Format: `listcustomer`


### Mark order as complete: `done`

Marks an incomplete order as complete from CHIM, identified by its index.

Format: `done ORDER_INDEX`
* Marks the order at the specified `ORDER_INDEX`.
* The `ORDER_INDEX` must be a positive integer.


### Search for customer: `search`

Searches for a customer in CHIM.

Format: `search n/NAME`
* Search is case-insensitive, e.g Betty will match betty

Examples:
* `search n/Betty`
* `search n/Robin Lee`


### Exiting the program: `exit`

Exits the program.

Format: `exit`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List Customer** | `listcustomer`
**Mark Done** | `done INDEX`<br> e.g., `done 2`
**Search** | `search n/NAME` <br> e.g. `search n/Betty`
**Exit** | `exit`

