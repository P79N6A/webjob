package webfood.mapper;

import java.util.List;

import webfood.model.ProjectBuild;

public interface ProjectBuildMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProjectBuild record);

	int insertSelective(ProjectBuild record);

	ProjectBuild selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProjectBuild record);

	int updateByPrimaryKey(ProjectBuild record);

	List<ProjectBuild> queryByPid(Integer id);

	void deleteByPid(Integer id);
}