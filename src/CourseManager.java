/**
 * Created by ThangNguyen.
 */
public class CourseManager {
    private DBApp dba = new DBApp();

    public boolean addCourse(String courseId, String courseName, String prerequisites) {
        String sql = "INSERT INTO Course VALUES('" + courseId + "','" + courseName + "', '" + prerequisites + "');";
        System.out.println("Executing query: " + sql);
        dba.insert(sql);
        System.out.println("Add successfully!");
        return true;
    }

    public boolean editCourseName(String courseId, String newCourseName) {
        String sql = "UPDATE Course SET name ='" + newCourseName + "'WHERE courseid = " + courseId + "";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public boolean editCoursePrerequisites(String courseId, String newPrerequisites) {
        String sql = "UPDATE Course SET prerequisites ='" + newPrerequisites + "'WHERE courseid = " + courseId + "";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public boolean deleteCourse(String courseId) {
        String sql = "DELETE FROM Course WHERE courseid = '" + courseId + "'";
        System.out.println("Executing query: " + sql);
        dba.delete(sql);
        System.out.println("Delete successfully!");
        return true;
    }

    public void displayCourse() {
        String sql = "SELECT * FROM Course;";
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "courses.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);

        TextIO.writeStandardOutput();
    }

    public boolean validateCourseId(String courseId) {
        String sql = "SELECT courseid FROM Course";
        String result = dba.selectToString(sql);

        if (result.contains(courseId)) {
            return true;
        } else
            return false;
    }

    public boolean validateCourseName(String courseName) {
        return ((courseName != null) && (courseName.length() > 0) && (courseName.length() <= 200));
    }

    public boolean validatePrerequisites(String prerequisites) {
        return ((prerequisites != null) && (prerequisites.length() > 0) && (prerequisites.length() <= 250));
    }
}
