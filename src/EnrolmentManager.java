/**
 * Created by ThangNguyen.
 */
public class EnrolmentManager {
    private DBApp dba = new DBApp();

    public boolean addEnrolment(int studenId, String courseName, int semester, char finalGrade) {
        String sql = "INSERT INTO Enrolment VALUES(" + studenId + ",'" + courseName + "', '" + semester + "', '"
                + finalGrade + "');";
        System.out.println("Executing query: " + sql);
        dba.insert(sql);
        System.out.println("Add successfully!");
        return true;
    }

    public boolean updateGrade(int studentId, String courseName, char newFinalGrade) {
        String sql = "UPDATE Enrolment SET finalgrade ='" + newFinalGrade + "'WHERE student = " + studentId
                + " AND course = '" + courseName + "'";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public void displayEnrolment() {
        String sql = "SELECT * FROM Enrolment;";
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "enrols.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);

        TextIO.writeStandardOutput();
    }

    public void displaySortedEnrolment() {
        String sql = "SELECT * FROM Enrolment" +
                     "ORDER BY CASE WHEN finalgrade = 'E' THEN '1'" +
                     "              WHEN finalgrade = 'G' THEN '2'" +
                     "              WHEN finalgrade = 'P' THEN '3'" +
                     "              WHEN finalgrade = 'F' THEN '4';";
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "enrols.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);

        TextIO.writeStandardOutput();
    }

    public boolean validateSemester(int semester) {
        return ((semester >= 1) && (semester <=8));
    }

    public boolean validateFinalGrade(char finalGrade) {
        return ((finalGrade == 'E') || (finalGrade == 'G') || (finalGrade == 'P') || (finalGrade == 'F'));
    }
}
