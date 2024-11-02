package projects;

import projects.dao.DbConnections;

public class ProjectsApp {

	public static void main(String[] args) {
		DbConnections.getConnection();
	}

}
