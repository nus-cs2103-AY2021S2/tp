---
User Guide
---

Tutor Tracker is a desktop app designed to help students search for tutors and manage tuition appointments, optimised for use via a Command Line Interface (CLI) for a fast and streamlined experience.

## Features

### 6. View tuition appointment details: `view_appointment`

View details of tuition appointment

Format:
`view_appointment INDEX`

Example:
`view_appointment 1`

Example Output:
`<Appointment Details>
Tutor Name: Chloe Lim
Appointment Date: 2021-4-20
Appointment Time: 2:00pm - 2:00pm
Location: Bedok National Library`

### 7. Delete a tuition appointment: `delete_appointment`

Format: `delete_appointment INDEX`
Deletes the specific appointment at the specified INDEX.
The index refers to the index number shown in the displayed person list.
The index must be a positive integer 1, 2, 3, …​

Following the list in 5),

Example:

`delete_1`

Example Output:

`1) Jane Doe - 2021-4-21 2:00pm - 4:00pm @ Bedok National Library`

`2) Peter Ng - 2021-4-24 2:00pm - 4:00pm @ Bedok National Library`
