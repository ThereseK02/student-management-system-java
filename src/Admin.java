/**
 * Defines the Admin role for the Student Management System.
 * The Admin class extends User and provides academic analytics features, including GPA forecasting and student performance reporting.
 * Author: Therese Kabayanja
 */

package studentManagementSystem;

public class Admin extends User {
	 private String departmentAccess;
	 
	 	// Constructor with parameters
	 
	    public Admin(String accountUsername, String accountPassword, String departmentAccess) {
	        super(accountUsername, accountPassword);
	        this.departmentAccess = departmentAccess;
	    }

	    // Performance Predictions using Linear Regression: y = (slope * x) + intercept 
	    
	    public double calculateForecast(double currentGpa, double slope, double intercept) {
	        double predicted = (slope * currentGpa) + intercept;
	        return Math.min(4.0, Math.max(0.0, predicted)); // setting GPA between 0.0 and 4.0
	    }

	    // Converting results into a performance report, demonstrating interaction between Admin logic and student data. 
	    
	    public String generateSuccessReport(Student student, double slope, double intercept) {
	        // Using the simple linear regression formula: y = mx + b
	        double forecastedGpa = (slope * student.getCurrentAcademicGpa()) + intercept;
	        
	        // Cap the GPA so it doesn't exceed 4.0
	        if (forecastedGpa > 4.0) forecastedGpa = 4.0;

	        // Humanized verdict logic
	        String verdict;
	        if (forecastedGpa >= 3.5) verdict = "High Distinction Potential";
	        else if (forecastedGpa >= 2.0) verdict = "Satisfactory Progress";
	        else verdict = "Academic Intervention Advised";

	        StringBuilder report = new StringBuilder();
	        report.append("\n*******************************************\n");
	        report.append("       OFFICIAL PERFORMANCE REPORT          \n");
	        report.append("********************************************\n");
	        report.append("Analyst: ").append(getAccountUsername()).append(" (").append(departmentAccess).append(")\n");
	        report.append("============================================\n");
	        report.append("Student Name:    ").append(student.getAccountUsername()).append("\n");
	        report.append("Current GPA:     ").append(student.getCurrentAcademicGpa()).append("\n");
	        report.append("Predicted GPA:  ").append(String.format("%.2f", forecastedGpa)).append("\n");
	        report.append("============================================\n");
	        report.append("DEPARTMENT VERDICT: ").append(verdict).append("\n");
	        report.append("******************************************\n");
	        
	        return report.toString();
	    }
	    @Override
	    public void displayRole() {
	        System.out.println("Access Level: Admin | Dept: " + departmentAccess + " | Analyst: " + getAccountUsername());
	    }

	    // Getter and Setter
	    public String getDepartmentAccess() { 
	    	return departmentAccess; 
	    }
	    public void setDepartmentAccess(String departmentAccess) { 
	    	this.departmentAccess = departmentAccess; 
	    }
	}

