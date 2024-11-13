package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

// JDBC Driver  for Big Decimal from Java to SQL 



public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();

	//aQ1  Delete DBconnection and 
	//aQ2 Remove Import projects dao DBConn

	// Formatter:off
	private List<String> operations = List.of(
		"1) Add a Project"
	);
	
	// @formatter:on
	
	
	//Q1 - Q4 Create Scanner & Method -->
	
	public static void main(String[] args) {
		new ProjectsApp().processUserSelections(); 
	}
	// q5 Create method > 
	
	private void processUserSelections() {
		boolean done = false;
		
		while(!done) {	
		 try {
		     int selection = getUserSelection();
			 
			 switch(selection) {
			 case -1:
				 done = exitMenu();
				 break;
				 
			 case 1:
				 createProject();
				 break;
				 
			default:
				System.out.println("\n" + selection + " is not a vaild selection. Try Again.");
			 }	 
		 }
		 catch(Exception e) {
			 System.out.println("\nError: " + e + " Try again.");
		 }
		}
	}

	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimateHours = getDecimalInput("Enter the Estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the acutal hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project Notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimateHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		//ProjectService projectService = null;
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created a project: " + dbProject);
	}
	
	// Create method of Big decimal  set it back 00.2 to two decimal places
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
		  return null;
		}

		try {
		  return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	}
	
	private boolean exitMenu() {
		System.out.println("Exciting the Menu.");
		return true;
	}
	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection"); 
			 
		return Objects.isNull(input) ? -1 : input;
	}
	
		// Q 7 User Inputs method objects
	private Integer getIntInput(String prompt) {
		 String input = getStringInput(prompt);
			
			if (Objects.isNull(input)) {
			return null;
			}
			
			try {
			return Integer.valueOf(input);
			}
			catch(NumberFormatException e) {
				throw new DbException(input + " is not a decimal valid number.");
			}
		}

		// Q8 Print Methods Prints Prompts 
		
		private String getStringInput(String prompt) {
			System.out.println(prompt + ": ");
			String input = scanner.nextLine();
			
			return input.isBlank() ? null : input.trim();
		}
		
		//Q6 Create Print Menu Method 
		
		private void printOperations() {
			System.out.println("\nThese are the available Selections. Press the Enter key to quit:");
			operations.forEach(line -> System.out.println("  " +line));
		
		}	
	
}