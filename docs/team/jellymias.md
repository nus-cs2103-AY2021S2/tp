---
layout: page
title: Jeremias Shae's Project Portfolio Page
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
* **New Feature**: Added modules, assignments, exams and general events.
    * What it does: allows the user to manage their tasks in terms of modules, assignments, exams and general events.
    * Justification: This feature improves RemindMe significantly as it allows the user to create, delete, edit and find entries
      that are better categorised to accentuate the nature of their tasks and events.
    * Highlights: This enhancement affects all existing commands, as well as the storage structure of RemindMe. It required the
      need to add the various classes, with methods that integrate them to commands, parsers and storage.
    * Credits: *{AddressBook3's Person and AddressBook3's AddressBook}*
    
* **New Feature**: Added the ability to edit modules, assignments, exams and general events.
    * What it does: allows the user to edit details of modules, assignments, exams and general events in RemindMe.
    * Justification: This feature improves RemindMe significantly as it allows the user to make changes to their entries in RemindMe
      without having to delete and recreate entries. This provides a convenient way to rectify mistakes made.
    * Highlights: This enhancement affects the existing edit command, which could only edit persons. It required the need to add in a parser for edit commands
      to differentiate between editing persons, modules, assignments, exams and general events. Therefore, this implementation required adding of new classes and a change in the logic of RemindMe.
    * Credits: *{AddressBook3's Parser and AddressBook3's EditPersonCommand}*

* **New Feature**: Added the ability to add modules, assignments, exams and general events.
    * What it does: allows the user to add modules, assignments, exams and general events in RemindMe.
    * Justification: This feature improves RemindMe significantly as it allows the user to add entries of different natures to RemindMe
      with proper categorisation. This provides a convenient way to view the different types of tasks that the user has.
    * Highlights: This enhancement affects the existing add command, which could only add persons. It required the need to add in a parser for add commands
      to differentiate between adding persons, modules, assignments, exams and general events. Therefore, this implementation required adding of new classes and a change in the logic of RemindMe.
    * Credits: *{AddressBook3's Parser and AddressBook3's AddPersonCommand}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w15-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&since=2021-02-19&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=jellymias&tabRepo=AY2021S2-CS2103T-W15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Wrote additional tests for new features (Pull requests [\#217](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/217),
      [\#220](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/220), [\#221](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/221),
      [\#231](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/231), [\#313](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/313),
      [\#314](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/314), [\#319](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/319),
      [\#320](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/320), [\#323](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/323))

* **Documentation**:
    * User Guide:
        * Added documentations for the features `add`, `delete` and `edit` [\#32](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/32), [\#179](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/179)
        * Updated introduction [\#321](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/321)
        * Added warnings to all relevant features [\#333](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/333)
        * Final polishing of User Guide [\#333](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/333), [\#334](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/334)
    * Developer Guide:
        * Add implementation details of the `add` feature.
    
