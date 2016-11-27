/**
 * Created by ThangNguyen.
 */
public class Report {
    private DBApp dba = new DBApp();

    public Report(DBApp dba) {
        this.dba = dba;
    }

    public void displayStudentCourses(int studentId) {
        String sql = "SELECT" +
                " student.studentid," +
                " course.courseid," +
                " course.name" +
                " FROM" +
                " public.course," +
                " public.enrolment," +
                " public.student" +
                " WHERE" +
                " enrolment.course = course.courseid AND" +
                " enrolment.student = student.studentid AND" +
                " student.studentid = " + studentId + ";";
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "my_enrols.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);

        TextIO.writeStandardOutput();
    }

    public void displayStudentOfACourse(String courseId) {
        String sql = "SELECT" +
                " student.studentid," +
                " student.firstname," +
                " student.lastname," +
                " student.dateofbirth," +
                " course.courseid" +
                " FROM" +
                " public.course," +
                " public.enrolment," +
                " public.student" +
                " WHERE" +
                " enrolment.course = course.courseid AND" +
                " enrolment.student = student.studentid AND" +
                " course.courseid = '" + courseId + "'";
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "course_enrols.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);
        TextIO.writeStandardOutput();
    }

    public void displayFailedStudent() {
        String sql = "SELECT Enrolment.course, Student.studentid, Student.firstname, Student.lastname, Student.dateofbirth FROM Enrolment " +
                     "INNER JOIN student ON enrolment.student = student.studentid WHERE finalgrade = 'F';";;
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "fails.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);
        TextIO.writeStandardOutput();
    }
}
