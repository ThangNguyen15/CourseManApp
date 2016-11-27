/**
 * Created by ThangNguyen.
 */
public class CourseManApp {
    public static void main(String[] args) {
        DBApp dba = null;
        dba = new DBApp(DBApp.DRIVER_POSTGRESQL);

        // connect to database
        boolean ok = dba.connect("courseman", "postgres", "A02041988b@");
        if (!ok) {
            System.exit(1);
        }

        StudentManager studentManager = new StudentManager(dba);
        CourseManager courseManager = new CourseManager(dba);
        EnrolmentManager enrolmentManager = new EnrolmentManager(dba);
        Report report = new Report(dba);

        int option, option1, option2, option3, option4, option5, option6;

        if (ok) {
            do {//main menu
                System.out.println("==========STUDENT MANAGER PROGRAM==========");
                System.out.println("1. Manage student.");
                System.out.println("2. Manage course.");
                System.out.println("3. Manage enrolment.");
                System.out.println("4. Report.");
                System.out.println("5. Quit program.");
                System.out.println("Enter your option (1-5): ");
                option = TextIO.getlnInt();
                switch (option) {
                    case 1:
                        do {//student manager's menu
                            System.out.println("==========STUDENT MANAGER==========");
                            System.out.println("1. Add new student.");
                            System.out.println("2. Edit student's information.");
                            System.out.println("3. Delete a student.");
                            System.out.println("4. Display a table of all students.");
                            System.out.println("5. Return to main menu.");
                            System.out.println("Enter your option (1-5): ");
                            option1 = TextIO.getlnInt();
                            switch (option1) {
                                case 1://add a new student
                                    System.out.println("Enter student ID: ");
                                    int studentId = TextIO.getlnInt();
                                    if (studentManager.validateStudentId(studentId)) {
                                        System.out.println("Student ID is exist!");
                                        break;
                                    }
                                    System.out.println("Enter firstName: ");
                                    String firstName = TextIO.getln();
                                    if (!studentManager.validateFirstName(firstName)) {
                                        System.out.println("First name's length must not over 50 characters.");
                                        break;
                                    }
                                    System.out.println("Enter lastName: ");
                                    String lastName = TextIO.getln();
                                    if (!studentManager.validateLastName(lastName)) {
                                        System.out.println("Last name's length must not over 50 characters.");
                                        break;
                                    }
                                    System.out.println("Enter address: ");
                                    String address = TextIO.getln();
                                    if (!studentManager.validateAddress(address)) {
                                        System.out.println("Address's length must not over 250 characters.");
                                        break;
                                    }
                                    System.out.println("Enter date of birth(dd/mm/yyyy): ");
                                    String dOB = TextIO.getln();
                                    if (!studentManager.validateDateOfBirth(dOB)) {
                                        System.out.println("DOB's length must not over 30 characters.");
                                    }

                                    studentManager.addStudent(studentId, firstName, lastName, address, dOB);

                                    break;
                                case 2://edit an existing student
                                    System.out.println("Enter ID of student you want to edit: ");
                                    studentId = TextIO.getlnInt();
                                    if (!studentManager.validateStudentId(studentId)) {
                                        System.out.println("Student ID does not exist!");
                                        break;
                                    } else {
                                        System.out.println(dba.selectToString("SELECT * FROM Student WHERE studentid = " + studentId + ";"));
                                        System.out.println("Are you sure you want to edit this student?(Y/N): ");
                                        char choice = TextIO.getlnChar();
                                        if (choice == 'N' || choice == 'n') {
                                            System.out.println("Return to Student Manager's Menu");
                                            break;
                                        } else if (choice == 'Y' || choice == 'y') {
                                            System.out.println("Which information of this student do you want to change?");
                                            System.out.println("1. First name");
                                            System.out.println("2. Last name");
                                            System.out.println("3. Address");
                                            System.out.println("4. Date of birth");
                                            System.out.println("Enter your option(1-4): ");
                                            option2 = TextIO.getlnInt();
                                            switch (option2) {
                                                case 1:
                                                    TextIO.getln();
                                                    System.out.println("Enter new firstName: ");
                                                    String newFirstName = TextIO.getln();
                                                    if (studentManager.validateFirstName(newFirstName)) {
                                                        studentManager.editFirstName(studentId, newFirstName);
                                                    } else {
                                                        System.out.println("First name's length must not over 50 characters.");
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("Enter new lastName: ");
                                                    String newLastName = TextIO.getln();
                                                    if (studentManager.validateLastName(newLastName)) {
                                                        studentManager.editLastName(studentId, newLastName);
                                                    } else {
                                                        System.out.println("Last name's length must not over 50 characters.");
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("Enter new address: ");
                                                    String newAddress = TextIO.getln();
                                                    if (studentManager.validateAddress(newAddress)) {
                                                        studentManager.editAddress(studentId, newAddress);
                                                    } else {
                                                        System.out.println("Address's length must not over 250 characters.");
                                                    }
                                                    break;
                                                case 4:
                                                    System.out.println("Enter new date of birth: ");
                                                    String newDOB = TextIO.getln();
                                                    if (studentManager.validateDateOfBirth(newDOB)) {
                                                        studentManager.editDOB(studentId, newDOB);
                                                    } else {
                                                        System.out.println("DOB's length must not over 30 characters.");
                                                    }
                                            }
                                        }
                                    }
                                    break;
                                case 3://delete an existing student
                                    System.out.println("Enter ID of the student you want to delete: ");
                                    studentId = TextIO.getlnInt();
                                    if (!studentManager.validateStudentId(studentId)) {
                                        System.out.println("Student ID does not exist!");
                                        break;
                                    } else {
                                        System.out.println(dba.selectToString("SELECT * FROM Student WHERE studentid = " + studentId + ";"));
                                        System.out.println("Are you sure you want to delete this student?(Y/N): ");
                                        char choice = TextIO.getlnChar();
                                        if (choice == 'N' || choice == 'n') {
                                            System.out.println("Return to Student Manager's Menu");
                                            break;
                                        } else if (choice == 'Y' || choice == 'y') {
                                            studentManager.deleteStudent(studentId);
                                        }
                                    }
                                    break;
                                case 4://display all student in an HTML file
                                    studentManager.displayStudent();
                                    break;
                            }
                        } while (option1 != 5);
                        break;
                    case 2:
                        do {//course manager's menu
                            System.out.println("==========COURSE MANAGER==========");
                            System.out.println("1. Add new course.");
                            System.out.println("2. Edit course's information.");
                            System.out.println("3. Delete a course.");
                            System.out.println("4. Display a table of all courses.");
                            System.out.println("5. Return to main menu.");
                            System.out.println("Enter your option (1-5): ");
                            option3 = TextIO.getlnInt();
                            switch (option3) {
                                case 1://add a new course
                                    System.out.println("Enter course ID: ");
                                    String courseId = TextIO.getln();
                                    if (courseManager.validateCourseId(courseId) || courseId.length() > 5) {
                                        System.out.println("Course ID must not exist and Course ID's length must not over 5 characters.");
                                        break;
                                    }
                                    System.out.println("Enter course name: ");
                                    String courseName = TextIO.getln();
                                    if (!courseManager.validateCourseName(courseName)) {
                                        System.out.println("Course name's length must not over 200 characters.");
                                        break;
                                    }
                                    System.out.println("Enter prerequisites: ");
                                    String prerequisites = TextIO.getln();
                                    if (!courseManager.validatePrerequisites(prerequisites)) {
                                        System.out.println("Prerequisite's length must not over 250 characters.");
                                        break;
                                    }

                                    courseManager.addCourse(courseId, courseName, prerequisites);

                                    break;
                                case 2://edit an existing course
                                    System.out.println("Enter ID of the course you want to edit: ");
                                    courseId = TextIO.getln();
                                    if (!courseManager.validateCourseId(courseId) || courseId.length() > 5) {
                                        System.out.println("Course ID must exist and Course ID's length must not over 5 characters.");
                                        break;
                                    } else {
                                        System.out.println(dba.selectToString("SELECT * FROM Course WHERE courseid = '" + courseId + "';"));
                                        System.out.println("Are you sure you want to edit this course?(Y/N)");
                                        char choice = TextIO.getlnChar();
                                        if (choice == 'N' || choice == 'n') {
                                            System.out.println("Return to Course Manager's Menu.");
                                            break;
                                        } else if (choice == 'Y' || choice == 'y') {
                                            System.out.println("Which information of this course do you want to change?");
                                            System.out.println("1. Course name");
                                            System.out.println("2. Course prerequisites");
                                            System.out.println("Enter your option(1 or 2): ");
                                            option4 = TextIO.getlnInt();
                                            switch (option4) {
                                                case 1:
                                                    System.out.println("Enter new course name: ");
                                                    String newCourseName = TextIO.getln();
                                                    if (courseManager.validateCourseName(newCourseName)) {
                                                        courseManager.editCourseName(courseId, newCourseName);
                                                    } else {
                                                        System.out.println("Course name's length must not over 200 characters.");
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("Enter new prerequisites: ");
                                                    String newPrerequisites = TextIO.getln();
                                                    if (courseManager.validatePrerequisites(newPrerequisites)) {
                                                        courseManager.editCoursePrerequisites(courseId, newPrerequisites);
                                                    } else {
                                                        System.out.println("Prerequisite's length must not over 250 characters.");
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case 3://delete an existing course
                                    System.out.println("Enter ID of the course you want to delete: ");
                                    courseId = TextIO.getln();
                                    if (courseManager.validateCourseId(courseId) || courseId.length() <= 5) {
                                        System.out.println(dba.selectToString("SELECT * FROM Course WHERE courseid = '" + courseId + "';"));
                                        System.out.println("Are you sure you want to delete this course?(Y/N): ");
                                        char choice = TextIO.getlnChar();
                                        if (choice == 'N' || choice == 'n') {
                                            System.out.println("Return to Course Manager's Menu.");
                                            break;
                                        } else if (choice == 'Y' || choice == 'y') {
                                            courseManager.deleteCourse(courseId);
                                        }
                                    } else {
                                        System.out.println("Course ID must exist and course ID's length must not over 5 characters.");
                                    }
                                    break;
                                case 4://display all course in an HTML file
                                    courseManager.displayCourse();
                                    break;
                            }
                        } while (option3 != 5);

                        break;
                    case 3:
                        do {//enrolment manager's menu
                            System.out.println("==========ENROLMENT MANAGER==========");
                            System.out.println("1. Add new enrolment.");
                            System.out.println("2. Update final grade.");
                            System.out.println("3. Display enrolment.");
                            System.out.println("4. Display sorted enrolment.");
                            System.out.println("5. Return to main menu.");
                            System.out.println("Enter your option(1-5): ");
                            option5 = TextIO.getlnInt();
                            switch (option5) {
                                case 1://add a new enrolment
                                    System.out.println("Enter student ID: ");
                                    int studentId = TextIO.getlnInt();
                                    if (!studentManager.validateStudentId(studentId)) {
                                        System.out.println("Student ID does not exist.");
                                        break;
                                    }
                                    System.out.println("Enter course ID: ");
                                    String courseId = TextIO.getln();
                                    if (!courseManager.validateCourseId(courseId) || courseId.length() > 5) {
                                        System.out.println("Course ID must exist and course ID's length must not over 5 characters.");
                                        break;
                                    }
                                    System.out.println("Enter semester(1-8): ");
                                    int semester = TextIO.getlnInt();
                                    if (!enrolmentManager.validateSemester(semester)) {
                                        System.out.println("Semester value must be between 1 and 8");
                                        break;
                                    }
                                    System.out.println("Enter final grade(E, G, P or F): ");
                                    char finalGrade = TextIO.getChar();
                                    if (!enrolmentManager.validateFinalGrade(finalGrade)) {
                                        System.out.println("Final grade value is E, G, P or F!");
                                        break;
                                    }

                                    enrolmentManager.addEnrolment(studentId, courseId, semester, finalGrade);

                                    break;
                                case 2://update final grade for an existing enrolment
                                    System.out.println("Enter student ID: ");
                                    studentId = TextIO.getlnInt();
                                    if (!studentManager.validateStudentId(studentId)) {
                                        System.out.println("Student ID does not exist!");
                                        break;
                                    }
                                    System.out.println("Enter course ID: ");
                                    courseId = TextIO.getln();
                                    if (!courseManager.validateCourseId(courseId) || courseId.length() > 5) {
                                        System.out.println("Course ID must exist and course ID's length must not over 5 characters");
                                        break;
                                    }
                                    System.out.println("Enter new final grade(E, G, P, F: ");
                                    char newFinalGrade = TextIO.getChar();
                                    if (enrolmentManager.validateFinalGrade(newFinalGrade)) {
                                        enrolmentManager.updateGrade(studentId, courseId, newFinalGrade);
                                    }
                                    else {
                                        System.out.println("Final grade must be E, G, P or F!");
                                    }
                                    break;
                                case 3://display all enrolment in an HTML file
                                    enrolmentManager.displayEnrolment();
                                    break;
                                case 4://display all enrolment sorted by final grade in an HTML file
                                    enrolmentManager.displaySortedEnrolment();
                                    break;
                            }
                        } while (option5 != 5);
                        break;
                    case 4:
                        do {//Report's menu
                            System.out.println("==========REPORT==========");
                            System.out.println("1. Display courses of a student.");
                            System.out.println("2. Display students of a course.");
                            System.out.println("3. Display failed student.");
                            System.out.println("4. Return to main menu.");
                            System.out.println("Enter your option(1-4): ");
                            option6 = TextIO.getlnInt();
                            switch (option6) {
                                case 1://display all courses of an existing student
                                    System.out.println("Enter student ID: ");
                                    int studentId = TextIO.getlnInt();
                                    if (studentManager.validateStudentId(studentId)) {
                                        report.displayStudentCourses(studentId);
                                    } else {
                                        System.out.println("Student ID does not exist!");
                                    }
                                    break;
                                case 2://display all students of an existing course
                                    System.out.println("Enter course ID: ");
                                    String courseId = TextIO.getln();
                                    if (courseManager.validateCourseId(courseId) && courseId.length() < 5) {
                                        report.displayStudentOfACourse(courseId);
                                    } else {
                                        System.out.println("Invalid course ID!");
                                    }
                                    break;
                                case 3://display all students who have failed at least one course
                                    report.displayFailedStudent();
                                    break;
                            }
                        } while (option6 != 4);
                        break;
                }
            } while (option != 5);
            dba.close();//close DBApp
        }
    }
}