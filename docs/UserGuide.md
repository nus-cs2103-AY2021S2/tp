---
layout: page
title: User Guide
---

JJIMY is a **desktop app for managing your restaurant, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, JJIMY can get your restaurant management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `JJIMY.jar` from here.

3. Copy the file to the folder you want to use as the _home folder_ for JJIMY.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type in command in the command box and press Enter to execute it.

6. Refer to the [Features](#features) below for command details.

--------------------------------------------------------------------------------------------------------------------

## Features

### General

#### Access help page
To access the help page, input:
```
help
```
Alternatively, you can click on the ![settings](images/settings.png) icon > `help` or simply press `F1` on your keyboard.  
Shortly it will open up the `User Guide` in your default browser.

---

#### Exit application
To exit the application, input:
```
exit
```
Alternatively, you can click on the ![settings](images/settings.png) icons > `exit` or simply press `F2` on your keyboard.

### `customer`

#### `list` - List all customers
Format: 
```
customer list
```

#### `add` - Add a customers
Format: 
```
customer add n/[NAME] p/[PHONE_NUMBER] e/[EMAIL] a/[ADDRESS]
```

Example:
```
customer add n/Alan Tan p/81236789 e/alantan@nus.edu.sg a/21 Lower Kent Ridge Road, Singapore 119077 t/Gluten Allergy
```

The above command would add a new contact named "Alan Tan" with phone number 81236789, email address "alantan@nus.edu.sg", 
address "Lower Kent Ridge Road, Singapore 119077" and tagged with "Gluten Allergy".

#### `delete` - Delete customer
Format:
```
customer delete [INDEX] (-f)
```

In the event that there are uncompleted orders by the contact that is being attempted to be deleted, a `-f` flag has to be added to the end of the command to confirm the command. This is to prevent you from accidentally deleting orders unknowingly and leading to unhappy customers!

Examples:
```
customer delete 2
```

The above command would delete the second customer on the customer information pane.

```
customer delete 2 -f
```

In the event that there are pending orders that have not been fulfilled that belong to customer 2, then deletion of the customer will not be allowed since we do not want to unknowingly delete orders that are still pending.
If deleting all orders, even pending ones, is desired, then adding the `-f` at the end of the command acknowledges and confirms the deletion behavior.

#### `edit` - Edit customer information
Format:
```
customer edit [INDEX] (n/[NAME]) (p/[PHONE_NUMBER]) (e/[EMAIL]) (a/[ADDRESS])
```

At least one of the fields in brackets must be present in the edit command.

Examples:

```
customer edit 2 n/George Tan
```

The above command changes the name of the second customer on the contacts pane to "George Tan".

```
customer edit 2 n/George Tan p/80009999
```

The above command changes both the name and phone number of the second customer.

From the examples above, more than one field can be edited at a time but at least 1 field must be specified for it to be a valid command.

#### `find` - Find contact
Format:
```
customer find n/[KEYWORD] (MORE_KEYWORDS)...
```

- `n/` - Finds all customers whose names contain any of the keywords (case-insensitive). Keywords are space separated.

Example:
```
customer find n/George Michael Lim
```

The above command would display all contacts with names that contain either "George", "Michael", or "Lim". Note that at least 1 keyword must be specified, `customer find n/Thomas` is a valid command too with only 1 keyword ("Thomas").

### `menu`

#### `list` - List all dishes
Format: 
```
menu list (-a)
```

#### `add` - Add a dish
Format: 
```
menu add n/[NAME] p/[PRICE]  
```

#### `delete` - Delete a dish from the menu
Format:
```
menu delete [INDEX]
or
menu delete [INDEX] (-f)
```

#### `edit` - Edit dish
Format:
```
menu edit [INDEX] (n/[NAME]) (p/[PRICE])
```

At least one of the fields in brackets must be present in the edit command.

#### `find` - Find dish
Format:
```
menu find n/[KEYWORD] (MORE KEYWORDS) i/[KEYWORD]
```

At least one prefix must be specified. If both are specified, both conditions will be checked.

- `n/` - Finds all dishes with names that contain any of the keywords (case-insensitive). Keywords are space separated. 
- `i/` - Finds all dishes with ingredient names that contain keyword (case-insensitive).

### `inventory`

#### `list` - List all items
Format: 
```
inventory list
```

#### `add` - Add an item
Note:
If the ingredient already exists, its listed quantity will be incremented.

Format: 
```
inventory add n/[INGREDIENT_NAME] q/[QUANTITY]
```

#### `decrease` - Decrease quantity of item
Format:
```
inventory decrease [INDEX] [QUANTITY]
```

#### `delete` - Delete an item
Format:
```
inventory delete [INDEX]
or
inventory delete [INDEX] (-f)
```

#### `edit` - Edit item
Format:
```
inventory edit [INDEX] (n/[INGREDIENT_NAME]) (q/[QUANTITY])
```

At least one of the fields in brackets must be present in the edit command.

#### `find` - Find an item
Format:
```
inventory find n/[KEYWORD] (MORE KEYWORDS) q/[LESS THAN QUANTITY]
```

At least one prefix must be specified. If both are specified, both conditions will be checked.

- `n/` - Finds all ingredients with names that contain any of the keywords (case-insensitive). Keywords are space separated. 
- `q/` - Finds all ingredients with less than specified quantity. Must be a non-negative whole number (>= 0).

### `order`

#### `list` - List all orders
Format: 
```
order list
```

#### `add` - Add an order
Format: 
```
order add n/[CUSTOMER_NAME] dt/[DELIVERY_DATETIME] (DD-MM-YYYY HH:MM) d/[DISH_NAME] q/[QUANTITY]...  
```

#### `delete` - Delete an order
Format:
```
order delete [INDEX]
```

#### `edit` - Edit item
Format:
```
inventory edit [INDEX] (n/[CUSTOMER_NAME]) (dt/[DELIVERY_DATETIME] (DD-MM-YYYY HH:MM)) (d/[DISH_NAME] q/[QUANTITY]...)
```

At least one of the fields in brackets must be present in the edit command.

#### `find` - Find an order
Format:
```
orders find n/[KEYWORD] (MORE KEYWORDS) q/[LESS THAN QUANTITY]
```

At least one prefix must be specified. If both are specified, both conditions will be checked.

- `n/` - Finds all orders with customer names that contain any of the keywords (case-insensitive). Keywords are space separated. 
- `d/` - Finds all orders with dish names that contain keyword (case-insensitive).

#### `complete` - Mark order as completed
Format:
```
order complete [INDEX]
```

#### `history` - List all completed and cancelled orders
Format:
```
order history
```

--------------------------------------------------------------------------------------------------------------------

## FAQ

In progress

--------------------------------------------------------------------------------------------------------------------
