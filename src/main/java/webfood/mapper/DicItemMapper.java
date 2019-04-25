package webfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import webfood.model.DicItem;
import webfood.vo.ItemVo;

public interface DicItemMapper
{
	int deleteByPrimaryKey(Integer id);

	int insert(DicItem record);

	int insertSelective(DicItem record);

	DicItem selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DicItem record);

	int updateByPrimaryKey(DicItem record);

	List<DicItem> queryItem(String className);

	DicItem queryItemName(@Param("sql") String sql);

	List<ItemVo> queryItemAll();
}