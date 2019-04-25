package webfood.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import webfood.model.Project;
import webfood.model.ScatterProject;

public interface ProjectService {

	void deleteById(Integer id);

	void deleteSById(Integer id);

	void insert(Project project);

	List<Project> queryAll();

	Project queryById(Integer id);

	void update(Project project);

	List<ScatterProject> queryAllSp();

	void updateSp(ScatterProject project);

	void insertSp(ScatterProject project);

	ScatterProject querySprojectById(int id);

	List<Project> query(StringBuffer sqlWhere);

	List<ScatterProject> querySp(StringBuffer sqlWhere);

	String insertPic(HttpServletRequest request);

}
