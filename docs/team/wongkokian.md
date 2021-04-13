---
layout: page
title: Wong Kok Ian's Project Portfolio Page
---

## Project: ClientBook

ClientBook is a desktop application for managing your clients’ contact details. While it has a GUI created by JavaFX, most of the user interactions happen using a CLI (Command Line Interface). It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to use and store custom shortcuts.
    * What it does: Allows the user to use shortcuts as defined by them. These custom shortcuts can be added, edited, deleted, cleared or listed.
    * Justification: This feature improves the product significantly because a user can make existing ClientBook commands more personal by assigning more meaningful names to them. This in turn can improve the user's speed/efficiency.
    * Highlights: This enhancement requires an in-depth analysis of design alternatives. The implementation was challenging as it required changes to the existing model.
    * Credits: Structure of shortcut feature was adapted from the AB3 address book and command structures.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=wongkokian&tabRepo=AY2021S2-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Modified `find` to allow the user to find contacts by fields other than name (Pull request [\#44](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/44))
    * Removed `clear` after the addition of `batch` commands (Pull request [\#104](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/104))

* **Documentation**:
    * User Guide:
        * Designed the first prototype mockup of ClientBook: [\#29](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/29)
        * Added annotations to screenshots of ClientBook: [\#90](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/90)
        * Added documentation for `find` and `shortcut` commands: [\#44](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/44), [\#95](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/95)
        * Removed documentation of `clear`: [\#104](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/104)
    * Developer Guide:
        * Modified UML diagrams to include classes associated with the `shortcut` feature: [\#185](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/185)
        * Added implementation details of the `shortcut` feature including sequence diagram: [\#185](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/185)
        * Added use cases for the `shortcut` feature: [\#185](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/185)
        * Converted Developer Guide into a neater PDF format

* **Community**:
    * PRs reviewed (with non-trivial review comments): 
      [\#21](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/21), 
      [\#45](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/45), 
      [\#55](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/55), 
      [\#61](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/61), 
      [\#85](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/85), 
      [\#95](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/95), 
      [\#99](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/99),
      [\#170](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/170),
      [\#173](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/173),
      [\#179](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/179),
      [\#185](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/185),
      [\#187](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/187),
      [\#197](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/197),
      [\#199](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/199),
      [\#213](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/213)
    * Reported bugs and suggestions for other teams in the class (example: [1](https://github.com/wongkokian/ped/issues))
    
