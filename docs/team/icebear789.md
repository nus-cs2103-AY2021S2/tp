---
layout: page
title: Pan JingBin's Project Portfolio Page
---

## Project: SpamEZ

SpamEZ is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
It helps users to classify contacts based on their details in order to facilitate effective and efficient information dissemination to a large audience.

Given below are my contributions to the project.

* **New Feature**: Added `MassDelete` command (Pull request: [#50](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/50))
  * What it does: Allows users to delete all contacts
    within a certain index range.
  * Justification: This command allows the user to delete large sections of the 
    contact list without having to delete each contact one-by-one. This significantly
    increases convenience when the user has a large number of contacts.
  * Highlights: The input format uses the hyphen symbol (-) to separate between the start index
    and the end index. Since the address book is not able to parse the hyphen symbol by default, entirely new methods
    will need to be written. 

* **New Feature**: Added `MassBlacklist` command (Pull request: [#66](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/66))
  * What it does: Allows users to either blacklist or unblacklist all contacts within a certain index range.
  * Justification: Similar to the `MassDelete` command, this command makes it more convenient for the user to work
    with groups of contacts.
  * Highlights: The `MassBlacklist` command functions rather differently from the `Blacklist` command (which only blacklist one contact
    at a time) since that command "toggles" the blacklist status instead of setting the status to blacklist or
    unblacklist. Thus significant modifications need to be made to adapt the code from
    `Blacklist` into the `MassBlacklist` command.

* **New Feature**: Added `Remark` field to `Person` (Pull request: [#26](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/26))
  * What it does: Allows users to assign an optional remark to each contact.
  * Justification: Many students will have special considerations. For example, some students
    may be suspended or on leave of absence. The `Tag` feature is inadequete in this regard due to its restrictive
    formatting (e.g. does not allow spaces). The `Remark` field provides a way for the user
    to note down these special considerations. 
  * Highlights: Since the `Person` class in our project contains more fields than
    that of the original AddressBook Level 3 (Such as `Blacklist` and `ModeOfContact`),
    significant modifications have to be made to the tutorial implementation of `Remark`.
    Frequent communication between team members are also needed to ensure that the different
    fields work properly when integrated, especially when the app is reading from a JSON file.

* **New Feature**: Added `Sort` command (Pull requests: [#22](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/22), [#47](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/47))
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
  the implementation of `PersonComparator` class to compare 2 persons (Pull request: [#22](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/22))

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `massdelete`, `massblist`, `remark` and `sort` (Pull request: [#175](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/175))
    * Standardised the index format in the user guide (Pull request: [#175](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/175))
  * Developer Guide:
    * Added implementation details for the commands `massdelete`, `massblist`, `remark` and `sort` (Pull requests: [#165](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/165),
      [#170](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/170), [#176](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/176))

* **Community**:
  * Contributed ideas to the design of the Graphical User Interface.
  * Reviewed pull requests: [#78](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/78),
    [#79](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/79),
    [#138](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/138),
    [#139](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/139),
    [#141](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/141),
    [#142](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/142),
    [#151](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/151),
    [#152](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/152),
    [#168](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/168),
    [#173](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/173),
    [#174](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/174),
    [#177](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/177)
