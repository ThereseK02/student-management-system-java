/**
 * KabayanjaThereseFinalProject.java   
 * Program that defines the TestingSystem class that is the master controller and entry point (main method) in the Student Management System.
 * The TestingSystem class performs a full system check on all project classes and ensures data persistence even in the event of a system crash.
 * @author Kabayanja, Therese
 * @assignment CSCI 428 Final Project
 * @date April 26, 2026 
 */

package studentManagementSystem;

public class TestingSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        System.out.println("**********************************************");
        System.out.println("   STUDENT MANAGEMENT SYSTEM BOOTLOADER    ");
        System.out.println("**********************************************\n");

     // a) Initializing the controller
        ManagementSystem engine = new ManagementSystem();

        try {
            System.out.println("*** [STAGE 1: Identity & User Verification] ***");
            
            // Testing User & Student logic
            // Using polymorphism: Student "is-a" User
            
            User testUser = new Student("Initial_Tester", "pass123", 0.0);
            System.out.println(">> User/Student Test: Identity '" + testUser.getAccountUsername() + "' verified.");
            testUser.displayRole(); 

            System.out.println("\n*** [STAGE 2: Course] ***");
            
            // Testing Course Class
            Course javaCourse = new Course("Java Programming", 4);
            System.out.println(">> Course Test: " + javaCourse.getCourseName() + " created (" + javaCourse.getCreditHours() + " hrs).");

            // *** [STAGE 3: Business logic validation] ***
            System.out.println("\n*** [STAGE 3: Business logic validation] ***");
            // Testing Admin Class for Student Management System
            
            Admin sysAdmin = new Admin("System_Root", "rootPass", "Registrar");
            System.out.println(">> Admin Test: " + sysAdmin.getAccountUsername() + " initialized for " + sysAdmin.getDepartmentAccess());

            Student testStudent = new Student("John_Test", "studentPass", 3.2);

            // Running the prediction logic
           
            String finalReport = sysAdmin.generateSuccessReport(testStudent, 1.1, 0.1);
            System.out.println(finalReport);
            
            // Testing Tuition logic via ManagementSystem
        
            double sampleTuition = engine.calculateStudentTuition("Goodness");
            
            if (sampleTuition != -1) {
                System.out.println(">> Logic test: Tuition for Goodness verified at $" + sampleTuition);
            }

            // Manual Data Sync (Pre-interaction)
            // Saving the state of the "Bootloader" tests before the user takes over
           
            System.out.println("\n>> Running Pre-interaction Data Sync...");
            engine.triggerDataSync();

            System.out.println("\n*** [STAGE 4: Launching interaction layer] ***");
            
            // b) Running the interactive Console Menu
            engine.runConsoleMenu();

        } catch (Exception e) {
            System.err.println("Critical System Error: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
        	
            // c) Ensuring final persistence Check (The Safety Net)
        	
            System.out.println("\n***[FINAL STAGE: Secure Shutdown] ***");
            engine.triggerDataSync();
            System.out.println("*****************************************");
            System.out.println("   System shutdown securely. Data persisted.  ");
            System.out.println("******************************************");
        }
    }
}