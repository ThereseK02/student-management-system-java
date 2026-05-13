
/**
 * Defines the abstract User class for the Student Management System.
 * 
 * The User class serves as the foundational abstraction for all system users, including Student and Admin subclasses. 
 * It implements the Authenticatable interface to provide authentication functionality while enforcing encapsulation
 * through controlled access to user credentials and account information.
 * 
 * Author: Therese Kabayanja
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
