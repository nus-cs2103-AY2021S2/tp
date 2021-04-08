---
layout: page
title: Colin's Project Portfolio Page
---

## Project: SunRez

SunRez is a resident management system for use in hostels and residential colleges (RCs). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLoC.
Given below are my contributions to the project.

* **New Feature**: Added the ability to manage residents (CRUD Residents)
  * What it does: Allows the user to manage the residents of a hostel/RC.
  * Justification: This a core feature that details the information about all incoming and current residents that need to be managed and contacted.     
  * Highlights: This is the base feature to support commands to be added in the future. 
    Rigorous testing and effort went into ensuring that the basic commands were stable. 
    Integration had to be done with the existing infrastructure. Collaboration had to be done with overlapping models
    such as the room model. 
    
* **New Feature**: Added the ability to allocate residents to rooms.  
  * What it does: Allows the user to allocate/deallocate a resident to/from a room.
  * Justification: In a realistic hostel setting, residents are allocated to rooms. 
  * Highlights: There were multiple implementation approaches that Azeem and I had to design, each having their merits.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=colintkn&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=colintkn&tabRepo=AY2021S2-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide:
    * Added documentation for resident features.  [\#8](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/8)
    * Added documentation for allocation features. [\#133](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/133)
  * Developer Guide:
    * Added implementation details of the resident feature. [\#288](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/288)
    * Added implementation details of the allocation feature. [\#103](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/103)

* **Community**:
  * Contributed to forum discussions (examples: [158](https://github.com/nus-cs2103-AY2021S2/forum/issues/158))
  * Reported bugs and suggestions for other teams in the class: [PED-D](https://github.com/colintkn/ped/issues)
