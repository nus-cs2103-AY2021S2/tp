[![CI Status](https://github.com/AY2021S2-CS2103-W16-4/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2021S2-CS2103-W16-4/tp/actions)

![Ui](docs/images/Ui.png)

This is a sample project for Managers of Tuition Centres
Example usages:
- To allocate students and tutors to a specific class
- Determine class size

The project simulates an ongoing software project for a desktop application (called EZManage) used for
managing students, tutors and classes.
- It is written in OOP fashion. It provides a reasonably well-written code base bigger (around 6 KLoC) than what students usually write in beginner-level SE modules,
  without being overwhelmingly big.
- It comes with a reasonable level of user and developer documentation.

It is named as EzManage as it allows tuition centres managers to easily manage
students, tutors and classes all in one single web application.

For the detailed documentation of this project, see the [EZManage product website](https://ay2021s2-cs2103-w16-4.github.io/tp/).
This project is a part of the se-education.org initiative. If you would like to contribute code to this project, see se-education.org for more info.

## Feature List
* Add Student
* Add Tutor
* Add Session
* List Persons
* List Students
* List Tutors
* List Sessions
* View Student
* View Tutor
* View Session
* Edit Student
* Edit Tutor
* Edit Session
* Delete Student
* Delete Tutor
* Delete Session
* Clear Contacts
* Help
* Exit

## Command summary

Action | Format, Examples
--------|------------------
**Add** | For Person:`add_person tp/ROLE n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tag/TAG]…​` <br> e.g., `add_person tp/student n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`<br> For Session: `add_session d/DAY ts/TIMESLOT s/SUBJECT [tag/TAG]…​` <br> e.g. `add_session d/Saturday ts/13:00 to 15:00 s/Math` 
**Clear** | `clear`
**Delete** | For Student: <br> `delete_person s/ID`<br> e.g., `delete_person s/22` <br><br> For Tutor: <br> `delete_person t/ID`<br> e.g., `delete_person t/8`<br><br> For Session:<br>`delete_session c/ID` <br> e.g., `delete_session c/9`
**Edit** | For Student: <br> `edit_person s/ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [tag/TAG]…​` <br> e.g., `edit_person s/2 n/Betsy Crower tag/` <br><br> For Tutor: <br> `edit_person t/ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [tag/TAG]…​` <br> e.g., `edit_person t/1 p/88888888 e/sarahwong@example.com` <br><br> For Session: <br> `edit_session c/ID [d/DAY] [ts/TIMESLOT] [s/SUBJECT] [tag/TAG]…​`<br> e.g.,`edit_session c/1 d/Monday s/Biology` <br> e.g. `edit_session c/2 d/Saturday ts/13:00 to 15:00 tag/Hard` 
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | For All Persons: <br>`list persons` <br> For All Students: <br>`list students` <br> For All Tutors: <br>`list tutors` <br> For All Sessions: <br>`list sessions`
**View** | For Person: <br> `view_person s/ID` or `view_person t/ID` <br> e.g., `view_person s/1` <br> For Session: <br> `view_session c/ID` <br> e.g., `view_session c/5`
**Help** | `help`

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org)
