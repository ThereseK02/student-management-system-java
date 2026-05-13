/**
 * Defines the Course class for the Student Management System.
 * The Course class represents an academic subject within the system and stores course-related information such as course name and credit hours. 
 * Credit hour data is used in tuition calculation and academic management operations.
 * Author: Therese Kabayanja
 */

package studentManagementSystem;

public class Course {

    private String courseName;
    private int creditHours;
    
    //Constructor
    
    public Course(String courseName, int creditHours) {
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    // Accessors and mutators

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;   // We assume that this value is multiplied by $200 in the controller ManagementSystem class.
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    // Testing console using the toString () method:

    @Override
    public String toString() {
        return courseName + " (" + creditHours + " Credits)";
    }
}
