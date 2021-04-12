---
layout: page
title: Pavitra's Project Portfolio Page
---

## Project: CakeCollate

CakeCollate is a desktop application used for tracking cake orders. The user interacts with it using a CLI, 
and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark orders as undelivered/delivered/cancelled.
  * What it does: Allows the user to mark multiple orders as undelivered, delivered or cancelled in one go.
  * Justification: The user can modify many orders in one go without having to re-type the command.

* **New Feature**: Added a command that allows the user to navigate to previous commands using up/down keys.
  * What it does: This allows the user to navigate through previously entered commands. 
  * Justification: This feature improves the ease of using the product significantly because a user can easily 
    re-enter or edit a previously entered command without having to retype the entire line.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=pavz02&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=pavz02&tabRepo=AY2021S2-CS2103T-T11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Updated the GUI to include two models (orders and order items)
  * Updated the help command and the GUI to dynamically change the order and order item panels to the help panel and back.
 
* **Documentation**:
  * User Guide:
    * Added documentation for the features `help`, `delivered`, `undelivered`, `cancelled`, and `keyboard shortcuts`
    * Modified the glossary, FAQ, and the prefix summary table
  * Developer Guide:
    * Added implementation details of the `delete` feature.
    * Updated the UI class diagram
    * Modified the use cases, manual testing, and effort sections
  



