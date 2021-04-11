---
layout: page
title: Tan Jun Han Ivan's Project Portfolio Page
---

## Project: FriendDex

FriendDex is a relationship management tool that empowers users to maintain long-lasting relationships with their friends, without compromising other aspects of their lives.
It is a Java GUI application created with JavaFX and users mainly interact with it by typing commands.

Given below are my contributions to the project.

* **New Feature**: Added the ability to record meetings with friends and to delete them.
  * Justification: This feature provides a way for users to input meeting data into FriendDex so users can view their past meetings if they want to.
  * Highlights: It was challenging manipulating and validating date/time inputs from users. 
  * Elaboration: Dates are used extensively within FriendDex (e.g. birthdays, special dates and calculating goal deadlines). I ended up creating a `DateUtil` class to standardise
    the format for date inputs. All commands that take date inputs uses the util class to parse dates. <br><br>
    
* **New Feature**: Added the ability to add profile pictures to friends and to delete them.
  * Justification: This feature provides a way to attach a picture with a friend so users can remember how their friends look.
  * Highlights: This feature required interactions with file system APIs to do validation and copying of image files into the data directory. 
  * Elaboration: I also had to propagate errors from the file system APIs to the users so they can take corrective actions. Renaming of image files was also done to prevent name conflicts
    with existing files already in the data directory. <br><br>

* **New feature**: Added autocomplete for commands
  * Justification: This feature improves the product because a user does not have to remember the commands available for execution.
  * Highlights: Lots of research was done to figure out the various ways to implement this feature. Ended up using a library to speed up development and reduce the number of bugs found from feature. 
    It took me a while to understand the library code and how to integrate it into an existing project.
  * Credits: Autocomplete UI from [controlsfx](https://github.com/controlsfx/controlsfx) <br><br>
  
* **New feature**: Streak Dashboard
  * What it does: Shows a dashboard with the user's friends and the streaks maintained with each of them sorted in descending order. Please refer to UG for more information on Streaks.
  * Justification: This feature motivates users to consistently meet their friends based on the desired meeting frequency set. If users missed their deadlines, their streak with that friend will be reset to zero.
  * Highlights: Needed to ensure that the Streaks Dashboard was always updated whenever there were changes to the data.
  * Elaboration: To ensure that the Streaks Dashboard was always updated, I used an observable list to store streak data. Whenever there were changes to Person data, I made the same changes to the observable list. 
    The changes were done with modifications to take into account the sorted nature of the dashboard. I also wrote the algorithm for calculating streaks. It was tedious to test that algorithm because the streak is dependent on today's date. <br><br>
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?breakdown=true&search=ivantjh)

* **Team Tasks**:
  * Set up Github Actions and repository for optimised iteration workflow (e.g. making CI to run only when we want it to, branch protection rules for master branch)
  * Managed releases `v1.3.trial` - `v1.3.1` (3 releases) on GitHub
  * Maintained the issue tracker by adding appropriate labels/milestones and closing issues when appropriate
  * Standardised the order of feature descriptions in the UG
  * Triaged and categorised 60+ issues from PE-D [#205](https://github.com/AY2021S2-CS2103T-W14-1/tp/issues/205) <br><br>
  
* **Documentation**:
  * User Guide:
    * Added documentation for `add-picture`, `del-picture`, `add-meeting` and `del-meeting` commands
    * Updated Installation section
    * Added Autocomplete, Goals and Streaks section <br><br>
    
  * Developer Guide:
    * Added implementation details for `add-picture` command
    * Added implementation details for Streaks Dashboard
    * Added instructions for manual testing for the commands: `add-picture`, `del-picture`, `add-meeting` and `del-meeting` <br><br>

* **Review contributions**:
  * PRs reviewed (with non-trivial review comments): 
    [#42](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/42),
    [#45](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/45), 
    [#83](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/83), 
    [#84](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/84), 
    [#217](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/217) 
    <br><br>
