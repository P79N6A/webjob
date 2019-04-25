package webfood.mapper;

import webfood.model.DicClass;

public interface DicClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicClass record);

    int insertSelective(DicClass record);

    DicClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicClass record);

    int updateByPrimaryKey(DicClass record);
}