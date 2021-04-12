---
layout: page
title: Xing Peng's Project Portfolio Page
---

## Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=mechastriker3)
* **New Feature**: Added the ability to add incomplete contacts.
  * What it does: allows the user to add contacts without specifying all the fields 
  * Justification: This feature improves the product as it allowed users to add contacts that 
    they might not have all the information for.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. 
    It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Pull requests: [#62](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/62)

* **New Feature**: Added ability to find contacts by specific fields.
  * What it does: allows the user to find contacts by specifying a specific field to search in by 
    adding `o/OPTION` to the command.
  * Justification: This feature improves the product significantly because a user might know exactly what 
    they wish to find and need a quick way to find it. By allowing them to search in the specified field, 
    they can find contacts faster.
  * Highlights: The parser need to be edited to handle all the different options.This increased the number of branches in the code
    significantly, hence the implementation was challenging. The number of exceptions to be handled also increased, adding to the complexity.
    In addition, OOP was used as I extended the search predicates from a common parent class to prevent code duplication.
  * Pull requests: [#131](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/131), [#171](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/173)

* **New Feature**: Added ability to find contacts by any field.
  * What it does: allows the user to find contacts by searching through all the fields of the contact.
  * Justification: This feature allows the user to quickly search through all the fields of the contact if they do not know which exact 
    field they want to search for. This will return more results rather than just searching by name.
  * Highlights: The implementation of varies predicates into a single predicate was challenging and required use of functional programming paradigms.
  * Pull requests:

* **New Feature**: Added ability to find appointments by specific fields.
  * What it does: allows the user to find appointments by specifying a specific field to search in by
    adding `o/OPTION` to the command.
  * Justification: This feature improves the product significantly because a user might know exactly what
    they wish to find and need a quick way to find it. By allowing them to search in the specified field,
    they can find appointments faster.
  * Highlights: The parser need to be edited to handle all the different options.This increased the number of branches in the code
    significantly, hence the implementation was challenging. The number of exceptions to be handled also increased, adding to the complexity.
    In addition, OOP was used as I extended the search predicates from a common parent class to prevent code duplication.
  * Pull requests: [#141](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/141), [#176](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/176), 
  

* **New Feature**: Added ability to find appointments by any field.
  * What it does: allows the user to find appointments by searching through all the fields of the appointment.
  * Justification: This feature allows the user to quickly search through all the fields of the appointment if they do not know which exact
    field they want to search for. This will return more results rather than just searching by name previously.
  * Highlights: The implementation of varies predicates into a single predicate was challenging and required use of functional programming paradigms.
  * Pull requests: [#147](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/147)

* **Enhancements to existing features**: Extend usage of `help` command from teammate [Nicole](https://github.com/nicoleang09).
  * What it does: Instead of just copying the link to the user's clipboard, allowed button to open the link in the user's default browser.
  * Justification: it is troublesome to click on `copy URL`, open the browser then paste the link into a browser. Instead, opening the link straight would make things a lot easier for the user.
  * Highlights: Had to make use of external libraries that i had to do a lot of googling to find out about. Hence, the implementation was hard as it required reading a lot of external API documentations.
  * Pull requests: [#151](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/151) 

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `findAppt` and `find` [\#272](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/272)
    * Edited documentation for feature `add` for **adding incomplete contacts** [\#103](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/103)
    * Edited documentation for feature `delete` for **deleting multiple contacts** [\#310](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/310)
  * Developer Guide:
    * Added implementation details of the `add` feature [\#301](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/301)
    * Added use cases for `delete`, `find` in Appendix:Requirements [\#291](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/291)

* **Community**:
  * Contributed to forum discussions (examples: [#17](https://github.com/nus-cs2103-AY2021S2/forum/issues/17#issuecomment-767455612), [21](https://github.com/nus-cs2103-AY2021S2/forum/issues/21), [#281](https://github.com/nus-cs2103-AY2021S2/forum/issues/281#issuecomment-811904159), [#95](https://github.com/nus-cs2103-AY2021S2/forum/issues/95#issuecomment-775764330), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/177), [2](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/173), [3](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/167), [4](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/165), [5](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/163))

* **Team-Based**:
  * Set up Git Repo and organization at the start of project.
  * Set up GitHub pages and added initial PPP for teammates.
  * Documented product aim in README.md 

* _{you can add/remove categories in the list above}_
