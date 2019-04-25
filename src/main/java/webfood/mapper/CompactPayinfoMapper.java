package webfood.mapper;

import java.util.List;

import webfood.model.CompactPayinfo;

public interface CompactPayinfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CompactPayinfo record);

	int insertSelective(CompactPayinfo record);

	CompactPayinfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CompactPayinfo record);

	int updateByPrimaryKey(CompactPayinfo record);

	List<CompactPayinfo> queryByCpid(Integer id);
}