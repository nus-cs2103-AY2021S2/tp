---
layout: page
title: Nguyen Son Linh's Project Portfolio Page
---

## Project: FlashBack

FlashBack is a desktop flashcard application written in around 10kLoC of Java. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 

Given below are my contributions to the project.
* **Contributed Code**: [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=linhns&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false) 
* **Enhanced Add Feature**
    * *What it does previously*: Allows the user to add a person in AddressBook.
    * *What it does now*: Allows the user to add a flashcard into FlashBack.
    * *Justification*: Clearly, this facilitates the whole application.
    * *Highlights*: This enhancement requires having a new standard prefix set, 
      thus setting the tone for other command modifications. Implementation was challenging
      as refactoring work to test cases was arduous.
      
* **Sort Feature**: Added the ability to sort flashcards by either question or priority
    * *What it does*: Allows the user to sort flashcards according to a specified order (e.g. ascending question,
      descending priority), FlashBack displays the sorted flashcards list.
    * *Justification*: Alphabetical sorting of questions allow user to trace questions naturally,
    while sort-by-priority helps the user to decide which flashcard needs more attention.
    * *Highlights*: Implementation was made difficult as the sorting options need to be defined in an enum,
    together with some parsing methods.
      
* **Refactoring**: 
  * Refactored the AB-3 codebase with new classes and conditions to suit the purpose of FlashBack. More specifically,
    the model component was overhauled.[#51](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/51)
    
* **Project Management**:
    * Taken main responsibility for creating, managing team repo, merging pull requests and setting issues, milestones 
      as well as settling conflicts.
      
* **Documentation**
    * User Guide:
        * Added anchor links in the table of contents.[#61](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/61)
        * Added the documentation for the features `add`, and `sort`.[#59](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/59), [#92](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/92)
    * Developer Guide:
        * Added implementation details including class diagram and sequence diagram for Sort feature [#92](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/92)
    


