/**
 * Defines the ManagementSystem controller class for the Student Management System.
 * The ManagementSystem class serves as the core controller of the application, managing user data, tuition calculation, file input/output operations, 
 * and overall system functionality. It coordinates both console-based interactions 
 * and JavaFX graphical interface operations within the system.
 *
 * Author: Therese Kabayanja
 */

package studentManagementSystem;

import java.util.*;
import java.io.*;

public class ManagementSystem {
		// Field/Attributes involve a polymorphic list storing both Students and Admins:
		// Combining tuition calculation, categorical analysis, and File I/O.
	    
	private List<User> userList = new ArrayList<>();
	private final String DATABASE_FILE = "student_database.txt";
	private final String RESULTS_LOG = "system_results.txt";
	private Scanner consoleInput = new Scanner(System.in);
	   
	    // Constructor:
	    
	public ManagementSystem() {
	    this.userList = new ArrayList<>();
	    
	    	// Loading what is already on the hard drive
	        loadDatabase(); 
	        
	        // If the file was empty, provide a starting point
	        
	    if (userList.isEmpty()) {
	        Student testStudent = new Student("John", "pass123", 4.0);
	        testStudent.setCurrentAcademicGpa(4.0);
	        testStudent.enrollInCourse(new Course("Cybersecurity", 3));
	        testStudent.enrollInCourse(new Course("Data Science", 4));
	        userList.add(testStudent);
	        System.out.println(">> Database was empty. Initialized with test student: John");
	    } else {
	        System.out.println(">> Database loaded successfully. Total records: " + userList.size());
	    }
	}
	  
	    // Console menu system:
	 
