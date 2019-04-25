package webfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import webfood.model.User;
import webfood.vo.MenuVo;

@Repository
public interface UserMapper
{

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	// 通过用户username精确查询用户
	public User findUserByUsername(String username);

	List<MenuVo> getMenu(@Param("sql") String sql);

}
