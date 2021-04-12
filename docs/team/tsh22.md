---
layout: page
title: Toh Sihui's Project Portfolio Page
---

## Project: Teaching Assistant

Teaching Assistant is a desktop application used for managing contacts and entries for JC/Secondary school teachers.
The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created
with JavaFX. It is written in Java, and has about 10 (tbc) kLoC.

Given below are my contributions to the project.
* **New Feature**: Added a free command.
  * What it does: Allows the user to check if a certain datetime interval is free or occupied.
  * Justification: This feature improves the product for our target users. Teachers can have many
    items on their entry list. This feature allows them to conveniently find out if they are available for a
    consultation with a student, for instance, rather than having to check each entry individually.
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=tsh22&tabRepo=AY2021S2-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Team-based tasks**:
  1. Set up branch protection rules.
  1. Updated User Guide for PE Dry-Run (#114).
  1. Necessary general code enhancements (#228):
      1. Renamed AddressBook instances to TeachingAssistant.
      1. Removed Person class.
* **Enhancements to existing features**:
  1. Updated the GUI (#59, #92, #182 #189)
  1. Link GUI to show contacts instead of persons (#228)
  1. Wrote additional tests for existing features to increase coverage. (#204, #205, #216, #247)
* **Documentation**:
  * User Guide:
    * Added "Structure of User Guide" section
  * Developer Guide:
    * Added implementation details of the `free` feature.

