---
layout: page
title: User Guide
---
<!-- This UG's structure is inspired by 
https://ay2021s2-cs2103t-t11-2.github.io/tp/UserGuide.html#1-introduction
-->

<div class="toc-no-bullet-points">
  * Table of Contents
  {:toc}
</div>

## **1. Introduction**
Welcome to our User Guide and thank you for using CakeCollate! Are you a home baker searching for a reliable tool to keep track of your orders? 
CakeCollate promises to be an efficient desktop application that allows you to easily consolidate and manage your orders. Our main features include:<br>
1. Order management
2. Order Item management
3. Reminders for undelivered orders that have delivery dates approaching the current date
4. Checking the delivery status of your orders

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you're a small-time cake seller that can type fast, CakeCollate can get your order management tasks done faster than traditional GUI apps.

Let us take you through how to user our CakeCollate in the rest of our user guide.

--------------------------------------------------------------------------------------------------------------------
## **2. Using the User Guide**
In this section, you can learn more about the different terminologies and what to expect from each section of the User Guide.
This allows you to better comprehend the terms that are used and quickly navigate to sections where the solution to your questions
may lie.

### **2.1 What's in the User Guide**

In [Section 2.2: Reading the User Guide](#22-reading-the-user-guide), you can find essential information that enables to read
the user guide seamlessly.

In [Section 3: Quick Start](#3-quick-start), you can find the instructions of how and where you can start installing and setting up 
CakeCollate.

In [Section 4: Features](#4-features), you can find instructions on what are the existing features and commands in CakeCollate
and how you can use them to suit your needs.

In [Section 5: Glossary](#5-glossary), you can find some commonly used terms from our user guide and app at a glance.

In [Section 6: FAQ](#6-faq), you can find solutions to commonly asked questions.

In [Section 7: Command Summary](#7-command-and-prefix-summary), you can find a summary to all of existing CakeCollate's commands.


### **2.2 Reading the User Guide**
In this section, you will learn how to read CakeCollate's User Interface (UI), the format of commands, and the user inputs
commands may receive.

#### **2.2.1 Sections of the UI**
![User Interface](images/Annotated%20UI.png)

#### **2.2.2 Formatting of Commands**

<div markdown="block" class="alert alert-info">

**:information_source: How to interpret the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional fields that the user can choose to include or not as an input.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times. <br>  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc. <!-- order desc? --> 

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
  
* `INDEXES` refer to the list number of orders on the left of the GUI while `ORDER_ITEM_INDEXES` refers to the list number of order items on the right of the GUI <!-- [comment]: <> (can add a link to the gui screenshot and annotate which index refers to what) -->

* Items that are `INDEXES` or `ORDER_ITEM_INDEXES` take in whole number parameters separated by spaces. 
  For example, for a command that takes in `oi/ORDER_ITEM_INDEXES`, you can input `oi/1` or `oi/1 4 5`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

#### **2.2.3 Types of User Input**
In this section, you will learn about the commonly used User Inputs as well as their accompanying prefixes. 
These will be helpful when you are trying to specify the fields for certain commands.
<br><br>


##### `ADDRESS`
The address of the customer who has placed the order.<br>
Prefix: `a/`
* It can contain any type of characters.
* It cannot be empty.

##### `DAYS`
The number of days from the current date.<br>
Prefix: `none`
* It can only contain integers greater than or equal to 0.
  E.g. `0`,`365`,`99`

##### `DELIVERY_DATE`
The delivery date for the order.<br>
Prefix: `d/`
* It should be a valid calendar date.
* It should be any of the format:
  * `dd/MM/yyyy` E.g. `01/12/2021`
  * `dd-MM-yyyy` E.g. `31-12-2021`
  * `dd.MM.yyyy` E.g. `01.12.2021`
  * `dd MMM yyyy` E.g. `31 Dec 2021`
* When adding or editing an order using the commands, the `DELIVERY_DATE` should be today's date or a future date.<br>
  I.e. the date today or a date after today.
* Orders with a `DELIVERY_DATE` before today's date will not be deleted.<br>
  I.e. If you entered an order with a `DELIVERY_DATE` for tomorrow, the order will not be deleted even if you launch the application again in two days.<br>
:information_source: You do not have to worry about losing track of overdue orders.<br>

**:exclamation: For advanced users:** You will be able to enter a delivery date in the past to an order that has yet to be delivered into the storage file, `cakecollate.json`. The app will not be able to warn you that you have added an invalid delivery date. As such, you are recommended to add/edit a delivery date through the application itself.

##### `INDEX`
Each order is given a particular index so you can easily refer an order for certain commands. The index of an order is found on the left side of the gui
in the [orders box](#221-sections-of-the-ui).<br>
Prefix: `none`
* It can only contain positive integers greater than 1, but should not be greater than the total number of orders in 
the Orders Box.
  E.g. `1`,`20`,`35`

##### `INDEXES`

Certain commands allow you to enter multiple [index parameters](#index), including the [delete command](#deleting-an-order-delete), which allows you to delete multiple orders at one go.

Prefix: `none`
* To specific multiple indexes, you can separate them with a space. For example `1 2 5`.
  
##### `EMAIL`
The email of the customer who has placed the order.<br>
Prefix: `e/`
* Emails should be of the format `local-part@domain`.
* `local-part` can contain alphabetical or numerical characters and these special characters: ``!#$%&'*+/=?`{|}~^.-``.<br>
  E.g. `alice#3in?wonderland!`
* `domain` should
  * be at least 2 characters long
  * start and end with alphabetical or numerical characters
  * contain alphabetical or numerical characters, a period `.` or a hyphen `-` for the characters in between, if any.<br>
  E.g. `sample-domain.com`
  
##### `NAME`
The name of the customer who has placed the order.<br>
Prefix: `n/`
* It can contain alphabetical characters, numbers and spaces.<br>
  E.g. `Alex Yeoh`, `Johnathan9`, `Charlotte the 5th`
* It cannot be longer than 70 characters.
* It cannot be empty.

##### `ORDER_DESCRIPTION`
The order description of the order.<br>
Prefix: `o/`
* Each order description cannot be longer than 70 characters.
* It cannot be empty.<br>
  E.g. `Durian Cake`, `Blackforest Cake`

##### `ORDER_ITEM_INDEXES`
The order item index of the order item table.<br>
Prefix: `oi/`
* This refers to indexes of the order item table in the [order item box](#221-sections-of-the-ui).
* This can be used with or as a replacement for `ORDER_DESCRIPTION`, given the order item in the table matches the order description you want to add/edit.

##### `PHONE_NUMBER`
The phone number of the customer who has placed the order.<br>
Prefix: `p/`
* It can only contain numbers.<br>
  E.g. `90126969`
* It should be at least 3 digits long.
* It cannot be longer than 20 digits.

##### `REQUEST`
The special request or notes you can add to an order. What makes it different from tags it that it can contain
large amounts of information.<br>
Prefix: `r/`
* It can contain any type of characters.
* It can be empty.
  * An empty request is used to clear/reset the `REQUEST` field of the order.

:information_source: The user input `Request` and its prefix `r/` will only be used in the [Request Command](#adding-a-special-request-to-an-order-request) and [Find Command](#locating-orders-find).

##### `TAG`
The tags you can add to an order. A small piece of information you can add to an order.<br>
Prefix: `t/`
* It can contain alphabetical or numerical characters but not spaces.
* Each tag cannot be longer than 30 characters.
* There is no specific usage for `TAG`.
  * Use it as a tag for the customer. E.g. `friend`, `fussy`, `important`
  * Use it as a tag for the order. E.g. `urgent`, `complicated`
  
## **3. Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cakecollate.jar` from [here](https://github.com/AY2021S2-CS2103T-T11-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CakeCollate.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. 
You can use the sample data pre-loaded in the application to get play around and get used to the available commands. <br><br> 

    ![Ui](images/Ui.png)


5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will display the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all orders in the CakeCollate database.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/13-05-2021 o/2 x Strawberry Cake` : Adds a `2 x Strawberry Cake` order to CakeCollate.

   * **`delete`**`3` : Deletes the 3rd order shown in the current list.
   
   * **`remind`**`2` : Lists all undelivered orders that are 2 days within the current local date.

   * **`clear`** : Deletes all order in the CakeCollate database.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#4-features) section below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **4. Features**

This section contains information about all existing commands and features that we have implemented.
Being well-versed in this section will enable you to use our application to it's fullest potential. 

### **4.1 Orders**

#### **4.1.1** ***Interacting with orders***

##### Adding an order: `add`

Adds an order to the CakeCollate database. The order will be initialised as undelivered, but can be modified with the `delivered` and `cancelled` commands found below.

###### Simple format

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DELIVERY_DATE o/ORDER_DESCRIPTION... [t/TAG]...`

Examples:

* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 d/13-05-2100 o/chocolate cake o/5 durian cake o/10 mochi cake 100 t/friend t/daughter` adds an order with all compulsory fields, three order descriptions, and a friend and daughter tag.

* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 d/13-05-2100 o/chocolate cake` adds an order with all compulsory fields, one order description and no tags.


###### Alternative format 

Advanced Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DELIVERY_DATE oi/ORDER_ITEM_INDEXES [o/ORDER_DESCRIPTION]... [t/TAG]...`

* The difference here compared to the previous format is that you can omit the `ORDER_DESCRIPTION` parameter, but you need to include an `ORDER_ITEM_INDEXES` parameter.

* This alternative format is aimed at saving you some typing. If an order description you want to type already exists in the order items table of the GUI, you can specify its index instead of typing its entire name out.

* For example, for an order that involves chocolate cake, if `chocolate cake` exists in the order items table as shown in the screenshot below, instead of typing `o/chocolate cake`, you can type out `oi/1`

* As such, you can interpret the order item table of the gui as being a shortcut table.

[comment]: <> ({screenshot})

Examples

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/13-05-2021 oi/1 4 5` adds an order with all compulsory fields and adds order items 1, 4, 5 of order items table to the order.

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/13-05-2021 o/strawberry cake oi/1` adds an order with all compulsory fields, an order description of strawberry cake and the first item in the list of order items in the GUI.

:information_source: **How are items added to the order items table?**<br>

* When you specify an order description using the prefix `o/`, e.g. `o/chocolate cake`, the app automatically adds it into the order items table. As such, you don't need to go through extra steps to add in items into the order items table manually.

* If you do want to add items into the order items table, you can refer to the [add item command](#adding-order-items-additem) below.

:information_source: **How do you specify that a cake has been ordered multiple times?**<br>

* If a user orders a chocolate cake multiple times, you can repeat the `o/` prefix twice, e.g. `o/chocolate cake o/chocolate cake`.

* Alternatively, if the chocolate cake is in index 1 of the order items table, you can use `oi/1 1`.


##### Adding a special request to an order: `request`

Adds a special request to an existing order in the CakeCollate database.

Format: `request INDEX r/REQUEST`

* Adds a special request to the order at the specified `INDEX`. The index refers to the index number shown in the displayed order list. The index **must be a positive integer** 1, 2, 3, …​
* Adding new special requests to an order will replace the existing special request the order currently has.
* You can remove an order’s special request by typing `r/` without specifying any requests after it, or only simply
specifying the index without adding the prefix.

Examples:
* `request 1 r/More sugar, spice and everything nice` Sets the special request of the 1st order to be `More sugar, spice and everything nice.`
* `request 1 r/` Removes the 1st order's current special request.
* `request 1` Removes the 1st order's current special request.
* Assuming the special request on an order was previously empty as shown in this picture,
![empty_request](images/Empty_request.PNG) using the command `request 1 r/More sugar, spice and everything nice` would
update the order's current special request to ![fllled_request](images/Filled_request.PNG) 
using the command `request 1` or `request 1 r/` on the same order again would update the request of the order back to 
![empty_request](images/Empty_request.PNG).


##### Deleting an order: `delete`

Deletes the specified orders from the CakeCollate database.

Format: `delete INDEXES`

* Deletes the orders with the specified `INDEXES`.
* The `INDEXES` refers to the order `INDEXES` number shown in the list of orders.
* One or more `INDEXES` can be entered.
* The `INDEXES` **must be valid**.

Examples:
* `delete 2` deletes the order with `INDEX` 2 from the CakeCollate database.
* `delete 2 3` deletes the orders with `INDEXES` 2 and 3 from the CakeCollate database.

##### Updating Delivery Status of an order

###### Setting the delivery status of an order as undelivered: `undelivered`

Sets the delivery status of the specified order/orders from the CakeCollate database as `undelivered`.

Format: `undelivered INDEXES...`

* Sets the delivery status of the orders with the specified indexes as `undelivered`.
* The `INDEX` refers to the order `INDEX` number shown in the list of orders.
* One or more indexes can be entered.
* The `INDEX` **must be valid**.

Examples:
* `list` followed by `undelivered 2` sets the delivery status of the orders with `INDEX` 2 from the 
  CakeCollate database as `undelivered`.
* `undelivered 2 3` sets the delivery status of the orders with indexes 2 and 3 from the 
  CakeCollate database as `undelivered`.

###### Setting the delivery status of an order as delivered: `delivered`

Sets the delivery status of the specified order/orders from the CakeCollate database as `delivered`.

Format: `delivered INDEXES...`

* Sets the delivery status of the orders with the specified indexes as `delivered`.
* The `INDEX` refers to the order `INDEX` number shown in the list of orders.
* One or more indexes can be entered.
* The `INDEXES` **must be valid**.

Examples:
* `list` followed by `delivered 2` sets the delivery status of the orders with `INDEX` 2 from the
  CakeCollate database as `delivered`.
* `delivered 2 3` sets the delivery status of the orders with indexes 2 and 3 from the CakeCollate database as `delivered`.

###### Setting the delivery status of an order as cancelled: `cancelled`

Sets the delivery status of the specified order/orders from the CakeCollate database as `cancelled`.

Format: `cancelled INDEXES...`

* Sets the delivery status of the orders with the specified indexes as cancelled.
* The `INDEX` refers to the order `INDEX` number shown in the list of orders.
* One or more indexes can be entered.
* The `INDEX` **must be valid**.

Examples:
* `list` followed by `cancelled 2` sets the delivery status of the orders with `INDEXE` 2 from the
  CakeCollate database as `cancelled`.
* `cancelled 2 3` sets the delivery status of the orders with indexes 2 and 3 from the CakeCollate database as `cancelled`.

##### Editing an order: `edit`

Edits an existing order in the CakeCollate database.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [d/DELIVERY_DATE] [o/ORDER_DESCRIPTION]​`

* Edits the order at the specified `INDEX`. The index refers to the index number shown in the displayed order list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the order will be removed i.e adding of tags is not cumulative.
* You can remove all the order’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st order to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd order to be `Betsy Crower` and clears all existing tags.

#### **4.1.2** ***Order Functionalities*** 

##### Locating orders: `find`

Find orders whose specified field contain any of the given keywords.

Format: `find [n/KEYWORD_NAME]... [p/KEYWORD_PHONE]... [e/KEYWORD_EMAIL]... [a/KEYWORD_ADDRESS]... [o/KEYWORD_ORDER_DESCRIPTION]... [t/KEYWORD_TAG]... [d/KEYWORD_DELIVERY_DATE]... [s/KEYWORD_DELIVERY_STATUS]... [r/KEYWORD_REQUEST]... `

* The search is case-insensitive. E.g. `hans` will match `Hans`.
* The order of the keywords does not matter. E.g. `Hans Bo` will match `Bo Hans`.
* Sub-strings will be matched. E.g. `Han` will match `Hans`.
* Fields are searched according to specified prefixes. E.g. `n/Hans` will only find orders with name that match `Hans`.
* If no prefixes are specified, orders matching at least one keyword will be returned (i.e. `OR` search). E.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* If multiple keywords are specified for a certain prefix, orders matching at least one keyword for the specified field will be returned (i.e. `OR` search). E.g. `n/Hans Bo` will return orders with name `Hans Gruber`, `Bo Yang`.
* If multiple prefixes are specified, each keyword specified for each field must match orders with corresponding fields. (i.e. `AND` search) e.g. `n/Hans o/Cake` will only match orders with name that matches `Hans` and order description that matches `Cake`.
* `AND` searches will take priority.

Examples:
* `find n/John` will return all orders with name `john`, `John Doe` or `Johnathan`.
* `find n/Alex Bob` will return all orders with name `Alex`, `alexander`, `Bob` or `bobby`.
* `find n/Alex o/Chocolate` will return all orders with name `Alex` and order description `Chocolate`.<br>
  ![result for 'find n/Alex o/Chocolate'](images/findAlexChocolate.PNG)
<br><br>
* `find alex bernice` will return all orders that matches `alex` or `bernice`.<br>
  ![result for 'find alex bernice'](images/findAlexBernice.PNG)
<br><br>
* `find n/Alex Charlotte o/Chocolate` will return all orders that matches (`Alex` or `Charlotte`) and `Chocolate`. <br>
  ![result for 'find n/Alex Charlotte o/Chocolate'](images/findAlexCharlotteChocolate.PNG) <br>
  
##### List all existing orders: `list`

Shows a list of all orders in the CakeCollate database.

Format: `list`

##### Receiving reminders for orders: `remind`

Displays a list of reminder for all undelivered orders that are X days within the current date.

Format: `remind DAYS`

* Lists all undelivered orders within the current date to the numbers of days from the specified date.
* The `DAYS` refers to the number of days from the current date.
* The `DAYS` **must be a positive integer starting from 0**.

Examples:
* `remind 0` lists all undelivered orders that have a delivery date for today.
* `remind 365` lists all undelivered orders that have a delivery date within 365 days from today. <br>
    ![result for 'remind 365'](images/Remind365.PNG) 


### **4.2 Order Items**

#### Adding order items: `addItem`

Adds an order item to the order items table on the right side of the GUI.

Format: `addItem ORDER_ITEM_DESCRIPTION`

* The `ORDER_ITEM_DESCRIPTION` can only contain alphanumeric characters and ".

Examples:
* `addItem 2 x Red Velvet` adds an order item with the specified description to the order items table.

#### Deleting order items: `deleteItem`

Deletes order items from the order items table based on the specified list of indices.

Format: `deleteItem ORDER_ITEM_INDEXES`

* The `ORDER_ITEM_INDEX` refers to the `ORDER_ITEM_INDEX` number shown in the order items table.
* One or more `ORDER_ITEM_INDEXES` can be entered.
* The `ORDER_ITEM_INDEXES` **must be valid**.

Examples:
* `deleteItem 2` deletes the order items with `ORDER_ITEM_INDEX` 2 from the order items table.
* `deleteItem 2 3` deletes the order items with `ORDER_ITEM_INDEXES` 2 and 3 from the order items table.

### **4.3 Others**

#### Viewing help: `help`

* Gives instructions on how to enter orders into the CLI.
* Displays a message with a list of all available commands and their format.
* To return to the main order list click on the `Return to the main order list` button. 
* Help can also be accessed by clicking the `help` button in the top left corner of the application or by clicking the `F1` keyboard key.
<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** You can enter the command `list` to go back to the order and order item lists.<br>
</div> <br>

Format: `help`

#### Clearing all existing orders and order items: `clear`

Deletes all existing orders and order items in the CakeCollate database.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This is not an un-doable action! With this command, all existing orders and order items will be deleted from 
the CakeCollate database and you will not be able to retrieve them. 
</div>

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

CakeCollate data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

CakeCollate data is saved as a JSON file `[JAR file location]/data/cakecollate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CakeCollate will discard all data and start with an empty data file at the next run.
</div>

#### Keyboard shortcuts

* Click the `Up` arrow in the keyboard to traverse up the previously inputted commands if they exist. 
* Click the `Down` arrow in the keyboard to traverse down the previously inputted commands if they exist. 
* Click the `Shift`Button followed by the `Backspace` button on the keyboard to delete all the text in the command box in one go. 

--------------------------------------------------------------------------------------------------------------------

## **5. Glossary**


--------------------------------------------------------------------------------------------------------------------

## **6. FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CakeCollate home folder.

**Q**: How do I install Java?<br>
**A**: [Click Here](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) and download java based 
on the operating system of the computer you are running CakeCollate on.

--------------------------------------------------------------------------------------------------------------------

## **7. Command and prefix summary**

[comment]: <> (Would something along the lines of `Setting order details` and `Displaying specific orders` improve the next two headings??)

### Order Interaction

Action  | Format
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DELIVERY_DATE o/ORDER_DESCRIPTION... [t/TAG]...` <br> <br> `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DELIVERY_DATE oi/ORDER_ITEM_INDEXES [o/ORDER_DESCRIPTION]... [t/TAG]...`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/ORDER_DESCRIPTION]... [t/TAG]...`
**Request** | `request INDEX r/REQUEST` 
**Undelivered** | `undelivered INDEXES`
**Delivered** | `delivered INDEXES`
**Cancelled** | `cancelled INDEXES`
**Delete** | `delete INDEXES`

### Order Functionalities

Action | Format
-------|----------
**Find** | `find [n/KEYWORD_NAME]... [p/KEYWORD_PHONE]... [e/KEYWORD_EMAIL]... [a/KEYWORD_ADDRESS]... [o/KEYWORD_ORDER_DESCRIPTION]... [t/KEYWORD_TAG]... [d/KEYWORD_DELIVERY_DATE]... [s/KEYWORD_DELIVERY_STATUS]... [r/KEYWORD_REQUEST]...`
**List** | `list`
**Remind** | `remind DAYS`


### Order Items

Action | Format
-------|----------
**Add Order Item** | `addItem ORDER_ITEM_DESCRIPTION`
**Delete Order Item** | `deleteItem ORDER_ITEM_INDEXES`

### Others

Action | Format
-------|----------
**Help** | `help`
**Clear** | `clear`
**Exit** | `exit`

### Prefix

Prefix  | Description
--------|------------------
**n/** | Name of the customer
**p/** | Phone number of the customer
**e/** | Email of the customer
**a/** | Address of the customer
**d/** | Delivery date of the order
**o/** | Order placed by the customer
**oi/** | Order index of the order placed by the customer, based on the order item table on the right
**t/** | Tags for the order
**r/** | Request placed by the customer for an order
**s/** | Status of the order (undelivered, delivered or cancelled)


## Acknowledgements

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
