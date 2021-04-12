---
layout: page
title: Soh Xin Wei's Project Portfolio Page
---

## Project: CakeCollate

CakeCollate is a desktop order book application used for tracking cake orders. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.
CakeCollate is based on the AddressBook-Level3 project (AB3) created by the [SE-EDU initiative](https://se-education.org).

## Summary of Contributions

Given below are my contributions to the project.

### Code contributed:
* [This](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=xiinweii98&tabRepo=AY2021S2-CS2103T-T11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
  is a link to the code that I have contributed.

### Enhancements implemented:
* **Enhancement to existing feature**: Added `DeliveryDate` to the `Order`(refactored from Person). (Pull request [\#56](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/56))
  * What it does: Allow users to add a delivery date to the order that being created.
  * Justification: As our team's goal is to adapt AB3's address book into an order book, adding a delivery date field allow users to better
    manage the tracking of their orders in CakeCollate.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation was challenging as it required changes to existing commands.

<div style="page-break-after: always;"></div>

* **Enhancement to existing feature**: Modified `FindCommand`. (Pull request [\#92](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/92))
  * What was changed: 
    * Allow users a less restrictive search.<br>
    I.e. The find function no longer require matching of full keywords.
    * Allow users to specify prefixes for their search of orders.
  * Justification: To allow for efficient tracking of orders, our team wanted to allow users to execute both generic OR search and specific AND search for the fields of an orders.
  * Highlights: The implementation was challenging as it required changes to existing find command. As it required changes to the original `NameContainsKeywordsPredicate`, it required an in-depth analysis of design alternatives as described in the [Developer Guide](https://ay2021s2-cs2103t-t11-4.github.io/tp/DeveloperGuide.html#design-considerations).

### Contributions to User Guide:
* Edited documentation for the find feature.
* Added the Types of user input.
* Vetted the whole document to fix inconsistent usage of terms, ordering of sections, brief descriptions etc. (Pull request [\#242](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/242))

### Contributions to Developer Guide:
* Added non-functional requirements.
* Added implementation details for the find feature.
* Added instructions for manual testing for the find feature.

### Contributions to team-based tasks:
* Refactored terms to adapt the address book into an order book. (Pull request [\#75](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/75))
* Maintained milestone tracker.
