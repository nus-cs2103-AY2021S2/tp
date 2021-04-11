---
layout: page
title: Jiaxiang's Project Portfolio Page
---

## Project: SOChedule

### Overview
SOChedule is a one-stop solution for managing tasks and events, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).

### Summary of contributions
Given below are my contributions to the project.

#### Features-related contributions
* **New Feature 1**: Mark one or multiple tasks as done, `done_task`.
    * **What it does:**
      
      It allows the user to mark one or more tasks as completed at a time. 
      If the users need, they can use `undone_task` to mark the tasks as uncompleted afterwards.
    * **Justification:**
    
      Since our product aims to help users manager their tasks and events. 
      One of the most common needs for the users is to mark the tasks as completed.
      Very often, they need to do so for more than 1 tasks. 
      As a result, this new feature improves the product significantly 
      as it provides a convenient and efficient way for users to do so with one single command.
    * **Highlights:**
      
      This feature acts as one of the most fundamental and important functions in our products.
      The implementation too was challenging as it required changes to the Model, UI and Logic component 
      of the application. In-depth analysis of these components are needed.

* **New Feature 2**: Mark a completed task as uncompleted, `undone_task`.

    * **What it does:**

      It allows the user to mark a completed task as uncompleted.
      If the users need, they can use `done_task` to mark the task as completed afterwards.
    * **Justification:**
        
        Occasionally, users may realize that they made a mistake in completing a task, or certain parts of
      a completed task is not finished yet. Thus, they may have the need to uncomplete a task rather than
      deleting it and add the same task again. As a result, this new feature provides 
      a convenient and efficient way to meet our users' potential needs.

* **New Feature 3**: Find tasks and events before or on the given date, `find_schedule`.

    * **What it does:**

      Given a date, it returns users uncompleted tasks that are due before or on the specified date
      and events that are ongoing given the specified date. 
    * **Justification:**
        
        Very often, users may need to know the tasks they have not completed at a specific date. Also,
      they may wish to know the events happening at this date. Together, this formed a schedule of tasks to complete
      and events they are involved given a specific date. This new feature acts as a helpful shortcut function that
      finds the tasks and events that may interest the users the most. This helps to make our product more convenient 
      and efficient to use.
      

* **Enhancements to existing features**:
    * Enhance the function of editing an existing task, `Edit task`.
        * **What it does:**

            Allows users to edit the details of the task at the specified index 
            and updates its respective values with the supplied values. 
            Further restrictions are added to disallow users from editing a completed task.
            However, users are allowed to edit an overdue task.
      
        * **Justification:**
          
          In-depth analysis of design alternatives are needed when implementing this command.
          For overdue tasks, we believe that users may need to edit an overdue task at very frequent occasions.
          For example, users may wish to extend the deadline of an overdue task, 
          or increasing its priority to remind themselves that this task needs more attention. 
          Thus, we allow overdue tasks to be edited. 
          For completed task, we believe that users do not often need to edit a task once it is completed. 
          One of the major reasons is that when a task is completed, it is naturally seen as being 'frozen'.
          This means no more changes are expected to be performed on it. 
          Certainly, if the users need to edit a completed task because they realize they made some mistakes in entering the details of the task, 
          they can undone it and perform the editing operations.
        
    * Contributed to the transition of previous AddressBook3 codebase to current needs, mainly about Logic
    (Pull requests [\#87](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/87), [\#89](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/89),
    [\#90](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/90), [\#118](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/118))
      
* **Testing**:
    * Created and developed tests for `done_task`, `undone_task`, `find_schedule` commands 
and their parsers. 
    * Also, helped to develop tests for `Logic` component, such as `SocheduleParserUtil`.
    * One of the PRs significantly improved the testing coverage by `5.66%`, [\#344](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/344).

    

#### Code contributed
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=litone01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

#### Other contributions
* **Documentation**:
    * User Guide:
        * Explained the use and constraints on `edit_task` command.
        * Explained the use and constraints on `done_task` command.
        * Explained the use and constraints on `undone_task` command.
        * Explained the use and constraints on `find_schedule` command.
        * Updated command summary and added visual illustrations to the relevant commands.
        
    * Developer Guide:
        * Updated and explained the `Model` component.
          Created the **class diagrams** for `Model`, `Task` and `Event`.
          Explained the **design considerations** for comparing same tasks and events 
          and setting maximum length to `Name`, `Tag` and `Category`.
          
        * Explained the **implementation details** and **design considerations** for `done_task`, `undone_task`, `edit_task` and `find_schedule`.
            Created **sequence diagrams** for sample usages of `done_task`, `undone_task`, `edit_task` and `find_schedule`
            Created an **activity diagram** for `undone_task` command.
        * Added use cases and manual testing cases for `done_task`, `undone_task`, `edit_task` and `find_schedule`.
        * Contributed to the effort section. 
          Summarised the highlights of the product, and explain the efforts the team in developing the product.
          

* **Team-related contributions**:
  * Managed and helped releases `v1.2.1` on GitHub.
  * Participated in managing Milestone `v1.3` and `v1.3b`.
  * Reviewed PRs (selected few, in decreasing amount of significance contributed):
    
    [\#79](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/79), 
    [\#54](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/54),
    [\#101](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/101),
    [\#120](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/120),
    [\#203](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/203),
    [\#354](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/354),
    [\#355](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/355),
    [\#365](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/365),
    [\#51](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/51),
    [\#216](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/216),
    [\#211](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/211),
    [\#194](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/194),
    [\#195](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/195),
    [\#225](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/225).
    

* **Community**:
    * Contributed to forum discussions:
      
      [\#16](https://github.com/nus-cs2103-AY2021S2/forum/issues/16),
      [\#31](https://github.com/nus-cs2103-AY2021S2/forum/issues/31),
      [\#81](https://github.com/nus-cs2103-AY2021S2/forum/issues/81),
      [\#141](https://github.com/nus-cs2103-AY2021S2/forum/issues/141),
      [\#287](https://github.com/nus-cs2103-AY2021S2/forum/issues/287).
      
    * Some of my suggestions have benefited other classmates:

        [\#34](https://github.com/nus-cs2103-AY2021S2/forum/issues/34),
        [\#36](https://github.com/nus-cs2103-AY2021S2/forum/issues/36).

  
