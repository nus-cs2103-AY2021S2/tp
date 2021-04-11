---
layout: page
title: Pan Jing Bin's Project Portfolio Page
---

## Project: SpamEZ

SpamEZ is a contact management system that aims to make it easier for the
School of Computing staff to disseminate information to the school. The user interacts with it using a CLI, and
it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added `MassDelete` command.
  * What it does: Allows users to delete all contacts
    within a certain index range.
  * Justification: This command allows the user to delete large sections of the 
    contact list without having to delete each contact one-by-one. This significantly
    increases convenience when the user has a large number of contacts.
  * Highlights: The input format uses the hyphen symbol (-) to separate between the start index
    and the end index. Thus entirely new methods will need to be written to parse the hyphen symbol.

* **New Feature**: Added `MassBlacklist` command.
  * What it does: Allows users to either blacklist or unblacklist all contacts within a certain index range.
  * Justification: Similar to the `MassDelete` command, this command makes it more convenient for the user to work
    with groups of contacts.
  * Highlights: The `MassBlacklist` command functions rather differently from the `Blacklist` command (which only blacklist one contact
    at a time) since that command "toggles" the blacklist status instead of setting the status to blacklist or
    unblacklist. Thus significant modifications need to be made to adapt the code from
    `Blacklist` into the `MassBlacklist` command.

* **New Feature**: Added `Remark` field to `Person`.
  * What it does: Allows users to assign an optional remark to each contact.
  * Justification: Many students will have special considerations. For example, some students
    may be suspended or on leave of absence. The `Remark` field allows the user
    to note down these special considerations.
  * Highlights: Since the `Person` class in our project contains more fields than
    that of the original AddressBook Level 3 (Such as `Blacklist` and `ModeOfContact`),
    significant modifications have to be made to the tutorial implementation of `Remark`.
    Frequent communication between team members are also needed to ensure that the different
    fields work properly when integrated, especially when the app is reading from a JSON file.

* **New Feature**: Added `Sort` command.
  * What it does: Allows users to sort the contact list by name in alphabetical order.
  * Justification: In NUS, many administrative procedures are performed based on alphabetical order
    (such as the seating plan for examinations). This command makes it more convenient for the user
    to perform such procedures.
  * Highlights: Implementing the `Sort` command requires a deep understanding
    of the entire codebase as new code will have to be introduced
    in the `UniquePersonList` class, which is at the lowest level of abstraction.
    

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=IceBear789&tabRepo=AY2021S2-CS2103-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Assisted in keeping track of deadlines and deliverables.

* **Enhancements to existing features**:
  * Implemented the `Comparable` interface in `Name` and `Email` to facilitate
  the implementation of `PersonComparator` class to compare 2 persons.

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `massdelete`, `massblist`, `remark` and `sort`:
    * Standardised the index format in the user guide:
  * Developer Guide:
    * Added implementation details for the commands `massdelete`, `massblist`, `remark` and `sort`:

* **Community**:
  * Contributed ideas to the design of the Graphical User Interface.
  * PRs reviewed:
  
* _{you can add/remove categories in the list above}_
