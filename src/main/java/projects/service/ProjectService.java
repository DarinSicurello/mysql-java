package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;


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
    // wk 10 New exception w/  Lambda Expression ... 
	
	//Optional<Project> op projectDao.fetchProjectById(projectId);
	//return null;
	
	public Project fetchProjectById(Integer projectId) {
	    return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
	        "Project with project ID=" + projectId + " does not exist."));
	  }
// Wk 11 1a Changes to Project Service 
	public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) {
		      throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
		    }
		  }

	public void deleteProject(Integer projectId) {
	    if(!projectDao.deleteProject(projectId)) {
	      throw new DbException("Project with ID=" + projectId + " does not exist.");
	    }
	  }
}
