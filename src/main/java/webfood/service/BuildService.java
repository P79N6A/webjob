package webfood.service;

import java.util.List;

import webfood.model.ProjectBuild;

public interface BuildService {

	List<ProjectBuild> queryByPid(int pid);

}
