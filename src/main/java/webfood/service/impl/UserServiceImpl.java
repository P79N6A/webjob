package webfood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webfood.mapper.UserMapper;
import webfood.model.User;
import webfood.service.UserService;
import webfood.vo.MenuVo;

@Service()
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserMapper userDao;

	@Override
	public User findUserByUsername(String username)
	{
		return userDao.findUserByUsername(username);

	}

	@Override
	public List<String> getMenuListByUserId(Integer id)
	{
		String sql = "select  a.id, a.menu_code, a.title, a.url,  a.remark from hz_menu a, hz_role b, hz_role_menu c where c.menu_id=a.id and c.role_id=b.id and c.role_id in (select role_id from hz_user_role where user_id="
				+ id + ") order by a.order_no";
		List<MenuVo> list = userDao.getMenu(sql);

		List<String> result = new ArrayList<String>();
		for (MenuVo vo : list)
		{
			result.add(vo.getTitle() + "|" + vo.getMenuCode() + "|" + vo.getUrl() + "|_blank||" + vo.getRemark());
		}
		return result;
	}

}
