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
  
* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub
    
* **Enhancements to existing features**:
    * Wrote additional tests for new features (Pull requests [\#118](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/118),
      [\#129](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/129), [\#131](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/131),
      [\#132](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/132), [\#201](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/201),
      [\#215](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/215), [\#228](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/228),
      [\#247](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/247), [\#317](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/317)) 
    * Added respective clear command for modules, persons and events. This allows the users to clear only one component 
      of RemindMe, rather than the whole of RemindMe. (Pull requests [\#145](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/145),
      [\#152](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/152))
      
* **Documentation**:
    * User Guide:
        * Added documentations for the features `clear` and `find` [\#169](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/169) 
        * Added relevant images for sample inputs [\#240](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/240)
    * Developer Guide:
        * Add implementation details of the `find` feature.    
    