package webfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import webfood.model.Project;

public interface ProjectMapper
{
	int deleteByPrimaryKey(Integer id);

	int insert(Project record);

	int insertSelective(Project record);

	Project selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Project record);

	int updateByPrimaryKey(Project record);

	List<Project> queryAll();

	List<Project> query(@Param("sqlWhere") String sqlWhere);
}