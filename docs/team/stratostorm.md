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

* **New Feature**: Introduced ChildTags and integrated them into Appointments
  * What it does: allows the user to add on child tags which are visibly different from regular tags to allow them to
    easily differentiate contacts based on which of their children they are related to.
  * Justification: This greatly improves user experience for those with multiple children to allow them an easy way
  to organize their contacts as well as find the one their looking for throw 'find'.
  * Highlights: Involved changing the way tags are sorted in the UI so ChildTags will be at the front,
    as well as adding functionality to the UI to color code ChildTags to a different color.

* **New Feature**: Added the ability to delete all contacts with the same tag.
  * What it does: allows the user to clear all contacts tagged with the same tag in one command.
  * Justification: This feature greatly aids the user in decluttering the contact book, allowing them to delete
    a large amount of related contacts with a single command without having to delete them 1 by 1.
  * Highlights: Some large scale changes were required when the appointment functionality was added to ParentPal, which
  required that deletion related commands checked the appointments for clashes before allowing deletion.
    
* **New Feature**: Implemented the editAppt and listAppt commands as part of the Appointment feature update.
  * What it does: allows the user to edit appointments and list all appointments.
  * Justification: necessary for the ease of use of our appointment feature to allow users to edit their appointments
    without having to delete and re-add them as well as to return to viewing all appointments after performing a search.
  * Credits: largely based upon the original edit and list commands


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=stratostorm)


* **Enhancements to existing features**:
  * Performed large scale refactoring of the existing program in changing references of AB3 to HeliBook initially, 
    and subsequently to ParentPal, and also changing references of Person to Contact. 
    (Pull requests [\#37](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/37), 
    [\#174](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/174))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `tag` and `clear` [\#14](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/14),
      [\#112](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/112)
    * Improved the introduction to be a better description of our application [\#154](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/154)
  * Developer Guide: [\#277](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/277)
    * Added an introduction
    * Fixed several naming issues of existing diagrams 
    * Added implementation details of the `tag` feature along with a class and sequence diagram 