	public void runConsoleMenu() {
	    int choice = -1;
	    while (choice != 0) {
	        System.out.println("\n******************************************");
	        System.out.println("    INTELLIGENT SMS CONSOLE (v2.0)       ");
	        System.out.println("******************************************");
	        System.out.println("1. View All System Users");
	        System.out.println("2. Update Account Identity (Rename)");
	        System.out.println("3. Calculate Tuition ($200/credit)");
	        System.out.println("4. Run AI Success Prediction (Regression)");
	        System.out.println("5. Create New Student Account");
	        System.out.println("6. Override/Update Student GPA");
	        System.out.println("0. Save and Exit");
	        System.out.print("\nSelection: ");

	        try {
	            choice = Integer.parseInt(consoleInput.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a digit:");
	            continue;
	        }

	        switch (choice) {
	            case 1 -> displayAllUsers();  // the arrow syntax has an implicit break.
	            case 2 -> handleConsoleUpdate();
	            case 3 -> handleConsoleTuition();
	            case 4 -> handleConsoleAnalysis();
	            case 5 -> handleCreateStudent();
	            case 6 -> handleConsoleGpaUpdate();
	            case 0 -> {
	            	System.out.println(">> Persistence Engine: Saving to student_database.txt...");
	                    saveDatabase();
	                    System.out.println(">> System shutdown complete.");
	                }
	            default -> System.out.println("Invalid selection. Try again:");
	        }
	    }
	}

	    // Business Logic: Tuition calculation by multiplying total credit hours by a flat rate of $200.
	    
	public double calculateStudentTuition(String studentName) {
	    User u = findUser(studentName);
	    if (u instanceof Student s) {
	        int totalCredits = 0;
	        for (Course c : s.getAcademicCourseLoad()) {
	            totalCredits += c.getCreditHours();
	        }
	        return totalCredits * 200.0;
	    }
	    return -1.0;
	    }

	    // To analyze performance, the controller ManagementSystem class uses the class Admin analyst to 
		// generate a student categorical report. 
	    
	public String runAcademicAnalysis(String studentName) {
	    User u = findUser(studentName);
	    if (u instanceof Student s) {
	        Admin analyst = new Admin("System_Analyst", "root", "Registrar");
	           
	        // Standard regression variables: Slope 0.85, Intercept 0.3
	        String report = analyst.generateSuccessReport(s, 0.85, 0.3);
	        logOutput(report); // Automatically save this report to the log file
	        return report;
	    }
	    return "Error: " + studentName + " is not a valid Student record.";
	}
	    // Getters and Setters:
	    
	public List<User> getUsers() {
	    return this.userList;
	} 
	 	// Updating a student's GPA directly .
	    
	public void updateStudentGpa(String name, double newGpa) {
	    User u = findUser(name);
	    if (u instanceof Student s) {
	        s.setCurrentAcademicGpa(newGpa);
	        saveDatabase(); // Ensure the new grade is saved to the text file!
	        System.out.println("GPA updated successfully for " + name);
	    } else {
	        System.out.println("Error: Could not find a student named " + name);
	    }
	}
	    
	      // Automating File input output (I/O): saveDatabase for the data persistence logic. Iterating through the userList and writing
		  // each record to the text file, ensuring that name changes, GPA updates, and new users are remembered. 

	private void saveDatabase() {
	      
		// We use a try-catch structure to ensure the file closes even if there is an error
	    
		try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter("student_database.txt"))) {
	            
	        for (User u : userList) {
	                // Determine if the record is a Student or Admin
	            String type = (u instanceof Student) ? "STUDENT" : "ADMIN";
	                
	            if (u instanceof Student s) {
	                // Save format: TYPE, USERNAME, PASSWORD, GPA
	                // Note: We use getInternalPassword() from our earlier security discussion
	                writer.println(type + "," + s.getAccountUsername() + "," + 
	                               s.getAccountPassword() + "," + s.getCurrentAcademicGpa());
	            } else {
	                // Save format for Admin: TYPE, USERNAME, PASSWORD
	                writer.println(type + "," + u.getAccountUsername() + "," + u.getAccountPassword());
	            }
	        }
	            // No need for writer.close() because the try-catch structure handles it!
	            
	    } catch (java.io.IOException e) {
	        System.err.println("Critical Error: Could not save data to disk. " + e.getMessage());
	    }
	 }
	        // Public bridge to trigger a database save from outside the class.
	         
	public void triggerDataSync() {
	    saveDatabase();
	    System.out.println(">> Manual sync: Data persisted to disk.");
	}
	    
	    // Recovering database: Reads users from the .txt file back into memory.
	    
	private void loadDatabase() {
	    File file = new File(DATABASE_FILE);
	    if (!file.exists()) 
	    	return;

	    userList.clear();
	    try (Scanner fileScanner = new Scanner(file)) {
	        while (fileScanner.hasNextLine()) {
	            String[] data = fileScanner.nextLine().split(",");
	            if (data.length < 3) continue;

	            if (data[0].equals("STUDENT")) {
	                // 1. Extract GPA from data[3] if it exists, otherwise default to 0.0
	                double initialGpa = (data.length >= 4) ? Double.parseDouble(data[3]) : 0.0;

	                // 2. Create the student using the new 3-parameter constructor
	                Student s = new Student(data[1], data[2], initialGpa);

	                // 3. Give them their default course load so the tuition math works
	                s.enrollInCourse(new Course("Restored Credits", 7)); 
	                userList.add(s);
	                
	            } else {
	                userList.add(new Admin(data[1], data[2], "Staff"));
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Load Error: " + e.getMessage());
	    }
	}

	    //LOGGING: Appends results to system_results.txt.
	    
	private void logOutput(String data) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULTS_LOG, true))) {
	        writer.write("\n[Timestamp: " + new java.util.Date() + "]\n");
	        writer.write(data + "\n");
	        writer.write("*********************************************\n");
	    } catch (IOException e) {
	        System.err.println("Log Write Error: " + e.getMessage());
	    }
	}

	    // Helpers and handlers: 
	  
	public User findUser(String name) {
	    for (User u : userList) {
	    
	    	// .trim() handles accidental spaces, .equalsIgnoreCase handles typing errors
	        
	    	if (u.getAccountUsername().trim().equalsIgnoreCase(name.trim())) {
	            return u;
	    }
	    }
	    return null;
	}
	private void displayAllUsers() {
	    if (userList.isEmpty()) System.out.println("Database is currently empty.");
	    for (User u : userList) {
	        u.displayRole();
	    }
	}

	private void handleConsoleUpdate() {
	    System.out.print("Enter Current Name: ");
	    String oldN = consoleInput.nextLine();
	    System.out.print("Enter New Name: ");
	    String newN = consoleInput.nextLine();
	        
	    User u = findUser(oldN);
	    if (u != null) {
	        u.setAccountUsername(newN);
	        saveDatabase(); // auto-save after update
	        System.out.println("Update Successful and Saved to Disk.");
	    } else {
	        System.out.println("User not found.");
	    }
	}

	private void handleConsoleTuition() {
	    System.out.print("Enter Student Name: ");
	    String name = consoleInput.nextLine();
	    double total = calculateStudentTuition(name);
	    
	    if (total >= 0) {
	        String receipt = "Tuition for " + name + ": $" + total;
	        System.out.println(receipt);
	        logOutput(receipt); // Record the tuition calculation
	    } else {
	        System.out.println("Error: '" + name + "'not found. Did you rename this User?");
	    }
	}

	private void handleConsoleAnalysis() {
	    System.out.print("Enter Student Name for Analysis: ");
	    String name = consoleInput.nextLine();
	    System.out.println(runAcademicAnalysis(name));
	}
	
	private void handleCreateStudent() {
	    System.out.print("Enter Name for New Student: ");
	    String name = consoleInput.nextLine();
	    System.out.print("Create Password: ");
	    String pass = consoleInput.nextLine();

	    Student newStudent = new Student(name, pass, 0.0);
	   
	    // Giving them default test data so they aren't empty
	   
	    newStudent.setCurrentAcademicGpa(0.0);
	    newStudent.enrollInCourse(new Course("General Education", 3));
	        
	    userList.add(newStudent);
	    saveDatabase();
	    System.out.println("Student " + name + " created and saved successfully.");
	}
	
	private void handleConsoleGpaUpdate() {
	    System.out.print("Enter Student Name: ");
	    String name = consoleInput.nextLine();
	    System.out.print("Enter New Cumulative GPA (0.0 - 4.0): ");
	        
	    try {
	        double gpa = Double.parseDouble(consoleInput.nextLine());
	        if (gpa >= 0 && gpa <= 4.0) {
	            updateStudentGpa(name, gpa);
	        } else {
	            System.out.println("Invalid GPA range. Please use 0.0 to 4.0:");
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input. Please enter a decimal number for GPA:");
	    }
	}
}
