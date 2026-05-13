/**
 * KabayanjaThereseFinalProject.java   
 * Program that defines the interface Authenticatable of the student management system. The interface defines 
 * the contract for any user that can log in. In this system, the abstract class User implements the Interface Authenticatable. 
 * @author Kabayanja, Therese
 * @assignment CSCI 428 Final Project
 * @date April 26, 2026 
 */

package studentManagementSystem;

public interface Authenticatable {
	  // Methods in interfaces are public and abstract by default
    boolean login(String username, String password);
}
