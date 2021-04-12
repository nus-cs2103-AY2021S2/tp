[![CI Status](https://github.com/AY2021S2-CS2103T-W10-4/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2021S2-CS2103T-W10-4/tp/actions)

![Ui](docs/images/Ui.png)

* Vax@NUS is an app for **staff at the NUS University Health Centre (UHC)**
  to keep track of the COVID-19 vaccination status of students. <br>
* It is targeted at staff who are **proficient and comfortable with the
  Command Line Interface (CLI)**, as the app will have very limited UI interaction features.


### Example usages:

* Create, read, update and delete (CRUD) of student profiles which details critical information such as:
    * Contact information
    * NUS faculty and residence
    * Vaccination status
    * Medical history, existing medical conditions and allergies
* Creating and managing vaccination appointments for students

### Problems addressed:

* Eliminates the need for the university to use the Ministry of Health's database.
* This in turn simplifies the process of contacting, monitoring and administering vaccines for students.
* Consolidates and streamlines only data relevant to vaccinations into one app.
* **Goal:** To reduce the administrative workload on UHC staff of keeping track of vaccination and managing the
vaccination appointments of students. 


### Planned Features:

#### v1.2

1. On top of the fields already available in AB3, excluding the `tag` feature, the user will be able to input the following:
    * Student's matriculation number
    * Vaccination status
    * Faculty
    * Residence on campus (if applicable)
    * Medical history
1. A `filter` function that will allow the user to display a specific group of students, by 
   vaccination status, faculty or school residence. 
1. Modify the `find` and `delete` commands to identify students by matriculation number.
1. Create vaccination appointments and display them on the UI sorted by date.
1. Introduce the use of icons in the UI.

#### v1.3

1. Provide statistical insights regarding the vaccination of the student population, by faculty, residence or across 
   the entire NUS. 
1. Edit the details of existing vaccination appointments
1. Delete existing vaccination appointments
1. View the number of vaccination appointments in the past week and in the next week. 

#### v1.4

1. Fix bugs found during the PE-D in v1.3


>For the detailed documentation of this project, see the
**[Vax@NUS Website](https://ay2021s2-cs2103t-w10-4.github.io/tp/)**.



This project is based on the AddressBook-Level3 project created by
the [SE-EDU initiative](https://se-education.org).
