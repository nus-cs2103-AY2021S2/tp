---
layout: page
title: Edeline's Project Portfolio Page
---

## Project: ParentPal

ParentPal is a desktop app for managing your children’s contacts and their related appointments. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort contacts.
  * What it does: allows the user to sort the contact list in alphabetical or chronological order.
  * Justification: This feature helps users keep their contact list organised in whichever way they want, 
    and is a foundational function that users expect to have when they store a lot of information.
  * Highlights: 

* **New Feature**: Added the ability to favourite contacts and list favourited contacts.
  * What it does: allows the user to favourite or unfavourite contacts in the contact list.
  * Justification: This feature makes the process of finding frequently contacted people much easier.
  * Highlights: This enhancement affected the list command as it can now accept arguments in terms of options of what to list.
    It required analysis of design alternatives as there were multiple ways to implement it.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=edelinetenges&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
