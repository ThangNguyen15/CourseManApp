/**
 * Created by ThangNguyen.
 */
public class CourseManApp {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        EnrolmentManager enrolmentManager = new EnrolmentManager();
        Report report = new Report();

        int option, option1, option2, option3, option4, option5, option6;

        DBApp dba = null;
        dba = new DBApp(DBApp.DRIVER_POSTGRESQL);

        // connect to database
        boolean ok = dba.connect("courseman", "postgres", "A02041988b@");
        if (!ok) {
            System.exit(1);
        }
        if (ok) {
            do {
                System.out.println("==========STUDENT MANAGER PROGRAM==========");
                System.out.println("1. Manage student.");
                System.out.println("2. Manage course.");
                System.out.println("3. Manage enrolment.");
                System.out.println("4. Report.");
                System.out.println("5. Quit program.");
                System.out.println("Enter your option (1-5): ");
                option = TextIO.getInt();
                switch (option) {
                    case 1:
                        do {
                            System.out.println("==========STUDENT MANAGER==========");
                            System.out.println("1. Add new student.");
                            System.out.println("2. Edit student's information.");
                            System.out.println("3. Delete a student.");
                            System.out.println("4. Display a table of all students.");
                            System.out.println("5. Return to main menu.");
                            System.out.println("Enter your option (1-5): ");
                            option1 = TextIO.getInt();
                            switch (option1) {
                                case 1:
                                    System.out.println("Enter student ID: ");
                                    int studentId = TextIO.getInt();
                                    System.out.println("Enter firstName: ");
                                    TextIO.getln();
                                    String firstName = TextIO.getln();
                                    System.out.println("Enter lastName: ");
                                    String lastName = TextIO.getln();
                                    System.out.println("Enter address: ");
                                    String address = TextIO.getln();
                                    System.out.println("Enter date of birth: ");
                                    String dOB = TextIO.getln();
                                    if ((!studentManager.validateStudentId(studentId)) && studentManager.validateInfo(firstName, lastName, address, dOB)) {
                                        studentManager.addStudent(studentId, firstName, lastName, address, dOB);
                                    } else {
                                        System.out.println("Please try again!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter ID of student you want to edit: ");
                                    studentId = TextIO.getInt();
                                    if (studentManager.validateStudentId(studentId)) {
                                        System.out.println("Which information of this student do you want to change?");
                                        System.out.println("1. First name");
                                        System.out.println("2. Last name");
                                        System.out.println("3. Address");
                                        System.out.println("4. Date of birth");
                                        System.out.println("Enter your option(1-4): ");
                                        option2 = TextIO.getInt();
                                        switch (option2) {
                                            case 1:
                                                System.out.println("Enter new firstName: ");
                                                String newFirstName = TextIO.getln();
                                                if (studentManager.validateFirstName(newFirstName)) {
                                                    studentManager.editFirstName(studentId, newFirstName);
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Enter new lastName: ");
                                                String newLastName = TextIO.getln();
                                                if (studentManager.validateLastName(newLastName)) {
                                                    studentManager.editLastName(studentId, newLastName);
                                                }
                                                break;
                                            case 3:
                                                System.out.println("Enter new address: ");
                                                String newAddress = TextIO.getln();
                                                if (studentManager.validateAddress(newAddress)) {
                                                    studentManager.editAddress(studentId, newAddress);
                                                }
                                                break;
                                            case 4:
                                                System.out.println("Enter new address: ");
                                                String newDOB = TextIO.getln();
                                                if (studentManager.validateDateOfBirth(newDOB)) {
                                                    studentManager.editDOB(studentId, newDOB);
                                                }
                                        }
                                    } else {
                                        System.out.println("Student ID does not exist!");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter ID of the student you want to delete: ");
                                    studentId = TextIO.getInt();
                                    if (studentManager.validateStudentId(studentId)) {
                                        studentManager.deleteStudent(studentId);
                                    } else {
                                        System.out.println("Student ID does not exist!");
                                    }
                                    break;
                                case 4:
                                    studentManager.displayStudent();
                                    break;
                            }
                        } while (option1 != 5);
                        break;
                    case 2:
                        do {
                            System.out.println("==========COURSE MANAGER==========");
                            System.out.println("1. Add new course.");
                            System.out.println("2. Edit course's information.");
                            System.out.println("3. Delete a course.");
                            System.out.println("4. Display a table of all courses.");
                            System.out.println("5. Return to main menu.");
                            System.out.println("Enter your option (1-5): ");
                            option3 = TextIO.getInt();
                            switch (option3) {
                                case 1:
                                    System.out.println("Enter course ID: ");
                                    TextIO.getln();
                                    String courseId = TextIO.getln();
                                    System.out.println("Enter course name: ");
                                    String courseName = TextIO.getln();
                                    System.out.println("Enter prerequisites: ");
                                    String prerequisites = TextIO.getln();
                                    if (!courseManager.validateCourseId(courseId) && courseManager.validateCourseName(courseName)
                                            && courseManager.validatePrerequisites(prerequisites)) {
                                        courseManager.addCourse(courseId, courseName, prerequisites);
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter ID of the course you want to edit: ");
                                    courseId = TextIO.getln();
                                    if (courseManager.validateCourseId(courseId)) {
                                        System.out.println("Which information of this course do you want to change?");
                                        System.out.println("1. Course name");
                                        System.out.println("2. Course prerequisites");
                                        System.out.println("Enter your option(1 or 2): ");
                                        option4 = TextIO.getInt();
                                        switch (option4) {
                                            case 1:
                                                System.out.println("Enter new course name: ");
                                                String newCourseName = TextIO.getln();
                                                courseManager.editCourseName(courseId, newCourseName);
                                                break;
                                            case 2:
                                                System.out.println("Enter new prerequisites: ");
                                                String newPrerequisites = TextIO.getln();
                                                courseManager.editCoursePrerequisites(courseId, newPrerequisites);
                                        }
                                    } else {
                                        System.out.println("Course ID does not exist!");
                                    }
                                    break;
                            }
                        } while (option3 != 5);
                        break;
                    case 3:
                        do {
                            System.out.println("==========ENROLMENT MANAGER==========");
                            System.out.println("1. Add new enrolment.");
                            System.out.println("2. Update final grade.");
                            System.out.println("3. Display enrolment.");
                            System.out.println("4. Display sorted enrolment.");
                            System.out.println("5. Return to main menu.");
                            System.out.println("Enter your option(1-5): ");
                            option5 = TextIO.getInt();
                            switch (option5) {
                                case 1:
                                    System.out.println("Enter student ID: ");
                                    int studentId = TextIO.getInt();
                                    System.out.println("Enter course name: ");
                                    String courseName = TextIO.getln();
                                    System.out.println("Enter semester(1-5): ");
                                    int semester = TextIO.getInt();
                                    System.out.println("Enter final grade(E, G, P or F): ");
                                    char finalGrade = TextIO.getChar();
                                    if (!studentManager.validateStudentId(studentId) && courseManager.validateCourseName(courseName)
                                            && enrolmentManager.validateSemester(semester) && enrolmentManager.validateFinalGrade(finalGrade)) {
                                        enrolmentManager.addEnrolment(studentId, courseName, semester, finalGrade);
                                    } else {
                                        System.out.println("Please try again!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter student ID: ");
                                    studentId = TextIO.getInt();
                                    System.out.println("Enter course name: ");
                                    courseName = TextIO.getln();
                                    if (studentManager.validateStudentId(studentId) && courseManager.validateCourseName(courseName)) {
                                        System.out.println("Enter new final grade(E, G, P, F: ");
                                        char newFinalGrade = TextIO.getChar();
                                        if (enrolmentManager.validateFinalGrade(newFinalGrade)) {
                                            enrolmentManager.updateGrade(studentId, courseName, newFinalGrade);
                                        } else {
                                            System.out.println("Invalid final grade!");
                                        }
                                    } else {
                                        System.out.println("Please try again!");
                                    }
                                    break;
                                case 3:
                                    enrolmentManager.displayEnrolment();
                                    break;
                                case 4:
                                    enrolmentManager.displaySortedEnrolment();
                                    break;
                            }
                        } while (option5 != 5);
                        break;
                    case 4:
                        do {
                            System.out.println("==========REPORT==========");
                            System.out.println("1. Display courses of a student.");
                            System.out.println("2. Display students of a course.");
                            System.out.println("3. Display failed student.");
                            System.out.println("4. Return to main menu.");
                            System.out.println("Enter your option(1-4): ");
                            option6 = TextIO.getInt();
                            switch (option6) {
                                case 1:
                                    System.out.println("Enter student ID: ");
                                    int studentId = TextIO.getInt();
                                    if (studentManager.validateStudentId(studentId)) {
                                        report.displayStudentCourses(studentId);
                                    } else {
                                        System.out.println("Student ID does not exist!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter course ID: ");
                                    String courseId = TextIO.getln();
                                    if (courseManager.validateCourseId(courseId)) {
                                        report.displayStudentOfACourse(courseId);
                                    } else {
                                        System.out.println("Invalid course ID!");
                                    }
                                    break;
                                case 3:
                                    report.displayFailedStudent();
                                    break;
                            }
                        } while (option6 != 4);
                        break;
                }
            } while (option != 5);
            dba.close();
        }
    }
}