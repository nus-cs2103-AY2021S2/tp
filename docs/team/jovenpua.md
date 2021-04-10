---
layout: page
title: Joven Pua's Project Portfolio Page
---

## Project: Taskify

Taskify is a desktop task management application for university students. Using Taskify, students can
manage all their tasks (academics/personal/CCAs) effectively and seamlessly. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLOC.

Given below are my contributions to the project.

* ### Major contributions:
    * #### Implemented multiple deletion of tasks
        * What it does: Allows users to delete more than one task with one command, by providing multiple individual 
          indices, giving an index range, or by providing a status of which all tasks that have it will be deleted.
          
        * Justification: This feature makes deletion of tasks much faster for the user. Users would want to delete all 
          completed tasks to declutter Taskify, or delete select tasks in Taskify without having to delete them one by
          one.
          
        * Highlights: The implementation involved rigorous testing because it was easy to mess up the rather complex 
          regular expressions that accepts different types of input for the delete command. It was a little challenging
          to reorganise the new code into their different classes to try to obey SLAP and SRP.
          
        * Relevant PRs: [\#65](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/65), [\#158](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/158),
          [\#160](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/160), [\#155](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/155),
          [\#165](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/165), [\#172](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/172)  
          
    * #### Implemented the Date class
        * What it does: An essential field in a Task, used for deadlines.
        
        * Justification: A task tracker without the ability to keep track of deadlines is not helpful at all. By adding 
          the Date class, tasks can now have a deadline.
          
        * Highlights: Minor changes to logic in many classes. Checking if a string is valid to be parsed as a Date was 
          slightly challenging at first.
          
        * Relevant PRs: [\#33](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/33)
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=W14-4&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=CSmortal&tabRepo=AY2021S2-CS2103T-W14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)  

* **Enhancements to existing features**:
    * Made the response messages for some commands clearer, in [PR #164](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/164)
    * Refactored typical persons in AB3 to typical tasks in Taskify, in [PR #80](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/80)
    
* **Documentation**:
    * User Guide:
        * Added the Features and Command Summary section: [\#40](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/40)
        * Spruced up the description of the `delete` command: [\#79](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/79)
    
    * Developer Guide:
        * Added user stories and use cases: [\#40](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/40)
    
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#54](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/54), [\#36](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/36),
      [\#37](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/37), [\#84](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/84),
      [\#66](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/66)
    * Reported bugs and suggestions for other teams in the class (examples: [Bugs reported in PED](https://github.com/CSmortal/ped/issues))
    * Contributed questions to the forum: [127](https://github.com/nus-cs2103-AY2021S2/forum/issues/127), [154](https://github.com/nus-cs2103-AY2021S2/forum/issues/154)
    
* **Tools**:
    * Integrated the `junit-jupiter-params` JUnit framework to the project for efficient testing
    

