
/**
 * KabayanjaThereseFinalProject.java   
 * Program that defines the abstract class User in the Student Management System. 
 * This class User that has two subclasses (Student and Adm) in this system implements 
 * the interface Authenticatable method, and naturally ensures encapsulation in Java. 
 * @author Kabayanja, Therese
 * @assignment CSCI 428 Final Project
 * @date April 26, 2026 
 */

package studentManagementSystem;

public abstract class User implements Authenticatable {
	
	private String accountUsername;
	private String accountPassword;
	
	// Constructors:
	public User() {						// Default constructor
	    this.accountUsername = "";
	    this.accountPassword = "";
	}
	public User(String username, String password) {   // Constructor with parameters 
	    this.accountUsername = username;
	    this.accountPassword = password;
	}

	// Encapsulation - Public getters and setters for private fields
	
	public String getAccountUsername() { return accountUsername; }
	public String getAccountPassword() { return accountPassword; }
	public void setAccountUsername(String newName) {
	    this.accountUsername = newName;
	}
	    
	// Abstract method overridden by subclasses Student and Admin
	
	public abstract void displayRole();

	// Implementation of the interface method
	
	@Override
	public boolean login(String username, String password) {
	    return this.accountUsername.equals(username) && this.accountPassword.equals(password);
	}
}
