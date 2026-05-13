/**
 * Defines the Student class for the Student Management System.
 * The Student class extends the abstract User class and represents the academic profile of a student within the system. 
 * It stores academic information, participation records, and a collection of enrolled Course objects.
 * The class utilizes the List interface with an ArrayList implementation to provide flexible and efficient course management operations.
 * Author: Therese Kabayanja
 */

package studentManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{

	 private double currentAcademicGpa;
	 private double participationRate;
	    
	    // A Student 'has-a' list of Courses: Composition. The list of Courses acts as the student's active semester load.
	    
	 private List<Course> academicCourseLoad = new ArrayList<>();
	 
	 // Default constructor:
	 
	 public Student() {   
		 super(); // Calls the User default constructor 
	     this.currentAcademicGpa = 0.0;
	     this.participationRate = 0.0;
	  }

	// Parameterized constructor: 
	 public Student(String accountUsername, String accountPassword, double currentAcademicGpa) {   // constructor with parameters
	        super(accountUsername, accountPassword); // Calls the parent User constructor 
	        this.currentAcademicGpa = currentAcademicGpa; 
	        this.participationRate = 0.0;
	    }

	    // Methods for Course management:

	 public void enrollInCourse(Course course) {
	        if (course != null) {
	            this.academicCourseLoad.add(course);
	        }
	    }

	 public List<Course> getAcademicCourseLoad() { 
	        return academicCourseLoad; 
	    }

	    // Academic attributes / Getters and Setters:

	 public double getCurrentAcademicGpa() { 
	        return currentAcademicGpa; 
	    }
	    
	 public void setCurrentAcademicGpa(double currentAcademicGpa) { 
	        this.currentAcademicGpa = currentAcademicGpa; 
	    }

	 public double getParticipationRate() { 
	        return participationRate; 
	    }
	    
	 public void setParticipationRate(double participationRate) { 
	        this.participationRate = participationRate; 
	    }
	 // Override: implementation of abstract displayRole () method using getAccountUsername() from the parent User class.

	 @Override
	 public void displayRole() {
	        System.out.println("Access Level: Student | Identity: " + getAccountUsername());
	    }

	}

