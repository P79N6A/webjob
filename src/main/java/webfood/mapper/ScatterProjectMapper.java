package webfood.mapper;

import java.util.List;

import webfood.model.ScatterProject;

public interface ScatterProjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ScatterProject record);

	int insertSelective(ScatterProject record);

	ScatterProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ScatterProject record);

	int updateByPrimaryKey(ScatterProject record);

	List<ScatterProject> queryAll();

	List<ScatterProject> query(StringBuffer sqlWhere);
}