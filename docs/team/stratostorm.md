---
layout: page
title: Huang Weicong's Project Portfolio Page
---

## Project: ParentPal

ParentPal is a desktop address book application designed for parents to manage their children's contacts.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to append tags to existing contacts.
  * What it does: allows the user to add on tags to already created contacts without having to replace all of them 
    via edit. 
  * Justification: This feature makes it much faster for the user to add on tags to pre-established contacts,
    previously a user would have to retype all existing tags into

* **New Feature**: Added the ability to delete all contacts with the same tag.
  * What it does: allows the user to clear all contacts tagged with the same tag in one command.
  * Justification: This feature greatly aids the user in decluttering the contact book, allowing them to delete
    a large amount of related contacts with a single command without having to delete them 1 by 1.
  * Highlights: Many changes  were needed to made when the appointment functionality was added to ParentPal, which
  required that deletion related commands checked the appointments for clashes before allowing deletion.
    
* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=stratostorm)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Performed large scale refactoring of the existing program in changing references of AB3 to HeliBook initially, 
    and subsequently to ParentPal, and also changing references of Person to Contact. 
    (Pull requests [\#37](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/37), 
    [\#174](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/174))
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `tag` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
