---
layout: page
title: Chee Erynne's Project Portfolio Page
---

## Project: HEY MATEz

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within 
the club efficiently and easily! It is a Command Line Interface (CLI) application which handles user requests 
that are typed into the input box as commands and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to assign members to a task (Pull Requests: [\#161](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/161))
    * What it does: allows the user to assign multiple members, with valid names, to a task. 
    * Justification: This feature improves the product significantly because a user can conveniently track which members  
      are assigned to a task. 
    * Highlights: The implementation was challenging as I had to check whether the specified member's name exists in the 
      members' list before allowing assignment to a task. I decided to implement an Assignee class, along with a checkAssignees 
      method within the ModelManager class. The checkAssignees method checks the validity of all the specified member's name by 
      calling the hasPerson method within the Model class while not creating any unnecessary dependency between the Task and Person class.
      Furthermore, integration and unit testing had to be implemented thoroughly to ensure that the various scenarios of usage were accounted for.

**New Feature**: Added the ability to find tasks with specified keywords. (Pull Requests: [\#133](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/133))
    * What it does: allows the user to find all tasks which contains any of the specified keywords in its title or description. 
    * Justification: This feature improves the product significantly because a user can easily filter and find tasks based on specific 
      keywords. With this easy filtering, there is no need for users to scroll through the entire list just to find a specific task. 
    * Highlights: The implementation was challenging as I had to check only the title and description fields of a task. 
      I decided to implement a predicate, which takes in the title and description of a task, to check if any of these 2 fields
      matches any of the keywords given. Furthermore, integration and unit testing had to be implemented thoroughly to ensure 
      that the various scenarios of usage were accounted for.

**New Feature**: Added the ability to find members with specified keywords. (Pull Requests: [\#130](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/130))
    * What it does: allows the user to find all members whose name, phone number, email or role any of the specified keywords.
    * Justification: This feature improves the product significantly because a user can easily filter and find members based on specific 
      keywords. With this easy filtering, there is no need for users to scroll through the entire list just to find a specific member.
    * Highlights: The implementation was challenging as I had to check through all the fields of a member. I decided to implement a 
      predicate, which takes in all the fields of a member, to check if any of the fields matches any of the keywords given. 
      Furthermore, unit testing had to be implemented thoroughly to ensure that the various scenarios of usage were accounted for. 

**New Feature**: Added the ability to remove all members assigned to a task (Pull Requests: [\#163](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/163), [\#174](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/174))
    * What it does: allows the user to remove all members assigned to a task with a single command.
    * Justification: This feature improves the product significantly because a user can easily remove all members assigned to a task
      with a single command instead of removing one member at a time.  
    * Highlights: The implementation was challenging as I had to update the other task's details accordingly after removing all assignees 
      to the task. Furthermore, unit testing had to be implemented thoroughly to ensure that the various scenarios of usage were accounted for.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=cheeerynne&tabRepo=AY2021S2-CS2103T-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  
* **Project management**:
    * Managed milestones and issues
    * Created issues 
    
* **Enhancements to existing features**:
    * Updated the deleteMember and editMember commands to delete/edit by name instead of index. (Pull Requests: [\#70](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/70), [#\85](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/85))
    * Added JavaDoc comments to existing classes for better documentation. (Pull Requests: [\#187](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/187))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `findTasks`, `findMembers` and `clearAssignees`. (Pull Requests: [\#137](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/137), [\#166](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/166))
        * Categorized and organized the features into sub-features. (Pull Requests: [\#166](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/166))
    
    * Developer Guide:
        * Added implementation details of the `clearAssignees` feature. (Pull Requests: [\#191](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/191))
        * Updated the Storage UML Diagram in the Developer Guide. (Pull Requests: [\#191](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/191)) 
        * Added use cases for various features (Pull Requests: [\#143](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/143))
       
* **Community**:
    * PRs reviewed (Pull requests: [\#246](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/246))
    * Reported bugs and suggestions for other teams in the class (Examples: [1](https://github.com/cheeerynne/ped/issues/11), [2](https://github.com/cheeerynne/ped/issues/12), [3](https://github.com/cheeerynne/ped/issues/9))
    

