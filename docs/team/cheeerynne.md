---
layout: page
title: Chee Erynne's Project Portfolio Page
---

## Project: HEY MATEz

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within 
a club efficiently and easily! It is a Command Line Interface (CLI) application which handles user requests 
that are typed into the input box as commands and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to assign members to a task. (Pull Requests: [\#161](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/161), [\#163](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/163))
    * What it does: Allows the user to assign multiple members to a task and edit assignees to a task. 
    * Justification: This feature improves the product significantly because a user can conveniently track which members are assigned to a task.
      Also, users can easily modify the assignees to a task should there be any changes in the assignment of a task. 
    * Highlights: The implementation was challenging as I had to check whether the specified member's name exists in the 
      members' list before allowing assignment to a task. I decided to implement an Assignee class, along with a checkAssignees 
      method within the ModelManager class. The checkAssignees method checks the validity of the specified member's name by 
      calling the hasPerson method within the ModelManager class. With the checkAssignees method, I was able to check 
      the validity of the specified names while not creating any unnecessary dependency between the Task and Person class.

* **New Feature**: Added the ability to find tasks with specified keywords. (Pull Requests: [\#133](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/133))
    * What it does: Allows the user to find all tasks which contain any of the specified keywords in its title or description. 
    * Justification: This feature improves the product significantly because a user can easily filter and find tasks based on specific 
      keywords. With this easy filtering, there is no need for users to scroll through the entire list just to find a specific task. 
    * Highlights: The implementation was challenging as I had to check through the title and description fields of all the tasks 
      in the task list. I decided to implement a predicate, TaskContainsKeywordPredicate, which compares the title and description of
      a task to the list of keywords specified. The task list is then updated accordingly to the predicate. 
      Furthermore, integration and unit testing had to be implemented thoroughly to ensure that the various scenarios of usage were accounted for.
    
* **New Feature**: Added the ability to find members with specified keywords. (Pull Requests: [\#130](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/130))
    * What it does: Allows the user to find all members whose name, phone number, email or role contain any of the specified keywords.
    * Justification: This feature improves the product significantly because a user can easily filter and find members based on specific 
      keywords. With this easy filtering, there is no need for users to scroll through the entire list just to find a specific member.
    * Highlights: The implementation was challenging as I had to check through all the fields of the members in the list. 
      I decided to implement a predicate, DetailsContainsKeywordsPredicate, which compares all the fields of a member to the list of keywords specified.
      The members list is then updated accordingly to the predicate. Furthermore, integration and unit testing had to be implemented 
      thoroughly to ensure that the various scenarios of usage were accounted for.
      
* **New Feature**: Added the ability to remove all members assigned to a task (Pull Requests: [\#163](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/163), [\#174](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/174))
    * What it does: allows the user to remove all members assigned to a task with a single command.
    * Justification: This feature improves the product significantly because a user can easily remove all members assigned to a task
      with a single command instead of removing one member at a time.
    * Highlights: The implementation was challenging as after removing all the assignees to a task, I had to update the other 
      tasks' details accordingly. Furthermore, integration and unit testing had to be implemented thoroughly to ensure that 
      the various scenarios of usage were accounted for.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=cheeerynne&tabRepo=AY2021S2-CS2103T-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  
* **Project management**:
    * Created issues
    * Managed milestones and issues
    
* **Enhancements to existing features**:
    * Updated the deleteMember and editMember commands to delete/edit by name instead of index. (Pull Requests: [\#70](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/70), [\#85](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/85))
    * Added JavaDoc comments to existing classes for better documentation. (Pull Requests: [\#187](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/187))

* **Documentation**:
    * User Guide:
        * Added documentation for the commands `findTasks`, `findMembers` and `clearAssignees`. (Pull Requests: [\#137](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/137), [\#166](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/166))
        * Categorized and organized the User Guide. (Pull Requests: [\#166](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/166))
    
    * Developer Guide:
        * Updated the Storage UML Diagram in the Developer Guide. (Pull Requests: [\#191](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/191))
        * Added implementation explanation for the `clearAssignees` command. (Pull Requests: [\#191](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/191))
        * Added and updated use cases in the Developer Guide. (Pull Requests: [\#143](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/143), [\#171](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/171))
       
* **Community**:
    * PRs reviewed (Pull requests: [\#246](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/246), [\#276](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/276))
    * Reported bugs and suggestions for other teams in the class (Examples: [1](https://github.com/cheeerynne/ped/issues/11), [2](https://github.com/cheeerynne/ped/issues/12), [3](https://github.com/cheeerynne/ped/issues/9))
    

