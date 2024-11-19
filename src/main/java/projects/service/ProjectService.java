package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import projects.dao.ProjectDao;
import projects.entity.Project;


public class ProjectService {
	  private ProjectDao projectDao = new ProjectDao();
	
	// wk method calls  DAO class Insert(s)  Row 
	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);

	}
 // Wk 10 New Method fetch Projects and DAO classes
	
	public List<Project> fetchAllProjects() {
	    return projectDao.fetchAllProjects();
	  }
    // wk 10 New exception w/  Lamada Express ... 
	
	//Optional<Project> op projectDao.fetchProjectById(projectId);
	//return null;
	
	public Project fetchProjectById(Integer projectId) {
	    return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
	        "Project with project ID=" + projectId + " does not exist."));
	  }
}
