---
layout: page
title: Edeline's Project Portfolio Page
---

## Project: ParentPal

ParentPal is a desktop app for managing your children’s contacts and their related appointments. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort contacts.
  * What it does: allows the user to sort the contact list in alphabetical or chronological order.
  * Justification: This feature helps users keep their contact list organised in whichever way they want, 
    and is a foundational function that users expect to have when they store a lot of information.
  * Highlights: 

* **New Feature**: Added the ability to favourite contacts and list favourited contacts.
  * What it does: allows the user to favourite or unfavourite contacts in the contact list.
  * Justification: This feature makes the process of finding frequently contacted people much easier.
  * Highlights: This enhancement affected many other commands, as favourite was a new attribute under a contact.
    In addition, the list command can now accept arguments in terms of options of what to list.
    It required analysis of design alternatives as there were multiple ways to implement it.

* **New Feature**: Added the ability to delete appointments.
  * What it does: allows the user to delete appointments by index.
  * Justification: This feature is a core functionality.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=edelinetenges&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Prevent deleting of contacts that are involved in appointments (Pull requests [\#149](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/149))
  * Allow appointments of same name to be added, given that they have a different date/time or address (Pull requests [\#257](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/257))
  * Allow users to edit contacts to remove their previous entries to optional fields such as the phone, email and address (Pull requests [\#263](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/263))
  * Wrote tests for features (`FavouriteCommand`, `FavouriteCommandParser`, `SortCommand`, `SortCommandParser`, `ListCommand`, `ListCommandParser`, `DeleteAppointmentCommand`, `DeleteAppointmentCommandParser`, `ListAppointmentCommand`) to increase coverage 
    (Pull requests [\#284](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/284), [\#288](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/288))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort` and `favourite` (Pull requests [\#89](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/89), [\#107](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/107))
    * Did cosmetic tweaks to existing documentation of features `list`, `delete`: (Pull requests [\#107](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/107),
      [\#172](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/172))
  * Developer Guide:
    * Updated target user, value proposition and user stories. (Pull request [\#22](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/22))
    * Added use cases. (Pull request [\#22](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/22))
    * Added implementation details of the `sort` feature. (Pull request [\#97](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/97))
    * Added implementation details of the `favourite` feature. (Pull request [\#283](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/283))
    * Added test cases for `sort` and `favourite` feature in Appendix. (Pull request [\#](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\##139](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/139), [\#141](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/141), [\#147](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/147), [\#42]()
