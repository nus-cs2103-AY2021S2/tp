---
layout: page
title: Zheng Yong's Project Portfolio Page
---

### Project: SmartLib

SmartLib is a desktop app for managing private book loaning services owning less than 10,000 books,
optimized for use via a Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

### Summary of contribution

Given below are my contributions to the project.

* **New Feature**: Added a `findreader` command that allows the user to find all the readers in SmartLib with a
    specified name.
  * What it does: Allows the user to find readers via their names.
  * Justification: This feature is very important for the product, as users are very likely to have the need to search
    for readers with a particular name, as manually scrolling through the reader list would be very time consuming.
  * Highlights: This enhancement is very useful when used in conjunction with other commands.
    For example, if users have keyed in the details of a reader incorrectly, they can use `findreader` to search for
    the incorrect entry, `deletereader` to delete the entry, and then `addreader` to add the reader back in with his/her
    correct details.
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

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=arsatis&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Enhancements to existing features**:
  * Introduced book barcode to enable storage of books with same names (Pull requests [\#152](), [\#160](), [\#195]())
  * Improved the formatting of time and date in GUI to enhance user-friendliness of SmartLib (Pull request [\#174]())
  * Added Javadoc to existing features to enhance readability and understandability of each method (Pull requests
    [\#161](), [\#191]())
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#184](), [\#185](), [\#186](),
    [\#192](), [\#196](), [\#197](), [\#279](), [\#280](), [\#281](), [\#282]())
  * Fixed miscellaneous bugs in existing features (Pull requests [\#171](), [\#183](), [\#195](), [\#270]())

* **Contributions to the User Guide**:
  * Added an introduction to enhance reader-friendliness of the UG (Pull requests [\#65](), [\#159](), [\#169]())
  * Added documentation for the features `listreader` and `findreader` (Pull requests [\#65](), [\#178]())
  * Rearranged the ordering of the features to enhance reader-friendliness of the UG (Pull request [\#169]())
  * Added table of command parameters for each section (Pull requests [\#178](), [\#194](), [\#273]())
  * Added glossary to clarify technical terminology  (Pull requests [\#178](), [\#273]())

* **Contributions to the Developer Guide**:
  * Added target user profile, value proposition, and user stories (Pull request [\#62]())
  * Added use cases (Pull requests [\#66](), [\#82]())
  * Replaced all instances of AddressBook with SmartLib (Pull request [\#138]())
  * Added implementation details of the `findreader` and `listreader` features (Pull requests [\#139](), [\#143]())
  * Added an introduction to enhance reader-friendliness of the DG  (Pull request [\#157]())
  * Fixed bugs with the DG (Pull requests [\#157](), [\#159](), [\#199]())

* **Project management**:
  * Refactored all references to "AddressBook" in the code to "SmartLib"
  * Updated general UG and DG documentation, e.g. target user profile, architecture and storage diagrams, etc.
  * Maintained the issue tracker (setting up of issues for various tasks)
  * Managed releases `v1.1` - `v1.4` (5 milestones) on GitHub
  * Managed project meeting agendas
  * Kept track of task schedules and deadlines

* **Community**:
  * PRs reviewed: [\#154](), [\#158]()
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/194),
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/139),
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/110#issuecomment-774674147))
  * Reported bugs and suggestions for other teams in the class (example: [PE-D](https://github.com/arsatis/ped/issues))
