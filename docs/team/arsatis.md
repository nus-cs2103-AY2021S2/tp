---
layout: page
title: Zheng Yong's Project Portfolio Page
---

## Project: SmartLib

SmartLib is a desktop app for managing private book loaning services owning less than 10,000 books,
optimized for use via a Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=arsatis&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added a `findreader` command that allows the user to find all the readers in SmartLib with a
    specified name.
  * What it does: Allows the user to find readers via their names.
  * Justification: This feature is very important for the product, as users are very likely to have the need to search
    for readers with a particular name, as manually scrolling through the reader list would be very time consuming.
  * Credits: _AB3's prior implementation of `find` guided the implementation of the `findreader` command._

* **New Feature**: Added the ability to find readers via their tags.
  * What it does: Allows the user to find readers via their tags as well, instead of only via their readers' names.
  * Justification: This feature improves the product significantly because a user can quickly obtain a list of all VIPs
    for their library/bookstore, or find the top borrower for the month.
  * Highlights: This enhancement is very useful when used in conjunction with other commands.
    For example, the `deletereader` command can be used to delete a certain VIP from the list,
    if a reader with the `VIP` tag has not visited the user's bookstore for a long period of time.

* **New Feature**: Added a `listreader` command that allows the user to list all the readers in SmartLib.
  * What it does: Allows the user to list all the readers in SmartLib.
  * Justification: This feature is important for enabling the user to retrieve the original reader list,
    after performing the `findreader` command.
  * Highlights: This enhancement is useful when used after `findreader`, so that the user can retrieve the original
    list of all readers in SmartLib.
  * Credits: _AB3's prior implementation of `list` guided the implementation of the `listreader` command._

* **Enhancements to existing features**:
  * Introduced book barcode to enable storage of books with same names (pull requests
    [\#152](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/152),
    [\#160](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/160),
    [\#195](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/195))
  * Improved the formatting of time and date in GUI to enhance user-friendliness of SmartLib (pull request
    [\#174](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/174))
  * Added Javadoc to existing features to enhance readability and understandability of each method (pull requests
    [\#161](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/161),
    [\#191](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/191))
  * Wrote additional tests for existing features to increase coverage (pull requests
    [\#184](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/184),
    [\#185](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/185),
    [\#186](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/186),
    [\#192](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/192),
    [\#196](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/196),
    [\#197](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/197),
    [\#279](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/279),
    [\#280](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/280),
    [\#281](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/281),
    [\#282](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/282),
    [\#292](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/292),
    [\#294](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/294),
    [\#295](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/295),
    [\#296](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/296),
    [\#297](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/297),
    [\#300](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/300),
    [\#301](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/301),
    [\#302](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/302),
    [\#303](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/303),
    [\#305](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/305),
    [\#306](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/306),
    [\#307](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/307),
    [\#308](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/308),
    [\#309](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/309),
    [\#315](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/315),
    [\#316](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/316))
  * Fixed miscellaneous bugs in existing features (pull requests
    [\#171](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/171),
    [\#183](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/183),
    [\#195](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/195),
    [\#270](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/270))

* **Contributions to the User Guide**:
  * Added an introduction to enhance reader-friendliness of the UG (pull requests
    [\#65](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/65),
    [\#159](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/159),
    [\#169](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/169))
  * Added documentation for the features `listreader` and `findreader` (pull requests
    [\#65](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/65),
    [\#178](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/178))
  * Rearranged the ordering of the features to enhance reader-friendliness of the UG (pull request
    [\#169](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/169))
  * Added table of command parameters for each section (pull requests
    [\#178](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/178),
    [\#194](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/194),
    [\#273](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/273))
  * Added glossary to clarify technical terminology (pull requests
    [\#178](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/178),
    [\#273](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/273))
  * Carried out an overall check and reformatting of UG (pull request
    [\#325](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/325))

* **Contributions to the Developer Guide**:
  * Added target user profile, value proposition, and user stories (pull request
    [\#62](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/62))
  * Added use cases (pull requests
    [\#66](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/66),
    [\#82](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/82))
  * Replaced all instances of AddressBook with SmartLib (pull request
    [\#138](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/138))
  * Added implementation details of the `findreader` and `listreader` features (pull requests
    [\#139](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/139),
    [\#143](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/143))
  * Added an introduction to enhance reader-friendliness of the DG  (pull request
    [\#157](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/157))
  * Fixed bugs with the DG (pull requests
    [\#157](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/157),
    [\#159](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/159),
    [\#199](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/199))

* **Project management**:
  * Refactored all references to "AddressBook" in the code to "SmartLib"
  * Updated general UG and DG documentation, e.g. target user profile, architecture and storage diagrams, etc.
  * Maintained the issue tracker (setting up of issues for various tasks)
  * Managed releases `v1.1` - `v1.4` (5 milestones) on GitHub
  * Managed project meeting agendas
  * Kept track of task schedules and deadlines

* **Community**:
  * PRs reviewed:
    [\#154](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/154),
    [\#158](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/158),
    [\#293](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/293)
  * Contributed to forum discussions (examples:
    [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/194),
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/139),
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/110#issuecomment-774674147),
    [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/300))
  * Reported bugs and suggestions for other teams in the class (example: [PE-D](https://github.com/arsatis/ped/issues))
