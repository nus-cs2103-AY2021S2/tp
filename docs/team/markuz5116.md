---
layout: page
title: Marcus Ong's Project Portfolio Page
---

## Project: RemindMe

---

## Overview

RemindMe is a desktop application for keeping track of user events and deadlines, optimised for use via Command Line 
Interface(CLI) while still having the benefits of a Graphic User Interface(GUI).
RemindMe allows students to always be aware of exams and events deadline as there will be reminder pop-ups and calendar
for students to see! 
It is written in Java, and has about 17 kLOC contributed.

## Summary of Contributions

* **New Feature**: Added the ability to find modules and general events.
    * What it does: allows the user to search for specific modules and general events in RemindMe.
    * Justification: This feature improves RemindMe significantly because a user can make numerous entries into RemindMe, 
      and the app provides a convenient way to search for the specific entries the user wants.
    * Highlights: This enhancement affects existing find command, which only searched for persons and commands to be added
      in the future. It required the need to add in a parser for find commands to differentiate between finding persons,
      modules and events. Therefore, this implementation required adding of new classes and a change in the logic of RemindMe.
    * Credits: *{AddressBook3's Parser and AddressBook3's FindPersonCommand}*
    

* **New Feature**: Added the ability to clear modules, persons and general events respectively.
    * What it does: allows the user to clear specifically modules, general events or persons without changing the other entities in RemindMe.
    * Justification: With the addition of modules and general events, there will be instances where users want to clear the  
      entirety of one of the modules, general events or persons without changing the rest. 
    * Highlights: This enhancement affects the existing clear command, which will clear all the persons in RemindMe. Therefore, 
      I enhanced it to clear modules and general events too. Next, I added respective commands to clear each of the entities in RemindMe.
      Therefore, I added ways to parse the respective inputs from the user to differentiate when to clear the whole of
      RemindMe, modules, persons, or general events.
    * Credits: *{AddressBook3's Parser and AddressBook3's ClearCommand}*
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w15-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&since=2021-02-19&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=markuz5116&tabRepo=AY2021S2-CS2103T-W15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub
    

* **Enhancements to existing features**:
    * Wrote additional tests for new features (Pull requests [\#118](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/118),
      [\#129](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/129), [\#131](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/131),
      [\#132](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/132), [\#201](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/201),
      [\#215](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/215), [\#228](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/228),
      [\#247](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/247), [\#317](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/317))
    * Wrote specific error messages for each commands (Pull requests [\#312](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/312),
      [\#316](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/316), [\#325](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/325))
      

* **Documentation**:
    * User Guide:
        * Added documentations for the features `clear` and `find` (Pull request [\#169](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/169))
        * Added relevant images for sample inputs (Pull request [\#240](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/240))
    * Developer Guide:
        * Add implementation details of the `find` feature. (Pull requests [\#184](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/184),
          [\#340](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/340))
