---
layout: page
title: Jiahe's Project Portfolio Page
---

## Project: SOChedule

### Overview

SOChedule is a one-stop solution for NUS School of Computing (SoC) students to manage their tasks and events 
effectively. Targeted at users who can type fast and prefer typing to mouse input, SOChedule is optimized for use via a 
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

SOChedule was morphed from a sample project named AddressBook Level 3. This application was developed by my teammates 
and me for the Software Engineering module coded CS2103 in School of Computing, National University of Singapore (NUS).

### Summary of Contributions

#### New Feature
* Getting today's tasks/events: `today_task`, `today_event`
    - Sample relevant pull request: [\#216](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/216)
    - What it does: 
        * For task, this feature allows user to check out all the tasks whose deadline is on today by filtering out all
        the relevant tasks from the task list.
          
        * For event, this feature allows user to check out all the events that is happening on today (i.e. the time 
        duration of the event has overlap with today) by filtering out all the relevant events from the event list.
          
    - Justification:
        * For task, our target user (SoC student) may have a lot of assignments or homework with deadline. This feature
        can help them check the assignments or other tasks that they need to complete today, so that they do not need
          to manually check which are the tasks that due today.
          
        * For event, our target user (SoC student) may be overwhelmed by different events (e.g. Career Talk, Interview, 
          etc). This feature will help them check all the events happening today, so that they do not need to manually
          check which are the events they need to prepare for today.
          
    
#### Enhancement Implemented
* Morphing the Logic component from AddressBook to SOChedule
    - Sample relevant pull requests: [\#79](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/79)
    [\#98](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/98)
      [\#120](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/120)
      [\#142](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/142)
      
    - What it does:
      
        Morphed the Logic component, including `add`, `delete`, `list` and `find` features into `add_task`, 
      `delete_task`, `done_task`, `list_task`, `list_event`, `find_task` and `find_event` and their corresponding unit 
      tests to the ones usable for SOChedule.
      
    - Justification:
      
        This enhancement is necessary and fundamental as the Logic component acts as the bridge between the input from 
        users and Model.
      
    - Highlights:
        
        During the morphing process, I need to check through the original codebase and understand it comprehensively. 
      Besides morphing the code, I also added a check in `AddTaskParser` so that `Task` with past `Deadline` cannot be 
        added.
    
  
* Optimizing the output for `list` command to handle the case for empty list
    - Sample relevant pull requests: [\#336](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/336)
    
    - What it does:
    
        Changes the output message of listing tasks/events when corresponding task/event list is empty from "Listed all
        tasks" to "There is no task/event present".
      
    - Justification:
        
        This enhancement is important as the message "Listed all tasks" may happen when there are some tasks/events in 
        the list but are not shown in the UI yet due to possible sluggishness. Under this circumstance, the output 
        message "There is no task/event present" will inform user about the fact that the task/event list is empty.

#### Code Contributed
* [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=ljhgab&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false) | [Pull Requests](https://github.com/AY2021S2-CS2103-W16-1/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Aljhgab)

#### Contributions to team-based tasks
* Morphed the task part of Logic component into our team project 
* Managed release v1.3.1.

#### Review/mentoring contributions
* PR reviewed:
[\#41](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/41)
  [\#43](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/43)
  [\#44](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/44)
  [\#45](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/45)
  [\#50](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/50)
  [\#54](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/54)
  [\#79](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/79)
  [\#87](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/87)
  [\#89](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/89)
  [\#90](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/90)
  [\#96](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/96)
  [\#99](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/99)
  [\#101](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/101)
  [\#118](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/118)
  [\#134](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/134)
  [\#135](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/135)
  [\#136](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/136)
  [\#161](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/161)
  [\#177](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/177)
  [\#218](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/218)
  [\#313](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/313)
  [\#315](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/315)
  [\#320](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/320)
  [\#323](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/323)
  [\#327](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/327)
  [\#330](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/330)
  [\#340](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/340)
  [\#363](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/363)
  [\#380](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/380)
  [\#382](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/382)
  
#### Contribution beyond the project team
  
* Contributed to forum discussion (examples: 
  [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/49#issuecomment-767235349), 
  [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/158#issuecomment-781799753))
  
* Reported bugs and suggestions for other team in the class (examples: 
  [1](https://github.com/AY2021S2-CS2103T-W13-4/tp/issues/120), 
  [2](https://github.com/AY2021S2-CS2103T-W13-4/tp/issues/121),
  [3](https://github.com/AY2021S2-CS2103T-W13-4/tp/issues/122),
  [4](https://github.com/AY2021S2-CS2103T-W13-4/tp/issues/123),
  [5](https://github.com/AY2021S2-CS2103T-W13-4/tp/issues/126))
  

#### Contributions to the UG:
* Wrote documentations about `add_task`, `delete_task`, `list_task`, `list_event`, `find_task`, `find_event`, `today_task` and `today_event`.
* Wrote documentations for the part **General Commands**.

#### Contributions to the DG
* Updated `Logic` diagram.
* Wrote three overview sections for SOChedule, Task and Event.
* Added implementation details of `add_task`, `find_task`, `find_event`, `today_task`, `today_event`.
* Added use cases and instructions for manual testing 
  for `add_task`, `delete_task`, `list_task`, `list_event`, `today_task`, `today_event`, `find_task`, `find_event`.
* Updated the NFR part of Developer Guide.

