/**
 * Defines the Authenticatable interface for the Student Management System.
 * This interface establishes the authentication contract for users who are authorized to access the system. 
 * The abstract User class implements this interface to provide login functionality across the application.
 * Author: Therese Kabayanja
 */

package studentManagementSystem;

public interface Authenticatable {
	  // Methods in interfaces are public and abstract by default
    boolean login(String username, String password);
}
