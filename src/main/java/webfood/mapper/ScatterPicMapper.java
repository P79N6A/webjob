package webfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import webfood.model.ScatterPic;

public interface ScatterPicMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ScatterPic record);

	int insertSelective(ScatterPic record);

	ScatterPic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ScatterPic record);

	int updateByPrimaryKey(ScatterPic record);

	List<ScatterPic> queryByPidAndType(@Param("id") Integer id, @Param("type") Integer type);

	void deleteByPid(@Param("id") Integer id, @Param("type") Integer type);
}