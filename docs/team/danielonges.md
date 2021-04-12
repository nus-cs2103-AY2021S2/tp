**semester.config is a desktop application for managing your tasks.**
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

# Summary Of Contributions

## Code Contributed

You can see my code contributions [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=danielonges&tabRepo=AY2021S2-CS2103-T14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).

## Enhancements implemented

I implemented

1. Sorting feature according to pre-defined parameters
2. Daily task list that allows users to add and remove existing tasks into a daily planner
3. Ability to add notes to a task, which allows a user to jot down any quick remarks related to the task
4. Weightage attribute that allows users to capture the percentage weightage of their tasks
5. UI enhancements, such as colour coding priority tags, adding emojis for task status and creating additional headers for `All tasks` and `Daily tasks`

### Enhancement 1: `Sorting Feature` (Pull requests [#140](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/140), [#183](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/183))

What it does: This feature allows the user to sort semester.config according to any one of the five specified parameters mentioned above. It sorts the tasks and displays them according to this order:

1. Date and time - chronological
2. Module code - lexicographical
3. Priority tag - HIGH > MEDIUM > LOW
4. Task name - lexicographical
5. Weightage - descending

Justification: The motivation behind implementing this feature is to allow users to view their tasks in a preferred order, e.g. viewing the assignments that are due the soonest.

Highlights: Initially, I only planned to do the first 3 parameters. However, I realised that the implementation of the sorting feature could be easily extended to include parameters 4 and 5, and hence added them in for more flexibility for the user.

### Enhancement 2: `Daily task list` (Pull request [#182](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/182))

What it does: Displays a daily task list that the user can view as a to-do list for the day. The user can manually add or remove tasks from this daily task list by selecting the task index from the All tasks list.

Justification: This feature adds an extra dimension to the functionality of the application, as it allows the user to not only just view the tasks that he/she has, but select those that he/she wants to focus on today and plan their schedule accordingly for the day. 

Highlights: This feature proved challenging to implement, as it required an additional UniqueTaskList to be added to the TaskTracker, along with the relevant methods required to update it. Furthermore, I needed to take into account the modification of tasks on the All tasks list, and propagate any updates to the dailyTaskList.

Credits: I got the inspiration for this idea from Notion, as I personally use Notion to record down all my tasks and to plan my schedule for my days.

### Enhancement 3: `Notes feature` (Pull request [#77](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/77))

What it does: Allows user to add any additional notes that he/she might have to a task. 

Justification: A task might sometimes have additional attributes to take note of, or a user might have a certain idea as to how to approach a particular assignment. The notes feature allows the user to quickly capture and save these thoughts so that he/she can refer back to them in the future.

### Enhancement 4: `Weightage attribute` (Pull request [#81](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/81))

What it does: Provides a weightage component for each task.

Justification: Many assignments in School of Computing have weightages associated with them, and students might choose to prioritise those tasks that have a higher weightage. Therefore, having the weightage associated with the task allows them to quickly view how much each assignment is worth, and prioritise their work based on that information.

### Enhancement 5: `UI enhancements` (Pull request [#198](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/198), [#208](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/208), [#283](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/283))

The UI enhancements implemented include colour coding the priority tags (`HIGH = red`, `MEDIUM = yellow`, `LOW = green`), adding emojis for status of tasks (`finished ☑` and `not finished ☒`), adding headers for `All tasks` and `Daily tasks`, and ensuring that the `Notes` label had a style of `wrap text`.

What it does and justification: Provide the user with a more pleasant experience while using the application. In particular, the colours associated with the priority tags accurately reflect the severity and priority of the tasks, and allow users to more easily identify which tasks are more urgent.

Highlight: Despite being a seemingly simple feature, colour coding the priority tags proved to be more complicated than I initially assumed it to be. It required the styleProperty of the priority tag component in the UI to be bound to its State, and to be dynamically updated when the priority tag values were updated by the user. Nonetheless, it was an interesting feature that allowed me to learn how to dynamically bind and generate elements using JavaFX.

## Contributions to the User Guide (UG)

- Helped to refine the overall format and cosmetics of the UG (Pull request [#204](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/204))
- Added table to outline all available prefixes and parameters that can be used for the `add` and `edit` commands (Pull request [#204](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/204))
- Add usage description for `notes` command (Pull request [#282](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/282))
- Add usage description for `sort` command (Pull request [#204](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/204))
- Add usage description for `doToday` command (Pull request [#204](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/204))
- Format Command summary table (Pull request [#204](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/204))

## Contributions to the DG

- Add sorting feature implementation details (Pull request [#160](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/160))

## Contributions to team-based tasks

- Set up the GitHub team organisation and repository
- Generally helped to refactor certain codes to morph the address book into a task tracker
- Update target user profile, value proposition, user stories and glossary (Pull request [#30](https://github.com/AY2021S2-CS2103-T14-4/tp/pull/30))
- Maintain the issue tracker, and assigned people to tasks
- Facilitate the agenda and flow of team meetings most of the time
- Review, approve and merge PRs for the team

