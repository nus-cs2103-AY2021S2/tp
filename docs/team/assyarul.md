---
layout: page
title: Muhd Assyarul Ariffin's Project Portfolio Page
---

## Project: FriendDex

FriendDex is a relationship management tool that empowers users to maintain long-lasting relationships with their friends, without compromising other aspects of their lives.
It is a Java GUI application created with JavaFX and users mainly interact with it by typing commands.

Given below are my contributions to the project.

* **New Feature**: Added the Birthday Field into Person.
  * Justification: This feature allows the user to put in their friends' birthdays, so they do not
  have to remember.
  
* **New Feature**: Added Groups into FriendDex along with the UI associated with FriendDex.
  * Justification: This feature allows the user to organise their friends into specified groups
  * Highlights: It was challenging trying to tie together the Group and Person class together due to the many 
    moving parts in Person. Additionally, creating the UI for the Group List Panel which shows all the groups in 
    FriendDex was challenging when it comes to selecting the list cell corresponding to a group whenever a command
    is involved with that group.

* **New Feature**: Added Debt Feature into FriendDex
  * Justification: This feature allows the user to track an individual friend's debt that they owe to the user or 
    that the user owes them.
  * Highlights: Debt value was initially stored as a Float which was an error when the precision gets high enough. 
    Changing it into Java's BigDecimal fixes the issues like loss of significance.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=assyarul)

* **Documentation**:
  * User Guide:
    * Added the Friends section which describes the various information about a Person FriendDex can store.
    * Added documentation for `add-group`, `del-group`, `add-debt` and `subtract-debt`.
    * Updated documentation for `list`.
  * Developer Guide:
    * Updated the sequence diagram that exists in the Logic component section.
    * Updated the class diagram that exists in the Model component section.
    * Added implementation details for `add-debt`, `subtract-debt`, `del-group` and `add-group` along with 
      the respective sequence diagrams.
