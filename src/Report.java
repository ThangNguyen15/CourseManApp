/**
 * Created by ThangNguyen.
 */
public class Report {
    private DBApp dba = new DBApp();

    public void displayStudentCourses(int studentId) {
        String sql = "SELECT* FROM Enrolment WHERE student =" + studentId + "";
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
        String sql = "SELECT* FROM Enrolment WHERE course ='" + courseId + "'";
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
        String sql = "SELECT* FROM Enrolment WHERE finalgrade ='F'";
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
