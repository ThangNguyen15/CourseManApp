/**
 * Created by ThangNguyen.
 */
public class StudentManager {
    private DBApp dba = new DBApp();

    public boolean addStudent(int studentId, String firstName, String lastName, String address, String dOB) {

        String sql = "INSERT INTO Student VALUES(" + studentId + ",'" + firstName + "', '" + lastName + "', '" + address
                + "', '" + dOB + "');";
        System.out.println("Executing query: " + sql);
        dba.insert(sql);
        System.out.println("Add successfully!");
        System.out.println("Fail to add new student.");
        return true;
    }

    public boolean editFirstName(int studentId, String newFirstName) {
        String sql = "UPDATE student SET firstname ='" + newFirstName + "'WHERE studentid = " + studentId + "";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public boolean editLastName(int studentId, String newLastName) {
        String sql = "UPDATE student SET lastname ='" + newLastName + "'WHERE studentid = " + studentId + "";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public boolean editAddress(int studentId, String newAddress) {
        String sql = "UPDATE student SET address ='" + newAddress + "'WHERE studentid = " + studentId + "";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public boolean editDOB(int studentId, String newDOB) {
        String sql = "UPDATE student SET dateofbirth ='" + newDOB + "'WHERE studentid = " + studentId + "";
        System.out.println("Executing query: " + sql);
        dba.update(sql);
        System.out.println("Update successfully!");
        return true;
    }

    public boolean deleteStudent(int studentId) {
        String sql = "Delete from student where studentid = " + studentId + "";
        System.out.println("Executing query: " + sql);
        dba.delete(sql);
        System.out.println("Delete successfully!");
        return true;
    }

    public void displayStudent() {
        String sql = "SELECT * FROM student;";
        System.out.println("Executing query: " + sql);
        String result = dba.select(sql);
        String userDir = System.getProperty("user.dir");
        String fileChar = System.getProperty("file.separator");
        String file = userDir + fileChar + "students.html";
        TextIO.writeFile(file);
        TextIO.putln("<p>");
        TextIO.putln(result);

        System.out.println("Written result to file " + file);

        TextIO.writeStandardOutput();

    }

    public boolean validateStudentId(int studentId) {
        String sql = "SELECT* FROM Student WHERE studentid = " + studentId + ";";
        String result = dba.selectToString(sql);

        if (result.equals("")) {
            return false;
        } else
            return true;
    }

    public boolean validateFirstName(String firstName) {
        return ((firstName != null) && (firstName.length() > 0) && (firstName.length() <= 50));
    }

    public boolean validateLastName(String lastName) {
        return ((lastName != null) && (lastName.length() > 0) && (lastName.length() <= 50));
    }

    public boolean validateAddress(String address) {
        return ((address != null) && (address.length() > 0) && (address.length() <= 250));
    }

    public boolean validateDateOfBirth(String dOB) {
        return ((dOB != null) && (dOB.length() > 0) && (dOB.length() <= 30));
    }

    public boolean validateInfo(String firstName, String lastName, String address, String dOB) {
        return (validateFirstName(firstName) && validateLastName(lastName) && validateAddress(address) && validateDateOfBirth(dOB));
    }
}
