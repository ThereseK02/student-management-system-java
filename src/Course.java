/**
 * KabayanjaThereseFinalProject.java   
 * Program that defines the class Course that represents an individual academic subject. 
 * The class Course provides the Credit Hour data to calculate tuition. 
 * @author Kabayanja, Therese
 * @assignment CSCI 428 Final Project
 * @date April 26, 2026 
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
